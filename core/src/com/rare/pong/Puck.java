package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Puck {
    private Vector2 pos = new Vector2(Gdx.graphics.getWidth()/2f - 15f/2f, Gdx.graphics.getHeight()/2f - 15f/2f); // Spawn at the center
    private Vector2 vel;
    private float size = 15f;
    private int maxSpeed = 1200;
    private int minSpeed = 500;
    private float speed;
    private float accelerationPercent = 1.002f;
    public boolean checkPaddles = false;
    private ShapeRenderer shapeRenderer;
    private Score score;
    private Sound blip1;
    private Sound blip2;
    private Sound blip3;
    private final float soundVolume = 0.2f;

    public Puck(Score score){
        reset();
        shapeRenderer = new ShapeRenderer();
        this.score = score;
        blip1 = Gdx.audio.newSound(Gdx.files.internal("sounds/blip1.wav"));
        blip2 = Gdx.audio.newSound(Gdx.files.internal("sounds/blip2.wav"));
        blip3 = Gdx.audio.newSound(Gdx.files.internal("sounds/blip3.wav"));
    }

    public void update(float dt, Paddle leftP, Paddle rightP){
        if(!score.gameOver) {
            glide(dt);

            if(checkPaddles)
                checkCollisions(leftP, rightP);

            // Accelerating
            if (vel.len() <= maxSpeed) {
                vel.scl(accelerationPercent, accelerationPercent);
                speed *= accelerationPercent;
            }
        }

        // Drawing the puck
        draw();
    }

    public void draw(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(pos.x, pos.y, size, size);
        shapeRenderer.end();
    }

    public void glide(float dt){
        pos.add(new Vector2(vel.x * dt, vel.y * dt)); // Applying velocity to the position of the puck

        if(checkPaddles) { // If checkPaddles is enabled then check if the puck goes off the horizontal sides of the scene
            if (pos.x <= -size) { // LEFT
                reset();
                score.scoreRight();
            } else if (pos.x >= Gdx.graphics.getWidth()) { // RIGHT
                reset();
                score.scoreLeft();
            }
        } else {
            // Bouncing off horizontal edges of the window
            if (pos.x <= 0f) { // LEFT
                vel.scl(-1f, 1f);
                pos.set(0f, pos.y);
                playSound();
            } else if (pos.x >= Gdx.graphics.getWidth() - size) { // RIGHT
                vel.scl(-1f, 1f);
                pos.set(Gdx.graphics.getWidth() - size, pos.y);
                playSound();
            }
        }

        // Bouncing off vertical edges of the window
        if(pos.y <= 0f) {
            vel.scl(1f, -1f);
            pos.set(pos.x, 0f);
            playSound();
        } else if(pos.y >= Gdx.graphics.getHeight() - size){
            vel.scl(1f, -1f);
            pos.set(pos.x, Gdx.graphics.getHeight() - size);
            playSound();
        }
    }

    private void checkCollisions(Paddle leftP, Paddle rightP){
        if(pos.y <= leftP.getY() + leftP.getHeight() && pos.y + size >= leftP.getY() && pos.x <= leftP.getX() + leftP.getWidth() && pos.x + size >= leftP.getX() + leftP.getWidth()/2f) {
            // Bouncing off with some angle depending on the certain place of the paddle
            float diff = (pos.y + size/2f) - (leftP.getY() + leftP.getHeight()/2f);
            float angle = diff / (leftP.getHeight()/2f) * 45f;
            angle = MathUtils.clamp(angle, -45f, 45f) * MathUtils.degRad;
            vel = new Vector2(Math.abs(speed * MathUtils.cos(angle)), Math.abs(speed) * MathUtils.sin(angle));
            pos.set(leftP.getX() + leftP.getWidth(), pos.y); // Avoiding bugs via positioning the puck just in front of the paddle
            playSound();
        } else if(pos.y <= rightP.getY() + rightP.getHeight() && pos.y + size >= rightP.getY() && pos.x + size >= rightP.getX() && pos.x <= rightP.getX() + rightP.getWidth()/2f) {
            // Same here
            float diff = (pos.y + size/2f) - (rightP.getY() + rightP.getHeight()/2f);
            float angle = diff / (rightP.getHeight()/2f) * 45f;
            angle = MathUtils.clamp(angle, -45f, 45f) * MathUtils.degRad;
            vel = new Vector2(-Math.abs(speed * MathUtils.cos(angle)), Math.abs(speed) * MathUtils.sin(angle));
            pos.set(rightP.getX() - size, pos.y); // same here
            playSound();
        }
    }

    private void reset(){
        pos.set(Gdx.graphics.getWidth()/2f - size/2f, Gdx.graphics.getHeight()/2 - size/2); // Positioning the puck right in the centre and resetting the speed
        speed = minSpeed * (MathUtils.random() < 0.5f ? -1f : 1f); // Randomly choosing side to glide to starting with minSpeed
        float angle = MathUtils.random(-45f, 45f) * MathUtils.degRad;
        vel = new Vector2(speed * MathUtils.cos(angle), speed * MathUtils.sin(angle)); // Applying speed and angle to the velocity
    }

    private void playSound(){
        float random = MathUtils.random();
        if(random < 0.3f)
            blip3.play(soundVolume);
        else if(random < 0.5f)
            blip2.play(soundVolume);
        else
            blip3.play(soundVolume);
    }

    public Vector2 getPos() {
        return pos;
    }

    public Vector2 getVel() {
        return vel;
    }

    public float getSize() {
        return size;
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
