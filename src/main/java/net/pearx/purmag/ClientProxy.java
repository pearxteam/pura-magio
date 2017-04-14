package net.pearx.purmag;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.pearx.purmag.registries.ItemRegistry;
import net.pearx.purmag.registries.BlockRegistry;
import net.pearx.purmag.registries.SipTypeRegistry;
import net.pearx.purmag.tiles.TileCrystal;

/**
 * Created by mrAppleXZ on 08.04.17 21:06.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit()
    {
        OBJLoader.INSTANCE.addDomain(PurmagCore.ModId);
        ItemRegistry.setupModels();
    }

    @Override
    public void init()
    {
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
            return PurmagCore.CrystalReg.registry.get(type).getColor();
        }, BlockRegistry.crystal);
        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
        {
            String type = SipTypeRegistry.def;
            if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
            {
                type = stack.getTagCompound().getString("type");
            }
            return PurmagCore.CrystalReg.registry.get(type).getColor();
        }, ItemRegistry.crystal);

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) ->
        {
            String type = SipTypeRegistry.def;
            if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
            {
                type = stack.getTagCompound().getString("type");
            }
            return PurmagCore.CrystalReg.registry.get(type).getColor();
        }, ItemRegistry.crystal_shard);
    }
}
