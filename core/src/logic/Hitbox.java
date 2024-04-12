package logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Hitbox {
    private Circle circle;

    public Hitbox(float x, float y, float rad) {
        this.circle = new Circle();
        this.circle.set(x, y ,rad);
    }
    public void render(){
        ShapeRenderer sr = new ShapeRenderer();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.GREEN);
        sr.circle(this.circle.x, this.circle.y,this.circle.radius);
        sr.end();
    }
    public Circle getCircle(){
        return this.circle;
    }
}
