package net.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.worldgen.WGOre;

import java.util.Random;

/**
 * Created by mrAppleXZ on 19.05.17 9:59.
 */
//registering ores: create and register BlockOre, create and register ItemBlock for BlockOre, register worldgen
public class BlockOre extends BlockBase
{
    public ItemStack dropped;

    public BlockOre(String modid, String name, float hardness, int harvestLevel)
    {
        super(Material.ROCK);
        setRegistryName(new ResourceLocation(modid, "ore_" + name));
        setUnlocalizedName("ore_" + name);
        setHardness(hardness);
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
