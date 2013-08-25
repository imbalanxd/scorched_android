package scorched.engine.shader;

import android.opengl.GLES20;
import android.util.Log;
import scorched.engine.renderer.DefaultRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/17/13
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class EffectLoader
{
    static public int loadShader(int type, String shaderCode)
    {
        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        Log.d("SCORCHED TEST",GLES20.glGetShaderInfoLog(shader));

        DefaultRenderer.checkGlError("glCompileShader");
        return shader;
    }

    static public int loadProgram(int vertex, int fragment)
    {
        int program = GLES20.glCreateProgram();             // create empty OpenGL Program
        DefaultRenderer.checkGlError("glCreateProgram");
        GLES20.glAttachShader(program, vertex);   // add the vertex shader to program
        GLES20.glAttachShader(program, fragment); // add the fragment shader to program
        GLES20.glLinkProgram(program);                  // create OpenGL program executables

        return program;
    }

    static public int loadEffect(String vert, String frag)
    {
        int vertex = loadShader(GLES20.GL_VERTEX_SHADER, vert);
        int fragment = loadShader(GLES20.GL_FRAGMENT_SHADER, frag);
        return loadProgram(vertex,fragment);
    }
}
