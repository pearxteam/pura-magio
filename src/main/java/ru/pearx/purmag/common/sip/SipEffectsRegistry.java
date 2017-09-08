package ru.pearx.purmag.common.sip;

import net.minecraft.init.MobEffects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 21.05.17 17:09.
 */
public class SipEffectsRegistry
{
    private Map<String, SipEffect> map = new HashMap<>();

    public Map<String, SipEffect> getMap()
    {
        return map;
    }

    public void register(String type, SipEffect effect)
    {
        getMap().put(type, effect);
    }

    public void unregister(String type)
    {
        getMap().remove(type);
    }

    public void register()
    {
        register("rock", new SipEffect(MobEffects.RESISTANCE, 400, -1));
        register("sea", new SipEffect(MobEffects.WATER_BREATHING, 400, -1));
        register("flame", new SipEffect(MobEffects.FIRE_RESISTANCE, 400, -1));
        register("air", new SipEffect(MobEffects.SPEED, 400, -1));
        register("information", new SipEffect(MobEffects.BLINDNESS, 20, -1));
        register("vision", new SipEffect(MobEffects.NIGHT_VISION, 400, -1));
        register("analysis", new SipEffect(MobEffects.NAUSEA, 20, -1));
        register("electricity", new SipEffect(MobEffects.STRENGTH, 300, -1));
    }
}
