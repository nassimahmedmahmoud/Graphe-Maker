package controller;

import graphe.*;

import java.awt.event.*;
import dialogue.*;

public class ListenerBoutonGraphe implements MouseListener{
	
	private GrapheView gv;
	private Sommet sCourant;
        private Arc arcCourant;
        
	public ListenerBoutonGraphe(GrapheView gv){
		this.gv=gv;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(this.gv.getBsommet().isSelected()){
			Sommet s= new Sommet();
			s.setPosX(e.getX());
			s.setPosY(e.getY());
			this.gv.getGraphe().getSommets().add(s);
		}
		this.gv.getJpg().repaint();
	}

        @Override
	public void mousePressed(MouseEvent e) {
		if(this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected())
			this.sCourant=this.gv.getGraphe().isSommet(50,e);
        else if(this.gv.getBarc().isSelected() || this.gv.getBarrete().isSelected())
        {
            sCourant = this.gv.getGraphe().isSommet(50, e);
            if(sCourant != null)
            {
                arcCourant = new Arc();
                arcCourant.setOrigine(sCourant);
            }
        }
	}

        @Override
	public void mouseReleased(MouseEvent e) {
            if(sCourant == null)
                arcCourant = null;
            
            if(this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected())
            {
		if(this.sCourant!=null)
                    this.sCourant=null;
            }
            
            if((this.gv.getBarc().isSelected() || this.gv.getBarrete().isSelected()) && arcCourant != null)
            {
                    
                sCourant = this.gv.getGraphe().isSommet(50, e);
                //System.out.println(sCourant);
                if(sCourant != null)
                {
                    arcCourant.setArrivee(sCourant);
                    this.gv.getGraphe().getArcs().add(arcCourant);
                    arcCourant.getArrivee().ajouterArc(arcCourant);
                    arcCourant.getOrigine().ajouterArc(arcCourant);
                }
                sCourant = null;
                arcCourant = null;
                this.gv.getJpg().repaint();
            }
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
        
        public Arc getArcCourant()
        {
            return arcCourant;
        }
        
        public void setArcCourant(Arc a)
        {
            arcCourant = a;
        }
	
}
