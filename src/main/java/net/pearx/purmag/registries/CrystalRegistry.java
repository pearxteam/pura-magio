package net.pearx.purmag.registries;

import net.pearx.purmag.model.CrystalType;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 10.04.17 9:05.
 */
public class CrystalRegistry
{
    public CrystalRegistry()
    {
        registry.put("rock", new CrystalType(0x9e9e9e));
        registry.put("sea", new CrystalType(0x42A5F5));
    }

    public HashMap<String, CrystalType> registry = new HashMap<>();
}
