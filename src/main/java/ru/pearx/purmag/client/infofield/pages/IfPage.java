package ru.pearx.purmag.client.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/*
 * Created by mrAppleXZ on 08.01.18 14:44.
 */
@SideOnly(Side.CLIENT)
public abstract class IfPage implements IIfPage
{
    private boolean useCache = true;
    private IPRenderer cache;

    public boolean isUseCache()
    {
        return useCache;
    }

    public void setUseCache(boolean useCache)
    {
        this.useCache = useCache;
    }

    @Override
    public IPRenderer getRenderer()
    {
        if(useCache)
        {
            if(cache == null)
                cache = createNewRenderer();
            return cache;
        }
        else
            return createNewRenderer();
    }

    public abstract IPRenderer createNewRenderer();
}
