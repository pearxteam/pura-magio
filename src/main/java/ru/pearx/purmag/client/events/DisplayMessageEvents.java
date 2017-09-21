package ru.pearx.purmag.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.DisplayMessageQuery;
import ru.pearx.purmag.common.DisplayMessage;
import ru.pearx.purmag.common.SoundRegistry;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mrAppleXZ on 25.06.17 11:51.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = PurMag.MODID, value = Side.CLIENT)
public class DisplayMessageEvents
{
    private static Timer timerDM = new Timer();
    private static boolean isDMDisplayed = false;

    @SubscribeEvent
    public static void renderMessage(RenderGameOverlayEvent.Pre e)
    {
        if (e.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            DisplayMessage msg = DisplayMessageQuery.getMessage();
            if (msg != null)
            {
                if (!isDMDisplayed)
                {
                    Minecraft.getMinecraft().player.playSound(SoundRegistry.NOTIFICATION, 1, 1);
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
                GlStateManager.color(1, 1, 1);
                msg.draw(0, 0);
            }
        }
    }
}
