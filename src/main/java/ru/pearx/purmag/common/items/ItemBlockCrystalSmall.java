package ru.pearx.purmag.common.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.ClientUtils;
import ru.pearx.purmag.common.blocks.BlockCrystalSmall;
import ru.pearx.purmag.common.blocks.BlockRegistry;

/**
 * Created by mrAppleXZ on 04.06.17 18:49.
 */
public class ItemBlockCrystalSmall extends ItemBlockBase
{
    public ItemBlockCrystalSmall()
    {
        super(BlockRegistry.crystal_small);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + BlockCrystalSmall.Type.values()[stack.getMetadata()].getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(BlockCrystalSmall.Type t : BlockCrystalSmall.Type.values())
            ClientUtils.setModelLocation(this, t.ordinal(), "");
    }
}
