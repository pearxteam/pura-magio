package net.pearx.purmag;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
public class SoundRegistry
{
    public static SoundEvent IfChannelChange;

    public static void setup()
    {
        IfChannelChange = new SoundEvent(Utils.getRegistryName("if_channel_change"));
        IfChannelChange.setRegistryName(Utils.getRegistryName("if_channel_change"));
        GameRegistry.register(IfChannelChange);
    }
}
