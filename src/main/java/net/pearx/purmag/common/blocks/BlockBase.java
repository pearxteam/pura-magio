package net.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.pearx.purmag.common.PMCreativeTab;

/**
 * Created by mrAppleXZ on 08.04.17 19:01.
 */
public class BlockBase extends Block
{
    public BlockBase(Material materialIn)
    {
        super(materialIn);
        setCreativeTab(PMCreativeTab.instance);
    }
}
