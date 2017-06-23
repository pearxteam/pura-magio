package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

/**
 * Created by mrAppleXZ on 19.05.17 9:59.
 */
//registering ores: create and register BlockOre, create and register ItemBlock for BlockOre, register worldgen
public class BlockOre extends BlockBase
{
    public ItemStack dropped;

    public BlockOre(ResourceLocation loc, float hardness, float lightlevel, int harvestLevel)
    {
        super(Material.ROCK);
        setRegistryName(loc);
        setUnlocalizedName(loc.getResourcePath());
        setHardness(hardness);
        setLightLevel(lightlevel);
        setHarvestLevel("pickaxe", harvestLevel);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return dropped.getItem();
    }

    @Override
    public int quantityDropped(Random random)
    {
        return dropped.getCount();
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return dropped.getMetadata();
    }
}
