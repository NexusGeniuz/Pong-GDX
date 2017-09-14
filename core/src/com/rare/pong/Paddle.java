package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class Paddle {
    private int x, y;
    private int width = 15;
    private int height = 100;
    private ShapeRenderer shapeRenderer;
    private int upKey, downKey;
    private int speed = 700;

    public Paddle(int x, int y, int upKey, int downKey) {
        this.x = x;
        this.y = y;
        this.upKey = upKey;
        this.downKey = downKey;
        shapeRenderer = new ShapeRenderer();
    }

    public void update(float dt){
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

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
