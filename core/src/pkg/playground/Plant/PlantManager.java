package pkg.playground.Plant;

import com.badlogic.gdx.math.Vector2;
import pkg.playground.Screen.ScreenManager;

import java.util.ArrayList;

public class PlantManager {
    private ArrayList<PlantObject> plantList;
    public PlantManager () {
        plantList = new ArrayList<>();
    }

    public void createPlant (float x, float y) {
        PlantObject plant = new PlantObject(new Vector2(x, y));
        plantList.add(plant);
        System.out.println("PlantObject created - Index: " + (plantList.size()-1));
    }

    public void growPlants(int len) {
        for(PlantObject plant : plantList) {
            plant.growLastLayer(len);
        }
    }

    public void forkPlants(int len, int forks, float spread, int offset) {
        for(PlantObject plant : plantList) {
            plant.forkLastLayer(len, forks, spread);
            plant.rotateLastLayer(-spread * ((forks - 1) / 2f ) + offset);

        }
    }

    public void rotatePlants(float degree) {
        for(PlantObject plant : plantList) {
            plant.rotateLastLayer(degree);
        }
    }

    public void flowerPlants() {
        for(PlantObject plant : plantList) {
            plant.forkLastLayer(10, 72, 17);
            plant.growLastLayer(1);
        }
    }
    public void renderPlants(ScreenManager screenManager) {
        for(PlantObject plant : plantList) {
            screenManager.render(plant);
        }
    }
    public void basicPlants (int window_Width) {

        createPlant(window_Width/2f, 50);

    }
}
