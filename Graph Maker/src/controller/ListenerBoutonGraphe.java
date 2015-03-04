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
 // FAIRE LA SUPPRESSION DE TOUT LES ARCS LOL
	public void mouseClicked(MouseEvent e) {
		if(this.gv.getBsommet().isSelected() && SwingUtilities.isLeftMouseButton(e))
		{
			Sommet s= new Sommet();
			s.setPosX(e.getX());
			s.setPosY(e.getY());
			this.gv.getGraphe().getSommets().add(s);
		}
		this.setsCourant(e);

		if(this.gv.getBgomme().isSelected() && SwingUtilities.isLeftMouseButton(e) && sCourant!=null)
		{
			for(int i=0;i<this.getsCourant().getArcs().size();i++)
				this.gv.getGraphe().getArcs().remove(this.getsCourant().getArc(i));
			this.gv.getGraphe().getSommets().remove(this.getsCourant());
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

		if(this.gv.getBarc().isSelected())
		{
			sCourant = this.gv.getGraphe().isSommet(50, e);
			if(sCourant!=null && arcCourant==null){
				arcCourant = new Arc();
				arcCourant.setOrigine(sCourant);

				//System.out.println("loul");
			}
			else if(sCourant!=null){
				//System.out.println("lol");
				arcCourant.setArrivee(sCourant);
				this.gv.getGraphe().getArcs().add(arcCourant);
				arcCourant = null;
				sCourant = null;
			}
		}
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
				arcCourant_2 = new Arc();
				arcCourant_2.setOrigine(sCourant);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		/* if(sCourant == null)
                arcCourant = null;
		 */
		if((this.gv.getBarc().isSelected() || this.gv.getBarrete().isSelected()) && arcCourant_2 != null)
		{
			sCourant = this.gv.getGraphe().isSommet(50, e);
			if(sCourant != null)
			{
				arcCourant_2.setArrivee(sCourant);

				if(!(gv.getGraphe().arcInGraphe(arcCourant_2)))
				{
					if((!(gv.getGraphe().isType()) && arcCourant_2.getArrivee() !=
							arcCourant_2.getOrigine()) || gv.getGraphe().isType())
					{
						arcCourant_2.getArrivee().ajouterArc(arcCourant_2);
						arcCourant_2.getOrigine().ajouterArc(arcCourant_2);
						this.gv.getGraphe().getArcs().add(arcCourant_2);
						sCourant = null;
						arcCourant_2 = null;
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
