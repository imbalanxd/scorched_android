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
//    private String vertexShaderCode =
//            "attribute highp vec3 aVertexPosition;" +
//            "attribute highp vec2 aTexCoord;" +
//            "" +
//            "uniform mat4 uMVPMatrix;" +
//            "uniform sampler2D uTexture01;" +
//            "" +
//            "varying vec3 vTexture01;" +
//            "" +
//            "void main(void) " +
//            "{" +
//            "vTexture01 = texture2D(uTexture01, aTexCoord).xyz;" +
//            "    gl_Position =  uMVPMatrix * vec4(aVertexPosition.xyz, 1.0);" +
//            "}";
//
//    private String fragmentShaderCode =
//            "precision mediump float;" +
//            "uniform vec3 color;" +
//            "" +
//            "varying vec3 vTexture01;" +
//            "" +
//            "void main(void) " +
//            "{" +
//            "gl_FragColor = vec4(vTexture01, 1.0);" +
//            "}";

    public TextureEffect()
    {
        super();
        fragmentShaderCode =
                "precision mediump float;" +
                        "uniform vec4 vColor;" +
                        "uniform sampler2D uTexture01;" +
                        "varying vec2 vTexture01;" +
                        "void main(void) " +
                        "{" +
                        "gl_FragColor = vec4(texture2D(uTexture01, vTexture01).xyz, 1.0);" +
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
        setColor();
        setMVP();
        setTexture01();
    }

}
