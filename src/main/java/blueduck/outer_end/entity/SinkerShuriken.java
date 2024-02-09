package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndEntities;
import blueduck.outer_end.registry.OuterEndItems;
import com.google.common.base.MoreObjects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class SinkerShuriken extends ThrowableItemProjectile {
    public SinkerShuriken(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }
    public SinkerShuriken(Level p_37443_) {
        super(OuterEndEntities.SINKER_SHURIKEN.get(), p_37443_);
    }
    public SinkerShuriken(Level p_37443_, LivingEntity livingEntity ) {
        super(OuterEndEntities.SINKER_SHURIKEN.get(), livingEntity, p_37443_);
    }

    @Override
    protected Item getDefaultItem() {
        return OuterEndItems.SINKER_SHURIKEN.get();
    }

    @Override
    public void onHitEntity(EntityHitResult p_37259_) {
        super.onHitEntity(p_37259_);
        Entity entity = p_37259_.getEntity();
        Entity entity1 = this.getOwner();
        LivingEntity livingentity = entity1 instanceof LivingEntity ? (LivingEntity)entity1 : null;
        boolean flag = entity.hurt(this.damageSources().mobProjectile(this, livingentity), 8.0F);
        if (flag) {
            this.doEnchantDamageEffects(livingentity, entity);
            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity)entity;
                if (!(livingentity1 instanceof Sinker)) {
                    livingentity1.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 2), MoreObjects.firstNonNull(entity1, this));
                }
                }
        }
    }

    protected void onHit(HitResult p_37488_) {
        if (p_37488_.getType().equals(HitResult.Type.BLOCK)) {
            this.remove(RemovalReason.DISCARDED);
        }
        else {
            super.onHit(p_37488_);
        }
    }
}
