package scorched.engine.Geometry;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/18/13
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vector3
{
    public float x = 0;
    public float y = 0;
    public float z = 0;

    public Vector3(float _x,float  _y,float  _z)
    {
        x = _x;
        y = _y;
        z = _z;
    }

    public Vector3()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public void add(float _x, float _y,float _z)
    {
        x+=_x;
        y+=_y;
        z+=_z;
    }
}
