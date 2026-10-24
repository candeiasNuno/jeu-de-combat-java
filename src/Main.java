import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        /* V1
        MenuPrincipal menu = new MenuPrincipal();
        menu.lancer();*/

        /* V2 */
        Combattant c1 = new Shoto("Romain");
        Combattant c2 = new Shoto("Baptiste");
        Arene combat1 = new Arene(c1, c2);
        combat1.affichageEtatCombatV2(1);
    }
}