package blueduck.outer_end.block;

import blueduck.outer_end.registry.OuterEndBlocks;
import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PowderSnowBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class BrineBlock extends PowderSnowBlock {
    public BrineBlock(Properties p_154253_) {
        super(p_154253_);
    }


    public static boolean canEntityWalkOnPowderSnow(Entity p_154256_) {
        if (p_154256_.getType().is(EntityTypeTags.POWDER_SNOW_WALKABLE_MOBS)) {
            return true;
        } else {
            return false;//p_154256_ instanceof LivingEntity ? ((LivingEntity)p_154256_).getItemBySlot(EquipmentSlot.FEET).canWalkOnPowderedSnow((LivingEntity)p_154256_) : false;
        }
    }

    public void entityInside(BlockState p_154263_, Level p_154264_, BlockPos p_154265_, Entity p_154266_) {
        if (!(p_154266_ instanceof LivingEntity) || p_154266_.getFeetBlockState().is(this)) {
            p_154266_.makeStuckInBlock(p_154263_, new Vec3((double)0.9F, 1.5D, (double)0.9F));
            if (p_154264_.isClientSide) {
                RandomSource randomsource = p_154264_.getRandom();
                boolean flag = p_154266_.xOld != p_154266_.getX() || p_154266_.zOld != p_154266_.getZ();
                if (flag && randomsource.nextBoolean()) {
                    p_154264_.addParticle(ParticleTypes.SNOWFLAKE, p_154266_.getX(), (double)(p_154265_.getY() + 1), p_154266_.getZ(), (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), (double)0.05F, (double)(Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
                }
            }
        }
        if (p_154266_ instanceof LivingEntity) {
            ((LivingEntity)p_154266_).addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 100, 0, true, true));
            ((LivingEntity)p_154266_).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0, true, true));
            ((LivingEntity)p_154266_).addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0, true, true));
            ((LivingEntity)p_154266_).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20, 4, true, true));

        }

        p_154266_.setIsInPowderSnow(true);

    }

    public ItemStack pickupBlock(LevelAccessor p_154281_, BlockPos p_154282_, BlockState p_154283_) {
        p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
        if (!p_154281_.isClientSide()) {
            p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
        }

        return new ItemStack(OuterEndItems.BRINE_BUCKET.get());
    }

    public void animateTick(BlockState p_220918_, Level p_220919_, BlockPos p_220920_, RandomSource p_220921_) {
        boolean flag = p_220919_.getBlockState(p_220920_.above()).is(Blocks.AIR);
        for (int i = 1; i < 6 && flag; i++) {
            if (!p_220919_.getBlockState(p_220920_.below(i)).is(OuterEndBlocks.BRINE.get())) {
                flag = false;
            }
        }
        if (flag && p_220921_.nextInt(12) == 0) {
            for(int i = 0; i < p_220921_.nextInt(1) + 1; ++i) {
                p_220919_.addAlwaysVisibleParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, true, (double)p_220920_.getX() + 0.5D + p_220921_.nextDouble() / 3.0D * (double)(p_220921_.nextBoolean() ? 1 : -1), (double)p_220920_.getY() + p_220921_.nextDouble() + p_220921_.nextDouble(), (double)p_220920_.getZ() + 0.5D + p_220921_.nextDouble() / 3.0D * (double)(p_220921_.nextBoolean() ? 1 : -1), 0.0D, 0.07D, 0.0D);
            }
        }

    }
}
