package net.pearx.purmag.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.CrystalModel;
import net.pearx.purmag.common.DisplayMessage;
import net.pearx.purmag.common.SoundRegistry;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mrAppleXZ on 09.04.17 15:36.
 */
@SideOnly(Side.CLIENT)
public class ClientEvents
{
    private Timer timerDM = new Timer();
    private boolean isDMDisplayed = false;

    @SubscribeEvent
    public void onBake(ModelBakeEvent e)
    {
        CrystalModel mdl = new CrystalModel();
        mdl.bake();
        e.getModelRegistry().putObject(new ModelResourceLocation(new ResourceLocation(PurMag.ModId, "crystal"), "normal"), mdl);
    }

    @SubscribeEvent
    public void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PurMag.ModId, "models/crystal"));
    }

    @SubscribeEvent
    public void renderMessage(RenderGameOverlayEvent e)
    {
        if(e.getType() == RenderGameOverlayEvent.ElementType.HOTBAR)
        {
            DisplayMessage msg = DisplayMessageQuery.getMessage();
            if (msg != null)
            {
                if(!isDMDisplayed)
                {
                    Minecraft.getMinecraft().player.playSound(SoundRegistry.Notification, 1, 1);
                    timerDM.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            isDMDisplayed = false;
                            DisplayMessageQuery.removeMessage();
                        }
                    }, 5000); //5 seconds
                }
                isDMDisplayed = true;
                msg.draw(0, 0);
            }
        }
    }
}
