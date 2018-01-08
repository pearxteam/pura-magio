package ru.pearx.purmag.client.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPPapyrusRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/**
 * Created by mrAppleXZ on 02.06.17 21:59.
 */
@SideOnly(Side.CLIENT)
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
    public IPRenderer createNewRenderer()
    {
        return new IPPapyrusRenderer(this);
    }
}
