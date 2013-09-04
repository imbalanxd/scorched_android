package scorched.engine.interfaces;

import scorched.engine.geometry.Rectangle;

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

    public void draw();
    public void update();
}
