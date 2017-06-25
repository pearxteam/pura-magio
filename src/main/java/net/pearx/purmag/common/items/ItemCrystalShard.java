package net.pearx.purmag.common.items;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.sip.SipType;

/**
 * Created by mrAppleXZ on 11.04.17 8:24.
 */
public class ItemCrystalShard extends ItemSingleSip
{
    public ItemCrystalShard()
    {
        setRegistryName("crystal_shard");
        setHasSubtypes(true);
    }
}
