package ru.pearx.purmag.common.sip;

import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 10.04.17 9:05.
 */
public class SipTypeRegistry
{
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

    public void register()
    {
        register(new SipType("rock", 0x9e9e9e, TextFormatting.GRAY));
        register(new SipType("sea", 0x42A5F5, TextFormatting.BLUE));
        register(new SipType("flame", 0xFB8C00, TextFormatting.GOLD));
        register(new SipType("air", 0xFFE57F, TextFormatting.YELLOW));
        register(new SipType("information", 0x68C397, TextFormatting.GREEN));
    }
}
