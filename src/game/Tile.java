package game;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle
{
    public static double wt = 80;
    public static double ht = 150;
    public boolean isUnique;

    public Tile(boolean isUnique, int i)
    {
        setWidth(wt);
        setHeight(ht);
        this.isUnique= isUnique;
        setX(wt*i);
//        setTranslateY(y);
        setFill((isUnique) ? Color.BLACK : Color.WHITE);
    }

}
