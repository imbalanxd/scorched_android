package scorched.engine.stage;


import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/21/13
 * Time: 9:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class StageManager
{


    private Stage m_empty;
    private HashMap<String, Stage> m_stages;
    private Stage m_currentStage;

    public StageManager()
    {
        m_stages = new HashMap<String, Stage>();
    }

    public void setup()
    {
        new Stage(Stage.EMPTY_STAGE);
        activateStage(Stage.EMPTY_STAGE);
    }

    public void addStage(Stage _stage)
    {
        m_stages.put(_stage.getName(), _stage);
    }

    public void removeStage(String _name)
    {
        m_stages.remove(_name);
    }

    public Stage getStage(String _name)
    {
        return m_stages.get(_name);
    }

    public void activateStage(String _name)
    {
        if(m_currentStage != null)
        {

        }
        m_currentStage = getStage(_name);
        m_currentStage.initialize();
    }

    public Stage getCurrentStage()
    {
        return m_currentStage;
    }
}
