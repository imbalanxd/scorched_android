package scorched.engine.geometry;

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

    public Vector3 cross(Vector3 b)
    {
        return new Vector3(this.y*b.z - this.z*b.y, this.z*b.x - this.x*b.z, this.x*b.y - this.y*b.x);
    }

    public void normalise()
    {
        float magnitude = magnitude();
        x/=magnitude;
        y/=magnitude;
        z/=magnitude;
    }

    public float magnitude()
    {
        return (float)Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
    }
}
