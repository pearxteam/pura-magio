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
public class IfPageBlocks implements IIfPage
{
    private BlockArray array;
    private String unlocStructureName;

    public IfPageBlocks(BlockArray array, String unlocStructureName)
    {
        this.array = array;
        this.unlocStructureName = unlocStructureName;
    }

    public IfPageBlocks(BlockArray array)
    {
        this.array = array;
    }

    public String getUnlocalizedStructureName()
    {
        return unlocStructureName;
    }

    public void setUnlocalizedStructureName(String unlocStructureName)
    {
        this.unlocStructureName = unlocStructureName;
    }


    public String getStructureName()
    {
        return getUnlocalizedStructureName() == null ? "" : I18n.format(getUnlocalizedStructureName());
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
