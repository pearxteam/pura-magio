package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.common.infofield.IfEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 23.04.17 19:12.
 */
public class GuiIfTabletEntries extends ControlIfTabletPart
{
    public int entrSize = 32;
    public AnimatedDrawable runes;

    public int entrsMinX, entrsMaxX, entrsMinY, entrsMaxY;
    public int minX, maxX, minY, maxY;
    public int offsetX, offsetY;
    public boolean isMouseDown;

    public GuiIfTabletEntries()
    {

    }

    @Override
    public void init()
    {
        runes = new AnimatedDrawable(new ResourceLocation(PurMag.ModId, "textures/runes.png"), 32, 38, 32, 38, 32, 380, 100);
    }

    @Override
    public void mouseDown(int button, int x, int y)
    {
        isMouseDown = true;
    }

    @Override
    public void mouseUp(int button, int x, int y)
    {
        isMouseDown = false;
    }

    public void reload()
    {
        controls.clear();
        for(IfEntry entr : PurMag.instance.if_registry.entries)
        {
            if(getTablet().selector.getSelectedChannel().containsEntry(entr.getId()))
            {
                if(entr.isAvailable(Minecraft.getMinecraft().player, getTablet().tier))
                {
                    if(entr.getX() < entrsMinX)
                        entrsMinX = entr.getX();
                    if(entr.getX() > entrsMaxX)
                        entrsMaxX = entr.getX();
                    if(entr.getY() < entrsMinY)
                        entrsMinY = entr.getY();
                    if(entr.getY() > entrsMaxY)
                        entrsMaxY = entr.getY();
                    controls.add(new GuiIfTabletEntry(entr));
                }
            }
        }
        minX = entrsMinX * entrSize;
        minY = entrsMinY * entrSize;
        maxX = entrsMaxX * entrSize + entrSize;
        maxY = entrsMaxY * entrSize + entrSize;
    }

    public void setOffset(int plusX, int plusY)
    {
        int newX = offsetX + plusX;
        int newY = offsetY + plusY;
        if(newX <= maxX && newY <= maxY && newX >= minX && newY >= minY)
        {
            offsetX = newX;
            offsetY = newY;
        }
    }
}
