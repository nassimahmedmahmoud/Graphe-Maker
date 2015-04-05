package controller;

import graphe.*;
import java.awt.event.*;
import javax.swing.*;
import dialogue.*;

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
                JPanelAide aide = new JPanelAide(0,new Aide());
                JOptionPane.showMessageDialog(null,aide,"Aide",1);
            }
            
            if(e.getSource()==this.gv.getMenusave()){
                String val = (String) JOptionPane.showInputDialog(null,
                        "Entrer le nom de votre fichier :", "Sommet", JOptionPane.QUESTION_MESSAGE, null, null, "");
                if (val != null)
                	this.gv.getGraphe().save(val,this.gv.getGraphe());
            }
            this.gv.getJpg().repaint();
	}

	
	
}
