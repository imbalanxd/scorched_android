package scorched.common.ui;

import scorched.engine.renderer.RenderObject;
import scorched.engine.geometry.Vector2;
import scorched.engine.geometry.Vector3;
import scorched.engine.shader.Effect;
import scorched.engine.ui.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/7/13
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Quad extends Sprite
{
    float [] m_color = {1.0f,1.0f,1.0f,1.0f};

    public Quad(int _x, int _y, int _width, int _height)
    {
        super();
        m_width = _width;
        m_height = _height;
        m_position = new Vector2(_x, _y);
    }

    public Quad(int _width, int _height)
    {
        super();
        m_width = _width;
        m_height = _height;
    }

    public Quad(Sprite parent)
    {
        super(parent);
    }

    protected void setEffect()
    {
        m_view = new RenderObject();
        m_view.setEffect(new Effect());

    }

    protected void setProperties()
    {
        m_view.getEffect().setValue(Effect.COLOR_HANDLE, m_color);
    }

    protected void createObject()
    {
        float depth = 0.0f;
        m_view.setVertices(new Vector3[] {new Vector3(0.0f, 0.0f, depth),
                new Vector3(m_width,0.0f,depth),
                new Vector3(m_width,m_height,depth),
                new Vector3(0.0f,m_height,depth)});

        m_view.setIndices(new int [] {0,1,3,2});
    }

    public void setColor(float _r, float _g, float _b, float _a)
    {
        m_color = new float[] {_r, _g, _b, _a};
        propertiesChanged();
    }

    public float[] getColor()
    {
        return m_color;
    }
}
