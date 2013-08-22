package scorched.common.objects;

import android.opengl.GLES20;
import android.util.Log;
import scorched.engine.Geometry.Vector3;
import scorched.engine.interfaces.IRenderObject;
import scorched.engine.renderer.DefaultRenderer;
import scorched.engine.shader.Effect;

import java.nio.*;

/**
 * Created with IntelliJ BufferEA.
 * User: Robin
 * Date: 7/21/13
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class RenderObject implements IRenderObject
{
    protected FloatBuffer vertexBuffer;
    protected int vertexSize = 3;
    protected float [] vertices;
    protected int vertexLength;

    protected ShortBuffer indexBufferShort;
    protected IntBuffer indexBuffer;
    protected int indexSize = 1;
    protected int [] indices;
    protected int indexLength;

    protected FloatBuffer texCoordBuffer;
    protected int texCoordSize = 2;
    protected float [] texCoords;

    protected FloatBuffer normalBuffer;
    protected int normalSize = 3;

    protected float [] normals;

    //Shader assigned to this object
    protected Effect effect;

    public RenderObject()
    {
        effect = new Effect();
    }

    @Override
    public void dispose() {
    }

    @Override
    public float[] getVertices() {
        return new float[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    @Override
    public void setVertices(Vector3[] _vertices) {
        setVertices(_vertices, 3);
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setVertices(Vector3[] _vertices, int itemSize) {
        vertexSize = itemSize;
        vertexLength = _vertices.length;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_vertices.length * itemSize * 4);
        buffer.order(ByteOrder.nativeOrder());

        vertexBuffer = buffer.asFloatBuffer();
        for(int i=0; i < vertexLength; i++)
        {
            Vector3 vertex = _vertices[i];
            vertexBuffer.put(vertex.x);
            vertexBuffer.put(vertex.y);
            vertexBuffer.put(vertex.z);
        }
        vertexBuffer.position(0);
        Log.d("SCORCHED", "vertices set");
    }

    @Override
    public void addVertices(Vector3[] _vertices) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float[] getTexCoords() {
        return new float[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FloatBuffer getTexCoordBuffer() {
        return texCoordBuffer;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setTexCoords(float[] _texCoords) {
        setTexCoords(_texCoords, 2);
    }

    @Override
    public void setTexCoords(float[] _texCoords, int itemSize) {
        texCoordSize = itemSize;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_texCoords.length * 4);
        buffer.order(ByteOrder.nativeOrder());

        texCoordBuffer = buffer.asFloatBuffer();
        texCoordBuffer.put(_texCoords);
        texCoordBuffer.position(0);
    }

    @Override
    public void addTexCoords(float[] _vertices) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float[] getIndices() {
        return new float[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ShortBuffer getIndexBuffer() {
        return indexBufferShort;
    }

    @Override
    public void setIndices(short[] _indices) {
        setIndices(_indices, 1);
    }

    @Override
    public void setIndices(short[] _indices, int itemSize) {
        indexSize = itemSize;
        indexLength = _indices.length;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_indices.length * 2);
        buffer.order(ByteOrder.nativeOrder());

        indexBufferShort = buffer.asShortBuffer();
        indexBufferShort.put(_indices);
        indexBufferShort.position(0);
        Log.d("SCORCHED", "indices set");
    }

    @Override
    public void addIndices(short[] _indices) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setIndices(int[] _indices) {
        setIndices(_indices, 1);
    }

    @Override
    public void setIndices(int[] _indices, int itemSize) {
        indexSize = itemSize;
        indexLength = _indices.length;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_indices.length * 4);
        buffer.order(ByteOrder.nativeOrder());

        indexBuffer = buffer.asIntBuffer();
        indexBuffer.put(_indices);
        indexBuffer.position(0);
        Log.d("SCORCHED", "indices set");
    }

    @Override
    public float[] getNormals() {
        return new float[0];  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FloatBuffer getNormalBuffer() {
        return normalBuffer;
    }

    @Override
    public void setNormals(float[] _normals) {
        setNormals(_normals, 3);
    }

    @Override
    public void setNormals(float[] _normals, int itemSize) {
        normalSize = itemSize;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_normals.length * 4);
        buffer.order(ByteOrder.nativeOrder());

        normalBuffer = buffer.asFloatBuffer();
        normalBuffer.put(_normals);
        normalBuffer.position(0);
    }

    @Override
    public void addNormals(float[] _vertices) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Effect getEffect() {
        return effect;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void draw(float[] _mvpMatrix)
    {
        if(effect == null)
            effect = new Effect();
        effect.activate();

        if(vertexBuffer != null)
        {
            GLES20.glEnableVertexAttribArray(effect.getAttributeLocation(Effect.VERTEX_HANDLE));
            GLES20.glVertexAttribPointer(effect.getAttributeLocation(Effect.VERTEX_HANDLE), vertexSize,
                                                                        GLES20.GL_FLOAT, false,
                                                                        vertexSize * 4, vertexBuffer);
            DefaultRenderer.checkGlError("glVertexAttribPointer");
            Log.d("SCORCHED RUN", "Vertices sent");
        }
        if(texCoordBuffer != null)
        {
            GLES20.glEnableVertexAttribArray(effect.getAttributeLocation(Effect.TEXCOORD_HANDLE));
            GLES20.glVertexAttribPointer(effect.getAttributeLocation(Effect.TEXCOORD_HANDLE), texCoordSize,
                    GLES20.GL_FLOAT, false,
                    texCoordSize * 4, texCoordBuffer);
        }
        if(normalBuffer != null)
        {
            GLES20.glEnableVertexAttribArray(effect.getAttributeLocation(Effect.NORMAL_HANDLE));
            GLES20.glVertexAttribPointer(effect.getAttributeLocation(Effect.NORMAL_HANDLE), normalSize,
                    GLES20.GL_FLOAT, false,
                    normalSize * 4, normalBuffer);
        }

        if(indexBuffer != null)
            GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, indexLength,
                    GLES20.GL_UNSIGNED_INT, indexBuffer);
        else
            GLES20.glDrawElements(GLES20.GL_TRIANGLE_STRIP, vertexLength, GLES20.GL_FLOAT, 0);
        DefaultRenderer.checkGlError("glDrawElements");
        Log.d("SCORCHED RUN", "Draw");
    }
}
