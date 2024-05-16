package com.mygdx.game.dungeon;

import com.mygdx.game.logic.Detection;
import com.mygdx.game.projectiles.FireballManager;

public class TrapRoom extends Room {
    private boolean isKeyPickedUp;
    private final FireballManager fireballManager;
    public TrapRoom() {
        super("Maps/trap_room.tmx");
        this.isKeyPickedUp = false;
        this.fireballManager = new FireballManager(200);
    }

    @Override
    public void objectInteractions(float deltaTime, Detection detector) {
        if (!this.isKeyPickedUp) {
            if (detector.specialObjectCollision(this.getPlayer().getHitboxRectangle())) {
                this.isKeyPickedUp = true;
                this.getMap().getLayers().get("Decor").setVisible(false);
            }
        }
    }
    public void setDetectorForManager(Detection detector){
        this.fireballManager.setDetector(detector);
    }
    @Override
    public boolean checkIfRoomIsExitable() {
        return this.isKeyPickedUp;
    }
}
