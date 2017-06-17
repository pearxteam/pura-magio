package net.pearx.purmag.common;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
public class SoundRegistry
{
    public static SoundEvent magicalClick;
    public static SoundEvent notification;
    public static SoundEvent click;
    public static SoundEvent glass;
    public static SoundEvent error;

    public static void setup()
    {
        magicalClick = new SoundEvent(Utils.getRegistryName("magical_click"));
        magicalClick.setRegistryName(Utils.getRegistryName("magical_click"));

        notification = new SoundEvent(Utils.getRegistryName("notification"));
        notification.setRegistryName(Utils.getRegistryName("notification"));

        click = new SoundEvent(Utils.getRegistryName("click"));
        click.setRegistryName(Utils.getRegistryName("click"));

        glass = new SoundEvent(Utils.getRegistryName("glass"));
        glass.setRegistryName(Utils.getRegistryName("glass"));

        error = new SoundEvent(Utils.getRegistryName("error"));
        error.setRegistryName(Utils.getRegistryName("error"));

        GameRegistry.register(magicalClick);
        GameRegistry.register(notification);
        GameRegistry.register(click);
        GameRegistry.register(glass);
        GameRegistry.register(error);
    }
}
