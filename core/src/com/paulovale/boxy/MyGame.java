package com.paulovale.boxy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.paulovale.boxy.stages.TestStage;
import com.paulovale.boxy.utils.In;

public class MyGame extends Game {
	public static Preferences prefs;

	@Override
	public void create () {
		loadPreferences();
		In.loadController();
		setScreen(new TestStage());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	//Loading the saved data
	public void loadPreferences(){
		prefs = Gdx.app.getPreferences("paulovale-boxy");
		prefs.putBoolean("visitedfirst", false);
		prefs.flush();
	}
}
