package moteur;
import java.util.Scanner;
import combattants.Combattant;
import combattants.Action;
import ui.Affichage;
import enums.TypeAction;


public class Arene {
/* VARIABLES */
    protected Combattant c1;
    protected Combattant c2;

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
        J1 = "VIT : " + c1.getVitesse() + "         ATK : " +   (c1.getAttaqueDeBase() + c1.getBoostAttaque());
        J2 = "VIT : " + c2.getVitesse() + "         ATK : " +   (c2.getAttaqueDeBase() + c2.getBoostAttaque());
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
                action = new Action(TypeAction.ATTAQUE, 1);
                break;
            case 2:
                action = new Action(TypeAction.GARDE, 1);
                break;
            case 3:
                action = new Action(TypeAction.CHOPE, 1);
                break;
            case 4:
                action = new Action(TypeAction.ATTAQUE, 2);
                break;
            case 5:
                action = new Action(TypeAction.ATTAQUE, 3);
                break;
            case 6:
                action = new Action(TypeAction.ATTAQUE, 4);
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
            if(c.isEstEtourdi()){
                System.out.println("> " + c.getNom().toUpperCase() + ", est etourdi ! ");
                this.pause(2000);
                action = new Action(TypeAction.STUN, 0);
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
    public String resoudreCroisementV2(Action act1, Combattant c1, Action act2, Combattant c2){
        TypeAction actionJ1 = act1.getNomAction();
        int niveauActJ1 = act1.getNiveauAction();
        TypeAction actionJ2 = act2.getNomAction();
        int niveauActJ2 = act2.getNiveauAction();
        RapportAction rapport = null;
        String message = "> Duel precedent : " + c1.getNom().toUpperCase() + " [" + actionJ1 + "][" + niveauActJ1 + "] ◄── VS ──► " +
                c2.getNom().toUpperCase() + " [" + actionJ2 + "][" + niveauActJ2 + "] \n";
        if(actionJ1 == TypeAction.ATTAQUE){
            if(actionJ2 == TypeAction.GARDE){
                rapport = c1.resoudreAttaqueVsGarde(niveauActJ1, c2);
            } else if(actionJ2 == TypeAction.CHOPE){
                rapport = c1.resoudreAttaqueVsChope(niveauActJ1, c2);
            } else if(actionJ2 == TypeAction.STUN){
                rapport = c1.resoudreAttaqueVsStun(niveauActJ1, c2);
            } else if(actionJ2 == TypeAction.ATTAQUE){
                rapport = c1.resoudreAttaqueVsAttaque(niveauActJ1, c2, niveauActJ2);
            }
        } else if(actionJ1 == TypeAction.GARDE){
            if(actionJ2 == TypeAction.GARDE){
                rapport = c1.resoudreGardeVsGarde(c2);
            } else if(actionJ2 == TypeAction.CHOPE){
                rapport = c2.resoudreChopeVsGarde(c1);
            } else if (actionJ2 == TypeAction.STUN){
                rapport = c1.resoudreGardeVsStun(c2);
            } else if(actionJ2 == TypeAction.ATTAQUE){
                rapport = c2.resoudreAttaqueVsGarde(niveauActJ2, c1);
            }
        } else if(actionJ1 == TypeAction.CHOPE){
            if(actionJ2 == TypeAction.ATTAQUE){
                rapport = c2.resoudreAttaqueVsChope(niveauActJ2, c1);
            } else if(actionJ2 == TypeAction.GARDE){
                rapport = c1.resoudreChopeVsGarde(c2);
            } else if(actionJ2 == TypeAction.CHOPE){
                rapport = c1.resoudreChopeVsChope(c2);
            } else if(actionJ2 == TypeAction.STUN){
                rapport = c1.resoudreChopeVsStun(c2);
            }
        } else if(actionJ1 == TypeAction.STUN){
            if(actionJ2 == TypeAction.ATTAQUE){
                rapport = c2.resoudreAttaqueVsStun(niveauActJ2, c1);
            } else if(actionJ2 == TypeAction.GARDE){
                rapport = c2.resoudreGardeVsStun(c1);
            } else if(actionJ2 == TypeAction.CHOPE){
                rapport = c2.resoudreChopeVsStun(c1);
            } else if(actionJ2 == TypeAction.STUN){
                rapport = c2.resoudreStunVsStun(c1);
            }
        }
        this.appliqueRapport(rapport, rapport.getCombattantCible());
        return message += rapport.getTexteVerdict() + "\n" + rapport.getTexteImpact();
    }
    public void appliqueRapport(RapportAction rapport, Combattant cible){
        if(cible != null){
            cible.recevoirDegats(rapport.getDegatsInfliges());
            cible.recevoirStun(rapport.getDegatsStun());
            cible.recevoirJaugeSpecial(rapport.getJaugeSpeGagne());
            cible.perteJaugeSpe(rapport.getJaugeSpePerdue());
        }
    }
    public void lancerCombatV2(){
        int round = 0;
        this.c1.reinitialiseBonusAttaque();
        this.c2.reinitialiseBonusAttaque();
        String actionRoundPrecedent = "> Tour precedent : Aucun tour encore joue !";
        while(c1.getEstVivant() && c2.getEstVivant()){
            round++;
            Action actJ1 = null;
            Action actJ2 = null;
            actJ1 = this.selectionnerAction(c1, round, actionRoundPrecedent);
            this.pause(1000);
            actJ2 = this.selectionnerAction(c2, round, actionRoundPrecedent);
            c1.desactiveStun();//methode de flemmard mais simple
            c2.desactiveStun();//methode de flemmard mais simple
            actionRoundPrecedent = this.resoudreCroisementV2(actJ1, c1, actJ2, c2);
        }
        Scanner scanner = new Scanner(System.in);
        if(c1.getEstVivant()){
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
