/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzlegame;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import static java.lang.Double.min;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Bartek
 */
public class SlidingPuzzleGame extends Application {
    
    public final int WIDTH = 740;
    public final int HEIGHT = 480;
    private final int RIGHT_GROUP_LEFT_MARGIN = 40;
    private final int RIGHT_GROUP_TOP_MARGIN = 40;
    private final int RIGHT_GROUP_SPACING = 40;
    public final int SIZE = 4;
    
    private Label counterLabel;
    private Label counterField;
    private Button shuffleButton;
    private Button newPictureButton;   
    
    private World world;
    private Image image;
    
    private HBox counterLine;
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
        image = new Image(new FileInputStream("src\\images\\template.png"));
        world = new World(this, image);
        
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
        shuffleButton.setOnAction((ActionEvent event) -> {
            try {
                shuffle();
            } catch (InterruptedException | FileNotFoundException ex) {
                Logger.getLogger(SlidingPuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        newPictureButton = new Button();
        newPictureButton.setText("New Picture");
        //newPictureButton.setLayoutY(shuffleButton.getLayoutY() 
                //+ shuffleButton.getHeight() + RIGHT_GROUP_SPACING);
        newPictureButton.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            configurateFileChooser(fileChooser);
            File file = fileChooser.showOpenDialog(primaryStage);
            if(file != null){
                try {
                    loadNewPicture(file);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SlidingPuzzleGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        counterLine = new HBox();
        
        root = new Group();
        left = new Group();
        right = new VBox();
        
        counterLine.getChildren().addAll(counterLabel, counterField);
        counterLine.setSpacing(RIGHT_GROUP_SPACING/2);
        
        root.getChildren().addAll(left, right);
        left.getChildren().addAll();
        world.addToGroup(left);
        right.getChildren().addAll(counterLine, shuffleButton,
                newPictureButton);
        
        right.setSpacing(RIGHT_GROUP_SPACING);
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
        world = new World(this, image);
        left.getChildren().removeAll(left.getChildren());
        world.addToGroup(left);
        world.shuffle();
    }
    
    public void refreshMoves(int value){
        counterField.setText(Integer.toString(value));
    }
    
    private static void configurateFileChooser(FileChooser fileChooser){
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
            //new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
    public void loadNewPicture(File file) throws FileNotFoundException{
        left.getChildren().removeAll(left.getChildren());
        image = new Image(new FileInputStream(file.getPath()));
        world = new World(this, image);
        world.addToGroup(left);
    }
    
    public void endGame() throws FileNotFoundException{
        world.setMapField(SIZE - 1, SIZE - 1, 
                new Piece(image, SIZE * SIZE - 1,
                SIZE - 1 , SIZE - 1, min(image.getHeight(), 
                image.getWidth())/SIZE, world));
        world.setEmptyX(SIZE+2);
        world.setEmptyY(SIZE+2);        
        left.getChildren().removeAll(left.getChildren());
        world.addToGroup(left);
    }
}
