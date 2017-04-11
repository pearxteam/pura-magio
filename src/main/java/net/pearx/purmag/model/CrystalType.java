package net.pearx.purmag.model;

/**
 * Created by mrAppleXZ on 10.04.17 9:06.
 */
public class CrystalType
{
    public static final String def = "rock";

    public CrystalType(int color)
    {
        setColor(color);
    }

    private int color;

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}
