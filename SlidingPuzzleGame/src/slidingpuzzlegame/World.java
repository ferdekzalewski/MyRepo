/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzlegame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 *
 * @author Bartek
 */
public class World {
    private Piece[][] map;
    private final SlidingPuzzleGame game;
    private int emptyX;
    private int emptyY;
    public World(SlidingPuzzleGame game) throws FileNotFoundException{
        this.game = game;
        map = new Piece[4][4];
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
            map[i][j] = new Piece(new Image(new FileInputStream("src\\images\\" + Integer.toString(4*i+j) + ".png")), j, i, this);
            map[i][j].setX(j*120);
            map[i][j].setY(i*120);
            }
        }
        map[3][3].setVisible(false);
        emptyX = 3;
        emptyY = 3;
    }
    public void addToGroup(Group group){
        for(Piece[] raw : map){
            for(Piece element : raw){
                group.getChildren().add(element);
            }
        }
    }

    public void checkWin() {
        for (int i = 0; i< 4; i++){
            for (int j = 0; j<4; j++){
                if (map[i][j].getCurrentPoz() != 4*i + j)
                    return;
            }
        }
        game.endGame();
    }
    
    public int getEmptyX(){
        return emptyX;
    }
    
    public void setEmptyX(int empty){
        this.emptyX = empty;
    }
    
    public int getEmptyY(){
        return emptyY;
    }
    
    public void setEmptyY(int empty){
        this.emptyY = empty;
    }
}
