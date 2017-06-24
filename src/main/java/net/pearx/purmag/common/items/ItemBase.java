package net.pearx.purmag.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.PMCreativeTab;

/**
 * Created by mrAppleXZ on 11.04.17 8:27.
 */
public class ItemBase extends Item implements IModelProvider
{
    public ItemBase()
    {
        setCreativeTab(PMCreativeTab.instance);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        ClientUtils.setModelLocation(this);
    }
}
