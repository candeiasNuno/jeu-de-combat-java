import java.util.Scanner;
public class Arene {
/* VARIABLES */
    private Combattant c1;
    private Combattant c2;

/* CONSTRUCTEUR */
    public Arene(Combattant c1, Combattant c2){
        this.c1 = c1;
        this.c2 = c2;
    }

/* METHODES */
    /* METHODE MULTIPLES*/
    public void pause(int millisecondes) {
    try {
        Thread.sleep(millisecondes);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

    /* METHODES V1 */
    public void lancerCombat(){
        int round = 0;
        while(c1.getEstVivant() && c2.getEstVivant()){
            round++;
            this.pause(2000);
            this.affichageEtatCombats(round);
            if(c1.getVitesse() >= c2.getVitesse()){
                System.out.println(" >>> C'EST AU TOUR DE " + c1.getNom().toUpperCase() + " ! <<<");
                this.jouerTour(c1, c2);
                if(c2.getEstVivant()){
                    this.pause(2000);
                    this.affichageEtatCombats(round);
                    System.out.println(" >>> C'EST AU TOUR DE " + c2.getNom().toUpperCase() + " ! <<<");
                    this.jouerTour(c2, c1);
                }
            }
            else{
                System.out.println(" >>> C'EST AU TOUR DE " + c2.getNom().toUpperCase() + " ! <<<");
                this.jouerTour(c2, c1);
                if(c1.getEstVivant()) {
                    this.pause(2000);
                    this.affichageEtatCombats(round);
                    System.out.println(" >>> C'EST AU TOUR DE " + c1.getNom().toUpperCase() + " ! <<<");
                    this.jouerTour(c1, c2);
                }
            }

        }
        if(c1.getEstVivant()){
            this.pause(2000);
            this.victoire(c1);
            this.pause(2000);
        }
        else{
            this.pause(2000);
            this.victoire(c2);
            this.pause(3000);
        }
    }
    public void choixAttaque(int coups, Combattant attaquant, Combattant receveur){
        switch (coups){
            case 0:
                attaquant.lanceAttaqueDebase(receveur);
                System.out.println();
                System.out.println(" \uD83D\uDCA5 BAM ! " + attaquant.getNom().toUpperCase() + " lance son Coups de base !");
                if(receveur.getEstVivant()){
                    System.out.println(" \uD83E\uDE78 " + receveur.getNom().toUpperCase() + " subit les degats ! ");
                }
                break;
            case 1:
                attaquant.lanceAttaqueSpecial1(receveur);
                System.out.println();
                System.out.println(" \uD83D\uDCA5 BAM ! " + attaquant.getNom().toUpperCase() + " lance son Special 1 !");
                if(receveur.getEstVivant()){
                    System.out.println(" \uD83E\uDE78 " + receveur.getNom().toUpperCase() + " subit les degats ! ");
                }
                break;
            case 2:
                attaquant.lanceAttaqueSpecial2(receveur);
                System.out.println();
                System.out.println(" \uD83D\uDCA5 BAM ! " + attaquant.getNom().toUpperCase() + " lance son Special 2 !");
                if(receveur.getEstVivant()){
                    System.out.println(" \uD83E\uDE78 " + receveur.getNom().toUpperCase() + " subit les degats ! ");
                }
                break;
            case 3:
                attaquant.lanceAttaqueUltime(receveur);
                System.out.println();
                System.out.println(" \uD83D\uDCA5 BAM ! " + attaquant.getNom().toUpperCase() + " lance son ULTIME !");
                if(receveur.getEstVivant()){
                    System.out.println(" \uD83E\uDE78 " + receveur.getNom().toUpperCase() + " subit les degats ! ");
                }
                break;
            default:
                System.out.println("Erreur dans le numero saisie : " + coups);
                break;
        }
    }
    public void jouerTour(Combattant attaquant, Combattant defenseur){
        System.out.println();
        Scanner sc = new Scanner(System.in);
        int coup = -1;
        boolean saisieValide = false;
        switch (attaquant.getJaugeSpecial()){
            case 3:
                System.out.println(" ╔═══════════════════════ ══════════════════════════╗");
                System.out.println();
                System.out.println(" ║ [ 0 ] Coup Basique  [ DGT: " + (attaquant.getAttaqueDeBase()) + " ]                  ║");
                System.out.println(" ║ [ 1 ] Spécial 1     [ DGT: " + (attaquant.getAttaqueDeBase() * 2) + " | Coût: 1 SP]      ║");
                System.out.println(" ║ [ 2 ] Spécial 2     [ DGT: " + (attaquant.getAttaqueDeBase() * 3) + " | Coût: 2 SP]      ║");
                System.out.println(" ║ [ 3 ] ULTIME        [ DGT: " + (attaquant.getAttaqueDeBase() * 5) + " | Coût: 3 SP]      ║");
                System.out.println();
                System.out.println(" ╚═══════════════════════ ══════════════════════════╝");
                while (!saisieValide){
                    System.out.print(" Ta saisie : ");
                    if(sc.hasNextInt()){
                        coup = sc.nextInt();
                        if(coup == 0 || coup == 1 || coup == 2 || coup == 3){
                            saisieValide = true;
                        }else{
                            System.out.println("Saisie invalide !  Ce chiffre n'est pas dans la liste.");
                        }
                    }else{
                        System.out.println("Saisie  invalide ! Veuillez entrer un chiffre autorise.");
                        sc.next();
                    }
                }
                this.choixAttaque(coup, attaquant, defenseur);
                break;
            case 2:
                System.out.println(" ╔═══════════════════════ ══════════════════════════╗");
                System.out.println();
                System.out.println(" ║ [ 0 ] Coup Basique  [ DGT: " + (attaquant.getAttaqueDeBase()) + " ]                  ║");
                System.out.println(" ║ [ 1 ] Spécial 1     [ DGT: " + (attaquant.getAttaqueDeBase() * 2) + " | Coût: 1 SP]      ║");
                System.out.println(" ║ [ 2 ] Spécial 2     [ DGT: " + (attaquant.getAttaqueDeBase() * 3) + " | Coût: 2 SP]      ║");
                System.out.println();
                System.out.println(" ╚═══════════════════════ ══════════════════════════╝");
                while (!saisieValide){
                    System.out.print(" Ta saisie : ");
                    if(sc.hasNextInt()){
                        coup = sc.nextInt();
                        if(coup == 0 || coup == 1 || coup == 2){
                            saisieValide = true;
                        }else{
                            System.out.println("Saisie invalide !  Ce chiffre n'est pas dans la liste.");
                        }
                    }else{
                        System.out.println("Saisie  invalide ! Veuillez entrer un chiffre.");
                        sc.next();
                    }
                }
                this.choixAttaque(coup, attaquant, defenseur);
                break;
            case 1:
                System.out.println(" ╔═══════════════════════ ══════════════════════════╗");
                System.out.println();
                System.out.println(" ║ [ 0 ] Coup Basique  [ DGT: " + (attaquant.getAttaqueDeBase()) + " ]                  ║");
                System.out.println(" ║ [ 1 ] Spécial 1     [ DGT: " + (attaquant.getAttaqueDeBase() * 2) + " | Coût: 1 SP]      ║");
                System.out.println();
                System.out.println(" ╚═══════════════════════ ══════════════════════════╝");
                while (!saisieValide){
                    System.out.print(" Ta saisie : ");
                    if(sc.hasNextInt()){
                        coup = sc.nextInt();
                        if(coup == 0 || coup == 1){
                            saisieValide = true;
                        }else{
                            System.out.println("Saisie invalide !  Ce chiffre n'est pas dans la liste.");
                        }
                    }else{
                        System.out.println("Saisie  invalide ! Veuillez entrer un chiffre.");
                        sc.next();
                    }
                }
                this.choixAttaque(coup, attaquant, defenseur);
                break;
            case 0:
                System.out.println(" ╔═══════════════════════ ══════════════════════════╗");
                System.out.println();
                System.out.println(" ║ [ 0 ] Coup Basique  [ DGT: " + (attaquant.getAttaqueDeBase()) + " ]                  ║");
                System.out.println();
                System.out.println(" ╚═══════════════════════ ══════════════════════════╝");
                while (!saisieValide){
                    System.out.print(" Ta saisie : ");
                    if(sc.hasNextInt()){
                        coup = sc.nextInt();
                        if(coup == 0){
                            saisieValide = true;
                        }else{
                            System.out.println("Saisie invalide !  Ce chiffre n'est pas dans la liste.");
                        }
                    }else{
                        System.out.println("Saisie  invalide ! Veuillez entrer un chiffre.");
                        sc.next();
                    }
                }
                this.choixAttaque(coup, attaquant, defenseur);
                break;
        }

    }
    public void affichageEtatCombats(int nbRound){
        System.out.print("\033[H\033[2J"); // Commande pour nettoyer le terminal
        System.out.flush();
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.printf("║                ROUND %-2d - FIGHT !                ║\n", nbRound);
        System.out.println("╠════════════════════════╦═════════════════════════╣");
        System.out.println();
        System.out.printf("║ J1 : %-10s        ║ J2 : %-10s         ║\n", c1.getNom().toUpperCase(), c2.getNom().toUpperCase());
        System.out.printf("║ PV : [########] %-3d    ║ PV : [########] %-3d     ║\n", c1.getVie(), c2.getVie());
        System.out.printf("║ SP : [***] %-3d         ║ SP : [***] %-3d          ║\n", c1.getJaugeSpecial(), c2.getJaugeSpecial());
        System.out.println();
        System.out.println("╚════════════════════════╩═════════════════════════╝");
        System.out.println();
    }
    public void victoire(Combattant winner){
        System.out.print("\033[H\033[2J"); // Commande pour nettoyer le terminal
        System.out.flush();
        System.out.println("             ╔════════════════════════════════════════╗");
        System.out.println("             ║              FIN DU COMBAT             ║");
        System.out.println("             ╠════════════════════════════════════════╣");
        System.out.println("             ║                                        ║ ");
        System.out.printf("             ║       \uD83C\uDFC6 VICTOIRE DE : %-10s \uD83C\uDFC6   ║\n", winner.getNom().toUpperCase());
        System.out.printf("             ║       \uD83D\uDCA5 L\' entrainement à paye ! \uD83D\uDCA5   ║\n");
        System.out.println("             ╚════════════════════════════════════════╝");

    }

    /* METHODES V2 */
    public void affichageEtatCombatV2(int nbRound){
        Affichage.clear();
        Affichage.haut();
        String etatC1;
        String etatC2;
        Affichage.ligneCentree("ROUND " + nbRound + " - ARENE PVP");
        Affichage.separateurDeuxColonnes();
        String J1 = "JOUEUR 1 : " + c1.getNom().toUpperCase() + " (" + c1.getClass().getSimpleName().toUpperCase() + ")";
        String J2 = "JOUEUR 2 : " + c2.getNom().toUpperCase() + " (" + c2.getClass().getSimpleName().toUpperCase() + ")";
        Affichage.deuxColonnes(J1, J2);
        J1 = "État     : " + c1.etatsCombattant();
        J2 = "État     : " + c2.etatsCombattant();
        Affichage.deuxColonnes(J1, J2);
        J1 = "PV       : " + Affichage.genererBarre(c1.getVie(), c1.getVieMax()) + " " + c1.getVie() + "/" + c1.getVieMax();
        J2 = "PV       : " + Affichage.genererBarre(c2.getVie(), c2.getVieMax()) + " " + c2.getVie() + "/" + c2.getVieMax();
        Affichage.deuxColonnes(J1, J2);
        J1 = "Jauge Sp : " + Affichage.genererJaugeSpeciale(c1.getJaugeSpecial()) + " (" + c1.getJaugeSpecial() + "/" + 3 + ")" + " "
                + c1.getJaugeSpecial() + "/" + 3;
        J2 = "Jauge Sp : " + Affichage.genererJaugeSpeciale(c2.getJaugeSpecial()) + " (" + c2.getJaugeSpecial() + "/" + 3 + ")" + " "
                + c2.getJaugeSpecial() + "/" + 3;
        Affichage.deuxColonnes(J1, J2);
        J1 = "Stun     : " + Affichage.genererBarre(c1.getJaugeStun(), c1.getStunMax()) + " " + c1.getJaugeStun() + "/" + c1.getStunMax();
        J2 = "Stun     : " + Affichage.genererBarre(c2.getJaugeStun(), c2.getStunMax()) + " " + c2.getJaugeStun() + "/" + c2.getStunMax();
        Affichage.deuxColonnes(J1, J2);
        J1 = "VIT : " + c1.getVitesse() + "         ATK : " +   c1.getAttaqueDeBase();
        J2 = "VIT : " + c2.getVitesse() + "         ATK : " +   c2.getAttaqueDeBase();
        Affichage.deuxColonnes(J1, J2);
        Affichage.separateurDeuxColonnesVersHaut();
    }
    public void affichageActionDispo(Combattant c){
        Affichage.ligne("ACTIONS DISPONIBLES (" + c.getNom().toUpperCase() + ") :");
        Affichage.ligneSpecial("[1] Attaque Base (0)", "[2] Garde (0)", "[3] Chope (0)");
        switch (c.getJaugeSpecial()){
            case 0:
                Affichage.ligneSpecial("[-] Spéciale1 (Bloq)", "[-] Spéciale2 (Bloq)", "[-] Ultime (Bloq)");
                break;
            case 1:
                Affichage.ligneSpecial("[4] Spéciale1 (1)", "[-] Spéciale2 (Bloq)", "[-] Ultime (Bloq)");
                break;
            case 2:
                Affichage.ligneSpecial("[4] Spéciale1 (1)", "[5] Spéciale2 (2)", "[-] Ultime (Bloq)");
                break;
            case 3:
                Affichage.ligneSpecial("[4] Spéciale1 (1)", "[5] Spéciale2 (2)", "[6] Ultime (3)");
                break;
            default:
                System.out.println("ERREUR JAUGE SPECIAL");
        }
        Affichage.bas();
    }
    public Action creerAction(int choix){
        Action action = null;
        switch (choix){
            case 1:
                action = new Action("Attaque", 1);
                break;
            case 2:
                action = new Action("Garde", 1);
                break;
            case 3:
                action = new Action("Chope", 1);
                break;
            case 4:
                action = new Action("Attaque", 2);
                break;
            case 5:
                action = new Action("Attaque", 3);
                break;
            case 6:
                action = new Action("Attaque", 4);
                break;
            default:
                System.out.println("Erreur creation d action");
                break;
        }
        return action;
    }
    public Action selectionnerAction(Combattant c, int nbRound){
        this.affichageEtatCombatV2(nbRound);
        this.affichageActionDispo(c);
        int maxDispo;
        int rep;
        boolean choixFait = false;
        Action action = null;

        switch (c.getJaugeSpecial()){
            case 0:
                maxDispo = 3;
                break;
            case 1:
                maxDispo = 4;
                break;
            case 2:
                maxDispo = 5;
                break;
            case 3:
                maxDispo = 6;
                break;
            default:
                maxDispo = -1;
                break;
        }
        Scanner sc = new Scanner(System.in);
        while(!choixFait){
            System.out.print("> " + c.getNom().toUpperCase() + ", choisissez votre action (1-" + maxDispo + ") : ");
            if(sc.hasNextInt()){
                rep = sc.nextInt();
                if(rep >=1 && rep <= maxDispo){
                    action = creerAction(rep);
                    choixFait = true;
                } else{
                    System.out.println("> Selectionne une action disponible !");
                    sc.next();

                }
            } else{
                System.out.println("> Selectionne une action disponible !");
                sc.next();
            }
        }
        return action;

    }
    public void resoudreCroisement(Action act1, Combattant j1, Action act2, Combattant j2){
        String actionJ1 = act1.nomAction.toUpperCase();
        int niveauActJ1 = act1.getNiveauAction();
        String actionJ2 = act2.nomAction.toUpperCase();
        int niveauActJ2 = act2.getNiveauAction();
        
    }




}
