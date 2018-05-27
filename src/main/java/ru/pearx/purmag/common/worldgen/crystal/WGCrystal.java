package ru.pearx.purmag.common.worldgen.crystal;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import ru.pearx.carbide.mc.common.misc.GlobalChunkPos;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.tiles.TileSingleSip;

import java.util.List;
import java.util.Random;

/*
 * Created by mrAppleXZ on 09.09.17 12:30.
 */
public class WGCrystal implements IWorldGenerator
{
    public CrystalGenPredicate predicate;
    public String sipType;
    public List<Integer> dimList;
    public boolean dimListMode;

    public WGCrystal(CrystalGenPredicate predicate, String sipType, List<Integer> dimList, boolean dimListMode)
    {
        this.predicate = predicate;
        this.sipType = sipType;
        this.dimList = dimList;
        this.dimListMode = dimListMode;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if(dimList.contains(world.provider.getDimension()) == dimListMode)
        {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            float sif = world.getChunkFromChunkCoords(chunkX, chunkZ).getCapability(CapabilityRegistry.SIF_STORAGE, null).getPower();
            int y = predicate.getY(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, x, z, sif);
            BlockPos pos = new BlockPos(x, y, z);

            if (predicate.canGenerateHere(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, pos, sif))
            {
                int count = predicate.getCrystalCount(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, pos, sif);
                predicate.beforePlace(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, pos, sif, count);
                BlockPos.MutableBlockPos genPos = new BlockPos.MutableBlockPos(pos);
                for (int i = 0; i < count; i++)
                {
                    genPos.setY(pos.getY() + i);
                    world.setBlockState(genPos, BlockRegistry.crystal.getDefaultState(), 2);
                    TileSingleSip tss = new TileSingleSip();
                    tss.setType(sipType);
                    world.setTileEntity(genPos, tss);
                }
                PurMag.INSTANCE.debug("Crystal " + sipType + " " + pos);
                predicate.afterPlace(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, pos, sif, count);
            }
        }
    }
}
