package net.pearx.purmag.client.guis.if_tablet.pages;

import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.infofield.pages.IfPagePapyrus;
import net.pearx.purmag.common.infofield.pages.IfPageText;

import java.awt.*;

/**
 * Created by mrAppleXZ on 02.06.17 22:00.
 */
public class IPPapyrusRenderer extends IPRenderer<IfPagePapyrus>
{
    public IPPapyrusRenderer(IfPagePapyrus page)
    {
        super(page);
    }

    @Override
    public void render()
    {
        super.render();
        DrawingTools.drawTexture(Utils.getRegistryName("textures/gui/papyrus_v.png"), 0, 0, getWidth(), getHeight(), 0, 0, 368, 207);
        DrawingTools.drawString(page.getDisplayText(), 5, 5, Color.LIGHT_GRAY, getWidth() - 5);
    }
}
