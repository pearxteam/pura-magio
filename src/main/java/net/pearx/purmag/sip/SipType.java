package net.pearx.purmag.sip;

import net.minecraft.client.resources.I18n;

/**
 * Created by mrAppleXZ on 10.04.17 9:06.
 */
public class SipType
{
    private String name;
    private int color;

    public SipType(String name, int color)
    {
        setColor(color);
        setName(name);
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDisplayName()
    {
        return I18n.format("sip." + name + ".name");
    }
}
