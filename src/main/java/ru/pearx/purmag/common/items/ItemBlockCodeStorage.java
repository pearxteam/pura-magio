package ru.pearx.purmag.common.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.ClientUtils;
import ru.pearx.purmag.common.blocks.BlockRegistry;

/*
 * Created by mrAppleXZ on 16.09.17 16:58.
 */
public class ItemBlockCodeStorage extends ItemBlockBase
{
    public ItemBlockCodeStorage()
    {
        super(BlockRegistry.code_storage);
        setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(int i = 0; i < 2; i++)
        {
            ClientUtils.setModelLocation(this, i, "");
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + (stack.getMetadata() == 0 ? ".lockable" : ".not_lockable") + ".name");
    }
}
