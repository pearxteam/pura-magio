package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.client.resources.I18n;

/*
 * Created by mrAppleXZ on 01.01.18 21:54.
 */
public abstract class IfPageAbstractBlocks implements IIfPage
{
    private String unlocStructureName;

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
}
