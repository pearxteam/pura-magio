package net.pearx.purmag.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.papyrus.GuiPapyrus;
import net.pearx.purmag.client.guis.translation_desk.GuiTranslationDesk;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.client.sif.SifStorageClient;
import net.pearx.purmag.common.CommonProxy;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.PmGui;
import net.pearx.purmag.client.guis.if_tablet.GuiIfTablet;
import net.pearx.purmag.common.blocks.BlockCrystalSmall;
import net.pearx.purmag.common.blocks.BlockSingleSip;
import net.pearx.purmag.common.entities.EntityRegistry;
import net.pearx.purmag.common.sif.SifStorage;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.blocks.BlockRegistry;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    private SifStorageClient sifStorage = new SifStorageClient();

    @Override
    public void preInit()
    {
        Minecraft.getMinecraft().getFramebuffer().enableStencil();
        OBJLoader.INSTANCE.addDomain(PurMag.MODID);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        EntityRegistry.registerClient();
    }

    @Override
    public void init()
    {
        GuiDrawableRegistry.setup();

        registerSipBlockColor(BlockRegistry.crystal);
        registerSipBlockColor(BlockRegistry.crystal_glass);
        registerSipItemColor(ItemRegistry.crystal_glass);
        registerSipItemColor(ItemRegistry.crystal);
        registerSipItemColor(ItemRegistry.crystal_shard);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> state.getValue(BlockCrystalSmall.TYPE).getColor(), BlockRegistry.crystal_small);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> BlockCrystalSmall.Type.values()[stack.getMetadata()].getColor(), ItemRegistry.crystal_small);

        KeyBindings.setup();

        PurMag.instance.if_registry.setupClient();
    }

    public static void registerSipItemColor(Item itm)
    {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
                PurMag.instance.sip.getType(stack.getMetadata()).getColor(), itm);
    }

    public static void registerSipBlockColor(Block bl)
    {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, w, pos, tintIndex) ->
                PurMag.instance.sip.getType(state.getValue(BlockSingleSip.SIPTYPE)).getColor(), bl);
    }

    @Override
    public void openIfTablet(int tier)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PmGui(new GuiIfTablet(tier)));
    }

    @Override
    public void openPapyrus(String id)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PmGui(new GuiPapyrus(id)));
    }

    @Override
    public SifStorage getSifStorage()
    {
        return sifStorage;
    }

    @Override
    public void openTranslationDesk(BlockPos pos,  World world)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PmGui(new GuiTranslationDesk(pos, world)));
    }

    @Override
    public void setupModels(IModelProvider prov)
    {
        prov.setupModels();
    }
}
