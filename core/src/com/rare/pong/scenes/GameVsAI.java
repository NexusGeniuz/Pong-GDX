package com.rare.pong.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rare.pong.*;

public class GameVsAI extends Game {
    public GameVsAI(Pong pong){
        super(pong);
        leftP = new PaddleP(15, Gdx.graphics.getHeight()/2 - 100/2, Input.Keys.A, Input.Keys.Z, score);
        rightP = new PaddleAI(Gdx.graphics.getWidth() - 15f*2, Gdx.graphics.getHeight()/2f - 100f/2, score, puck);
    }
}
