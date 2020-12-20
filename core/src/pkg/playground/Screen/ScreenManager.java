package pkg.playground.Screen;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.GL20;
import pkg.playground.Plant.PlantManager;
import pkg.playground.Renderable;
import space.earlygrey.shapedrawer.ShapeDrawer;


import java.awt.*;
import java.util.ArrayList;



public class ScreenManager implements Disposable {
    PlantManager plantManager;
    ShapeRenderer shapeRenderer;
    ShapeDrawer drawer;
    SpriteBatch batch;
    Stage stage;


    boolean hasPrinted = false; // for debug

    private Texture texture;
    private TextureRegion upRegion;
    private TextureRegion downRegion;
    private BitmapFont font;
    private TextButton.TextButtonStyle style;
    private Skin skin;
    private handleButtons handleButtons;
    TextureRegion region;
    TextButton button;

    public ScreenManager(PlantManager plantManager){
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        //stage = new Stage(new ExtendViewport(640, 480, 800, 480));
        stage = new Stage(new ScreenViewport());

        handleButtons = new handleButtons(stage, plantManager);
        texture = new Texture(Gdx.files.internal("Button_400x200x2.jpg"));
        region = new TextureRegion(texture,0,0,0,0);
        drawer = new ShapeDrawer(batch, region);
        Gdx.input.setInputProcessor(stage);


    }
    public void resize (int width, int height) {
        stage.getViewport().update(width, height);
    }

    public void update () {
        float delta = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    public void render (Renderable obj){
        ArrayList<Vector2[]> renderData = obj.getRenderData();
        int temp = 0;
        batch.begin();
        for (Vector2[] pair : renderData) {
            if (!hasPrinted) System.out.println("Render: " + pair[0] + " -> " + pair[1]);
            drawer.setColor(Color.BLACK);
            drawer.line(pair[0], pair[1], 4);


            temp++;
        }
        batch.end();
        hasPrinted = true;
    }

    public void setPlantManager (PlantManager plantManager) {
        this.plantManager = plantManager;
    }


    @Override
    public void dispose() {
        stage.dispose();
        shapeRenderer.dispose();
        batch.dispose();
    }
}

//