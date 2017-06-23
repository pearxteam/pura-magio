package net.pearx.purmag.common.entities;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.pearx.purmag.common.SoundRegistry;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by mrAppleXZ on 20.06.17 9:44.
 */
public class EntityBeetle extends EntityCreature
{
    public EntityBeetle(World worldIn)
    {
        super(worldIn);
        setSize(1f, 0.5f);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27f);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4);
    }

    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackMelee(this, 1, true));
        tasks.addTask(3, new EntityAIWanderAvoidWater(this, 0.7f));
        tasks.addTask(4, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundRegistry.BEETLE_IDLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound()
    {
        return SoundRegistry.BEETLE_HURT;
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        if(entityIn instanceof EntityLiving)
        {
            ((EntityLiving)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 40));
        }
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue());
    }
}
