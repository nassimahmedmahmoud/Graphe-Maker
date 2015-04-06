package dialogue;

import java.awt.*;
import javax.swing.*;

import controller.*;
import graphe.*;

public class GrapheView extends JFrame{

	private static final long serialVersionUID = 1L;

	public static final int ONGLET_GRAPHE =0;
	public static final int ONGLET_DIJKSTRA =1;
	public static final int ONGLET_BRELAZ =2;
	public static final int ONGLET_GENERAL =3;

	private JTextArea aff;
	private JMenuItem apropos;
	private JMenuItem assistance;
	private JToggleButton barc;                  // Bouton arc
	private JButton barcarrete;                  // Bouton pour orienter/desorienter le graphe
	private JToggleButton barrete;               // Bouton arrete
	private JToggleButton barrivee;
	private JToggleButton bclic;                 // Bouton clic
	private JToggleButton bgomme;                // Bouton gomme
	private JButton brelaz;
	private JToggleButton bsommet;               // Bouton sommet
	private JToggleButton bsource;
	private ButtonGroup btngrp;
	private JButton chaine;
	private JToggleButton chainec;
	private JButton clik;
	private JToggleButton clikc;
	private int[] colors;
	private JButton create;
	private JToggleButton cyclc;
	private JButton cycle;
	private Dijkstra dijkstra;
	private JTextField dist;
	private JPanel gen;
	private JButton generate;
	private Graphe graphe;
	private JLabel info;
	private JLabel jl ;
	private JLabel jlca;
	private JLabel jlColors;        
	private JLabel jld;
	private JPanelBrelaz jpb;
	private JPanelDijkstra jpd;
	private JPanelGraphe jpg;
	private JTextField jtfNode;
	private JPanel latex;
	private JTextField margep;
	private JMenuItem menugraphe;
	private JMenuItem menuload;
	private JMenuItem menusave;

	private JTabbedPane onglets;
	private JPanel pangen;
	private JButton rename;
	private JTextField taillec;

	public GrapheView(String titre,int w,int h){
		super(titre);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.initMenu();
		this.initialise();
		this.setSize(w,h);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public JPanel generationcode()
	{
		JPanel jp = new JPanel();
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();
		generate = new JButton("Générer code");
		JLabel marge = new JLabel("Choisir marge en pixel");
		JLabel taille = new JLabel("Choisir taille de colonne et de ligne en cm");
		taillec = new JTextField("1");
		margep = new JTextField("50");
		aff = new JTextArea("LaTeX...");
		jp.setBorder(BorderFactory.createTitledBorder("Génération de code"));
		jp1.setBorder(BorderFactory.createEtchedBorder(1));
		jp2.setBorder(BorderFactory.createTitledBorder("Code LaTeX"));
		jp.setLayout(new BorderLayout());
		jp2.setLayout(new BorderLayout());
		jp1.setLayout(new GridLayout(5,1));
		jp1.add(marge);
		jp1.add(margep);
		jp1.add(taille);
		jp1.add(taillec);
		jp1.add(generate);
		jp2.add(aff);
		jp.add(jp1, "North");
		jp.add(jp2, "South");
		margep.addActionListener(new ListenerActionGraphe(this));
		taillec.addActionListener(new ListenerActionGraphe(this));
		generate.addActionListener(new ListenerActionGraphe(this));
		return jp;
	}

	public void grapheMetrique() {
		if(this.getGraphe().metrique())
			this.getOnglets().setEnabledAt(1, true);
		else
			this.getOnglets().setEnabledAt(1, false);
	}

	public JPanel initAvc()
	{
		JPanel opt = new JPanel();
		opt.setPreferredSize(new Dimension(200,this.getHeight()));

		JPanel clk = new JPanel();
		JPanel cst = new JPanel();
		JPanel dst = new JPanel();
		clk.setBorder(BorderFactory.createTitledBorder("Graphes génériques"));
		cst.setBorder(BorderFactory.createTitledBorder("<html><p>Graphes avec"
				+ "<br />sommets spécifiques</p></html>"));
		dst.setBorder(BorderFactory.createTitledBorder("Distance par defaut"));
		opt.setLayout(new GridLayout(3,1));
		dst.setLayout(new GridLayout(5,1));
		//opt.setLayout(new BorderLayout());
		cycle = new JButton("Cycle");
		clik = new JButton("Clique");
		chaine = new JButton("Chaine");
		clikc = new JToggleButton("Clique Custom");
		cyclc = new JToggleButton("Cycle Custom");
		chainec = new JToggleButton("Chaine Custom");
		create = new JButton("Append");
		opt.setBorder(BorderFactory.createTitledBorder("Options Clique"));
		JLabel labelclique = new JLabel("<html><p>Créer un type de graphe"
				+ "<br/>comprenant tous les sommets</p></html>");
		JLabel cliqueavc = new JLabel("<html><p>Cliquez sur les sommets pour"
				+ "<br />faire une graphe customisée</p></html>");
		JLabel d = new JLabel("<html><p>Distance des arcs/arrêtes<br /></p></html>");
		dist = new JTextField("0");
		cst.setMinimumSize(new Dimension(200,400));
		dst.add(d);
		dst.add(dist);
		btngrp.add(clikc);
		btngrp.add(cyclc);
		btngrp.add(chainec);
		clk.add(labelclique);
		clk.add(clik);
		clk.add(cycle);
		clk.add(chaine);
		cst.add(cliqueavc);
		cst.add(clikc);
		cst.add(cyclc);
		cst.add(chainec);
		cst.add(create);
		opt.add(clk,"North");
		opt.add(cst,"Center");
		opt.add(dst,"South");
		cycle.addActionListener(new ListenerActionGraphe(this));
		chaine.addActionListener(new ListenerActionGraphe(this));
		clik.addActionListener(new ListenerActionGraphe(this));
		create.addActionListener(new ListenerActionGraphe(this));
		dist.addActionListener(new ListenerActionGraphe(this));
		return opt;
	}

	public void initialise(){
		Container c = this.getContentPane();
		onglets = new JTabbedPane();
		this.graphe=new Graphe();
		this.dijkstra=new Dijkstra(graphe);
		onglets.addTab("Graphe", null,this.panel1());
		onglets.addTab("Dijkstra", null, this.panel2());
		onglets.addTab("Coloration", null, panel3());
		onglets.addTab("Général", null, this.panel4());
		onglets.setEnabledAt(1, false); 
		onglets.addChangeListener(new ListenerChangeGraphe(this));
		c.add(onglets);
	}

	public void initMenu(){
		JMenuBar jmb=new JMenuBar();
		this.setJMenuBar(jmb);
		jmb.add(menuFile());
		jmb.add(menuEdition());
		jmb.add(menuAide());
	}

	public JMenu menuAide(){
		JMenu menuAide = new JMenu("Aide");
		assistance = new JMenuItem("Assistance");
		apropos = new JMenuItem("A propos de nous");
		menuAide.add(assistance);
		menuAide.add(apropos);
		apropos.addActionListener(new ListenerMenuGraphe(this));
		assistance.addActionListener(new ListenerMenuGraphe(this));
		return menuAide;
	}

	public JMenu menuEdition(){
		JMenu menuEdition = new JMenu("Editer");
		JMenuItem menuundo = new JMenuItem("Undo");
		JMenuItem menuredo = new JMenuItem("Redo");
		menuEdition.add(menuundo);
		menuEdition.add(menuredo);
		return menuEdition;
	}

	public JMenu menuFile(){
		JMenu menufichier=new JMenu("Fichier");
		menugraphe=new JMenuItem("Réinitialiser un graphe");
		menusave = new JMenuItem("sauvegarder graphe");
		menuload = new JMenuItem("charger graphe");
		menufichier.add(menugraphe);
		menufichier.add(menusave);
		menuload.addActionListener(new ListenerMenuGraphe(this));
		menusave.addActionListener(new ListenerMenuGraphe(this));
		menufichier.add(menuload);
		return menufichier;
	}

	public JPanel nbChromatique(){
		JPanel jp=new JPanel(); 
		jp.setBorder(BorderFactory.createTitledBorder("Nombre Chromatique"));
		jlColors= new JLabel();
		jlColors.setHorizontalAlignment(JLabel.CENTER);
		jlColors.setVerticalAlignment(JLabel.CENTER);
		jlColors.setPreferredSize(new Dimension(400,70));
		String s = "";
		s+=""+graphe.chromatique();
		jlColors.setText(s);
		jp.add(jlColors);
		return jp;
	}

	public JPanel panel1(){
		JPanel jp = new JPanel(new BorderLayout());
		jp.add(this.panelGraphe(),"North");
		jp.add(this.panelListenerTest(),"Center");
		jp.add(initAvc(),"East");
		return jp;
	}

	public JPanel panel2(){
		JPanel jp = new JPanel(new BorderLayout());
		jp.add(this.panelDijkstra(),"North");
		jp.add(this.panelListenerTest3(),"Center");
		jld = new JLabel("");
		jp.add(jld,"South");
		return jp;
	}

	public JPanel panel3(){
		JPanel jp = new JPanel(new BorderLayout());
		jp.add(this.panelListenerTest2(),"Center");
		jp.add(nbChromatique(),"South");
		return jp;
	}

	public JPanel panel4(){
		pangen = new JPanel();
		JPanel east = new JPanel();
		latex = generationcode();
		east.setLayout(new GridLayout(2,1));
		gen = panelInfoGeneral();
		pangen.add(panelMatrice(),"North");
		east.add(gen,"North");
		east.add(panelConnexeArbre(),"South");
		pangen.add(east,"East");
		pangen.add(latex);

		return pangen;
	}

	public JPanel panelConnexeArbre(){
		JPanel jp=new JPanel(); 
		jp.setBorder(BorderFactory.createTitledBorder("Informations complémentaires"));
		jlca= new JLabel();
		jlca.setHorizontalAlignment(JLabel.CENTER);
		jlca.setVerticalAlignment(JLabel.CENTER);
		jlca.setPreferredSize(new Dimension(300,40));
		jlca.setText(graphe.toString(Graphe.CONNEXE_ARBRE));
		jp.add(jlca);
		return jp;
	}

	public JPanel panelDijkstra(){
		JPanel jp = new JPanel();
		ButtonGroup btnDijkstra = new ButtonGroup();
		bsource = new JToggleButton("Source");
		barrivee = new JToggleButton("Puit");
		btnDijkstra.add(bsource);
		btnDijkstra.add(barrivee);
		jp.add(bsource);
		jp.add(barrivee);
		return jp;
	}

	public JPanel panelGraphe(){
		JPanel pgraphe = new JPanel();
		btngrp = new ButtonGroup();
		bclic = new JToggleButton(new ImageIcon("img/click.png"));
		bsommet = new JToggleButton(new ImageIcon("img/sommet.png"));
		barc = new JToggleButton("arc");
		barrete = new JToggleButton("arÃªte");
		barcarrete = new JButton(new ImageIcon("img/orienter.png"));
		bgomme= new JToggleButton(new ImageIcon("img/gomme.png"));
		JLabel ltSizeNode = new JLabel("Taille du sommet : ");
		jtfNode= new JTextField("50");
		JButton breset = new JButton(new ImageIcon("img/reset.png"));
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
		pgraphe.add(ltSizeNode);
		pgraphe.add(jtfNode);
		pgraphe.add(breset);
		barcarrete.addActionListener(new ListenerActionGraphe(this));
		breset.addActionListener(new ListenerActionGraphe(this));
		jtfNode.addActionListener(new ListenerActionGraphe(this));
		return pgraphe;
	}

	public JPanel panelInfoGeneral()
	{
		JPanel jp = new JPanel();
		rename = new JButton("Renommer graphe");
		jp.setBorder(BorderFactory.createTitledBorder("Informations générales"));
		info = new JLabel(this.graphe.toString(Graphe.GENERAL));        
		info.setPreferredSize(new Dimension(400,100));
		info.setVerticalAlignment(JLabel.CENTER);
		info.setHorizontalAlignment(JLabel.CENTER);        
		jp.add(info);
		jp.add(rename);
		rename.addActionListener(new ListenerActionGraphe(this));

		return jp;
	} 

	public JPanelGraphe panelListenerTest(){
		jpg = new JPanelGraphe(new BorderLayout(),this);
		jpg.setBorder(BorderFactory.createTitledBorder("Editer le graphe"));
		jpg.setBackground(new Color(250,245,220));
		ListenerBoutonGraphe lbg = new ListenerBoutonGraphe(this);
		ListenerMouseMotionGraphe lmmg = new ListenerMouseMotionGraphe(this,lbg);
		jpg.addMouseListener(lbg);
		jpg.addMouseMotionListener(lmmg);
		return jpg;
	}

	public JPanelBrelaz panelListenerTest2(){
		jpb = new JPanelBrelaz(new BorderLayout(), this);
		jpb.setBorder(BorderFactory.createTitledBorder("Coloration"));
		jpb.setBackground(new Color(250,245,220));
		return jpb;
	}

	public JPanelDijkstra panelListenerTest3(){
		jpd = new JPanelDijkstra(new BorderLayout(),this);
		jpd.setBorder(BorderFactory.createTitledBorder("Dijkstra"));
		jpd.setBackground(new Color(250,245,220));
		jpd.addMouseListener(new ListenerDijkstraGraphe(this));
		return jpd;
	}

	public JPanel panelMatrice(){
		JPanel jp=new JPanel(); 
		jp.setBorder(BorderFactory.createTitledBorder("Matrice"));
		jl= new JLabel();
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
		jl.setMinimumSize(new Dimension(70,70));
		String s = this.graphe.toString(Graphe.MATRIX);
		jl.setText(s);
		jp.add(jl);
		return jp;
	}

	public JTextArea getAff() {
		return aff;
	}
	public JMenuItem getApropos() {
		return apropos;
	}

	public JMenuItem getAssistance() {
		return assistance;
	}

	public JToggleButton getBarc()
	{
		return barc;
	}

	public JButton getBarcarrete() {
		return barcarrete;
	}

	public JToggleButton getBarrete()
	{
		return barrete;
	}
	public JToggleButton getBarrivee() {
		return barrivee;
	}

	public JToggleButton getBclic() {
		return bclic;
	}

	public JToggleButton getBgomme()
	{
		return bgomme;
	}

	public JButton getBrelaz() {
		return brelaz;
	}

	public JToggleButton getBsommet() {
		return bsommet;
	}

	public JToggleButton getBsource() {
		return bsource;
	}

	public ButtonGroup getBtngrp() {
		return btngrp;
	}

	public JButton getChaine() {
		return chaine;
	}

	public JToggleButton getChainec() {
		return chainec;
	}

	public JButton getClik() {
		return clik;
	}

	public JToggleButton getClikc() {
		return clikc;
	}
	public int[] getColors() {
		return colors;
	}
	public JButton getCreate() {
		return create;
	}

	public JToggleButton getCyclc() {
		return cyclc;
	}
	public JButton getCycle() {
		return cycle;
	}

	public Dijkstra getDijkstra() {
		return dijkstra;
	}

	public JTextField getDist() {
		return dist;
	}

	public JPanel getGen() {
		return gen;
	}

	public JButton getGenerate() {
		return generate;
	}

	public Graphe getGraphe() {
		return graphe;
	}

	public JLabel getInfo() {
		return info;
	}

	public JLabel getJl() {
		return jl;
	}

	public JLabel getJlca() {
		return jlca;
	}

	public JLabel getJlColors() {
		return jlColors;
	}

	public JLabel getJld() {
		return jld;
	}

	public JPanelBrelaz getJpb() {
		return jpb;
	}

	public JPanelDijkstra getJpd() {
		return jpd;
	}

	public JPanelGraphe getJpg() {
		return jpg;
	}

	public JTextField getJtfNode() {
		return jtfNode;
	}

	public JPanel getLatex() {
		return latex;
	}

	public JTextField getMargep() {
		return margep;
	}

	public JMenuItem getMenugraphe() {
		return menugraphe;
	}

	public JMenuItem getMenuload() {
		return menuload;
	}

	public JMenuItem getMenusave() {
		return menusave;
	}

	public JTabbedPane getOnglets() {
		return onglets;
	}

	public JPanel getPangen() {
		return pangen;
	}

	public JButton getRename() {
		return rename;
	}

	public JTextField getTaillec() {
		return taillec;
	}

	
	public void setAff(JTextArea aff) {
		this.aff = aff;
	}

	public void setApropos(JMenuItem apropos) {
		this.apropos = apropos;
	}

	public void setAssistance(JMenuItem assistance) {
		this.assistance = assistance;
	}

	public void setBarc(JToggleButton butarc)
	{
		barc = butarc;
	}

	public void setBarcarrete(JButton barcarrete) {
		this.barcarrete = barcarrete;
	}

	public void setBarrete(JToggleButton butarrete)
	{
		barrete = butarrete;
	}

	public void setBarrivee(JToggleButton barrivee) {
		this.barrivee = barrivee;
	}

	public void setBclic(JToggleButton bclic) {
		this.bclic = bclic;
	}

	public void setBgomme(JToggleButton butgomme)
	{
		bgomme = butgomme;
	}

	public void setBrelaz(JButton brelaz) {
		this.brelaz = brelaz;
	}

	public void setBsommet(JToggleButton bsommet) {
		this.bsommet = bsommet;
	}

	public void setBsource(JToggleButton bsource) {
		this.bsource = bsource;
	}

	public void setBtngrp(ButtonGroup btngrp) {
		this.btngrp = btngrp;
	}

	public void setChaine(JButton chaine) {
		this.chaine = chaine;
	}

	public void setChainec(JToggleButton chainec) {
		this.chainec = chainec;
	}

	public void setClik(JButton clik) {
		this.clik = clik;
	}

	public void setClikc(JToggleButton clikc) {
		this.clikc = clikc;
	}

	public void setColors(int[] colors) {
		this.colors = colors;
	}

	public void setCreate(JButton create) {
		this.create = create;
	}

	public void setCyclc(JToggleButton cyclc) {
		this.cyclc = cyclc;
	}

	public void setCycle(JButton cycle) {
		this.cycle = cycle;
	}

	public void setDijkstra(Dijkstra dijkstra) {
		this.dijkstra = dijkstra;
	}

	public void setDist(JTextField dist) {
		this.dist = dist;
	}	

	public void setGen(JPanel gen) {
		this.gen = gen;
	}

	public void setGenerate(JButton generate) {
		this.generate = generate;
	}
	public void setGraphe(Graphe graphe) {
		this.graphe = graphe;
	}

	public void setGrapheReset(){
		this.graphe = new Graphe();
	}

	public void setInfo(JLabel info) {
		this.info = info;
	}

	public void setJl(JLabel jl) {
		this.jl = jl;
	}

	public void setJlca(JLabel jlca) {
		this.jlca = jlca;
	}

	public void setJlColors(JLabel jlColors) {
		this.jlColors = jlColors;
	}

	public void setJld(JLabel jld) {
		this.jld = jld;
	}

	public void setJpb(JPanelBrelaz jpb) {
		this.jpb = jpb;
	}

	public void setJpd(JPanelDijkstra jpd) {
		this.jpd = jpd;
	}

	public void setJpg(JPanelGraphe jpg) {
		this.jpg = jpg;
	}

	public void setJtfNode(JTextField jtfNode) {
		this.jtfNode = jtfNode;
	}

	public void setLatex(JPanel latex) {
		this.latex = latex;
	}

	public void setMargep(JTextField margep) {
		this.margep = margep;
	}

	public void setMenugraphe(JMenuItem menugraphe) {
		this.menugraphe = menugraphe;
	}

	public void setMenuload(JMenuItem menuload) {
		this.menuload = menuload;
	}

	public void setMenusave(JMenuItem menusave) {
		this.menusave = menusave;
	}

	public void setOnglets(JTabbedPane onglets) {
		this.onglets = onglets;
	}

	public void setPangen(JPanel pangen) {
		this.pangen = pangen;
	}

	public void setRename(JButton rename) {
		this.rename = rename;
	}

	public void setTaillec(JTextField taillec) {
		this.taillec = taillec;
	}
}