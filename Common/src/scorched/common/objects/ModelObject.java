package scorched.common.objects;

import android.opengl.Matrix;
import scorched.engine.geometry.Vector3;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.shader.Effect;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/3/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModelObject
{
    private Map<String, RenderObject> bones;

    Vector3 m_position = new Vector3();
    Vector3 m_rotation = new Vector3();

    private boolean m_dirty = true;
    private float[] m_transform = new float[16];

    public ModelObject()
    {
        bones = new HashMap<String, RenderObject>();
    }

    public void addBone(String _identifier, RenderObject _bone)
    {
        bones.put(_identifier, _bone);
    }

    public void addBone(String _identifier)
    {
        bones.put(_identifier, new RenderObject());
    }

    public RenderObject getBone(String _identifier)
    {
        if(bones.containsKey(_identifier))
            return bones.get(_identifier);
        return null;
    }

    public Effect getEffect(String _identifier)
    {
        if(bones.containsKey(_identifier))
            return bones.get(_identifier).getEffect();
        return null;
    }

    public RenderObject getLazyBone(String _identifier)
    {
        if(bones.containsKey(_identifier))
            return bones.get(_identifier);
        addBone(_identifier);
        return bones.get(_identifier);
    }

    public int boneCount()
    {
        return bones != null ? bones.size() : 0;
    }

    public void rotate(float x, float y, float z, float theta)
    {
        m_rotation.add(x * theta, y * theta, z * theta);
        changed();
    }

    public void translate(float x, float y, float z)
    {
        m_position.add(x, y, z);
        changed();
    }

    private void changed()
    {
        m_dirty = true;
    }

    private void createTransform()
    {
        Matrix.setIdentityM(m_transform, 0);

        float [] rot = new float[16];
        float [] trans = new float[16];
        Matrix.setIdentityM(rot, 0);
        Matrix.setIdentityM(trans, 0);
        Matrix.rotateM(rot, 0,m_rotation.z, 0.0f,1.0f,0.0f);
        Matrix.translateM(trans, 0, m_position.x,m_position.y,m_position.z);
        Matrix.multiplyMM(m_transform, 0, trans   , 0,rot , 0);

        m_dirty = false;
    }

    public void draw(ICameraObject _camera)
    {
        if(m_dirty)
            createTransform();
        float [] out = new float[16];
        Matrix.multiplyMM(out, 0,  _camera.modelViewMatrix() , 0,m_transform, 0);
        Matrix.multiplyMM(out, 0, _camera.projectionMatrix() , 0, out, 0);
        for (String key : bones.keySet())
        {
            getEffect(key).setValue(Effect.MODELVIEWPROJECTION_HANDLE, out);
            getBone(key).draw(out);
        }
    }
}
