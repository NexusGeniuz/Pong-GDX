package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Game implements Screen {
    protected Puck puck;
    protected Pong pong;
    protected Score score;
    private ShapeRenderer shapeRenderer;
    protected Paddle leftP, rightP; // SHOULD BE INITIALIZED IN CHILD CLASSES

    public Game(Pong pong){
        this.pong = pong;
        score = new Score(pong);
        puck = new Puck(score);
        shapeRenderer = new ShapeRenderer();
        puck.checkPaddles = true;
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        score.update();
        leftP.update(delta);
        rightP.update(delta);
        puck.update(delta, leftP, rightP);

        // Drawing center line
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < Gdx.graphics.getHeight()/20f-1; i+=2){
            shapeRenderer.rect(Gdx.graphics.getWidth()/2f - 10f/2, 20f/2 + i * 20f, 10f, 20f);
        }
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        puck.dispose();
        score.dispose();
        shapeRenderer.dispose();
        leftP.dispose();
        rightP.dispose();
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
