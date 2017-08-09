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

    public void register()
    {
        getMap().put("rock", new SipEffect(MobEffects.RESISTANCE, 400, -1));
        getMap().put("sea", new SipEffect(MobEffects.WATER_BREATHING, 400, -1));
        getMap().put("flame", new SipEffect(MobEffects.FIRE_RESISTANCE, 400, -1));
        getMap().put("air", new SipEffect(MobEffects.SPEED, 400, -1));
        getMap().put("information", new SipEffect(MobEffects.BLINDNESS, 20, -1));
    }
}
