package controller;

import graphe.Sommet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.MouseInputListener;

import dialogue.*;
public class ListenerBoutonGraphe implements MouseListener{
	
	private GrapheView gv;
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
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {

	}
}
