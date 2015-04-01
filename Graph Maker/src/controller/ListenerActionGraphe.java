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
		if(this.gv.getBarcarrete()==e.getSource())
			this.gv.getGraphe().switchTypeOfGraphe();
		if(e.getActionCommand()=="reset"){
			this.gv.getGraphe().getSommets().clear();
			this.gv.getGraphe().getArcs().clear();
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
                
                if(e.getSource() == gv.getRename())
                {
                    String val = (String) JOptionPane.showInputDialog(null,
                    "Modifier le nom du graphe", "Nouveau nom", JOptionPane.QUESTION_MESSAGE, null, null, "");
                    String s = val.trim();
                    if (/*s != null) ||*/ s.length() > 0) 
                        gv.getGraphe().setNom(val);
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
                
		this.gv.getJpb().repaint();
		this.gv.getJpg().repaint();
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