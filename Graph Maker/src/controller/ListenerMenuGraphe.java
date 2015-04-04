package controller;

import graphe.Arc;
import graphe.Sommet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import dialogue.GrapheView;

public class ListenerMenuGraphe implements ActionListener {
    private GrapheView gv;

    public ListenerMenuGraphe(GrapheView gv) {
        this.gv = gv;
    }
    
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.gv.getMenuload()){
			JFileChooser dialogue = new JFileChooser();
			dialogue.showOpenDialog(null);
			System.out.println("Fichier choisi : " + dialogue.getSelectedFile());
			this.gv.setGraphe(this.gv.getGraphe().read(dialogue.getSelectedFile()));
			
		}
        this.gv.getJpg().repaint();
	}

	
	
}
