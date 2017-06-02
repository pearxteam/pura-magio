package net.pearx.purmag.common.infofield.pages;

import net.pearx.purmag.client.guis.if_tablet.pages.IPPapyrusRenderer;
import net.pearx.purmag.client.guis.if_tablet.pages.IPRenderer;

/**
 * Created by mrAppleXZ on 02.06.17 21:59.
 */
public class IfPagePapyrus extends IfPageText
{
    public IfPagePapyrus(String unlocalized)
    {
        super(unlocalized);
    }

    public IfPagePapyrus(String unlocalized, String... properties)
    {
        super(unlocalized, properties);
    }

    @Override
    public IPRenderer getRenderer()
    {
        return new IPPapyrusRenderer(this);
    }
}
