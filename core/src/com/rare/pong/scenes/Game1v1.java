package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.rare.pong.Paddle;
import com.rare.pong.Puck;

public class Game1v1 implements Screen {
    private Paddle leftP, rightP;
    private Puck puck;

    public Game1v1() {
        puck = new Puck();
        leftP = new Paddle(15, Gdx.graphics.getHeight()/2 - 100/2, Input.Keys.A, Input.Keys.Z);
        rightP = new Paddle(Gdx.graphics.getWidth() - 15*2, Gdx.graphics.getHeight()/2 - 100/2, Input.Keys.K, Input.Keys.M);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        leftP.update(delta);
        rightP.update(delta);
        puck.update(delta, leftP, rightP);
    }

    @Override
    public void dispose() {
        leftP.dispose();
        rightP.dispose();
        puck.dispose();
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
