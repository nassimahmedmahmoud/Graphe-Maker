package controller;

import graphe.*;

import java.awt.event.*;

import javax.swing.*;

import dialogue.*;

public class ListenerBoutonGraphe implements MouseListener{
	
	private GrapheView gv;
	private Sommet sCourant;
        private Sommet sCourant_2;
        private Arc arcCourant;
        private Arc arcCourant_2;
        
	public ListenerBoutonGraphe(GrapheView gv){
		this.gv=gv;
	}
	
	public void mouseClicked(MouseEvent e) {
		if(this.gv.getBsommet().isSelected() && SwingUtilities.isLeftMouseButton(e))
                {
			Sommet s= new Sommet();
			s.setPosX(e.getX());
			s.setPosY(e.getY());
			this.gv.getGraphe().getSommets().add(s);
		}
		//this.setsCourant(e);
                
		if(this.gv.getBgomme().isSelected() && SwingUtilities.isLeftMouseButton(e))
                {
			this.setsCourant(e);
			this.gv.getGraphe().getSommets().remove(this.getsCourant());
			for(int i=0;i<this.getsCourant().getArcs().size();i++)
			this.gv.getGraphe().getArcs().remove(this.getsCourant().getArc(i));
		}
                
		if(SwingUtilities.isRightMouseButton(e) && sCourant!=null)
                {
			String val =  (String)JOptionPane.showInputDialog(null, 
					"Modifier la valeur du sommet","Sommet",JOptionPane.QUESTION_MESSAGE, null, null, "");
			if(val!=null){
				sCourant.setNom(val);
			}
			else
				sCourant=null;
		}
                /*
                if(this.gv.getBarc().isSelected() || this.gv.getBarrete().isSelected())
                {
                    sCourant_2 = this.gv.getGraphe().isSommet(50, e);
                    System.out.println("gfffdg : " + arcCourant_2);
                    
                    if(sCourant_2 != null && arcCourant_2 == null)
                    {
                        arcCourant_2 = new Arc();
                        arcCourant_2.setOrigine(sCourant_2);
                        System.out.println(arcCourant_2);
                    }
                    
                    else if(sCourant_2 != null && arcCourant_2 != null && arcCourant_2.getOrigine() != null)
                    {
                        System.out.println("lol");
                        arcCourant_2.setArrivee(sCourant_2);
                        if(((!(gv.getGraphe().isType()) && arcCourant_2.getArrivee() !=
                                arcCourant_2.getOrigine()) || gv.getGraphe().isType()))
                        {
                            this.gv.getGraphe().getArcs().add(arcCourant_2);
                            arcCourant_2.getArrivee().ajouterArc(arcCourant_2);
                            arcCourant_2.getOrigine().ajouterArc(arcCourant_2);
                            sCourant_2 = null;
                            arcCourant_2 = null;
                        }
                    }
                }*/
                
                this.gv.getJpg().repaint();
	}

        @Override
	public void mousePressed(MouseEvent e)
        {
            this.setsCourant(e);
            if(this.gv.getBarc().isSelected() || this.gv.getBarrete().isSelected())
            {
                sCourant = this.gv.getGraphe().isSommet(50, e);
                if(sCourant != null)
                {
                    //System.out.println("loul");
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
                    
                    if(!(gv.getGraphe().arcInGraphe(arcCourant)))
                    {
                        if((!(gv.getGraphe().isType()) && arcCourant.getArrivee() !=
                                arcCourant.getOrigine()) || gv.getGraphe().isType())
                        {
                            this.gv.getGraphe().getArcs().add(arcCourant);
                            arcCourant.getArrivee().ajouterArc(arcCourant);
                            arcCourant.getOrigine().ajouterArc(arcCourant);
                            sCourant = null;
                            arcCourant = null;
                        }
                    }
                }
            }
            
            this.gv.getJpg().repaint();
	}

    public void setsCourant(MouseEvent e){
    	if(this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected() || this.gv.getBgomme().isSelected())
    		this.sCourant=this.gv.getGraphe().isSommet(50,e);
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
