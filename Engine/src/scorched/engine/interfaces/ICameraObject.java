package scorched.engine.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/21/13
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICameraObject
{
    public float [] projectionMatrix();
    public float [] modelViewMatrix();

    public void setProjection(float [] projection);
    public void setModelView(float [] modelView);
}
