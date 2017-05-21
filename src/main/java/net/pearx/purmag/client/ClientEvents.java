package net.pearx.purmag.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.IModelBase;
import net.pearx.purmag.client.models.StandardModels;
import net.pearx.purmag.common.DisplayMessage;
import net.pearx.purmag.common.SoundRegistry;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.SPacketUseSipAmulet;

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
        putModel(e, new StandardModels.Crystal(), new ResourceLocation(PurMag.ModId, "crystal"));
        putModel(e, new StandardModels.CrystalGlass(), new ResourceLocation(PurMag.ModId, "crystal_glass"));
    }

    private void putModel(ModelBakeEvent e, IModelBase model, ResourceLocation loc)
    {
        model.bake();
        e.getModelRegistry().putObject(new ModelResourceLocation(loc, "normal"), model);
    }

    @SubscribeEvent
    public void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PurMag.ModId, "models/crystal"));
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 2; k++)
                    for(int l = 0; l < 2; l++)
                        e.getMap().registerSprite(new ResourceLocation(PurMag.ModId, "blocks/crystal_glass/" + i + j + k + l));
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

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent e)
    {
        if(KeyBindings.USE_SIP_AMULET.isPressed())
        {
            NetworkManager.sendToServer(new SPacketUseSipAmulet());
        }
    }
}
