package ru.pearx.purmag.client.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.KeyBindings;
import ru.pearx.purmag.common.items.ItemSipAmulet;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.SPacketUseSipAmulet;

/**
 * Created by mrAppleXZ on 25.06.17 11:52.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = PurMag.MODID, value = Side.CLIENT)
public class KeyEvents
{
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent e)
    {
        if (KeyBindings.USE_SIP_AMULET.isPressed())
        {
            if (ItemSipAmulet.checkForAmulet(Minecraft.getMinecraft().player))
            {
                NetworkManager.sendToServer(new SPacketUseSipAmulet());
            }
        }
    }
}
