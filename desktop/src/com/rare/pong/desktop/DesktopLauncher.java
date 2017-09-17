package com.rare.pong.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.rare.pong.Pong;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = false;
		config.resizable = false;
		config.title = "Pong by rAre";
		config.addIcon("icons/pong128_icon.png", Files.FileType.Internal);
		config.addIcon("icons/pong32_icon.png", Files.FileType.Internal);
		config.addIcon("icons/pong16_icon.png", Files.FileType.Internal);
		new LwjglApplication(new Pong(), config);
	}
}
