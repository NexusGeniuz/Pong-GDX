package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.rare.pong.Pong;
import com.rare.pong.Puck;

public class MainMenu implements Screen {
    private Pong pong;
    private Puck puck;

    private Texture exitButtonNormal;
    private Texture exitButtonOver;
    private Texture play1v1ButtonNormal;
    private Texture play1v1ButtonOver;
    // TODO
    // private Texture playVsAIButtonNormal;
    // private Texture playVsAIButtonOver;
    // private Texture creditsButtonNormal;
    // private Texture creditsButtonOver;

    public MainMenu(Pong pong) {
        this.pong = pong;
        // Loading buttons
        play1v1ButtonNormal = new Texture(Gdx.files.internal("buttons/play1v1but_n.png"));
        play1v1ButtonOver = new Texture(Gdx.files.internal("buttons/play1v1but_o.png"));
        exitButtonNormal = new Texture(Gdx.files.internal("buttons/exitbut_n.png"));
        exitButtonOver = new Texture(Gdx.files.internal("buttons/exitbut_o.png"));
        //TODO
        // playVsAIButtonNormal = new Texture(Gdx.files.internal("buttons/playvsaibut_n.png));
        // playVsAIButtonOver = new Texture(Gdx.files.internal("buttons/playvsaibut_o.png));
        // creditsButtonNormal = new Texture(Gdx.files.internal("buttons/creditsbut_n.png));
        // creditsButtonOver = new Texture(Gdx.files.internal("buttons/creditsbut_o.png));

        puck = new Puck(null);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // TODO RENDER Buttons and make them clickable
        pong.batch.begin();

        // Drawing buttons
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + play1v1ButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - play1v1ButtonNormal.getWidth() / 2
            && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * .75f + play1v1ButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * .75f - play1v1ButtonOver.getHeight() / 2) {
            pong.batch.draw(play1v1ButtonOver, Gdx.graphics.getWidth() / 2 - play1v1ButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * .75f - play1v1ButtonOver.getHeight() / 2);
            if(Gdx.input.isTouched(0)){
                pong.setScreen(new Game1v1(pong));
            }
        }
        else
            pong.batch.draw(play1v1ButtonNormal, Gdx.graphics.getWidth() / 2 - play1v1ButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * .75f - play1v1ButtonNormal.getHeight() / 2);
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + exitButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - exitButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * .25f + exitButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * .25f - exitButtonNormal.getHeight() / 2) {
            pong.batch.draw(exitButtonOver, Gdx.graphics.getWidth() / 2 - exitButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * .25f - exitButtonOver.getHeight() / 2);
            if(Gdx.input.isTouched(0)){
                Gdx.app.exit();
            }
        }
        else
            pong.batch.draw(exitButtonNormal, Gdx.graphics.getWidth()/2 - exitButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*.25f - exitButtonNormal.getHeight()/2);
        //TODO
        // pong.batch.draw(playVsAIButtonNormal, Gdx.graphics.getWidth()/2 - playVsAIButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*.5f - playVsAIButtonNormal.getHeight()/2);
        // pong.batch.draw(creditsButtonNormal, Gdx.graphics.getWidth()/2 - creditsButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*.4f - creditsButtonNormal.getHeight()/2);

        pong.batch.end();

        // Puck (moving around)
        puck.glide(delta);
        puck.draw();
    }

    @Override
    public void dispose() {
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
