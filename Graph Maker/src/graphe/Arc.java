package graphe;

public class Arc {
	private int distance;
	private Sommet s_1;
	private Sommet s_2;
	private int centre_posX;
	private int centre_posY;
	
	public Arc(int distance, Sommet s_1, Sommet s_2, int centre_posX,
			int centre_posY) {
		this.distance = distance;
		this.s_1 = s_1;
		this.s_2 = s_2;
		this.centre_posX = centre_posX;
		this.centre_posY = centre_posY;
	}
	
	public Arc(){
		this(0,new Sommet(),new Sommet(),0,0);
	}
	
	public int getDistance() {
		return distance;
	}
	public Sommet getS_1() {
		return s_1;
	}
	public Sommet getS_2() {
		return s_2;
	}
	public int getCentre_posX() {
		return centre_posX;
	}
	public int getCentre_posY() {
		return centre_posY;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public void setS_1(Sommet s_1) {
		this.s_1 = s_1;
	}
	public void setS_2(Sommet s_2) {
		this.s_2 = s_2;
	}
	public void setCentre_posX(int centre_posX) {
		this.centre_posX = centre_posX;
	}
	public void setCentre_posY(int centre_posY) {
		this.centre_posY = centre_posY;
	}

	public String toString() {
		return "Arc [distance=" + distance + ", s_1=" + s_1 + ", s_2=" + s_2
				+ ", centre_posX=" + centre_posX + ", centre_posY="
				+ centre_posY + "]";
	}

	public int distancePointX(){
		if(this.s_1.getPosX()> this.s_2.getPosX())
			return this.s_1.getPosX()-this.s_2.getPosX();
		else
			return this.s_2.getPosX()-this.s_1.getPosX();
	}
	public int petitX(){
		if(this.s_1.getPosX()> this.s_2.getPosX())
			return this.s_2.getPosX();
		else
			return this.s_1.getPosX();
	}
	public int grandX(){
		if(this.s_1.getPosX()< this.s_2.getPosX())
			return this.s_2.getPosX();
		else
			return this.s_1.getPosX();
	}
	
	public int petitY(){
		if(this.s_1.getPosY()> this.s_2.getPosY())
			return this.s_2.getPosY();
		else
			return this.s_1.getPosY();
	}
	public int grandY(){
		if(this.s_1.getPosY()< this.s_2.getPosY())
			return this.s_2.getPosY();
		else
			return this.s_1.getPosY();
	}
	public int distancePointY(){
		if(this.s_1.getPosY()> this.s_2.getPosY())
			return this.s_1.getPosY()-this.s_2.getPosY();
		else
			return this.s_2.getPosY()-this.s_1.getPosY();
	}
}
