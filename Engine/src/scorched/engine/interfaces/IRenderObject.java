package scorched.engine.interfaces;

import scorched.engine.Geometry.Vector3;
import scorched.engine.shader.Effect;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created with IntelliJ BufferEA.
 * User: Robin
 * Date: 7/31/13
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IRenderObject
{
    public void dispose();

    public float[] getVertices();
    public FloatBuffer getVertexBuffer();
    public void setVertices(Vector3[] _vertices);
    public void setVertices(Vector3[] _vertices, int itemSize);
    public void addVertices(Vector3 [] _vertices);

    public float[] getTexCoords();
    public FloatBuffer getTexCoordBuffer();
    public void setTexCoords(float[] _vertices);
    public void setTexCoords(float[] _vertices, int itemSize);
    public void addTexCoords(float [] _vertices);

    public float[] getIndices();
    public ShortBuffer getIndexBuffer();
    public void setIndices(short[] _vertices);
    public void setIndices(short[] _vertices, int itemSize);
    public void addIndices(short [] _vertices);

    public void setIndices(int[] _vertices);
    public void setIndices(int[] _vertices, int itemSize);

    public float[] getNormals();
    public FloatBuffer getNormalBuffer();
    public void setNormals(float[] _vertices);
    public void setNormals(float[] _vertices, int itemSize);
    public void addNormals(float [] _vertices);

    public Effect getEffect();

    public void draw(float[] _mvpMatrix);
}
