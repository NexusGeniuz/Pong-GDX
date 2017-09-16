package com.rare.pong;

public class PaddleAI extends Paddle {
    private Puck puck;

    public PaddleAI(float x, float y, Score score, Puck puck) {
        super(x, y, score);
        this.puck = puck;
    }

    @Override
    public void update(float dt){
        if(!score.gameOver)
            handleMovement();

        super.update(dt);
    }

    private void handleMovement(){ // AI TODO
        

        clampY();
    }
}
