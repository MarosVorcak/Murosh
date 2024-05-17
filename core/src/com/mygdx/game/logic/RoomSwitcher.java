package com.mygdx.game.logic;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.mygdx.game.dungeon.Room;

/**
 * Trieda RoomSwitcher je zodpovedná za prepínanie medzi miestnosťami.
 *
 * Importy:
 * import com.badlogic.gdx.maps.objects.RectangleMapObject; // Pre prácu s objektami na mape
 * import com.mygdx.game.dungeon.Room; // Pre prácu s miestnosťami
 */
public class RoomSwitcher {
    private Room currentRoom;
    private Room switchRoom;

    /**
     * Konštruktor triedy RoomSwitcher.
     *
     * @param currentRoom Aktuálna miestnosť.
     */
    public RoomSwitcher(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Metóda pre prepínanie hráča medzi miestnosťami.
     */
    private void switchPlayer() {
        this.switchRoom.setPlayer(this.currentRoom.getPlayer());
        this.currentRoom.setPlayer(null);
    }

    /**
     * Metóda pre prepínanie medzi miestnosťami.
     *
     * @return Nová aktuálna miestnosť.
     */
    public Room switchRooms() {
        float newY;
        this.findSwitchRoom();
        if (this.currentRoom.getPlayer().getY() > 300) {
            newY = this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(1).getRectangle().y + this.currentRoom.getPlayer().getTexture().getHeight() - 32;
        } else {
            newY = this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle().y - this.currentRoom.getPlayer().getTexture().getHeight();
        }
        this.currentRoom.getPlayer().setY(newY);
        this.switchPlayer();
        this.currentRoom = this.switchRoom;
        return this.currentRoom;
    }

    /**
     * Metóda pre nájdenie miestnosti, do ktorej sa má prepnúť.
     */
    private void findSwitchRoom() {
        if (this.currentRoom.getConnectedRooms().size() == 1) {
            this.switchRoom = this.currentRoom.getConnectedRooms().get(0);
        } else {
            float deltaYDownRoom = Math.abs(this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(1).getRectangle().y - this.currentRoom.getPlayer().getY());
            float deltaYUpRoom = Math.abs(this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle().y - this.currentRoom.getPlayer().getY());
            if (deltaYDownRoom < deltaYUpRoom) {
                this.switchRoom = this.currentRoom.getConnectedRooms().get(0);
            } else {
                this.switchRoom = this.currentRoom.getConnectedRooms().get(1);
            }
        }
    }
}
