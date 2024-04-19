package dungeon;

import logic.Detection;

public class DungeonGenerator {
    public Room generateDungeon(){
        SpawnRoom spawn = new SpawnRoom("Maps/spawn.tmx");
        return spawn;
    }
}
