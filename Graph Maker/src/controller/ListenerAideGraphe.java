package controller;

import dialogue.*;
import java.awt.event.*;

public class ListenerAideGraphe implements ActionListener
{
    private JPanelAide jpa;
    
    public ListenerAideGraphe(JPanelAide jp)
    {
        super();
        jpa = jp;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
       if(e.getSource() == jpa.getPrec())
       {
           jpa.setCurrentPage(jpa.getCurrentPage()-1);
       }
       
       if(e.getSource() == jpa.getSuiv())
       {
           jpa.setCurrentPage(jpa.getCurrentPage()+1);
       }
       jpa.repaint();
    }
    
}
