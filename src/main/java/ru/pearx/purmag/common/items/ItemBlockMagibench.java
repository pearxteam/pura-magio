package ru.pearx.purmag.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.model.ModelLoader;
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
            ModelLoader.setCustomModelResourceLocation(this, t.getTier(), new ModelResourceLocation(t.getSmartModel(), "normal"));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocal(PurMag.INSTANCE.getMagibenchRegistry().getTier(stack.getMetadata()).getUnlocalizedName());
    }
}
