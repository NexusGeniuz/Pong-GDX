package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rare.pong.Pong;
import com.rare.pong.Puck;

public class Credits implements Screen {
    private Pong pong;
    private Texture credits;
    private Texture backButtonNormal;
    private Texture backButtonOver;
    private Puck puck;

    public Credits(Pong pong){
        this.pong = pong;
        credits = new Texture(Gdx.files.internal("creditsScreen.png"));
        backButtonNormal = new Texture(Gdx.files.internal("buttons/backbut_n.png"));
        backButtonOver = new Texture(Gdx.files.internal("buttons/backbut_o.png"));
        puck = new Puck(null);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pong.batch.begin();
        pong.batch.draw(credits, 0, 0);

        // Back button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + backButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - backButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * .15f + backButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * .15f - backButtonNormal.getHeight() / 2) {
            pong.batch.draw(backButtonOver, Gdx.graphics.getWidth() / 2 - backButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * .15f - backButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                pong.setScreen(new MainMenu(pong));
            }
        }
        else
            pong.batch.draw(backButtonNormal, Gdx.graphics.getWidth() / 2 - backButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * .15f - backButtonNormal.getHeight() / 2);

        pong.batch.end();

        // Puck (moving around)
        puck.glide(delta);
        puck.draw();
    }

    @Override
    public void dispose() {
        credits.dispose();
        backButtonNormal.dispose();
        backButtonOver.dispose();
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
