package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndEntities;
import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class CrystalArrow extends AbstractArrow {
    private int duration = 200;
    private final Item pickupItem;

    public CrystalArrow(EntityType<? extends CrystalArrow> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
        this.pickupItem = OuterEndItems.ROSE_CRYSTAL_ARROW.get();
    }

    public CrystalArrow(EntityType<? extends CrystalArrow> p_37411_, Level p_37419_, LivingEntity p_37420_, ItemStack item) {
        super(p_37411_, p_37420_, p_37419_);
        this.pickupItem = item.getItem();
    }

    public CrystalArrow(EntityType<? extends CrystalArrow> p_37411_, Level p_37414_, double p_37415_, double p_37416_, double p_37417_) {
        super(p_37411_, p_37415_, p_37416_, p_37417_, p_37414_);
        this.pickupItem = OuterEndItems.ROSE_CRYSTAL_ARROW.get();
    }

    public void tick() {
        super.tick();
        if (this.level().isClientSide && !this.inGround) {
            this.level().addParticle(ParticleTypes.INSTANT_EFFECT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }

    }

    public ItemStack getPickupItem() {
        return new ItemStack(pickupItem);
    }

    protected void doPostHurtEffects(LivingEntity p_37422_) {
        super.doPostHurtEffects(p_37422_);
        MobEffectInstance mobeffectinstance = new MobEffectInstance(MobEffects.LEVITATION, this.duration, 0);
        p_37422_.addEffect(mobeffectinstance, this.getEffectSource());
    }

    public void readAdditionalSaveData(CompoundTag p_37424_) {
        super.readAdditionalSaveData(p_37424_);
        if (p_37424_.contains("Duration")) {
            this.duration = p_37424_.getInt("Duration");
        }

    }

    public void addAdditionalSaveData(CompoundTag p_37426_) {
        super.addAdditionalSaveData(p_37426_);
        p_37426_.putInt("Duration", this.duration);
    }


    protected float getGravity() {
        return 0.00325F;
    }
}
