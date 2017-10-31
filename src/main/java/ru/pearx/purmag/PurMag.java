package ru.pearx.purmag;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.Logger;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.common.structure.blockarray.BlockArrayEntry;
import ru.pearx.libmc.common.structure.processors.IStructureProcessor;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.CommonProxy;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.commands.CommandIf;
import ru.pearx.purmag.common.config.PMConfig;
import ru.pearx.purmag.common.entities.EntityRegistry;
import ru.pearx.purmag.common.expressions.ExpressionRegistry;
import ru.pearx.purmag.common.expressions.IExpression;
import ru.pearx.purmag.common.infofield.IfRegistry;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.items.papyrus.PapyrusRegistry;
import ru.pearx.purmag.common.loot_tables.LootTablesRegistry;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.sif.SifStorageServer;
import ru.pearx.purmag.common.sip.SipEffectsRegistry;
import ru.pearx.purmag.common.sip.SipTypeRegistry;
import ru.pearx.purmag.common.structure.CodeStorageProcessor;
import ru.pearx.purmag.common.structure.PMStructureProcessorRegistry;
import ru.pearx.purmag.common.tiles.TileCodeStorage;
import ru.pearx.purmag.common.tiles.TileRegistry;
import ru.pearx.purmag.common.worldgen.WorldgenRegistry;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by mrAppleXZ on 08.04.17 10:31.
 */
@Mod(name = PurMag.NAME, modid = PurMag.MODID, version = PurMag.VERSION, acceptedMinecraftVersions = "", dependencies = "required-after:pxlmc;required-after:baubles;")
public class PurMag
{
    public static final String MODID = "purmag";
    public static final String NAME = "Purificati Magicae";
    public static final String VERSION = "@VERSION@";
    @Mod.Instance
    public static PurMag INSTANCE;
    @SidedProxy(clientSide = "ru.pearx.purmag.client.ClientProxy", serverSide = "ru.pearx.purmag.server.ServerProxy")
    public static CommonProxy proxy;
    public PMConfig config = new PMConfig();
    public Logger log;
    //don't move this to PurMagClient
    public Random random = new Random();

    public SifStorageServer sif_storage = new SifStorageServer();
    private SipTypeRegistry sip_registry = new SipTypeRegistry();
    private SipEffectsRegistry sip_effects = new SipEffectsRegistry();
    private IfRegistry if_registry = new IfRegistry();
    private PapyrusRegistry papyrus_registry = new PapyrusRegistry();
    private ExpressionRegistry expressionRegistry = new ExpressionRegistry();
    private MagibenchRegistry magibenchRegistry = new MagibenchRegistry();
    private Configuration configFile;

    public SipTypeRegistry getSipRegistry()
    {
        return sip_registry;
    }

    public SipEffectsRegistry getSipEffects()
    {
        return sip_effects;
    }

    public IfRegistry getIfRegistry()
    {
        return if_registry;
    }

    public PapyrusRegistry getPapyrusRegistry()
    {
        return papyrus_registry;
    }

    public ExpressionRegistry getExpressionRegistry()
    {
        return expressionRegistry;
    }

    public MagibenchRegistry getMagibenchRegistry()
    {
        return magibenchRegistry;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        log = e.getModLog();
        setupMetadata(e.getModMetadata());

        configFile = new Configuration(new File(e.getModConfigurationDirectory(), "Purificati Magicae.cfg"));
        config.setup(configFile);

        proxy.setupDrawables();
        getSipRegistry().register();
        TileRegistry.register();
        CapabilityRegistry.register();
        EntityRegistry.register();
        getPapyrusRegistry().setup();
        getExpressionRegistry().setup();
        PMStructureProcessorRegistry.setup();
        getMagibenchRegistry().setup();

        proxy.setupIfTiers();

        proxy.preInit();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        config.setupInit(configFile);
        getSipEffects().register();
        ItemRegistry.setup();
        getIfRegistry().setup();
        NetworkManager.setup();
        WorldgenRegistry.setup();
        LootTablesRegistry.setup();

        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        EntityRegistry.setupSpawns();
    }

    private void setupMetadata(ModMetadata data)
    {
        data.autogenerated = false;
        data.credits = "afdw & Prototik - cool guys";
        data.authorList = Arrays.asList("mrAppleXZ", "DrVexsear", "gt22");
        data.description = "Purificati Magicae";
        data.modId = PurMag.MODID;
        data.name = PurMag.NAME;
        data.version = PurMag.VERSION;
    }

    @Mod.EventHandler
    public void serverStartup(FMLServerStartingEvent e)
    {
        e.registerServerCommand(new CommandIf());
    }
}
