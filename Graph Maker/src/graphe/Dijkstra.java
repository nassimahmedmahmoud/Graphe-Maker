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
		this.sommetProche = new int[graphe.getArcinit().size()];
		this.marquage = new boolean[graphe.getArcinit().size()];
		this.dmin = new int[graphe.getArcinit().size()];
	}

	public Dijkstra(Graphe graphe){
		this.graphe = graphe;
		this.sommetProche = new int[graphe.getArcinit().size()];
		this.marquage = new boolean[graphe.getArcinit().size()];
		this.dmin = new int[graphe.getArcinit().size()];
	}

	public void algorithmDijkstra(){
		boolean ui = initialisation();
		if (ui && source!=null) {
			int indexSource = graphe.getArcinit().indexOf(source);
			dijkstraAlgorithm(indexSource);
		}
	}

	public boolean initialisation() {
		if (graphe.getArcinit().contains(source)) {
			for (int i = 0; i < graphe.getArcinit().size(); i++) {
				if (!graphe.getArcinit().get(i).equals(source))
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
			String s = "indexDep :"+index;
			index = smallestValue(tabVoisins);
			System.out.println(s+" index 1  : "+index+"\t");
			if (index == -1) {
				index = smallestValue();
			}
			if (index == -1){
				index = marquageFirstIndex();
			}
			if(index!=-1)
				dminAJour(index);
			marquage[index]=true;
			System.out.println();
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
			if ((smallest > dmin[tabVoisins[i]]) && !marquage[tabVoisins[i]]) {
				smallest = dmin[tabVoisins[i]];
				val = tabVoisins[i];
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
		System.out.println("sommet Marque: "+sommetMarque+" tabvoisins : ");
		for(int i=0;i<tabVoisins.length;i++){
			System.out.print(tabVoisins[i]+"\t");
		}
		System.out.println("");
		
		if (tabVoisins.length == 0) {
			return;
		}
		for (int i = 0; i < tabVoisins.length; i++){
			int sommeDmin = dmin[sommetMarque] + valArc(graphe.getArcinit().get(sommetMarque), graphe.getArcinit().get(tabVoisins[i]));
			if (dmin[tabVoisins[i]] > sommeDmin && sommeDmin>0) {
				dmin[tabVoisins[i]] = sommeDmin;
				sommetProche[tabVoisins[i]] = sommetMarque;
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
		int indexLocal = graphe.getArcinit().indexOf(arrive);
		tabFinal.add(graphe.getArcinit().get(indexLocal));
		while(indexLocal!=-1){
			if(indexLocal!=-1){
				tabFinal.add(graphe.getArcinit().get(indexLocal));
				indexLocal=sommetProche[indexLocal];
			}
		}
		return tabFinal;
	}

	public ArrayList<Arc> distanceSourceArc(){
		ArrayList<Arc> tabFinal = new ArrayList<Arc>();
		int indexLocal = graphe.getArcinit().indexOf(puit);
		while(indexLocal!=-1){
			Arc a=null;
			if(sommetProche[indexLocal]!=-1)
				a= graphe.arcaPartirSommets(graphe.getArcinit().get(indexLocal),graphe.getArcinit().get(sommetProche[indexLocal]));
			if(a!=null)
				tabFinal.add(a);
			indexLocal=sommetProche[indexLocal];
		}
		return tabFinal;
	}
	
	public int[] tabVoisins(int index)
	{
		int[]tab=new int[graphe.getSommets().get(index).getArcs().size()];
		for(int i = 0; i < graphe.getSommets().get(index).getArcs().size(); i++){
			int voisinArrivee=graphe.getSommets().indexOf(graphe.getSommets().get(index).getArcs().get(i).getArrivee());
			if(voisinArrivee!=index)
				tab[i]=voisinArrivee;
			else if(!graphe.isType()){
				voisinArrivee=graphe.getSommets().indexOf(graphe.getSommets().get(index).getArcs().get(i).getOrigine());
				tab[i]=voisinArrivee;
			}
			else
				tab[i]=voisinArrivee;			
		}
		return tab;
	}
	public boolean successeurPossible(){
		boolean ui=false;
		
		return ui;
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
			s+="<td>"+graphe.getArcinit().get(i).getNom()+"</td>";
		}
		s+="</tr><tr><td>Dmin</td>";
		for(int i=0;i<dmin.length;i++){
			if(dmin[i]!=Integer.MAX_VALUE && dmin[i]!=Integer.MIN_VALUE)
				s+="<td>"+dmin[i]+"</td>";
			else
				s+="<td>Impossible</td>";
		}
		s+="</tr><tr><td>Sommets proches</td>";
		for(int i=0;i<sommetProche.length;i++){
			if(sommetProche[i]!=-1)
				s+="<td>"+graphe.getArcinit().get(sommetProche[i]).getNom()+"</td>";
			else
				s+="<td>#</td>";
		}
		s+="</tr></table></html>";
		return s;
	}

}