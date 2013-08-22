package scorched.engine.shader;

import android.opengl.GLES20;
import android.util.Log;
import scorched.engine.renderer.DefaultRenderer;

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
    public static final String PROJECTION_HANDLE = "uPMatrix";
    public static final String MODELVIEW_HANDLE = "uMVMatrix";
    public static final String MODELVIEWPROJECTION_HANDLE = "uMVPMatrix";
    public static final String COLOR_HANDLE = "vColor";

    static protected final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +

                    "attribute vec4 aVertexPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    "  gl_Position =  uMVPMatrix * aVertexPosition;" +
                    "}";

    static protected final String fragmentShaderCode =
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
        Log.d("SCORCHED INIT", "Shader uniform "+key+" hooked to "+value);
        if(EffectManager.hasEffect(effectName))
        {
            return EffectManager.addHandle(effectName, key, value);
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
            DefaultRenderer.checkGlError("glGetAttribLocation");
            setHandle(id, handle);
        }
        //Log.d("SCORCHED", "Attribute Location "+id+" = "+handle);
        return handle;
    }

    public int getProgram()
    {
        if(!EffectManager.hasEffectValue(effectName, Effect.PROGRAM_HANDLE))
        {
            // prepare shaders and OpenGL program
//            int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER,
//                    vertexShaderCode);
//            int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER,
//                    fragmentShaderCode);
//
//            program = GLES20.glCreateProgram();             // create empty OpenGL Program
//            DefaultRenderer.checkGlError("glCreateProgram");
//            GLES20.glAttachShader(program, vertexShader);   // add the vertex shader to program
//            GLES20.glAttachShader(program, fragmentShader); // add the fragment shader to program
//            GLES20.glLinkProgram(program);                  // create OpenGL program executables
//
//            initialized = true;
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

    protected void setMVP()
    {
        GLES20.glUniformMatrix4fv(getUniformLocation(Effect.MODELVIEWPROJECTION_HANDLE), 1, false, (float[]) getValue(Effect.MODELVIEWPROJECTION_HANDLE), 0);
        DefaultRenderer.checkGlError("glUniformMatrix4fv");
    }

    public int position()
    {
        return getAttributeLocation("vPosition");
    }

    public int activate()
    {
        if(true)
        {
            GLES20.glUseProgram(getProgram());
            setColor();
            setMVP();
            return 0;
        }
        else
            return -1;
    }
}
