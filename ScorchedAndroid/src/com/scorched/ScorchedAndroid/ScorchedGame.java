package com.scorched.ScorchedAndroid;

import android.content.Context;
import android.util.Log;
import scorched.common.objects.CameraObject;
import scorched.common.objects.GameObject;
import scorched.common.objects.PlaneObject;
import scorched.common.ui.Quad;
import scorched.engine.Game;
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

        Quad q = new Quad();

        hud.getScreen().addChild(q);

        GameObject go = new PlaneObject(20,20,32,32);
    }
}
