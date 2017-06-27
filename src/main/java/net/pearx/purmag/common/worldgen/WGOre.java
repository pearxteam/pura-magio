package net.pearx.purmag.common.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.List;
import java.util.Random;

/**
 * Created by mrAppleXZ on 19.05.17 10:16.
 */
public class WGOre implements IWorldGenerator
{
    public int minVeinSize, maxVeinSize, minY, maxY;
    public float chance;
    public IBlockState state;
    public List<Integer> dims;
    public boolean whitelist;

    public WGOre(int minVeinSize, int maxVeinSize, int minY, int maxY, float chance, IBlockState state, List<Integer> dims, boolean whitelist)
    {
        this.minVeinSize = minVeinSize;
        this.maxVeinSize = maxVeinSize;
        this.minY = minY;
        this.maxY = maxY;
        this.chance = chance;
        this.state = state;
        this.dims = dims;
        this.whitelist = whitelist;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(dims.contains(world.provider.getDimension()) == whitelist)
        {
            if (random.nextFloat() <= chance)
            {
                int y = random.nextInt(maxY - minY + 1) + minY;
                int vs = random.nextInt(maxVeinSize - minVeinSize + 1) + minVeinSize;
                int x = chunkX * 16 + random.nextInt(16);
                int z = chunkZ * 16 + random.nextInt(16);
                BlockPos last = new BlockPos(x, y, z);
                for (int i = 0; i < vs; i++)
                {
                    IBlockState prev = world.getBlockState(last);
                    if (prev.getBlock().isReplaceableOreGen(prev, world, last, input -> input.getBlock() != Blocks.AIR))
                    {
                        setState(world, last, state);
                        last = new BlockPos(x + getOffset(random), y + getOffset(random), z + getOffset(random));
                    }
                }
            }
        }
    }

    public void setState(World world, BlockPos pos, IBlockState state)
    {
        world.setBlockState(pos, state, 2);
        //System.out.println(pos + " ore " + state);
    }

    public int getOffset(Random rand)
    {
        return rand.nextInt(3) - 1;
    }
}
