package ru.pearx.purmag.client.gui.if_tablet.pages;

import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.EntityShowcase;
import ru.pearx.purmag.client.infofield.pages.IfPageEntity;


/*
 * Created by mrAppleXZ on 04.07.17 20:39.
 */
public class IPEntityRenderer extends IPRenderer<IfPageEntity>
{
    public EntityShowcase showcase;

    public IPEntityRenderer(IfPageEntity page)
    {
        super(page);
        showcase = new EntityShowcase(page.clazz);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(page.getName(), (getWidth() - DrawingTools.measureString(page.getName())) / 2, 0, Colors.WHITE);
    }

    @Override
    public void postRender()
    {

    }

    @Override
    public void init()
    {
        super.init();
        showcase.setPos(0, DrawingTools.getFontHeight());
        showcase.setSize(getWidth(), getHeight() - DrawingTools.getFontHeight());
        getControls().add(showcase);
    }
}
