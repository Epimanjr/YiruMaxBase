package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Maxime Blaise
 */
public final class BaseInformation implements Serializable {

    /**
     * Map qui gère toutes les données
     */
    private HashMap<String, String> map = new HashMap<String, String>();

    /**
     * Constructeur qui initialise d'office la map.
     */
    public BaseInformation(HashMap<String, String> maplu) {
    	this.map = maplu;
    }

    /**
     * Constructeur vide.
     * Se contente de remplir avec les valeurs par défaut.
     */
    public BaseInformation() {
    	map.put("driver", "mysql");
    	map.put("namedriver", "com.mysql.jdbc.Driver");
    	map.put("dbname", "dbname");
    	map.put("login", "root");
    	map.put("password", "");
    	map.put("url", "//localhost");
    }

   
    /**
     * Method which read all informations from infobdd.idb file.
     *
     * @return les informations utiles
     */
    public static BaseInformation lectureInformations(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        BaseInformation res = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)) {
            //On récupère simplement l'objet.
            res = new BaseInformation((HashMap<String, String>) ois.readObject());
        }
        

        return res;
    }

    /**
     * This method is the most important in this class. It writes database
     * information in the file.
     *
     * @return true if well-written
     */
    public boolean ecrireInformations(String filename) throws FileNotFoundException, IOException {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            return true;
        }

        return false;
    }

}
