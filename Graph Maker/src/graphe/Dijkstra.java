package graphe;
 
import java.util.*;
 
public class Dijkstra {
    /*// creation du graphe
    public static final int INFINITE = Integer.MAX_VALUE;
    private int x0;
    private int [] S;//ensemble de sommets dont les distances les plus courtes à la source sont connues au départ seulement Source
    private int [] sommetProche;//ensemble des prédécesseur des sommets de 0 à N-1;
    private Graphe graphe;
    private int [] d_min;//tableau des valeurs du meilleur raccourci pour se rendre à chaque sommet
    // rajout
    private boolean [] noeudsMarqués;
    private static int DIMENSION_MATRICE;//je rajoute ça pour simplifier le code.
    private static int NOMBRE_ARCS;//je rajoute ça pour simplifier le code.
     
    public Dijkstra( int x, Graphe graphe){   
        x0 = x;
        this.graphe= graphe;
        DIMENSION_MATRICE = graphe.getSommets().size();
        NOMBRE_ARCS=graphe.getArcs().size();
        S = new int [DIMENSION_MATRICE];//sommets atteints
        d_min = new int [DIMENSION_MATRICE];//distances
        noeudsMarqués = new boolean[DIMENSION_MATRICE];
        sommetProche = new int [DIMENSION_MATRICE];
        calculePlusCourtChemin();
    }
     
    private void calculePlusCourtChemin(){
        int n =0;
        for (int a = 0; a < DIMENSION_MATRICE; a++){
            noeudsMarqués[a] =false;
            S[a]=-1; //en supposant qu'on numérote les sommets de 0 ou de 1 à n.
            sommetProche[a]=0; // pour les noeuds qui n'ont pas de prédecésseur
            d_min[a]=INFINITE;
        }
        S[n]=x0;
        d_min[x0]=0;
        sommetProche[x0]=x0;
         
        initDistMin();
         
        for (int i = 0; i< DIMENSION_MATRICE ;i++){   
            if (!noeudsMarqués[i]){
                 
                int t = choixSommet();
                noeudsMarqués[t] = true;
                n++;
                S[n]=t;
                majDistMin(t);
            } //end if
        }//end for
//        for (int i=0; i<dimensionDeLaMatrice;i++){
//            System.out.print(" S[i]"+S[i]);
//        }
//        for (int i=0; i<dimensionDeLaMatrice;i++){
//            System.out.print(" R["+i+"]"+R[i]);
//        }
//        System.out.println();
    }
     
    //implémentation de initDistMin
    private void initDistMin(){
        noeudsMarqués[x0]=true;
        int[][] matrice = graphe.matrice();
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
                if (!noeudsMarqués[i]){
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
            if (!noeudsMarqués[i])
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
    //fonction à définir min
    private int min (int i, int j){
        if (i<=j)
            return i;
        else return j;
    }
    public void afficheChemin(int i){
    	
        int source = x0;
        int antécédent = i;
        ArrayList<Integer> lesNoeudsIntermediaires = new ArrayList<Integer>();;
        System.out.println("Chemin de "+x0+" à "+ i+":");
        while (antécédent!=source){
        	for(int j=0;j<sommetProche.length;j++)
        	System.out.println(sommetProche[j]);
        	
            lesNoeudsIntermediaires.add(antécédent);
            antécédent = sommetProche[antécédent];
             
        }
        lesNoeudsIntermediaires.add(source);
        for (int j= lesNoeudsIntermediaires.size()-1; j>0;j--){
            System.out.print("-->"+lesNoeudsIntermediaires.get(j));
        }
        System.out.println();
    }
      */
}