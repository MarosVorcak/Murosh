package com.mygdx.game.dungeon;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.mygdx.game.entities.Boss;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Goblin;
import com.mygdx.game.entities.Shaman;
import com.mygdx.game.entities.Slime;
import com.mygdx.game.logic.Detection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Trieda DungeonGenerator je zodpovedná za generovanie celého dungeonu.
 *
 * Importy:
 * import com.badlogic.gdx.maps.MapObject;
 * import com.badlogic.gdx.maps.objects.RectangleMapObject;
 * import com.mygdx.game.entities.Boss;
 * import com.mygdx.game.entities.Enemy;
 * import com.mygdx.game.entities.Goblin;
 * import com.mygdx.game.entities.Shaman;
 * import com.mygdx.game.entities.Slime;
 * import com.mygdx.game.logic.Detection;
 * import java.util.ArrayList;
 * import java.util.Iterator;
 * import java.util.Random;
 */
public class DungeonGenerator {
    private final Detection detector;

/**
     * Konštruktor pre triedu DungeonGenerator, ktorý inicializuje detektor kolízií.
     * @param detector detektor kolízií
     */
    public DungeonGenerator(Detection detector) {
        this.detector = detector;
    }

    /**
     * Metóda generateDungeon vygeneruje dungeon s miestnosťami a nepriateľmi.
     * @return miestnosť s dungeonom
     */
    public Room generateDungeon() {
        SpawnRoom spawn = new SpawnRoom();
        Room previous = spawn;
        Room current = null;
        int roomCounter = 0;
        int bossRoomChance = 25;
        Random random = new Random();
        while (true) {
            roomCounter++;
            if (roomCounter >= 15) {
                boolean isBossRoom = ((random.nextInt(100) + 1) <= bossRoomChance);
                if (isBossRoom) {
                    current = new BossRoom();
                    current.addEnemy(new Boss(400, 400, this.detector));
                    previous = this.connectRooms(current, previous);
                    break;
                } else {
                    bossRoomChance += 10;
                    current = this.generateNormalRoom(random);
                    this.spawnEnemies(current, random);
                    previous = this.connectRooms(current, previous);
                }
            } else {
                boolean isSpecial = ((random.nextInt(100) + 1) <= 40);
                if (isSpecial) {
                    boolean isTreasureRoom = ((random.nextInt(100) + 1) <= 60);
                    if (isTreasureRoom) {
                        current = new TreasureRoom();
                        previous = this.connectRooms(current, previous);
                    } else {
                        current = new TrapRoom();
                        TrapRoom currentTrap = (TrapRoom)current;
                        currentTrap.setDetectorForManager(this.detector);
                        previous = this.connectRooms(current, previous);
                    }
                } else {
                    current = this.generateNormalRoom(random);
                    this.spawnEnemies(current, random);
                    previous = this.connectRooms(current, previous);
                }
            }
        }
        return spawn;
    }

    /**
     * Metóda generateNormalRoom generuje normálnu miestnosť.
     *
     * @param random Random - generátor náhodných čísel
     * @return Room - normálna miestnosť
     */
    private Room generateNormalRoom(Random random) {
        int templateNumber = random.nextInt(4) + 1;
        return new Room("Maps/normal_room" + templateNumber + ".tmx");
    }

    /**
     * Metóda connectRooms spojí dve miestnosti.
     *
     * @param current Room - aktuálna miestnosť
     * @param previous Room - predchádzajúca miestnosť
     * @return Room - predchádzajúca miestnosť
     */
    private Room connectRooms(Room current, Room previous) {
        current.connectRoom(previous);
        previous.connectRoom(current);
        previous = current;
        return previous;
    }

    /**
     * Metóda spawnEnemies vygeneruje nepriateľov v miestnosti.
     *
     * @param room Room - miestnosť
     * @param random Random - generátor náhodných čísel
     */
    private void spawnEnemies(Room room, Random random) {
        Iterator<MapObject> iterator = room.getMap().getLayers().get("Spawns").getObjects().iterator();
        ArrayList<Enemy> spawnedEnemies = new ArrayList<Enemy>();
        while (iterator.hasNext()) {
            MapObject object = iterator.next();
            float spawnX = ((RectangleMapObject)object).getRectangle().x;
            float spawnY = ((RectangleMapObject)object).getRectangle().y;
            int enemyType = random.nextInt(3) + 1;
            switch (enemyType) {
                case 1:
                    if (!this.checkIfEnemyExists(enemyType, spawnedEnemies)) {
                        Goblin goblin = new Goblin(spawnX, spawnY, this.detector);
                        room.addEnemy(goblin);
                        spawnedEnemies.add(goblin);
                    } else {
                        int replacementEnemy = random.nextInt(2) + 1;
                        if (replacementEnemy == 1) {
                            Slime slime = new Slime(spawnX, spawnY, this.detector);
                            room.addEnemy(slime);
                            spawnedEnemies.add(slime);
                        } else {
                            Shaman shaman = new Shaman(spawnX, spawnY, this.detector);
                            room.addEnemy(shaman);
                            spawnedEnemies.add(shaman);
                        }
                    }
                    break;
                case 2:
                    if (!this.checkIfEnemyExists(enemyType, spawnedEnemies)) {
                        Slime slime = new Slime(spawnX, spawnY, this.detector);
                        room.addEnemy(slime);
                        spawnedEnemies.add(slime);
                    } else {
                        int replacementEnemy = random.nextInt(2) + 1;
                        if (replacementEnemy == 1) {
                            Goblin goblin = new Goblin(spawnX, spawnY, this.detector);
                            room.addEnemy(goblin);
                            spawnedEnemies.add(goblin);
                        } else {
                            Shaman shaman = new Shaman(spawnX, spawnY, this.detector);
                            room.addEnemy(shaman);
                            spawnedEnemies.add(shaman);
                        }
                    }
                    break;
                case 3:
                    if (!this.checkIfEnemyExists(enemyType, spawnedEnemies)) {
                        Shaman shaman = new Shaman(spawnX, spawnY, this.detector);
                        room.addEnemy(shaman);
                        spawnedEnemies.add(shaman);
                    } else {
                        int replacementEnemy = random.nextInt(2) + 1;
                        if (replacementEnemy == 1) {
                            Slime slime = new Slime(spawnX, spawnY, this.detector);
                            room.addEnemy(slime);
                            spawnedEnemies.add(slime);
                        } else {
                            Goblin goblin = new Goblin(spawnX, spawnY, this.detector);
                            room.addEnemy(goblin);
                            spawnedEnemies.add(goblin);
                        }
                    }
                    break;
            }
        }
    }

    /**
     * Metóda checkIfEnemyExists zistí, či v miestnosti existuje nepriateľ.
     *
     * @param enemyType int - typ nepriateľa
     * @param enemies ArrayList<Enemy> - zoznam nepriateľov
     * @return boolean - true, ak nepriateľ existuje, inak false
     */
    private boolean checkIfEnemyExists(int enemyType, ArrayList<Enemy>enemies) {
        if (enemies.isEmpty()) {
            return false;
        } else {
            switch (enemyType) {
                case 1:
                    for (Enemy enemy : enemies) {
                        if (enemy instanceof Goblin) {
                            return true;
                        }
                    }
                    break;
                case 2:
                    for (Enemy enemy : enemies) {
                        if (enemy instanceof Slime) {
                            return true;
                        }
                    }
                    break;
                case 3:
                    for (Enemy enemy : enemies) {
                        if (enemy instanceof Shaman) {
                            return true;
                        }
                    }
                    break;
            }
        }
        return false;
    }
}
