package graphe;

import java.util.*;

public class Dijkstra {
	private int[] dmin;
	private Sommet source;
	private int[] sommetProche;
	private Graphe graphe;
	private boolean[] marquage;
	private Sommet puit;
        
	public Dijkstra(Sommet source, Graphe graphe) {
		this.source = source;
		this.graphe = graphe;
		this.sommetProche = new int[graphe.getSommets().size()];
		this.marquage = new boolean[graphe.getSommets().size()];
		this.dmin = new int[graphe.getSommets().size()];
	}

	public Dijkstra(Graphe graphe){
		this.graphe = graphe;
		this.sommetProche = new int[graphe.getSommets().size()];
		this.marquage = new boolean[graphe.getSommets().size()];
		this.dmin = new int[graphe.getSommets().size()];
	}

	public void algorithmDijkstra(){
		boolean ui = initialisation();
		if (ui && source!=null) {
			int indexSource = graphe.getSommets().indexOf(source);
			dijkstraAlgorithm(indexSource);
			dmin[0]=dmin[1]+valArc(graphe.getSommets().get(0), graphe.getSommets().get(1));
		}
	}

	public boolean initialisation() {
		if (graphe.getSommets().contains(source)) {
			for (int i = 0; i < graphe.getSommets().size(); i++) {
				if (!graphe.getSommets().get(i).equals(source))
					dmin[i] = Integer.MAX_VALUE;
				else
					dmin[i]=0;
				sommetProche[i] = -1;
				marquage[i] = false;
			}
			return true;
		}
		return false;
	}

	public void dijkstraAlgorithm(int index) {
		do {
			int[] tabVoisins = graphe.tabVoisins(index);

			index = smallestValue(tabVoisins);
			if (index == -1) {
				index = smallestValue();
			}
			if (index == -1) {
				index = marquageFirstIndex();
			}
			System.out.print("dmin : ");
			for(int i=0;i<dmin.length;i++){
				System.out.print(dmin[i]+"|");
			}
			System.out.println();
			dminAJour(index);
		}while(!isMarqued());
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
	
	public boolean dminTest(){
		boolean ui=true;
		for(int i=0;i<dmin.length;i++){
			if(dmin[i]>=Integer.MAX_VALUE)
				return false;
		}
		return ui;
	}

	public void dminAJour(int sommetMarque) {
		int[] tabVoisins = graphe.tabVoisins(sommetMarque);
		if (tabVoisins.length == 0) {
			return;
		}
		for (int i = 0; i < tabVoisins.length; i++){
			int sommeDmin = dmin[sommetMarque] + valArc(graphe.getSommets().get(sommetMarque), graphe.getSommets().get(tabVoisins[i]));
			if (dmin[tabVoisins[i]] > sommeDmin && sommeDmin>0) {
				dmin[tabVoisins[i]] = sommeDmin;
				sommetProche[tabVoisins[i]] = sommetMarque;
			}
		}
		marquage[sommetMarque]=true;
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

	public ArrayList<Arc> distanceSourceArc(){
		ArrayList<Arc> tabFinal = new ArrayList<Arc>();
		int indexLocal = graphe.getSommets().indexOf(puit);
		while(indexLocal!=-1){
			Arc a=null;
			if(sommetProche[indexLocal]!=-1)
				a= graphe.arcaPartirSommets(graphe.getSommets().get(indexLocal),graphe.getSommets().get(sommetProche[indexLocal]));
			if(a!=null){
				System.out.println("Arc : "+a.getNom());
				tabFinal.add(a);
			}
			indexLocal=sommetProche[indexLocal];
			System.out.println("indexLocal : "+indexLocal);
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

	public Sommet getArrivee() {
		return puit;
	}

	public void setArrivee(Sommet arrivee) {
		this.puit = arrivee;
	}

	public String toString(){
		String s = "<html><table border=\"1\"><tr><td>Noms des sommets</td>";
		for(int i=0;i<dmin.length;i++){
			s+="<td>"+graphe.getSommets().get(i).getNom()+"</td>";
		}
		s+="</tr><tr><td>Dmin</td>";
		for(int i=0;i<dmin.length;i++){
			s+="<td>"+dmin[i]+"</td>";
		}
		s+="</tr><tr><td>Sommets proches</td>";
		for(int i=0;i<sommetProche.length;i++){
			if(sommetProche[i]!=-1)
				s+="<td>"+graphe.getSommets().get(sommetProche[i]).getNom()+"</td>";
			else
				s+="<td>#</td>";
		}
		s+="</tr></table></html>";
		return s;
	}

}