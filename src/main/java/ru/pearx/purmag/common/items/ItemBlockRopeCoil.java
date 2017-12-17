package ru.pearx.purmag.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.blocks.BlockRopeCoil;

/*
 * Created by mrAppleXZ on 04.12.17 18:15.
 */
public class ItemBlockRopeCoil extends ItemBlockBase
{
    public ItemBlockRopeCoil()
    {
        super(BlockRegistry.rope_coil);

    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + BlockRopeCoil.Type.values()[stack.getMetadata()].getName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(BlockRopeCoil.Type t : BlockRopeCoil.Type.values())
            ModelLoader.setCustomModelResourceLocation(this, t.ordinal(), new ModelResourceLocation(Utils.gRL("rope_coil"), "axis=x,type=" + t.getName()));
    }
}
