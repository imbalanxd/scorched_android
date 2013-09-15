package scorched.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.util.Log;
import android.view.WindowManager;
import scorched.engine.assets.Loader;
import scorched.engine.interfaces.IGameObject;
import scorched.engine.surface.DisplaySurface;
import scorched.engine.ui.HUD;

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
    private int MAX_FPS = 40;

    public static Vector<IGameObject> gameObjects;
    public static HUD hud;
    public static Display display;

    public static long frameTimeElapsed = 0;
    public static long frameTimeStart = 0;

    protected DisplaySurface m_mainScreen;

    public Game(Context context)
    {
        Log.d("SCORCHED", "Game::Constructor");
        setup(context);

        m_mainScreen = new DisplaySurface(context);
        m_mainScreen.initialize();
        m_mainScreen.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        display = ((WindowManager)(context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay();

        gameObjects = new Vector<IGameObject>();
        hud = new HUD();
    }

    protected void setup(Context _context)
    {
        Loader.setContext(_context);
    }

    public GLSurfaceView getSurface()
    {
        return m_mainScreen;
    }

    public void initGame()
    {
        Log.d("SCORCHED", "Game::Initialised");


        hud.createScreen("main");

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
        Game.hud.update();
    }

    public static void addGameObject(IGameObject object)
    {
        gameObjects.add(object);
    }
}
