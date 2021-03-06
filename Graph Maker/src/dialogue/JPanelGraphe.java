package dialogue;

import graphe.*;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;


public class JPanelGraphe extends JPanel{

	private static final long serialVersionUID = 1L;

	public static final int ARR_SIZE=6;

	private GrapheView gv;

	public JPanelGraphe(LayoutManager layout,GrapheView graphe){
		super(layout);
		this.gv=graphe;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(Arc a : gv.getGraphe().getArcs()){
			this.drawLines(g, a);
		}
		for( Sommet s : gv.getGraphe().getSommets())
			this.drawNode(s,g,gv.getGraphe().getTailleSommet(),new Color(240,240,240));	
	}

	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
		Graphics2D g = (Graphics2D) g1.create();
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx*dx + dy*dy);
		AffineTransform at =affineTransforme(x1,y1,x2,y2);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g.transform(at);
		g.fillPolygon(new int[] {len/2, len/2-ARR_SIZE, len/2-ARR_SIZE, len/2},
				new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
	}

	public AffineTransform affineTransforme(int x1, int y1, int x2, int y2){
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		return at;
	}

	void drawNode(Sommet s,Graphics g,int diametre,Color color){
		g.setColor(color);
		g.fillOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre, diametre);
		g.setColor(Color.BLACK);
		g.drawOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre, diametre);
		FontMetrics fm = g.getFontMetrics();
		double text = fm.getStringBounds(s.getNom(), g).getWidth();    
		g.drawString(s.getNom(),(int)(s.getPosX()-text/2),(int)(s.getPosY()));
		g.setColor(Color.black);
	}

	public void drawLines(Graphics g, Arc a)
	{
		int x1=a.getOrigine().getPosX();
		int x2=a.getArrivee().getPosX();
		int y1=a.getOrigine().getPosY();
		int y2=a.getArrivee().getPosY();
		int ctrlx=a.getCentre_posX();
		int ctrly=a.getCentre_posY();
		Graphics2D g2D =(Graphics2D)g.create();
		Path2D p = new Path2D.Double();
		CubicCurve2D.Double cc = new CubicCurve2D.Double();
		if(gv.getGraphe().isType()){
			drawArrow(g,x1,y1,x2,y2);
			g.drawLine(x1, y1, x2, y2);
			if(a.boucleMemeSommet()){
				p.moveTo(x1, y1);
				p.curveTo(x1, y1,x1/2, y2/2, x2, y2);
				g2D.draw(p);
			}
		}
		else
			cc.setCurve(x1, y1, ctrlx,(ctrly+y1)/2,(ctrlx+x2)/2,(ctrly+y2)/2, x2, y2);
		g.drawString(a.getNom(),ctrlx,ctrly);
		g2D.draw(cc);
	}
}