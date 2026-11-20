package moteur;
import combattants.Action;
import combattants.Combattant;
import ia.IA;

import java.util.Scanner;

public class AreneIa extends Arene{
/* CONSTRUCTEUR */
    public AreneIa(Combattant c1, Combattant c2){
        super(c1, c2);
    }

/* METHODES */
    @Override public void lancerCombatV2(){
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
            actJ2 = IA.choisirActionIa(c2);
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
