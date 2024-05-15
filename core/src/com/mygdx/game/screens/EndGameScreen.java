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
import jdk.tools.jmod.Main;

public class EndGameScreen implements Screen {
    private Texture image;
    private String message;
    private final BitmapFont font;
    private final MainGame mainGame;

    public EndGameScreen(MainGame game, boolean victory) {
        this.mainGame = game;
        this.font = new BitmapFont(Gdx.files.internal("font/font.fnt"));
        if(!victory){
            this.image = new Texture("UI/you_died.png");
            this.message = "Press SPACE to try again\n" +
                    "Press ESCAPE to quit the game";
        }else{
            this.image = new Texture("UI/you_win.png");
            this.message = "Press SPACE to beat another dungeon\n" +
                    "Press ESCAPE to quit the game";
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            this.mainGame.setScreen(new GameScreen(this.mainGame));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
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
