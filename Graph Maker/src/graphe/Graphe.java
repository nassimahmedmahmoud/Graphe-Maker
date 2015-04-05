package graphe;


import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Graphe {
	public static final boolean ORIENTE = true;
	public static final boolean NON_ORIENTE = false;
	public static final int GENERAL = 1;
	public static final int CONNEXE_ARBRE = 2;
	public static final int MATRIX = 3;
	public static final int COLONNE_X = 0;
	public static final int COLONNE_Y = 1;
	public static final String ARC = "\\ar";
	public static final String ARETE = "\\ar@{-}";

	private String nom;
	private boolean type;
	private ArrayList<Sommet> sommets;
	private ArrayList<Arc> arcs;
	private int tailleSommet=50;
	private ArrayList<Sommet> tabCick;          //Liste de sommets pour les graphes génériques et personalisés
	private ArrayList<Sommet> arcinit;          //Pour debug Cycle et Chaine générique
	private int dist;                           //Distance par défaut des graphes génériques

	/**
	 * Constructeur champ a champ du graphe prend en paramÃƒÂ¨tres :
	 *  - Une chaine de caractÃƒÂ¨re correspondant au nom du graphe
	 *  - Un booleen indiquant si le graphe est orientÃƒÂ© ou non
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
		this.arcinit = new ArrayList<Sommet>();
	}

	/**
	 * Contructeur par dÃƒÂ©faut d'un graphe,
	 * CrÃƒÂ©er un graphe sans nom, non orientÃƒÂ© sans, avec une liste de sommets vide
	 * et une liste d'arcs vide.
	 */
	public Graphe(){
		this("Graphe_1",Graphe.NON_ORIENTE,new ArrayList<Sommet>(),new ArrayList<Arc>());
	}

	/**
	 * La méthode arcaPartirSommets prend en paramètre 2 sommet et renvoie l'arc qui
	 * relie ces 2 sommets si il existe, renvoie null sinon.
	 * @param s1
	 * @param s2
	 * @return Arc
	 */
	public Arc arcaPartirSommets(Sommet s1,Sommet s2){
		for(Arc a : arcs){
			if((a.getOrigine().equals(s2) && a.getArrivee().equals(s1)) || (a.getOrigine().equals(s1) && a.getArrivee().equals(s2)))
				return a;
		}
		return null;
	}

	/**
	 * La méthode supprimerDoublons permettant de supprimer les arcs présent en double
	 * dans le graphe, utiliser pour supprimer les arcs ayant pour origine et arrivée
	 * un même sommet, mais également pour supprimer le double d'arc relié a 2 sommets
	 * dans un graphe non orienté.
	 */
	public void supprimerDoublons()
	{
		arcs = new ArrayList<Arc>();
		for(Sommet s : sommets)
		{
			for(Arc a : s.getArcs())
				if(!arcInGraphe(a) && !(a.getOrigine().equals(a.getArrivee())))
					arcs.add(a);
		}
	}


	public int[] etiquetteX(int marge){
		int[] etiquette = new int[this.arcinit.size()];
		int num=1;
		for(int i=0;i<this.arcinit.size();i++,num++){
			if(etiquette[i]==0){
				etiquette[i]=num;
				for(int j=i+1;j<this.arcinit.size();j++){
					if(etiquette[j]==0){
						if(Math.abs(this.arcinit.get(i).getPosX()-this.arcinit.get(j).getPosX())<marge){
							etiquette[j]=num;
						}
					}
				}
			}
		}
		return etiquette;
	}

	public int[] etiquetteY(int marge){
		int[] etiquette = new int[this.arcinit.size()];
		int num=1;
		for(int i=0;i<this.arcinit.size();i++,num++){
			if(etiquette[i]==0){
				etiquette[i]=num;
				for(int j=i+1;j<this.arcinit.size();j++){
					if(etiquette[j]==0){
						if(Math.abs(this.arcinit.get(i).getPosY()-this.arcinit.get(j).getPosY())<marge){
							etiquette[j]=num;
						}
					}
				}
			}
		}
		return etiquette;
	}

	public int nbColonneEtiquette(int marge){
		int val =0;
		int[]etX = this.etiquetteX(marge);
		Arrays.sort(etX);
		for(int i=1;i<etX.length;i++){
			if(etX[i-1]!=etX[i])
				val++;
		}
		return val+1;
	}

	public int nbLigneEtiquette(int marge){
		int val =0;
		int[]etY = this.etiquetteY(marge);
		Arrays.sort(etY);
		for(int i=1;i<etY.length;i++){
			if(etY[i-1]!=etY[i])
				val++;
		}
		return val+1;
	}

	public Sommet[] ligneY(int marge, Sommet s, ArrayList<Sommet> tab)
	{
		ArrayList<Sommet> stab = new ArrayList<>();
		ArrayList<Sommet> tmp = new ArrayList<>();
		System.out.println("nbLigne : "+(this.nbLigneEtiquette(marge)));
		System.out.println("nbColonne : "+(this.nbColonneEtiquette(marge)));
		for(int i = 0; i < tab.size(); i++)
			if(Math.abs(s.getPosY() - tab.get(i).getPosY()) <= marge)
				tmp.add(tab.get(i));

		for (int i = 0; i < tmp.size(); i++)
		{
			tab.remove(tmp.get(i));
			stab.add(tmp.get(i));
		}
		return stab.toArray(new Sommet[stab.size()]);
	}

	public Sommet[][] ligne(int marge)
	{
		Sommet[][] tabmat = new Sommet[this.nbLigneEtiquette(marge)][this.nbColonneEtiquette(marge)];
		ArrayList<Sommet> tab = new ArrayList<>(arcinit);
		int len = tab.size();
		int i=0;
		while(len>0 && i<tabmat.length)
		{
			tabmat[i] = ligneY(marge, tab.get(0), tab);
			len--;
			i++;
		}
		return tabmat;
	}

	public Sommet[] colonneX(int marge, Sommet s, ArrayList<Sommet> tab)
	{
		ArrayList<Sommet> stab = new ArrayList<>();
		ArrayList<Sommet> tmp = new ArrayList<>();
		for(int i = 0; i < tab.size(); i++)
			if(Math.abs(s.getPosX() - tab.get(i).getPosX()) <= marge)
				tmp.add(tab.get(i));

		for (int i = 0; i < tmp.size(); i++)
		{
			tab.remove(tmp.get(i));
			stab.add(tmp.get(i));
		}
		return stab.toArray(new Sommet[stab.size()]);
	}

	public Sommet[][] colonne(int marge)
	{
		Sommet[][] tabmat = new Sommet[this.nbColonneEtiquette(marge)][this.nbLigneEtiquette(marge)];
		ArrayList<Sommet> tab = new ArrayList<>(arcinit);
		int len = tab.size();
		int i=0;

		while(len>0 && i<tabmat.length)
		{
			tabmat[i] = colonneX(marge, tab.get(0), tab);
			len--;
			i++;
		}

		return tabmat;
	}

	public void sortLigne(Sommet[][] tab, int marge)
	{
		Sommet[] li = new Sommet[this.nbColonneEtiquette(marge)];
		for(int i = 0; i < tab.length; i++)
		{
			for(int j = 0; j < tab.length; j++)
			{
				if(tab[i][0].getPosY() < tab[j][0].getPosY())
				{
					li = tab[i];
					tab[i] = tab[j];
					tab[j] = li;
				}
			}       
		}
	}

	public void sortColonne(Sommet[][] tab, int marge)
	{
		Sommet[] co = new Sommet[this.nbLigneEtiquette(marge)];
		for(int i = 0; i < tab.length; i++)
		{
			for(int j = 0; j < tab.length; j++)
			{
				if(tab[i][0].getPosX() < tab[j][0].getPosX())
				{
					co = tab[i];
					tab[i] = tab[j];
					tab[j] = co;
				}
			}       
		}
	}

	public Sommet_matrix[] initTab(int marge)
	{
		Sommet_matrix[] tab = new Sommet_matrix[arcinit.size()];
		Sommet[][] lignes = ligne(marge);
		Sommet[][] colonnes = colonne(marge);
		sortLigne(lignes, marge);
		sortColonne(colonnes, marge);

		for(int i = 0; i < tab.length; i++)
		{
			tab[i] = new Sommet_matrix();
			tab[i].setSommet(arcinit.get(i));
			for(int j = 0; j < lignes.length; j++)
			{
				for(int k = 0; k < lignes[j].length; k++){
					if(tab[i].getSommet().equals(lignes[j][k]))
						tab[i].setLigne(j);
				}
			}
			for(int j = 0; j < colonnes.length; j++)
			{
				for(int k = 0; k < colonnes[j].length; k++)
					if(tab[i].getSommet().equals(colonnes[j][k]))
						tab[i].setColonne(j);
			}
		}                                
		return tab;
	}

	public Sommet_matrix sommetInSommetMatrix(Sommet s,Sommet_matrix[]tab){
		return tab[arcinit.indexOf(s)];
	}

	public String sommetVoisinsLaTeX(Sommet s,Sommet_matrix[]tab){
		Sommet_matrix sm = sommetInSommetMatrix(s,tab);
		int[] tabVoisins = this.tabVoisins(arcinit.indexOf(s));
		String chaine = "";
		if(type)
			chaine+=Graphe.ARC;
		else
			chaine+=Graphe.ARETE;
		for(int i=0;i<tabVoisins.length;i++){
			chaine+="[";
			Sommet_matrix smtmp = this.sommetInSommetMatrix(sommets.get(tabVoisins[i]), tab);
			int sml= sm.getLigne();
			int smtmpl = smtmp.getLigne();
			int smc = sm.getColonne();
			int smtmpc = smtmp.getColonne();
			if(sml>smtmpl){
				while(sml>0){
					chaine+="u";
					sml--;
				}
			}
			else if(sml<smtmpl){
				while(smtmpl>0){
					chaine+="d";
					smtmpl--;
				}
			}
			if(smc>smtmpc){
				while(smc>0){
					chaine+="l";
					smc--;
				}
			}
			else if(smc<smtmpc){
				while(smtmpc>0){
					chaine+="r";
					smtmpc--;
				}
			}
			chaine+="]";
		}
		return chaine;
	}

	public String[][] matrixLatex(Sommet_matrix[] tab, int marge)
	{
		String[][] matrice = new String[this.nbLigneEtiquette(marge)+2][this.nbColonneEtiquette(marge)];
		matrice[0][0] = "\\xymatrix@R=" + (marge/50) + "cm@C=" + (marge/50) + "cm\n"
				+ "{\n";
				for(int i = 1; i < matrice.length -1; i++)
				{
					for(int j = 0; j < matrice[i].length; j++)
					{
						if(j != matrice[i].length-1)
							matrice[i][j] = " &";
						else
							matrice[i][j] =" \\"+"\\";
					}
				}

				for(Sommet_matrix sm : tab)
					matrice[sm.getLigne()+1][sm.getColonne()] = sm.getSommet().getNom() + matrice[sm.getLigne()+1][sm.getColonne()];

				matrice[this.nbLigneEtiquette(marge)+1][0] = "\n}";
				return matrice;
	}

	/**
	 * MÃƒÂ©thode qui permet d'orienter un graphe non orientÃƒÂ©, ou si celui-ci est orientÃƒÂ© de
	 * le de-orienter.
	 */
	 public void switchTypeOfGraphe(){
		if(this.type)
		{
			this.setType(Graphe.NON_ORIENTE);
			if(this.arcs.size() > 0)
				this.supprimerDoublons();
			System.out.println(arcs);
		}
		else
			this.setType(Graphe.ORIENTE);
	}

	/**
	 * La mÃƒÂ©thode isSommet prend en paramÃƒÂ¨tre un entier correspondant au diamÃƒÂ¨tre
	 * d'un sommet, et d'un objet de la classe MouseEvent permettant de connaÃƒÂ®tre
	 * les coordonnÃƒÂ©es oÃƒÂ¹ l'utilisateur a cliquÃƒÂ© dans la fenÃƒÂªtre de graphe,
	 * Cette mÃƒÂ©thode retourne un objet de type Sommet qui correspond au sommet
	 * corresponant au sommet prÃƒÂ©sent a l'endroit ou l'utilisateur a cliquÃƒÂ©, ou null
	 * si l'utilisateur n'a pas cliquÃƒÂ© sur un sommet.
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
	  * La mÃƒÂ©thode isArc prend en paramÃƒÂ¨tre un diamÃƒÂ¨tre dÃƒÂ©finit arbitrairement et un
	  * objet de la classe MouseEvent permettant de connaÃƒÂ®tre les coordonnÃƒÂ©es oÃƒÂ¹
	  * l'utilisateur a cliquÃƒÂ©,
	  * Cette mÃƒÂ©thode retourne l'arc correspondant a l'arc auquel l'utilisateur a
	  * cliquÃƒÂ© en son milieu, et null si l'utilisateur n'a pas cliquÃƒÂ© sur le milieu
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
	  * La mÃƒÂ©thode positionSommets prend en paramÃƒÂ¨tre un Sommet et retourne 
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
	  * La méthode createChaine prend en paramètre une distance et une liste de sommets,
	  * et instancie des arcs reliés 2 à 2 aux sommets de la liste tab qui ont pour valeur
	  * dist, tel que sommet 1 relié a sommet 2, sommet 2 relié a sommet 3 etc.
	  * @param dist
	  * @param tab 
	  */
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

	 /**
	  * La méthode createCycle prend en paramètre une distance et une liste de sommets,
	  * fonction comme la méthode <b>createChaine(int, ArrayList)</b> a ceci pres que
	  * la méthode createCycle instancie en plus un arc entre le dernier sommet etle premier
	  * sommet de la liste de sommets passée en paramètre.
	  * @see createChaine(int, ArrayList)
	  * @param dist
	  * @param tab 
	  */
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

	 /**
	  * La méthode createClique prend en paramètre un entier i correspondanta la distance
	  * de l'arc, et créer des arcs qui ont pour nom i reliés a tous les sommets pour chaque
	  * sommet.
	  * @param i 
	  */
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

	 /**
	  * La methode createClique prend en paramètre un entier i correspondant a une
	  * distance et une liste de sommets, cette methode fonctionne comme la methode
	  * createClique(int) à ceci-pres qu'on relie par des arcs les sommets de la liste
	  * en paramètre voir <b>createClique(int)</b>.
	  * @ see createClique(int)
	  * @param i
	  * @param tab 
	  */
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
	  * La mÃƒÂ©thode metrique revoie un boolÃƒÂ©en indiquant si le graphe est mÃƒÂ©trique
	  * ou non.
	  * @return boolean
	  */
	 public boolean metrique(){
		 for(Sommet s : this.arcinit){
			 for(Arc a : s.getArcs()){
				 boolean ui =isNumeric(a.getNom());
				 if(!ui)
					 return ui;
			 }
		 }
		 return true;
	 }

	 /**
	  * 
	  * @param matrice
	  * @return 
	  */
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

	 /**
	  * La methode connexeGraphe renvoie true si le graphe est connexe et false sinon.
	  * @return boolean
	  */
	 public boolean connexeGraphe(){
		 if(sommets.isEmpty() && !type)
			 return true;
		 if(sommets.isEmpty() && type)
			 return false;
		 int sommet = 0;
		 boolean marquage[]=new boolean[sommets.size()];
		 dfs(marquage,sommet);
		 return isMarqued(marquage);
	 }

	 public boolean isMarqued(boolean[]marquage){
		 for(int i=0;i<marquage.length;i++){
			 if(!marquage[i])
				 return false;
		 }
		 return true;
	 }

	 public void dfs(boolean[]marquage,int index){
		 marquage[index]=true;
		 int[] tabVoisins = this.tabVoisins(index);
		 for(int i=0;i<tabVoisins.length;i++){
			 if(!marquage[tabVoisins[i]]){
				 dfs(marquage,tabVoisins[i]);
			 }
		 }
	 }
	 /**
	  * La mÃ©thode voisins prend en paramÃƒÂ¨tre deux sommets s1 et s2 et renvoie true
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
	  * La méthode isNumeric prend en paramètre un String et renvoie false si ce n'est
	  * pas un chiffre, true sinon, cette méthode est utilisée pour savoir si le nom
	  * d'un arc est un nombre ou pas.
	  * @param str
	  * @return boolean
	  */
	 public boolean isNumeric(String str)  
	 {  
		 double d;
		 try  
		 {  
			 d = Double.parseDouble(str);  
		 }  
		 catch(NumberFormatException nfe)  
		 {  
			 return false;  
		 }  

		 return d!=Double.NaN;  
	 }

	 public int isNumeric2(String str)  
	 {  
		 int d;
		 try  
		 {  

			 d = Integer.parseInt(str);  
		 }  
		 catch(NumberFormatException nfe)  
		 {  
			 return 0;  
		 }  
		 return d;  
	 }

	 /**
	  * La méthode matriceNonOriente renvoie une matrice avec pour valeur le nom des
	  * arcs casté en int si cette matrice est metrique ou 1 sinon, si le graphe est
	  * non oriente.
	  * @see matriceOriente()
	  * @return int[][]
	  */
	 public int [][] matriceNonOriente(){
		 int tab[][]=new int[this.sommets.size()][this.sommets.size()];
		 int i=0;
		 boolean metrique = metrique();
		 for(Sommet s : this.sommets){
			 for(Arc a : s.getArcs()){
				 int pos=0;
				 if(a.getOrigine().equals(s))
					 pos = positionSommets(a.getArrivee());
				 else
					 pos = positionSommets(a.getOrigine());
				 if(pos!=-1){
					 if(metrique  )
						 tab[i][pos]=Integer.parseInt(a.getNom());
					 else
						 tab[i][pos]=1;
				 }
			 }
			 i++;
		 }
		 return tab;
	 }

	 /**
	  * La méthode matriceOriente renvoie une matrice avec pour valeur le nom des
	  * arcs casté en int si cette matrice est metrique ou 1 sinon,comme la méthode
	  * <b>matriceNonOriente()</b> a ceci-pres que cette methode le fait pour un graphe
	  * orienté.
	  * @see matriceNonOriente()
	  * @return int[][]
	  */
	 public int[][] matriceOriente(){
		 int tab[][]=new int[this.sommets.size()][this.sommets.size()];
		 int i=0;
		 boolean metrique = metrique();
		 for(Sommet s : this.sommets){
			 //System.out.println(s.getArcs());
			 for(Arc a : s.getArcs()){
				 int pos=0;
				 if(a.getOrigine().equals(s)){
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

	 /**
	  * La méthode matrice renvoie le resultat <b>matriceNonOriente()</b> ou celui de
	  * <b>matriceOriente()</b> en fonction du graphe, si il est orienté ou non.
	  * @see matriceNonOriente()
	  * @see matriceOriente()
	  * @return int[][]
	  */
	 public int[][]matrice(){
		 if(this.type==Graphe.ORIENTE){
			 return matriceOriente();
		 }
		 else{
			 return matriceNonOriente();
		 }
	 }

	 /**
	  * La méthode sommetInGraphe prend en paramètre une liste de sommets et un sommet
	  * et renvoie true si ce sommet est dans la liste passé en paramètre, false sinon.
	  * @param tab
	  * @param somm_s
	  * @return boolean
	  */
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

	 /**
	  * La méthode arcInGraohe prend en paramètre un arc et revoie true si cet arc est
	  * présent dans le graphe, false sinon.
	  * @param arc_g
	  * @return boolean
	  */
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

	 /**
	  * La méthode ajouterSommet prend en paramètre un sommet s et l'ajoute dans la
	  * liste des sommets du graphe.
	  * @param s 
	  */
	 public void ajouterSommet(Sommet s){
		 if(!sommets.contains(s)){
			 s.setNom(""+(sommets.size()+1));
			 sommets.add(s);
			 arcinit.add(s);
		 }
	 }

	 /**
	  * La méthode stringMatrice renvoie une chaine de caractère correspondant aux
	  * valeurs présentes dans la matrice crée par la methode <b>matrice()</b>, cette
	  * méthode est rendue obselète depuis la création de la méthode <b>toString(int)</b>.
	  * @see matrice()
	  * @see toString(int)
	  * @return String
	  */
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

	 /**
	  * La méthode connexeArbre renvoie une chaine de caractère correspondant encapsulant
	  * le résultat des méthodes <b>connexeGraphe()</b> et <b>isTree()</b> sous forme
	  * de Chaine de caractère, cette méthode est rendue obselète depuis la création
	  * de la méthode <b>toString(int)</b>.
	  * @see connexeGraphe()
	  * @see isTree()
	  * @see toString(int)
	  * @return String
	  */
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

	 /**
	  * La méthode isTree renvoie un booleen qui vaut true si le graphe est un arbre
	  * et false sinon, un graphe est un arbre si sont nombre d'arcs vaut le nombre de
	  * sommets-1 présents dans le graphe.
	  * @return boolean
	  */
	 public boolean isTree(){
		 if(this.sommets.size()==1)
			 return false;

		 if(this.sommets.size()-1==this.arcs.size() && this.type==NON_ORIENTE)
			 return true;
		 return false;
	 }

	 /**
	  * La mÃƒÂ©thode voisinsColorie prend en paramÃƒÂ¨tre un sommet s1 et un tableau d'entiers
	  * correspondant au nombre de degrÃƒÂ©s (arcs sortants/entrants) de chaque sommet du
	  * graphe, Cette mÃƒÂ©thode retourne un entier correspondant au nombre de sommets
	  * voisins du sommet s1 qui sont coloriÃƒÂ©s.
	  * @param s1
	  * @param dsat
	  * @return int
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
	  * La méthode chromatique renvoie un entier correspondant au nombre de couleurs
	  * différentes présent dans la table des couleurs.
	  * @return int
	  */
	 public int chromatique(){
		 int [] colors = coloration();
		 int val;
		 if(colors!=null && colors.length>1){
			 val = colors[0]+1;
			 for (int i = 1; i < colors.length; i++)
				 if (val<colors[i])
					 val = colors[i];
		 }
		 else if(colors.length == 1)
			 val = colors.length;
		 else
			 val = 0;
		 return val;
	 }

	 /**
	  * La mÃƒÂ©thode initialisation crÃƒÂ©er un tableau d'entier d'une taille correspondant
	  * au nombre de sommet prÃƒÂ©sents dans le graphe, et pour chaque sommet correspondant
	  * effecte a la case correspondant a son indice le degrÃƒÂ© ou nombre d'arcs associÃƒÂ©
	  * a ce sommet, et retourne ce tableau.
	  * @return int[]
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
	  * La mÃƒÂ©thode isNotColored prend en paramÃƒÂ¨tre un tableau d'entiers correspondant au
	  * degrÃƒÂ© de chaque sommet du graphe, Cette fonction parcours tout ce tableau et
	  * si un sommet est coloriÃƒÂ© (la case correspondant a ce sommet est ÃƒÂ  
	  * Integer.MAX_VALUE) renverra false, et true sinon (alors tous les sommets du
	  * graphe seront coloriÃƒÂ©s).
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
	  * La mÃƒÂ©thode trie prend en paramÃƒÂ¨tre un tableau d'entiers correspondant au degrÃƒÂ©
	  * de chaque sommet et renvoie un tableau temporaire triÃƒÂ© dÃƒÂ©croissant du tableau
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
	  * La mÃƒÂ©thode DSATAJour prend en paramÃƒÂ¨tre un tableau d'entiers correspondant
	  * au degrÃƒÂ© de chaque sommets du graphe, Cette mÃƒÂ©thode met ÃƒÂ  jour ce tableau
	  * lorsqu'un sommet a ÃƒÂ©tÃƒÂ© coloriÃƒÂ©.
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
	  * La mÃƒÂ©thode plusGrandDegre prend en paramÃƒÂ¨tre un tableau d'entiers DSAT
	  * correspondant au degrÃƒÂ© de chaque sommet du graphe et si ce graphe n'est pas
	  * pas entiÃƒÂ¨rement coloriÃƒÂ© renvoie la plus grande valeur prÃƒÂ©sente dans ce tableau
	  * (exceptÃƒÂ© Integer.MAX_VALUE car correspond a un sommet coloriÃƒÂ©), renvoie -1
	  * si le graphe est entiÃƒÂ¨rement coloriÃƒÂ©.
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


	 public void defColor(int[] colors, int index,int[] DSAT) // definis la couleur du sommet ÃƒÂ  partir de l'index
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
	  * La methode tabVoisins prend en paramÃƒÂ¨tre un entier index et renvoie un tableau
	  * d'entiers correspondant au degrÃƒÂ© de chaque sommet voisin du sommet prÃƒÂ©sent ÃƒÂ 
	  * l'index 
	  * @param index
	  * @return 
	  */
	 public int[] tabVoisins(int index) // renvoie les positions des voisins du sommet courant
	 {
		 int[]tab=new int[this.sommets.get(index).getArcs().size()]; // crÃƒÂ©ÃƒÂ© un tableau local a partir de la taille de son ArrayList d'arc qui correspond au nb de sommets voisin du sommet courant
		 for(int i = 0; i < this.sommets.get(index).getArcs().size(); i++){
			 int voisinArrivee=this.sommets.indexOf(this.sommets.get(index).getArcs().get(i).getArrivee()); // on rÃƒÂ©cupÃƒÂ¨re la position des sommets voisins et on les ajoute dans notre tableau local tab
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
		 for(Sommet s : this.arcinit){
			 if(s.getPosX()==posX && s.getPosY()==posY)
				 return s;
		 }
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

	 public ArrayList<Sommet> getArcinit() {
		 return arcinit;
	 }

	 public void setArcinit(ArrayList<Sommet> arcinit) {
		 this.arcinit = arcinit;
	 }

	 public String toString() {
		 String s="";
		 int type=0;
		 if(this.isType())
			 type=0;
		 else
			 type=1;
		 s+=this.getNom()+","+type+","+0+","+0+","+this.getSommets().size()+","+this.getArcs().size()+"\n";
		 for(Sommet s0 : this.arcinit)
			 s+=s0.getNom()+","+s0.getPosX()+","+s0.getPosY()+","+0+"\n";
		 for(Arc a0 : this.getArcs()){
			 s+=a0.getOrigine().getPosX()+","+a0.getOrigine().getPosY()+","+a0.getCentre_posX()+","+a0.getCentre_posY()+",";
			 s+=a0.getArrivee().getPosX()+","+a0.getArrivee().getPosY()+","+a0.getCentre_posX()+","+a0.getCentre_posY()+","+a0.getNom()+","+0+"\n";
		 }
		 return s;
	 }

	 /**
	  * La méthode toString avec parèmtre est une surcharge de la méthode toString,
	  * et prend en paramètre un entier qui correspond a l'information que l'on veut
	  * afficher dans l'onglet général (1 pour les informations générales, 2 pour 
	  * les informations sur la connexité, et 3 pour la matrice).
	  * @param info
	  * @see connexeGraphe()
	  * @return String informations
	  */
	 public String toString(int info)
	 {
		 String s = "";
		 if(info == GENERAL)
		 {
			 s = "<html><p><strong>Nom du graphe : " + nom + "</strong></p><br />";
			 if(this.isType() == Graphe.NON_ORIENTE)
				 s+="<p>Le graphe est <span style=\"color;red\">non orientÃ©</span></p>";
			 else
				 s+="<p>Le graphe est <span style=\"color;green\">orientÃ©</span></p>";

			 if(sommets.isEmpty())
				 s+="<p>Le graphe ne contient aucun sommet</p>";
			 else
				 s+="<p>Le graphe contient " + sommets.size() + " sommets</p>";

			 if(arcs.isEmpty() && this.isType() == ORIENTE)
				 s+="<p>Le graphe ne contient aucun arc</p>";
			 else if(arcs.isEmpty() && this.isType() == NON_ORIENTE)
				 s+="<p>Le graphe ne contient aucune arrÃªte</p>";
			 else if(arcs.size() > 0 && this.isType() == ORIENTE)
				 s+="<p>Le graphe contient " + arcs.size() + " arcs</p>";
			 else
				 s+="<p>Le graphe contient " + arcs.size() + " arrÃªtes</p>";
		 }
		 else if(info == CONNEXE_ARBRE)
		 {
			 s ="<html>";
			 if(connexeGraphe())
				 s+="Le graphe est connexe<br/>";
			 else
				 s+="Le graphe n'est pas connexe<br/>";
			 if(isTree())
				 s+="Le graphe est un arbre<br/>";
			 else
				 s+="Le graphe n'est pas un arbre<br/>";
			 s+="</html>";
		 }
		 else if(info == MATRIX)
		 {
			 s="<html>";
			 int[][]tab =matrice();
			 for(int i=0;i< tab.length;i++){
				 for(int j=0;j< tab.length;j++){
					 s+=tab[i][j]+"\t";
				 }
				 s+="<br/>";
			 }
			 s+="</html>";
		 }
		 return s;
	 }

	 public Graphe read(File fileName){
		 BufferedReader br = null;
		 Graphe g =new Graphe();
		 int nbSommet=0;
		 @SuppressWarnings("unused")
		 int nbArcs;
		 try {
			 br = new BufferedReader(new FileReader(fileName));
		 } catch (FileNotFoundException e) { }

		 try {
			 String line = "";
			 int line_i = 0;
			 try {
				 while ((line = br.readLine()) != null) {
					 String[] texte = line.split(",");
					 if(line_i == 0){
						 g.setNom(texte[0]);
						 if(texte[1]=="1")
							 g.setType(NON_ORIENTE);
						 else
							 g.setType(ORIENTE);
						 nbSommet=Integer.parseInt(texte[4]);
						 nbArcs=Integer.parseInt(texte[5]);
					 }
					 else if(line_i<=nbSommet){
						 int x;
						 int y;
						 Sommet s =new Sommet();
						 s.setNom(texte[0]);
						 x=(int) Double.parseDouble(texte[1]);
						 y=(int) Double.parseDouble(texte[2]);	
						 s.setPosX(x);
						 s.setPosY(y);
						 g.ajouterSommet(s);
					 }
					 else{
						 Arc a =new Arc();
						 int depart_X=(int) Double.parseDouble(texte[0]);
						 int depart_Y=(int) Double.parseDouble(texte[1]);
						 int arriver_X=(int) Double.parseDouble(texte[4]);
						 int arriver_Y=(int) Double.parseDouble(texte[5]);
						 a.setNom("");
						 a.setOrigine(g.getSommet(depart_X, depart_Y));
						 a.setArrivee(g.getSommet(arriver_X, arriver_Y));
						 a.milieu();
						 g.getArcs().add(a);
						 g.getSommet(depart_X, depart_Y).ajouterArc(a);
						 g.getSommet(depart_X, depart_Y).ajouterArc(a);

					 }
					 line_i++;
				 }
			 } catch (IOException e) { }
		 } finally {
			 try {
				 br.close();
			 } catch (IOException e) { }
		 }
		 g.supprimerDoublons();
		 return g;
	 }

	 public void save(String nom,Graphe g){
		 FileWriter fileWriter = null;
		 try {
			 File newTextFile = new File(nom+".ntm");
			 fileWriter = new FileWriter(newTextFile);
			 fileWriter.write(g.toString());
			 fileWriter.close();
		 } catch (IOException ex) {
		 } finally {
			 try {
				 fileWriter.close();
			 } catch (IOException ex) {}
		 }
	 }

}