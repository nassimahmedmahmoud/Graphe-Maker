package graphe;

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