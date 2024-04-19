package logic;

import dungeon.Room;

public class RoomSwitcher {
    private final Room currentRoom;
    private final Room switchRoom;

    public RoomSwitcher(Room currentRoom, Room switchRoom) {
        this.currentRoom = currentRoom;
        this.switchRoom = switchRoom;
    }
    public void switchPlayer(){
        this.switchRoom.setPlayer(this.currentRoom.getPlayer());
        this.currentRoom.setPlayer(null);
    }
    public void switchRooms(){

    }
}
