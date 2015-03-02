package graphe;

import java.util.*;

public class Sommet {
	private String nom;
	private ArrayList<Arc> arcs;
	private int posX;
	private int posY;
	
	public Sommet(String nom, ArrayList<Arc> arcs, int posX, int posY) {
		this.nom = nom;
		this.arcs = arcs;
		this.posX = posX;
		this.posY = posY;
	}
	
	public Sommet(){
		this("",new ArrayList<Arc>(),0,0);
	}

	public String getNom() {
		return nom;
	}

	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setArcs(ArrayList<Arc> arcs) {
		this.arcs = arcs;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public String toString() {
		return "Sommet [nom=" + nom + ", arcs=" + arcs + ", posX=" + posX
				+ ", posY=" + posY + "]";
	}
	
}
