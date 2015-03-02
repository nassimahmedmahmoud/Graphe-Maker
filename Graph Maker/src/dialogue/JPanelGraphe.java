package dialogue;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import graphe.*;

public class JPanelGraphe extends JPanel{
	
	private Graphe graphe;
	private Arc arc;
	public final int ARR_SIZE=4;
	
	public JPanelGraphe(LayoutManager layout,Graphe graphe){
		super(layout);
		this.graphe=graphe;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int height = 50, width = 50;
		
		for( Sommet s : graphe.getSommets())
		{
		    g.setColor(Color.WHITE);
		    g.fillOval(s.getPosX()-height/2, s.getPosY()-width/2,width, height);
		    g.setColor(Color.BLACK);
		    g.drawOval(s.getPosX()-height/2, s.getPosY()-width/2,width+1, height+1);
		    g.drawString(s.getNom()+"",s.getPosX()-height/12, s.getPosY()-width/12);
		}
		for(Arc a : graphe.getArcs()){
			if(this.graphe.isType())
				this.drawArrow(g, a.getOrigine().getPosX(), a.getOrigine().getPosY(), a.getArrivee().getPosX(), a.getArrivee().getPosY());
			else{
				g.drawLine(a.getOrigine().getPosX(),a.getOrigine().getPosY(),a.getArrivee().getPosX(),a.getArrivee().getPosY());
			}
		}
	}	
	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();
        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len =  (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
}
