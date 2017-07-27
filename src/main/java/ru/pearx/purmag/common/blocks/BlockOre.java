package ru.pearx.purmag.common.blocks;

import net.minecraft.util.ResourceLocation;
import ru.pearx.purmag.common.PMCreativeTab;

/*
 * Created by mrAppleXZ on 27.07.17 23:24.
 */
public class BlockOre extends ru.pearx.libmc.common.blocks.BlockOre
{
    public BlockOre(ResourceLocation loc, float hardness, float lightlevel, int harvestLevel)
    {
        super(loc, hardness, lightlevel, harvestLevel);
        setCreativeTab(PMCreativeTab.INSTANCE);
    }
}
