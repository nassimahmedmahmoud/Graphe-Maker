package controller;

import java.awt.event.*;
import dialogue.*;

public class ListenerActionGraphe implements ActionListener {
	
	private GrapheView gv;
	
	public ListenerActionGraphe(GrapheView gv){
		this.gv=gv;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(this.gv.getBarcarrete()==e.getSource())
			this.gv.getGraphe().switchTypeOfGraphe();
                
                if(this.gv.getBrelaz() == e.getSource())
                    this.gv.setColors(this.gv.getGraphe().coloration());
                
                this.gv.getJpb().repaint();
		this.gv.getJpg().repaint();
	}
}
