package dialogue;
 
import java.awt.*;
import javax.swing.*;
import controller.*;
import graphe.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
 
 
 
public class GrapheView extends JFrame{
    private JTabbedPane onglets;
    private Graphe graphe;
    private JToggleButton bclic;                 // Bouton clic
    private JToggleButton bsommet;               // Bouton sommet
    private JToggleButton barc;                  // Bouton arc
    private JToggleButton barrete;               // Bouton arrete
    private JButton barcarrete;                  // Bouton pour orienter/desorienter le graphe
    private JToggleButton bgomme;                // Bouton gomme
    private JButton brelaz;
    private JPanelGraphe jpg;
    private JPanelBrelaz jpb;
    private JTextField jtfNode;
    private JButton jlColors;
    private JButton clik;
    private int[] colors;
 
    public GrapheView(String titre,int w,int h){
        super(titre);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initMenu();
        this.initialise();
        this.setSize(w,h);
        this.setVisible(true);
    }
 
    public void initialise(){
        Container c = this.getContentPane();
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JPanel jp3 = new JPanel();
        JLabel jl = new JLabel("Saluuuuut");
        onglets = new JTabbedPane();
        this.graphe=new Graphe();
        jp1.add(jl);
 
        onglets.addTab("Graphe", null,this.panel1());
        onglets.addTab("Djikstra", null, jp1);
        onglets.addTab("Coloration", null, panel2());
        onglets.addTab("GÃ©nÃ©ral", null, this.panel4());
        c.add(onglets);
    }
 
    public void initMenu(){
        JMenuBar jmb=new JMenuBar();
        this.setJMenuBar(jmb);
        jmb.add(menuFile());
        jmb.add(menuEdition());
        jmb.add(menuAide());
    }
    public JMenu menuFile(){
        JMenu menufichier=new JMenu("Fichier");
        JMenuItem menugraphe=new JMenuItem("RÃ©initialiser un graphe");
        JMenuItem menusave = new JMenuItem("sauvegarder graphe");
        JMenuItem menuload = new JMenuItem("charger graphe");
        menufichier.add(menugraphe);
        menufichier.add(menusave);
        menufichier.add(menuload);
        return menufichier;
    }
 
    public JMenu menuEdition(){
        JMenu menuEdition = new JMenu("Editer");
        JMenuItem menuundo = new JMenuItem("Undo");
        JMenuItem menuredo = new JMenuItem("Redo");
        menuEdition.add(menuundo);
        menuEdition.add(menuredo);
        return menuEdition;
    }
 
    public JMenu menuAide(){
        JMenu menuAide = new JMenu("Aide");
        JMenuItem menuhelp = new JMenuItem("Assistance");
        JMenuItem menupropos = new JMenuItem("A propos de nous");
        menuAide.add(menuhelp);
        menuAide.add(menupropos);
        return menuAide;
    }
    public JPanel panelGraphe(){
        JPanel pgraphe = new JPanel();
        ButtonGroup btngrp = new ButtonGroup();
        bclic = new JToggleButton("clic");
        bsommet = new JToggleButton("sommet");
        barc = new JToggleButton("arc");
        barrete = new JToggleButton("arÃªte");
        barcarrete = new JButton("arc <-> arÃªte");
        bgomme= new JToggleButton("gomme");
        JLabel ltSizeNode = new JLabel("Taille du sommet : ");
        jtfNode= new JTextField("50");
        JButton breset = new JButton("reset");
        clik = new JButton("Clique");
        pgraphe.add(this.panelListenerTest(),"Center");
        btngrp.add(bclic);
        btngrp.add(bsommet);
        btngrp.add(barc);
        btngrp.add(barrete);
        btngrp.add(bgomme);
        pgraphe.add(bclic);
        pgraphe.add(bsommet);
        pgraphe.add(barc);
        pgraphe.add(barcarrete);
        pgraphe.add(bgomme);
        pgraphe.add(clik);
        pgraphe.add(jpg);
        pgraphe.add(ltSizeNode);
        pgraphe.add(jtfNode);
        pgraphe.add(breset);
        clik.addActionListener(new ListenerActionGraphe(this));
        barcarrete.addActionListener(new ListenerActionGraphe(this));
        breset.addActionListener(new ListenerActionGraphe(this));
        jtfNode.addActionListener(new ListenerActionGraphe(this));
        return pgraphe;
    }
    public JPanel panel1(){
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(this.panelGraphe(),"North");
        jp.add(this.panelListenerTest(),"Center");
        return jp;
    }
 
    public JPanel panel2(){
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(this.panelListenerTest2(),"Center");
        jlColors = new JButton();
       // jlColors.setBorderPainted( false );
       
        jp.add(jlColors,"South");
        return jp;
    }
     
    public JPanel panel4(){
        JPanel jp=new JPanel();
        JLabel jl = new JLabel();
        this.graphe.matrice();
        String s="";
        int[][]tab =this.graphe.matrice();
        for(int i=0;i< tab.length;i++)
            for(int j=0;j< tab.length;j++){
                s+=tab[i][j];
            }
        jl.setText(s);
        jp.add(jl);
        return jp;
    }
 
    public JPanelBrelaz panelListenerTest2(){
        jpb = new JPanelBrelaz(new BorderLayout(),this.graphe, this);
        jpb.setBackground(Color.GRAY);
        return jpb;
    }
    public JPanelGraphe panelListenerTest(){
        jpg = new JPanelGraphe(new BorderLayout(),this.graphe);
        jpg.setBackground(Color.GRAY);
        ListenerBoutonGraphe lbg = new ListenerBoutonGraphe(this);
        ListenerMouseMotionGraphe lmmg = new ListenerMouseMotionGraphe(this,lbg);
        jpg.addMouseListener(lbg);
        jpg.addMouseMotionListener(lmmg);
        return jpg;
    }
 
    public JToggleButton getBarc()
    {
        return barc;
    }
 
    public JToggleButton getBarrete()
    {
        return barrete;
    }
 
    public JToggleButton getBgomme()
    {
        return bgomme;
    }
 
    public void setBarc(JToggleButton butarc)
    {
        barc = butarc;
    }
 
    public void setBarrete(JToggleButton butarrete)
    {
        barrete = butarrete;
    }
 
    public void setBgomme(JToggleButton butgomme)
    {
        bgomme = butgomme;
    }

    public JButton getClik() {
        return clik;
    }
 
    public JTabbedPane getOnglets() {
        return onglets;
    }
 
    public Graphe getGraphe() {
        return graphe;
    }
 
    public JToggleButton getBclic() {
        return bclic;
    }
 
    public void setOnglets(JTabbedPane onglets) {
        this.onglets = onglets;
    }
 
    public void setGraphe(Graphe graphe) {
        this.graphe = graphe;
    }
 
    public void setBclic(JToggleButton bclic) {
        this.bclic = bclic;
    }

    public void setClik(JButton clik) {
        this.clik = clik;
    }
 
    public void setGrapheReset(){
        this.graphe = new Graphe();
    }
 
    public JPanelGraphe getJpg() {
        return jpg;
    }
 
    public JToggleButton getBsommet() {
        return bsommet;
    }
 
    public void setJpg(JPanelGraphe jpg) {
        this.jpg = jpg;
    }
 
    public void setBsommet(JToggleButton bsommet) {
        this.bsommet = bsommet;
    }
 
    public JButton getBarcarrete() {
        return barcarrete;
    }
 
    public void setBarcarrete(JButton barcarrete) {
        this.barcarrete = barcarrete;
    }

    public void setBrelaz(JButton brelaz) {
        this.brelaz = brelaz;
    }

    public void setJpb(JPanelBrelaz jpb) {
        this.jpb = jpb;
    }

    public void setColors(int[] colors) {
        this.colors = colors;
    }

    public JButton getBrelaz() {
        return brelaz;
    }

    public JPanelBrelaz getJpb() {
        return jpb;
    }

    public int[] getColors() {
        return colors;
    }

	public JTextField getJtfNode() {
		return jtfNode;
	}

	public void setJtfNode(JTextField jtfNode) {
		this.jtfNode = jtfNode;
	}

	public JButton getJlColors() {
		return jlColors;
	}

	public void setJlColors(JButton jlColors) {
		this.jlColors = jlColors;
	}
 
 
 
}