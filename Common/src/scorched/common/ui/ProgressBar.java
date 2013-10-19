package scorched.common.ui;

import scorched.engine.ui.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/28/13
 * Time: 6:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProgressBar extends Sprite
{
    private float m_progress = 0.0f;
    private Quad m_bar;

    public ProgressBar(int _width, int _height)
    {
        super();
        m_width = _width;
        m_height = _height;
        m_bar = new Quad(0, this.m_height);
        this.addChild(m_bar);
    }

    public void setProgress(float _progress)
    {
        m_progress = Math.max(0.0f, Math.min(1.0f, _progress));
        m_bar.setWidth(this.m_width * m_progress);
    }

    public float getProgress()
    {
        return m_progress;
    }
}
