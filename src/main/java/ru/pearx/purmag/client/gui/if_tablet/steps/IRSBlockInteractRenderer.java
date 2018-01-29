package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.common.infofield.steps.IRSBlockInteract;

/*
 * Created by mrAppleXZ on 13.10.17 20:25.
 */
@SideOnly(Side.CLIENT)
public class IRSBlockInteractRenderer extends IRSRenderer<IRSBlockInteract>
{
    public BlockArrayShowcase showcase;
    public IRSBlockInteractRenderer(IRSBlockInteract step)
    {
        super(step);
        BlockArray arr = new BlockArray();
        for(int i = 0, x = -step.getRenderStates().size() / 2; i < step.getRenderStates().size(); i++, x++)
        {
            arr.getMap().put(new BlockPos(x, 0, 0), step.getRenderStates().get(i));
        }
        showcase = new BlockArrayShowcase(PurMagClient.BUTTON_TEXTURE, arr);
    }

    @Override
    public void init()
    {
        super.init();
        showcase.setSize(getWidth(), getHeight() - DrawingTools.getStringHeight(step.getDescription()));
        getControls().add(showcase);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(step.getDescription(), 0, 0, Colors.WHITE, getWidth());
        int y = DrawingTools.getStringHeight(step.getDescription(), getWidth());
    }
}
