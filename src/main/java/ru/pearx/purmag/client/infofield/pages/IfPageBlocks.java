package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPBlocksRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/*
 * Created by mrAppleXZ on 17.10.17 15:02.
 */
@SideOnly(Side.CLIENT)
public class IfPageBlocks extends IfPageAbstractBlocks
{
    private BlockArray array;

    public IfPageBlocks(BlockArray array, String unlocStructureName)
    {
        setArray(array);
        setUnlocalizedStructureName(unlocStructureName);
    }

    public IfPageBlocks(BlockArray array)
    {
        setArray(array);
    }

    public BlockArray getArray()
    {
        return array;
    }

    public void setArray(BlockArray array)
    {
        this.array = array;
    }

    @Override
    public IPRenderer getRenderer()
    {
        return new IPBlocksRenderer(this);
    }
}
