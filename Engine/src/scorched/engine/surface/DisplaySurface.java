package scorched.engine.surface;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/27/13
 * Time: 3:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class DisplaySurface extends GLSurfaceView {
    public DisplaySurface(Context context) {
        super(context);
        setEGLContextClientVersion(2);
    }

    public DisplaySurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setEGLContextClientVersion(2);
    }
}
