package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.purmag.common.infofield.steps.IRSPapyrus;
import ru.pearx.purmag.common.items.ItemRegistry;


/**
 * Created by mrAppleXZ on 01.06.17 19:42.
 */
@SideOnly(Side.CLIENT)
public class IRSPapyrusRenderer extends IRSRenderer<IRSPapyrus>
{
    private int lastX, lastY;
    private ItemDrawable draw;

    public IRSPapyrusRenderer(IRSPapyrus step)
    {
        super(step);
    }

    @Override
    public void render()
    {
        super.render();
        if (draw == null || draw.stack.getItem() != ItemRegistry.papyrus || !ItemRegistry.papyrus.getId(draw.stack).equals(step.id))
        {
            draw = new ItemDrawable(ItemRegistry.papyrus.getPapyrusItem(step.id), 5);
        }
        DrawingTools.drawString(step.getDescription(), 5, draw.getHeight() + 5, Colors.WHITE, getWidth() - 5);
        draw.drawWithTooltip(getGuiScreen(), (getWidth() - draw.getWidth()) / 2, 0, lastX, lastY);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        lastX = x;
        lastY = y;
    }

    @Override
    public void setFocused(boolean val)
    {
        super.setFocused(val);
        if (!val)
        {
            lastX = -1;
            lastY = -1;
        }
    }
}
