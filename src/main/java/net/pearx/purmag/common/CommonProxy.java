package net.pearx.purmag.common;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.sif.SifStorage;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
public class CommonProxy
{
    public void preInit()
    {

    }

    public void init()
    {

    }

    public void openIfTablet(int tier)
    {

    }

    public void openPapyrus(String id)
    {

    }

    public void openTranslationDesk(BlockPos pos, World world)
    {

    }

    public SifStorage getSifStorage()
    {
        throw new NotImplementedException();
    }

    public void setupModels(IModelProvider prov) {}
}
