package scorched.engine.ui;

import android.graphics.Point;
import android.opengl.Matrix;
import scorched.engine.Game;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.interfaces.ISpriteObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/1/13
 * Time: 10:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class HUD
{
    protected Map<String, ISpriteObject> m_screens;
    protected int m_width, m_height;

    public HUD()
    {
        Point screenSize = new Point();
        Game.display.getSize(screenSize);
        m_width = screenSize.x;
        m_height = screenSize.y;

        m_screens = new HashMap<String, ISpriteObject>();
        createScreen("main");
    }

    public void addChild()
    {

    }

    public void addChildAt(int _index)
    {

    }

    public ISpriteObject getScreen(String _name)
    {
        return m_screens.get(_name);
    }

    public ISpriteObject getScreen()
    {
        for (ISpriteObject screen : m_screens.values())
        {
            return screen;
        }
        return null;
    }

    public void update()
    {
        for (ISpriteObject screen : m_screens.values())
        {
            screen.update();
        }
    }

    public void draw(ICameraObject _camera)
    {
        for (ISpriteObject screen : m_screens.values())
        {
            screen.draw(_camera);
        }
    }

    public void hitTest()
    {

    }

    public void createScreen(String _name)
    {
        Sprite screen = new Sprite();
        m_screens.put(_name, screen);
    }
}
