package pkg.playground.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import pkg.playground.Plant.PlantManager;


public class handleButtons extends Actor {
    private Texture texture;
    private TextureRegion upRegion;
    private TextureRegion downRegion;
    private BitmapFont font;
    private TextButton.TextButtonStyle style;
    private Skin skin;
    Stage stage;
    PlantManager plantManager;
    TextButton button;
    boolean state;
    public handleButtons(Stage stage, PlantManager plantManager){
        this.stage = stage;
        this.plantManager = plantManager;
        texture = new Texture(Gdx.files.internal("Button_400x200x2.jpg"));
        upRegion = new TextureRegion(texture, 400, 200);
        downRegion = new TextureRegion(texture, 400,0,400,200);
        font = new BitmapFont();
        style = new TextButton.TextButtonStyle();
        skin = new Skin();
        skin.add("up", upRegion);
        skin.add("down", downRegion);
        style.font = font;
        style.up = skin.getDrawable("up");
        style.down = skin.getDrawable("down");

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        texture = new Texture(pixmap); //remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);


        button = new TextButton("Grow", style);
        button.setPosition(50, 150);
        button.setSize(200,50);
        stage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                growPlants();
            }
        });


        button = new TextButton("Rotate", style);
        button.setPosition(50, 100);
        button.setSize(200,50);
        stage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                rotatePlants();
            }
        });


        button = new TextButton("Fork", style);
        button.setPosition(50, 50);
        button.setSize(200,50);
        stage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                forkPlants();
            }
        });


        button = new TextButton("Flower", style);
        button.setPosition(50, 0);
        button.setSize(200,50);
        stage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                flowerPlants();
            }
        });
    }

    private void growPlants () {
        plantManager.growPlants(50);
    }
    private void rotatePlants () {
        plantManager.rotatePlants(36);
    }
    private void forkPlants () {
        plantManager.forkPlants(50, 3, 45, 0);
    }
    private void flowerPlants () {
        plantManager.flowerPlants();
    }


    @Override
    public void draw (Batch batch, float parentAlpha) {
        button.draw(batch, parentAlpha);
    }

}
