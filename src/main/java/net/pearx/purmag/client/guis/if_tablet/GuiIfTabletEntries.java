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

    public GuiIfTabletEntries()
    {

    }

    @Override
    public void init()
    {
        setWidth(getTablet().w - 10);
        setHeight(getTablet().h - 10);
        setX(5);
        setY(5);
        runes = new AnimatedDrawable(new ResourceLocation(PurMag.ModId, "textures/runes.png"), 32, 38, 32, 38, 32, 380, 100);
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
                    GuiIfTabletEntry entrC = new GuiIfTabletEntry(entr);
                    entrC.setX(entr.getX() * entrSize + ((getTablet().w - entrSize) / 2));
                    entrC.setY(entr.getY() * entrSize + ((getTablet().h - entrSize) / 2));
                    controls.add(entrC);
                }
            }
        }
    }
}
