package scorched.engine.stage;

import scorched.engine.Game;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.interfaces.IGameObject;
import scorched.engine.ui.HUD;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/21/13
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */

enum StageState{
    ACTIVE, INACTIVE, CLOSED, DISPOSED;
}
public class Stage
{
    public static final String EMPTY_STAGE = "empty_stage";


    private int refreshRate = 30;
    private StageState state = StageState.DISPOSED;

    private String m_name;
    private Vector<IGameObject> m_gameObjects;
    private HUD m_hud;

    public Stage(String _name)
    {
        m_name = _name;
        m_gameObjects = new Vector<IGameObject>();
        m_hud = new HUD();

        Game.getStageManager().addStage(this);
    }

    public void initialize()
    {
        switch(state)
        {
            case INACTIVE:
                setup();
                break;
            case CLOSED:
                objects();
                setup();
                break;
            case DISPOSED:
                resources();
                objects();
                setup();
                break;
        }
    }

    /**
     * Load all resources required by this stage in this method
     */
    protected void resources()
    {

    }

    /**
     * Create all UI or Game objects required by this stage in this method
     */
    protected void objects()
    {

    }

    /**
     * Perform necessary setup for this stage and its objects in this method
     */
    protected void setup()
    {

    }

    public Vector<IGameObject> getGameObjects()
    {
        return m_gameObjects;
    }

    public void addGameObject(IGameObject _object)
    {
        m_gameObjects.add(_object);
    }


    public HUD getHud()
    {
        return m_hud;
    }

    public void update()
    {
        for(IGameObject object : m_gameObjects)
        {
            object.update();
        }
        m_hud.update();
    }

    public void draw(ICameraObject _camera, ICameraObject _hudCamera)
    {
        for(IGameObject object : m_gameObjects)
        {
            object.draw(_camera);
        }
        m_hud.draw(_hudCamera);
    }

    public void change(Stage _target, StageTransition _transition)
    {

    }

    public void close()
    {

    }

    public void destroy()
    {

    }

    public void setRefreshRate(int _rate)
    {
       refreshRate = _rate;
    }

    public int getRefreshRate()
    {
        return refreshRate;
    }

    public String getName()
    {
        return m_name;
    }

    public void setName(String _name)
    {
        m_name = _name;
    }
}
