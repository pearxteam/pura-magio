package ru.pearx.purmag.common.worldgen;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.pearx.libmc.common.worldgen.WGGround;
import ru.pearx.libmc.common.worldgen.WGOre;
import ru.pearx.libmc.common.worldgen.WGOreOnOre;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.blocks.BlockCrystalSmall;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.config.*;
import ru.pearx.purmag.common.worldgen.crystal.CrystalGenPredicate;
import ru.pearx.purmag.common.worldgen.crystal.CrystalGenPredicates;
import ru.pearx.purmag.common.worldgen.crystal.WGCrystal;

import java.util.Random;

/**
 * Created by mrAppleXZ on 04.05.17 15:10.
 */
public class WorldgenRegistry
{
    public static void setup()
    {
        PMConfig config = PurMag.INSTANCE.config;
        if(config.genCrystalSea.generate)
        {
            ConfigCrystalGenEntry entr = config.genCrystalSea;
            GameRegistry.registerWorldGenerator(new WGCrystal(new CrystalGenPredicate()
            {
                @Override
                public int getY(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, int x, int z, float sif)
                {
                    return world.getHeight(x, z);
                }

                @Override
                public boolean canGenerateHere(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif)
                {
                    Biome b = world.getBiome(pos);
                    return (int)sif > 0 && (BiomeDictionary.hasType(b, BiomeDictionary.Type.BEACH) || BiomeDictionary.hasType(b, BiomeDictionary.Type.RIVER));
                }
            }, "sea", entr.dimList, entr.dimListMode), 5);
        }
        if(config.genCrystalRock.generate)
        {
            ConfigCrystalGenEntry entr = config.genCrystalRock;
            GameRegistry.registerWorldGenerator(new WGCrystal(new CrystalGenPredicates.Underground(), "rock", entr.dimList, entr.dimListMode), 5);
        }
        if(config.genCrystalFlame.generate)
        {
            ConfigCrystalGenEntry entr = config.genCrystalFlame;
            GameRegistry.registerWorldGenerator(new WGCrystal(new CrystalGenPredicates.FirstAir(), "flame", entr.dimList, entr.dimListMode), 5);
        }
        if(config.genCrystalAir.generate)
        {
            ConfigCrystalGenEntry entr = config.genCrystalAir;
            GameRegistry.registerWorldGenerator(new WGCrystal(new CrystalGenPredicate()
            {
                @Override
                public int getY(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, int x, int z, float sif)
                {
                    return world.getHeight(x, z);
                }

                @Override
                public boolean canGenerateHere(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif)
                {
                    Biome b = world.getBiome(pos);
                    return (int)sif > 0 && pos.getY() >= 90 && BiomeDictionary.hasType(b, BiomeDictionary.Type.HILLS);
                }
            }, "air", entr.dimList, entr.dimListMode), 5);
        }
        if(config.genCrystalVision.generate)
        {
            ConfigCrystalGenEntry entr = config.genCrystalVision;
            GameRegistry.registerWorldGenerator(new WGCrystal(new CrystalGenPredicate()
            {
                @Override
                public int getY(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, int x, int z, float sif)
                {
                    return world.getHeight(x, z);
                }

                @Override
                public boolean canGenerateHere(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider, BlockPos pos, float sif)
                {
                    return (int)sif > 0 && BiomeDictionary.hasType(world.getBiome(pos), BiomeDictionary.Type.END) && !world.isAirBlock(pos.down());
                }
            }, "vision", entr.dimList, entr.dimListMode), 5);
        }

        if (config.genCrysagnetite.generate)
        {
            ConfigOregenEntry coe = config.genCrysagnetite;
            GameRegistry.registerWorldGenerator(new WGOre(coe.minVeinSize, coe.maxVeinSize, coe.minY, coe.maxY, coe.chance, BlockRegistry.ore_crysagnetite.getDefaultState(), coe.dimList, coe.dimListMode, coe.minVeins, coe.maxVeins, new WGOre.StonePredicate()), 5);
        }
        if (config.genCrystallizedRedstone.generate)
        {
            ConfigOreOnOreEntry coe = config.genCrystallizedRedstone;
            GameRegistry.registerWorldGenerator(new WGOreOnOre(coe.minY, coe.maxY, coe.chance,
                    BlockRegistry.crystal_small.getDefaultState().withProperty(BlockCrystalSmall.TYPE, BlockCrystalSmall.Type.REDSTONE),
                    (world, pos, posUp) ->
                    {
                        Block b = world.getBlockState(pos).getBlock();
                        if (b == Blocks.REDSTONE_ORE || b == Blocks.LIT_REDSTONE_ORE)
                        {
                            IBlockState up = world.getBlockState(posUp);
                            if (up.getBlock().isReplaceableOreGen(up, world, posUp, new WGOre.StonePredicate()))
                            {
                                return true;
                            }
                        }
                        return false;
                    }, coe.dimList, coe.dimListMode), 6);
        }
        if (config.genCrystallizedGlowstone.generate)
        {
            ConfigOreOnOreEntry coe = config.genCrystallizedGlowstone;
            GameRegistry.registerWorldGenerator(new WGOreOnOre(coe.minY, coe.maxY, coe.chance,
                    BlockRegistry.crystal_small.getDefaultState().withProperty(BlockCrystalSmall.TYPE, BlockCrystalSmall.Type.GLOWSTONE),
                    (world, pos, posUp) ->
                    {
                        Block b = world.getBlockState(pos).getBlock();
                        if (b == Blocks.GLOWSTONE)
                        {
                            IBlockState up = world.getBlockState(posUp);
                            if (up.getBlock().isReplaceableOreGen(up, world, posUp, input -> input.getBlock() != Blocks.GLOWSTONE))
                            {
                                return true;
                            }
                        }
                        return false;
                    }, coe.dimList, coe.dimListMode), 6);
        }
        if (config.genLabSmall.generate)
        {
            ConfigStructureEntry cse = config.genLabSmall;
            GameRegistry.registerWorldGenerator(new WGLabSmall(cse.chance, cse.minY, cse.maxY, cse.dimList, cse.dimListMode), 20);
        }
        if (config.genBrulantaFlower.generate)
        {
            ConfigGroundgenEntry entr = config.genBrulantaFlower;
            GameRegistry.registerWorldGenerator(new WGGround(BlockRegistry.brulanta_flower.getDefaultState(),
                    (world, pos, rand, toGenerate) ->
                    {
                        if (toGenerate.getBlock() instanceof BlockBush)
                        {
                            BlockBush bush = (BlockBush) toGenerate.getBlock();
                            if (bush.canBlockStay(world, pos, toGenerate))
                            {
                                Biome b = world.getBiome(pos);
                                return BiomeDictionary.hasType(b, BiomeDictionary.Type.HOT) && BiomeDictionary.hasType(b, BiomeDictionary.Type.DRY);
                            }
                        }
                        return false;
                    }, entr.minY, entr.maxY, entr.minCount, entr.maxCount, entr.chance, entr.dimList, entr.dimListMode), 5);
        }
    }
}