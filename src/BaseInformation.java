package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Maxime Blaise
 */
public final class BaseInformation implements Serializable {

    /**
     * For instance oracle or mysql.
     */
    private String driver = "";

    /**
     * In general com.mysql.jdbc.Driver.
     */
    private String namedriver = "";

    /**
     * Database's name.
     */
    private String dbname = "";

    /**
     * Login.
     */
    private String login = "";

    /**
     * Password.
     */
    private String password = "";

    /**
     * URL, such as localhost.
     */
    private String url = "";

    /**
     * Constructor, create object and write information.
     *
     * @param driver driver
     * @param namedriver namedriver
     * @param dbname dbname
     * @param login login
     * @param password password
     * @param url url
     */
    public BaseInformation(String driver, String namedriver, String dbname, String login,
            String password, String url) {
        super();

        this.driver = driver;
        this.namedriver = namedriver;
        this.dbname = dbname;
        this.login = login;
        this.password = password;
        this.url = url;

        // Write information.
        this.ecrireInformations();
    }

    /**
     * Returns driver name, normally needn't.
     *
     * @return Driver name
     */
    public String getNamedriver() {
        return namedriver;
    }

    /**
     * Returns database URL, essential to have a connection.
     *
     * @return database url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns the driver, eighter oracle, or mysql most often.
     *
     * @return driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Returns the name of table, needed to get connection.
     *
     * @return table name
     */
    public String getDbname() {
        return dbname;
    }

    /**
     * Returns user's login.
     *
     * @return user login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Returns user's password.
     *
     * @return mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method which read all informations from infobdd.idb file.
     *
     * @return les informations utiles
     */
    public static BaseInformation lectureInformations() {
        BaseInformation res = null;
        try {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("donnees/infobdd.idb"))) {
                //On récupère simplement l'objet.
                res = (BaseInformation) ois.readObject();
            }
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {

        }

        return res;
    }

    /**
     * This method is the most important in this class. It writes database
     * information in the file.
     *
     * @return true if well-written
     */
    public boolean ecrireInformations() {
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("donnees/infobdd.idb"))) {
                oos.writeObject(this);
                return true;
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        return false;
    }

}
