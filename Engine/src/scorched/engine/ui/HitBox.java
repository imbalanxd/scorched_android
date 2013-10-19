package scorched.engine.ui;

import android.graphics.Matrix;
import android.graphics.RectF;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/29/13
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class HitBox
{
    private RectF m_hitbox = new RectF(0.0f,0.0f,0.0f,0.0f);

    public HitBox()
    {

    }

    public HitBox(float _left, float _top, float _width, float _height)
    {
        setTop(_top);
        setLeft(_left);
        setWidth(_width);
        setHeight(_height);
    }

    public void setDimensions(float _left, float _top, float _width, float _height)
    {
        setTop(_top);
        setLeft(_left);
        setWidth(_width);
        setHeight(_height);
    }

    public void setTop(float _top)
    {
        m_hitbox.top = _top;
        m_hitbox.bottom = m_hitbox.bottom + m_hitbox.top;
    }

    public void setLeft(float _left)
    {
        m_hitbox.left = _left;
        m_hitbox.right = m_hitbox.right + m_hitbox.left;
    }

    public void setWidth(float _width)
    {
        m_hitbox.right = _width + m_hitbox.left;
    }

    public void setHeight(float _top)
    {
        m_hitbox.bottom = _top + m_hitbox.top;
    }

    public void transform(Matrix _transform)
    {
        _transform.mapRect(m_hitbox);
    }
}
