package scorched.engine.assets;

import scorched.engine.interfaces.IResource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/14/13
 * Time: 11:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssetCache
{
    private static final Map<Integer, IResource> assets = new HashMap<Integer, IResource>();

    static public IResource retrieve(int _id)
    {
        return assets.get(_id);
    }

    static public void store(Integer _id, IResource _asset)
    {
        assets.put(_id, _asset);
    }
}
