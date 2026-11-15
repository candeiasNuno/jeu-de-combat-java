package combattants;
import enums.TypeAction;

public class Action {
/* VARIABLES */
    protected TypeAction nomAction;
    protected int niveauAction;

/* CONSTRUCTEUR */
    public Action(TypeAction nomAction, int niveauAction){
        this.nomAction = nomAction;
        this.niveauAction = niveauAction;
    }

/* GETTERS */
    public TypeAction getNomAction() { return nomAction; }
    public int getNiveauAction() { return niveauAction;}
}
