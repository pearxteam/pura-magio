package ru.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

/*
 * Created by mrAppleXZ on 16.09.17 16:58.
 */
public class ItemBlockCodeStorage extends ItemBlockBase
{
    public ItemBlockCodeStorage()
    {
        super(BlockRegistry.code_storage);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + (stack.getMetadata() == 0 ? ".lockable" : ".not_lockable") + ".name");
    }
}
