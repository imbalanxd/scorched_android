package scorched.engine.renderer;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import scorched.engine.geometry.Vector2;
import scorched.engine.geometry.Vector3;
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
    protected int texCoordLength;
    protected float [] texCoords;

    protected FloatBuffer normalBuffer;
    protected int normalSize = 3;

    protected float [] normals;

    //Shader assigned to this object
    protected Effect effect;

    Vector3 m_origin = new Vector3();
    Vector3 m_position = new Vector3();
    Vector3 m_rotation = new Vector3();
    private boolean m_dirty = true;
    private float[] m_transform = new float[16];

    public RenderObject()
    {
        //effect = new Effect();
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
    public void setTexCoords(Vector2[] _texCoords) {
        setTexCoords(_texCoords, 2);
    }

    @Override
    public void setTexCoords(Vector2[] _texCoords, int itemSize) {
        texCoordSize = itemSize;
        texCoordLength = _texCoords.length;

        ByteBuffer buffer = ByteBuffer.allocateDirect(_texCoords.length * itemSize * 4);
        buffer.order(ByteOrder.nativeOrder());

        texCoordBuffer = buffer.asFloatBuffer();
        for(int i=0; i < texCoordLength; i++)
        {
            Vector2 texCoord = _texCoords[i];
            texCoordBuffer.put(texCoord.x);
            texCoordBuffer.put(texCoord.y);
        }
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
        return effect;
    }

    @Override
    public void setEffect(Effect _effect) {
        effect = _effect;
    }

    public void rotate(float x, float y, float z, float theta)
    {
        m_rotation.add(x * theta, y * theta, z * theta);
        changed();
    }

    public void translate(float x, float y, float z)
    {
        m_position.add(x, y, z);
        changed();
    }

    @Override
    public void setRotation(Vector3 _rotation) {
        m_rotation = _rotation;
        changed();
    }

    @Override
    public void setTranslation(Vector3 _translation) {
        m_position = _translation;
        changed();
    }

    @Override
    public void setOrigin(Vector3 _origin) {
        m_origin = _origin;
    }

    private void changed()
    {
        m_dirty = true;
    }

    protected void createTransform()
    {
        //TODO fix this up for general purpose
        Matrix.setIdentityM(m_transform, 0);

        float [] rot = new float[16];
        float [] trans = new float[16];
        Matrix.setIdentityM(rot, 0);
        Matrix.setIdentityM(trans, 0);

        Matrix.translateM(trans, 0, m_origin.x,m_origin.y,m_origin.z);
        Matrix.rotateM(rot, 0,m_rotation.z, 0.0f,0.0f,1.0f);
        Matrix.multiplyMM(m_transform, 0, rot   , 0,trans , 0);

        Matrix.setIdentityM(trans, 0);
        Matrix.translateM(trans, 0, m_position.x,m_position.y,m_position.z);
        Matrix.multiplyMM(m_transform, 0, trans   , 0,m_transform , 0);

        m_dirty = false;
    }

    @Override
    public void draw(float[] _projection, float[] _modelView)
    {
        if(effect == null)
            effect = new Effect();

        if(m_dirty)
            createTransform();
        float [] out = new float[16];
        Matrix.multiplyMM(out, 0,  _modelView, 0, m_transform, 0);
        Matrix.multiplyMM(out, 0, _projection, 0, out, 0);
        effect.setValue(Effect.MODELVIEWPROJECTION_HANDLE, out);

        effect.activate();

        if(effect.getAttributeLocation(Effect.VERTEX_HANDLE) >= 0)
        {
            GLES20.glEnableVertexAttribArray(effect.getAttributeLocation(Effect.VERTEX_HANDLE));
            GLES20.glVertexAttribPointer(effect.getAttributeLocation(Effect.VERTEX_HANDLE), vertexSize,
                                                                        GLES20.GL_FLOAT, false,
                                                                        vertexSize * 4, vertexBuffer);
            DefaultRenderer.checkGlError("glVertexAttribPointer");
            Log.d("SCORCHED RUN", "Vertices sent");
        }
        if(effect.getAttributeLocation(Effect.TEXCOORD_HANDLE) >= 0)
        {
            GLES20.glEnableVertexAttribArray(effect.getAttributeLocation(Effect.TEXCOORD_HANDLE));
            GLES20.glVertexAttribPointer(effect.getAttributeLocation(Effect.TEXCOORD_HANDLE), texCoordSize,
                    GLES20.GL_FLOAT, false,
                    texCoordSize * 4, texCoordBuffer);
        }
        if(effect.getAttributeLocation(Effect.NORMAL_HANDLE) >= 0)
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

        effect.unload();
    }
}
