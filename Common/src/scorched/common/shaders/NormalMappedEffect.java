package scorched.common.shaders;

import scorched.engine.shader.Effect;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/29/13
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class NormalMappedEffect extends Effect
{
    public NormalMappedEffect()
    {
        super();
        fragmentShaderCode =
                "precision mediump float;" +
                        "uniform mat4 uMVPMatrix;" +
                        "uniform vec3 uDirLight01;" +
                        "uniform vec4 uDirLightColor01;" +
                        "uniform vec4 vColor;" +
                        "uniform sampler2D uTexture01;" +
                        "varying vec2 vTexture01;" +
                        "uniform sampler2D uTexture02;" +
                        "varying vec2 vTexture02;" +
                        "void main(void) " +
                        "{" +
                        "vec4 transformedDirectional = uMVPMatrix * vec4(uDirLight01, 0.0);"+
                        "float diffuse = max(dot(transformedDirectional.xyz, normalize(texture2D(uTexture02, vTexture02).xyz)), 0.0);"+
                        "gl_FragColor = vColor * diffuse;" +
                        "}";

        vertexShaderCode =
                "attribute vec3 aVertexPosition;" +
                        "attribute vec2 aTexCoord;" +
                        "uniform mat4 uMVPMatrix;" +
                        "varying vec2 vTexture02;" +
                        "void main(void) " +
                        "{" +
                        "vTexture02 = aTexCoord;" +
                        "  gl_Position =  uMVPMatrix * vec4(aVertexPosition.xyz, 1.0);" +
                        "}";
    }

    protected void load()
    {
        setColor();
        setMVP();
        setTexture02();
        setDirLight01();
        setDirLightColor01();
    }
}
