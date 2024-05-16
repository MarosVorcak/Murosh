package com.mygdx.game.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.mygdx.game.items.Defensivetem;
import com.mygdx.game.items.ItemType;
import com.mygdx.game.logic.Detection;
import com.mygdx.game.projectiles.FireballManager;

public class TrapRoom extends Room {
    public static final float TRAP_COOLDOWN = 2;
    private boolean isKeyPickedUp;
    private final FireballManager fireballManager;
    private float trapTimer;
    public TrapRoom() {
        super("Maps/trap_room.tmx");
        this.isKeyPickedUp = false;
        this.fireballManager = new FireballManager(200, TRAP_COOLDOWN);
        this.trapTimer = 0;
    }


    @Override
    public void renderEnemies(SpriteBatch batch, float deltaTime) {
        this.trapTimer += deltaTime;
        if (this.trapTimer >= TRAP_COOLDOWN) {
            for (MapObject object : this.getMap().getLayers().get("Spawns").getObjects()) {
                float spawnX = ((RectangleMapObject)object).getRectangle().x;
                float spawnY = ((RectangleMapObject)object).getRectangle().y;
                float angle = this.getAngle(object);
                this.fireballManager.shootFireballs(spawnX, spawnY, angle, TRAP_COOLDOWN);
            }
            this.trapTimer = 0;
        }
        this.fireballManager.renderAndUpdateFireballs(deltaTime, batch, this.getPlayer(), 20);
    }

    public float getAngle(MapObject object) throws IllegalArgumentException {
        String direction = (String)object.getProperties().get("direction");
        switch (direction) {
            case "right":
                return 0;
            case "left":
                return (float)Math.PI;
            case "down":
                return -(float)(Math.PI / 2);
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    @Override
    public void objectInteractions(float deltaTime, Detection detector) {
        if (detector.dangerObjectCollision(this.getPlayer().getHitboxRectangle())) {
            if (this.getPlayer().getInventory().hasItem(ItemType.IRON_BOOTS)) {
                Defensivetem item = (Defensivetem)this.getPlayer().getInventory().getItem(ItemType.IRON_BOOTS);
                this.getPlayer().takeDMG(item.calculateDmg(this.generateDamage()), Character.MIN_VALUE, deltaTime);
            } else {
                this.getPlayer().takeDMG(this.generateDamage(), Character.MIN_VALUE, deltaTime);
            }
        }
        if (!this.isKeyPickedUp) {
            if (detector.specialObjectCollision(this.getPlayer().getHitboxRectangle())) {
                this.isKeyPickedUp = true;
                this.getMap().getLayers().get("Decor").setVisible(false);
            }
        }
    }
    public void setDetectorForManager(Detection detector) {
        this.fireballManager.setDetector(detector);
    }

    private int generateDamage() {
        return (int)((Math.random()) * (20 - 15) + 15);
    }

    @Override
    public boolean checkIfRoomIsExitable() {
        return this.isKeyPickedUp;
    }
}
