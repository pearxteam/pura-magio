package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.sip.SipType;

/**
 * Created by mrAppleXZ on 14.05.17 18:51.
 */
public class ItemBlockSingleSip extends ItemBlockBase implements IModelProvider
{
    public ItemBlockSingleSip(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.INSTANCE.sip.getType(stack.getMetadata()).getDisplayName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(SipType t : PurMag.INSTANCE.sip.types)
            ClientUtils.setModelLocation(this, t.getId(), "");
    }
}
