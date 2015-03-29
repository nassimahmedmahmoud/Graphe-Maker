package controller;

import javax.swing.*;
import javax.swing.event.*;

import dialogue.*;

public class ListenerChangeGraphe implements ChangeListener {

	private GrapheView gv;
	
	public ListenerChangeGraphe(GrapheView gv) {
		this.gv = gv;
	}



	public void stateChanged(ChangeEvent e) {
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_GENERAL){
			gv.getJl().setText(gv.getGraphe().stringMatrice());
			gv.getJl().repaint();
			gv.getJlca().setText(gv.getGraphe().connexeArbre());
			gv.getJlca().repaint();
		}
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_BRELAZ){
			gv.getJlColors().setText("Le nombre de couleurs est majoré par : "+gv.getGraphe().chromatique());
			gv.getJlColors().repaint();
		}
		
	}

}
