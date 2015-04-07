package controller;

import dialogue.*;
import java.awt.event.*;
import javax.swing.*;

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
			
			try{
				this.gv.setGraphe(this.gv.getGraphe().read(dialogue.getSelectedFile()));
			}catch(Exception exc){}
			
			this.gv.grapheMetrique();
			if(this.gv.getGraphe().isType()){
				this.gv.getOnglets().setEnabledAt(2, false);
				this.gv.getBarcarrete().setIcon(new ImageIcon("img/nonorienter.png"));
			}
			else{
				this.gv.getOnglets().setEnabledAt(2, true);
				this.gv.getBarcarrete().setIcon(new ImageIcon("img/orienter.png"));
			}
		}

                if(e.getSource() == gv.getMenugraphe())
                {
                        this.gv.getGraphe().getSommets().clear();
			this.gv.getGraphe().getArcs().clear();
			this.gv.getGraphe().getArcinit().clear();
			this.gv.getDijkstra().setArrivee(null);
			this.gv.getDijkstra().setSource(null);
                }
                
		if(e.getSource() == gv.getApropos())
		{
			Aide a = new Aide();
			a.aPropos();
			JLabel affichage = new JLabel("<html><body><p>Auteur : " + a.getAuteur() +
					"<br>Editeur : " + a.getEditeur() + "<br><br>" +
					a.getText() +
					"</p></body></html>");
			JOptionPane.showMessageDialog(null,affichage,"A propos de nous",1);
		}

		if(e.getSource() == gv.getAssistance())
		{
			gv.setTexte(new JLabel());
			this.gv.setPanassist(new JPanel());
			JPanel affichage = gv.assistance(Aide.SOMMAIRE);
			JOptionPane.showMessageDialog(null,affichage,"Aide",1);
                        //gv.getA().setText("");
		}

		if(e.getSource()==this.gv.getMenusave()){
			String val = (String) JOptionPane.showInputDialog(null,
					"Entrer le nom de votre fichier :", "Sauvegarde", JOptionPane.QUESTION_MESSAGE, null, null, "");
			if (val != null)
				this.gv.getGraphe().save(val,this.gv.getGraphe());
		}
		this.gv.getJpg().repaint();
	}
}