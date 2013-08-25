package scorched.common.objects;

import android.graphics.Color;
import android.util.Log;
import scorched.common.shaders.TextureEffect;
import scorched.engine.geometry.Vector2;
import scorched.engine.geometry.Vector3;
import scorched.engine.shader.Effect;
import scorched.engine.texture.Texture;
import scorched.engine.util.PerlinNoise;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/18/13
 * Time: 1:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlaneObject extends GameObject
{
    private float m_width, m_height;
    private int m_gridX, m_gridY;

    //_width: Total width of the plane
    //_height: Total height of the plane
    // _x: Number of divisions in the x axis
    // _y: Number of divisions in the y axis
    public PlaneObject(float _width, float _height, int _x, int _y)
    {
        super();
        m_width = _width;
        m_height = _height;
        m_gridX = _x;
        m_gridY = _y;

        model = new ModelObject();
        model.getLazyBone("main").setEffect(new TextureEffect());


        createGrid();
        createTexture();

        model.translate(0.0f,-5.0f,-20.0f);
        //model.rotate(1,0,0,90);
    }

    private void createGrid()
    {
        Vector3 [] vertices = new Vector3 [(m_gridX + 1)*(m_gridY + 1)];
        int [] indices = new int [m_gridX*m_gridY*2 + (m_gridX + 1)];
        Vector2 [] texture = new Vector2 [(m_gridX + 1)*(m_gridY + 1)];

        createVertexAndTex(vertices, texture);
        createIndex(indices);

        model.getLazyBone("main").setVertices(vertices);
        model.getLazyBone("main").setTexCoords(texture);
        model.getLazyBone("main").setIndices(indices);
    }

    private void createTexture()
    {
        int width = 64, height = 64;
        float z = (float)Math.random() * 100;

         int [] tex = new int[64*64];

        for(int i = 0; i < height*width; i++)
        {
            int val = (int)(PerlinNoise.noise(i % width, (float)Math.floor(i / width),z)*255);
            tex[i] = Color.argb(val,val,val,255);//(int)(PerlinNoise.noise(i % width, (float)Math.floor(i / width),z)*255);
        }
        //Log.d("SCORCHED TEST", tex[10]+" "+tex[30]+" "+tex[50]+" "+tex[100]+" ");

        Texture t = new Texture(tex, 64, 64, Texture.TEXTURE2D_ARGB, true);

        model.getEffect("main").setValue(Effect.TEXTURE01_HANDLE, t);
    }

    private void createVertexAndTex(Vector3 [] _vertices, Vector2[] _texCoords)
    {
        int x1 = m_gridX + 1, y1 = m_gridY + 1;
        float widthHalf = m_width/2.0f, heightHalf = m_height/2.0f,
                widthSegment = m_width/m_gridX, heightSegment = m_height/m_gridY;

        for(int y = 0; y < y1; y++)
        {
            for(int x = 0; x < x1; x++)
            {

                _vertices[y*x1 + x] = new Vector3(
                        widthSegment * x - widthHalf, 			//GRIDX
                        0.0f,                                      //HEIGHT
                        heightSegment * y - heightHalf);        //GRIDY

                _texCoords[y*x1 + x] = new Vector2((float)x/(float)(m_gridX), (float)y/(float)(m_gridY));
            }
        }
    }

    private void createIndex(int [] indices)
    {
        int x1 = m_gridX + 1, y1 = m_gridY + 1;
        float widthHalf = m_width/2.0f, heightHalf = m_height/2.0f,
                widthSegment = m_width/m_gridX, heightSegment = m_height/m_gridY;

        int a,b,x, index = 0;
        for(int y = 0; y < m_gridY; y++)
        {
            indices[index] = y * x1;
            index++;
            for(x = 0; x < m_gridX; x++)
            {
                a = (y+1)*x1 + x;
                b = (y)*x1 + (x+1);
                indices[index] = a;
                index++;
                indices[index] = b;
                index++;
            }
            indices[index] = (y+1)*x1 + x;
            index++;

            y++;
            for(x = m_gridX; x > 0; x--)
            {
                a = (y+1)*x1 + x;
                b = (y)*x1 + (x-1);
                indices[index] = a;
                index++;
                indices[index] = b;
                index++;
            }
        }
        indices[index] = m_gridY * x1;
        Log.d("SCORCHED TEST", "index "+index);
    }

    public void update()
    {
        //model.translate(0.0f,0.0f,-0.001f);
        model.rotate(0.0f,0.0f,1.0f,1.0f);
    }
}
