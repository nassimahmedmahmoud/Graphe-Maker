package controller;

import java.awt.event.*;

import dialogue.GrapheView;

public class ListenerActionGraphe implements ActionListener {
	
	private GrapheView gv;
	
	public ListenerActionGraphe(GrapheView gv){
		this.gv=gv;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(this.gv.getBarcarrete()==e.getSource())
			this.gv.getGraphe().switchTypeOfGraphe();
		this.gv.getJpg().repaint();
	}
}
