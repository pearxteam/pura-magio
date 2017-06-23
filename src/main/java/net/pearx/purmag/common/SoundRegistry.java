package net.pearx.purmag.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
public class SoundRegistry
{
    public static SoundEvent MAGICAL_CLICK;
    public static SoundEvent NOTIFICATION;
    public static SoundEvent CLICK;
    public static SoundEvent GLASS;
    public static SoundEvent ERROR;
    public static SoundEvent BEETLE_IDLE;
    public static SoundEvent BEETLE_HURT;

    public static void setup()
    {

        MAGICAL_CLICK = register(Utils.getRegistryName("misc.magical_click"));
        NOTIFICATION = register(Utils.getRegistryName("misc.notification"));
        CLICK = register(Utils.getRegistryName("misc.click"));
        GLASS = register(Utils.getRegistryName("misc.glass"));
        ERROR = register(Utils.getRegistryName("misc.error"));
        BEETLE_IDLE = register(Utils.getRegistryName("mob.verda_beetle.idle"));
        BEETLE_HURT = register(Utils.getRegistryName("mob.verda_beetle.hurt"));
    }

    public static SoundEvent register(ResourceLocation loc)
    {
        SoundEvent ev = new SoundEvent(loc);
        ev.setRegistryName(loc);
        GameRegistry.register(ev);
        return ev;
    }
}
