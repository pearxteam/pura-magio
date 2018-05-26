package ru.pearx.purmag.client.infofield.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.Supplied;
import ru.pearx.purmag.client.gui.controls.CraftingControl;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPCraftingRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

import java.util.*;

/*
 * Created by mrAppleXZ on 03.09.17 21:33.
 */
@SideOnly(Side.CLIENT)
public class IfPageCrafting extends IfPage
{
    private List<Supplied<CraftingControl>> crafts;

    @SafeVarargs
    public IfPageCrafting(Supplied<CraftingControl>... crafts)
    {
        setCrafts(Arrays.asList(crafts));
    }

    public List<Supplied<CraftingControl>> getCrafts()
    {
        return crafts;
    }

    public void setCrafts(List<Supplied<CraftingControl>> crafts)
    {
        this.crafts = crafts;
    }

    @Override
    public IPRenderer createNewRenderer()
    {
        return new IPCraftingRenderer(this);
    }
}
