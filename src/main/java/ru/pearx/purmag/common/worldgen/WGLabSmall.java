package ru.pearx.purmag.common.worldgen;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import ru.pearx.lib.Size;
import ru.pearx.libmc.common.structure.StructureApi;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/*
 * Created by mrAppleXZ on 26.08.17 17:14.
 */
public class WGLabSmall implements IWorldGenerator
{
    public float chance;
    public List<Integer> dimList;
    public boolean dimListMode;

    public WGLabSmall(float chance, List<Integer> dimList, boolean dimListMode)
    {
        this.chance = chance;
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
                try
                {
                    NBTTagCompound tag = StructureApi.INSTANCE.getStructureNbt(Utils.getRegistryName("lab_small"));
                    System.out.println("WG START");
                    int x = chunkX * 16 + 16;
                    int z = chunkZ * 16 + 16;
                    BlockPos pos = new BlockPos(x, world.getHeight(x, z) - 7, z);
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