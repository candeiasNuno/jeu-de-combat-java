import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

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
/* METHODES MULTIPLES */
    public void pause(int millisecondes) {
    try {
        Thread.sleep(millisecondes);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

/* METHODES V1 */
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
                Combattant c1 = this.choixCombattant(1);
                Combattant c2 = this.choixCombattant(2);
                System.out.print("\033[H\033[2J"); // Commande pour nettoyer le terminal
                System.out.flush();
                System.out.println(" > J1 : " + c1.getNom().toUpperCase());
                System.out.println("      VS ");
                System.out.println(" > J2 : " + c2.getNom().toUpperCase());
                this.pause(2000);
                Arene combat1 = new Arene(c1, c2);
                combat1.lancerCombat();
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
    public Combattant choixCombattant(int numeroJ){
        Scanner sc = new Scanner(System.in);
        int repJ;
        Combattant c1 = null;
        this.affichageRoster();
        boolean selectionJ = false;
        while(!selectionJ){
            System.out.printf(" > J%d sélectionne un combattant ( tapez son ID ) : ", numeroJ);
            if(sc.hasNextInt()){
                repJ = sc.nextInt();
                if(repJ >= 0 && repJ < this.roster.getRoster().size()){
                    c1 = this.roster.getRoster().get(repJ);
                    selectionJ = true;
                } else{
                    System.out.println(" > Veillez sélectionne un combattant présent dans le roster !");
                }
            } else{
                System.out.println(" > Veillez sélectionne un combattant présent dans le roster !");
                sc.next();
            }
        }
        return c1;
    }

 /* METHODES V2*/
    public void affichageHubV2(){
        String texte;
        Affichage.clear();
        Affichage.haut();
        Affichage.saut();
        texte = "T O U R N O I  M A R T I A L";
        Affichage.ligneCentree(texte);
        texte = "-- Édition Terminal --";
        Affichage.ligneCentree(texte);
        Affichage.saut();
        Affichage.separateur();
        Affichage.saut();
        texte = "Menu Principal :";
        Affichage.ligne(texte);
        Affichage.saut();
        texte = "[ 1 ] Lancer un Combat (Versus)";
        Affichage.ligne(texte);
        texte = "[ 2 ] Afficher les Combattants (Roster)";
        Affichage.ligne(texte);
        texte = "[ 3 ] Afficher les Regles du Combat";
        Affichage.ligne(texte);
        texte = "[ 4 ] Quitter le jeu";
        Affichage.ligne(texte);
        Affichage.saut();
        Affichage.bas();
    }
    public void choixMenuPrincipalV2(int x){
        Scanner sc = new Scanner(System.in);
        int rep = 0;
        switch (x){
            case 1:
                Combattant c1 = this.choixCombattant(1);
                Combattant c2 = this.choixCombattant(2);
                Affichage.clear();
                this.pause(2000);
                Arene combat1 = new Arene(c1, c2);
                combat1.lancerCombat();
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
            case 4:
                this.jeuEnCours = false;
                System.out.println(" > MERCI D\'AVOIR JOUE A NOTRE JEU!");
                break;
            default:
                System.out.println(" > Selectionne un choix disponible parmi les options !");
                this.pause(2000);
                break;
        }
    }
    public void affichageRosterV2(){
        Affichage.clear();
        Affichage.haut();
    }


}
