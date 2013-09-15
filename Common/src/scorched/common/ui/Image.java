package scorched.common.ui;

import scorched.common.shaders.TextureEffect;
import scorched.engine.renderer.RenderObject;
import scorched.engine.shader.Effect;
import scorched.engine.texture.Texture;
import scorched.engine.ui.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/4/13
 * Time: 10:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class Image extends Sprite
{
    private Texture m_texture;

    public Image(Texture _texture)
    {
        m_texture = _texture;
        m_width = _texture.getWidth();
        m_height = _texture.getHeight();
    }

    public Image(Texture _texture, int _width, int _height)
    {
        m_texture = _texture;
        m_width = _width;
        m_height = _height;
    }

    protected void setEffect()
    {
        m_view = new RenderObject();
        m_view.setEffect(new TextureEffect());
    }

    protected void setProperties()
    {
        m_view.getEffect().setValue(Effect.TEXTURE01_HANDLE, m_texture);
    }

    public void setTexture(Texture _texture)
    {
        m_texture = _texture;
    }

    public Texture getTexture()
    {
        return m_texture;
    }

    public void update()
    {
        rotate(0.5f);
    }
}
