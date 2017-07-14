package ru.pearx.purmag.client.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 23.06.17 using Qubble.
 */
@SideOnly(Side.CLIENT)
public class ModelBeetle extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer heady;
    public ModelRenderer ear1;
    public ModelRenderer worm1;
    public ModelRenderer worm2;
    public ModelRenderer ear2;
    public ModelRenderer wing1;
    public ModelRenderer wing2;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;

    public ModelBeetle()
    {
        this.textureWidth = 64;
        this.textureHeight = 64;

        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.body.addBox(-7.0F, -2.5F, -5.5F, 14, 5, 11);
        this.setRotationAngles(this.body, 0.0F, 1.5707963267948966F, 0.0F);
        this.head = new ModelRenderer(this, 0, 16);
        this.head.setRotationPoint(6.5F, -0.2F, 0.5F);
        this.head.addBox(-3.0F, -2.0F, -4.0F, 6, 4, 8);
        this.body.addChild(this.head);
        this.heady = new ModelRenderer(this, 0, 42);
        this.heady.setRotationPoint(5.0F, -0.7F, 0.0F);
        this.heady.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
        this.head.addChild(this.heady);
        this.ear1 = new ModelRenderer(this, 50, 10);
        this.ear1.setRotationPoint(-0.5F, -3.5F, 1.4F);
        this.ear1.addBox(0.0F, -1.5F, -0.5F, 0, 3, 1);
        this.setRotationAngles(this.ear1, -0.12740903872453743F, 0.0F, 0.0F);
        this.heady.addChild(this.ear1);
        this.worm1 = new ModelRenderer(this, 54, 11);
        this.worm1.setRotationPoint(3.0F, 1.2F, 2.0F);
        this.worm1.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 1);
        this.setRotationAngles(this.worm1, 0.0F, -0.5235987755982988F, 0.0F);
        this.heady.addChild(this.worm1);
        this.worm2 = new ModelRenderer(this, 54, 13);
        this.worm2.setRotationPoint(3.0F, 1.2F, -2.0F);
        this.worm2.addBox(-1.5F, -0.5F, -0.5F, 2, 1, 1);
        this.setRotationAngles(this.worm2, 0.0F, 0.5235987755982988F, 0.0F);
        this.heady.addChild(this.worm2);
        this.ear2 = new ModelRenderer(this, 52, 10);
        this.ear2.setRotationPoint(-0.5F, -3.5F, -1.4F);
        this.ear2.addBox(0.0F, -1.5F, -0.5F, 0, 3, 1);
        this.setRotationAngles(this.ear2, 0.12740903872453743F, 0.0F, 0.0F);
        this.heady.addChild(this.ear2);
        this.wing1 = new ModelRenderer(this, 0, 28);
        this.wing1.setRotationPoint(7.0F, -3.5F, 0.0F);
        this.wing1.addBox(-15.0F, -0.5F, -6.0F, 15, 1, 6);
        this.setRotationAngles(this.wing1, 0.12217304763960307F, -0.17453292519943295F, 0.0F);
        this.body.addChild(this.wing1);
        this.wing2 = new ModelRenderer(this, 0, 35);
        this.wing2.setRotationPoint(7.0F, -3.5F, 1.0F);
        this.wing2.addBox(-15.0F, -0.5F, 0.0F, 15, 1, 6);
        this.setRotationAngles(this.wing2, -0.12217304763960307F, 0.17453292519943295F, 0.0F);
        this.body.addChild(this.wing2);
        this.leg1 = new ModelRenderer(this, 43, 16);
        this.leg1.setRotationPoint(-6.0F, 3.0F, -6.0F);
        this.leg1.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
        this.setRotationAngles(this.leg1, 0.7853981633974483F, 0.0F, 0.0F);
        this.body.addChild(this.leg1);
        this.leg2 = new ModelRenderer(this, 43, 22);
        this.leg2.setRotationPoint(0.0F, 3.0F, -6.0F);
        this.leg2.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
        this.setRotationAngles(this.leg2, 0.7853981633974483F, 0.0F, 0.0F);
        this.body.addChild(this.leg2);
        this.leg3 = new ModelRenderer(this, 43, 28);
        this.leg3.setRotationPoint(6.0F, 3.0F, -6.0F);
        this.leg3.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
        this.setRotationAngles(this.leg3, 0.7853981633974483F, 0.0F, 0.0F);
        this.body.addChild(this.leg3);
        this.leg4 = new ModelRenderer(this, 43, 34);
        this.leg4.setRotationPoint(6.0F, 3.0F, 6.0F);
        this.leg4.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
        this.setRotationAngles(this.leg4, -0.7853981633974483F, 0.0F, 0.0F);
        this.body.addChild(this.leg4);
        this.leg5 = new ModelRenderer(this, 43, 40);
        this.leg5.setRotationPoint(0.0F, 3.0F, 6.0F);
        this.leg5.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
        this.setRotationAngles(this.leg5, -0.7853981633974483F, 0.0F, 0.0F);
        this.body.addChild(this.leg5);
        this.leg6 = new ModelRenderer(this, 43, 46);
        this.leg6.setRotationPoint(-6.0F, 3.0F, 6.0F);
        this.leg6.addBox(-1.0F, -1.0F, -2.0F, 2, 2, 4);
        this.setRotationAngles(this.leg6, -0.7853981633974483F, 0.0F, 0.0F);
        this.body.addChild(this.leg6);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float rotationYaw, float rotationPitch, float scale)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.body.offsetX, this.body.offsetY, this.body.offsetZ);
        GlStateManager.translate(this.body.rotationPointX * scale, this.body.rotationPointY * scale, this.body.rotationPointZ * scale);
        GlStateManager.scale(1.0F, 1.0F, 1.0F);
        GlStateManager.translate(-this.body.offsetX, -this.body.offsetY, -this.body.offsetZ);
        GlStateManager.translate(-this.body.rotationPointX * scale, -this.body.rotationPointY * scale, -this.body.rotationPointZ * scale);
        this.body.render(scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;

        leg1.rotateAngleZ = (MathHelper.cos(limbSwing * 0.6662F * 3) * 1.4F * limbSwingAmount);
        leg2.rotateAngleZ = (MathHelper.cos(limbSwing * 0.6662F * 3 + (float) Math.PI) * 1.4F * limbSwingAmount);
        leg3.rotateAngleZ = (MathHelper.cos(limbSwing * 0.6662F * 3) * 1.4F * limbSwingAmount);

        leg4.rotateAngleZ = (MathHelper.cos(limbSwing * 0.6662F * 3) * 1.4F * limbSwingAmount);
        leg5.rotateAngleZ = (MathHelper.cos(limbSwing * 0.6662F * 3 + (float) Math.PI) * 1.4F * limbSwingAmount);
        leg6.rotateAngleZ = (MathHelper.cos(limbSwing * 0.6662F * 3) * 1.4F * limbSwingAmount);
    }

    private void setRotationAngles(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}