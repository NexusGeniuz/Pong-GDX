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
    private Texture creditsButtonNormal;
    private Texture creditsButtonOver;
    private Texture playVsAIButtonNormal;
    private Texture playVsAIButtonOver;
    private Texture pongLogo;

    private final float PONGLOGO_Y_PERCENT = .75f;
    private final float PLAY1V1BUTTON_Y_PERCENT = .6f;
    private final float PLAYVSAI_Y_PERCENT = .4f;
    private final float CREDITS_Y_PERCENT = .222f;
    private final float EXIT_Y_PERCENT = .08f;

    public MainMenu(Pong pong) {
        this.pong = pong;
        // Loading buttons
        play1v1ButtonNormal = new Texture(Gdx.files.internal("buttons/play1v1but_n.png"));
        play1v1ButtonOver = new Texture(Gdx.files.internal("buttons/play1v1but_o.png"));
        creditsButtonNormal = new Texture(Gdx.files.internal("buttons/creditsbut_n.png"));
        creditsButtonOver = new Texture(Gdx.files.internal("buttons/creditsbut_o.png"));
        exitButtonNormal = new Texture(Gdx.files.internal("buttons/exitbut_n.png"));
        exitButtonOver = new Texture(Gdx.files.internal("buttons/exitbut_o.png"));
        playVsAIButtonNormal = new Texture(Gdx.files.internal("buttons/playvsaibut_n.png"));
        playVsAIButtonOver = new Texture(Gdx.files.internal("buttons/playvsaibut_o.png"));
        pongLogo = new Texture(Gdx.files.internal("ponglogo.png"));

        puck = new Puck(null);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pong.batch.begin();
        pong.batch.draw(pongLogo, Gdx.graphics.getWidth() / 2 - pongLogo.getWidth() / 2, Gdx.graphics.getHeight() * PONGLOGO_Y_PERCENT);

        // Drawing buttons
        // Play 1v1 button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + play1v1ButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - play1v1ButtonNormal.getWidth() / 2
            && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * PLAY1V1BUTTON_Y_PERCENT + play1v1ButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * PLAY1V1BUTTON_Y_PERCENT - play1v1ButtonNormal.getHeight() / 2) {
            pong.batch.draw(play1v1ButtonOver, Gdx.graphics.getWidth() / 2 - play1v1ButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * PLAY1V1BUTTON_Y_PERCENT - play1v1ButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                pong.setScreen(new Game1v1(pong));
            }
        }
        else
            pong.batch.draw(play1v1ButtonNormal, Gdx.graphics.getWidth() / 2 - play1v1ButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * PLAY1V1BUTTON_Y_PERCENT - play1v1ButtonNormal.getHeight() / 2);
        
        // Play vs AI button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + playVsAIButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - playVsAIButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * PLAYVSAI_Y_PERCENT + playVsAIButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * PLAYVSAI_Y_PERCENT - playVsAIButtonNormal.getHeight() / 2) {
            pong.batch.draw(playVsAIButtonOver, Gdx.graphics.getWidth() / 2 - playVsAIButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * PLAYVSAI_Y_PERCENT - playVsAIButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                pong.setScreen(new GameVsAI(pong));
            }
        }
        else
            pong.batch.draw(playVsAIButtonNormal, Gdx.graphics.getWidth() / 2 - playVsAIButtonNormal.getWidth() / 2, Gdx.graphics.getHeight() * PLAYVSAI_Y_PERCENT - playVsAIButtonNormal.getHeight() / 2);

        // Credits button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + creditsButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - creditsButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * CREDITS_Y_PERCENT + creditsButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * CREDITS_Y_PERCENT - creditsButtonNormal.getHeight() / 2){
            pong.batch.draw(creditsButtonOver, Gdx.graphics.getWidth()/2 - creditsButtonOver.getWidth()/2, Gdx.graphics.getHeight()*CREDITS_Y_PERCENT - creditsButtonOver.getHeight()/2);
            if(Gdx.input.justTouched()){
                pong.setScreen(new Credits(pong));
            }
        } else
            pong.batch.draw(creditsButtonNormal, Gdx.graphics.getWidth()/2 - creditsButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*CREDITS_Y_PERCENT - creditsButtonNormal.getHeight()/2);

        // Exit button
        if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2 + exitButtonNormal.getWidth() / 2 && Gdx.input.getX() > Gdx.graphics.getWidth() / 2 - exitButtonNormal.getWidth() / 2
                && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight() * EXIT_Y_PERCENT + exitButtonNormal.getHeight() / 2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight() * EXIT_Y_PERCENT - exitButtonNormal.getHeight() / 2) {
            pong.batch.draw(exitButtonOver, Gdx.graphics.getWidth() / 2 - exitButtonOver.getWidth() / 2, Gdx.graphics.getHeight() * EXIT_Y_PERCENT - exitButtonOver.getHeight() / 2);
            if(Gdx.input.justTouched()){
                Gdx.app.exit();
            }
        }
        else
            pong.batch.draw(exitButtonNormal, Gdx.graphics.getWidth()/2 - exitButtonNormal.getWidth()/2, Gdx.graphics.getHeight()*EXIT_Y_PERCENT - exitButtonNormal.getHeight()/2);

        pong.batch.end();

        // Puck (moving around)
        puck.glide(delta);
        puck.draw();
    }

    @Override
    public void dispose() {
        puck.dispose();
        exitButtonNormal.dispose();
        exitButtonOver.dispose();
        play1v1ButtonNormal.dispose();
        play1v1ButtonOver.dispose();
        creditsButtonNormal.dispose();
        creditsButtonOver.dispose();
        playVsAIButtonNormal.dispose();
        playVsAIButtonOver.dispose();
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
