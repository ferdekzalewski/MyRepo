/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidepuzzle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.scene.text.*;


/**
 *
 * @author Bartek
 */
public class SlidePuzzle extends Application {
    private final int WIDTH = 740;
    private final int HEIGHT = 480;
    private final int RIGHT_GROUP_LEFT_MARGIN = 40;
    private final int RIGHT_GROUP_TOP_MARGIN = 40;
    private final int RIGHT_GROUP_SPACING = 40;

    @Override
    public void start(Stage primaryStage) {
        
        Group root = new Group();
        Group left = new Group();
        Group right = new Group();
        
        //RIGHT
        
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
        
        Label logLabel = new Label("Log:");
        logLabel.setLayoutY(newPictureButton.getLayoutY() 
                + newPictureButton.getHeight() + RIGHT_GROUP_SPACING);
        
        TextFlow logFlow = new TextFlow();
        logFlow.setLayoutY(logLabel.getLayoutY() 
                + logLabel.getHeight() + RIGHT_GROUP_SPACING);

        root.getChildren().addAll(left, right);
        left.getChildren().addAll();
        right.getChildren().addAll(counterLabel, counterField, shuffleButton,
                newPictureButton, logLabel, logFlow);
        
        right.setLayoutX(HEIGHT + RIGHT_GROUP_LEFT_MARGIN);
        
        //LEFT
        
        Image[] arrayOfImages;
        arrayOfImages = new Image[16];
        int index = 0;
        for(Image pic : arrayOfImages){
            pic = new Image("images\\" + Integer.toString(index) + ".png");
            index++;
        }
        
        PuzzlePiece[] arrayOfPuzzles;
        arrayOfPuzzles = new PuzzlePiece[16];
        for(PuzzlePiece piece : arrayOfPuzzles){
            
        }

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        
        primaryStage.setTitle("Sliding Puzzles");
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

}
