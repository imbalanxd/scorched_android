package scorched.engine.geometry;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/24/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vector2
{
    public float x = 0;
    public float y = 0;

    public Vector2(float _x,float  _y)
    {
        x = _x;
        y = _y;
    }

    public Vector2()
    {
        x = 0;
        y = 0;
    }

    public void add(float _x, float _y)
    {
        x+=_x;
        y+=_y;
    }

    public void add(Vector2 _b)
    {
        x+= _b.x;
        y+= _b.y;
    }
}
