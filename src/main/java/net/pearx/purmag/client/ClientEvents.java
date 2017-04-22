package net.pearx.purmag.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.CrystalModel;

/**
 * Created by mrAppleXZ on 09.04.17 15:36.
 */
public class ClientEvents
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
}
