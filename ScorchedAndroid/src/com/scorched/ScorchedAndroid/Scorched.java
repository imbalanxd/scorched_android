package com.scorched.ScorchedAndroid;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import scorched.common.objects.GameObject;
import scorched.common.objects.PlaneObject;
import scorched.engine.Game;
import scorched.engine.renderer.DefaultRenderer;

public class Scorched extends Activity {

    GLSurfaceView m_mainView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Game scorchedGame = new ScorchedGame(this);
        scorchedGame.initGame();
        scorchedGame.start();
        setContentView(scorchedGame.getSurface());
    }
}
