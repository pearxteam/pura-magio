package ru.pearx.purmag.client.gui.if_tablet.pages;

import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.infofield.pages.IfPagePapyrus;

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
