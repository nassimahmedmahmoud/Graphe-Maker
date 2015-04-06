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
           gv.setPanassist(gv.assistance(gv.getA().getCurentpage()));
           
           System.out.println("précédent");
       }
       
       if(e.getSource() == gv.getSuiv())
       {
           gv.getA().setCurentpage(gv.getA().getCurentpage()+1);
           gv.setPanassist(gv.assistance(gv.getA().getCurentpage()));
           
           System.out.println("suivant");
       }
       gv.getPanassist().repaint();
    }
    
}
