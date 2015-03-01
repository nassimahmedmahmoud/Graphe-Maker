package controller;

import graphe.Sommet;

import java.awt.*;

import dialogue.GrapheView;

public class ListenerMouseMotionGraphe implements MouseMotionListener  {
private GrapheView gv;
	
	public ListenerMouseMotionGraphe(GrapheView gv){
		this.gv=gv;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected()){
			for(Sommet s : this.gv.getGraphe().getSommets()){
				if(s.getPosX()-25<=e.getX() && s.getPosY()-25<=e.getY() && (s.getPosX()+25)>=e.getX() && (s.getPosY()+25)>=e.getY()){
					s.setPosX(e.getX());
					s.setPosY(e.getY());	
					this.gv.getJpg().repaint();
					break;
				}
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
