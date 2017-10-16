package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.libmc.common.structure.blockarray.BlockArrayEntry;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.infofield.steps.IRSBlockInteract;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.sip.SipUtils;
import ru.pearx.purmag.common.tiles.TileSingleSip;

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
        for(int i = 0, x = -(int)Math.floor((double)step.getRenderStates().size() / 2); i < step.getRenderStates().size(); i++, x++)
        {
            arr.getMap().put(new BlockPos(x, 0, 0), step.getRenderStates().get(i));
        }
        showcase = new BlockArrayShowcase(arr);
    }

    @Override
    public void init()
    {
        super.init();
        showcase.setSize(getWidth(), getHeight() - DrawingTools.getStringHeight(step.getDescription()));
        controls.add(showcase);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(step.getDescription(), 0, 0, Colors.WHITE, getWidth());
        int y = DrawingTools.getStringHeight(step.getDescription(), getWidth());
    }
}
