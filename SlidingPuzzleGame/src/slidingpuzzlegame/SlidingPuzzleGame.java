/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzlegame;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 *
 * @author Bartek
 */
public class SlidingPuzzleGame extends Application {
    
    private final int WIDTH = 740;
    private final int HEIGHT = 480;
    private final int RIGHT_GROUP_LEFT_MARGIN = 40;
    private final int RIGHT_GROUP_TOP_MARGIN = 40;
    private final int RIGHT_GROUP_SPACING = 40;
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        /*
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });*/
        
        Label counterLabel = new Label("Moves:");
        counterLabel.setLayoutY(RIGHT_GROUP_TOP_MARGIN);
        
        Label counterField = new Label("0");
        counterField.setLayoutY(counterLabel.getLayoutY() 
                + counterLabel.getHeight() + RIGHT_GROUP_SPACING);
        //counterField.setTextAlignment(TextAlignment.RIGHT); TO DO
        
        Button shuffleButton = new Button();
        shuffleButton.setText("Shuffle");
        shuffleButton.setLayoutY(counterField.getLayoutY() 
                + counterField.getHeight() + RIGHT_GROUP_SPACING);
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                shuffle();
            }
        });
        
        Button newPictureButton = new Button();
        newPictureButton.setText("New Picture");
        newPictureButton.setLayoutY(shuffleButton.getLayoutY() 
                + shuffleButton.getHeight() + RIGHT_GROUP_SPACING);
        newPictureButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                loadNewPicture();
            }
        });
        
        World world = new World(this);
        
        Group root = new Group();
        Group left = new Group();
        Group right = new Group();
        
        root.getChildren().addAll(left, right);
        left.getChildren().addAll();
        world.addToGroup(left);
        right.getChildren().addAll(counterLabel, counterField, shuffleButton,
                newPictureButton);
        
        right.setLayoutX(HEIGHT + RIGHT_GROUP_LEFT_MARGIN);
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        primaryStage.setTitle("Puzzle!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void shuffle(){
        //TODO
    }
    
    public void loadNewPicture(){
        //TODO
    }
    
    public void endGame(){
        //TODO
    }
    
}
