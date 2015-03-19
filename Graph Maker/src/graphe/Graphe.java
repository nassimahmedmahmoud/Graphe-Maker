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

	public boolean voisins(Sommet s1, Sommet s2)
	{
		boolean ui = false;

		for(Arc a : arcs)
		{
			if((s1 == a.getOrigine() && s2 == a.getArrivee()) || (s2 == a.getOrigine() && s1 == a.getArrivee()))
				ui = true;
		}
		return ui;
	}

	public int voisinsColorie(Sommet s1, int[] dsat)
	{
		int color = 0;

		for(Arc a : arcs)
		{
			if((this.voisins(s1, a.getOrigine()) &&
					dsat[sommets.indexOf(a.getOrigine())] == Integer.MAX_VALUE)
					|| (this.voisins(s1, a.getArrivee()) &&
							dsat[sommets.indexOf(a.getArrivee())] == Integer.MAX_VALUE))
				color++;
		}
		return color;
	}

	public int[] initialisation()
	{
		if(!this.isType())
		{
			int DSAT[] = new int[this.getSommets().size()];
			for(int i= 0; i<this.getSommets().size(); i++)
				DSAT[i] = this.getSommets().get(i).getArcs().size();
			return DSAT;
		}
		return null;
	}

	public boolean isColored(int[] tab)
	{
		boolean ui  = false;
		for(int i=0; i<tab.length; i++)
		{
			if(tab[i] != Integer.MAX_VALUE)  // tant qu'il y a des elements non colories
				ui = true; 
		}
		return ui;
	}


	public int[] tri(int[] DSAT)
	{
		int tmp[]= new int[this.getSommets().size()];
		for(int i=0;i<DSAT.length;i++){
			for(int j=0;j<DSAT.length;j++){
				if(DSAT[i]>DSAT[j])
					tmp[i]=DSAT[j];
			}
		}
		return tmp;
	}
	/*
    public Sommet[] metAJourDSAT(int[] DSAT)
    {
        ArrayList<Sommet> tab = new ArrayList<Sommet>();
        int val = -1;
        for(int i=0; i<DSAT.length; i++)
        {
            if(DSAT[i] != Integer.MAX_VALUE)
                tab.add(this.getSommets().get(i));
        }
    }*/

	public void DSATAJour(int[] DSAT)
	{
		for(int i=0;i<DSAT.length;i++)
		{
			if(DSAT[i]!=Integer.MAX_VALUE &&
					voisinsColorie(this.getSommets().get(i),DSAT)!=0)
				DSAT[i]=voisinsColorie(this.getSommets().get(i),DSAT);
		}
	}

	public int plusGrandDegre(int[] DSAT)
	{
		int val=0;
		for(int i=1;i<DSAT.length;i++)
		{
			if(DSAT[i-1]<DSAT[i])
				val=i;
		}
		return val;
	}

	public int defColor(int[] colors, int index)
	{
		int []tabVoisins = tabVoisins(index,colors);
		this.tri(tabVoisins);
		for(int j = 1; j <tabVoisins.length; j++)
		{
			if(tabVoisins[j]!=j && tabVoisins[j-1]!=tabVoisins[j])
				return j;
		}
		return tabVoisins[tabVoisins.length-1]+1;
	}

	public int[] tabVoisins(int index,int[]color)
	{
		int[]tab=new int[this.sommets.get(index).getArcs().size()];
		for(int i = 0; i < this.sommets.get(index).getArcs().size(); i++){
			int voisinArrivee=color[this.sommets.indexOf(this.sommets.get(index).getArcs().get(i).getArrivee())];
			if(voisinArrivee!=index && color[voisinArrivee]!=0)
				tab[i]=color[voisinArrivee];
		}
		return tab;
	}

	public int[] coloration()
	{
		int color[] = new int[this.getSommets().size()];
		int DSAT[] = initialisation();
		int degree = -1;

		while(isColored(DSAT))
		{
			degree = this.plusGrandDegre(DSAT);
			color[degree] = defColor(color,degree);
			System.out.println(defColor(color,degree));
			DSAT[degree]=Integer.MAX_VALUE;
			DSATAJour(DSAT);
		}
		return color;
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

	public int [][] matriceNonOriente(){
		int tab[][]=new int[this.sommets.size()][this.sommets.size()];
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
						tab[i][pos]=Integer.parseInt(a.getNom());
					else
						tab[i][pos]=1;
				}
			}
			i++;
		}
		return tab;
	}

	public int[][] matriceOriente(){
		int tab[][]=new int[this.sommets.size()][this.sommets.size()];
		int i=0;
		boolean metrique = metrique();
		for(Sommet s : this.sommets){
			//System.out.println(s.getArcs());
			for(Arc a : s.getArcs()){
				int pos=0;
				if(a.getOrigine()==s){
					pos = positionSommets(a.getArrivee());
					if(pos!=-1){
						if(metrique)
							tab[i][pos]=Integer.parseInt(a.getNom());
						else
							tab[i][pos]=1;
					}
				}
			}
			i++;
		}
		return tab;
	}

	public int[][]matrice(){
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
