package dialogue;

import controller.*;
import java.awt.*;
import javax.swing.*;

public class JPanelAide extends JPanel
{
    private int currentpage;
    private JButton prec;
    private JButton suiv;
    private Aide a;
 
    public JPanelAide(/*LayoutManager layout,Graphe graphe, GrapheView gv*/ int i, Aide a)
    {
        /*super(layout);
        this.gv = gv;*/
        this.a = a;
        currentpage = i;
        init();
        //currentpage = i;
    }
    
    public void init()
    {
        //a = new Aide();
        //currentpage = Aide.SOMMAIRE;
        JLabel texte = new JLabel("<html><body><p>" + a.assistance(currentpage)
                + "</p></body></html>");
        prec = new JButton("préc.");
        suiv = new JButton("suiv.");
        
        if(currentpage == 0)
        {
            this.add(texte);
            this.add(suiv);
        }
        else if(currentpage < 6)
        {
            this.add(texte);
            this.add(prec);
            this.add(suiv);
        }
        else
        {
            this.add(texte);
            this.add(prec);
        }
        suiv.addActionListener(new ListenerAideGraphe(this));
        prec.addActionListener(new ListenerAideGraphe(this));
    }
    
    public Aide getAide()
    {
        return a;
    }
    
    public void setAide(Aide a)
    {
        this.a = a;
    }
    
    public int getCurrentPage()
    {
        return currentpage;
    }
    
    public void setCurrentPage(int current)
    {
        this.currentpage = current;
    }

    public JButton getPrec() {
        return prec;
    }

    public JButton getSuiv() {
        return suiv;
    }

    public void setPrec(JButton prec) {
        this.prec = prec;
    }

    public void setSuiv(JButton suiv) {
        this.suiv = suiv;
    }
}