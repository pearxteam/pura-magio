package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.client.infofield.pages.IfPageBlocks;

/*
 * Created by mrAppleXZ on 17.10.17 15:06.
 */
@SideOnly(Side.CLIENT)
public class IPBlocksRenderer extends IPAbstractBlocksRenderer<IfPageBlocks>
{
    public IPBlocksRenderer(IfPageBlocks page)
    {
        super(page, new BlockArrayShowcase(PurMagClient.BUTTON_TEXTURE, page.getArray()));
    }
}
