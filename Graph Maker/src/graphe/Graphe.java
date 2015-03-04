package graphe;

import java.awt.event.*;
import java.util.*;

public class Graphe {
	public static boolean ORIENTE = true;
	public static boolean NON_ORIENTE = false;
	private String nom;
	private boolean type;
	private ArrayList<Sommet> sommets;
	private ArrayList<Arc> arcs;


	public Graphe(String nom, boolean type, ArrayList<Sommet> sommets,
			ArrayList<Arc> arcs) {
		this.nom = nom;
		this.type = type;
		this.sommets = sommets;
		this.arcs = arcs;
	}

	public Graphe(){
		this("",Graphe.NON_ORIENTE,new ArrayList<Sommet>(),new ArrayList<Arc>());
	}

	public void switchTypeOfGraphe(){
		if(this.type)
			this.setType(Graphe.NON_ORIENTE);
		else
			this.setType(Graphe.ORIENTE);
	}
	public String getNom() {
		return nom;
	}

	public boolean isType() {
		return type;
	}

	public ArrayList<Sommet> getSommets() {
		return sommets;
	}

	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public void setSommets(ArrayList<Sommet> sommets) {
		this.sommets = sommets;
	}

	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}

	public String toString() {
		return "Graphe [nom=" + nom + ", type=" + type + ", sommets=" + sommets
				+ ", arcs=" + arcs + "]";
	}

	public Sommet getSommet(int posX, int posY){
		return null;
	}

	public Sommet isSommet(int diametre,MouseEvent e){
		int rayon = diametre/2;
		for(Sommet s : this.sommets){
			if(s.getPosX()-rayon<=e.getX() && s.getPosY()-rayon<=e.getY() && (s.getPosX()+rayon)>=e.getX() && (s.getPosY()+rayon)>=e.getY()){
				return s;
			}
		}
		return null;
	}

	public boolean arcInGraphe(Arc arc_g)
	{
		boolean ui = false;

		for(Arc a : this.arcs)
		{
			if(arc_g.equals(a))
				ui = true;
		}
		return ui;
	}

}
