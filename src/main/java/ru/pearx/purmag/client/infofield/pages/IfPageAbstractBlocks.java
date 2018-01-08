package ru.pearx.purmag.client.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Created by mrAppleXZ on 01.01.18 21:54.
 */
@SideOnly(Side.CLIENT)
public abstract class IfPageAbstractBlocks extends IfPage
{
    public abstract String getText();
}
