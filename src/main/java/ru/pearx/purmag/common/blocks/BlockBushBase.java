package ru.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import ru.pearx.purmag.common.PMCreativeTab;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 31.08.17 18:06.
 */
public class BlockBushBase extends ru.pearx.libmc.common.blocks.BlockBushBase
{
    public BlockBushBase()
    {
        super();
        setCreativeTab(PMCreativeTab.INSTANCE);
    }

    public BlockBushBase(Material materialIn)
    {
        super(materialIn);
        setCreativeTab(PMCreativeTab.INSTANCE);
    }

    public BlockBushBase(String name, Material materialIn)
    {
        this(materialIn);
        setRegistryName(name);
        setUnlocalizedName(Utils.getUnlocalizedName(name));
    }

    public BlockBushBase(String name)
    {
        this(name, Material.PLANTS);
    }
}
