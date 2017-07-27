package ru.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import ru.pearx.libmc.client.models.IModelProvider;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 08.04.17 18:44.
 */
@GameRegistry.ObjectHolder(PurMag.MODID)
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class BlockRegistry
{
    public static final Block crystal = null;
    public static final Block crystal_glass = null;
    public static final BlockOre ore_crysagnetite = null;
    public static final BlockTranslationDesk translation_desk = null;
    public static final BlockCrystalSmall crystal_small = null;
    public static final BlockWallIfTablet wall_if_tablet = null;
    public static final BlockBrokenWallIfTablet broken_wall_if_tablet = null;

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> e)
    {
        IForgeRegistry<Block> reg = e.getRegistry();
        register(new BlockCrystal(), reg);
        register(new BlockCrystalGlass(), reg);
        register(new BlockOre(Utils.getRegistryName("ore_crysagnetite"), 3f, 0.1f, 2), reg);
        register(new BlockTranslationDesk(), reg);
        register(new BlockCrystalSmall(), reg);
        register(new BlockWallIfTablet(), reg);
        register(new BlockBrokenWallIfTablet(), reg);
    }

    public static void register(Block b, IForgeRegistry<Block> reg)
    {
        reg.register(b);
        if(b instanceof IModelProvider)
            PurMag.proxy.setupModels((IModelProvider) b);
    }
}
