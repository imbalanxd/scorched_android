package com.scorched.ScorchedAndroid;

import android.content.Context;
import android.util.Log;
import scorched.common.objects.CameraObject;
import scorched.common.objects.GameObject;
import scorched.common.objects.PlaneObject;
import scorched.common.ui.Image;
import scorched.common.ui.Quad;
import scorched.engine.Game;
import scorched.engine.assets.Loader;
import scorched.engine.geometry.Vector2;
import scorched.engine.interfaces.IGameObject;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/20/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScorchedGame extends Game
{
    public ScorchedGame(Context context) {
        super(context);
        m_mainScreen.setCamera(new CameraObject());
        m_mainScreen.setHUDCamera(new CameraObject());
    }

    public void initGame()
    {
        super.initGame();

        Image q = new Image(Loader.loadTexture(R.drawable.ic_launcher));
        q.setPosition(new Vector2(300,300));
        q.setPivot(32,32);
        q.rotate(45.0f);

        Image w = new Image(Loader.loadTexture(R.drawable.icon2));
        w.setPosition(new Vector2(500,500));
//
        hud.getScreen().addChild(q);
        hud.getScreen().addChild(w);

        //GameObject go = new PlaneObject(20,20,32,32);
    }
}
