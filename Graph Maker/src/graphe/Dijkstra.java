package graphe;

import java.util.*;

public class Dijkstra {
	/*// creation du graphe
=======
	/* // creation du graphe
>>>>>>> Stashed changes
    public static final int INFINITE = Integer.MAX_VALUE;
    private int x0;
    private int [] S;//ensemble de sommets dont les distances les plus courtes à la source sont connues au depart seulement Source
    private int [] sommetProche;//ensemble des predecesseur des sommets de 0 à N-1;
    private Graphe graphe;
    private int [] d_min;//tableau des valeurs du meilleur raccourci pour se rendre à chaque sommet
    // rajout
    private boolean [] noeudsMarques;
    private static int DIMENSION_MATRICE;//je rajoute ça pour simplifier le code.
    private static int NOMBRE_ARCS;//je rajoute ça pour simplifier le code.

    public Dijkstra( int x, Graphe graphe){   
        x0 = x;
        this.graphe= graphe;
        DIMENSION_MATRICE = graphe.getSommets().size();
        NOMBRE_ARCS=graphe.getArcs().size();
        S = new int [DIMENSION_MATRICE];//sommets atteints
        d_min = new int [DIMENSION_MATRICE];//distances
        noeudsMarques = new boolean[DIMENSION_MATRICE];
        sommetProche = new int [DIMENSION_MATRICE];
        calculePlusCourtChemin();
    }

    private void calculePlusCourtChemin(){
        int n =0;
        for (int a = 0; a < DIMENSION_MATRICE; a++){
            noeudsMarques[a] =false;
            S[a]=-1; //en supposant qu'on numerote les sommets de 0 ou de 1 à n.
            sommetProche[a]=-1; // pour les noeuds qui n'ont pas de predecesseur
            d_min[a]=INFINITE;
        }
        S[n]=x0;
        d_min[x0]=0;
        sommetProche[x0]=x0;

        initDistMin();

        for (int i = 0; i< DIMENSION_MATRICE ;i++){   
            if (!noeudsMarques[i]){
                int t = choixSommet();
                noeudsMarques[t] = true;
                n++;
                S[n]=t;
                majDistMin(t);
            }
        }
    }

    //implementation de initDistMin
    private void initDistMin(){
        noeudsMarques[x0]=true;
        int[][] matrice = graphe.matrice();
        System.out.println("Matrice : ");
        for(int i=0;i<matrice.length;i++){
        	for(int j =0;j<matrice[i].length;j++){
        		System.out.print(matrice[i][j]+"-");
        	}
        	System.out.println();
        }
        for (int i=0; i<graphe.getSommets().get(x0).getArcs().size();i++){
            if(graphe.getSommets().get(x0).getArc(i)!=null){
              d_min[i] =matrice[x0][i];
                sommetProche[i] = x0;
            }
            else {
                if (!graphe.getSommets().get(x0).equals(graphe.getSommets().get(i)))
                     d_min[i]=INFINITE;
            }
        }
    }

    private void majDistMin(int n){
        for (int i = 0; i < graphe.getSommets().get(n).getArcs().size(); i++){           
                if (!noeudsMarques[i]){
                    //D[i] = min(D[i], D[n] + distanceDsGraphe(n,i));
                    if (d_min[n] + distanceDsGraphe(n,i)<d_min[i]){
                        d_min[i]=d_min[n] + distanceDsGraphe(n,i);
                        sommetProche[i]=n;
                    }
                }
        }
    }
    private int distanceDsGraphe (int t, int s){
        if (graphe.getSommets().get(t).getArc(s)!=null){       
            return graphe.matrice()[t][s];
        }
        else {
            return INFINITE;
        }
    }

    public int choixSommet(){
        int valeurMin = INFINITE;
        int min = x0;
        for (int i=0; i<DIMENSION_MATRICE ;i++){
            if (!noeudsMarques[i])
                if (d_min[i]<=valeurMin){
                    min = i;
                    valeurMin = d_min[i];
                }
        }
        return min;
    }



    public int longueurChemin (int i){
        return d_min[i];
    }
    //fonction à definir min
    private int min (int i, int j){
        if (i<=j)
            return i;
        else return j;
    }
    public void afficheChemin(int i){
        int source = x0;
        int antecedent = i;
        ArrayList<Integer> lesNoeudsIntermediaires = new ArrayList<Integer>();;
        System.out.println("Chemin de "+x0+" à "+ i+":");
    	System.out.println("Antecedent : "+antecedent);
    	System.out.println("Source : "+source);
    	System.out.print("Sommet proche : ");
    	for(int j=0;j<sommetProche.length;j++)
    		System.out.print(sommetProche[i]+"-");
    	System.out.println();
        while (antecedent!=source){	
        	System.out.println("Antecedent : "+antecedent);

            if(antecedent!=-1){
            	lesNoeudsIntermediaires.add(antecedent);
            	antecedent = sommetProche[antecedent];     	  
            }
            else
            	antecedent=0;
        }
        lesNoeudsIntermediaires.add(source);
        for (int j= lesNoeudsIntermediaires.size()-1; j>0;j--){
            System.out.print("-->"+(lesNoeudsIntermediaires.get(j)+1));
        }
        System.out.println();
<<<<<<< Updated upstream
    }
    }*/

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
                System.out.println("le sommet choisis est bien dans l'arrayList de sommets");
                int indexSource = graphe.getSommets().indexOf(source);
                dijkstraAlgorithm(indexSource);
                System.out.print("Dmin : ");
                for (int i = 0; i < dmin.length; i++) {
                    System.out.print(dmin[i] + "-");
                }
                System.out.println();
                System.out.print("sommetProche : ");
                for (int i = 0; i < sommetProche.length; i++) {
                    System.out.print("|" + sommetProche[i] + "|");
                }
                System.out.println();
                System.out.print("marquage : ");
                for (int i = 0; i < marquage.length; i++) {
                    System.out.print("|" + marquage[i] + "|");
                }
                System.out.println();

            } else {
                System.out.println("Le sommet choisis n'est pas dans l'arrayList de sommets :(");
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
                    index = smallestValue(tabVoisins);
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