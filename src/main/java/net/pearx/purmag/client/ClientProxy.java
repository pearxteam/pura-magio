package net.pearx.purmag.client;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.sif.SifStorageClient;
import net.pearx.purmag.common.CommonProxy;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.PmGui;
import net.pearx.purmag.client.guis.if_tablet.GuiIfTablet;
import net.pearx.purmag.common.sif.SifStorage;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.blocks.BlockRegistry;
import net.pearx.purmag.common.sip.SipTypeRegistry;
import net.pearx.purmag.common.tiles.TileCrystal;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
    private SifStorageClient storage = new SifStorageClient();

    @Override
    public void preInit()
    {
        Minecraft.getMinecraft().getFramebuffer().enableStencil();
        OBJLoader.INSTANCE.addDomain(PurMag.ModId);
        ItemRegistry.setupModels();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @Override
    public void init()
    {
        GuiDrawableRegistry.setup();

        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, w, pos, tintIndex) ->
        {
            String type = SipTypeRegistry.def;
            if(w != null && pos != null)
            {
                TileEntity te = w.getTileEntity(pos);
                if (te != null && te instanceof TileCrystal)
                {
                    TileCrystal tec = (TileCrystal) te;
                    type = tec.getType();
                }
            }
            return PurMag.instance.sip.getType(type).getColor();
        }, BlockRegistry.crystal);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
        {
            String type = SipTypeRegistry.def;
            if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
            {
                type = stack.getTagCompound().getString("type");
            }
            return PurMag.instance.sip.getType(type).getColor();
        }, ItemRegistry.crystal);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
        {
            String type = SipTypeRegistry.def;
            if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
            {
                type = stack.getTagCompound().getString("type");
            }
            return PurMag.instance.sip.getType(type).getColor();
        }, ItemRegistry.crystal_shard);
    }

    @Override
    public void openIfTablet(int tier)
    {
        Minecraft.getMinecraft().displayGuiScreen(new PmGui(new GuiIfTablet(tier)));
    }

    @Override
    public SifStorage getSifStorage()
    {
        return storage;
    }
}
