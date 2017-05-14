package net.pearx.purmag.common.sip;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraftforge.common.DimensionManager;
import net.pearx.purmag.PurMag;
import org.lwjgl.input.Mouse;

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
        return I18n.translateToLocal("sip." + name + ".name");
    }

    public int getId()
    {
        return PurMag.proxy.getSipIdStorage().getMap().get(getName());
    }
}
