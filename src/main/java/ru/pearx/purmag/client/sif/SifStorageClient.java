package ru.pearx.purmag.client.sif;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.common.sif.SifStorage;

/**
 * Created by mrAppleXZ on 27.06.17 15:31.
 */
@SideOnly(Side.CLIENT)
public class SifStorageClient extends SifStorage
{
    @Override
    public void markDirty()
    {

    }
}
