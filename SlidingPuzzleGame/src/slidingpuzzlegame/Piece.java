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

import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration; 

public class Piece extends ImageView {
    
    private final int pozX;
    private final int pozY;
    private final int number;
    private final World world;
    private final TranslateTransition move;
    private boolean isShuffling;
    
    public Piece(Image image, int number, int pozX, int pozY, double size, World world){
        super(image);
        this.number = number;
        this.pozX = pozX;
        this.pozY = pozY;
        this.world = world;
        this.setViewport(new Rectangle2D(
                    pozX * size,
                    pozY * size,
                    size,
                    size));
        this.setFitHeight(size);
        this.setFitWidth(size);
        this.setX(pozX*size);
        this.setY(pozY*size);
        this.setOnMouseClicked((MouseEvent event) -> {
            swipe(false);
            world.checkWin();
        });
        move = new TranslateTransition();
        move.setDuration(Duration.millis(150)); 
        move.setNode(this); 
        move.setCycleCount(1); 
        move.setAutoReverse(false);
    }
    
    public void swipe(boolean isShuffling){
        int currentY = world.getIndexYOfFieldInMap(this);
        int currentX = world.getIndexXOfFieldInMap(this);
        if(currentX == world.getEmptyX()){
            if(currentY == world.getEmptyY()+1){
                //System.out.println("up");
                world.setMapField(world.getEmptyY(), world.getEmptyX(), this);
                world.setEmptyY(world.getEmptyY()+1);
                world.setMapField(world.getEmptyY(), world.getEmptyX(), null);
                if(!isShuffling){
                    move.setByX(0);
                    move.setByY(-120);
                    move.play();
                    world.increaseMoves();
                }
                //System.out.println("up");
            }
            else if(currentY == world.getEmptyY()-1){
                //System.out.println("down");
                world.setMapField(world.getEmptyY(), world.getEmptyX(), this);
                world.setEmptyY(world.getEmptyY()-1);
                world.setMapField(world.getEmptyY(), world.getEmptyX(), null);
                if(!isShuffling){
                    move.setByX(0);
                    move.setByY(120);
                    move.play();
                    world.increaseMoves();
                }
                //System.out.println("down");
            }
        }
        else if(currentY == world.getEmptyY()){
            if(currentX == world.getEmptyX()+1){
                //System.out.println("left");
                world.setMapField(world.getEmptyY(), world.getEmptyX(), this);
                world.setEmptyX(world.getEmptyX()+1);
                world.setMapField(world.getEmptyY(), world.getEmptyX(), null);
                if(!isShuffling){
                    move.setByX(-120);
                    move.setByY(0);
                    move.play();
                    world.increaseMoves();
                }
                //System.out.println("left");
            }
            else if(currentX == world.getEmptyX()-1){
                //System.out.println("right");
                world.setMapField(world.getEmptyY(), world.getEmptyX(), this);
                world.setEmptyX(world.getEmptyX()-1);
                world.setMapField(world.getEmptyY(), world.getEmptyX(), null);
                if(!isShuffling){
                    move.setByX(120);
                    move.setByY(0);
                    move.play();
                    world.increaseMoves();
                }
                //System.out.println("right");
            }
        }
    }

    public boolean isCorrect() {
        if(world.getIndexYOfFieldInMap(this) == pozY 
                && world.getIndexXOfFieldInMap(this) == pozX)
            return true;
        return false;
    }
    
    public String getNumber(){
        return Integer.toString(number);
    }
}
