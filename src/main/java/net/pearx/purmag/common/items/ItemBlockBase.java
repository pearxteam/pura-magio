package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;

/**
 * Created by mrAppleXZ on 14.05.17 18:12.
 */
public class ItemBlockBase extends ItemBlock implements IModelProvider
{
    public ItemBlockBase(Block block)
    {
        super(block);
        setRegistryName(block.getRegistryName());
        setUnlocalizedName(block.getUnlocalizedName());
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        ClientUtils.setModelLocation(this);
    }
}
