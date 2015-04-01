package controller;

import javax.swing.*;
import javax.swing.event.*;
import graphe.*;
import dialogue.*;

public class ListenerChangeGraphe implements ChangeListener {

	private GrapheView gv;

	public ListenerChangeGraphe(GrapheView gv) {
		this.gv = gv;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_GENERAL){
			gv.getJl().setText(gv.getGraphe().toString(Graphe.MATRIX));
                        //gv.getJl().setText(gv.getGraphe().stringMatrice());
			gv.getJl().repaint();
			gv.getJlca().setText(gv.getGraphe().toString(Graphe.CONNEXE_ARBRE));
			//gv.getJlca().setText(gv.getGraphe().connexeArbre());
                        gv.getJlca().repaint();
			gv.getInfo().setText(gv.getGraphe().toString(Graphe.GENERAL));
			gv.getInfo().repaint();
		}
                
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_BRELAZ){
			gv.getJlColors().setText("Le nombre de couleurs est majorÃ© par : "+gv.getGraphe().chromatique());
			gv.getJlColors().repaint();
		}
                
		if(gv.getOnglets().getSelectedIndex()==GrapheView.ONGLET_DIJKSTRA){
			gv.setDijkstra(new Dijkstra(gv.getGraphe()));
			gv.getDijkstra().setGraphe(gv.getGraphe());
			gv.getDijkstra().algorithmDijkstra();
			gv.getJld().setText(gv.getDijkstra().toString());
			gv.getJld().repaint();
		}
	}

}
