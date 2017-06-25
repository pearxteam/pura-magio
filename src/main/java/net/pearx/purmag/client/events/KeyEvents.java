package net.pearx.purmag.client.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.KeyBindings;
import net.pearx.purmag.common.items.ItemSipAmulet;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.SPacketUseSipAmulet;

/**
 * Created by mrAppleXZ on 25.06.17 11:52.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class KeyEvents
{
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent e)
    {
        if(KeyBindings.USE_SIP_AMULET.isPressed())
        {
            if(ItemSipAmulet.checkForAmulet(Minecraft.getMinecraft().player))
            {
                NetworkManager.sendToServer(new SPacketUseSipAmulet());
            }
        }
    }
}
