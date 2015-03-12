package graphe;

public class Arc {

    private String nom;
    private Sommet origine;
    private Sommet arrivee;
    private int centre_posX;
    private int centre_posY;

    public Arc(String nm, Sommet s_1, Sommet s_2, int centre_posX,
            int centre_posY) {
        nom = nm;
        origine = s_1;
        this.arrivee = s_2;
        this.centre_posX = centre_posX;
        this.centre_posY = centre_posY;
    }

    public Arc() {
        this("0", new Sommet(), new Sommet(), 0, 0);
    }

    public String getNom() {
        return nom;
    }

    public Sommet getOrigine() {
        return origine;
    }

    public Sommet getArrivee() {
        return arrivee;
    }

    public int getCentre_posX() {
        return centre_posX;
    }

    public int getCentre_posY() {
        return centre_posY;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setOrigine(Sommet s_1) {
        origine = s_1;
    }

    public void setArrivee(Sommet s_2) {
        arrivee = s_2;
    }

    public void setCentre_posX(int centre_posX) {
        this.centre_posX = centre_posX;
    }

    public void setCentre_posY(int centre_posY) {
        this.centre_posY = centre_posY;
    }

    public String toString() {
        return "Arc [nom=" + nom + ", origine=" + origine + ", arrivee="
                + arrivee + ", centre_posX=" + centre_posX + ", centre_posY="
                + centre_posY + "]";
    }

    public int distancePointX() {
            return this.arrivee.getPosX() + this.origine.getPosX();
    }

    public int petitX() {
        if (this.origine.getPosX() > this.arrivee.getPosX())
            return this.arrivee.getPosX();
        else
            return this.origine.getPosX();
    }

    public int grandX() {
        if (this.origine.getPosX() < this.arrivee.getPosX())
            return this.arrivee.getPosX();
        else
            return this.origine.getPosX();
    }

    public int petitY() {
        if (this.origine.getPosY() > this.arrivee.getPosY())
            return this.arrivee.getPosY();
        else
            return this.origine.getPosY();
    }

    public int grandY() {
        if (this.origine.getPosY() < this.arrivee.getPosY())
            return this.arrivee.getPosY();
        else
            return this.origine.getPosY();
    }

    public int distancePointY() {
            return this.arrivee.getPosY() + this.origine.getPosY();
    }
    
    public void milieu()
    {
        int X = this.distancePointX()/2;
        int Y = this.distancePointY()/2;
        
        if(this.origine.getPosX() > this.arrivee.getPosX())
            this.setCentre_posX(origine.getPosX()-X);
        else
            this.setCentre_posX(arrivee.getPosX()-X);
        
        if(this.origine.getPosY() > this.arrivee.getPosY())
            this.setCentre_posY(origine.getPosY()-Y);
        else
            this.setCentre_posY(arrivee.getPosY()-Y);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
            return false;
        
        if (!(o instanceof Arc))
            return false;

        Arc a = (Arc) o;
        return a.nom == this.nom && a.arrivee.equals(this.arrivee)
                && a.origine.equals(this.origine) && a.centre_posX == this.centre_posX
                && a.centre_posY == this.centre_posY;
    }
}
