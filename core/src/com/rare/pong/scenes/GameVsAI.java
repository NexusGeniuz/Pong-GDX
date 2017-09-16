package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.rare.pong.PaddleP;
import com.rare.pong.Pong;
import com.rare.pong.Score;

public class GameVsAI implements Screen { // TODO EXTEND GAME
    private Pong pong;
    private Score score;
    private PaddleP leftP, rightP;

    public GameVsAI(Pong pong){
        this.pong = pong;
        score = new Score(pong);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        score.update();
        leftP.update(delta);
    }

    @Override
    public void dispose() {

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
