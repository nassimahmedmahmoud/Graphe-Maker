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
                                //System.out.println("Marge av : "+gv.getGraphe().getMargelinecolumn());
                                //System.out.println("Taille av : "+gv.getGraphe().getLengthlinecolumn());
                                gv.getGraphe().setMargelinecolumn(Integer.parseInt(gv.getMargep().getText()));//isInteger(gv.getMargep().getText()));
                                gv.getGraphe().setLengthlinecolumn(Integer.parseInt(gv.getTaillec().getText()));//isInteger(gv.getTaillec().getText()));
                                //System.out.println("Marge ap : "+gv.getGraphe().getMargelinecolumn());
                                //System.out.println("Taille ap : "+gv.getGraphe().getLengthlinecolumn());
				gv.getAff().setText(gv.getGraphe().matrixLatexToString(gv.getGraphe()
						.matrixLatex(gv.getGraphe().initTab(gv.getGraphe().getMargelinecolumn()), gv.getGraphe().getMargelinecolumn()
								, gv.getGraphe().getLengthlinecolumn()), gv.getGraphe().getMargelinecolumn() , gv.getGraphe().getLengthlinecolumn(),
								gv.getGraphe().initTab(gv.getGraphe().getMargelinecolumn())));
			}
                        catch(NumberFormatException ec)
                        {
                                JLabel affichage = new JLabel("<html><p>Le code que vous essayez de générer n'est pas bon.<br>La valeur pour la taille ou la marge indiquée est incorrecte.<br><br><span color=red>Attention : Les valeurs de taille et de marge doivent<br>être des nombres entiers supérieur à 0.</span></p></html>");
				JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Valeur_de_champs_erroné",0);
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
                                try
                                {
                                        gv.getGraphe().setDist(Integer.parseInt(gv.getDist().getText()));
                                        if(gv.getClikc().isSelected())
                                                gv.getGraphe().createClique(gv.getGraphe().getDist(),gv.getGraphe().getTabCick());
                                        else if(gv.getChainec().isSelected())
                                                gv.getGraphe().createChaine(gv.getGraphe().getDist(),gv.getGraphe().getTabCick());
                                        else
                                                gv.getGraphe().createCycle(gv.getGraphe().getDist(),gv.getGraphe().getTabCick());
                                        gv.getGraphe().setTabCick(new ArrayList<Sommet>());
                                }
                                catch(NumberFormatException ec)
                                {
                                        JLabel affichage = new JLabel("<html><p>Impossible de créer le graphe.<br>La distance indiquée est incorrecte.<br><br><span color=red>Attention : La distance par défaut des arcs doit<br>correspondre un nombre entier.</span></p></html>");
                                        JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Valeur_de_champs_erroné",0);
                                }
			}
		}
	
		if(e.getSource() == gv.getClik())
                {
                        try
                        {
                            gv.getGraphe().setDist(Integer.parseInt(gv.getDist().getText()));
                            gv.getGraphe().createClique(gv.getGraphe().getDist());
                        }
                        catch(NumberFormatException ec)
                        {
                            JLabel affichage = new JLabel("<html><p>Impossible de créer le graphe.<br>La distance indiquée est incorrecte.<br><br><span color=red>Attention : La distance par défaut des arcs doit<br>correspondre un nombre entier.</span></p></html>");
                            JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Valeur_de_champs_erroné",0);
                        }
                }
		if(e.getSource() == gv.getCycle())
                {
                        try
                        {
                            gv.getGraphe().setDist(Integer.parseInt(gv.getDist().getText()));
                            gv.getGraphe().createCycle(gv.getGraphe().getDist(),gv.getGraphe().getArcinit());
                        }
                        catch(NumberFormatException ec)
                        {
                            JLabel affichage = new JLabel("<html><p>Impossible de créer le graphe.<br>La distance indiquée est incorrecte.<br><br><span color=red>Attention : La distance par défaut des arcs doit<br>correspondre un nombre entier.</span></p></html>");
                            JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Valeur_de_champs_erroné",0);
                        }
                }
		if(e.getSource() == gv.getChaine())
                {
                        try
                        {
                            gv.getGraphe().setDist(Integer.parseInt(gv.getDist().getText()));
                            gv.getGraphe().createChaine(gv.getGraphe().getDist(),gv.getGraphe().getArcinit());
                        }
                        catch(NumberFormatException ec)
                        {
                            JLabel affichage = new JLabel("<html><p>Impossible de créer le graphe.<br>La distance indiquée est incorrecte.<br><br><span color=red>Attention : La distance par défaut des arcs doit<br>correspondre un nombre entier.</span></p></html>");
                            JOptionPane.showMessageDialog(null,affichage,"Erreur : " + ec + ":Valeur_de_champs_erroné",0);
                        }
                }
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
		} catch(NumberFormatException | NullPointerException e) {
		}
		return val;
	}
}