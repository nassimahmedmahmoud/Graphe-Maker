package dialogue;

public class Aide
{
    public static final int SOMMAIRE = 0;
    
    private String auteur;
    private String editeur;
    private String text;
    private int curentpage;
    
    public Aide(String auth, String ed, String txt)
    {
        auteur = auth;
        editeur = ed;
        text = txt;
        curentpage = SOMMAIRE;
    }
    
    public Aide(int curent)
    {
        auteur = "";
        editeur = "";
        text = "";
        curentpage = curent;
    }
    
    public Aide()
    {
        this("","","");
    }
    
    public String aPropos()
    {
        auteur = "La team des gars stylés et de la meuf ouf";
        editeur = "EmbrouilleTech©";
        text = "EmbrouilleTech vous propose leur application <b>Graphe-Maker++</b>"
                + " à la pointe de la technologie.<br>Afin de vous faciliter l'utilisation"
                + " de notre application nous vous proposons une assistance disponible<br>"
                + " dans l'onglet Assistance.<br>"
                + "EmbrouilleTech soucieuse du bonheur et du bien-être de ses clients vous"
                + " propose des produits toujours<br>"
                + " plus inovants et de meilleur qualité."
                + "<br>Vous possédez actuellement la version :<br>"
                + " - <b>GrapheMaker++v0.9.31s.release:09.04.2015</b>"
                + "<br><br>Upcoming features :<br>"
                + " - Graphe-MakerExtensionPack_10_colors<br><br>"
                + " - Graphe-MakerAdvancedPackv1.2.11<br><br>"
                + " - Graphe-MakerAdvancedPack_DeluxEditionv1.2.13<br><br>"
                + " - Graphe-MakerExtensionProPackage_5_colorsv1.5.3<br><br>"
                + " - Graphe-MakerBugFixPack0.7_Displayfix<br><br>"
                + " - Graphe-MakerLanguageExtentsionPack_3_languages<br><br>"
                + " - Graphe-MakerUpdatePack++v2.0.1_15_colors<br><br>"
                + "Prix variant des futures version, a partir de $23.99<br>"
                + "<span font-size=4px>Tous droits réservés " + this.editeur + "</span>";
        return auteur+editeur+text;
    }
    
    public String assistance(int page)
    {
        if(page == SOMMAIRE)
            text = "Sommaire :<br><br>"
                + "<b>I - Prise en main de Graphe-Maker++</b><br><br>"
                + " - 1 Options de base<br>"
                + " - 2 Onglets<br><br>"
                + "<b>II - Onglet Editeur de graphe</b><br><br>"
                + " - 1 Manipuler des éléments du graphe<br>"
                + " - 2 Créer/modifier des éléments du graphe<br>"
                + " - 3 Options avancées<br><br>"
                + "<b>III - Onglet Dijkstra</b><br><br>"
                + " - 1 Choix des sources et arrivées<br>"
                + " - 2 Informations<br><br>"
                + "<b>IV - Onglet Coloration</b><br><br>"
                + "<b>V - Onglet général</b><br><br>"
                + " - 1 Informations générales<br>"
                + " - 2 Options avancées<br>";
        switch(page)
        {
            case 1 :
                text = "I - Prise en main de Graphe_Maker++<br>"
                        + " - 1 Options de base<br><br>"
                        + "    Afin de vous aider a mieux comprendre Graphe-Maker++ et<br>"
                        + "afin de vous faciliter son utilisation, nous allons d'abord<br>"
                        + "vous détailler les fonctionnalités de base.<br>"
                        + "Tout d'abord cette application vous permet de créer des graphes<br>"
                        + "à partir de rien ou encore d'en charger, et en sauvegarder.<br>"
                        + "Pour charger votre graphe vous devez d'abord aller dans le menu<br>"
                        + "'Fichier' et ensuite cliquer sur 'charger graphe', pour <br>"
                        + "sauvegarder votre graphe actuel, vous devez accomplir la même<br>"
                        + "démarche a ceci-pres que vous devez clique sur 'sauvegarder<br>"
                        + "graphe'. Ce menu vous permet également de réinitialiser votre<br>"
                        + "graphe.<br><br>"
                        + " - 2 Les onglets<br><br>"
                        + "    A présent que vous connaissez les fonctionnalités de base<br>"
                        + "vous devez maintenant vous familiariser avec l'interface. Pas<br>"
                        + "de panique c'est tout simple ! En effet celle-ci ce découpe en<br>"
                        + "4 onglets qui sont :<br>"
                        + " - L'onglet 'Graphe' pour manipuler le graphe<br>"
                        + " - L'onglet 'Dijkstra' appliquant l'algorithme de Dijkstra<br>"
                        + " - L'onglet 'coloration' permettant de colorer le graphe<br>"
                        + " - L'onglet 'général' contenant diverses informations du graphe<br>";
                break;
            case 2 :
                text = "II - Onglet Editeur de graphe<br>"
                        + " - 1 Manipuler les éléments du graphe<br><br>"
                        + "    Cet onglet sera le plus important, en effet, c'est ici<br>"
                        + "que vous créérez et manipulerez votre graphe. Tout d'abord<br>"
                        + "vous disposez d'un bouton 'clic' a gauche sous le menu principal<br>"
                        + "celui-ci vous permettra de selectionenr un sommet ou un arc<br>"
                        + "et de pouvoir les déplacer. Vous pouvez également gommer un<br>"
                        + "sommet ou un arc avec le bouton 'gomme' situé sur la droite<br>"
                        + "ou encore réinitialiser tout le graphe a l'aide du bouton<br>"
                        + "'reset' situé tout a droite en dessous du menu principal<br><br>"
                        + " 2 - Créer/modifier des éléments du graphe<br><br>"
                        + "    Pouvoir sélectionner des éléments ou pouvoir les effacer<br>"
                        + "c'est bien, mais pouvoir en créer ou en modifier, c'est mieux !<br>"
                        + "Ne vous inquiétez pas, Graphe-Maker++ est prévu pour !<br>"
                        + "Tout d'abord pour créer des sommets, il suffit simplement<br>"
                        + "de sélectionenr le bouton 'Sommet' tout a gauche sous le menu<br>"
                        + "principal. Une fois ce bouton sélectionné appuyez dans le cadre<br>"
                        + "jaune pour créer un sommet, et pour modifier sa valeur, il suffit<br>"
                        + "de faire clic droit sur ce sommet avec ce meme bouton enfoncé.<br><br>"
                        + "<span color=red>Attention Créer plus de 100 sommets peut causer<br>"
                        + "un ralentissement du programme</span><br>";
                break;
            case 3 :
                text = " 2 - Créer/modifier des éléments du graphe<br><br>"
                        + "Une fois que fois que plusieurs sommets sont créés vous pouvez<br>"
                        + "les reliés par un arc (ou arrete) en cliquant sur le bouton arc et<br>"
                        + "en cliquant sur les 2 sommets a relier, et, de la même manière que<br>"
                        + "les sommets vous pouvez changer leur valeur en cliquant sur<br>"
                        + "avec clique droit sur le milieu de l'arc avec le bouton arc, ou clic<br>"
                        + "enfoncé.<br><br> <span color=blue>Asutce : pour créer des arcs avec valeur initiale vous pouvez<br>"
                        + "également faire clique droit sur le sommet d'arrivé !</span><br><br>"
                        + " 3 - Options avancées<br><br>"
                        + "    Vous pouvez dès a présent créer le graphe de vos envies, mais<br>"
                        + "c'est vite lent et peu pratique lorsqu'on doit créer 10 sommets reliés.<br>"
                        + "Oui nous l'avons compris, et c'ets pour ça que nous avons ajouté la<br>"
                        + "possibilité de créer des graphes génériques qui sont la clique, la chaine<br>"
                        + "ou le cycle. Pour ce faire vous n'avez qu'a cliquer sur le bouton<br>"
                        + "correspondant, et ça créera votre modèle avec tous les sommets existants.<br>"
                        + "Vous pouvez également créer des graphes génériques customisés ! Rien de<br>"
                        + "plus simple en vérité, pour ça vous avez juste a cliquer sur les botuons<br>"
                        + "clique, cycle ou chaine custom sur votre droite et enfin clique sur<br>"
                        + "'Append' une fois tous vos sommets voulus séléctionnés. Et comme c'est<br>"
                        + "ennuyeux de devoir donner des valeurs aux arcs 1 a 1 vous pouvez remplir<br>"
                        + "le champ juste en dessous et les arcs de votre graphe générique auront cette<br>"
                        + "valeur.<br><br><span color=red>Attention : ce champ doit être rempli avec un nombre entier</span><br>";
                break;
            case 4 :
                text = "III - Onglet Dijkstra<br>"
                        + " - 1 Choix des sources et arrivées<br><br>"
                        + "    L'algorithme de Dijkstra permet de donner en fonctionde son sommet<br>"
                        + "source la ditance la plus courte entre cettesource et des autres sommets<br>"
                        + "du graphe. La définition d'un sommet d'arrivée permet de visualiser le<br>"
                        + "chemin le plus court pour atteindre cette arrivée en partant de la source<br><br>"
                        + " - 2 Informations<br><br>"
                        + "    Le panneaux du bas présente diverses informations qui sont les<br>"
                        + "sommets du graphe, et pour chaque sommet, son d_min, c'est à dire sa<br>"
                        + "distance par rapport a l'origine, ainsi que son sommet le plus proche<br>"
                        + "par lequel cette source doit passer pour atteindre son arrivée avec<br>"
                        + "la plus courte distance.<br><br>"
                        + "<span color=red>Attention : Un graphe non métrique ne pourra pas faire tourner<br>"
                        + "l'algorithme de Dijkstra</span><br>";
                break;
            case 5 :
                text = "IV - Onglet Coloration<br><br>"
                        + "    Cet onglet permet de visualiser l'algorithme de Brélaz.<br>"
                        + "Cet algorithme colorie chaque sommet en fonction de ses sommets<br>"
                        + "voisins, et chaque sommet a une couleur différente de son voisin.<br>"
                        + "Cet algorithme donne une majoration du nombre de couleurs différentes<br>"
                        + "que peut posséder un graphe. Un graphe planaire par exemple aura un<br>"
                        + "nombre de couleurs majoré par 4<br><br>"
                        + "<span color=red>Attention : Graphe-Maker++ ne supporte que 25 couleurs.<br>"
                        + "Attention : Graphe-Maker++ peut rencontrer des ralentissements si vous<br>"
                        + "avez créé plus de 75 sommets tous reliés entre eux</span>";
                break;
            case 6 :
                text = "V - Onglet Général<br>"
                        + " - 1 Informations générales<br><br>"
                        + "    Cet onlget vous présente les informations générales du graphe.<br>"
                        + "Ces informations sont, son nom, son nombre de sommets et d'arcs, si<br>"
                        + "il est orienté ou non, si ce graphe est connexe, mais également si<br>"
                        + "ce graphe est un arbre. De plus la matrice associée a ce graphe y<br>"
                        + "est également affichée.<br><br>"
                        + " - 2 Options avancées<br><br>"
                        + "    Cet onglet outre ces informations permet également de créer un <br>"
                        + "sc@¶☺&$!#N-gNh%<br>";
                break;
                
        }
        
        return text;
                
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public String getText() {
        return text;
    }

    public int getCurentpage() {
        return curentpage;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCurentpage(int currentpage) {
        this.curentpage = currentpage;
    }
}