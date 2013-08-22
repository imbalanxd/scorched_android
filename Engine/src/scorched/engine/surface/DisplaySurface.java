package scorched.engine.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.interfaces.IGameObject;
import scorched.engine.renderer.DefaultRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/27/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplaySurface extends GLSurfaceView {

    private DefaultRenderer renderer;

    public DisplaySurface(Context context) {
        super(context);
        setEGLContextClientVersion(2);
    }

    public DisplaySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
    }

    public void initialize()
    {
        renderer = new DefaultRenderer();
        this.setRenderer(renderer);
    }

    public void setCamera(ICameraObject _camera)
    {
        renderer.setCamera(_camera);
    }
}
