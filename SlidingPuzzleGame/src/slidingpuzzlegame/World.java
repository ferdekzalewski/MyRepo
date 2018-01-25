/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidingpuzzlegame;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private int moves;
    public World(SlidingPuzzleGame game) throws FileNotFoundException{
        this.game = game;
        this.moves = 0;
        map = new Piece[game.SIZE][game.SIZE];
        for(int i = 0; i<game.SIZE; i++){
            for(int j = 0; j<game.SIZE; j++){
            map[i][j] = new Piece(new Image(new FileInputStream("src\\images\\" + Integer.toString(4*i+j) + ".png")), game.SIZE*i+j, j, i, this);
            map[i][j].setX(j*120);
            map[i][j].setY(i*120);
            }
        }
        map[game.SIZE - 1][game.SIZE - 1] = null;
        emptyX = game.SIZE - 1;
        emptyY = game.SIZE - 1;
    }
    public void addToGroup(Group group){
        for(Piece[] raw : map){
            for(Piece element : raw){
                if(element != null)
                    group.getChildren().add(element);
            }
        }
    }

    public void checkWin() {
        for(Piece[] raw : map){
            for(Piece element : raw){
                if(element != null){
                    if(!element.isCorrect()){
                        System.out.println("unsorted");
                        return;
                    }
                }
            }
        }
        System.out.println("gg");
        try {
            game.endGame();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void shuffle() throws InterruptedException{
        Random randomGenerator = new Random();
        ArrayList<Piece> neighbors;
        for(int i = 0; i<500; i++){
            neighbors = getNeighbors();
            neighbors.get(randomGenerator.nextInt(neighbors.size())).swipe(true);
            System.out.println(i+1);
            printWorld();
        }
        resetWorld();
        game.refreshMoves(0);
    }
    
    private ArrayList<Piece> getNeighbors(){
        ArrayList<Piece> neighbors = new ArrayList<>();
        if(emptyX < 3){
            neighbors.add(map[emptyY][emptyX + 1]);
        }
        if(emptyX > 0){
            neighbors.add(map[emptyY][emptyX - 1]);
        }
        if(emptyY < 3){
            neighbors.add(map[emptyY + 1][emptyX]);
        }
        if(emptyY > 0){
            neighbors.add(map[emptyY - 1][emptyX]);
        }
        return neighbors;
    }
    
    public void setMapField(int y, int x, Piece piece){
        map[y][x] = piece;
    }
    
    public int getIndexXOfFieldInMap(Piece wanted){
        for (int i = 0; i< 4; i++){
            for (int j = 0; j<4; j++){
                if(map[i][j] == wanted)
                    return  j;
            }
        }
        return -1;
    }
    
    public int getIndexYOfFieldInMap(Piece wanted){
        for (int i = 0; i< 4; i++){
            for (int j = 0; j<4; j++){
                if(map[i][j] == wanted)
                    return  i;
            }
        }
        return -1;
    }
    
    public void resetWorld(){
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                if(map[i][j]!=null){
                    map[i][j].setX(j*120);
                    map[i][j].setY(i*120);
                }
            }
        }
    }
    
    public void printWorld(){
        for(Piece[] raw : map){
            for(Piece element : raw){
                if (element == null)
                    System.out.print("X\t");
                else
                    System.out.print(element.getNumber() + "\t");
            }
            System.out.print("\n");
        }
    }
    
    public void increaseMoves(){
        moves++;
        game.refreshMoves(moves);
    }
}
