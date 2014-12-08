package base;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Maxime on 08/12/2014.
 */
public class Shell {

    public static void main(String[] args) {
        Shell.lancerShell();
    }

    /**
     * Lancement du shell
     */
    public static void lancerShell() {

        Scanner sc = new Scanner(System.in);

        String line = lectureClavier(sc);
        while(!line.equals("exit")) {
            String[] lines = line.split(" ");
            switch(lines[0]) {
                case "new":
                    nouveau();
                    break;
                case "test":
                    tester();
                    break;
                case "set":
                    modifierVariable(lines[1], lines[2]);
                    break;
            }

            line = lectureClavier(sc);
        }

        // Fermeture
        sc.close();
    }

    /**
     * Test de la connexion.
     */
    private static void tester() {
        if(BaseSetting.getInstance().testerConnexion()) {
            System.out.println("Connexion OK");
        } else {
            System.out.println("Connexion non OK");
        }
    }

    /**
     * Création d'un nouveau fichier de configuration.
     */
    private static void nouveau() {
        BaseSetting.getInstance().bi = new BaseInformation();
        try {
            BaseSetting.getInstance().bi.ecrireInformations(BaseSetting.filename);
            System.out.println("Nouveau fichier (" + BaseSetting.filename + ") OK");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void modifierVariable(String var, String valeur) {
        switch(var) {
            case "fname":
                String sauvegardeValeurFilename = BaseSetting.filename;

                BaseSetting.filename = valeur;
                System.out.println("Test du fichier " + valeur);
                boolean flag = BaseSetting.getInstance().testerConnexion();

                if(flag) {
                    System.out.println("Connexion à la base OK");
                    System.out.println("Nouveau nom du fichier : " + BaseSetting.filename);
                } else {
                    BaseSetting.filename = sauvegardeValeurFilename;
                    System.out.println("Connexion à la base NON OK");
                    System.out.println("Reprise de l'ancien nom du fichier : " + BaseSetting.filename);
                }

                break;

            default:
                BaseSetting.getInstance().bi.modifierValeur(var, valeur);
                System.out.println("Modification " + var + " -> " + valeur + " OK");
                break;

        }
    }

    /**
     * Lecture au clavier.
     * @param sc Scanner.
     * @return La ligne écrite par l'utilisateur.
     */
    private static String lectureClavier(Scanner sc) {
        System.out.print("YiruMaxBase:$ ");
        String res = sc.nextLine();

        return res;
    }
}
