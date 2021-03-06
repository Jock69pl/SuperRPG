package com.ph.rpg.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.ph.rpg.controllers.CameraController;
import com.ph.rpg.controllers.DrawController;
import com.ph.rpg.objects.MageObject;
import com.ph.rpg.managers.GameInputProcessor;
import com.ph.rpg.managers.GameKeys;
import com.ph.rpg.scene.SceneManager;

public class Game implements ApplicationListener {

    public static int WIDTH;
    public static int HEIGHT;

    public static float stateTime = 0f;

    SpriteBatch spriteBatch;
    public static Texture fontTexture;
    public static ShaderProgram fontShader;
    private static Sound explosion;
    private static Sound coin;
    private static Sound punch;

    public static void explosion(){
        explosion.play(0.3f);
    }

    public static void coin(){
        coin.play(0.3f);
    }

    public static void punch(){
        punch.play(0.6f);
    }

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        CameraController.init();
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(new GameInputProcessor());

        new MageObject();

        SceneManager.goToScene(1);


        explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
         coin = Gdx.audio.newSound(Gdx.files.internal("coin.mp3"));
        punch = Gdx.audio.newSound(Gdx.files.internal("punch.mp3"));
        Sound sound = Gdx.audio.newSound(Gdx.files.internal("dawna.mp3"));
        long  id = sound.play(0.5f);
        sound.setLooping(id, true);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        fontTexture = new Texture(Gdx.files.internal("arial.png"));
        fontTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        fontShader = new ShaderProgram(Gdx.files.internal("arial.vert"), Gdx.files.internal("arial.frag"));
        if (!fontShader.isCompiled()) {
            Gdx.app.error("fontShader", "compilation failed:\n" + fontShader.getLog());
        }

        GameKeys.update();
        CameraController.update();
        spriteBatch.setProjectionMatrix(CameraController.cam.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        DrawController.render(spriteBatch);


        CameraController.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
