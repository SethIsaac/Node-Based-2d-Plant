package pkg.playground;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import pkg.playground.Plant.PlantManager;
import pkg.playground.Screen.ScreenManager;

public class Playground extends ApplicationAdapter {
	ScreenManager screenManager;
	PlantManager plantManager;

	int window_Width, window_Height;


	public Playground (int window_Width, int window_Height) {
		this.window_Width = window_Width;
		this.window_Height = window_Height;

	}
	@Override
	public void create () {
		Gdx.gl.glClearColor(.15f, .3f, .25f, .3f);
		plantManager = new PlantManager();
		screenManager = new ScreenManager(plantManager);
		plantManager.basicPlants(window_Width);
	}

	@Override
	public void resize (int width, int height) {
		screenManager.resize(width, height);
	}


	@Override
	public void render () {
		screenManager.update();
		plantManager.renderPlants(screenManager);

	}
	
	@Override
	public void dispose () {
		screenManager.dispose();

	}
}
