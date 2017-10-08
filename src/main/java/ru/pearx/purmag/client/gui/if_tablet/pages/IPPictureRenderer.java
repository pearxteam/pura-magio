package ru.pearx.purmag.client.gui.if_tablet.pages;

import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.client.infofield.pages.IfPagePicture;

/*
 * Created by mrAppleXZ on 07.10.17 12:24.
 */
public class IPPictureRenderer extends IPRenderer<IfPagePicture>
{
    public IPPictureRenderer(IfPagePicture page)
    {
        super(page);
    }

    @Override
    public void render()
    {
        super.render();
        page.getPicture().draw(getGuiScreen(), 0, 0);
        String s = page.getText();
        DrawingTools.drawString(s, 0, getHeight() - DrawingTools.getStringHeight(s, getWidth()), Colors.WHITE, getWidth());
    }
}
