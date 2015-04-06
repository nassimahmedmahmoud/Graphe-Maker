package dialogue;

import graphe.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.JPanel;
import javax.swing.Timer;

public class JPanelDijkstra extends JPanel{

	private static final long serialVersionUID = 1L;

	private static final int ANIM_DELAY = 0; // 50ms == 20fps
	private static final float ARC_DASH_LENGTH = 9;
	private static final float DASH_OFFSET_SUM = 0.5f;

	private GrapheView gv;
	private float dashOffset = 0;
	private Timer animTimer;

	public JPanelDijkstra(LayoutManager layout,GrapheView gv)
	{
		super(layout);
		this.gv = gv;
		this.animTimer = new Timer(ANIM_DELAY, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JPanelDijkstra.this.repaint();
			}
		});
		this.animTimer.setRepeats(true);
		this.animationStart();
	}

	public void animationStart()
	{
		this.animTimer.start();
	}

	public void animationStop()
	{
		this.animTimer.stop();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gv.getDijkstra().algorithmDijkstra();
		for(Arc a : gv.getGraphe().getArcs()){
			this.drawLines(g,a,Color.BLACK, false);
		}
		if(gv.getDijkstra().getArrivee()!=null){
			ArrayList<Arc> tab = gv.getDijkstra().distanceSourceArc();
			for(Arc arc : tab)
				this.drawLines(g,arc,Color.CYAN, true);
		}
		for(Sommet s : gv.getGraphe().getSommets()){
			drawNode(s,g,gv.getGraphe().getTailleSommet(),Color.WHITE);
		}
		if(gv.getDijkstra().getSource()!=null)
			drawNode(gv.getDijkstra().getSource(), g,gv.getGraphe().getTailleSommet(), Color.CYAN);
		if(gv.getDijkstra().getArrivee()!=null)
			drawNode(gv.getDijkstra().getArrivee(), g,gv.getGraphe().getTailleSommet(), Color.RED);
		dashOffset += DASH_OFFSET_SUM;
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

	public void drawLines(Graphics g, Arc a,Color color, boolean dashed)
	{
		Graphics gctx = dashed ? (Graphics2D)g.create() : g;

		gctx.setColor(color);
		int x1=a.getOrigine().getPosX();
		int x2=a.getArrivee().getPosX();
		int y1=a.getOrigine().getPosY();
		int y2=a.getArrivee().getPosY();
		int ctrlx=a.getCentre_posX();
		int ctrly=a.getCentre_posY();
		Path2D p = new Path2D.Double();
		if (dashed) {
			Stroke s = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{ARC_DASH_LENGTH}, dashOffset);
			((Graphics2D)gctx).setStroke(s);
		}
		if(gv.getGraphe().isType()){
			drawArrow(g,x1,y1,x2,y2);
			if(a.boucleMemeSommet()){
				//cc.setCurve(x1, y1, 0,0,0,0, x2, y2);
				p.moveTo(x1, y1);
				p.curveTo(x1, y1,x1/2, y2/2, x2, y2);
				((Graphics2D) gctx).draw(p);
			}
		}
		gctx.drawLine(x1, y1, x2, y2);

		if (dashed) {
			gctx.dispose(); // on tej' la copy du contexte
			gctx = g;
		}

		g.drawString(a.getNom(),ctrlx,ctrly);
		g.setColor(Color.BLACK);
	}  
	void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
		Graphics2D g = (Graphics2D) g1.create();
		double dx = x2 - x1, dy = y2 - y1;
		double angle = Math.atan2(dy, dx);
		int len = (int) Math.sqrt(dx*dx + dy*dy);
		AffineTransform at =affineTransforme(x1,y1,x2,y2);
		at.concatenate(AffineTransform.getRotateInstance(angle));
		g.transform(at);
		g.fillPolygon(new int[] {len/2, len/2-JPanelGraphe.ARR_SIZE, len/2-JPanelGraphe.ARR_SIZE, len/2},
				new int[] {0, -JPanelGraphe.ARR_SIZE, JPanelGraphe.ARR_SIZE, 0}, 4);
	}
	public AffineTransform affineTransforme(int x1, int y1, int x2, int y2){
		AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
		return at;
	}
}