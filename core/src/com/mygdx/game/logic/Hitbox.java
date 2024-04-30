package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Hitbox {

    private final Rectangle rectangle;

    public Hitbox(float x, float y, float width, float height) {
        this.rectangle = new Rectangle();
        this.rectangle.set(x, y, width, height);
    }
    public void render() {
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.GREEN);
        sr.rect(this.rectangle.x, this.rectangle.y, this.rectangle.width, this.rectangle.height);
        sr.end();
    }
    public Rectangle getRectangle() {
        return this.rectangle;
    }
}
