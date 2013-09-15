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
    protected float m_rotation = 0.0f;
    protected Vector2 m_position = new Vector2(0,0);
    protected Vector2 m_pivot = new Vector2(0,0);

    protected boolean isActive = true;
    protected boolean isVisible = true;

    private boolean m_sizeChanged = true;
    private boolean m_positionChanged = true;
    private boolean m_propertiesChanged = true;

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

    protected void propertiesChanged()
    {
        m_propertiesChanged = true;
    }

    protected void setProperties()
    {

    }

    public void setWidth(int _width)
    {
        m_width = _width;
        m_sizeChanged = true;
    }

    public int getWidth()
    {
        return m_width;
    }

    public void setHeight(int _height)
    {
        m_height = _height;
        m_sizeChanged = true;
    }

    public int getHeight()
    {
        return m_height;
    }

    public void setPosition(Vector2 _position)
    {
        setPosition((int)_position.x ,(int)_position.y);
    }

    public void setPivot(Vector2 _pivot)
    {
        setPivot((int)_pivot.x ,(int)_pivot.y);
    }

    public void setPosition(int _x, int _y)
    {
        m_position = new Vector2(_x, _y);
        m_positionChanged = true;
    }

    public void setPivot(int _x, int _y)
    {
        m_pivot = new Vector2(_x, _y);
        m_positionChanged = true;
    }

    public void rotate(float _rot)
    {
        m_rotation += _rot;
        m_positionChanged = true;
    }

    public void setRotation(float _rot)
    {
        m_rotation = _rot;
        m_positionChanged = true;
    }

    public float getRotation()
    {
        return m_rotation;
    }

    public Vector2 getPosition()
    {
        return m_position;
    }

    public Vector2 getPivot()
    {
        return m_pivot;
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
            if(m_sizeChanged)
            {
                createObject();
                m_sizeChanged = false;
            }

            if(m_positionChanged)
            {
                //TODO do the pivot properly
                m_view.setRotation(new Vector3(0.0f, 0.0f, m_rotation));
                m_view.setTranslation(new Vector3(-m_pivot.x, -m_pivot.y, 0.0f));

                m_view.translate(m_position.x,m_position.y,-2.0f);


                m_positionChanged = false;
            }

            if(m_propertiesChanged)
            {
                setProperties();
                m_propertiesChanged = false;
            }
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
    public void update()
    {
            for (ISpriteObject child : m_children)
            {
                child.update();
            }
    }

    protected void createObject()
    {
        float depth = 0.0f;
        m_view.setVertices(new Vector3[] {new Vector3(0.0f, 0.0f, depth),
                new Vector3(m_width,0.0f,depth),
                new Vector3(m_width,m_height,depth),
                new Vector3(0.0f,m_height,depth)});

        m_view.setTexCoords(new Vector2[] {new Vector2(0.0f, 1.0f),
                new Vector2(1.0f,1.0f),
                new Vector2(1.0f,0.0f),
                new Vector2(0.0f,0.0f)});

        m_view.setIndices(new int [] {0,1,3,2});
    }
}
