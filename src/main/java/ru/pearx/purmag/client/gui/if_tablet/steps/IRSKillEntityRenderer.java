package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.EntityShowcase;
import ru.pearx.purmag.common.infofield.steps.IRSKillEntity;


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
        DrawingTools.drawString(step.getDescription(), 0, getHeight() - DrawingTools.getFontHeight() - 5, Colors.WHITE);
    }

    @Override
    public void init()
    {
        super.init();
        getControls().add(showcase);
        showcase.setWidth(getWidth());
        showcase.setHeight(getHeight() - DrawingTools.getFontHeight() - 2 - 5);
    }
}
