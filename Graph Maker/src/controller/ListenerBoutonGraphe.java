package controller;

import graphe.Sommet;

import java.awt.event.*;
import dialogue.*;
public class ListenerBoutonGraphe implements MouseListener{
	
	private GrapheView gv;
	private Sommet sCourant;
	public ListenerBoutonGraphe(GrapheView gv){
		this.gv=gv;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(this.gv.getBsommet().isSelected()){
			Sommet s= new Sommet();
			s.setPosX(e.getX());
			s.setPosY(e.getY());
			this.gv.getGraphe().getSommets().add(s);
			this.gv.getJpg().repaint();
		}
		if(this.gv.getBarcarrete()==e.getSource()){
			this.gv.getGraphe().switchTypeOfGraphe();
		}
	}

	public void mousePressed(MouseEvent e) {
		if(this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected())
			this.sCourant=this.gv.getGraphe().isSommet(50,e);
	}

	public void mouseReleased(MouseEvent e) {
		if(this.sCourant!=null)
			this.sCourant=null;
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {

	}

	public Sommet getsCourant() {
		return sCourant;
	}

	public void setsCourant(Sommet sCourant) {
		this.sCourant = sCourant;
	}
	
	
}
