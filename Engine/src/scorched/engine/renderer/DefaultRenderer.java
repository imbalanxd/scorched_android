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
    private ICameraObject m_camera;
    private ICameraObject m_hudCamera;

    public void setCamera(ICameraObject _camera)
    {
        m_camera = _camera;
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
        GLES20.glEnable( GLES20.GL_CULL_FACE );
        //GLES20.glCullFace(GLES20.GL_BACK);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        //GLES20.glClearDepthf(1.0f);
        //GLES20.glDepthFunc( GLES20.GL_LEQUAL );
       // GLES20.glDepthMask( true );
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(m_camera.getProjection(), 0, -ratio, ratio, -1, 1, 1, 1000);
        Matrix.setIdentityM(m_camera.getModelView(), 0);

        Matrix.orthoM(m_hudCamera.getProjection(),0,0,width,0,height,0,10);
        Matrix.setIdentityM(m_hudCamera.getModelView(), 0);
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
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);


        for(IGameObject object : Game.gameObjects)
        {
            object.draw(m_camera);
        }

        Game.hud.draw(m_hudCamera);
    }

    public static void checkGlError(String glOperation) {
        int error;
        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e("SCORCHED", glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void setHUDCamera(ICameraObject hudCamera) {
        m_hudCamera = hudCamera;
    }
}
