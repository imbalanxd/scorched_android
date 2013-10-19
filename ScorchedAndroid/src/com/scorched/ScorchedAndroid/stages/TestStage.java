package com.scorched.ScorchedAndroid.stages;

import com.scorched.ScorchedAndroid.R;
import scorched.common.objects.*;
import scorched.common.ui.*;
import scorched.engine.assets.Loader;
import scorched.engine.geometry.*;
import scorched.engine.stage.Stage;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/28/13
 * Time: 10:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestStage extends Stage
{
    private ProgressBar bar;

    public TestStage(String _name)
    {
        super(_name);
    }

    /**
     * Load all resources required by this stage in this method
     */
    protected void resources()
    {
        Loader.loadTexture(R.drawable.ic_launcher);
    }

    /**
     * Create all UI or Game objects required by this stage in this method
     */
    protected void objects()
    {
        Image q = new Image(Loader.loadTexture(R.drawable.ic_launcher));
        q.setPosition(new Vector2(300,300));
        q.setPivot(32,32);
        q.rotate(45.0f);
        getHud().getScreen().addChild(q);

        bar = new ProgressBar(200,25);
        bar.setPosition(600,300);
        getHud().getScreen().addChild(bar);

        GameObject go = new PlaneObject(20,20,32,32);
    }

    /**
     * Perform necessary setup for this stage and its objects in this method
     */
    protected void setup()
    {

    }

    public void update()
    {
        super.update();
        bar.setProgress(bar.getProgress() + 0.01f);
    }
}
