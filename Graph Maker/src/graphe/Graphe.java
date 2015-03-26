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

	/**
	 * Constructeur champ a champ du graphe prend en paramètres :
	 * \n - Une chaine de caractère correspondant au nom du graphe
	 * \n - Un booleen indiquant si le graphe est orienté ou non
	 * \n - Une liste de sommets
	 * \n - Une liste d'arcs
	 * @param nom
	 * @param type
	 * @param sommets
	 * @param arcs 
	 */
	public Graphe(String nom, boolean type, ArrayList<Sommet> sommets,
			ArrayList<Arc> arcs) {
		this.nom = nom;
		this.type = type;
		this.sommets = sommets;
		this.arcs = arcs;
	}

	/**
	 * Contructeur par défaut d'un graphe,
	 * Créer un graphe sans nom, non orienté sans, avec une liste de sommets vide
	 * et une liste d'arcs vide.
	 */
	public Graphe(){
		this("",Graphe.NON_ORIENTE,new ArrayList<Sommet>(),new ArrayList<Arc>());
	}

	/**
	 * Méthode qui permet d'orienter un graphe non orienté, ou si celui-ci est orienté de
	 * le de-orienter.
	 */
	public void switchTypeOfGraphe(){
		if(this.type)
			this.setType(Graphe.NON_ORIENTE);
		else
			this.setType(Graphe.ORIENTE);
	}

	/**
	 * La méthode isSommet prend en paramètre un entier correspondant au diamètre
	 * d'un sommet, et d'un objet de la classe MouseEvent permettant de connaître
	 * les coordonnées où l'utilisateur a cliqué dans la fenêtre de graphe,
	 * Cette méthode retourne un objet de type Sommet qui correspond au sommet
	 * corresponant au sommet présent a l'endroit ou l'utilisateur a cliqué, ou null
	 * si l'utilisateur n'a pas cliqué sur un sommet.
	 * @param diametre
	 * @param e
	 * @return Sommet
	 */
	public Sommet isSommet(int diametre,MouseEvent e){
		int rayon = diametre/2;
		for(Sommet s : this.sommets){
			if(s.getPosX()-rayon<=e.getX() && s.getPosY()-rayon<=e.getY() && (s.getPosX()+rayon)>=e.getX() && (s.getPosY()+rayon)>=e.getY()){
				return s;
			}
		}
		return null;
	}

	/**
	 * La méthode isArc prend en paramètre un diamètre définit arbitrairement et un
	 * objet de la classe MouseEvent permettant de connaître les coordonnées où
	 * l'utilisateur a cliqué,
	 * Cette méthode retourne l'arc correspondant a l'arc auquel l'utilisateur a
	 * cliqué en son milieu, et null si l'utilisateur n'a pas cliqué sur le milieu
	 * d'un arc.
	 * @param diametre
	 * @param e
	 * @return Arc
	 */
	public Arc isArc(int diametre,MouseEvent e){
		int rayon = diametre/2;
		for(Arc a : this.arcs)
		{
			if(a.getCentre_posX()-rayon<=e.getX() && a.getCentre_posY()-rayon<=e.getY() && (a.getCentre_posX()+rayon)>=e.getX() && (a.getCentre_posY()+rayon)>=e.getY())
				return a;

		}
		return null;
	}

	/**
	 * La méthode positionSommets prend en paramètre un Sommet et retourne 
	 * son indice dans la liste des sommets du graphe.
	 * @param s
	 * @return int
	 */
	public int positionSommets(Sommet s){
		return this.sommets.indexOf(s);
	}


	/*public int nbArcs(){
		int nb=0;
		for(Sommet s : this.sommets)
			nb+=s.nbArc();

		return nb;
	}*/

	/**
	 * La méthode metrique revoie un booléen indiquant si le graphe est métrique
	 * ou non.
	 * @return 
	 */
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

	/**
	 * La méthode voisins prend en paramètre deux sommets s1 et s2 et renvoie true
	 * si ceux-ci sont voisins (ont un arc en commun) et false sinon.
	 * @param s1
	 * @param s2
	 * @return 
	 */
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

	/**
	 * La méthode voisinsColorie prend en paramètre un sommet s1 et un tableau d'entiers
	 * correspondant au nombre de degrés (arcs sortants/entrants) de chaque sommet du
	 * graphe, Cette méthode retourne un entier correspondant au nombre de sommets
	 * voisins du sommet s1 qui sont coloriés.
	 * @param s1
	 * @param dsat
	 * @return 
	 */
	public int voisinsColorie(Sommet s1, int[] dsat)
	{
            int color = 0;

            for(Sommet s : sommets)
            {
		if((this.voisins(s1, s) &&
                    dsat[sommets.indexOf(s)] == Integer.MAX_VALUE))
                    color++;
            }
            return color;
	}

	/**
	 * La méthode initialisation créer un tableau d'entier d'une taille correspondant
	 * au nombre de sommet présents dans le graphe, et pour chaque sommet correspondant
	 * effecte a la case correspondant a son indice le degré ou nombre d'arcs associé
	 * a ce sommet, et retourne ce tableau.
	 * @return 
	 */
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

	/**
	 * La méthode isNotColored prend en paramètre un tableau d'entiers correspondant au
	 * degré de chaque sommet du graphe, Cette fonction parcours tout ce tableau et
	 * si un sommet est colorié (la case correspondant a ce sommet est à 
	 * Integer.MAX_VALUE) renverra false, et true sinon (alors tous les sommets du
	 * graphe seront coloriés).
	 * @param tab
	 * @return 
	 */
	public boolean isNotColored(int[] tab)
	{
		boolean ui  = false;
		for(int i=0; i<tab.length; i++)
		{
			if(tab[i] != Integer.MAX_VALUE)  // tant qu'il y a des elements non colories
				ui = true; 
		}
		return ui;
	}


	/**
	 * La méthode trie prend en paramètre un tableau d'entiers correspondant au degré
	 * de chaque sommet et renvoie un tableau temporaire trié décroissant du tableau
	 * DSAT.
	 * @param DSAT
	 * @return 
	 */
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

	/**
	 * La méthode DSATAJour prend en paramètre un tableau d'entiers correspondant
	 * au degré de chaque sommets du graphe, Cette méthode met à jour ce tableau
	 * lorsqu'un sommet a été colorié.
	 * @param DSAT 
	 */
	public void DSATAJour(int[] DSAT)
	{
		if(isNotColored(DSAT))
		{
			for(int i=0;i<DSAT.length;i++)
			{
				if(DSAT[i]!=Integer.MAX_VALUE &&
						voisinsColorie(this.getSommets().get(i),DSAT)!=0)
					DSAT[i]=voisinsColorie(this.getSommets().get(i),DSAT);
			}
		}
	}

	/**
	 * La méthode plusGrandDegre prend en paramètre un tableau d'entiers DSAT
	 * correspondant au degré de chaque sommet du graphe et si ce graphe n'est pas
	 * pas entièrement colorié renvoie la plus grande valeur présente dans ce tableau
	 * (excepté Integer.MAX_VALUE car correspond a un sommet colorié), renvoie -1
	 * si le graphe est entièrement colorié.
	 * @param DSAT
	 * @return 
	 */
	public int plusGrandDegre(int[] DSAT)
	{
		int val=-1;
                int tmp = 0;
		if(isNotColored(DSAT)){
			val = 0;
			for(int i=0;i<DSAT.length;i++)
			{
				if(tmp<DSAT[i] && DSAT[i]!=Integer.MAX_VALUE
                                       /* && DSAT[i] > tmp || DSAT[i-1] == Integer.MAX_VALUE*/)
                                    {
                                        tmp = DSAT[i];
					val=i;
                                    }
			}
		}
		return val;
	}


	public void defColor(int[] colors, int index,int[] DSAT) // definis la couleur du sommet à partir de l'index
	{
		int []tabVoisins=tabVoisins(index);
                if(tabVoisins.length>0)
                {
                    int []color = new int[tabVoisins.length];
                    
                    for(int i=0;i<color.length;i++)
                    {
                        if(tabVoisins[i]!=-1)
                            color[i]=colors[tabVoisins[i]];
                    }
                    
                    //System.out.print("color | index "+index+" : ");
                    
                    //for(int i=0;i<color.length;i++)
                    //    System.out.print(color[i]+"-");
                    
                    //System.out.println();
                    //System.out.print("tabVoisins | index "+index+" : ");
                    
                    //for(int i=0;i<tabVoisins.length;i++)
                    //    System.out.print(tabVoisins[i]+"-");
                
                    System.out.println();
                    Arrays.sort(color);
                    int valMax = color[color.length-1];
                    //System.out.println("valMax = "+valMax);
                    colors[index]=valMax+1;
                    
                    if(color.length == 1)
                    {
                        if(color[0] > 1)
                            colors[index] = 1;
                        else
                            colors[index] = 2;
                    }   
                    
                    for(int i = 1; i < color.length; i++)
                    {
                        if(color[i] - color[i-1] > 1)
                            colors[index] = color[i-1] + 1;
                    }
                }
		/*int []tabVoisins = tabVoisins(index); // on recupere sous forme de tableau les voisins de l'index
		int []colorsLocal = colors;
		for(int i=0;i<tabVoisins.length;i++){
			if(tabVoisins.length<colors.length)
				colorsLocal[i]=colors[tabVoisins[i]];
		}
		this.tri(colorsLocal);// on trie le tableau de voisins pour faciliter la recherche des couleurs non utilisées dans le tableau
		int valMax = colorsLocal[0];

		if(this.voisinsColorie(this.sommets.get(index),DSAT)==0)
			colors[index]=valMax+1;
		else{
			/*for(int i=0;i<valMax;i++){ // A FAIR A FAIR A FAIR
				for(int j = 0; j <colors.length; j++)
				{
					if(colorsLocal[i]!=j && colorsLocal[i]<j+1)
						colors[index]=i;
				}
			}
		}
		System.out.println(colors[index]+"-");
		//System.out.println("valMax : "+colors[index]);*/
	}

	/**
	 * La methode tabVoisins prend en paramètre un entier index et renvoie un tableau
	 * d'entiers correspondant au degré de chaque sommet voisin du sommet présent à
	 * l'index 
	 * @param index
	 * @return 
	 */
	public int[] tabVoisins(int index) // renvoie les positions des voisins du sommet courant
	{
		int[]tab=new int[this.sommets.get(index).getArcs().size()]; // créé un tableau local a partir de la taille de son ArrayList d'arc qui correspond au nb de sommets voisin du sommet courant
		for(int i = 0; i < this.sommets.get(index).getArcs().size(); i++){
			int voisinArrivee=this.sommets.indexOf(this.sommets.get(index).getArcs().get(i).getArrivee()); // on récupère la position des sommets voisins et on les ajoute dans notre tableau local tab
			if(voisinArrivee!=index) // si la position du voisin est different de la position du sommet courant et si sa couleur est differente de 0 alors on ajoute la position du voisin dans notre tableau
				tab[i]=voisinArrivee;
			else{
				voisinArrivee=this.sommets.indexOf(this.sommets.get(index).getArcs().get(i).getOrigine());
				tab[i]=voisinArrivee;
			}
		}
		return tab;
	}

        public void sortSommets(int[] DSAT)
        {
            int x = this.plusGrandDegre(DSAT);
        }
        
	public int[] coloration()
	{
		int color[] = new int[this.getSommets().size()];
		int DSAT[] = initialisation();
                /*int x;
                Sommet s;
                for(int i = 0; i < DSAT.length; i++)
                {
                    for(int j = 0; j < DSAT.length-1; j++)
                    {
                        if(DSAT[j+1] > DSAT[j] && DSAT[j+1] != Integer.MAX_VALUE)
                        {
                            x = DSAT[j+1];
                            DSAT[j+1] = DSAT[j];
                            DSAT[j] = x;
                            s = this.getSommets().get(j+1);
                            this.getSommets().set(j+1,this.getSommets().get(j+1));
                            this.getSommets().set(j, s);
                        }
                    }
                }*/
                
		int degree = -1;

                //System.out.println("le plus grand degré : " + this.plusGrandDegre(DSAT));
		while(isNotColored(DSAT))
		{
                    for(int i = 0; i < DSAT.length; i++)
                        System.out.print("DSAT : " + DSAT[i] + " ");
			degree = this.plusGrandDegre(DSAT);
			//System.out.println("degree "+degree+" : "+this.plusGrandDegre(DSAT));
			if(degree!=-1)
			{
				defColor(color,degree, DSAT);
				//System.out.println(defColor(color,degree));

				/*for(int i=0;i<color.length;i++)
                System.out.print(color[i]+"|\t");
                System.out.println();
                for(int i=0;i<color.length;i++)
                    System.out.print(DSAT[i]+"|\t");*/

				DSAT[degree]=Integer.MAX_VALUE;
				DSATAJour(DSAT);
			}
		}
		return color;
	}

	public boolean connexe()
	{
		return true;
	}

	public boolean isNumeric(String str)  
	{  
		try  
		{  
			@SuppressWarnings("unused")
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

                if(this.isType() == ORIENTE)
                {
                    for(Arc a : this.arcs)
                    {
			if(arc_g.equals(a))
				ui = true;
                    }
                }
                else
                {
                    for(Arc a : this.arcs)
                    {
                        if(arc_g.equals(a) || 
                                (arc_g.getArrivee()== a.getOrigine()
                                && arc_g.getOrigine() == a.getArrivee()))
                            ui = true;
                    }
                }    
		return ui;
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

	public Sommet getSommet(int posX, int posY){
		return null;
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

}
