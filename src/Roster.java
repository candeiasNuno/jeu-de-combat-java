import java.util.List;
import  java.util.ArrayList;

public class Roster {
/* VARIABLES */
    private List<Combattant> roster;

/* CONSTRUCTEUR */
    public Roster(){
        this.roster = new ArrayList<>();
    }

/* METHODES */
    public void ajouterCombattant(Combattant combattant){
        this.roster.add(combattant);
        System.out.println(combattant.getNom()+ " a été ajouté au Roster aux Roster de Personnage !");
    }

    public void afficherRoster(){
        System.out.println("--- ROSTER ---");
        this.roster.forEach(c -> System.out.println(" - " + c.getNom()));
    }

    public List<Combattant> getRoster(){ return roster; }
}
