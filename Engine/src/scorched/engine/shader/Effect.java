package scorched.engine.shader;

import android.opengl.GLES20;
import android.util.Log;
import scorched.engine.renderer.DefaultRenderer;
import scorched.engine.texture.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 7/23/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Effect
{
    public static final String PROGRAM_HANDLE = "Program";
    public static final String VERTEX_HANDLE = "aVertexPosition";
    public static final String NORMAL_HANDLE = "aVertexNormal";
    public static final String TEXCOORD_HANDLE = "aTexCoord";
    public static final String TEXTURE01_HANDLE = "uTexture01";
    public static final String TEXTURE02_HANDLE = "uTexture02";
    public static final String PROJECTION_HANDLE = "uPMatrix";
    public static final String MODELVIEW_HANDLE = "uMVMatrix";
    public static final String MODELVIEWPROJECTION_HANDLE = "uMVPMatrix";
    public static final String COLOR_HANDLE = "vColor";
    public static final String DIRLIGHT01_HANDLE = "uDirLight01";
    public static final String DIRLIGHTCOLOR01_HANDLE = "uDirLightColor01";

    protected String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
            "attribute vec3 aVertexPosition;" +
            "void main() {" +
            "  gl_Position =  uMVPMatrix * vec4(aVertexPosition.xyz, 1.0);" +
            "}";

    protected String fragmentShaderCode =
            "precision mediump float;" +
            "uniform vec4 vColor;" +
            "void main() {" +
            "  gl_FragColor = vColor;" +
            "}";

    //protected static Map<String, Integer> handles;
    //protected static boolean initialized;
    //protected static int program;

    protected Map<String, Object> values;
    protected String effectName;

    public Effect()
    {
        effectName =  getClass().getName();
        EffectManager.addEffect(effectName);
        //handles = new HashMap<String, Integer>();
        values = new HashMap<String, Object>();
    }

    protected int addHandle(String key)
    {
        if(EffectManager.hasEffectValue(effectName, key))
        {
            //handles.put(key, -1);
            values.put(key, null);
        }
        return -1;
    }

    protected int setHandle(String key, Integer value)
    {
        if(EffectManager.hasEffect(effectName))
        {
            if(EffectManager.addHandle(effectName, key, value) == 0)
            {
                Log.d("SCORCHED INIT", "Shader value "+key+" hooked to "+value);
                return 0;
            }
        }
        return -1;
    }

    public int setValue(String key, Object value)
    {
        if(values != null)
            values.put(key, value);
        return -1;
    }

    protected int getHandle(String key)
    {
        return EffectManager.getHandle(effectName, key);
    }

    protected Object getValue(String id)
    {
        if(values != null && values.containsKey(id))
            return values.get(id);
        else
            return -1;
    }

    protected int getUniformLocation(String id)
    {
        int handle = getHandle(id);
        if(handle == -1)
        {
            handle = GLES20.glGetUniformLocation(getProgram(), id);
            DefaultRenderer.checkGlError("glGetUniformLocation");
            setHandle(id, handle);
        }
        Log.d("SCORCHED RUN", "Uniform Location "+id+" = "+handle);
        return handle;
    }

    public int getAttributeLocation(String id)
    {
        int handle = getHandle(id);
        if(handle == -1)
        {
            handle = GLES20.glGetAttribLocation(getProgram(), id);
            try
            {
                DefaultRenderer.checkGlError("glGetAttribLocation");
            }
            catch(RuntimeException _e)
            {
                Log.d("SCORCHED RUN", id+" does not exist in shader");
                handle = -2;
            }
            setHandle(id, handle);
        }
        //Log.d("SCORCHED", "Attribute Location "+id+" = "+handle);
        return handle;
    }

    public int getProgram()
    {
        if(!EffectManager.hasEffectValue(effectName, Effect.PROGRAM_HANDLE))
        {
            EffectManager.addHandle(effectName, Effect.PROGRAM_HANDLE, EffectLoader.loadEffect(vertexShaderCode, fragmentShaderCode));

            Log.d("SCORCHED INIT", "Shader Program created: "+ EffectManager.getHandle(effectName, Effect.PROGRAM_HANDLE));
        }
        return EffectManager.getHandle(effectName, Effect.PROGRAM_HANDLE);
    }



    protected void setColor()
    {
        GLES20.glUniform4fv(getUniformLocation(Effect.COLOR_HANDLE), 1, (float[])getValue(Effect.COLOR_HANDLE), 0);
        DefaultRenderer.checkGlError("glUniform4fv");
    }

    protected void setDirLightColor01()
    {
        GLES20.glUniform4fv(getUniformLocation(Effect.DIRLIGHTCOLOR01_HANDLE), 1, (float[])getValue(Effect.DIRLIGHTCOLOR01_HANDLE), 0);
        DefaultRenderer.checkGlError("glUniform4fv");
    }

    protected void setDirLight01()
    {
        GLES20.glUniform3fv(getUniformLocation(Effect.DIRLIGHT01_HANDLE), 1, (float[])getValue(Effect.DIRLIGHT01_HANDLE), 0);
        DefaultRenderer.checkGlError("glUniform4fv");
    }

    protected void setMVP()
    {
        GLES20.glUniformMatrix4fv(getUniformLocation(Effect.MODELVIEWPROJECTION_HANDLE), 1, false, (float[]) getValue(Effect.MODELVIEWPROJECTION_HANDLE), 0);
        DefaultRenderer.checkGlError("glUniformMatrix4fv");
    }

    protected void setTexture01()
    {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,((Texture)(getValue(Effect.TEXTURE01_HANDLE))).getTexture());
        GLES20.glUniform1i(getUniformLocation(Effect.TEXTURE01_HANDLE), 0);
    }

    protected void unloadTexture01()
    {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,0);
    }

    protected void setTexture02()
    {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,((Texture)(getValue(Effect.TEXTURE02_HANDLE))).getTexture());
        GLES20.glUniform1i(getUniformLocation(Effect.TEXTURE02_HANDLE), 0);
    }

    protected void unloadTexture02()
    {
        GLES20.glActiveTexture(GLES20.GL_TEXTURE1);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,0);
    }

    public int position()
    {
        return getAttributeLocation("vPosition");
    }

    public int activate()
    {
        if(true)
        {
            loadProgram();
            load();
            return 0;
        }
        else
            return -1;
    }

    protected void loadProgram()
    {
        GLES20.glUseProgram(getProgram());
    }

    protected void load()
    {
        setColor();
        setMVP();
    }

    public void unload()
    {

    }
}
