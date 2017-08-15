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
import ru.pearx.purmag.client.gui.papyrus.GuiPapyrus;
import ru.pearx.purmag.client.gui.translation_desk.GuiTranslationDesk;
import ru.pearx.purmag.common.CommonProxy;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.gui.if_tablet.GuiIfTablet;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.BlockCrystalSmall;
import ru.pearx.purmag.common.entities.EntityRegistry;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        Minecraft.getMinecraft().getFramebuffer().enableStencil();
        OBJLoader.INSTANCE.addDomain(PurMag.MODID);
        EntityRegistry.registerClient();
    }

    @Override
    public void init()
    {
        registerSipBlockColor(BlockRegistry.crystal);
        registerSipBlockColor(BlockRegistry.crystal_glass);
        registerSipItemColor(ItemRegistry.crystal_glass);
        registerSipItemColor(ItemRegistry.crystal);
        registerSipItemColor(ItemRegistry.crystal_shard);
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> state.getValue(BlockCrystalSmall.TYPE).getColor(), BlockRegistry.crystal_small);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> BlockCrystalSmall.Type.values()[stack.getMetadata()].getColor(), ItemRegistry.crystal_small);

        KeyBindings.setup();

        PurMag.INSTANCE.if_registry.setupClient();
    }

    public static void registerSipItemColor(Item itm)
    {
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
                PurMag.INSTANCE.sip.getType(SipUtils.getSipInStack(stack)).getColor(), itm);
    }

    public static void registerSipBlockColor(Block bl)
    {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, w, pos, tintIndex) ->
                PurMag.INSTANCE.sip.getType(SipUtils.getSipInBlock(w, pos)).getColor(), bl);
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
    public void openTranslationDesk(BlockPos pos,  World world)
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
        PurMag.INSTANCE.if_registry.registerTierClient(0, new IfTier.TabletData(GuiDrawableRegistry.paperEntry, false, Utils.getRegistryName("textures/gui/if_tablet/0.png")), Utils.getRegistryName("models/wall_if_tablet/0"), Utils.getRegistryName("if_tablet/0"));
        PurMag.INSTANCE.if_registry.registerTierClient(1, new IfTier.TabletData(GuiDrawableRegistry.runes, true, Utils.getRegistryName("textures/gui/if_tablet/1.png")), Utils.getRegistryName("models/wall_if_tablet/1"), Utils.getRegistryName("if_tablet/1"));
        PurMag.INSTANCE.if_registry.registerTierClient(2, new IfTier.TabletData(GuiDrawableRegistry.runes, true, Utils.getRegistryName("textures/gui/if_tablet/2.png")), Utils.getRegistryName("models/wall_if_tablet/2"), Utils.getRegistryName("if_tablet/2"));
    }

    @Override
    public void setupDrawables()
    {
        GuiDrawableRegistry.setup();
    }
}
