package logic;

import com.badlogic.gdx.maps.objects.RectangleMapObject;
import dungeon.Room;

public class RoomSwitcher {
    private Room currentRoom;
    private Room switchRoom;


    public RoomSwitcher(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    private void switchPlayer(){
        this.switchRoom.setPlayer(this.currentRoom.getPlayer());
        this.currentRoom.setPlayer(null);
    }
    public Room switchRooms(){
        float newY;
        this.findSwitchRoom();
        if(this.currentRoom.getPlayer().getY() > 300){
            newY = this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(1).getRectangle().y + currentRoom.getPlayer().getTexture().getHeight()-32;
        }else{
            newY = this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle().y - currentRoom.getPlayer().getTexture().getHeight();
        }
        this.currentRoom.getPlayer().setY(newY);
        this.switchPlayer();
        this.currentRoom = this.switchRoom;
        return this.currentRoom;
    }
    private void findSwitchRoom(){
        if (this.currentRoom.getConnectedRooms().size() == 1){
            this.switchRoom = this.currentRoom.getConnectedRooms().getFirst();
        }else{
            float deltaYDownRoom = Math.abs(this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(1).getRectangle().y - this.currentRoom.getPlayer().getY());
            float deltaYUpRoom = Math.abs(this.currentRoom.getMap().getLayers().get("Doors").getObjects().getByType(RectangleMapObject.class).get(0).getRectangle().y - this.currentRoom.getPlayer().getY());
            if(deltaYDownRoom > deltaYUpRoom){
                this.switchRoom = this.currentRoom.getConnectedRooms().getFirst();
            }else{
                this.switchRoom = this.currentRoom.getConnectedRooms().get(1);
            }
        }
    }
}
