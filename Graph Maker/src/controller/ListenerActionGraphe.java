package controller;

import dialogue.*;
import graphe.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class ListenerActionGraphe implements ActionListener {

	private GrapheView gv;

	public ListenerActionGraphe(GrapheView gv){
		this.gv=gv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.gv.getBarcarrete()==e.getSource()){
			if(this.gv.getGraphe().isType()){
				this.gv.getOnglets().setEnabledAt(2, true);
				this.gv.getBarcarrete().setIcon(new ImageIcon("img/nonorienter.png"));
			}
			else{
				this.gv.getOnglets().setEnabledAt(2, false);
				this.gv.getBarcarrete().setIcon(new ImageIcon("img/orienter.png"));
			}
			this.gv.getGraphe().switchTypeOfGraphe();
		}
		if(e.getSource() == gv.getBreset()){
			this.gv.getGraphe().getSommets().clear();
			this.gv.getGraphe().getArcs().clear();
			this.gv.getGraphe().getArcinit().clear();
			this.gv.getDijkstra().setArrivee(null);
			this.gv.getDijkstra().setSource(null);
		}
		if(e.getSource()==gv.getJtfNode()){
			this.gv.getGraphe().setTailleSommet(isInteger(gv.getJtfNode().getText()));
			this.gv.getJtfNode().setText(gv.getJtfNode().getText());
		}

		if(e.getSource() == gv.getDist())
			this.gv.getGraphe().setDist(isInteger(gv.getDist().getText()));

		if(e.getSource() == gv.getMargep())
			gv.getGraphe().setMargelinecolumn(isInteger(gv.getMargep().getText()));

		if(e.getSource() == gv.getTaillec())
			gv.getGraphe().setLengthlinecolumn(isInteger(gv.getTaillec().getText()));

		if(e.getSource() == gv.getGenerate())
		{
			try{
				gv.getAff().setText(gv.getGraphe().matrixLatexToString(gv.getGraphe()
						.matrixLatex(gv.getGraphe().initTab(gv.getGraphe().getMargelinecolumn()), gv.getGraphe().getMargelinecolumn()
								, gv.getGraphe().getLengthlinecolumn()), gv.getGraphe().getMargelinecolumn() , gv.getGraphe().getLengthlinecolumn(),
								gv.getGraphe().initTab(gv.getGraphe().getMargelinecolumn())));
			}
                        catch(TailleColumnLineException ec)
                        {
                            JLabel affichage = new JLabel("<html><p>Le code que vous essayez de générer n'est pas bon.<br>La taille que vous avez indiqué est mauvaise.<br><br><span color=red>Attention : Vous devez entrer une valeur de taille<br>strictement supérieur à 0 pour que la génération s'effectue.</span></p></html>");
				JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Taille_colonne_ligne_erroné",0);
                        }
			catch(NullPointerException ec)
			{
				JLabel affichage = new JLabel("<html><p>Le code que vous essayez de générer n'est pas bon.<br>Votre graphe ne contient sûrement aucun sommet.<br><br><span color=red>Attention : Vous devez créer au moins un sommet<br>pour que la génération s'effectue.</span></p></html>");
				JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Graphe_inexistant",0);
			}
			catch(ArrayIndexOutOfBoundsException ec)
			{
				JLabel affichage = new JLabel("<html><p>Le code que vous essayez de générer n'est pas bon.<br>Cela provient du fait que la marge que vous avez entré<br>n'est pas correcte.<br><br><span color=red>Attention : La marge de comparaison doit être strictement<br>positive pour que la génération s'effectue</span></p></html>");
				JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Marge_incorrecte",0);
			}
		}

		if(e.getSource() == gv.getRename())
		{
			String val = (String) JOptionPane.showInputDialog(null,
					"Modifier le nom du graphe", "Nouveau nom", JOptionPane.QUESTION_MESSAGE, null, null, "");
			String s = val.trim();
			if (s.length() > 0) 
				gv.getGraphe().setNom(val);
			gv.getInfo().setText((gv.getGraphe().toString(Graphe.GENERAL)));
			gv.getInfo().repaint();
		}

		if(e.getSource() == gv.getCreate())
		{
			if(gv.getGraphe().getTabCick().size() > 1)
			{
				if(gv.getClikc().isSelected())
					gv.getGraphe().createClique(gv.getGraphe().getDist(),gv.getGraphe().getTabCick());
				else if(gv.getChainec().isSelected())
					gv.getGraphe().createChaine(gv.getGraphe().getDist(),gv.getGraphe().getTabCick());
				else
					gv.getGraphe().createCycle(gv.getGraphe().getDist(),gv.getGraphe().getTabCick());
				gv.getGraphe().setTabCick(new ArrayList<Sommet>());
			}
		}
	
		if(e.getSource() == gv.getClik())
			this.gv.getGraphe().createClique(gv.getGraphe().getDist());
		if(e.getSource() == gv.getCycle())
			gv.getGraphe().createCycle(gv.getGraphe().getDist(),gv.getGraphe().getArcinit());
		if(e.getSource() == gv.getChaine())
			gv.getGraphe().createChaine(gv.getGraphe().getDist(),gv.getGraphe().getArcinit());
		if(this.gv.getBrelaz() == e.getSource())
			this.gv.setColors(this.gv.getGraphe().coloration());      

		this.gv.getJpb().repaint();
		this.gv.getJpg().repaint();
		this.gv.grapheMetrique();
	}

	public int isInteger(String s) {
		int val=50;
		try { 
			val =Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
		} catch(NullPointerException e) {
		}
		return val;
	}
}