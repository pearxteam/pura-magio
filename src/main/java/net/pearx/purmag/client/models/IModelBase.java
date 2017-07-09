package net.pearx.purmag.client.models;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.item.ItemStack;

/**
 * Created by mrAppleXZ on 17.05.17 8:10.
 */
public interface IModelBase extends IBakedModel
{
    void bake();
    void setStack(ItemStack stack);
    ItemStack getStack();
}
