package ru.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 17.08.17 14:49.
 */
public class BlockMicroscope extends BlockBase
{
    public BlockMicroscope()
    {
        super(Material.CIRCUITS);
        setRegistryName("microscope");
        setUnlocalizedName(Utils.getUnlocalizedName("microscope"));
        setHardness(4);
        setHarvestLevel("pickaxe", 0);
    }
}
