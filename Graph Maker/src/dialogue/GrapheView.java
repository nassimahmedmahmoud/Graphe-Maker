package dialogue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import controller.*;
import graphe.*;



public class GrapheView extends JFrame{
   private JTabbedPane onglets;
   private Graphe graphe;
   private JToggleButton bclic;
   private JPanelGraphe jpg;
   private JToggleButton bsommet;
   private JButton barcarrete;
   public GrapheView(String titre,int w,int h){
	  super(titre);
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.initMenu();
	  this.initialise();
	  this.setSize(w,h);
      this.setVisible(true);
   	}
 
   public void initialise(){
	   Container c = this.getContentPane();
	   JPanel jp1 = new JPanel();
	   JPanel jp2 = new JPanel();
	   JPanel jp3 = new JPanel();
	   JLabel jl = new JLabel("Saluuuuut");
	   onglets = new JTabbedPane();
	   this.graphe=new Graphe();
	   jp1.add(jl);
	   
	   onglets.addTab("Graphe", null,this.panel1());
	   onglets.addTab("Djikstra", null, jp1);
	   onglets.addTab("Coloration", null, jp2);
	   onglets.addTab("Brelaz", null, jp3);
	   c.add(onglets);
   }
   
	public void initMenu(){
		JMenuBar jmb=new JMenuBar();
		this.setJMenuBar(jmb);
		jmb.add(menuFile());
		jmb.add(menuEdition());
		jmb.add(menuAide());
	}
	public JMenu menuFile(){
		JMenu menufichier=new JMenu("Fichier");
		JMenuItem menugraphe=new JMenuItem("Créer un graphe");
		JMenuItem menusave = new JMenuItem("sauvegarder graphe");
		JMenuItem menuload = new JMenuItem("charger graphe");
		menufichier.add(menugraphe);
		menufichier.add(menusave);
		menufichier.add(menuload);
		return menufichier;
	}
	
	public JMenu menuEdition(){
		JMenu menuEdition = new JMenu("Editer");
		JMenuItem menuundo = new JMenuItem("Undo");
		JMenuItem menuredo = new JMenuItem("Redo");
		menuEdition.add(menuundo);
		menuEdition.add(menuredo);
		return menuEdition;
	}
	
	public JMenu menuAide(){
		JMenu menuAide = new JMenu("Aide");
		JMenuItem menuhelp = new JMenuItem("Assistance");
		JMenuItem menupropos = new JMenuItem("A propose de nous");
		menuAide.add(menuhelp);
		menuAide.add(menupropos);
		return menuAide;
	}
  public JPanel panelGraphe(){
	  JPanel pgraphe = new JPanel();
	  ButtonGroup btngrp = new ButtonGroup();
	  bclic = new JToggleButton("clic");
	  bsommet = new JToggleButton("sommet");
	  JToggleButton barc = new JToggleButton("arc");
	  JToggleButton barrete = new JToggleButton("arrete");
	  barcarrete = new JButton("arc <-> arrete");
	  JToggleButton bgomme= new JToggleButton("gomme");
	  pgraphe.add(this.panelListenerTest(),"Center");
	  btngrp.add(bclic);
	  btngrp.add(bsommet);
	  btngrp.add(barc);
	  btngrp.add(barrete);
	  btngrp.add(bgomme);
	  pgraphe.add(bclic);
	  pgraphe.add(bsommet);
	  pgraphe.add(barc);
	  pgraphe.add(barcarrete);
	  pgraphe.add(bgomme);
	  pgraphe.add(jpg);
	  return pgraphe;
  }
  public JPanel panel1(){
	  JPanel jp = new JPanel(new BorderLayout());
	  jp.add(this.panelGraphe(),"North");
	  jp.add(this.panelListenerTest(),"Center");
	  return jp;
  }
  
  public JPanelGraphe panelListenerTest(){
	  jpg = new JPanelGraphe(new BorderLayout(),this.graphe);
	  jpg.setBackground(Color.GRAY);
	  jpg.addMouseListener(new ListenerBoutonGraphe(this));
	  jpg.addMouseMotionListener(new ListenerMouseMotionGraphe(this) );
	  return jpg;
  }
  


public JTabbedPane getOnglets() {
	return onglets;
}

public Graphe getGraphe() {
	return graphe;
}

public JToggleButton getBclic() {
	return bclic;
}

public void setOnglets(JTabbedPane onglets) {
	this.onglets = onglets;
}

public void setGraphe(Graphe graphe) {
	this.graphe = graphe;
}

public void setBclic(JToggleButton bclic) {
	this.bclic = bclic;
}

public void setGrapheReset(){
	this.graphe = new Graphe();
}

public JPanelGraphe getJpg() {
	return jpg;
}

public JToggleButton getBsommet() {
	return bsommet;
}

public void setJpg(JPanelGraphe jpg) {
	this.jpg = jpg;
}

public void setBsommet(JToggleButton bsommet) {
	this.bsommet = bsommet;
}

public JButton getBarcarrete() {
	return barcarrete;
}

public void setBarcarrete(JButton barcarrete) {
	this.barcarrete = barcarrete;
}
  
  
}
