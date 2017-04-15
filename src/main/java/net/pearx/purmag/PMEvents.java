package net.pearx.purmag;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pearx.purmag.client.models.CrystalModel;

/**
 * Created by mrAppleXZ on 09.04.17 15:36.
 */
public class PMEvents
{
    @SubscribeEvent
    public void onBake(ModelBakeEvent e)
    {
        e.getModelRegistry().putObject(new ModelResourceLocation(new ResourceLocation(PurMag.ModId, "crystal"), "normal"), new CrystalModel());
    }

    @SubscribeEvent
    public void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PurMag.ModId, "models/crystal"));
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e)
    {
        if(e.getItemStack().hasTagCompound())
        {
            if(e.getItemStack().getTagCompound().hasKey("sipStored"))
            {
                e.getToolTip().add(I18n.format("sip_tooltip") + e.getItemStack().getTagCompound().getInteger("sipStored"));
            }
        }
    }
}
