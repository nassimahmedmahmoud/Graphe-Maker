package controller;

import javax.swing.*;
import javax.swing.event.*;

import dialogue.*;

public class ListenerChangeGraphe implements ChangeListener {

	private GrapheView gv;

	public ListenerChangeGraphe(GrapheView gv) {
		this.gv = gv;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_GENERAL){
			gv.getJl().setText(gv.getGraphe().stringMatrice());
			gv.getJl().repaint();
			gv.getJlca().setText(gv.getGraphe().connexeArbre());
			gv.getJlca().repaint();
			gv.getInfo().setText(gv.getGraphe().toString(" "));
			gv.getInfo().repaint();

		}
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_BRELAZ){
			gv.getJlColors().setText("Le nombre de couleurs est majoré par : "+gv.getGraphe().chromatique());
			gv.getJlColors().repaint();
		}
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_DIJKSTRA){
			gv.getD().algorithmDijkstra();
			gv.getJlDijkstra().setText(gv.getD().toString());
			gv.getJlDijkstra().repaint();
		}
	}

}
