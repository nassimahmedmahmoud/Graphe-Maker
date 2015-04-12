package graphe;
/**
 * Exception gérant la taille des lignes/colonnes du graphe pour la génération du code
 * @author Aniki
 */
public class TailleColumnLineException extends Exception
{
    private int nb;
    
    public TailleColumnLineException(int n)
    {
        super();
        nb = n;
    }
    
    @Override
    public String getMessage()
    {
        return ""+nb;
    }
}
