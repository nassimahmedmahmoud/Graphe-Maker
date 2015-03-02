package controller;

import java.awt.event.*;
import dialogue.*;

public class ListenerMouseMotionGraphe implements MouseMotionListener  {

	private GrapheView gv;
	private ListenerBoutonGraphe lbg;
	
	public ListenerMouseMotionGraphe(GrapheView gv,ListenerBoutonGraphe lbg){
		this.gv=gv;
		this.lbg=lbg;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.gv.getBclic().isSelected() || this.gv.getBsommet().isSelected()){
			if(lbg.getsCourant()!=null){
				lbg.getsCourant().setPosX(e.getX());
				lbg.getsCourant().setPosY(e.getY());
				this.gv.getJpg().repaint();
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
