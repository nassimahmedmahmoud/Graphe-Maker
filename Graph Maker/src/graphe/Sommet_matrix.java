package graphe;


/**
 * &nbsp&nbsp&nbsp&nbspLa classe Sommet_matrix représente "l'étiquette" d'un sommet. Cette étiquette
 * représente un sommet dans la matrice de LaTeX, l'attribut Sommet permet d'avoir accès
 * a toutes les informations relatives a ce sommet, l'entier ligne correspond à la ligne
 * ou se trouve ce sommet dans la matrice LaTeX, et l'entier colonne correspond à la colonne
 * ou se trouve ce sommet dans la matrice LaTeX.
 * 
 * @see graphe.Sommet
 * @see graphe.Graphe
 * 
 * @version 1.0
 */
public class Sommet_matrix
{
    private Sommet somm;
    private int ligne;
    private int colonne;
    
    public Sommet_matrix(Sommet s, int l, int c)
    {
        somm = s;
        ligne = l;
        colonne = c;
    }
    
    public Sommet_matrix()
    {
        this(new Sommet(), 0, 0);
    }

    public int getLigne()
    {
        return ligne;
    }

    public int getColonne()
    {
        return colonne;
    }

    public Sommet  getSommet()
    {
        return somm;
    }

    public void setLigne(int ligne)
    {
        this.ligne = ligne;
    }

    public void setColonne(int colonne)
    {
        this.colonne = colonne;
    }

    public void setSommet(Sommet som)
    {
        this.somm = som;
    }
}