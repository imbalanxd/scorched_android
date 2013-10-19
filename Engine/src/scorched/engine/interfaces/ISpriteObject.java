package scorched.engine.interfaces;

import scorched.engine.geometry.Rectangle;
import scorched.engine.geometry.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/1/13
 * Time: 11:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ISpriteObject
{
    public void addChild(ISpriteObject _child);
    public void addChildAt(int _index, ISpriteObject _child);
    public int numChildren();
    public Rectangle getBounds();
    public Boolean hitTest();

    public void setParent(ISpriteObject _parent);

    public void positionChanged();
    public void sizeChanged();

    public Vector2 getAbsolutePosition();

    public void draw(ICameraObject _camera);
    public void update();
}
