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
/* METHODES */
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
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                    ║");
        System.out.println("║                🔥 T O U R N O I  M A R T I A L 🔥                  ║");
        System.out.println("║                      -- Édition Terminal --                        ║");
        System.out.println("║                                                                    ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                                    ║");
        System.out.println("║   Menu Principal :                                                 ║");
        System.out.println("║                                                                    ║");
        System.out.println("║   [ 1 ] Lancer un Combat (Versus)                                  ║");
        System.out.println("║   [ 2 ] Afficher les Combattants (Roster)                          ║");
        System.out.println("║   [ 3 ] Quitter le jeu                                             ║");
        System.out.println("║                                                                    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
    }
    public void choixMenuPrincipal(int x){
        Scanner sc = new Scanner(System.in);
        int rep = 0;
        switch (x){
            case 1:
                System.out.println(" Bientôt disponible : Lancement du Combat !");
                this.pause(2000);
                break;
            case 2:
                boolean dansRoster = true;
                while(dansRoster){
                    this.affichageRoster();
                    System.out.print(" > Appuyez sur [ -1 ] pour retourne au menu principal ! : ");
                    if(sc.hasNextInt()){
                        rep = sc.nextInt();
                        if( rep == -1){
                            dansRoster = false;
                        } else{
                            System.out.println(" > Choix invalide.");
                            this.pause(1000);
                        }
                    } else{
                        System.out.println(" > Saisie invalide ! Veuillez entrer un chiffre.");
                        sc.next();
                        this.pause(1000);
                    }

                }
                break;
            case 3:
                this.jeuEnCours = false;
                System.out.println(" > MERCI D\'AVOIR JOUE A NOTRE JEU!");
                break;
            default:
                System.out.println(" > Selectionne un choix disponible parmi les options !");
                this.pause(2000);
                break;
        }
    }
    public void affichageRoster(){
        System.out.print("\033[H\033[2J"); // Commande pour nettoyer le terminal
        System.out.flush();
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   \uD83D\uDC65 ROSTER DES COMBATTANTS \uD83D\uDC65                     ║");
        System.out.println("╠═════╦══════════════╦══════════════╦════════╦════════╦══════════════╣");
        System.out.println("║ ID  ║ NOM          ║ CLASSE       ║ ❤\uFE0F  PV  ║ ⚔\uFE0F  ATK ║ ⚡  VITESSE  ║");
        for(int i = 0; i < this.roster.getRoster().size(); i++){
            System.out.println("╠═════╬══════════════╬══════════════╬════════╬════════╬══════════════╣");
            Combattant c = this.roster.getRoster().get(i);
            System.out.printf("║ [%d] ║ %-12s ║ %-12s ║ %-6d ║ %-6d ║ %-12d ║\n",
                    i,
                    c.getNom().toUpperCase(),
                    c.getClass().getSimpleName(),
                    c.getVie(),
                    c.getAttaqueDeBase(),
                    c.getVitesse()
            );
        }
        System.out.println("╚═════╩══════════════╩══════════════╩════════╩════════╩══════════════╝");
    }
    public void pause(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
