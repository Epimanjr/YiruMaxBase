/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base;

import base.YiruMaxBase.Panel;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Maxime
 */
public class DialogLogin extends Stage {

    public static int width = 300;
    public static int height = 200;

    public final Panel pan;
    /**
     * Constructeur vide.
     * @param p Panel
     */
    public DialogLogin(Panel p) {
        super();
        this.pan = p;
        init();
    }

    /**
     * Initialisation de la fenêtre.
     */
    private void init() {
        Group group = new Group();
        pan.autoriserConnexion(false);

        Label label = new Label("Authentification");
        label.setTranslateX(20);
        label.setTranslateY(10);
        Font font = new Font("Trebuchet MS", 18);
        label.setFont(font);
        label.setTextFill(Color.web("#0076a3"));
        group.getChildren().add(label);

        int incVertical = 50;
        for (int i = 0; i < labels.length; i++) {
            labels[i].setTranslateX(30);
            labels[i].setTranslateY(incVertical);

            saisies[i].setTranslateX(120);
            saisies[i].setTranslateY(incVertical);

            incVertical += 30;
            group.getChildren().add(labels[i]);
            group.getChildren().add(saisies[i]);
        }

        Button valider = new Button("Connecter");
        valider.setOnAction((ActionEvent event) -> {
            BaseSetting.getInstance().bi.modifierValeur("login", saisies[0].getText());
            BaseSetting.getInstance().bi.modifierValeur("password", saisies[1].getText());
            fermer();
            
            //System.out.println("Suite .");
            pan.testerConnexion();
            pan.autoriserConnexion(true);
        });
        valider.setTranslateX(50);
        valider.setTranslateY(incVertical);
        group.getChildren().add(valider);

        this.setTitle("YiruMax Base");
        this.setScene(new Scene(group, width, height));
        this.show();
    }

    // Début des composants
    private final Label[] labels = {
        new Label("Login"),
        new Label("Mot de Passe")
    };
    private final TextField[] saisies = {
        new TextField("root"),
        new TextField("")
    };
    // Fin des composants

    private void fermer() {
        this.close();
    }
}
