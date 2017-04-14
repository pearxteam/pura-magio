package net.pearx.purmag.api;

/**
 * Created by mrAppleXZ on 10.04.17 9:06.
 */
public class SipType
{
    public SipType(int color)
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
