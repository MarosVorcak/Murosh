package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(144);
        config.setWindowedMode(1024, 728);
        config.setResizable(false);
        config.setTitle("Semestralna praca - Murosh the Dungeon Conqueror");
        new Lwjgl3Application(new MainGame(), config);
    }
}
