package net.pearx.purmag.common.sip;

import net.minecraft.util.text.TextFormatting;
import net.pearx.purmag.PurMag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mrAppleXZ on 10.04.17 9:05.
 */
public class SipTypeRegistry
{
    public static final String DEFAULT = "rock";

    public List<SipType> types = new ArrayList<>();
    public List<String> allowedValues = new ArrayList<>();

    public void register(SipType t)
    {
        types.add(t);
        allowedValues.add(t.getName());
    }

    public SipType getType(String name)
    {
        for(SipType type : types)
            if(type.getName().equals(name))
                return type;
        return null;
    }

    public SipType getType(int id)
    {
        for(SipType type : types)
            if(type.getId() == id)
                return type;
        return null;
    }

    public void setup()
    {
        register(new SipType("rock", 0x9e9e9e, TextFormatting.GRAY, 0));
        register(new SipType("sea", 0x42A5F5, TextFormatting.BLUE, 1));
        register(new SipType("flame", 0xFB8C00, TextFormatting.GOLD, 2));
        register(new SipType("air", 0xFFE57F, TextFormatting.YELLOW, 3));
        register(new SipType("information", 0x68C397, TextFormatting.GREEN, 4));
    }
}
