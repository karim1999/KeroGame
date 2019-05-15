package game;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class PianoPane extends Pane {


    ArrayList<TileGroup> tileGroups= new ArrayList();
    double minSpeed= 5;
    boolean isOn;
    AnimationTimer gameLoop;
    double lastClickTime;
    int score =0;

    boolean[] buttons= {false, false, false, false};

    public PianoPane()
    {
        TileGroup tileGroup= new TileGroup(-Tile.ht);
        tileGroups.add(tileGroup);
        this.getChildren().add(tileGroup);

        this.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.V)
                buttons[0]= true;
            if(e.getCode() == KeyCode.B)
                buttons[1]= true;
            if(e.getCode() == KeyCode.N)
                buttons[2]= true;
            if(e.getCode() == KeyCode.M)
                buttons[3]= true;
        });
        this.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.V)
                buttons[0]= false;
            if(e.getCode() == KeyCode.B)
                buttons[1]= false;
            if(e.getCode() == KeyCode.N)
                buttons[2]= false;
            if(e.getCode() == KeyCode.M)
                buttons[3]= false;
        });
        isOn= true;

        lastClickTime= System.currentTimeMillis();

        gameLoop= new AnimationTimer(){

            @Override
            public void handle(long now) {
                updateGame();
            }
        };
        gameLoop.start();
    }

    private void updateGame() {
        double speed= minSpeed + score/10;
        if(isOn){
            for(int i= 0; i < tileGroups.size(); i++){
                TileGroup tileGroup= tileGroups.get(i);
                tileGroup.setTranslateY(tileGroup.getTranslateY() + speed);
                if(tileGroup.getTranslateY() >= 600){
                    System.out.println("End Game");
                    endGame();
                }

                System.out.println(tileGroup.getTranslateY());

                if(tileGroup.getTranslateY() == 0){
                    creatGroup();
                }
            }
            double currentTime= System.currentTimeMillis();
            if(currentTime - lastClickTime >= 200){
                for (int i = 0; i < buttons.length; i++){
                    if(buttons[i]){
                        if(tileGroups.get(0).clickOnTile(i)){
                            System.out.println("Correct");
                            this.getChildren().remove(tileGroups.get(0));
                            tileGroups.remove(0);
                            creatGroup();
                            score++;
                            lastClickTime= currentTime;
                        }else {
                            System.out.println("Wrong");
                            endGame();
                        }
                    }
                }

            }

        }
    }
    private void creatGroup()
    {
        double y= tileGroups.get(tileGroups.size()-1).getTranslateY()-Tile.ht;
        TileGroup newTileGroup= new TileGroup(y);
        tileGroups.add(newTileGroup);
        this.getChildren().add(newTileGroup);
    }

    private void endGame(){
        isOn= false;
        gameLoop.stop();
    }

}
