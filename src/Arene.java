import javax.annotation.processing.SupportedSourceVersion;
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
    public void afficheVictoire2(Combattant winner, Combattant loser, int nbRound, String resumeDernierRound){
        Affichage.clear();
        Affichage.haut();
        String texte = "=== ★ VICTOIRE DE " + winner.getNom().toUpperCase() + " ★ ===";
        Affichage.banniereEncadree(texte);
        Affichage.separateur();
        texte = "BILAN DE L\'AFFRONTEMENT :";
        Affichage.ligne(texte);
        texte = "  • Adversaire vaincu : " + loser.getNom().toUpperCase() + " (" + loser.getClass().getSimpleName() + ")";
        Affichage.ligne(texte);
        texte = "  • Duree du duel     : " + nbRound + " Rounds";
        Affichage.ligne(texte);
        texte = "  • Vie restante      : " + Affichage.genererBarre(winner.getVie(), winner.getVieMax()) + " " +
                winner.getVie() + "/" + winner.getVieMax() + " PV";
        Affichage.ligne(texte);
        texte = "  • Jauge Sp finale   : " + Affichage.genererJaugeSpeciale(winner.getJaugeSpecial()) + " (" +
                winner.getJaugeSpecial() + "/3)";
        Affichage.ligne(texte);
        Affichage.separateur();
        texte = "RANG DU MATCH : ";
        if(winner.getVie() >= ( winner.getVieMax() * 0.90)){
            texte += "[ S - VICTOIRE PARFAITE ]";
        } else if(winner.getVie() >= ( winner.getVieMax() * 0.50)){
            texte += "[ A - VICTOIRE NORMAL ]";
        } else if(winner.getVie() >= ( winner.getVieMax() * 0.10)){
            texte += "[ C - VICTOIRE DANS LE DUR ]";
        } else{
            texte += "[ D - VICTOIRE IN EXTREMISTE ]";
        }
        Affichage.ligneCentree(texte);
        Affichage.bas();
        System.out.println("> Dernier echange effectue : ");
        System.out.println(resumeDernierRound);
        Affichage.ligneSimple();

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
        int round = 0;
        String actionRoundPrecedent = "> Tour precedent : Aucun tour encore joue !";
        while(c1.estVivant && c2.estVivant){
            round++;
            Action actJ1 = null;
            Action actJ2 = null;
            actJ1 = selectionnerAction(c1, round, actionRoundPrecedent);
            this.pause(1000);
            actJ2 = selectionnerAction(c2, round, actionRoundPrecedent);
            c1.desactiveStun();//methode de flemmard mais simple
            c2.desactiveStun();//methode de flemmard mais simple
            actionRoundPrecedent = this.resoudreCroisement(actJ1, c1, actJ2, c2);
        }
        Scanner scanner = new Scanner(System.in);
        if(c1.estVivant){
            this.afficheVictoire2(c1, c2, round, actionRoundPrecedent);
            System.out.print("> Appuyez sur [Entrée] pour retourner au Menu Principal...  ");
            scanner.nextLine();
        } else{
            this.afficheVictoire2(c2, c1, round, actionRoundPrecedent);
            System.out.print("> Appuyez sur [Entrée] pour retourner au Menu Principal...  ");
            scanner.nextLine();
        }
    }

}
