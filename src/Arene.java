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

    public void pause(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


}
