import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class MenuPrincipal {
/* VARIABLES */
    private Roster roster;
    private Sauvegarde sauvegarde;
    private boolean jeuEnCours;

/* CONSTRUCTEUR */
    public MenuPrincipal(){
        this.sauvegarde = new Sauvegarde();
        if(Files.exists(Paths.get("sauvegarde_roster.csv"))){
            this.roster = sauvegarde.chargerRoster();
        }else{
            System.out.println("ERREUR CRITIQUE : Le fichier sauvegarde_roster.csv est introuvable !");
            System.out.println("Fermeture d'urgence du jeu.");
            System.exit(1);
        }
        this.jeuEnCours = true;
    }

    public void lancer(){
        Scanner sc = new Scanner(System.in);
        int repMenuPrincipal;

        while(this.jeuEnCours){
            this.hub();
            System.out.print(" > Que voulez-vous faire ? : ");
            if(sc.hasNextInt()){
                repMenuPrincipal = sc.nextInt();
                this.choixMenuPrincipal(repMenuPrincipal);
            }else{
                System.out.println(" > Selectionne un choix disponible parmi les options ! ");
                sc.next();
                this.pause(2000);
            }
        }
    }
    public void hub(){
        System.out.print("\033[H\033[2J"); // Commande pour nettoyer le terminal
        System.out.flush();
        System.out.println(" ╔══════════════════════════════════════════════════╗ ");
        System.out.println(" ║                                                  ║");
        System.out.println(" ║      \uD83D\uDD25 T O U R N O I  M A R T I A L \uD83D\uDD25          ║");
        System.out.println(" ║            -- Édition Terminal --                ║");
        System.out.println(" ║                                                  ║");
        System.out.println(" ╠══════════════════════════════════════════════════╣");
        System.out.println(" ║                                                  ║");
        System.out.println(" ║   Menu Principal :                               ║");
        System.out.println(" ║                                                  ║");
        System.out.println(" ║   [ 1 ] Lancer un Combat (Versus)                ║");
        System.out.println(" ║   [ 2 ] Afficher les Combattants (Roster)        ║");
        System.out.println(" ║   [ 3 ] Quitter le jeu                           ║");
        System.out.println(" ║                                                  ║");
        System.out.println(" ╚══════════════════════════════════════════════════╝ ");
    }

    public void choixMenuPrincipal(int x){
        Scanner sc = new Scanner(System.in);
        int rep;
        switch (x){
            case 1:
                System.out.println(" Bientôt disponible : Lancement du Combat !");
                this.pause(2000);
                break;
            case 2:
                roster.afficherRoster();
                this.pause(2000); // DEVELOPPE CETTE PARTIE
                break;
            case 3:
                this.jeuEnCours = false;
                System.out.println(" > MERCI D\'AVOIR JOUE A NOTRE JEU!");
            default:
                System.out.println(" > Selectionne un choix disponible parmi les options !");
                this.pause(2000);
                break;
        }
    }

    public void pause(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
