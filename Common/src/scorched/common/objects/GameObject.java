package scorched.common.objects;

import scorched.engine.Game;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.interfaces.IGameObject;
import scorched.engine.shader.Effect;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/20/13
 * Time: 7:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameObject implements IGameObject
{
    protected ModelObject model;

    public GameObject ()
    {
        //model = new ModelObject();

//        model.getLazyBone("main").setVertices(new Vector3 [] { new Vector3(-0.5f,  0.5f, 0.0f),
//                new Vector3( -0.5f, -0.5f, 0.0f),
//                new Vector3(0.5f, -0.5f, 0.0f),
//                new Vector3(0.5f,  0.5f, 0.0f) });
//
//        model.getLazyBone("main").setIndices(new int [] { 0, 1, 2, 0, 2, 3 });

        Game.addGameObject(this);
    }

    @Override
    public void draw(ICameraObject _camera) {
        //Log.d("SCORCHED", "GameObject:Draw");
        if(model != null)
        {
            model.getEffect("main").setValue(Effect.COLOR_HANDLE, new float []{ 0.63671875f, 0.76953125f, 0.22265625f, 1.0f });

            model.draw(_camera);
        }
    }

    @Override
    public void update() {
        //Log.d("SCORCHED", "GameObject:Update");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void translate(float x, float y, float z) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void rotate(float x, float y, float z, float r) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
