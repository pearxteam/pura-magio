package net.pearx.purmag.sip;

import net.pearx.purmag.sip.SipType;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mrAppleXZ on 10.04.17 9:05.
 */
public class SipTypeRegistry
{
    public static final String def = "rock";

    public ArrayList<SipType> types = new ArrayList<>();

    public void register(SipType t)
    {
        types.add(t);
    }

    public SipType getType(String name)
    {
        for(SipType type : types)
            if(type.getName().equals(name))
                return type;
        return null;
    }

    public void setup()
    {
        register(new SipType("rock", 0x9e9e9e));
        register(new SipType("sea", 0x42A5F5));
    }
}
