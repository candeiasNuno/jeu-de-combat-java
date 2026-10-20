import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Sauvegarde systeme = new Sauvegarde();
        Roster monEquipe;
        if(Files.exists(Paths.get("sauvegarde_roster.csv"))){
            System.out.println("--- CHARGEMENTS DU ROSTER EXISTANT ---");
            monEquipe = systeme.chargerRoster();
        }else{
            System.out.println("--- CREATION DU ROSTER EN COURS ---");
            monEquipe = new Roster();
            Shoto ryu = new Shoto("Ryu");
            Shoto ken = new Shoto("ken");
            monEquipe.ajouterCombattant(ken);
            monEquipe.ajouterCombattant(ryu);
            systeme.sauvegardeRoster(monEquipe);
        }

        monEquipe.afficherRoster();

        Combattant combattantA = monEquipe.getRoster().get(0);
        Combattant combattantB = monEquipe.getRoster().get(1);
        Combattant combattantC = monEquipe.getRoster().get(2);
        Combattant combattantD = monEquipe.getRoster().get(3);
        Combattant combattantE = monEquipe.getRoster().get(4);

        Arene combat1 = new Arene(combattantC , combattantD);
        combat1.lancerCombat();
    }
}