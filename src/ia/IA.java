package ia;
import combattants.Combattant;
import combattants.Action;
import java.util.Random;

public class IA {
/* METHODES */
    public Action choisirActionIa(Combattant ia){
        Action act = null;
        Random generateur = new Random();
        int aleatoire = generateur.nextInt(100) + 1; // Genre un chiffre entre 1 - 100
        act = choixActionAleatoire(aleatoire, ia.getJaugeSpecial());
        return act;
    }
    public Action choixActionAleatoire(int proba, int jauge){
        Action act = null;
        switch (jauge) {
            case 0:
                if(proba <= 50){
                    act = new Action("GARDE", 1); // 50% garde
                } else if(proba <= 80){
                    act = new Action("ATTAQUE", 1); // 30% attaque 1
                } else{
                    act = new Action("CHOPE", 1); // 20% chope
                }
                break;
            case 1:
                if(proba <= 30){
                    act = new Action("ATTAQUE", 2); // 30% attaque 2
                } else if(proba <= 60){
                    act = new Action("ATTAQUE", 1); // 30% attaque 1
                } else if(proba <= 80){
                    act = new Action("GARDE", 1); // 20% garde
                } else{
                    act = new Action("CHOPE", 1); // 10% chope
                }
                break;
            case 2:
                if(proba <= 30){
                    act = new Action("ATTAQUE", 3); // 30% attaque 3
                } else if(proba <= 50){
                    act = new Action("ATTAQUE", 2); // 20% attaque 2
                } else if(proba <= 70){
                    act = new Action("ATTAQUE", 1); // 20% attaque 1
                } else if(proba <= 90){
                    act = new Action("GARDE", 1); // 20% garde
                } else{
                    act = new Action("CHOPE", 1); // 10% chope
                }
                break;
            case 3:
                if(proba <= 50){
                    act = new Action("ATTAQUE", 4); // 50% attaque 4
                } else if(proba <= 60){
                    act = new Action("ATTAQUE", 3); // 10% attaque 3
                } else if(proba <= 70){
                    act = new Action("ATTAQUE", 2); // 10% attaque 2
                } else if(proba <= 80){
                    act = new Action("ATTAQUE", 1); // 10% attaque 1
                } else if(proba <= 90){
                    act = new Action("CHOPE", 1); // 10% chope
                } else{
                    act = new Action("GARDE", 1); // 10% garde
                }
            default:
                break;
        }
        return act;
    }

}
