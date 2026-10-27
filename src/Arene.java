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
    /* METHODE MULTIPLES */
    public void pause(int millisecondes) {
    try {
        Thread.sleep(millisecondes);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    public int lireSaisieCachee(String message) {
        java.io.Console console = System.console();
        int choix = -1;
        // Mode Terminal (Saisie masquée)
        if (console != null) {
            char[] saisie = console.readPassword("%s", message);
            try {
                choix = Integer.parseInt(new String(saisie));
            } catch (NumberFormatException e) {
                // Reste à -1 si la conversion échoue
            }
        }
        // Mode IDE / Secours (Saisie visible)
        else {
            System.out.print(message);
            java.util.Scanner sc = new java.util.Scanner(System.in);
            if (sc.hasNextInt()) {
                choix = sc.nextInt();
            } else {
                sc.next(); // Vide le scanner
            }
        }
        return choix;
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
        J1 = "Jauge Sp : " + Affichage.genererJaugeSpeciale(c1.getJaugeSpecial()) + " (" + c1.getJaugeSpecial() + "/" + 3 + ")";
        J2 = "Jauge Sp : " + Affichage.genererJaugeSpeciale(c2.getJaugeSpecial()) + " (" + c2.getJaugeSpecial() + "/" + 3 + ")";
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
    public Action selectionnerAction(Combattant c, int nbRound, String texteTourPrecedant){
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
        System.out.println(texteTourPrecedant);
        Affichage.ligneSimple();
        while(!choixFait){
            if(c.estEtourdi){
                System.out.println("> " + c.getNom().toUpperCase() + ", est etourdi ! ");
                this.pause(2000);
                action = new Action("STUN", 0);
                break;
            }
            String prompt = "> " + c.getNom().toUpperCase() + ", choisissez votre action (1-" + maxDispo + ") : ";
            rep = this.lireSaisieCachee(prompt);
            if(rep >= 1 && rep <= maxDispo){
                action = creerAction(rep);
                choixFait = true;
            } else{
                System.out.println("> Selectionne une action disponible !");
            }
        }
        return action;

    }
    public String resoudreCroisement(Action act1, Combattant j1, Action act2, Combattant j2){
        String actionJ1 = act1.getNomAction().toUpperCase();
        int niveauActJ1 = act1.getNiveauAction();
        String actionJ2 = act2.getNomAction().toUpperCase();
        int niveauActJ2 = act2.getNiveauAction();
        int degatStun;
        int degats;
        String message = "> Duel precedent : " + j1.getNom().toUpperCase() + " [" + actionJ1 + "][" + niveauActJ1 + "] ◄── VS ──► " +
                j2.getNom().toUpperCase() + " [" + actionJ2 + "][" + niveauActJ2 + "] \n";

        if(actionJ1.equals("ATTAQUE")){
            if(actionJ2.equals("CHOPE")){
                degats = this.lanceAttaque(j1, j2, niveauActJ1);
                message += "> Verdict        : [COUNTER HIT - INTERROMPU !] " + j1.getNom().toUpperCase() + " frappe " +
                        j2.getNom().toUpperCase() + " au moment ou il tentait une Chope ! \n";
                message += "> Impact         : " + j2.getNom().toUpperCase() + " perd " + degats + " PV !";
            } else if(actionJ2.equals("GARDE")){
                degatStun = j1.estimationDegatInflige(niveauActJ1);
                j1.consomation(niveauActJ1);
                j2.recevoirStun(degatStun);
                message += "> Verdict        : [GARDE PARFAITE !] " + j2.getNom().toUpperCase() + " bloque parfaitement l\'attaque ! \n";
                message += "> Impact         : " + j2.getNom().toUpperCase() + " gagne 1 de jauge Special et subit " + degatStun + " de degats de stun.";
                j2.recevoirJaugeSpecial(1);
            } else if(actionJ2.equals("ATTAQUE")){
                if(niveauActJ1 > niveauActJ2){
                    degats = this.lanceAttaque(j1, j2, niveauActJ1);
                    message += "> Verdict        : [CRUNCH - ECRASEMENT !] " + j1.getNom().toUpperCase() + " ecrase l\'attaque de " +
                            j2.getNom().toUpperCase() + " ! \n";
                    message += "> Impact         : " + j2.getNom().toUpperCase() + " perd " + degats + " PV !";
                } else if(niveauActJ1 < niveauActJ2){
                    degats = this.lanceAttaque(j2, j1, niveauActJ2);
                    message += "> Verdict        : [CRUNCH - ECRASEMENT !] " + j2.getNom().toUpperCase() + " ecrase l\'attaque de " +
                            j1.getNom().toUpperCase() + " ! \n";
                    message += "> Impact         : " + j1.getNom().toUpperCase() + " perd " + degats + " PV !";
                } else{
                    if(j1.getVitesse() > j2.getVitesse()){
                        degats = this.lanceAttaque(j1, j2, niveauActJ1);
                        message += "> Verdict        : [COUNTER HIT !] " + j1.getNom().toUpperCase() + " est plus rapide que " +
                                j2.getNom().toUpperCase() + " ! \n";
                        message += "> Impact         : " + j2.getNom().toUpperCase() + " perd " + degats + " PV !";
                    } else if(j2.getVitesse() > j1.getVitesse()){
                        degats = this.lanceAttaque(j2, j1, niveauActJ2);
                        message += "> Verdict        : [COUNTER HIT !] " + j2.getNom().toUpperCase() + " est plus rapide que " +
                                j1.getNom().toUpperCase() + " ! \n";
                        message += "> Impact         : " + j1.getNom().toUpperCase() + " perd " + degats + " PV !";
                    } else{
                        message += "> Verdict        : [EGALITE !] " + j1.getNom().toUpperCase() + " et " +
                                j2.getNom().toUpperCase() + " ont la meme vitesse d\'attaque ! Ils se sont neutralise ! \n";
                        message += "> Impact         : Les 2 combattant ne subisse aucun degats !";
                    }
                }
            } else if(actionJ2.equals("STUN")){
                degats = this.lanceAttaque(j1, j2, niveauActJ1);
                message += "> Verdict        : [FREE HIT !] " + j1.getNom().toUpperCase() + " frappe " +
                        j2.getNom().toUpperCase() + " pendant qu\'il est stun !! \n";
                message += "> Impact         : " + j2.getNom().toUpperCase() + " perd " + degats + " PV !";
            }
        } else if(actionJ1.equals("GARDE")){
            if(actionJ2.equals("ATTAQUE")){
                degatStun = j2.estimationDegatInflige(niveauActJ2);
                j2.consomation(niveauActJ2);
                j1.recevoirStun(degatStun);
                message += "> Verdict        : [GARDE PARFAITE !] " + j1.getNom().toUpperCase() + " bloque parfaitement l\'attaque ! \n";
                message += "> Impact         : " + j1.getNom().toUpperCase() + " gagne 1 de jauge Special et subit " + degatStun + " de degats de stun.";
                j1.recevoirJaugeSpecial(1);
            } else if(actionJ2.equals("CHOPE")){
                degats = j2.lanceChope(j1);
                message += "> Verdict        : [BRISE-GARDE !] " + j2.getNom().toUpperCase() + " attrape " + j1.getNom().toUpperCase() +
                        " a travers sa Garde et l'envoi au sol !\n";
                message += "> Impact         : " + j1.getNom().toUpperCase() + " perd " + degats + " PV !";
            } else if(actionJ2.equals("GARDE")){
                message += "> Verdict        : [OBSERVATION !] " + j1.getNom().toUpperCase() + " et " +
                        j2.getNom().toUpperCase() + " reste les 2 en garde et s\'observe ! \n";
                message += "> Impact         : Les 2 combattant ne subisse aucun degats !";
            } else if(actionJ2.equals("STUN")){
                message += "> Verdict        : [OCCASION MANQUE !] " + j1.getNom().toUpperCase() + " observe " +
                        j2.getNom().toUpperCase() + " alors qu\'il est stun ! \n";
                message += "> Impact         : Les 2 combattant ne subisse aucun degats !";
            }
        } else if(actionJ1.equals("CHOPE")){
            if(actionJ2.equals("GARDE")){
                degats = j1.lanceChope(j2);
                message += "> Verdict        : [BRISE-GARDE !] " + j1.getNom().toUpperCase() + " attrape " + j2.getNom().toUpperCase() +
                        " a travers sa Garde et l'envoi au sol !\n";
                message += "> Impact         : " + j2.getNom().toUpperCase() + " perd " + degats + " PV !";
            } else if(actionJ2.equals("ATTAQUE")){
                degats = this.lanceAttaque(j2, j1, niveauActJ2);
                message += "> Verdict        : [COUNTER HIT - INTERROMPU !] " + j2.getNom().toUpperCase() + " frappe " +
                        j1.getNom().toUpperCase() + " au moment ou il tentait une Chope ! \n";
                message += "> Impact         : " + j1.getNom().toUpperCase() + " perd " + degats + " PV !";
            } else if(actionJ2.equals("CHOPE")){
                message += "> Verdict        : [TECH - ANNULATION !] " + j1.getNom().toUpperCase() + " et " +
                        j2.getNom().toUpperCase() + " tente une chope en meme temps et se neutralise ! \n";
                message += "> Impact         : Les 2 chope s\'annule !";
            } else if(actionJ2.equals("STUN")){
                degats = j1.lanceChope(j2);
                message += "> Verdict        : [FREE CHOPE !] " + j1.getNom().toUpperCase() + " chope " +
                        j2.getNom().toUpperCase() + " pendant qu\'il est stun !! \n";
                message += "> Impact         : " + j2.getNom().toUpperCase() + " perd " + degats + " PV !";
            }
        } else if(actionJ1.equals("STUN")){
            if(actionJ2.equals("ATTAQUE")){
                degats = this.lanceAttaque(j2, j1, niveauActJ2);
                message += "> Verdict        : [FREE HIT !] " + j2.getNom().toUpperCase() + " frappe " +
                        j1.getNom().toUpperCase() + " pendant qu\'il est stun !! \n";
                message += "> Impact         : " + j1.getNom().toUpperCase() + " perd " + degats + " PV !";
            } else if(actionJ2.equals("GARDE")){
                message += "> Verdict        : [OCCASION MANQUE !] " + j2.getNom().toUpperCase() + " observe " +
                        j1.getNom().toUpperCase() + " alors qu\'il est stun ! \n";
                message += "> Impact         : Les 2 combattant ne subisse aucun degats !";
            } else if(actionJ2.equals("CHOPE")){
                degats = j2.lanceChope(j1);
                message += "> Verdict        : [FREE CHOPE !] " + j2.getNom().toUpperCase() + " chope " +
                        j1.getNom().toUpperCase() + " pendant qu\'il est stun !! \n";
                message += "> Impact         : " + j1.getNom().toUpperCase() + " perd " + degats + " PV !";
            } else if(actionJ2.equals("STUN")){
                message += "> Verdict        : [DOUBLE STUN !] " + j2.getNom().toUpperCase() + " et " +
                        j1.getNom().toUpperCase() + " sont les 2 stun !!! \n";
                message += "> Impact         : Les 2 combattant passe leur tour !";
            }
        }
        return message;
    }
    public int lanceAttaque(Combattant att, Combattant cible, int niveauAttaque){
        int degats = 0;
        switch (niveauAttaque){
            case 1:
                degats = att.lanceAttaqueDebase(cible);
                break;
            case 2:
                degats = att.lanceAttaqueSpecial1(cible);
                break;
            case 3:
                degats = att.lanceAttaqueSpecial2(cible);
                break;
            case 4:
                degats = att.lanceAttaqueUltime(cible);
                break;
            default:
                System.out.println("Erreur lance attaque");
                break;
        }
        return degats;
    }
    public void lancerCombatV2(){
        int round = 1;
        String actionRoundPrecedent = "> Tour precedent : Aucun tour encore joue !";
        while(c1.estVivant && c2.estVivant){
            Action actJ1 = null;
            Action actJ2 = null;
            if(!c1.estEtourdi){
                actJ1 = selectionnerAction(c1, round, actionRoundPrecedent);
                this.pause(1000);
            } else{
                actJ1 = selectionnerAction(c1, round, actionRoundPrecedent);
                this.pause(1000);
            }
            if(!c2.estEtourdi){
                actJ2 = selectionnerAction(c2, round, actionRoundPrecedent);
            } else{
                actJ2 = selectionnerAction(c2, round, actionRoundPrecedent);
            }
            c1.desactiveStun();//methode de flemmard mais simple
            c2.desactiveStun();//methode de flemmard mais simple
            actionRoundPrecedent = this.resoudreCroisement(actJ1, c1, actJ2, c2);
            round++;
        }
        if(c1.estVivant){
            this.victoire(c1);
        } else{
            this.victoire(c2);
        }
    }




}
