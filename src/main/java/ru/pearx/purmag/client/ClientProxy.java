package ru.pearx.purmag.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.PXLGui;
import ru.pearx.libmc.client.models.IModelProvider;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.gui.GuiPapyrus;
import ru.pearx.purmag.client.gui.code_storage.GuiCodeStorageUnlock;
import ru.pearx.purmag.client.gui.if_tablet.GuiIfTablet;
import ru.pearx.purmag.client.gui.microscope.GuiMicroscope;
import ru.pearx.purmag.client.gui.translation_desk.GuiTranslationDesk;
import ru.pearx.purmag.common.CommonProxy;
import ru.pearx.purmag.common.blocks.BlockCrystalSmall;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.entities.EntityRegistry;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.sip.SipUtils;
import ru.pearx.purmag.common.tiles.TileCodeStorage;
import ru.pearx.purmag.common.tiles.TileMicroscope;
import ru.pearx.purmag.common.tiles.TileRegistry;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    public static void registerSipItemColor(Item itm)
    {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
                PurMag.INSTANCE.getSipRegistry().getType(SipUtils.getSipInStack(stack)).getColor().getARGB(), itm);
    }

    public static void registerSipBlockColor(Block bl)
    {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, w, pos, tintIndex) ->
                PurMag.INSTANCE.getSipRegistry().getType(SipUtils.getSipInBlock(w, pos)).getColor().getARGB(), bl);
    }

    @Override
    public void preInit()
    {
        Minecraft.getMinecraft().getFramebuffer().enableStencil();
        OBJLoader.INSTANCE.addDomain(PurMag.MODID);
        EntityRegistry.registerClient();
        PurMagClient.INSTANCE.getMicroscopeDataBuilder().setup();
    }

    @Override
    public void init()
    {
        registerSipBlockColor(BlockRegistry.crystal);
        registerSipBlockColor(BlockRegistry.crystal_glass);
        registerSipBlockColor(BlockRegistry.luminous_crystal_glass);
        registerSipItemColor(ItemRegistry.crystal_glass);
        registerSipItemColor(ItemRegistry.crystal);
        registerSipItemColor(ItemRegistry.crystal_shard);
        registerSipItemColor(ItemRegistry.luminous_crystal_glass);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> state.getValue(BlockCrystalSmall.TYPE).getColor(), BlockRegistry.crystal_small);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> BlockCrystalSmall.Type.values()[stack.getMetadata()].getColor(), ItemRegistry.crystal_small);

        KeyBindings.setup();

        PurMag.INSTANCE.getIfRegistry().setupClient();
        TileRegistry.registerClient();
    }

    @Override
    public void openIfTablet(int tier)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiIfTablet(tier)));
    }

    @Override
    public void openPapyrus(String id)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiPapyrus(id)));
    }

    @Override
    public void openTranslationDesk(BlockPos pos, World world)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiTranslationDesk(pos, world)));
    }

    @Override
    public void setupModels(IModelProvider prov)
    {
        prov.setupModels();
    }

    @Override
    public void setupIfTiers()
    {
        PurMag.INSTANCE.getIfRegistry().setupIfTiersClient();
    }

    @Override
    public void setupDrawables()
    {
        GuiDrawableRegistry.setup();
    }

    @Override
    public void openMicroscope(TileMicroscope tile)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiMicroscope(tile.handler.getStackInSlot(0), tile.getPos())));
    }

    @Override
    public void openCodeStorageUnlock(TileCodeStorage te)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiCodeStorageUnlock(te.getPos(), te.getText())));
    }
}
