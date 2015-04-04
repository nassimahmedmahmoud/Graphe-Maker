package dialogue;


public class Aide
{
    private String auteur;
    private String editeur;
    private String text;
    
    public Aide(String auth, String ed, String txt)
    {
        auteur = auth;
        editeur = ed;
        text = txt;
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
                + " propose des produits toujours plus inovants<br>"
                + "et de meilleur qualité."
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

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public String getText() {
        return text;
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
}
