package ru.pearx.purmag.common.items.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.ClientUtils;
import ru.pearx.purmag.common.items.base.ItemBlockBase;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 14.05.17 18:51.
 */
public class ItemBlockSingleSip extends ItemBlockBase
{
    public ItemBlockSingleSip(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.INSTANCE.getSipRegistry().getType(SipUtils.getSipInStack(stack)).getDisplayName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        ClientUtils.setModelLocationSimple(this);
    }
}
