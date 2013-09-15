package scorched.common.shaders;

import android.util.Log;
import scorched.engine.shader.Effect;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/24/13
 * Time: 1:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextureEffect extends Effect
{

    public TextureEffect()
    {
        super();
        fragmentShaderCode =
                "precision mediump float;" +
                        "uniform sampler2D uTexture01;" +
                        "varying vec2 vTexture01;" +
                        "void main(void) " +
                        "{" +
                        "gl_FragColor = texture2D(uTexture01, vTexture01);" +
                        "}";

        vertexShaderCode =
                "attribute vec3 aVertexPosition;" +
                        "attribute vec2 aTexCoord;" +
                        "uniform mat4 uMVPMatrix;" +
                        "varying vec2 vTexture01;" +
                        "void main(void) " +
                        "{" +
                        "vTexture01 = aTexCoord;" +
                        "  gl_Position =  uMVPMatrix * vec4(aVertexPosition.xyz, 1.0);" +
                        "}";
    }

    protected void load()
    {
        setMVP();
        setTexture01();
    }

    public void unload()
    {
        unloadTexture01();
    }

}
