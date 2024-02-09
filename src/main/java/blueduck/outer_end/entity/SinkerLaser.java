package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndEntities;
import blueduck.outer_end.registry.OuterEndItems;
import com.google.common.base.MoreObjects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class SinkerLaser extends ThrowableProjectile {
    private int duration = 200;

    public SinkerLaser(EntityType<? extends SinkerLaser> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
    }

    public SinkerLaser(Level p_37419_, LivingEntity p_37420_) {
        super(OuterEndEntities.SINKER_LASER.get(), p_37420_, p_37419_);
    }

    public SinkerLaser(Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(OuterEndEntities.SINKER_LASER.get(), p_37415_, p_37416_, p_37417_, p_37414_);
    }

    @Override
    public void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity entity = p_37259_.getEntity();
        Entity entity1 = this.getOwner();
        LivingEntity livingentity = entity1 instanceof LivingEntity ? (LivingEntity)entity1 : null;
        boolean flag = !(entity instanceof Sinker) && entity.hurt(this.damageSources().mobProjectile(this, livingentity), 8.0F);
        if (flag) {
            this.doEnchantDamageEffects(livingentity, entity);
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity)entity;
                if (!(livingentity1 instanceof Sinker)) {
                    livingentity1.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2), MoreObjects.firstNonNull(entity1, this));
                }
            }
        }
        Vec3 vec3 = this.getDeltaMovement();
        double d5 = vec3.x;
        double d6 = vec3.y;
        double d1 = vec3.z;
        for(int i = 0; i < 4; ++i) {
            this.level().addParticle(ParticleTypes.PORTAL, this.getX() + d5 * (double)i / 2.0D, this.getY() + d6 * (double)i / 2.0D, this.getZ() + d1 * (double)i / 2.0D, -d5, -d6 + 0.2D, -d1);
        }
    }
    public void tick() {
        super.tick();

        this.checkInsideBlocks();
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = this.getX() + vec3.x;
        double d0 = this.getY() + vec3.y;
        double d1 = this.getZ() + vec3.z;
        this.updateRotation();
        float f;
        if (this.isInWater()) {
            f = 1/0.8F;
        } else {
            f = 1/0.99F;
        }

        this.setDeltaMovement(vec3.scale((double)f));


        this.setPos(d2, d0, d1);
    }

    @Override
    protected void defineSynchedData() {

    }

    protected float getGravity() {
        return 0.00325F;
    }
}
