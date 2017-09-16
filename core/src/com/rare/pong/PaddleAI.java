package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public class PaddleAI extends Paddle {
    private Puck puck;
    private int offset = 0;

    public PaddleAI(float x, float y, Score score, Puck puck) {
        super(x, y, score);
        this.puck = puck;
    }

    @Override
    public void update(float dt){
        if(!score.gameOver)
            handleMovement(dt);

        super.update(dt);
    }

    private void handleMovement(float dt){ // AI TODO
        if(y + height/2 < Gdx.graphics.getHeight()/2 && puck.getPos().y + puck.getSize()/2 > Gdx.graphics.getHeight()/2 && puck.getVel().y > 0)
            speed = maxSpeed;
        else if (y + height/2 > Gdx.graphics.getHeight()/2 && puck.getPos().y + puck.getSize()/2 < Gdx.graphics.getHeight()/2 && puck.getVel().y < 0)
            speed = -maxSpeed;
        else if(y + height/2 < Gdx.graphics.getHeight()/2 && Math.abs(y + height/2 - puck.getPos().y - puck.getSize()/2) > 30 && puck.getVel().len() < 520)
            speed = maxSpeed;
        else if (y + height/2 > Gdx.graphics.getHeight()/2 && Math.abs(y + height/2 - puck.getPos().y - puck.getSize()/2) > 30 && puck.getVel().len() < 520)
            speed = -maxSpeed;
        else {
            speed = puck.getVel().y * MathUtils.random(0.985f, 1.085f);
            speed = MathUtils.clamp(speed, -maxSpeed, maxSpeed);
        }
        y += speed * dt;
        // THE ACTUAL METHOD: MOVE TOWARDS PUCK'S Y, BUT RANDOMLY ADD SOME NERFS TO THE SPEED OF PADDLE or KEEP IT THIS WAY BUT RESET PADDLE TO THE CENTRE WHEN SOMEONE SCORES

        clampY();
    }
}
