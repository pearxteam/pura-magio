package ru.pearx.purmag.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import ru.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
@GameRegistry.ObjectHolder(PurMag.MODID)
public class SoundRegistry
{
    @GameRegistry.ObjectHolder("misc.magical_click")
    public static SoundEvent MAGICAL_CLICK;
    @GameRegistry.ObjectHolder("misc.notification")
    public static SoundEvent NOTIFICATION;
    @GameRegistry.ObjectHolder("misc.click")
    public static SoundEvent CLICK;
    @GameRegistry.ObjectHolder("misc.glass")
    public static SoundEvent GLASS;
    @GameRegistry.ObjectHolder("misc.error")
    public static SoundEvent ERROR;
    @GameRegistry.ObjectHolder("mob.verda_beetle.idle")
    public static SoundEvent BEETLE_IDLE;
    @GameRegistry.ObjectHolder("mob.verda_beetle.hurt")
    public static SoundEvent BEETLE_HURT;

    public static void register(ResourceLocation loc, IForgeRegistry<SoundEvent> reg)
    {
        SoundEvent ev = new SoundEvent(loc);
        ev.setRegistryName(loc);
        reg.register(ev);
    }

    @SubscribeEvent
    public static void onRegisterSounds(RegistryEvent.Register<SoundEvent> e)
    {
        IForgeRegistry<SoundEvent> reg = e.getRegistry();
        register(Utils.getRegistryName("misc.magical_click"), reg);
        register(Utils.getRegistryName("misc.notification"), reg);
        register(Utils.getRegistryName("misc.click"), reg);
        register(Utils.getRegistryName("misc.glass"), reg);
        register(Utils.getRegistryName("misc.error"), reg);
        register(Utils.getRegistryName("mob.verda_beetle.idle"), reg);
        register(Utils.getRegistryName("mob.verda_beetle.hurt"), reg);
    }
}
