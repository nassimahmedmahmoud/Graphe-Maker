package graphe;
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
			this.setType(false);
		else
			this.setType(true);
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
	
	
	
}
