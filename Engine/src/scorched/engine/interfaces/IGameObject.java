package scorched.engine.interfaces;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/21/13
 * Time: 4:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IGameObject
{
    public void draw(float[] _mvpMatrix);
    public void update();

    public void translate(float x, float y, float z);
    public void rotate(float x, float y, float z, float r);
}
