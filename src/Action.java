public class Action {
/* VARIABLES */
    protected String nomAction;
    protected int niveauAction;

/* CONSTRUCTEUR */
    Action(String nomAction, int niveauAction){
        this.nomAction = nomAction;
        this.niveauAction = niveauAction;
    }

/* GETTERS */
    public String getNomAction() { return nomAction; }
    public int getNiveauAction() { return niveauAction;}
}
