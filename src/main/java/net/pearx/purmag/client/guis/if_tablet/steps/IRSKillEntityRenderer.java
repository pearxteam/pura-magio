package net.pearx.purmag.client.guis.if_tablet.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.common.EntityShowcase;
import net.pearx.purmag.common.infofield.steps.IRSKillEntity;
import net.pearx.purmag.common.infofield.steps.IRSPapyrus;

import java.awt.*;

/**
 * Created by mrAppleXZ on 02.07.17 13:03.
 */
@SideOnly(Side.CLIENT)
public class IRSKillEntityRenderer extends IRSRenderer<IRSKillEntity>
{
    public EntityShowcase showcase;

    public IRSKillEntityRenderer(IRSKillEntity step)
    {
        super(step);
        showcase = new EntityShowcase(step.clazz);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(step.getDescription(), 0, getHeight() - DrawingTools.getFontHeight() - 5, Color.WHITE);
    }

    @Override
    public void init()
    {
        super.init();
        controls.add(showcase);
        showcase.setWidth(getWidth());
        showcase.setHeight(getHeight() - DrawingTools.getFontHeight() - 2 - 5);
    }
}
