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
           gv.getTexte().setText("<html><body><p>" + gv.getA().assistance(gv.getA().getCurentpage())
   				+ "</p></body></html>");
           if(gv.getA().getCurentpage() == 6)
           {
               gv.getA().setCurentpage(gv.getA().getCurentpage()-1); 
               gv.getPanbtn().remove(gv.getPrec());
               gv.getPanbtn().add(gv.getPrec());
               gv.getPanbtn().add(gv.getSuiv());
           }
           else if(gv.getA().getCurentpage() != Aide.SOMMAIRE+1){
               gv.getA().setCurentpage(gv.getA().getCurentpage()-1);
               gv.getPanbtn().remove(gv.getPrec());
               gv.getPanbtn().remove(gv.getSuiv());
               gv.getPanbtn().add(gv.getPrec());
               gv.getPanbtn().add(gv.getSuiv());
           }
           else
           {
               gv.getA().setCurentpage(gv.getA().getCurentpage()-1);
               gv.getPanbtn().remove(gv.getPrec());
           }
       }
       
       if(e.getSource() == gv.getSuiv())
       {
           gv.getA().setCurentpage(gv.getA().getCurentpage()+1);
           gv.getTexte().setText("<html><body><p>" + gv.getA().assistance(gv.getA().getCurentpage())
   				+ "</p></body></html>");
           if(gv.getA().getCurentpage() == 6){
               gv.getPanbtn().remove(gv.getSuiv());
           }
           else if(gv.getA().getCurentpage() != Aide.SOMMAIRE)
           {
               gv.getPanbtn().remove(gv.getSuiv());
               gv.getPanbtn().add(gv.getPrec());
               gv.getPanbtn().add(gv.getSuiv());
           }
       }
       gv.getPanbtn().repaint();
    }
}