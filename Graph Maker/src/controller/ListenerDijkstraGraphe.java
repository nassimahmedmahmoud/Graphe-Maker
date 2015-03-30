package controller;
import dialogue.*;
import graphe.*;

import java.awt.event.*;


public class ListenerDijkstraGraphe  implements MouseListener  {

	private Dijkstra d;
    private GrapheView gv;
    private Sommet sCourant;
	private Sommet arrivee;
    
	
	public ListenerDijkstraGraphe(GrapheView gv,Dijkstra d) {
		super();
		this.d = d;
		this.gv = gv;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(sCourant==null){
			sCourant = this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e);
			d.setSource(sCourant);
		}
		else{
			d.setArrivee(this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e));
			arrivee = d.getArrivee();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	public Sommet getArrivee() {
		return arrivee;
	}

	public void setArrivee(Sommet arrivee) {
		this.arrivee = arrivee;
	}
}
