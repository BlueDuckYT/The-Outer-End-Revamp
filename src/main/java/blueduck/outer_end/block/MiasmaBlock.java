package blueduck.outer_end.block;

import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class MiasmaBlock extends GlassBlock implements BucketPickup {
    public MiasmaBlock(Properties p_154253_) {
        super(p_154253_);
    }


    public void entityInside(BlockState p_154263_, Level p_154264_, BlockPos p_154265_, Entity p_154266_) {
        if (p_154266_ instanceof LivingEntity) {
            ((LivingEntity)p_154266_).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 1, true, true));

        }


    }

    public ItemStack pickupBlock(LevelAccessor p_154281_, BlockPos p_154282_, BlockState p_154283_) {
        p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
        if (!p_154281_.isClientSide()) {
            p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
        }

        return new ItemStack(OuterEndItems.OMINOUS_MIASMA_BUCKET.get());
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.empty();
    }
}
