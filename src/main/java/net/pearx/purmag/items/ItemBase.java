package net.pearx.purmag.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PMCore;
import net.pearx.purmag.PMCreativeTab;
import net.pearx.purmag.registries.ItemRegistry;

/**
 * Created by mrAppleXZ on 11.04.17 8:27.
 */
public class ItemBase extends Item
{
    public ItemBase()
    {
        setCreativeTab(PMCreativeTab.instance);
    }
}
