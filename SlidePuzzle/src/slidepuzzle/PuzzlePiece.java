/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slidepuzzle;

import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Bartek
 */
public class PuzzlePiece extends ImageView{
    private int currentPoz;

    PuzzlePiece(Image image, int Poz) {
        super(image);
        this.currentPoz = Poz;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() { 
            public void handle(MouseEvent event) {
                swipe();
            }
        });
    }
    public void swipe(){
        
    }
}
