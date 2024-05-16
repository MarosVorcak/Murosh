package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MainGame;

public class MainMenuScreen implements Screen {
    private final MainGame mainGame;
    private final Texture image;
    private final String message;
    private final BitmapFont font;

    public MainMenuScreen(MainGame mainGame) {
        this.font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        this.mainGame = mainGame;
        this.image = new Texture("UI/logo.png");
        this.message = "Press SPACE to launch the game\n" +
                "Press ESCAPE to quit the game";
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(129 / 255f, 11 / 255f, 35 / 255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.mainGame.setScreen(new GameScreen(this.mainGame));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        this.mainGame.getBatch().begin();
        GlyphLayout messageLayout = new GlyphLayout(this.font, this.message, Color.BLACK, 0, Align.left, false);
        this.mainGame.getBatch().draw(this.image, Gdx.graphics.getWidth() / 2  - this.image.getWidth() / 2, Gdx.graphics.getHeight() - this.image.getHeight() - 40);
        this.font.draw(this.mainGame.getBatch(), messageLayout, Gdx.graphics.getWidth() / 2 - messageLayout.width / 2, Gdx.graphics.getHeight() / 2  - messageLayout.height / 2);
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
