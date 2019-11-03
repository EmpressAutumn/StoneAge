package com.yanny.age.zero.entities;

import com.yanny.age.zero.subscribers.EntitySubscriber;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class BoarEntity extends AnimalEntity implements IBecomeAngry {
    private int angerLevel;
    private UUID angerTargetUUID;

    public BoarEntity(EntityType<BoarEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableEntity createChild(@Nonnull AgeableEntity ageable) {
        return EntitySubscriber.boar.create(world);
    }

    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.fromItems(Items.CARROT), false));
        this.goalSelector.addGoal(5, new RaidFarmGoal<>(this, CropsBlock.class, CropsBlock.AGE));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new AgroTargetGoal(this, BoarEntity.class));
        this.targetSelector.addGoal(2, new TargetAggressorGoal(this));
    }

    public void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
    }

    @Override
    public void setRevengeTarget(@Nullable LivingEntity livingBase) {
        super.setRevengeTarget(livingBase);
        if (livingBase != null) {
            this.angerTargetUUID = livingBase.getUniqueID();
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        this.playSound(SoundEvents.ENTITY_PIG_HURT, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
        return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 4.0F);
    }

    @Override
    public void updateAITasks() {
        LivingEntity livingentity = this.getAttackTarget();
        if (livingentity != null && this.getDistanceSq(livingentity) < 16.0D) {
            this.calculateRotationYaw(livingentity.posX, livingentity.posZ);
            this.moveController.setMoveTo(livingentity.posX, livingentity.posY, livingentity.posZ, this.moveController.getSpeed());
        }

        LivingEntity revengeTarget = this.getRevengeTarget();
        if (this.isAngry()) {
            --this.angerLevel;
            LivingEntity livingentity1 = revengeTarget != null ? revengeTarget : livingentity;
            if (!this.isAngry() && livingentity1 != null) {
                if (!this.canEntityBeSeen(livingentity1)) {
                    this.setRevengeTarget(null);
                    this.setAttackTarget(null);
                } else {
                    this.angerLevel = this.nextRand();
                }
            }
        }

        if (this.isAngry() && this.angerTargetUUID != null && livingentity == null) {
            PlayerEntity playerentity = this.world.getPlayerByUuid(this.angerTargetUUID);
            this.setRevengeTarget(playerentity);
            this.attackingPlayer = playerentity;
            this.recentlyHit = this.getRevengeTimer();
        }
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getTrueSource();

            if (entity instanceof PlayerEntity && !((PlayerEntity)entity).isCreative() && this.canEntityBeSeen(entity)) {
                this.becomeAngryAt(entity);
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putShort("Anger", (short)this.angerLevel);
        if (this.angerTargetUUID != null) {
            compound.putString("HurtBy", this.angerTargetUUID.toString());
        } else {
            compound.putString("HurtBy", "");
        }

    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.angerLevel = compound.getShort("Anger");
        String s = compound.getString("HurtBy");
        if (!s.isEmpty()) {
            this.angerTargetUUID = UUID.fromString(s);
            PlayerEntity playerentity = this.world.getPlayerByUuid(this.angerTargetUUID);
            this.setRevengeTarget(playerentity);
            if (playerentity != null) {
                this.attackingPlayer = playerentity;
                this.recentlyHit = this.getRevengeTimer();
            }
        }

    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PIG_AMBIENT;
    }

    @Override
    public SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_PIG_HURT;
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PIG_DEATH;
    }

    @Override
    public void playStepSound(@Nonnull BlockPos pos, BlockState blockIn) {
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean becomeAngryAt(Entity entity) {
        this.angerLevel = this.nextRand();
        if (entity instanceof LivingEntity) {
            this.setRevengeTarget((LivingEntity)entity);
        }

        return true;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.CARROT;
    }

    private void calculateRotationYaw(double x, double z) {
        this.rotationYaw = (float)(MathHelper.atan2(z - this.posZ, x - this.posX) * (double)(180F / (float)Math.PI)) - 90.0F;
    }

    private int nextRand() {
        return 400 + this.rand.nextInt(400);
    }

    private boolean isAngry() {
        return this.angerLevel > 0;
    }

    static class TargetAggressorGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        TargetAggressorGoal(BoarEntity entity) {
            super(entity, PlayerEntity.class, true);
        }

        @Override
        public boolean shouldExecute() {
            return ((BoarEntity)this.goalOwner).isAngry() && super.shouldExecute();
        }
    }
}
