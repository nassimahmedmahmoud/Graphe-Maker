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
			if(this.gv.getGraphe().isType())
				this.gv.getOnglets().setEnabledAt(2,true);
			else
				this.gv.getOnglets().setEnabledAt(2,false);
			this.gv.getGraphe().switchTypeOfGraphe();
		}
		if(e.getActionCommand()=="reset"){
			this.gv.getGraphe().getSommets().clear();
			this.gv.getGraphe().getArcs().clear();
            this.gv.getGraphe().getArcinit().clear();
			this.gv.getDijkstra().setArrivee(null);
			this.gv.getDijkstra().setSource(null);
		}
		if(e.getSource()==gv.getJtfNode()){
			this.gv.getGraphe().setTailleSommet(isInteger(gv.getJtfNode().getText()));
			this.gv.getJtfNode().setText("");
		}
                
                if(e.getSource() == gv.getDist())
                {
                    this.gv.getGraphe().setDist(isInteger(gv.getDist().getText()));
                    //this.gv.getDist().setText("");
                }
                
                if(e.getSource() == gv.getMargep())
                {
                    gv.getGraphe().setMargelinecolumn(isInteger(gv.getMargep().getText()));
                }
                
                if(e.getSource() == gv.getTaillec())
                {
                    gv.getGraphe().setLengthlinecolumn(isInteger(gv.getTaillec().getText()));
                }
                
                if(e.getSource() == gv.getGenerate())
                {
                    try{
                        gv.getAff().setText(gv.getGraphe().matrixLatexToString(gv.getGraphe()
                            .matrixLatex(gv.getGraphe().initTab(gv.getGraphe().getMargelinecolumn()), gv.getGraphe().getMargelinecolumn()
                                    , gv.getGraphe().getLengthlinecolumn()), gv.getGraphe().getMargelinecolumn() , gv.getGraphe().getLengthlinecolumn(),
                                    gv.getGraphe().initTab(gv.getGraphe().getMargelinecolumn())));
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
                    //gv.getLatex().add(gv.getAff());
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
                    System.out.println("C REPAIN"+ gv.getGraphe().getNom());
                    this.gv.getPangen().repaint();
                }
                
                if(e.getSource() == gv.getCreate())
                {
                    //System.out.println("lol");
                    System.out.println(gv.getGraphe().getTabCick());
                    if(gv.getGraphe().getTabCick().size() > 1)
                    {
                        System.out.println(gv.getGraphe().getTabCick());
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
		
                this.gv.getPangen().repaint();
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