package net.pearx.purmag.registries;

import net.pearx.purmag.api.SipType;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 10.04.17 9:05.
 */
public class SipTypeRegistry
{
    public static final String def = "rock";

    public SipTypeRegistry()
    {
        registry.put("rock", new SipType(0x9e9e9e));
        registry.put("sea", new SipType(0x42A5F5));
    }

    public HashMap<String, SipType> registry = new HashMap<>();
}
