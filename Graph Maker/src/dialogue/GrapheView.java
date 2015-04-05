package dialogue;
 
import java.awt.*;

import javax.swing.*;
import controller.*;
import graphe.*;
 
 
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
    private JPanelDijkstra jpd;
    private JPanel pangen;
    private JTextField jtfNode;
    private JLabel jlColors;
    private JButton clik;
    private JButton cycle;
    private JButton chaine;
    private JToggleButton clikc;
    private JToggleButton cyclc;
    private JToggleButton chainec;
    private JButton create;
    private int[] colors;
    private JLabel jl ;
    private JLabel jlca;
    private JLabel info;
    private ButtonGroup btngrp;
    private JTextField dist;
    private Dijkstra dijkstra;
    private JLabel jld;
    private JToggleButton bsource;
    private JToggleButton barrivee;
    private JButton rename;
    private JPanel gen;        
    private JMenuItem menugraphe;
    private JMenuItem menusave;
    private JMenuItem menuload;
    private JMenuItem assistance;
    private JMenuItem apropos;
    
    public static final int ONGLET_GRAPHE =0;
    public static final int ONGLET_DIJKSTRA =1;
    public static final int ONGLET_BRELAZ =2;
    public static final int ONGLET_GENERAL =3;
 
    public GrapheView(String titre,int w,int h){
        super(titre);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initMenu();
        this.initialise();
        this.setSize(w,h);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
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
        assistance = new JMenuItem("Assistance");
        apropos = new JMenuItem("A propos de nous");
        menuAide.add(assistance);
        menuAide.add(apropos);
        apropos.addActionListener(new ListenerMenuGraphe(this));
        assistance.addActionListener(new ListenerMenuGraphe(this));
        return menuAide;
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

public JPanel panelGraphe(){
        JPanel pgraphe = new JPanel();
        btngrp = new ButtonGroup();
        bclic = new JToggleButton("clic");
        bsommet = new JToggleButton("sommet");
        barc = new JToggleButton("arc");
        barrete = new JToggleButton("arÃªte");
        barcarrete = new JButton("arc <-> arÃªte");
        bgomme= new JToggleButton("gomme");
        JLabel ltSizeNode = new JLabel("Taille du sommet : ");
        jtfNode= new JTextField("50");
        JButton breset = new JButton("reset");
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
    public JPanel panel1(){
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(this.panelGraphe(),"North");
        jp.add(this.panelListenerTest(),"Center");
        jp.add(initAvc(),"East");
        return jp;
    }
 
    public JPanel panel3(){
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(this.panelListenerTest2(),"Center");
        jp.add(nbChromatique(),"South");
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
     
    public JPanel panel4(){
        pangen = new JPanel();
        JPanel east = new JPanel();
        east.setLayout(new GridLayout(2,1));
        gen = panelInfoGeneral();
        pangen.add(panelMatrice(),"North");
        east.add(gen,"North");
        east.add(panelConnexeArbre(),"South");
        pangen.add(east,"East");
        
        return pangen;
    }
    
    public JPanel panelDijkstra(){
    	JPanel jp = new JPanel();
    	ButtonGroup btnDijkstra = new ButtonGroup();
    	bsource = new JToggleButton("Source");
        barrivee = new JToggleButton("Arrivee");
        btnDijkstra.add(bsource);
        btnDijkstra.add(barrivee);
        jp.add(bsource);
        jp.add(barrivee);
    	return jp;
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
        jlColors.repaint();
        return jp;
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
        jl.repaint();
        return jp;
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
    
    public JPanel panelConnexeArbre(){
    	JPanel jp=new JPanel(); 
    	jp.setBorder(BorderFactory.createTitledBorder("Informations complémentaires"));
    	jlca= new JLabel();
    	jlca.setHorizontalAlignment(JLabel.CENTER);
    	jlca.setVerticalAlignment(JLabel.CENTER);
    	jlca.setPreferredSize(new Dimension(300,40));
    	jlca.setText(graphe.toString(Graphe.CONNEXE_ARBRE));
        jp.add(jlca);
        jlca.repaint();
        return jp;
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

    public JButton getRename() {
        return rename;
    }

    public void setRename(JButton rename) {
        this.rename = rename;
    }

    public JPanel getGen() {
        return gen;
    }

    public void setGen(JPanel gen) {
        this.gen = gen;
    }
    
    public JTextField getJtfNode() {
	return jtfNode;
    }

    public void setJtfNode(JTextField jtfNode) {
	this.jtfNode = jtfNode;
    }

    public JLabel getJlColors() {
	return jlColors;
    }

    public void setJlColors(JLabel jlColors) {
	this.jlColors = jlColors;
    }

    public JLabel getJl() {
	return jl;
    }

    public void setJl(JLabel jl) {
	this.jl = jl;
    }

    public JLabel getJlca() {
	return jlca;
    }

	public void setJlca(JLabel jlca) {
		this.jlca = jlca;
	}

        public JToggleButton getClikc() {
            return clikc;
        } 

        public void setClikc(JToggleButton clikc) {
            this.clikc = clikc;
        }

        public JPanelDijkstra getJpd() {
            return jpd;
        }

        public JLabel getInfo() {
            return info;
        }

        public ButtonGroup getBtngrp() {
            return btngrp;
        }

        public void setJpd(JPanelDijkstra jpd) {
            this.jpd = jpd;
        }

        public void setInfo(JLabel info) {
            this.info = info;
        }

        public void setBtngrp(ButtonGroup btngrp) {
            this.btngrp = btngrp;
        }

        public JButton getCreate() {
            return create;
        }

        public JTextField getDist() {
            return dist;
        }

        public JButton getCycle() {
            return cycle;
        }

        public JButton getChaine() {
            return chaine;
        }

        public JToggleButton getCyclc() {
            return cyclc;
        }

        public JToggleButton getChainec() {
            return chainec;
        }

        public void setCycle(JButton cycle) {
            this.cycle = cycle;
        }

        public void setChaine(JButton chaine) {
            this.chaine = chaine;
        }

        public void setCyclc(JToggleButton cyclc) {
            this.cyclc = cyclc;
        }

        public void setChainec(JToggleButton chainec) {
            this.chainec = chainec;
        }

        public void setDist(JTextField dist) {
            this.dist = dist;
        }

        public void setCreate(JButton create) {
            this.create = create;
        }

	public Dijkstra getDijkstra() {
		return dijkstra;
	}

	public void setDijkstra(Dijkstra dijkstra) {
		this.dijkstra = dijkstra;
	}

	public JLabel getJld() {
		return jld;
	}

	public void setJld(JLabel jld) {
		this.jld = jld;
	}

	public JToggleButton getBsource() {
		return bsource;
	}

	public JToggleButton getBarrivee() {
		return barrivee;
	}

	public void setBsource(JToggleButton bsource) {
		this.bsource = bsource;
	}

	public void setBarrivee(JToggleButton barrivee) {
		this.barrivee = barrivee;
	}	

	public JPanel getPangen() {
		return pangen;
	}

	public void setPangen(JPanel pangen) {
		this.pangen = pangen;
	}
	public JMenuItem getMenugraphe() {
		return menugraphe;
	}

	public void setMenugraphe(JMenuItem menugraphe) {
		this.menugraphe = menugraphe;
	}

	public JMenuItem getMenusave() {
		return menusave;
	}

	public void setMenusave(JMenuItem menusave) {
		this.menusave = menusave;
	}

	public JMenuItem getMenuload() {
		return menuload;
	}

	public void setMenuload(JMenuItem menuload) {
		this.menuload = menuload;
	}

        public JMenuItem getAssistance() {
            return assistance;
        }

        public JMenuItem getApropos() {
            return apropos;
        }

        public void setAssistance(JMenuItem assistance) {
            this.assistance = assistance;
        }

        public void setApropos(JMenuItem apropos) {
            this.apropos = apropos;
        }
}