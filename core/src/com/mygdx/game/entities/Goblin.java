package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.logic.Detection;

public class Goblin extends Enemy {
    public Goblin(float x, float y, Detection detector) {
        super(new Texture("Entities/goblin.png"), x, y, 60, 10, detector, 130);
        this.getHitboxRectangle().set(x+16,y,this.getTexture().getWidth()-32,this.getTexture().getHeight()-32);
    }

    @Override
    public void update(Player player, float deltaTime) {
        super.update(player, deltaTime);
        this.getHitbox().getRectangle().setPosition(this.getX()+16,this.getY());
    }
}
