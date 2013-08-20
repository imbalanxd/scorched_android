package scorched.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import scorched.engine.interfaces.IGameObject;
import scorched.engine.renderer.DefaultRenderer;
import scorched.engine.surface.DisplaySurface;

import java.util.TimerTask;
import java.util.Timer;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/28/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game
{
    private int MAX_FPS = 30;

    public static Vector<IGameObject> gameObjects;

    public static long frameTimeElapsed = 0;
    public static long frameTimeStart = 0;

    private DisplaySurface m_mainScreen;

    public Game(Context context)
    {
        Log.d("SCORCHED", "Game::Constructor");
        m_mainScreen = new DisplaySurface(context);
        m_mainScreen.setRenderer(new DefaultRenderer());
        m_mainScreen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    public GLSurfaceView getSurface()
    {
        return m_mainScreen;
    }

    public void initGame()
    {
        Log.d("SCORCHED", "Game::Initialised");
        gameObjects = new Vector<IGameObject>();
        frameTimeStart = System.currentTimeMillis();
    }

    public void start()
    {
        gameLoop();
    }

    private void gameLoop()
    {
        update();
        draw();

        frameTimeElapsed = System.currentTimeMillis() - frameTimeStart;
        frameTimeStart += frameTimeElapsed;

        Timer gameLoop = new Timer();
        TimerTask gameCycle = new TimerTask() {
            @Override
            public void run() {
                gameLoop();
            }
        };
        gameLoop.schedule(gameCycle, Math.max(0, (1000/MAX_FPS) - frameTimeElapsed));
    }

    private void draw()
    {
        m_mainScreen.requestRender();
    }

    private void update()
    {
        //Log.d("SCORCHED", "Game:Update");
        for(IGameObject object : gameObjects)
        {
            object.update();
        }
    }

    public static void addGameObject(IGameObject object)
    {
        gameObjects.add(object);
    }
}

class GameLoop extends Thread
{

    @Override
    public void run()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
