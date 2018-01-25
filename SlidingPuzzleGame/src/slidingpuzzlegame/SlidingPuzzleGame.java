/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzlegame;

import java.io.FileInputStream;
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
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    public final int SIZE = 4;
    
    private Label counterLabel;
    private Label counterField;
    private Button shuffleButton;
    private Button newPictureButton;   
    
    private World world;
    
    private Group root;
    private Group left;
    private VBox right;
    
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
        world = new World(this);
        
        counterLabel = new Label("Moves:");
        //counterLabel.setLayoutY(RIGHT_GROUP_TOP_MARGIN);
        
        counterField = new Label("0");
        //counterField.setLayoutY(counterLabel.getLayoutY() 
                //+ counterLabel.getHeight() + RIGHT_GROUP_SPACING);
        //counterField.setTextAlignment(TextAlignment.RIGHT); TO DO
        
        shuffleButton = new Button();
        shuffleButton.setText("Shuffle");
        //shuffleButton.setLayoutY(counterField.getLayoutY() 
                //+ counterField.getHeight() + RIGHT_GROUP_SPACING);
        shuffleButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    shuffle();
                } catch (InterruptedException | FileNotFoundException ex) {
                    Logger.getLogger(SlidingPuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        newPictureButton = new Button();
        newPictureButton.setText("New Picture");
        //newPictureButton.setLayoutY(shuffleButton.getLayoutY() 
                //+ shuffleButton.getHeight() + RIGHT_GROUP_SPACING);
        newPictureButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                loadNewPicture();
            }
        });
        
        root = new Group();
        left = new Group();
        right = new VBox();
        
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
        world.printWorld();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public void shuffle() throws InterruptedException, FileNotFoundException{
        world = new World(this);
        left.getChildren().removeAll(left.getChildren());
        world.addToGroup(left);
        world.shuffle();
    }
    
    public void refreshMoves(int value){
        counterField.setText(Integer.toString(value));
    }
    
    public void loadNewPicture(){
        //TODO
    }
    
    public void endGame() throws FileNotFoundException{
        world.setMapField(SIZE - 1, SIZE - 1, new Piece(new Image(new FileInputStream("src\\images\\" 
                + Integer.toString(SIZE * SIZE - 1) + ".png")), SIZE * SIZE - 1, SIZE - 1 , SIZE - 1, world));
        left.getChildren().removeAll(left.getChildren());
        world.addToGroup(left);
    }
}
