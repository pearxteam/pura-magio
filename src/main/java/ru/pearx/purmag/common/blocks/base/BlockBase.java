package ru.pearx.purmag.common.blocks.base;

import net.minecraft.block.material.Material;
import ru.pearx.purmag.common.PMCreativeTab;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 14.07.17 11:46.
 */
public class BlockBase extends ru.pearx.libmc.common.blocks.BlockBase
{
    public BlockBase(Material materialIn)
    {
        super(materialIn);
        setCreativeTab(PMCreativeTab.INSTANCE);
    }

    public BlockBase(String name, Material materialIn)
    {
        this(materialIn);
        setRegistryName(name);
        setUnlocalizedName(Utils.getUnlocalizedName(name));
    }
}
