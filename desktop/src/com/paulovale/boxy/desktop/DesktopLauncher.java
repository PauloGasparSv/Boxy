package com.paulovale.boxy.desktop;

// import javax.swing.JOptionPane;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.paulovale.boxy.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		//Setting Fullscreen
		// boolean fullscreen = false;
		// int reply = JOptionPane.showConfirmDialog(
		// 	null, 
		// 	"Tela cheia?", 
		// 	"Tamanho do jogo", 
		// 	JOptionPane.YES_NO_OPTION
		// );
		// if (reply == JOptionPane.YES_OPTION) {
		// 	fullscreen = true;
        // }

		// config.fullscreen = fullscreen;
		config.width = 960;
		config.height = 540;
		config.resizable = false;
		
		new LwjglApplication(new MyGame(), config);
	}
}
