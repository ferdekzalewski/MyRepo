/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzlegame;

/**
 *
 * @author Bartek
 */

import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.animation.TranslateTransition;
import javafx.util.Duration; 

public class Piece extends ImageView {
    
    private int pozX;
    private int pozY;
    private World world;
    private TranslateTransition move;
    
    public Piece(Image image, int pozX, int pozY, World world){
        super(image);
        this.pozX = pozX;
        this.pozY = pozY;
        this.world = world;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() { 
            public void handle(MouseEvent event) {
                swipe();
                world.checkWin();
            }
        });
        move = new TranslateTransition();
        move.setDuration(Duration.millis(50)); 
        move.setNode(this); 
        move.setCycleCount(1); 
        move.setAutoReverse(false); 
    }
    
    public void swipe(){
        if(pozX == world.getEmptyX()){
            if(pozY == world.getEmptyY()+1){
                move.setByX(0);
                move.setByY(-120);
                world.setEmptyY(pozY);
                pozY--;
                move.play();
                System.out.println("up");
            }
            else if(pozY == world.getEmptyY()-1){
                move.setByX(0);
                move.setByY(120);
                world.setEmptyY(pozY);
                pozY++;
                move.play();
                System.out.println("down");
            }
        }
        else if(pozY == world.getEmptyY()){
            if(pozX == world.getEmptyX()+1){
                move.setByX(-120);
                move.setByY(0);
                world.setEmptyX(pozX);
                pozX--;
                move.play();
            }
            else if(pozX == world.getEmptyX()-1){
                move.setByX(120);
                move.setByY(0);
                world.setEmptyX(pozX);
                pozX++;
                move.play();
            }
        }
    }

    public int getCurrentPoz() {
        return 4*pozX + pozY;
    }
}
