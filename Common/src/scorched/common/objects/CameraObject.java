package scorched.common.objects;

import scorched.engine.interfaces.ICameraObject;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/20/13
 * Time: 9:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class CameraObject implements ICameraObject
{
    private float [] m_projection = new float [16];
    private float [] m_modelView = new float [16];

    public float [] projectionMatrix()
    {
        return m_projection;
    }

    public float [] modelViewMatrix()
    {
        return m_modelView;
    }

    @Override
    public void setProjection(float[] projection) {
        m_projection = projection;
    }

    @Override
    public void setModelView(float[] modelView) {
        m_modelView = modelView;
    }

    @Override
    public float[] getProjection() {
        return m_projection;
    }

    @Override
    public float[] getModelView() {
        return m_modelView;
    }
}
