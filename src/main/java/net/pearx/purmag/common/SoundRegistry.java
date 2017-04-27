package net.pearx.purmag.common;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
public class SoundRegistry
{
    public static SoundEvent IfChannelChange;
    public static SoundEvent Notification;

    public static void setup()
    {
        IfChannelChange = new SoundEvent(Utils.getRegistryName("if_channel_change"));
        IfChannelChange.setRegistryName(Utils.getRegistryName("if_channel_change"));

        Notification = new SoundEvent(Utils.getRegistryName("notification"));
        Notification.setRegistryName(Utils.getRegistryName("notification"));

        GameRegistry.register(IfChannelChange);
        GameRegistry.register(Notification);
    }
}
