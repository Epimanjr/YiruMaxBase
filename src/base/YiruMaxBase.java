package base;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;


public class YiruMaxBase extends Application {

	public static int width = 400;
	public static int height = 300;

	private void init(Stage primaryStage) {
        primaryStage.setTitle("YiruMax Base");
        primaryStage.setScene(new Scene(new Panel(), width,height));
        primaryStage.show();
    }

	@Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
    }

    public static void main(String[] args) {
    	launch(args);
    	/*for(String s : Font.getFontNames()) {
    		System.out.println(s);
    	}*/
    }

    class Panel extends Group {

    	// Tous les composants de la fenêtre
    	private Label labTitre = new Label("Gestion de la connexion");
    	private Label[] labels = {
    		new Label("Driver name : "),
    		new Label("Database name : "),
    		new Label("URL : (default : //localhost)")
    	};
    	private ComboBox<String> choixDriver = new ComboBox<String>();
    	private TextField[] saisies = {
    		new TextField(),
    		new TextField("//localhost")
    	};
    	private Button testerConnexion;
    	private Label labelRes;
    	private Label pied;
    	// Fin composants

    	private boolean connectionOK;

    	Panel() {
    		initComponents();
    	}

    	private void initComponents() {
    		// Caractéristiques du Label titre
    		labTitre.setTranslateX(50);
    		labTitre.setTranslateY(10);
    		Font font = new Font("Trebuchet MS", 18);
    		labTitre.setFont(font);
    		labTitre.setTextFill(Color.web("#0076a3"));

 			int iterateurVertical = 85;
 			int valInc = 30;

    		choixDriver.getItems().addAll(
    			new String("mysql"),
    			new String("oracle")
    		);
    		choixDriver.setValue("mysql");
    		choixDriver.setTranslateX(width/2);
    		choixDriver.setTranslateY(iterateurVertical-5);
    		choixDriver.setOnAction((ActionEvent event) -> {
            	String choix = choixDriver.getValue();
            	System.out.println(choix);
        	});

        	
        	for(Label l : labels) {
        		l.setTranslateX(30);
        		l.setTranslateY(iterateurVertical);
        		iterateurVertical+=valInc;
        		getChildren().add(l);
        	}

        	iterateurVertical = 80 + valInc;
        	for(TextField t : saisies) {
        		t.setTranslateX(width/2);
        		t.setTranslateY(iterateurVertical);
        		iterateurVertical+=valInc;
        		getChildren().add(t);
        	}

        	testerConnexion = new Button("Test connection");
        	testerConnexion.setOnAction((ActionEvent event) -> {
        		//JOptionPane.showMessageDialog(null, "OK", "Database Connection", JOptionPane.INFORMATION_MESSAGE);

        		connectionOK = (Math.random() * 10 < 5) ? true : false;
        		if(connectionOK) {
        			labelRes.setTextFill(Color.web("#00ff00"));
        			labelRes.setText("Connection OK");
        		} else {
        			labelRes.setTextFill(Color.web("#ff0000"));
        			labelRes.setText("Connection NON OK");
        		}
        	});
        	testerConnexion.setTranslateX(width/2 - 50);
        	testerConnexion.setTranslateY(190);

        	labelRes = new Label("");
        	labelRes.setTranslateX(width / 2 - 50);
        	labelRes.setTranslateY(220);

        	pied = new Label("©Maxime BLAISE, 2014. Tous droits réservés.");
        	pied.setFont(Font.font("Verdana", FontPosture.ITALIC, 11));
        	pied.setTranslateY(height - 20);

    		getChildren().add(labTitre);
    		getChildren().add(choixDriver);
    		getChildren().add(testerConnexion);
    		getChildren().add(labelRes);
    		getChildren().add(pied);
    	}
    }
}