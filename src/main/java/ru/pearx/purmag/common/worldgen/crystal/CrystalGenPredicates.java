package ru.pearx.purmag.common.worldgen.crystal;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import ru.pearx.libmc.common.worldgen.WGOre;

import java.util.Random;

/*
 * Created by mrAppleXZ on 09.09.17 15:09.
 */
public final class CrystalGenPredicates
{
    public static class Underground implements CrystalGenPredicate
    {
        @Override
        public int getY(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, int x, int z, float sif)
        {
            return world.getHeight(x, z) / 2;
        }

        @Override
        public boolean canGenerateHere(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif)
        {
            IBlockState ibs = world.getBlockState(pos);
            return (int)sif > 0 && ibs.getBlock().isReplaceableOreGen(ibs, world, pos, new WGOre.StonePredicate());
        }

        @Override
        public void beforePlace(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif, int count)
        {
            BlockPos.MutableBlockPos p = new BlockPos.MutableBlockPos(pos);
            for (int y1 = 0; y1 < count + 1; y1++)
            {
                p.setY(p.getY() + y1);
                world.setBlockToAir(p);
            }
        }
    }

    public static class FirstAir implements CrystalGenPredicate
    {
        @Override
        public int getY(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, int x, int z, float sif)
        {
            BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos(x, 0, z);
            for (int y = 0; y < 256; y++)
            {
                mut.setY(y);
                if (world.isAirBlock(mut))
                {
                    return y;
                }
            }
            return -1;
        }

        @Override
        public boolean canGenerateHere(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif)
        {
            return (int)sif > 0 && pos.getY() > -1;
        }
    }
}
