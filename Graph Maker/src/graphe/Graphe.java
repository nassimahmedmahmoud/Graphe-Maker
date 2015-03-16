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

	public Arc isArc(int diametre,MouseEvent e){
		int rayon = diametre/2;
		for(Arc a : this.arcs)
		{
			if(a.getCentre_posX()-rayon<=e.getX() && a.getCentre_posY()-rayon<=e.getY() && (a.getCentre_posX()+rayon)>=e.getX() && (a.getCentre_posY()+rayon)>=e.getY())
				return a;

		}
		return null;
	}

	public int positionSommets(Sommet s){
		return this.sommets.indexOf(s);
	}

	public int nbArcs(){
		int nb=0;
		for(Sommet s : this.sommets){
			nb+=s.nbArc();
		}
		return nb;
	}

	public boolean metrique(){
		for(Sommet s : sommets){
			for(Arc a : s.getArcs()){
				boolean ui =isNumeric(a.getNom());
				if(!ui)
					return ui;
			}
		}
		return true;
	}

	public boolean isNumeric(String str)  
	{  
		try  
		{  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	public double [][] matriceNonOriente(){
		double tab[][]=new double[this.sommets.size()][this.sommets.size()];
		int i=0;
		boolean metrique = metrique();
		for(Sommet s : this.sommets){
			for(Arc a : s.getArcs()){
				int pos=0;
				if(a.getOrigine()==s)
					pos = positionSommets(a.getArrivee());
				else
					pos = positionSommets(a.getOrigine());
				if(pos!=-1){
					if(metrique)
						tab[i][pos]=Double.parseDouble(a.getNom());
				}
			}
			i++;
		}
		return tab;
	}

	public double [][] matriceOriente(){
		double tab[][]=new double[this.sommets.size()][this.sommets.size()];
		int i=0;
		boolean metrique = metrique();
		for(Sommet s : this.sommets){
			System.out.println(s.getArcs());
			for(Arc a : s.getArcs()){
				int pos=0;
				if(a.getOrigine()==s){
					pos = positionSommets(a.getArrivee());
					if(pos!=-1){
						if(metrique)
							tab[i][pos]=Double.parseDouble(a.getNom());
					}
				}
			}
			i++;
		}
		return tab;
	}

	public double[][]matrice(){
		if(this.type==Graphe.ORIENTE){
			return matriceOriente();
		}
		else{
			return matriceNonOriente();
		}
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
