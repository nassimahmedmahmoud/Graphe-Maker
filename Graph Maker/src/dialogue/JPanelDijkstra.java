package dialogue;

import graphe.*;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class JPanelDijkstra extends JPanel{

	private static final long serialVersionUID = 1L;
	private GrapheView gv;

	public JPanelDijkstra(LayoutManager layout,GrapheView gv)
	{
		super(layout);
		this.gv = gv;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gv.getD().algorithmDijkstra();
		for(Arc a : gv.getGraphe().getArcs()){
			this.drawLines(g,a,Color.BLACK);
		}
		
		for(Sommet s : gv.getGraphe().getSommets()){
			drawNode(s,g,gv.getGraphe().getTailleSommet(),Color.WHITE);
		}
		if(gv.getD().getSource()!=null)
			this.drawNode(gv.getD().getSource(), g,gv.getGraphe().getTailleSommet(), Color.CYAN);
		if(gv.getD().getArrivee()!=null){
			ArrayList<Arc> tab = gv.getD().distanceSourceArc();
			for(Arc arc : tab)
				this.drawLines(g,arc,Color.CYAN);
			this.drawNode(gv.getD().getArrivee(), g,gv.getGraphe().getTailleSommet(), Color.CYAN);
		}
	} 

	void drawNode(Sommet s,Graphics g,int diametre,Color color)
	{
		g.setColor(color);
		g.fillOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre, diametre);
		g.setColor(Color.BLACK);
		g.drawOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre, diametre);
		FontMetrics fm = g.getFontMetrics();
		double text = fm.getStringBounds(s.getNom(), g).getWidth();   
		g.drawString(s.getNom(),(int)(s.getPosX()-text/2),(int)(s.getPosY()));
		g.setColor(Color.black);
	}

	public void drawLines(Graphics g, Arc a,Color color)
	{
		g.setColor(color);
		int x1=a.getOrigine().getPosX();
		int x2=a.getArrivee().getPosX();
		int y1=a.getOrigine().getPosY();
		int y2=a.getArrivee().getPosY();
		int ctrlx=a.getCentre_posX();
		int ctrly=a.getCentre_posY();
		g.drawLine(x1, y1, x2, y2);
		g.drawString(a.getNom(),ctrlx,ctrly);
		g.setColor(Color.BLACK);
	}  
}