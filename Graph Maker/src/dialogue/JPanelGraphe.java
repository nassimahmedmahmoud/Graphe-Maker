package dialogue;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import graphe.*;

public class JPanelGraphe extends JPanel{
	
	private Graphe graphe;
	private Arc arc;
	public final int ARR_SIZE=6;
	
	public JPanelGraphe(LayoutManager layout,Graphe graphe){
		super(layout);
		this.graphe=graphe;
	}
        
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!this.graphe.isType()){
			for(Arc a : graphe.getArcs())
				g.drawLine(a.getOrigine().getPosX(),a.getOrigine().getPosY(),a.getArrivee().getPosX(),a.getArrivee().getPosY()); // A MODIFIER (BEZIER)
		}	
		else{
			for(Arc a : graphe.getArcs())
				this.drawArrow(g, a.getOrigine().getPosX(), a.getOrigine().getPosY(), a.getArrivee().getPosX(), a.getArrivee().getPosY()); // A MODIFIER (BEZIER)
		}
		for( Sommet s : graphe.getSommets())
			this.drawNode(s,g,50,Color.WHITE);

	}
	
	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len =  (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(0, 0, len/2, 0);
        //AFFICHER NOM DE L'ARC
        g.fillPolygon(new int[] {len/2, len/2-ARR_SIZE, len/2-ARR_SIZE, len/2},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        g.drawLine(0, 0, len,0);
    }
	
	void drawNode(Sommet s,Graphics g,int diametre,Color color){
	    g.setColor(color);
	    g.fillOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre, diametre);
	    g.setColor(Color.BLACK);
	    g.drawOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre+1, diametre+1);
	    g.drawString(s.getNom()+"",s.getPosX(), s.getPosY());
	}
}
