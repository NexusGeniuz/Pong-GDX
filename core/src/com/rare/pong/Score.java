package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.rare.pong.scenes.MainMenu;

public class Score {
    private int scoreL = 0;
    private int scoreR = 0;
    private final int WIN_SCORE = 10;
    private FreeTypeFontGenerator fontGenerator80;
    private FreeTypeFontGenerator fontGenerator40;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font80;
    private BitmapFont font40;
    private Pong pong;
    private float winTextWidth;
    private float loseTextWidth;
    private float numberWidth;
    private float pressRTextWidth;
    private float pressETextWidth;
    private float toRestartTextWidth;
    private float toGoToMenuTextWidth;
    public boolean gameOver = false;

    public Score(Pong pong) {
        fontGenerator80 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/meek.ttf"));
        fontGenerator40 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/mm10.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 80;
        font80 = fontGenerator80.generateFont(fontParameter);
        this.pong = pong;
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font80, "0");
        numberWidth = layout.width;
        layout.setText(font80, "WIN");
        winTextWidth = layout.width;
        layout.setText(font80, "LOSE");
        loseTextWidth = layout.width;
        fontParameter.size = 40;
        font40 = fontGenerator40.generateFont(fontParameter);
        layout.setText(font40, "PRESS R");
        pressRTextWidth = layout.width;
        layout.setText(font40, "TO RESTART");
        toRestartTextWidth = layout.width;
        layout.setText(font40, "PRESS E");
        pressETextWidth = layout.width;
        layout.setText(font40, "TO GO TO MENU");
        toGoToMenuTextWidth = layout.width;
    }

    public void update(){
        pong.batch.begin();
        font80.draw(pong.batch, scoreL + "", Gdx.graphics.getWidth()/4f - numberWidth/2, Gdx.graphics.getHeight() * 0.97f);
        font80.draw(pong.batch, scoreR + "", Gdx.graphics.getWidth() * 0.75f - numberWidth/2, Gdx.graphics.getHeight() * 0.97f);

        if(gameOver){
            font80.draw(pong.batch, "WIN", (scoreL == WIN_SCORE ? Gdx.graphics.getWidth()/4f - winTextWidth/2 : Gdx.graphics.getWidth() * 0.75f - winTextWidth/2), Gdx.graphics.getHeight() * 0.7f);
            font80.draw(pong.batch, "LOSE", (scoreL == WIN_SCORE ? Gdx.graphics.getWidth() * 0.75f - loseTextWidth/2 : Gdx.graphics.getWidth()/4f - loseTextWidth/2), Gdx.graphics.getHeight() * 0.7f);
            font40.draw(pong.batch, "PRESS R", Gdx.graphics.getWidth() * .25f - pressRTextWidth/2, Gdx.graphics.getWidth() * 0.2f);
            font40.draw(pong.batch, "TO RESTART", Gdx.graphics.getWidth() * .75f - toRestartTextWidth/2, Gdx.graphics.getWidth() * 0.2f);
            font40.draw(pong.batch, "PRESS E", Gdx.graphics.getWidth() * .25f - pressETextWidth/2, Gdx.graphics.getWidth() * 0.1f);
            font40.draw(pong.batch, "TO GO TO MENU", Gdx.graphics.getWidth() * .75f - toGoToMenuTextWidth/2, Gdx.graphics.getWidth() * 0.1f);

            if(Gdx.input.isKeyJustPressed(Input.Keys.R)){
                reset();
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
                pong.setScreen(new MainMenu(pong));
            }
        }
        pong.batch.end();
    }

    public void scoreLeft(){
        scoreL++;
        checkScore();
    }

    public void scoreRight(){
        scoreR++;
        checkScore();
    }

    private void checkScore(){
        if(scoreL == WIN_SCORE || scoreR == WIN_SCORE) {
            // Show win/lose hud, disable puck movement, disable paddles' control
            gameOver = true;
        }
    }

    public void reset() {
        scoreL = 0;
        scoreR = 0;
        gameOver = false;
    }

    public void dispose(){
        fontGenerator80.dispose();
        fontGenerator40.dispose();
        font80.dispose();
        font40.dispose();
    }
}
