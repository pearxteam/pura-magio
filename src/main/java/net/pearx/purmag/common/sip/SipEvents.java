package net.pearx.purmag.common.sip;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 14.05.17 12:58.
 */
public class SipEvents
{
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e)
    {
        PurMag.proxy.getSipIdStorage().sync((EntityPlayerMP)e.player);
    }

    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent e)
    {
        PurMag.proxy.getSipIdStorage().desync((EntityPlayerMP)e.player);
    }
}
