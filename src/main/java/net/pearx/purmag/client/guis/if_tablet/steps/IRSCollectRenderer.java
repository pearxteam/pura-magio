package net.pearx.purmag.client.guis.if_tablet.steps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.drawables.ItemDrawable;
import net.pearx.purmag.common.infofield.steps.IRSCollect;

import java.awt.Color;
import java.util.List;

/**
 * Created by mrAppleXZ on 29.04.17 20:00.
 */
@SideOnly(Side.CLIENT)
public class IRSCollectRenderer extends IRSRenderer
{
    public IRSCollect step;

    @Override
    public void init()
    {
        super.init();
        step = (IRSCollect) getTablet().entry.getSteps().get(getTablet().doneSteps);
    }

    @Override
    public void render()
    {
        super.render();
        int yoff = 0;
        if(step.getShowStack())
        {
            List<ItemStack> toDisplay = step.getStacksToRender();
            ItemStack rend = toDisplay.get((int)(System.currentTimeMillis() / 1000 % toDisplay.size()));
            ItemDrawable draw = new ItemDrawable(rend, 5);
            yoff = draw.getWidth();
            draw.draw((getWidth() - draw.getWidth()) / 2, 0);
            //todo: Drawing item tooltip. Heh.
        }
        DrawingTools.drawString(step.getDescription(), 5, yoff, Color.WHITE, getWidth() - 5);
    }
}
