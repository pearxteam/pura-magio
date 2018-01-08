package ru.pearx.purmag.client.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPMultiblockRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;
import ru.pearx.purmag.common.blocks.multiblock.PMMultiblock;

/*
 * Created by mrAppleXZ on 01.01.18 21:54.
 */
@SideOnly(Side.CLIENT)
public class IfPageMultiblock extends IfPageAbstractBlocks
{
    private PMMultiblock multiblock;

    public IfPageMultiblock(PMMultiblock mb)
    {
        setMultiblock(mb);
    }

    public PMMultiblock getMultiblock()
    {
        return multiblock;
    }

    public void setMultiblock(PMMultiblock multiblock)
    {
        this.multiblock = multiblock;
    }

    @Override
    public IPRenderer createNewRenderer()
    {
        return new IPMultiblockRenderer(this);
    }

    @Override
    public String getText()
    {
        return multiblock.getName();
    }
}
