package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.infofield.IfChannel;
import net.pearx.purmag.common.infofield.IfEntry;
import net.pearx.purmag.common.infofield.IfEntryLocation;
import org.lwjgl.input.Mouse;

/**
 * Created by mrAppleXZ on 23.04.17 19:12.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSEEntries extends GuiIfTabletSEPart
{
    public int entrSize = 32;
    public int minOffsetX, minOffsetY, maxOffsetX, maxOffsetY;
    public int offsetX, offsetY;
    public int prevMouseX, prevMouseY;

    public GuiIfTabletSEEntries()
    {

    }

    @Override
    public void init()
    {
        setWidth(getTabletScreen().getWidth() - 10);
        setHeight(getTabletScreen().getHeight() - 10);
        setX(5);
        setY(5);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        int px = x - prevMouseX;
        int py = y - prevMouseY;
        if(Mouse.isButtonDown(0))
        {
            setOffset(px, py);
        }
        prevMouseX = x;
        prevMouseY = y;
    }

    public void reload()
    {
        controls.clear();

        int minX = 0, minY = 0, maxX = 0, maxY = 0;

        IfChannel chan = getTabletScreen().selector.getSelectedChannel();
        for(IfEntryLocation loc : chan.getEntries())
        {
            IfEntry entr = PurMag.INSTANCE.if_registry.getEntry(loc.getEntry());
            if (entr.isAvailableToSee(Minecraft.getMinecraft().player, getTabletScreen().getTablet().tier))
            {
                if (loc.getX() < minX)
                    minX = loc.getX();
                if (loc.getY() < minY)
                    minY = loc.getY();
                if (loc.getX() > maxX)
                    maxX = loc.getX();
                if (loc.getY() > maxY)
                    maxY = loc.getY();
                GuiIfTabletSEEntry entrC = new GuiIfTabletSEEntry(entr);
                entrC.setX(loc.getX() * entrSize + ((getTabletScreen().getWidth() - entrSize) / 2));
                entrC.setY(loc.getY() * entrSize + ((getTabletScreen().getHeight() - entrSize) / 2));
                controls.add(entrC);
            }
        }

        offsetX = 0;
        offsetY = 0;
        minOffsetX = (minX * entrSize) - (getTabletScreen().getWidth() / 2) - (entrSize / 2);
        minOffsetY = (minY * entrSize) - (getTabletScreen().getHeight() / 2) - (entrSize / 2);
        maxOffsetX = (maxX * entrSize) + getTabletScreen().getWidth() / 2;
        maxOffsetY = (maxY * entrSize) + (getTabletScreen().getHeight() / 2);
    }

    public void setOffset(int pX, int pY)
    {
        int newX = offsetX + pX;
        int newY = offsetY + pY;
        if(newX >= minOffsetX && newY >= minOffsetY && newX <= maxOffsetX && newY <= maxOffsetY)
        {
            offsetX = newX;
            offsetY = newY;
            for(Control cont : controls)
            {
                cont.setX(cont.getX() + pX);
                cont.setY(cont.getY() + pY);
            }
        }
    }
}
