package com.sampler;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Logger;

public class ApplicationListenerSample implements ApplicationListener {

    private static final Logger log = new Logger(ApplicationListenerSample.class.getName(), Logger.DEBUG);

    private boolean renderInterrupted = true;

    @Override
    public void create() {
        // used to initialize game and load resources
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        log.debug("create");
    }

    @Override
    public void resize(int width, int height) {
        // used to handle setting a new screen size
        log.debug("resize() width = " + width + " height = " + height);
    }

    @Override
    public void render() {
        // used to update and render the game elements called 60 times per second
        if (renderInterrupted) {
            log.debug("render()");
            renderInterrupted = false;
        }
    }

    @Override
    public void pause() {
        // used to save game state when it loses focus, which doesn't involve the actual
        // gameplay being paused unless the developper wants it to pause
        log.debug("pause()");
        renderInterrupted = true;
    }

    @Override
    public void resume() {
        // used to handle the game coming back from being paused and restores game state
        log.debug("resume()");
        renderInterrupted = true;
    }

    @Override
    public void dispose() {
        // used to free resources and cleanup
        log.debug("dispose()");
    }
}
