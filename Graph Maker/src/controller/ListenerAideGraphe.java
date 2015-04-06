package controller;

import dialogue.*;
import java.awt.event.*;

public class ListenerAideGraphe implements ActionListener
{
    private GrapheView gv;
    
    public ListenerAideGraphe(GrapheView gv)
    {
        super();
        this.gv = gv;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
       if(e.getSource() == gv.getPrec())
       {
           gv.getA().setCurentpage(gv.getA().getCurentpage()-1);
           gv.getTexte().setText("<html><body><p>" + gv.getA().assistance(gv.getA().getCurentpage())
   				+ "</p></body></html>");
              
           System.out.println(gv.getA().getCurentpage());
       }
       
       if(e.getSource() == gv.getSuiv())
       {
           gv.getA().setCurentpage(gv.getA().getCurentpage()+1);
           gv.getTexte().setText("<html><body><p>" + gv.getA().assistance(gv.getA().getCurentpage())
   				+ "</p></body></html>");
       }
       gv.getTexte().repaint();
       gv.getPanassist().repaint();
    }
    
}
