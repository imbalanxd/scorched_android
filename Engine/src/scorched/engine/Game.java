package scorched.engine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.util.Log;
import android.view.WindowManager;
import scorched.engine.assets.Loader;
import scorched.engine.interfaces.IGameObject;
import scorched.engine.stage.Stage;
import scorched.engine.stage.StageManager;
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
    private int MAX_FPS = 30;

    public static Display display;
    private static StageManager stageManager;

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

        stageManager = new StageManager();
        stageManager.setup();
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
        getCurrentStage().update();
    }

    public static void activateStage(String _name)
    {
        stageManager.activateStage(_name);
    }

    public static StageManager getStageManager()
    {
        return stageManager;
    }

    public static Stage getCurrentStage()
    {
        return stageManager.getCurrentStage();
    }

    public static Vector<IGameObject> getGameObjects()
    {
        return stageManager.getCurrentStage().getGameObjects();
    }

    public static void addGameObject(IGameObject _object)
    {
        addGameObject(_object, stageManager.getCurrentStage());
    }

    public static void addGameObject(IGameObject _object, Stage _stage)
    {
        synchronized (_stage)
        {
            _stage.addGameObject(_object);
        }
    }

    public static HUD getHUD()
    {
        return stageManager.getCurrentStage().getHud();
    }
}
