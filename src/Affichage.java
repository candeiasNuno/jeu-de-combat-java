public class Affichage {
    protected static final int LARGEUR = 70;
    private static final int LARGEUR_TEXTE = LARGEUR - 4;

/* AFFICHAGE CLASSIQUE */
    public static void haut(){
        System.out.println("╔" + "═".repeat(LARGEUR - 2) + "╗");
    }
    public static void ligneSimple(){
        System.out.println("─".repeat(LARGEUR));
    }
    public static void bas(){
        System.out.println("╚" + "═".repeat(LARGEUR - 2) + "╝");
    }
    public static void separateur(){
        System.out.println("║" + "═".repeat(LARGEUR - 2) + "║");
    }
    public static void separateurDeuxColonnes() {
        System.out.println("╠" + "═".repeat(33) + "╦" + "═".repeat(34) + "╣");
    }
    public static void separateurDeuxColonnesVersHaut() {
        System.out.println("╠" + "═".repeat(33) + "╩" + "═".repeat(34) + "╣");
    }
    public static void ligneVide(){
        System.out.println("╠" + " ".repeat(LARGEUR - 2) + "╣");
    }
    public static void clear(){
        System.out.print("\033[H\033[2J"); // Commande pour nettoyer le terminal
        System.out.flush();
    }

/* AFFICHAGE AVEC TEXTE */
    public static void ligne(String texte) {
        System.out.printf("║ %-" + LARGEUR_TEXTE + "s ║%n", tronquer(texte, LARGEUR_TEXTE));
    }
    public static void ligneSpecial(String texte1, String texte2, String texte3) {
        // 66 caractères disponibles : 3 colonnes de 20 caractères + 2 écarts de 3 espaces (20 + 3 + 20 + 3 + 20 = 66)
        int largeurCol = (LARGEUR_TEXTE - 6) / 3; // Donne exactement 20

        System.out.printf("║ %-" + largeurCol + "s   %-" + largeurCol + "s   %-" + largeurCol + "s ║%n",
                tronquer(texte1, largeurCol),
                tronquer(texte2, largeurCol),
                tronquer(texte3, largeurCol));
    }
    public static void ligneCentree(String texte) {
        String texteSecurise = tronquer(texte, LARGEUR_TEXTE);
        int espacesGauche = (LARGEUR_TEXTE - texteSecurise.length()) / 2;
        int espacesDroite = LARGEUR_TEXTE - texteSecurise.length() - espacesGauche;

        System.out.println("║ " + " ".repeat(Math.max(0, espacesGauche))
                + texteSecurise
                + " ".repeat(Math.max(0, espacesDroite)) + " ║");
    }
    public static void deuxColonnes(String gauche, String droite) {
        System.out.printf("║ %-31s ║ %-32s ║%n", tronquer(gauche, 31), tronquer(droite, 32));
    }

/* BARRE VISUELLES DYNAMIQUES */
    public static String genererBarre(int valeurActuelle, int valeurMax) {
        if (valeurMax <= 0) return "[░░░░░░░░░░]";

        int blocsPleins = Math.max(0, Math.min(10, (int) Math.round(((double) valeurActuelle / valeurMax) * 10)));
        int blocsVides = 10 - blocsPleins;

        return "[" + "█".repeat(blocsPleins) + "░".repeat(blocsVides) + "]";
    }
    public static String genererJaugeSpeciale(int jaugeActuelle) {
        int jaugeSecurisee = Math.max(0, Math.min(3, jaugeActuelle));
        return "[" + "■".repeat(jaugeSecurisee) + "□".repeat(3 - jaugeSecurisee) + " ]";
    }

/* SECURITE ANTI-DÉBORDEMENTS */
    private static String tronquer(String texte, int max) {
        if (texte == null) return "";
        if (texte.length() > max) {
            return texte.substring(0, max - 3) + "...";
        }
        return texte;
    }
}
