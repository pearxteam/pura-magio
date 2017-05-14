package net.pearx.purmag.common.items;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.PMCreativeTab;
import net.pearx.purmag.common.blocks.BlockRegistry;

/**
 * Created by mrAppleXZ on 10.04.17 23:00.
 */
public class ItemBlockCrystal extends ItemBlockSingleSip
{
    public ItemBlockCrystal()
    {
        super(BlockRegistry.crystal);
    }
}
