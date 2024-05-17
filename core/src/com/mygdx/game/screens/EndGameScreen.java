package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MainGame;

/**
 * Trieda EndGameScreen je zodpovedná za vytvorenie a správu koncovej obrazovky hry.
 *
 * Importy:
 * import com.badlogic.gdx.Gdx; // Pre prácu s libGDX knižnicou
 * import com.badlogic.gdx.Input; // Pre prácu s vstupmi (klávesnicou, myšou atď.)
 * import com.badlogic.gdx.Screen; // Pre vytvorenie obrazovky hry
 * import com.badlogic.gdx.graphics.Color; // Pre prácu s farbami
 * import com.badlogic.gdx.graphics.Texture; // Pre prácu s textúrami
 * import com.badlogic.gdx.graphics.g2d.BitmapFont; // Pre prácu s fontmi
 * import com.badlogic.gdx.graphics.g2d.GlyphLayout; // Pre prácu s rozložením textu
 * import com.mygdx.game.MainGame; // Pre prácu s hlavnou hrou
 */
public class EndGameScreen implements Screen {
    private final Texture image;
    private final String message;
    private final BitmapFont font;
    private final MainGame mainGame;

    /**
     * Konštruktor triedy EndGameScreen.
     *
     * @param game Hlavná hra.
     * @param victory Informácia o výhre alebo prehre.
     */
    public EndGameScreen(MainGame game, boolean victory) {
        this.mainGame = game;
        this.font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        if (!victory) {
            this.image = new Texture("UI/you_died.png");
            this.message = "Press SPACE to try again\n" +
                    "Press ESCAPE to quit the game";
        } else {
            this.image = new Texture("UI/you_win.png");
            this.message = "Press SPACE to beat another dungeon\n" +
                    "Press ESCAPE to quit the game";
        }
    }

    @Override
    public void show() {

    }

    /**
     * Metóda na vykreslenie koncovej obrazovky.
     *
     * @param deltaTime Časový rozdiel od posledného vykresleného snímku.
     */
    @Override
    public void render(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.mainGame.setScreen(new GameScreen(this.mainGame));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        this.mainGame.getBatch().begin();
        GlyphLayout messageLayout = new GlyphLayout(this.font, this.message, Color.BLACK, 0, Align.left, false);
        this.mainGame.getBatch().draw(this.image, Gdx.graphics.getWidth() / 2  - this.image.getWidth() / 2, Gdx.graphics.getHeight() / 2 - this.image.getHeight() / 2 );
        this.font.draw(this.mainGame.getBatch(), messageLayout, Gdx.graphics.getWidth() / 2 - messageLayout.width / 2, Gdx.graphics.getHeight() / 2  - messageLayout.height / 2 - 40);
        this.mainGame.getBatch().end();

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
