package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Paddle {
    private float x, y;
    private float width = 15f;
    private float height = 100f;
    private ShapeRenderer shapeRenderer;
    private int upKey, downKey;
    private float speed = 700f;
    private Score score;

    public Paddle(float x, float y, int upKey, int downKey, Score score) {
        this.x = x;
        this.y = y;
        this.upKey = upKey;
        this.downKey = downKey;
        this.score = score;
        shapeRenderer = new ShapeRenderer();
    }

    public void update(float dt){
        if(!score.gameOver)
            handleInput(dt);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.end();
    }

    private void handleInput(float dt){
        if(Gdx.input.isKeyPressed(upKey)){
            y += speed * dt;
        } else if(Gdx.input.isKeyPressed(downKey)){
            y -= speed * dt;
        }

        y = MathUtils.clamp(y, 0, Gdx.graphics.getHeight() - height);
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float getWidth() {
        return width;
    }

    float getHeight() {
        return height;
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
