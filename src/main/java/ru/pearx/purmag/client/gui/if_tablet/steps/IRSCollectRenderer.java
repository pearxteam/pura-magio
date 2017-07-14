package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.purmag.common.infofield.steps.IRSCollect;
import org.lwjgl.util.Rectangle;


import java.awt.*;
import java.util.List;

/**
 * Created by mrAppleXZ on 29.04.17 20:00.
 */
@SideOnly(Side.CLIENT)
public class IRSCollectRenderer extends IRSRenderer<IRSCollect>
{
    private int lastX, lastY;

    public IRSCollectRenderer(IRSCollect step)
    {
        super(step);
    }

    @Override
    public void render()
    {
        super.render();
        int yoff = 0;
        if(step.getShowStack())
        {
            List<ItemStack> toDisplay = step.getStacksToRender();
            ItemStack rend = toDisplay.get((int) (System.currentTimeMillis() / 1000 % toDisplay.size()));
            ItemDrawable draw = new ItemDrawable(rend, 5);
            yoff = draw.getWidth();
            int x = (getWidth() - draw.getWidth()) / 2;
            draw.draw(x, 0);
            DrawingTools.drawString(step.getDescription(), 5, yoff, Color.WHITE, getWidth() - 5);
            if(isFocused() && new Rectangle(x, 0, draw.getWidth(), draw.getHeight()).contains(lastX, lastY))
            {
                getGuiScreen().drawTooltip(rend, lastX, lastY);
            }
        }
        else
            DrawingTools.drawString(step.getDescription(), 5, yoff, Color.WHITE, getWidth() - 5);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        lastX = x;
        lastY = y;
    }
}
