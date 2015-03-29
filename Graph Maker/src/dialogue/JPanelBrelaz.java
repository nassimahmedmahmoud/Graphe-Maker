package dialogue;
 
import graphe.*;
import java.awt.*;
import java.awt.geom.*; 
import javax.swing.*;
 
public class JPanelBrelaz extends JPanel
{
    public final int ARR_SIZE=6;
    public static final Color[] colors = 
    {
        Color.RED,Color.RED,Color.BLUE,Color.GREEN,Color.BLACK,
        Color.YELLOW,Color.GRAY,Color.PINK,Color.CYAN,Color.ORANGE,
        Color.DARK_GRAY,Color.LIGHT_GRAY,Color.MAGENTA,new Color(200,255,200),
        new Color(255,215,215), new Color(50,156,207), new Color(150,30,255),
        new Color(30,50,80),new Color(230, 230, 230), new Color(90,20,10),
        new Color(255,255,200), new Color(50,255,160), new Color(200,200,50),
        new Color(255,0,100), new Color(200,0,0), new Color(0,200,0)
    };
    
    private Arc arc;
    private GrapheView gv;
 
    public JPanelBrelaz(LayoutManager layout,Graphe graphe, GrapheView gv)
    {
        super(layout);
        this.gv = gv;
    }
 
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(Arc a : gv.getGraphe().getArcs())
            this.drawLines(g, a);
        
        int[]tabColoration = gv.getGraphe().coloration();
        System.out.print("color : ");
        for(int i=0;i<tabColoration.length;i++){
        	System.out.print(tabColoration[i]+"-");
        }
        System.out.println();
        Color c = new Color(0);
        //if(tabColoration != null)
        //{
            for(int i=0;i<gv.getGraphe().getSommets().size();i++)
            {
                    if(tabColoration[i] >= colors.length)
                        c=colors[colors.length-1];
                    else
                        c=colors[tabColoration[i]];
                this.drawNode(gv.getGraphe().getSommets().get(i),g,gv.getGraphe().getTailleSommet(),c);
            //}
                
        } 
    }
 
    void drawNode(Sommet s,Graphics g,int diametre,Color color)
    {
        g.setColor(color);
        g.fillOval(s.getPosX()-diametre/2, s.getPosY()-diametre/2,diametre, diametre);
        if(color == colors[4] || color == colors[10] || color == colors[17]
                || color == colors[19] || color == colors[6])
            g.setColor(Color.WHITE);
        else
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
        QuadCurve2D.Double cq = new QuadCurve2D.Double();
        cc.setCurve(x1, y1, ctrlx,(ctrly+y1)/2,(ctrlx+x2)/2,(ctrly+y2)/2, x2, y2);
        /*cq.setCurve(x1, y1, (ctrlx+x1)/2, (ctrly+y1)/2, ctrlx, ctrly);
        cq.setCurve(ctrlx, ctrly, (ctrlx+x2)/2, (ctrly+y2)/2, x2,y2);
        g2D1.setTransform(affineTransforme((ctrlx+x1)/2,(ctrly+y1)/2,(ctrlx+x2)/2,(ctrly+y2)/2));*/
        g.drawString(a.getNom(),ctrlx,ctrly);
        g2D.draw(cc);
    }  
}