package com.rare.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.rare.pong.scenes.Game1v1;

public class Pong extends Game {
	@Override
	public void create () {
		setScreen(new Game1v1());
	}

	@Override
	public void render () {
		screen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		screen.dispose();
	}
}
