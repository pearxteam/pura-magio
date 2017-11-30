package ru.pearx.purmag.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import ru.pearx.purmag.PurMag;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
@GameRegistry.ObjectHolder(PurMag.MODID)
public class SoundRegistry
{
    @ObjectHolder("misc.magical_click")
    public static SoundEvent MAGICAL_CLICK;
    @ObjectHolder("misc.notification")
    public static SoundEvent NOTIFICATION;
    @ObjectHolder("misc.click")
    public static SoundEvent CLICK;
    @ObjectHolder("misc.glass")
    public static SoundEvent GLASS;
    @ObjectHolder("misc.error")
    public static SoundEvent ERROR;
    @ObjectHolder("mob.verda_beetle.idle")
    public static SoundEvent BEETLE_IDLE;
    @ObjectHolder("mob.verda_beetle.hurt")
    public static SoundEvent BEETLE_HURT;
    @ObjectHolder("misc.code_storage_open")
    public static SoundEvent CODE_STORAGE_OPEN;
    @ObjectHolder("misc.multiblock_form")
    public static SoundEvent MULTIBLOCK_FORM;

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
        register(Utils.gRL("misc.magical_click"), reg);
        register(Utils.gRL("misc.notification"), reg);
        register(Utils.gRL("misc.click"), reg);
        register(Utils.gRL("misc.glass"), reg);
        register(Utils.gRL("misc.error"), reg);
        register(Utils.gRL("mob.verda_beetle.idle"), reg);
        register(Utils.gRL("mob.verda_beetle.hurt"), reg);
        register(Utils.gRL("misc.code_storage_open"), reg);
        register(Utils.gRL("misc.multiblock_form"), reg);
    }
}
