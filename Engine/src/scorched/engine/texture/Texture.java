package scorched.engine.texture;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import scorched.engine.interfaces.IResource;
import scorched.engine.renderer.DefaultRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/23/13
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class Texture implements IResource
{
    public static final int TEXTURE2D_ARGB = 0;
    public static final int TEXTURE2D_ALPHA = 1;

    private int m_width, m_height;
    private int m_bitmapFormat = Texture.TEXTURE2D_ARGB;
    private int     m_magFilter = GLES20.GL_LINEAR,
                    m_minFilter = GLES20.GL_LINEAR,
                    m_wrapS = GLES20.GL_CLAMP_TO_EDGE,
                    m_wrapT = GLES20.GL_CLAMP_TO_EDGE,
                    m_type = GLES20.GL_UNSIGNED_BYTE,
                    m_format = GLES20.GL_RGBA;

    private boolean m_initialised = false;
    private Bitmap m_data;
    private int m_texture = -1;

    public Texture(int [] _data, int _width, int _height, boolean _dispose)
    {
        m_height = _height;
        m_width = _width;

        m_data = createBitmap(_data);
    }

    public Texture(int [] _data, int _width, int _height, int format, boolean _dispose)
    {
        m_height = _height;
        m_width = _width;

        m_bitmapFormat = format;

        m_data = createBitmap(_data);
    }

    public Texture(Bitmap _bitmap)
    {
        m_height = _bitmap.getHeight();
        m_width = _bitmap.getWidth();

        //TODO decide on format injection
        //m_bitmapFormat = _bitmap.;

        m_data = _bitmap;
    }

    private Bitmap createBitmap(int [] _data)
    {
        Bitmap bitmap = null;
        switch(m_bitmapFormat)
        {
            case Texture.TEXTURE2D_ARGB:
                bitmap = Bitmap.createBitmap(_data, m_width, m_height, Bitmap.Config.ARGB_8888);
                break;
            case TEXTURE2D_ALPHA:
                bitmap = Bitmap.createBitmap(_data, m_width, m_height, Bitmap.Config.ALPHA_8);
                break;
        }
        return bitmap;
    }

    private void createTexture(Bitmap _bitmap)
    {
        int [] texture = new int[1];

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glGenTextures(1,texture,0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);

        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, m_magFilter);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, m_minFilter);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, m_wrapS);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, m_wrapT);

        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, _bitmap, 0);
        DefaultRenderer.checkGlError("texImage2D");
        Log.d("SCORCHED INIT", "Texture assigned to TEXTURE"+texture[0]);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        m_initialised = true;
        m_texture = texture[0];
    }

    public int getTexture()
    {
        if(m_texture == -1)
            createTexture(m_data);
        return (m_initialised ? m_texture : -1);
    }

    public int getWidth()
    {
        return m_width;
    }

    public int getHeight()
    {
        return m_height;
    }
}
