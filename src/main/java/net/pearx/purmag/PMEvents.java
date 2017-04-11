package net.pearx.purmag;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
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
        e.getModelRegistry().putObject(new ModelResourceLocation(new ResourceLocation(PMCore.ModId, "crystal"), "normal"), new CrystalModel());
    }

    @SubscribeEvent
    public void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PMCore.ModId, "models/crystal"));
    }
}
