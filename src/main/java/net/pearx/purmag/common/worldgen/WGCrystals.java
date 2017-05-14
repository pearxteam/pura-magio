package net.pearx.purmag.common.worldgen;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.GlobalChunkPos;
import net.pearx.purmag.common.blocks.BlockRegistry;
import net.pearx.purmag.common.blocks.BlockSingleSip;

import java.util.Random;

/**
 * Created by mrAppleXZ on 04.05.17 16:07.
 */
public class WGCrystals implements IWorldGenerator
{
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        for(WGCrystalsEntry entr : WorldgenRegistry.crystalGen)
        {
            if(entr.getBlacklistedDims().contains(world.provider.getDimension())) continue;

            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = 0;

            boolean cont = false;
            if(entr.getBiomes().length > 0)
            {
                for (Biome b : entr.getBiomes())
                {
                    if (b.equals(world.getBiome(new BlockPos(x, y, z))))
                    {
                        cont = true;
                        break;
                    }
                }
            }
            else
                cont = true;

            if (!cont) continue;

            int sif = (int)PurMag.proxy.getSifStorage().getPower(new GlobalChunkPos(chunkX, chunkZ, world.provider.getDimension()));

            if (sif > 0)
            {
                switch (entr.getType())
                {
                    case SURFACE:
                        y = world.getHeight(x, z);
                        break;
                    case UNDERGROUND:
                        y = world.getHeight(x, z) / 2;
                        for (int y1 = 0; y1 <= 2; y1++)
                        {
                            world.setBlockToAir(new BlockPos(x, y + y1, z));
                        }
                        break;
                    case FIRSTAIR:
                        for(int y1 = 0; y1 < 256; y1++)
                        {
                            if(world.isAirBlock(new BlockPos(x, y1, z)))
                            {
                                y = y1;
                                break;
                            }
                        }
                        break;
                }

                for (int i = 0; i < sif; i++)
                {
                    BlockPos pos = new BlockPos(x, y + i, z);
                    world.setBlockState(pos, BlockRegistry.crystal.getDefaultState().withProperty(BlockSingleSip.SIPTYPE, entr.getSip()));
                }
            }
        }
    }
}
