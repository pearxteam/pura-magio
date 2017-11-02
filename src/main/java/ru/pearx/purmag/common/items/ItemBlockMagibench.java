package ru.pearx.purmag.common.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.ClientUtils;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;

/*
 * Created by mrAppleXZ on 31.10.17 18:49.
 */
public class ItemBlockMagibench extends ItemBlockBase
{
    public ItemBlockMagibench()
    {
        super(BlockRegistry.magibench);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(MagibenchRegistry.Tier t : PurMag.INSTANCE.getMagibenchRegistry().getTiers())
        {
            ClientUtils.setModelLocation(this, t.getTier(), "");
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + stack.getItemDamage();
    }
}
