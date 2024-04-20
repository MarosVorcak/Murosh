package dungeon;

import java.util.Random;

public class DungeonGenerator {
    public Room generateDungeon(){
        SpawnRoom spawn = new SpawnRoom("Maps/spawn.tmx");
        Room previous = spawn;
        Room current = null;
        int roomCounter = 0;
        int bossRoomChance = 25;
        Random random = new Random();
        while (true) {
            roomCounter++;
            if (roomCounter >= 15){
                boolean isBossRoom = ((random.nextInt(100) + 1) <= bossRoomChance);
                if(isBossRoom){
                    current = new BossRoom();
                    current.connectRoom(previous);
                    previous.connectRoom(current);
                    previous = current;
                    break;
                }else{
                    bossRoomChance += 10;
                    current = this.generateNormalRoom(random);
                    current.connectRoom(previous);
                    previous.connectRoom(current);
                    previous = current;
                }
            }else{
                boolean isSpecial = ((random.nextInt(100) + 1) <= 30);
                if (isSpecial){
                    boolean isTreasureRoom = ((random.nextInt(100) + 1) <= 40);
                    if (isTreasureRoom){
                        current = new TreasureRoom();
                        current.connectRoom(previous);
                        previous.connectRoom(current);
                        previous = current;
                    }else{
                        current = this.generateNormalRoom(random);
                        current.connectRoom(previous);
                        previous.connectRoom(current);
                        previous = current;
                    }
                }else{
                    current = this.generateNormalRoom(random);
                    current.connectRoom(previous);
                    previous.connectRoom(current);
                    previous = current;
                }
            }
        }
        return spawn;
    }
    private Room generateNormalRoom(Random random){
        int templateNumber = random.nextInt(3) + 1;
        return new Room("Maps/normal_room" + templateNumber + ".tmx");
    }
}
