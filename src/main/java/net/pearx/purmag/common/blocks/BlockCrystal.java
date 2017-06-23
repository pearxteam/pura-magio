package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemRegistry;

import java.util.*;

/**
 * Created by mrAppleXZ on 08.04.17 17:46.
 */
public class BlockCrystal extends BlockSingleSip
{
    public BlockCrystal()
    {
        super(Material.ROCK);
        setRegistryName(Utils.getRegistryName("crystal"));
        setUnlocalizedName("crystal");
        setHardness(2);
        setLightLevel(5);
        setHarvestLevel("pickaxe", 1);
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ItemRegistry.crystal_shard;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return random.nextInt(4) + 3;
    }
}
