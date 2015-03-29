package controller;

import dialogue.*;
import graphe.*;
import java.awt.event.*;
import java.util.ArrayList;


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
		}
		if(e.getSource()==gv.getJtfNode()){
			this.gv.getGraphe().setTailleSommet(isInteger(gv.getJtfNode().getText()));
			this.gv.getJtfNode().setText("");
		}
                if(e.getSource() == gv.getCreate())
                {
                    System.out.println("lol");
                    System.out.println(gv.getGraphe().getTabCick());
                    if(gv.getGraphe().getTabCick().size() > 1)
                    {
                        System.out.println(gv.getGraphe().getTabCick());
                        gv.getGraphe().createClique(gv.getGraphe().getTabCick());
                        gv.getGraphe().setTabCick(new ArrayList<Sommet>());
                    }
                }
                if(e.getSource() == gv.getClik())
                    this.gv.getGraphe().createClique();
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