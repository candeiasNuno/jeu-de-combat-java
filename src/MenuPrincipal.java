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
    public void affichageRosterV2(){
        String texte;
        Affichage.clear();
        Affichage.haut();
        texte = "=== ROSTER DES COMBATTANTS ===";
        Affichage.ligneCentree(texte);
        Affichage.separateurSeptColonnesHaut();
        Affichage.septColonnes("ID", "NOM", "CLASSE", "♥ PV", "▲ ATK", "» VIT", "★ STUN");
        for(int i = 0; i < this.roster.getRoster().size(); i++){
            Affichage.separateurSeptColonnesMilieu();
            Combattant c = this.roster.getRoster().get(i);
            Affichage.septColonnes(
                    "[" + i + "]",
                    c.getNom().toUpperCase(),
                    c.getClass().getSimpleName(),
                    String.valueOf(c.getVieMax()),
                    String.valueOf(c.getAttaqueDeBase()),
                    String.valueOf(c.getVitesse()),
                    String.valueOf(c.getStunMax())
            );
        }
        Affichage.basSeptColonnes();
    }
    public Combattant choixCombattant(int numeroJ){
        Scanner sc = new Scanner(System.in);
        int repJ;
        Combattant c1 = null;
        this.affichageRosterV2();
        boolean selectionJ = false;
        while(!selectionJ){
            System.out.printf(" > J%d sélectionne un combattant ( tapez son ID ) : ", numeroJ);
            if(sc.hasNextInt()){
                repJ = sc.nextInt();
                if(repJ >= 0 && repJ < this.roster.getRoster().size()){
                    c1 = this.roster.getRoster().get(repJ).copieCombattant();
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
                combat1.lancerCombatV2();
                break;
            case 2:
                boolean dansRoster = true;
                while(dansRoster){
                    this.affichageRosterV2();
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
                boolean dansRegles = true;
                while(dansRegles){
                    this.affichageRegles();
                    System.out.print(" > Appuyez sur [ -1 ] pour retourne au menu principal ! : ");
                    if(sc.hasNextInt()){
                        rep = sc.nextInt();
                        if( rep == -1){
                            dansRegles = false;
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
    public void affichageRegles(){
        Affichage.clear();
        Affichage.haut();
        String texte = "REGLES DU COMBAT V2";
        Affichage.ligneCentree(texte);
        Affichage.separateur();
        texte = "1. LE TRIANGLE TACTIQUE (Resolution simultanee)";
        Affichage.ligne(texte);
        texte = "   • ATTAQUE bat CHOPE   : Interrompt la prise (Counter Hit)";
        Affichage.ligne(texte);
        texte = "   • CHOPE   bat GARDE   : Traverse la défense (Brise-Garde)";
        Affichage.ligne(texte);
        texte = "   • GARDE   bat ATTAQUE : 0 dégât PV, +1 Jauge Sp mais +Stun";
        Affichage.ligne(texte);
        Affichage.saut();
        texte = "2. PRIORITÉS D'ATTAQUE & ÉTOURDISSEMENT (STUN)";
        Affichage.ligne(texte);
        texte = "   • Niveau supérieur écrase niveau inférieur (Ultime > Sp2 > ...)";
        Affichage.ligne(texte);
        texte = "   • À niveau égal, la plus haute VITESSE l'emporte";
        Affichage.ligne(texte);
        texte = "   • Si la jauge de Stun est pleine : ÉTOURDI au tour suivant !";
        Affichage.ligne(texte);
        Affichage.separateur();
        texte = "3. SPÉCIFICITÉS DES CLASSES";
        Affichage.ligne(texte);
        texte = "   • SHOTO    : +50% Stun sur Garde | Sp1 +Stun | Sp2 +1SP | Ult 0";
        Affichage.ligne(texte);
        texte = "   • ASSASSIN : Très rapide | Sp1 +Stun | Sp2 -1SP | Ult Exécution";
        Affichage.ligne(texte);
        texte = "   • GRAPPLER : Chope boostée (+1SP/+Stun) | Sp1 +Stun | Sp2 Soin";
        Affichage.ligne(texte);
        Affichage.bas();


    }
    public void lancerV2(){
        Scanner sc = new Scanner(System.in);
        int repMenuPrincipal;
        while(this.jeuEnCours){
            this.affichageHubV2();
            System.out.print(" > Que voulez-vous faire ? : ");
            if(sc.hasNextInt()){
                repMenuPrincipal = sc.nextInt();
                this.choixMenuPrincipalV2(repMenuPrincipal);
            }else{
                System.out.println(" > Selectionne un choix disponible parmi les options ! ");
                sc.next();
                this.pause(2000);
            }
        }
    }


}
