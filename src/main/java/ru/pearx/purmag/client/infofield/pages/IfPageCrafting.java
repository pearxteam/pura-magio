package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPCraftingRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/*
 * Created by mrAppleXZ on 03.09.17 21:33.
 */
public class IfPageCrafting implements IIfPage
{
    private ResourceLocation[] ids;

    public IfPageCrafting(ResourceLocation... ids)
    {
        this.ids = ids;
    }

    public ResourceLocation[] getIds()
    {
        return ids;
    }

    public void setIds(ResourceLocation[] ids)
    {
        this.ids = ids;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IPRenderer getRenderer()
    {
        return new IPCraftingRenderer(this);
    }
}
