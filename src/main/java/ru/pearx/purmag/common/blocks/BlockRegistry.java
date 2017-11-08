package ru.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBucket;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import ru.pearx.libmc.client.models.IModelProvider;
import ru.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 08.04.17 18:44.
 */
@GameRegistry.ObjectHolder(PurMag.MODID)
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class BlockRegistry
{
    public static final Block crystal = null;
    public static final Block crystal_glass = null;
    public static final Block ore_crysagnetite = null;
    public static final BlockTranslationDesk translation_desk = null;
    public static final BlockCrystalSmall crystal_small = null;
    public static final BlockWallIfTablet wall_if_tablet = null;
    public static final BlockBrokenWallIfTablet broken_wall_if_tablet = null;
    public static final BlockMicroscope microscope = null;
    public static final BlockLuminousCrystalGlass luminous_crystal_glass = null;
    public static final BlockTest test = null;
    public static final BlockBrulantaFlower brulanta_flower = null;
    public static final BlockCodeStorage code_storage = null;
    public static final BlockMagibench magibench = null;

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> e)
    {
        IForgeRegistry<Block> reg = e.getRegistry();
        register(new BlockCrystal(), reg);
        register(new BlockCrystalGlass(), reg);
        Block ore_crysagnetite = new BlockBase("ore_crysagnetite", Material.ROCK).setHardness(3).setLightLevel(0.1f);
        ore_crysagnetite.setHarvestLevel("pickaxe", 2);
        register(ore_crysagnetite, reg);
        register(new BlockTranslationDesk(), reg);
        register(new BlockCrystalSmall(), reg);
        register(new BlockWallIfTablet(), reg);
        register(new BlockBrokenWallIfTablet(), reg);
        register(new BlockMicroscope(), reg);
        register(new BlockLuminousCrystalGlass(), reg);
        register(new BlockTest(), reg);
        register(new BlockBrulantaFlower(), reg);
        register(new BlockCodeStorage(), reg);
        register(new BlockMagibench(), reg);
    }

    public static void register(Block b, IForgeRegistry<Block> reg)
    {
        reg.register(b);
        if (b instanceof IModelProvider)
            PurMag.proxy.setupModels((IModelProvider) b);
    }
}
