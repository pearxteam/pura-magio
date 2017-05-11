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
import net.pearx.purmag.common.tiles.TileCrystal;

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
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            Biome b = world.getBiome(new BlockPos(x, 0, z));

            boolean cont = false;
            for (Biome bio : entr.getBiomes())
            {
                if (b.equals(bio))
                {
                    cont = true;
                }
            }
            if (!cont)
                break;

            int count = (int)PurMag.proxy.getSifStorage().getPower(new GlobalChunkPos(chunkX, chunkZ, world.provider.getDimension()));

            for(int i = 0; i < count; i++)
            {
                if (entr.getType() == WGCrystalsType.SURFACE)
                {
                    int y = world.getHeight(x, z);
                    BlockPos pos = new BlockPos(x + i, y, z + i);

                    world.setBlockState(pos, BlockRegistry.crystal.getDefaultState());
                    TileEntity te = world.getTileEntity(pos);
                    if (te instanceof TileCrystal)
                        ((TileCrystal) te).setType(entr.getSip());
                    System.out.println(pos);
                }
            }
        }
    }
}
