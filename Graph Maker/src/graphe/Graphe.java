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
	private int tailleSommet=50;
	private ArrayList<Sommet> tabCick;
	private int dist;

	/**
	 * Constructeur champ a champ du graphe prend en paramÃ¨tres :
	 *  - Une chaine de caractÃ¨re correspondant au nom du graphe
	 *  - Un booleen indiquant si le graphe est orientÃ© ou non
	 *  - Une liste de sommets
	 *  - Une liste d'arcs
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
		this.tabCick = new ArrayList<Sommet>();
	}

	/**
	 * Contructeur par dÃ©faut d'un graphe,
	 * CrÃ©er un graphe sans nom, non orientÃ© sans, avec une liste de sommets vide
	 * et une liste d'arcs vide.
	 */
	public Graphe(){
		this("Graphe_1",Graphe.NON_ORIENTE,new ArrayList<Sommet>(),new ArrayList<Arc>());
	}

	public Arc arcaPartirSommets(Sommet s1,Sommet s2){
		for(Arc a : arcs){
			if(a.getOrigine().equals(s2) && a.getArrivee().equals(s1))
				return a;
		}
		return null;
	}
        
        public void supprimerDoublons()
        {
            ArrayList<Arc> tabl = new ArrayList<Arc>(arcs);
            tabl.add(arcs.get(0));
            /*for(int i = 0; i < arcs.size(); i++)
            {
                for(int j = 0; j < arcs.size(); j++)
                    if((arcs.get(i).getOrigine() == arcs.get(j).getArrivee()) &&
                        (arcs.get(i).getArrivee() == arcs.get(j).getOrigine()) && (arcInTab(tabl,tabl.get(i))))
                        tabl.remove(arcs.get(i));
            }*/
            this.setArcs(tabl);
            for(Sommet s : sommets)
            {
                //s.resetArcs();
                for(Arc a : arcs)
                    if(a.getArrivee() == s || a.getOrigine() == s)
                        s.ajouterArc(a);
            }
        }
	
	/**
	 * MÃ©thode qui permet d'orienter un graphe non orientÃ©, ou si celui-ci est orientÃ© de
	 * le de-orienter.
	 */
	public void switchTypeOfGraphe(){
		if(this.type)
                {
                    this.setType(Graphe.NON_ORIENTE);
                    /*System.out.println(arcs);
                    if(this.arcs.size() > 0)
                        this.supprimerDoublons();
                    System.out.println(arcs);*/
                }
                else
			this.setType(Graphe.ORIENTE);
	}

	/**
	 * La mÃ©thode isSommet prend en paramÃ¨tre un entier correspondant au diamÃ¨tre
	 * d'un sommet, et d'un objet de la classe MouseEvent permettant de connaÃ®tre
	 * les coordonnÃ©es oÃ¹ l'utilisateur a cliquÃ© dans la fenÃªtre de graphe,
	 * Cette mÃ©thode retourne un objet de type Sommet qui correspond au sommet
	 * corresponant au sommet prÃ©sent a l'endroit ou l'utilisateur a cliquÃ©, ou null
	 * si l'utilisateur n'a pas cliquÃ© sur un sommet.
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
	 * La mÃ©thode isArc prend en paramÃ¨tre un diamÃ¨tre dÃ©finit arbitrairement et un
	 * objet de la classe MouseEvent permettant de connaÃ®tre les coordonnÃ©es oÃ¹
	 * l'utilisateur a cliquÃ©,
	 * Cette mÃ©thode retourne l'arc correspondant a l'arc auquel l'utilisateur a
	 * cliquÃ© en son milieu, et null si l'utilisateur n'a pas cliquÃ© sur le milieu
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
	 * La mÃ©thode positionSommets prend en paramÃ¨tre un Sommet et retourne 
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

        public void createChaine(int dist, ArrayList<Sommet> tab)
        {
            if(tab.size() > 1)
            {
                Arc a;
                for(int i = 0; i < tab.size()-1; i++)
                {
                    if(dist == 0)
			a = new Arc("",tab.get(i),tab.get(i+1),0,0);
                    else
			a = new Arc(""+dist,tab.get(i),tab.get(i+1),0,0);
                    a.milieu();
                    if(!(arcInGraphe(a)) && (this.type == NON_ORIENTE && tab.get(i) != tab.get(i+1)) || this.type == ORIENTE)
                    {
			arcs.add(a);
			a.getOrigine().getArcs().add(a);
			a.getArrivee().getArcs().add(a);
                    }
                }
            }
        }
        
        public void createCycle(int dist, ArrayList<Sommet> tab)
        {
            createChaine(dist, tab);
            Arc a;
            if(tab.size() > 2)
            {
                if(dist == 0)
                    a = new Arc("",tab.get(tab.size()-1),tab.get(0),0,0);
                else
                    a = new Arc(""+dist,tab.get(tab.size()-1),tab.get(0),0,0);
                a.milieu();
                if(!(arcInGraphe(a))/* && (this.type == NON_ORIENTE && tab.get(i) != tab.get(i+1)) || this.type == ORIENTE*/)
                {
                    arcs.add(a);
                    a.getOrigine().getArcs().add(a);
                    a.getArrivee().getArcs().add(a);
                }
            }
        }
        
	public void createClique(int i)
	{
		if(this.sommets.size() > 1)
		{
			Arc a;
			for(Sommet s : sommets)
			{
				for(Sommet d : sommets)
				{
					if(i == 0)
						a = new Arc("",s,d,0,0);
					else
						a = new Arc(""+i,s,d,0,0);
					a.milieu();
					if(!(arcInGraphe(a)) && (this.type == NON_ORIENTE && s != d) || this.type == ORIENTE)
					{
						arcs.add(a);
						a.getOrigine().getArcs().add(a);
						a.getArrivee().getArcs().add(a);
					}
				}
			}
		}
	}

	public void createClique(int i, ArrayList<Sommet> tab)
	{
		if(tab.size() > 1)
		{
			Arc a;
			for(Sommet s : tab)
			{
				for(Sommet d : tab)
				{
					if(i == 0)
						a = new Arc("",s,d,0,0);
					else
						a = new Arc(""+i,s,d,0,0);
					a.milieu();
					if(!(arcInGraphe(a)) && (this.type == NON_ORIENTE && s != d) || this.type == ORIENTE)
					{
						arcs.add(a);
						a.getOrigine().getArcs().add(a);
						a.getArrivee().getArcs().add(a);
					}
				}
			}
		}
	}

	/**
	 * La mÃ©thode metrique revoie un boolÃ©en indiquant si le graphe est mÃ©trique
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

	public boolean isClique(int[][]matrice){
		boolean ui = true;
		for(int i=0;i<matrice.length;i++){
			for(int j=i+1; j < matrice[0].length ; j++){
				if (matrice[i][j] != matrice[j][i]){
					ui = false;
					break;
				}
			}
		}
		return ui;
	}

	public int[][] createClique(int n, int a){
		int [][] matrice=new int[n][n];
		for(int i=0;i<matrice.length;i++){
			for(int j=0;j<matrice[i].length;j++)
				matrice[i][j]=1;
		}
		return matrice;
	}

	public int isCliqueGraphe(int valMax){
		int[][]matriceGraphe=this.matrice();
		int val=0;
		for(int i=0;i<valMax;i++){
			int[][]clique=createClique(i, i);
			int j=0;
			for(int c=0;c<clique.length;c++){
				for(int d=0;d<clique[c].length;d++){
					for(int a=0;a<matriceGraphe.length;a++){
						for(int b=0;b<matriceGraphe[a].length;b++){
							if(clique[c][d]==matriceGraphe[a][b]){
								j++;
							}
							/////a finir
						}
					}
				}
			}
		}
		return val;
	}

	public boolean connexeGraphe(){
		int tab[]=new int[sommets.size()];
		if(tab.length>0){
			for(int i=0;i<tab.length;i++){
				for(Arc a : sommets.get(i).getArcs()){
					tab[sommets.indexOf(a.getArrivee())]=1;
					tab[sommets.indexOf(a.getOrigine())]=1;
				}
			}
			for(int i=0;i<tab.length;i++){
				if(tab[i]==0)
					return false;
			}
		}
		return true;
	}

	/**
	 * La mÃ©thode voisins prend en paramÃ¨tre deux sommets s1 et s2 et renvoie true
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

	public boolean sommetInGraphe(ArrayList<Sommet> tab, Sommet somm_s)
	{
		boolean ui = false;

		for(Sommet s : tab)
			if(somm_s.equals(s))
				ui = true;

		return ui;
	}

        /*public boolean arcInTab(ArrayList<Arc> tab, Arc arc_g)
	{
		boolean ui = false;

                if(tab.size() > 0)
                {
                    if(this.isType() == ORIENTE)
                    {
                            for(Arc a : tab)
                            {
                                    if(arc_g.equals(a))
                                            ui = true;
                            }
                    }
                    else
                    {
                            for(Arc a : tab)
                            {
                                    if(arc_g.equals(a) || 
                                                    (arc_g.getArrivee()== a.getOrigine()
                                                    && arc_g.getOrigine() == a.getArrivee()))
                                            ui = true;
                            }
                    }
                }
		return ui;
	}*/
        
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

	public void ajouterSommet(Sommet s){
		if(!sommets.contains(s)){
			s.setNom(""+(sommets.size()+1));
			sommets.add(s);
		}
	}

	public String stringMatrice(){
		String s="<html>";
		int[][]tab =matrice();
		for(int i=0;i< tab.length;i++){
			for(int j=0;j< tab.length;j++){
				s+=tab[i][j]+"\t";
			}
			s+="<br/>";
		}
		s+="</html>";
		return s;
	}

	public String connexeArbre(){
		String s ="<html>";
		if(connexeGraphe())
			s+="Le graphe est connexe<br/>";
		else
			s+="Le graphe n'est pas connexe<br/>";
		if(isTree())
			s+="Le graphe est un arbre<br/>";
		else
			s+="Le graphe n'est pas un arbre<br/>";
		s+="</html>";
		return s;
	}

	public int chromatique(){
		int [] colors = coloration();
		int val;
		if(colors!=null && colors.length>1){
			val = colors[0];
			for (int i = 1; i < colors.length; i++)
				if (val<colors[i])
					val = colors[i];
		}
		else if(this.sommets.size() == 1)
			val = 1;
		else
			val = 0;
		return val;
	}

	public boolean isTree(){
		if(this.sommets.size()==1)
			return false;

		if(this.sommets.size()-1==this.arcs.size() && this.type==NON_ORIENTE)
			return true;
		return false;
	}

	/**
	 * La mÃ©thode voisinsColorie prend en paramÃ¨tre un sommet s1 et un tableau d'entiers
	 * correspondant au nombre de degrÃ©s (arcs sortants/entrants) de chaque sommet du
	 * graphe, Cette mÃ©thode retourne un entier correspondant au nombre de sommets
	 * voisins du sommet s1 qui sont coloriÃ©s.
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
	 * La mÃ©thode initialisation crÃ©er un tableau d'entier d'une taille correspondant
	 * au nombre de sommet prÃ©sents dans le graphe, et pour chaque sommet correspondant
	 * effecte a la case correspondant a son indice le degrÃ© ou nombre d'arcs associÃ©
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
	 * La mÃ©thode isNotColored prend en paramÃ¨tre un tableau d'entiers correspondant au
	 * degrÃ© de chaque sommet du graphe, Cette fonction parcours tout ce tableau et
	 * si un sommet est coloriÃ© (la case correspondant a ce sommet est Ã  
	 * Integer.MAX_VALUE) renverra false, et true sinon (alors tous les sommets du
	 * graphe seront coloriÃ©s).
	 * @param tab
	 * @return 
	 */
	public boolean isNotColored(int[] tab)
	{
		boolean ui  = false;
		for(int i=0; i<tab.length; i++)
		{
			if(tab[i] != Integer.MAX_VALUE && tab[i]!=0)  // tant qu'il y a des elements non colories
				ui = true; 
		}
		return ui;
	}


	/**
	 * La mÃ©thode trie prend en paramÃ¨tre un tableau d'entiers correspondant au degrÃ©
	 * de chaque sommet et renvoie un tableau temporaire triÃ© dÃ©croissant du tableau
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
	/**
	 * La mÃ©thode DSATAJour prend en paramÃ¨tre un tableau d'entiers correspondant
	 * au degrÃ© de chaque sommets du graphe, Cette mÃ©thode met Ã  jour ce tableau
	 * lorsqu'un sommet a Ã©tÃ© coloriÃ©.
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
	 * La mÃ©thode plusGrandDegre prend en paramÃ¨tre un tableau d'entiers DSAT
	 * correspondant au degrÃ© de chaque sommet du graphe et si ce graphe n'est pas
	 * pas entiÃ¨rement coloriÃ© renvoie la plus grande valeur prÃ©sente dans ce tableau
	 * (exceptÃ© Integer.MAX_VALUE car correspond a un sommet coloriÃ©), renvoie -1
	 * si le graphe est entiÃ¨rement coloriÃ©.
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


	public void defColor(int[] colors, int index,int[] DSAT) // definis la couleur du sommet Ã  partir de l'index
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
			System.out.println();
			Arrays.sort(color);
			int valMax = color[color.length-1];
			colors[index]=valMax+1;


			if(color.length <= 1)
			{
				System.out.print("indice : "+sommets.get(index).getNom()+"Color : ");
				for(int i=0;i<color.length;i++){
					System.out.print(color[i]+"-");
				}
				System.out.println();
				if(color[0]==0)
					colors[index] = 1;
				else if(color[0] > 1)
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
	}

	public boolean isColoried(int[]colors){
		for(int i=0;i<colors.length;i++){
			if(colors[i]==0)
				return false;
		}
		return true;
	}

	/**
	 * La methode tabVoisins prend en paramÃ¨tre un entier index et renvoie un tableau
	 * d'entiers correspondant au degrÃ© de chaque sommet voisin du sommet prÃ©sent Ã 
	 * l'index 
	 * @param index
	 * @return 
	 */
	public int[] tabVoisins(int index) // renvoie les positions des voisins du sommet courant
	{
		int[]tab=new int[this.sommets.get(index).getArcs().size()]; // crÃ©Ã© un tableau local a partir de la taille de son ArrayList d'arc qui correspond au nb de sommets voisin du sommet courant
		for(int i = 0; i < this.sommets.get(index).getArcs().size(); i++){
			int voisinArrivee=this.sommets.indexOf(this.sommets.get(index).getArcs().get(i).getArrivee()); // on rÃ©cupÃ¨re la position des sommets voisins et on les ajoute dans notre tableau local tab
			if(voisinArrivee!=index) // si la position du voisin est different de la position du sommet courant et si sa couleur est differente de 0 alors on ajoute la position du voisin dans notre tableau
				tab[i]=voisinArrivee;
			else{
				voisinArrivee=this.sommets.indexOf(this.sommets.get(index).getArcs().get(i).getOrigine());
				tab[i]=voisinArrivee;
			}
		}
		return tab;
	}

	public int[] sortSommets(int[] DSAT)
	{
		int[] DSATLocal = DSAT;
		int[] DSATTemp =new int[DSAT.length];
		ArrayList<Sommet> sTab = new ArrayList<Sommet>();
		for(int i=0;i<DSATLocal.length;i++){
			if(this.plusGrandDegre(DSATLocal)>-1){
				Sommet s = this.sommets.get(this.plusGrandDegre(DSATLocal));
				if(!sTab.contains(s)){
					sTab.add(s);
					if(DSATLocal[this.plusGrandDegre(DSATLocal)]!=Integer.MAX_VALUE){
						DSATTemp[i]=DSATLocal[this.plusGrandDegre(DSATLocal)];
					}

				}
				DSATLocal[this.plusGrandDegre(DSATLocal)]=Integer.MAX_VALUE;
			}
		}
		for(Sommet s : sommets){
			if(!sTab.contains(s))
				sTab.add(s);
		}

		this.sommets=sTab;
		System.out.print("DSATTemp : ");
		for(int i = 0; i < DSATTemp.length; i++)
			System.out.print(+ DSATTemp[i] + " ");
		System.out.println();
		return DSATTemp;
	}

	public int[] coloration()
	{
		int color[] = new int[this.getSommets().size()];
		int DSAT[] = initialisation();
		System.out.print("DSAT : ");
		for(int i = 0; i < DSAT.length; i++)
			System.out.print(+ DSAT[i] + " ");
		System.out.println();
		int degree = -1;
		DSAT = sortSommets(DSAT);

		System.out.print("sommets  : ");
		for(Sommet s : sommets){
			System.out.print(s.getNom()+"-");
		}


		while(isNotColored(DSAT))
		{
			degree = this.plusGrandDegre(DSAT);
			if(degree!=-1)
			{

				defColor(color,degree, DSAT);
				DSAT[degree]=Integer.MAX_VALUE;
				DSATAJour(DSAT);
				System.out.print("DSAT new : ");
				for(int i = 0; i < DSAT.length; i++)
					System.out.print(+ DSAT[i] + " ");
				System.out.println();
			}
		}
		return color;
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



	public int getTailleSommet() {
		return tailleSommet;
	}

	public void setTailleSommet(int tailleSommet) {
		this.tailleSommet = tailleSommet;
	}

	public ArrayList<Sommet> getTabCick() {
		return tabCick;
	}

	public void setTabCick(ArrayList<Sommet> tabCick) {
		this.tabCick = tabCick;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public String toString() {
		return "Graphe [nom=" + nom + ", type=" + type + ", sommets=" + sommets
				+ ", arcs=" + arcs + "]";
	}

	public String toString(String s)
	{
		s = "<html><p><strong>Nom du graphe : " + nom + "</strong></p><br />";
		if(this.isType() == Graphe.NON_ORIENTE)
			s+="<p>Le graphe est <span style=\"color;red\">non orienté</span></p>";
		else
			s+="<p>Le graphe est <span style=\"color;green\">orienté</span></p>";

		if(sommets.isEmpty())
			s+="<p>Le graphe ne contient aucun sommet</p>";
		else
			s+="<p>Le graphe contient " + sommets.size() + " sommets</p>";

		if(arcs.isEmpty() && this.isType() == ORIENTE)
			s+="<p>Le graphe ne contient aucun arc</p>";
		else if(arcs.isEmpty() && this.isType() == NON_ORIENTE)
			s+="<p>Le graphe ne contient aucune arrête</p>";
		else if(arcs.size() > 0 && this.isType() == ORIENTE)
			s+="<p>Le graphe contient " + arcs.size() + " arcs</p>";
		else
			s+="<p>Le graphe contient " + arcs.size() + " arrêtes</p>";
		return s;
	}


}
