package scorched.engine.ui;

import scorched.engine.geometry.Rectangle;
import scorched.engine.geometry.Vector2;
import scorched.engine.geometry.Vector3;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.interfaces.IRenderObject;
import scorched.engine.interfaces.ISpriteObject;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/1/13
 * Time: 11:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sprite implements ISpriteObject
{
    protected ISpriteObject m_parent;
    protected IRenderObject m_view;
    protected ArrayList<ISpriteObject> m_children;

    protected int m_width,m_height;
    protected int m_xOffset, m_yOffset;
    protected Vector2 m_position;

    protected boolean isActive = true;
    protected boolean isVisible = true;

    private boolean m_viewChanged = true;

    public Sprite()
    {
        this(null);
    }

    public Sprite(Sprite parent)
    {
        m_parent = parent;
        m_children = new ArrayList<ISpriteObject>();
        setEffect();
        if(m_view != null && m_view.getEffect() == null)
            throw new RuntimeException("Component does not have assigned Effect");
    }

    protected void setEffect()
    {

    }

    public void setWidth(int _width)
    {
        m_width = _width;
        m_viewChanged = true;
    }

    public int getWidth()
    {
        return m_width;
    }

    public void setHeight(int _height)
    {
        m_height = _height;
        m_viewChanged = true;
    }

    public int getHeight()
    {
        return m_height;
    }

    public void setPosition(Vector2 _position)
    {
        m_position = _position;
    }

    public Vector2 getPosition()
    {
        return m_position;
    }

    @Override
    public void addChild(ISpriteObject _child) {
        m_children.add(_child);
        _child.setParent(this);
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
    public void setParent(ISpriteObject _parent) {
        m_parent = _parent;
    }

    @Override
    public void draw(ICameraObject _camera) {
        if(m_view != null)
        {
            if(m_viewChanged)
                createObject();
            m_view.draw(_camera.projectionMatrix(), _camera.modelViewMatrix());
        }

        if(isVisible)
        {
            for (ISpriteObject child : m_children)
            {
                child.draw(_camera);
            }
        }
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void createObject()
    {
        float depth = 1.0f;
        m_view.setVertices(new Vector3[] {new Vector3(0.0f, 0.0f, depth),
                new Vector3(m_width,0.0f,depth),
                new Vector3(m_width,m_height,depth),
                new Vector3(0.0f,m_height,depth)});

        m_view.setTexCoords(new Vector2[] {new Vector2(0.0f, 0.0f),
                new Vector2(1.0f,0.0f),
                new Vector2(1.0f,1.0f),
                new Vector2(0.0f,1.0f)});

        m_view.setIndices(new int [] {0,1,2,3});
    }
}
