package graphe;

import java.util.*;

public class Dijkstra {
	private int[] dmin;
	private Sommet source;
	private int[] sommetProche;
	private Graphe graphe;
	private boolean[] marquage;

	public Dijkstra(Sommet source, Graphe graphe) {
		super();
		this.source = source;
		this.graphe = graphe;
		this.sommetProche = new int[graphe.getSommets().size()];
		this.marquage = new boolean[graphe.getSommets().size()];
		this.dmin = new int[graphe.getSommets().size()];
		boolean ui = initialisation();
		if (ui) {
			int indexSource = graphe.getSommets().indexOf(source);
			dijkstraAlgorithm(indexSource);
			System.out.print("sommetProche : ");
			for (int j = 0; j < sommetProche.length; j++) {
				System.out.print("|"+sommetProche[j]+"|");
			}
		}
	}

	public boolean initialisation() {
		if (graphe.getSommets().contains(source)) {
			for (int i = 0; i < graphe.getSommets().size(); i++) {
				if (!graphe.getSommets().get(i).equals(source)) {
					dmin[i] = Integer.MAX_VALUE;
				}
				sommetProche[i] = -1;
				marquage[i] = false;
			}
			return true;
		}
		return false;
	}

	public void dijkstraAlgorithm(int index) {
		while (!isMarqued()) {
			int[] tabVoisins = graphe.tabVoisins(index);
			index = smallestValue(tabVoisins);
			if (index == -1) {
				index = smallestValue();
			}
			if (index == -1) {
				index = marquageFirstIndex();
			}
			dminAJour(index);
			marquage[index] = true;
		}
	}

	public int smallestValue() {
		int smallest = Integer.MAX_VALUE;
		if (dmin.length == 0) {
			return 0;
		}
		int val = -1;
		for (int i = 0; i < dmin.length; i++) {
			if (smallest > dmin[i] && !marquage[i]) {
				smallest = dmin[i];
				val = i;
			}
		}
		return val;
	}

	public int smallestValue(int[] tabVoisins) {
		int smallest = Integer.MAX_VALUE;
		if (dmin.length == 0) {
			return 0;
		}
		int val = -1;
		for (int i = 0; i < tabVoisins.length; i++) {
			if (smallest > dmin[tabVoisins[i]] && !marquage[i]) {
				smallest = dmin[tabVoisins[i]];
				val = i;
			}
		}
		return val;
	}

	public void dminAJour(int sommetMarque) {
		int[] tabVoisins = graphe.tabVoisins(sommetMarque);
		if (tabVoisins.length == 0) {
			return;
		}
		for (int i = 0; i < tabVoisins.length; i++) {
			int sommeDmin = dmin[sommetMarque] + valArc(graphe.getSommets().get(sommetMarque), graphe.getSommets().get(tabVoisins[i]));
			if (dmin[tabVoisins[i]] > sommeDmin) {
				sommetProche[tabVoisins[i]] = sommetMarque;
				dmin[tabVoisins[i]] = sommeDmin;
			}
		}
	}

	public boolean isMarqued() {
		boolean ui = true;
		for (int i = 0; i < marquage.length; i++) {
			if (!marquage[i]) {
				ui = false;
			}
		}
		return ui;
	}

	public int marquageFirstIndex() {
		for (int i = 0; i < marquage.length; i++) {
			if (!marquage[i]) {
				return i;
			}
		}
		return -1;
	}

	public int valArc(Sommet a, Sommet b) {
		if (graphe.metrique()) {
			for (Arc arc : a.getArcs()) {
				if (graphe.isType() == Graphe.ORIENTE) {
					if (arc.getArrivee().equals(b)) {
						return Integer.parseInt(arc.getNom());
					}
				} else {
					if (arc.getArrivee().equals(b) || arc.getOrigine().equals(b)) {
						return Integer.parseInt(arc.getNom());
					}
				}
			}
		}
		return 0;
	}

	public ArrayList<Sommet> distanceSource(Sommet arrive){
		ArrayList<Sommet> tabFinal = new ArrayList<Sommet>();

		int indexLocal = graphe.getSommets().indexOf(arrive);
		int i=1;
		tabFinal.add(graphe.getSommets().get(indexLocal));
		while(indexLocal!=-1){
			if(indexLocal!=-1){
				tabFinal.add(graphe.getSommets().get(indexLocal));
				indexLocal=sommetProche[indexLocal];
			}
		}
		System.out.print("tabFinal : ");
		for (int j = 0; j < tabFinal.size(); j++) {
			System.out.print("|"+tabFinal.get(j)+"|");
		}
		return tabFinal;
	}
	
	public ArrayList<Arc> distanceSourceArc(Sommet arrive){
		ArrayList<Arc> tabFinal = new ArrayList<Arc>();

		int indexLocal = graphe.getSommets().indexOf(arrive);
		while(indexLocal!=-1){
			if(indexLocal!=-1 && sommetProche[indexLocal]!=-1){
				tabFinal.add(graphe.arcaPartirSommets(graphe.getSommets().get(indexLocal),graphe.getSommets().get(sommetProche[indexLocal])));
				indexLocal=sommetProche[indexLocal];
			}
		}
		System.out.print("tabFinal : ");
		for (int j = 0; j < tabFinal.size(); j++) {
			System.out.print("|"+tabFinal.get(j)+"|");
		}
		return tabFinal;
	}

	public int[] getDmin() {
		return dmin;
	}
	public Sommet getSource() {
		return source;
	}
	public int[] getSommetProche() {
		return sommetProche;
	}
	public Graphe getGraphe() {
		return graphe;
	}
	public void setDmin(int[] dmin) {
		this.dmin = dmin;
	}
	public void setSource(Sommet source) {
		this.source = source;
	}
	public void setSommetProche(int[] sommetProche) {
		this.sommetProche = sommetProche;
	}
	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}

	public boolean[] getMarquage() {
		return marquage;
	}

	public void setMarquage(boolean[] marquage) {
		this.marquage = marquage;
	}
}