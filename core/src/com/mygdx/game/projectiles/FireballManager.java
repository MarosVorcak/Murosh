package com.mygdx.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.Player;
import com.mygdx.game.logic.Detection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Trieda FireballManager je zodpovedná za správu ohňových gúľ vo hre.
 *
 * Importy:
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.badlogic.gdx.graphics.g2d.SpriteBatch; // Pre prácu s vykresľovaním
 * import com.mygdx.game.entities.Player; // Pre prácu s hráčom
 * import com.mygdx.game.logic.Detection; // Pre prácu s detekciou
 * import java.util.ArrayList; // Pre prácu so zoznamom
 */
public class FireballManager {
    private final ArrayList<Fireball> fireballs;
    private Detection detector;
    private final float fireballCooldown;
    private float fireballTimer;
    private final float fireballSpeed;

    /**
     * Konštruktor triedy FireballManager.
     *
     * @param detector Detektor kolízií.
     * @param fireballSpeed Rýchlosť ohňovej gule.
     * @param fireballCooldown Časový interval medzi vystrelením ohňovej gule.
     */
    public FireballManager(Detection detector, float fireballSpeed, float fireballCooldown) {
        this.detector = detector;
        this.fireballs = new ArrayList<Fireball>();
        this.fireballTimer = 1;
        this.fireballSpeed = fireballSpeed;
        this.fireballCooldown = fireballCooldown;
    }

    /**
     * Konštruktor triedy FireballManager.
     *
     * @param fireballSpeed Rýchlosť ohňovej gule.
     * @param fireballCooldown Časový interval medzi vystrelením ohňovej gule.
     */
    public FireballManager(float fireballSpeed, float fireballCooldown) {
        this.fireballs = new ArrayList<Fireball>();
        this.fireballTimer = 1;
        this.fireballSpeed = fireballSpeed;
        this.fireballCooldown = fireballCooldown;
    }

    /**
     * Metóda na vystrelenie ohňových gúľ.
     *
     * @param spawnX Pozícia x-ová vystrelenia.
     * @param spawnY Pozícia y-ová vystrelenia.
     * @param angle Uhol vystrelenia.
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    public void shootFireballs(float spawnX, float spawnY, float angle, float deltaTime) {
        this.fireballTimer += deltaTime;
        if (this.fireballTimer >= this.fireballCooldown) {
            this.fireballTimer = 0;
            this.fireballs.add(new Fireball(spawnX, spawnY, new Texture("projectiles/fireball.png"), this.fireballSpeed, angle));
        }
    }

    /**
     * Metóda na nastavenie detektoru kolízií.
     *
     * @param detector Detektor kolízií.
     */
    public void setDetector(Detection detector) {
        this.detector = detector;
    }

    /**
     * Metóda na vykreslenie a aktualizáciu ohňových gúľ.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     * @param batch SpriteBatch pre vykreslenie.
     * @param player Hráč.
     * @param dmg Poškodenie od ohňovej gule.
     */
    public void renderAndUpdateFireballs(float deltaTime, SpriteBatch batch, Player player, int dmg) {
        Iterator<Fireball> iterator = this.fireballs.iterator();
        while (iterator.hasNext()) {
            Fireball fireball = iterator.next();
            fireball.update(deltaTime);
            fireball.getHitbox().getRectangle().setPosition(fireball.getX(), fireball.getY());
            fireball.render(batch);
            if (this.detector.rectangleToRectangle(player.getHitboxRectangle(), fireball.getHitbox().getRectangle())) {
                player.takeDMG(dmg, Character.MIN_VALUE, 0.43f);
                iterator.remove();
            }
            if (this.detector.wallCollision(fireball.getHitbox().getRectangle()) || this.detector.doorCollision(fireball.getHitbox().getRectangle())) {
                iterator.remove();
            }
        }
    }

    /**
     * Metóda na zistenie, či je ohňová guľa na cooldowne.
     *
     * @return True, ak je ohňová guľa na cooldowne, inak false.
     */
    public boolean isFireballOnCD() {
        return this.fireballTimer < this.fireballCooldown;
    }
}
