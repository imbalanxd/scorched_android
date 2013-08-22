package scorched.engine.renderer;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.Matrix;
import android.util.Log;
import scorched.engine.Game;
import scorched.engine.interfaces.ICameraObject;
import scorched.engine.interfaces.IGameObject;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/20/13
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultRenderer implements Renderer
{
    private final float[] mvpMatrix = new float[16];
    private final float[] projectionMatrix = new float[16];
    private final float[] mVMatrix = new float[16];
    private final float[] mRotationMatrix = new float[16];

    private ICameraObject camera;

    public void setCamera(ICameraObject _camera)
    {
        camera = _camera;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config)
    {
        GLES20.glClearColor(0.2f, 0.5f, 0.2f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        //Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
        //Matrix.perspectiveM(projectionMatrix, 0, 45, ratio, 0.1f, 100.0f);
        projectionMatrix[0] = 1.408291220664978f;
        projectionMatrix[5] = 2.4142136573791504f;
        projectionMatrix[10] = -1.0002000331878662f;
        projectionMatrix[11] = -1.0f;
        projectionMatrix[14] = -0.20002000033855438f;

        camera.setProjection(projectionMatrix);

        Matrix.setLookAtM(mVMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        Matrix.setIdentityM(mVMatrix, 0);

        camera.setModelView(mVMatrix);
    }

    public void onUpdateFrame()
    {
        for(IGameObject object : Game.gameObjects)
        {
            object.update();
        }
    }

    @Override
    public void onDrawFrame(GL10 gl)
    {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);


        for(IGameObject object : Game.gameObjects)
        {
            object.draw(camera);
        }
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("SCORCHED", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}
