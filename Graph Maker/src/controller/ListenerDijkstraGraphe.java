package controller;

import dialogue.*;
import graphe.*;
import java.awt.event.*;

public class ListenerDijkstraGraphe  implements MouseListener  {

    private GrapheView gv;
    
	public ListenerDijkstraGraphe(GrapheView gv) { this.gv = gv; }

	@Override
	public void mouseClicked(MouseEvent e) {
		gv.getDijkstra().setGraphe(gv.getGraphe());
		if(gv.getBsource().isSelected()){
			gv.setDijkstra(new Dijkstra(this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e),this.gv.getGraphe()));
		}
		else if(gv.getBarrivee().isSelected() && gv.getDijkstra().getSource()!=null)
			gv.getDijkstra().setArrivee(this.gv.getGraphe().isSommet(this.gv.getGraphe().getTailleSommet(), e));
		
		if(gv.getBsource().isSelected() || gv.getBarrivee().isSelected()){
			gv.getDijkstra().algorithmDijkstra();
			gv.getJld().setText(""+gv.getDijkstra().toString());
		}
		gv.getJld().repaint();
		gv.getJpd().repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
