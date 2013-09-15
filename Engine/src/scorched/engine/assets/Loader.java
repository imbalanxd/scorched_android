package scorched.engine.assets;

import android.content.Context;
import android.graphics.BitmapFactory;
import scorched.engine.texture.Texture;

/**
 * Created with IntelliJ IDEA.
 * User: Robin
 * Date: 9/14/13
 * Time: 11:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Loader
{
    private static Context context;

    static public void setContext(Context _context)
    {
        context = _context;
    }

    public static Texture loadTexture(int _id)
    {
        Texture texture = (Texture) AssetCache.retrieve(_id);
        if(texture == null)
        {
            texture = new Texture(BitmapFactory.decodeResource(context.getResources(), _id));
            AssetCache.store(_id, texture);
        }
        return texture;
    }
}
