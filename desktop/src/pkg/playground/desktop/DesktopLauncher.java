package pkg.playground.desktop;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import pkg.playground.Playground;

public class DesktopLauncher {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Playground");
		config.setWindowedMode(1536, 896);

		new Lwjgl3Application(new Playground(1536, 896), config);
	}
}
