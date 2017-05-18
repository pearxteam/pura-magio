package net.pearx.purmag.client.models;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 18.05.17 10:00.
 */
@SideOnly(Side.CLIENT)
public class BakedQuadWNT extends BakedQuadRetextured
{
    public BakedQuadWNT(BakedQuad base, TextureAtlasSprite newSprite)
    {
        super(base, newSprite);
        sprite = newSprite;
    }
}
