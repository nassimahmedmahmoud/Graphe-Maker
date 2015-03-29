package controller;

import dialogue.*;
import graphe.*;
import java.awt.event.*;


public class ListenerActionGraphe implements ActionListener {

	private GrapheView gv;

	public ListenerActionGraphe(GrapheView gv){
		this.gv=gv;
	}

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