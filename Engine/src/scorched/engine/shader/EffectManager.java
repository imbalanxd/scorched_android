package scorched.engine.shader;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 8/17/13
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class EffectManager
{
    private static final Map<String, Map<String, Integer>> handles = new HashMap<String, Map<String, Integer>>();

    public static int addEffect(String key)
    {
        if(!handles.containsKey(key))
        {
            handles.put(key, new HashMap<String, Integer>());
            Log.d("SCORCHED INIT", key + " effect handle dictionary created");
            return 0;
        }
        else
        {
            Log.d("SCORCHED INIT", key + " effect handle dictionary already exists");
            return -1;
        }

    }

    public static int addHandle(String effect, String key, Integer value)
    {
        if(value == -1)
            return -1;
        handles.get(effect).put(key, value);
        return 0;
    }

    public static boolean hasEffect(String effect)
    {
        return handles.containsKey(effect);
    }

    public static boolean hasValue(String effect, String key)
    {
        return handles.get(effect).containsKey(key);
    }

    public static boolean hasEffectValue(String effect, String key)
    {
        return hasEffect(effect) && hasValue(effect,key);
    }

    public static Map<String, Integer> getEffect(String effect)
    {
        return handles.get(effect);
    }

    public static Integer getHandle(String effect, String key)
    {
        if(hasEffectValue(effect,key))
            return handles.get(effect).get(key);
        return -1;
    }
}
