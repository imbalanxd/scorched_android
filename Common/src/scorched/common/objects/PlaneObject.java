package scorched.common.objects;

import android.util.Log;
import scorched.engine.Geometry.Vector3;

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


        createGrid();
    }

    private void createGrid()
    {
        Vector3 [] vertices = new Vector3 [(m_gridX + 1)*(m_gridY + 1)];
        int [] indices = new int [211];
        float [] texture = new float [(m_gridX + 1)*(m_gridY + 1)*3];

        createVertex(vertices);
        createIndex(indices);

        model.getLazyBone("main").setVertices(vertices);
        model.getLazyBone("main").setIndices(indices);
    }

    private void createVertex(Vector3 [] vertices)
    {
        int x1 = m_gridX + 1, y1 = m_gridY + 1;
        float widthHalf = m_width/2.0f, heightHalf = m_height/2.0f,
                widthSegment = m_width/m_gridX, heightSegment = m_height/m_gridY;

        for(int y = 0; y < y1; y++)
        {
            for(int x = 0; x < x1; x++)
            {

                vertices[y*x1 + x] = new Vector3(
                        widthSegment * x - widthHalf, 			//GRIDX
                        0.0f,                                      //HEIGHT
                        heightSegment * y - heightHalf);        //GRIDY
            }
        }
    }

    private void createVertexAndTexture(float [] vertices,float [] texture)
    {

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
    }
}
