package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rare.pong.Paddle;
import com.rare.pong.Pong;
import com.rare.pong.Puck;
import com.rare.pong.Score;

public class Game1v1 implements Screen {
    private Paddle leftP, rightP;
    private Puck puck;
    private Pong pong;
    private Score score;
    private ShapeRenderer shapeRenderer;

    public Game1v1(Pong pong) {
        this.pong = pong;
        score = new Score(pong);
        puck = new Puck(score);
        leftP = new Paddle(15, Gdx.graphics.getHeight()/2 - 100/2, Input.Keys.A, Input.Keys.Z, score);
        rightP = new Paddle(Gdx.graphics.getWidth() - 15f*2, Gdx.graphics.getHeight()/2f - 100f/2, Input.Keys.K, Input.Keys.M, score);
        puck.checkPaddles = true;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        score.update();
        leftP.update(delta);
        rightP.update(delta);
        puck.update(delta, leftP, rightP);

        // Center line
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < Gdx.graphics.getHeight()/20f-1; i+=2){
            shapeRenderer.rect(Gdx.graphics.getWidth()/2f - 10f/2, 20f/2 + i * 20f, 10f, 20f);
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        leftP.dispose();
        rightP.dispose();
        puck.dispose();
        score.dispose();
        shapeRenderer.dispose();
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
