package ru.pearx.purmag.common.worldgen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import ru.pearx.libmc.common.structure.StructureApi;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/*
 * Created by mrAppleXZ on 07.10.17 9:42.
 */
public class WGLabMedium implements IWorldGenerator
{
    public float chance;
    public int minY, maxY;
    public List<Integer> dimList;
    public boolean dimListMode;

    public WGLabMedium(float chance, int minY, int maxY, List<Integer> dimList, boolean dimListMode)
    {
        this.chance = chance;
        this.minY = minY;
        this.maxY = maxY;
        this.dimList = dimList;
        this.dimListMode = dimListMode;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (dimList.contains(world.provider.getDimension()) == dimListMode)
        {
            if (random.nextFloat() <= chance)
            {
                int x = chunkX * 16 + 16;
                int z = chunkZ * 16 + 16;
                BlockPos pos = new BlockPos(x, world.getHeight(x, z) - 1, z);
                if (pos.getY() >= minY && pos.getY() <= maxY)
                {
                    try
                    {
                        NBTTagCompound tag = StructureApi.INSTANCE.getStructureNbt(Utils.getResourceLocation("lab_medium"));
                        System.out.println("WG START [lab_medium]");
                        StructureApi.INSTANCE.spawnStructure(tag, pos, (WorldServer) world, random);
                        System.out.println("WG END AT " + pos);
                    }
                    catch (IOException e)
                    {
                        PurMag.INSTANCE.log.error("An IOException occurred when spawning the small lab structure!", e);
                    }
                }
            }
        }
    }
}