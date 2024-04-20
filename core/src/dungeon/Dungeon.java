package dungeon;

import logic.Detection;
import logic.RoomSwitcher;

public class Dungeon {
    private Room currentRoom;
    private final RoomSwitcher roomSwitcher;
    private final Detection detector;

    public Dungeon(Detection detector) {
        DungeonGenerator generator = new DungeonGenerator(detector);
        this.detector = detector;
        this.currentRoom = generator.generateDungeon();
        this.roomSwitcher = new RoomSwitcher(this.currentRoom);
    }

    public Room getCurrentRoom() {
        return this.currentRoom;
    }
    public boolean swithcedRooms(){
        if (this.currentRoom.checkIfRoomIsExitable()){
            if (this.detector.doorCollision(this.currentRoom.getPlayer().getHitboxRectangle())){
                this.currentRoom = this.roomSwitcher.switchRooms();
                return true;
            }
        }
        return false;
    }
    public void dangerObjects(float deltaTime){
        if (this.detector.dangerObjectCollision(this.currentRoom.getPlayer().getHitboxRectangle())){
            this.currentRoom.getPlayer().takeDMG(10,Character.MIN_VALUE,deltaTime);
        }
    }
}
