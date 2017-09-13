package com.rare.pong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Puck {
    private Vector2 pos = new Vector2(Gdx.graphics.getWidth()/2 - 15f/2, Gdx.graphics.getHeight()/2 - 15f/2);
    private Vector2 vel;
    private int size = 15;
    private int speed = 1000;
    private ShapeRenderer shapeRenderer;

    public Puck(){
        reset();
        shapeRenderer = new ShapeRenderer();
    }

    public void update(float dt){
        glide(dt);

        if(Gdx.input.isKeyJustPressed(Input.Keys.X)){
            reset();
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(pos.x, pos.y, size, size);
        shapeRenderer.end();
    }

    private void glide(float dt){
        pos.add(new Vector2(vel.x * dt, vel.y * dt));

        if(pos.x <= 0) {
            vel.scl(-1, 1);
            pos.set(0, pos.y);
        } else if(pos.x >= Gdx.graphics.getWidth() - size) {
            vel.scl(-1, 1);
            pos.set(Gdx.graphics.getWidth() - size, pos.y);
        }

        if(pos.y <= 0) {
            vel.scl(1, -1);
            pos.set(pos.x, 0);
        } else if(pos.y >= Gdx.graphics.getHeight() - size){
            vel.scl(1, -1);
            pos.set(pos.x, Gdx.graphics.getHeight() - size);
        }
    }

    private void reset(){
        pos.set(Gdx.graphics.getWidth()/2 - 15f/2, Gdx.graphics.getHeight()/2 - 15f/2);
        speed *= MathUtils.random() < 0.5f ? -1 : 1;
        float angle = MathUtils.random(-45f, 45f) * MathUtils.PI / 180f;
        vel = new Vector2(speed * MathUtils.cos(angle), speed * MathUtils.sin(angle));
    }

    public void dispose(){
        shapeRenderer.dispose();
    }
}
