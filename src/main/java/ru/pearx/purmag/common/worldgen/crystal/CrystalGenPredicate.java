package ru.pearx.purmag.common.worldgen.crystal;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;

import java.util.Random;

/*
 * Created by mrAppleXZ on 09.09.17 12:39.
 */
public interface CrystalGenPredicate
{
    int getY(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, int x, int z, float sif);
    boolean canGenerateHere(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif);
    default int getCrystalCount(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif)
    {
        return (int) sif;
    }
    default void beforePlace(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif, int count)
    {}
    default void afterPlace(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif, int count)
    {}
}
