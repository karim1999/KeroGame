package game;

import javafx.scene.Group;

import java.util.Random;

public class TileGroup extends Group {
    public Tile tiles[]= new Tile[4];

    TileGroup(double y){
        int random= new Random().nextInt(4);
        for (int i= 0; i < tiles.length; i++){
            boolean isUnique= false;
            if(i == random)
                isUnique= true;
            tiles[i]= new Tile(isUnique, i);
            setTranslateY(y);
            this.getChildren().add(tiles[i]);
        }
    }
    public boolean clickOnTile(int i){
        if(tiles[i].isUnique)
            return true;
        return false;
    }
}
