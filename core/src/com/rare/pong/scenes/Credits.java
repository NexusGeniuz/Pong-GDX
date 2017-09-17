package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rare.pong.Pong;
import com.rare.pong.Puck;

public class Credits implements Screen {
    private Pong pong;
    private Texture credits;
    private Texture backButtonNormal;
    private Texture backButtonOver;
    private Texture toggleMusicBox;
    private Texture toggleMusicTick;
    private Puck puck;

    public Credits(Pong pong){
        this.pong = pong;
        credits = new Texture(Gdx.files.internal("screens/creditsScreen.png"));
        backButtonNormal = new Texture(Gdx.files.internal("buttons/backbut_n.png"));
        backButtonOver = new Texture(Gdx.files.internal("buttons/backbut_o.png"));
        toggleMusicBox = new Texture(Gdx.files.internal("ui elements/checkbox.png"));
        toggleMusicTick = new Texture(Gdx.files.internal("ui elements/tick.png"));
        puck = new Puck(null);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pong.batch.begin();
        pong.batch.draw(credits, 0, 0);
        pong.batch.draw(toggleMusicBox, 760, 125);
        if(pong.music.isPlaying())
            pong.batch.draw(toggleMusicTick, 765, 130);

        // Back button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + backButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - backButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * .08f + backButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * .08f - backButtonNormal.getHeight() / 2) {
            pong.batch.draw(backButtonOver, Gdx.graphics.getWidth() / 2 - backButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * .08f - backButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                pong.setScreen(new MainMenu(pong));
            }
        }
        else
            pong.batch.draw(backButtonNormal, Gdx.graphics.getWidth() / 2 - backButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * .08f - backButtonNormal.getHeight() / 2);

        // Toggle music
        if(Gdx.input.getX() < 760 + toggleMusicBox.getWidth() && Gdx.input.getX() > 760 && Gdx.graphics.getHeight() - Gdx.input.getY() < 130 + toggleMusicBox.getHeight() && Gdx.graphics.getHeight() - Gdx.input.getY() > 130) {
            if (Gdx.input.justTouched()) {
                if (pong.music.isPlaying())
                    pong.music.stop();
                else {
                    pong.music.setPosition(0);
                    pong.music.play();
                }
            }
        }

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
