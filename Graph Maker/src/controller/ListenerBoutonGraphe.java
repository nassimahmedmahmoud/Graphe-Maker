package controller;

import graphe.*;
import dialogue.*;

import java.awt.event.*;
import javax.swing.*;

public class ListenerBoutonGraphe implements MouseListener {

    private GrapheView gv;
    private Sommet sCourant;
    private Arc arcCourant;

    public ListenerBoutonGraphe(GrapheView gv) {
        this.gv = gv;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.gv.getBsommet().isSelected() && SwingUtilities.isLeftMouseButton(e)) {
            Sommet s = new Sommet();
            s.setPosX(e.getX());
            s.setPosY(e.getY());
            this.gv.getGraphe().ajouterSommet(s);
         }
        this.setsCourant(e);

        if (this.gv.getBclic().isSelected() /*|| (this.gv.getBarc().isSelected()*/ && sCourant == null)
            arcCourant = this.gv.getGraphe().isArc(this.gv.getGraphe().getTailleSommet(), e);

        if (this.gv.getBgomme().isSelected() && SwingUtilities.isLeftMouseButton(e) && sCourant != null) {
            for (int i = 0; i < this.getsCourant().getArcs().size(); i++)
                this.gv.getGraphe().getArcs().remove(this.getsCourant().getArc(i));

            this.gv.getGraphe().getArcinit().remove(this.getsCourant());
            this.gv.getGraphe().getSommets().remove(this.getsCourant());
        }
        
        if (this.gv.getBgomme().isSelected() && SwingUtilities.isLeftMouseButton(e) && this.gv.getGraphe().isArc(this.gv.getGraphe().getTailleSommet(), e)!=null)
        {
            Arc a = this.gv.getGraphe().isArc(this.gv.getGraphe().getTailleSommet(), e);
            a.getOrigine().getArcs().remove(a);
            a.getArrivee().getArcs().remove(a);
            this.gv.getGraphe().getArcs().remove(this.gv.getGraphe().isArc(this.gv.getGraphe().getTailleSommet(), e));
        }
        
        if (SwingUtilities.isRightMouseButton(e) && arcCourant != null && (this.gv.getBarc().isSelected() || this.gv.getBclic().isSelected()))
        {
            String val = (String) JOptionPane.showInputDialog(null,
                    "Modifier la valeur de l'arc", "Arc",
                    JOptionPane.QUESTION_MESSAGE, null, null, "");
            if (val != null)
                arcCourant.setNom(val);
            else
                arcCourant = null;
        }

        if (SwingUtilities.isRightMouseButton(e) && sCourant != null && this.gv.getBsommet().isSelected()) {
            String val = (String) JOptionPane.showInputDialog(null,
                    "Modifier la valeur du sommet", "Sommet", JOptionPane.QUESTION_MESSAGE, null, null, "");
            if (val != null)
                sCourant.setNom(val);
            else
                sCourant = null;
        }

        if (this.gv.getBarc().isSelected())
        {
            sCourant = this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e);
            if (sCourant != null && arcCourant == null)
            {
                arcCourant = new Arc();
                arcCourant.setOrigine(sCourant);
            }
            else if(sCourant == null && arcCourant != null)
            {
                arcCourant = null;
                sCourant = null;
            }
            else if (sCourant != null)
            {
                arcCourant.setArrivee(sCourant);
                if (!(gv.getGraphe().arcInGraphe(arcCourant)))
                {
                    if ((!(gv.getGraphe().isType()) && arcCourant.getArrivee()
                            != arcCourant.getOrigine()) || gv.getGraphe().isType())
                    {
                        arcCourant.milieu();
                        if(!(this.gv.getGraphe().arcInGraphe(arcCourant)))
                        {
                            arcCourant.getArrivee().ajouterArc(arcCourant);
                            arcCourant.getOrigine().ajouterArc(arcCourant);
                            this.gv.getGraphe().getArcs().add(arcCourant);
                        }
                        sCourant = null;
                        arcCourant = null;
                    }
                }
            }
        }
        
        if(gv.getClikc().isSelected() || gv.getCyclc().isSelected() || gv.getChainec().isSelected())
        {
            sCourant = this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e);
            if(sCourant != null && (!(gv.getGraphe().sommetInGraphe(gv.getGraphe().getTabCick(),sCourant))))
                gv.getGraphe().getTabCick().add(sCourant);
        }
        this.gv.getJpg().repaint();
		this.gv.grapheMetrique();
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        this.setsCourant(e);
        if(this.gv.getBclic().isSelected())
        	arcCourant = this.gv.getGraphe().isArc(this.gv.getGraphe().getTailleSommet(), e);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (sCourant == null) {
            arcCourant = null;
        }
        this.gv.getJpg().repaint();
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

    public Arc getArcCourant() {
        return arcCourant;
    }

    public void setArcCourant(Arc a) {
        arcCourant = a;
    }
   
    public void setsCourant(MouseEvent e) {
        if (this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected() || this.gv.getBgomme().isSelected()) {
            this.sCourant = this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e);
        }
    }
}