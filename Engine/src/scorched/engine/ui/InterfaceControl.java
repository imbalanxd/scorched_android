package scorched.engine.ui;

import scorched.engine.geometry.Rectangle;
import scorched.engine.interfaces.ISpriteObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/1/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class InterfaceControl implements ISpriteObject
{
    private ArrayList<ISpriteObject> m_children;

    public InterfaceControl()
    {
        m_children = new ArrayList<ISpriteObject>();
    }


    @Override
    public void addChild(ISpriteObject _child) {
        m_children.add(_child);
    }

    @Override
    public void addChildAt(int _index, ISpriteObject _child) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int numChildren() {
        return m_children.size();
    }

    @Override
    public Rectangle getBounds() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean hitTest() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
