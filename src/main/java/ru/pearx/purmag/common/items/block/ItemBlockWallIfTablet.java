package ru.pearx.purmag.common.items.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.ClientUtils;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.items.base.ItemBlockBase;

/*
 * Created by mrAppleXZ on 07.07.17 16:07.
 */
public class ItemBlockWallIfTablet extends ItemBlockBase
{
    public ItemBlockWallIfTablet()
    {
        this(BlockRegistry.wall_if_tablet);
    }

    public ItemBlockWallIfTablet(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + stack.getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for (IfTier t : PurMag.INSTANCE.getIfRegistry().tiers)
            ClientUtils.setModelLocation(this, t.getTier(), "");
    }
}
