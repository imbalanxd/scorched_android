package scorched.common.ui;

import scorched.common.objects.RenderObject;
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
    public Quad()
    {
        super();
    }

    public Quad(Sprite parent)
    {
        super(parent);
    }

    protected void setEffect()
    {
        m_view = new RenderObject();
        m_view.setEffect(new Effect());
        m_view.getEffect().setValue(Effect.COLOR_HANDLE, new float [] {1.0f,1.0f,1.0f,1.0f});
    }

    protected void createObject()
    {
        float depth = 1.0f;
        m_view.setVertices(new Vector3[] {new Vector3(0.0f, 0.0f, depth),
                new Vector3(m_width,0.0f,depth),
                new Vector3(m_width,m_height,depth),
                new Vector3(0.0f,m_height,depth)});

        m_view.setIndices(new int [] {0,1,2,3});
    }
}
