package net.pearx.purmag.common.infofield;

import net.pearx.purmag.common.infofield.pages.IIfPage;

/**
 * Created by mrAppleXZ on 27.05.17 13:14.
 */
public class IfEntryLocation
{
    private String entry;
    private int x, y;

    public IfEntryLocation(String entry, int x, int y)
    {
        setEntry(entry);
        setX(x);
        setY(y);
    }

    public String getEntry()
    {
        return entry;
    }

    public void setEntry(String entry)
    {
        this.entry = entry;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IfEntryLocation that = (IfEntryLocation) o;

        if (x != that.x) return false;
        if (y != that.y) return false;
        return entry != null ? entry.equals(that.entry) : that.entry == null;
    }

    @Override
    public int hashCode()
    {
        int result = entry != null ? entry.hashCode() : 0;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}
