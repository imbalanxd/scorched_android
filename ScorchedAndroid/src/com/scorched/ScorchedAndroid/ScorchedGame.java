package com.scorched.ScorchedAndroid;

import android.content.Context;
import com.scorched.ScorchedAndroid.stages.TestStage;
import scorched.common.objects.CameraObject;
import scorched.engine.Game;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/20/13
 * Time: 9:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class ScorchedGame extends Game
{
    public static final String TEST_STAGE = "test_stage";

    public ScorchedGame(Context context) {
        super(context);
        m_mainScreen.setCamera(new CameraObject());
        m_mainScreen.setHUDCamera(new CameraObject());
    }

    public void initGame()
    {
        super.initGame();

        new TestStage(TEST_STAGE);
        activateStage(TEST_STAGE);

//        Image q = new Image(Loader.loadTexture(R.drawable.ic_launcher));
//        q.setPosition(new Vector2(300,300));
//        q.setPivot(32,32);
//        q.rotate(45.0f);
//
//        Image w = new Image(Loader.loadTexture(R.drawable.icon2));
//        w.setPosition(new Vector2(500,500));
//
//        hud.getScreen().addChild(q);
//        hud.getScreen().addChild(w);

//        GameObject go = new PlaneObject(20,20,32,32);
    }
}
