package com.mygdx.game.dungeon;

/**
 * Trieda SpawnRoom rozširuje triedu Room a je zodpovedná za vytvorenie a správu spawn miestnosti.
 *
 * Importy:
 * import com.mygdx.game.dungeon.Room; // Pre prácu s miestnosťami
 */
public class SpawnRoom extends Room {

    /**
     * Konštruktor pre triedu SpawnRoom, ktorý inicializuje mapu.
     */
    public SpawnRoom() {
        super("Maps/spawn.tmx");
    }
}
