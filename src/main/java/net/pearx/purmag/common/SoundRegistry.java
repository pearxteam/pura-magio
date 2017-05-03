package net.pearx.purmag.common;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
public class SoundRegistry
{
    public static SoundEvent MagicalClick;
    public static SoundEvent Notification;
    public static SoundEvent Click;

    public static void setup()
    {
        MagicalClick = new SoundEvent(Utils.getRegistryName("magical_click"));
        MagicalClick.setRegistryName(Utils.getRegistryName("magical_click"));

        Notification = new SoundEvent(Utils.getRegistryName("notification"));
        Notification.setRegistryName(Utils.getRegistryName("notification"));

        Click = new SoundEvent(Utils.getRegistryName("click"));
        Click.setRegistryName(Utils.getRegistryName("click"));

        GameRegistry.register(MagicalClick);
        GameRegistry.register(Notification);
        GameRegistry.register(Click);
    }
}
