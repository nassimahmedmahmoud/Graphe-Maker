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
	
	public Sommet(Sommet s)
        {
            this(s.nom,s.arcs,s.posX,s.posY);
        }

	public void ajouterArc(Arc arc)
	{
		if(!containsArc(arc))
			arcs.add(arc);
	}
        
        public void resetArcs()
        {
            this.arcs = new ArrayList<Arc>();
        }
        
	public boolean containsArc(Arc a){
		for(Arc arc : arcs){
			if(arc.getOrigine().equals(a.getOrigine()) && arc.getArrivee().equals(a.getArrivee())){
				return true;
			}
		}
		return false;
	}

	public String getNom() {
		return nom;
	}
        
	public ArrayList<Arc> getArcs() {
		return arcs;
	}

	public Arc getArc(int indexu)
	{
		return arcs.get(indexu);
	}

	public void setArc(int indexu, Arc arc)
	{
		arcs.set(indexu, arc);
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
		return "Sommet [nom=" + nom /*+ ", arcs=" + arcs*/ + ", posX=" + posX
				+ ", posY=" + posY + "]";
	}
	public int nbArc(){
		return this.arcs.size();
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == null)
			return false;
		if(!(o instanceof Sommet))
			return false;

		Sommet s = (Sommet)o;
		return s.nom == this.nom && s.posX == this.posX && s.posY == this.posY
				&& s.arcs.equals(this.arcs);
	}
}