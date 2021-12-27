package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.util.Random;

public class App extends Application {
    private GridPane grid;
    private AbstractWorldMap map;
    private Stage primarystage;
    private SimulationEngine engine;
    private Thread engineThread;
    private int moveDelay;
    private int startEnergy;
    private int moveEnergy;
    private int plantEnergy;
    private int amount;
    private double jungleratio;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        moveDelay = 300;
        startEnergy=40;
        moveEnergy=1;
        plantEnergy=3;
        amount=30;
        jungleratio= 0.1;
        //tu można wybrać jaką mapę się tworzy - wystarczy podmienić WalllessMap na ConstantMap.
        map= new ConstantMap(jungleratio,25,25);
        for (int i=0;i<amount;i++){
            Vector2d pos=new Vector2d(new Random().nextInt(map.getRightTopCorner().x),new Random().nextInt(map.getRightTopCorner().y));
            if (map.objectAt(pos) instanceof Animal){
                i--;
            }
            else {
                map.place(new Animal(map, pos, startEnergy, new Genes()));
            }
        }
        grid = new GridPane();
        primarystage = primaryStage;
        engine = new SimulationEngine(map,this, moveDelay,plantEnergy,moveEnergy,startEnergy);
        engineThread = new Thread(engine);
        engineThread.start();
    }
    public void DrawScene(){
        Platform.runLater(() -> {
            GridPane Scene= new GridPane();
            grid.getChildren().clear();
            this.grid = new GridPane();
            createAxes(grid,map);
            addObjects(grid,map);
            Scene.getChildren().add(grid);
            grid.setGridLinesVisible(true);
            primarystage.setScene(new Scene(Scene,700,700));
            primarystage.show();
        });
    }
    public void createAxes(GridPane grid, IWorldMap map){
        Integer minX = map.getLeftBottomCorner().x;
        Integer minY = map.getLeftBottomCorner().y;
        Integer maxX = map.getRightTopCorner().x;
        Integer maxY = map.getRightTopCorner().y;

        Label yx = new Label("y/x");
        grid.add(yx, 0, 0,1,1);
        GridPane.setHalignment(yx, HPos.CENTER);

        grid.getColumnConstraints().add(new ColumnConstraints(25));
        grid.getRowConstraints().add(new RowConstraints(25));
        for(Integer i = minX; i<= maxX;i++ ){
            Label label = new Label(i.toString());
            grid.add(label, i-minX+1, 0,1,1);
            grid.getColumnConstraints().add(new ColumnConstraints(25));
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for(Integer i = maxY; i >= minY;i--){
            Label label = new Label(i.toString());
            grid.add(label, 0, maxY-i+1,1,1);
            grid.getRowConstraints().add(new RowConstraints(25));
            GridPane.setHalignment(label, HPos.CENTER);
        }
    }
    public void addObjects(GridPane grid, IWorldMap map){
        Integer minX = map.getLeftBottomCorner().x;
        Integer minY = map.getLeftBottomCorner().y;
        Integer maxX = map.getRightTopCorner().x;
        Integer maxY = map.getRightTopCorner().y;
        for(Integer x = minX; x<=maxX ; x++){
            for (Integer y = minY; y<=maxY ; y++){
                Vector2d pos = new Vector2d(x,y);
                ImageView imgview=new ImageView();
                if (map.isOccupied(pos)){
                    Object object = map.objectAt(pos);
                    if( object instanceof Animal){
                        GuiElementBox img= new GuiElementBox(map.getImage((Animal) object),(Animal) object);
                        grid.add(img.vbox, x-minX+1, maxY+1-y,1,1);
                        GridPane.setHalignment(img.vbox, HPos.CENTER);
                    }
                }
                else if(map.objectAt(pos) instanceof Grass){
                    Image image= new Image("/grass.png");
                    imgview.setImage(image);
                    imgview.setFitWidth(20);
                    imgview.setFitHeight(20);
                    Label position= new Label("Grass");
                    position.setFont(new Font("Serif", 10));
                    VBox vbox= new VBox();
                    vbox.getChildren().addAll(imgview,position);
                    vbox.setAlignment(Pos.CENTER);
                    vbox.setSpacing(-7);
                    grid.add(vbox, x-minX+1, maxY+1-y,1,1);
                    GridPane.setHalignment(vbox, HPos.CENTER);

                }
            }
        }
    }
}
