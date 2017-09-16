package com.rare.pong;

import com.badlogic.gdx.Gdx;

public class PaddleP extends Paddle {
    private int upKey, downKey;

    public PaddleP(float x, float y, int upKey, int downKey, Score score) {
        super(x, y, score);
        this.upKey = upKey;
        this.downKey = downKey;
    }

    @Override
    public void update(float dt){
        if(!score.gameOver)
            handleInput(dt);

        super.update(dt);
    }

    private void handleInput(float dt){
        if(Gdx.input.isKeyPressed(upKey)){
            y += speed * dt;
        } else if(Gdx.input.isKeyPressed(downKey)){
            y -= speed * dt;
        }

        clampY();
    }
}
