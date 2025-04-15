package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndSounds;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class Himmelite extends Monster {
    private static final EntityDataAccessor<Integer> BITE_FACTOR = SynchedEntityData.defineId(Himmelite.class, EntityDataSerializers.INT);
    private int lastBiteFactor = 0;
    private boolean updateLastFactor = false;

    public Himmelite(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    boolean retreating = false;
    boolean justFinishedRetreat = false;

    public void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    public void addBehaviourGoals() {
        this.goalSelector.addGoal(2, new HimmeliteAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new HimmeliteRetreatGoal(this));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.ARMOR, 6.0D)
                .add(Attributes.MAX_HEALTH, 12);
    }

    public static boolean canSpawn(EntityType<Himmelite> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        return checkMobSpawnRules(entityType, level, type, pos, rand);
    }

    public SoundEvent getAmbientSound() {
        return OuterEndSounds.ENTITY_HIMMELITE_IDLE.get();
    }

    public SoundEvent getHurtSound(DamageSource p_33579_) {
        return OuterEndSounds.ENTITY_HIMMELITE_HURT.get();
    }

    public SoundEvent getDeathSound() {
        return OuterEndSounds.ENTITY_HIMMELITE_DEATH.get();
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if (this.isNoAi() || this.level().isClientSide) {
            if (this.level().isClientSide) {
                if (this.updateLastFactor || getBiteFactor() == 0) {
                    lastBiteFactor = getBiteFactor();
                    updateLastFactor = false;
                }
            }
            return;
        }

        LivingEntity target = this.getTarget();
        if (target != null && !retreating) {
            if (this.getBiteFactor() >= 17)
                this.setBiteFactor(0);
            if (this.distanceTo(target) <= 10 && this.getBiteFactor() != 16)
                incrementBiteFactor(1);
            else if (this.distanceTo(target) >= 10) setBiteFactor(0);
        } else {
            setBiteFactor(-1);
        }
    }

    class HimmeliteRetreatGoal extends Goal {
        PathfinderMob mob;
        int ticksSinceLastCheck = 5;

        public HimmeliteRetreatGoal(PathfinderMob mob) {
            this.mob = mob;
        }

        @Override
        public boolean canUse() {
            return retreating;
        }

        @Override
        public boolean canContinueToUse() {
            return retreating;
        }

        Path path;
        BlockPos target;

        private void makePath() {
            Vec2 v2 = new Vec2(
                    (float) this.mob.getLookAngle().x,
                    (float) this.mob.getLookAngle().z
            ).normalized();

            Vec3 targ = this.mob.position().add(new Vec3(v2.x * -16, 0, v2.y * -16));
            int y = this.mob.level().getHeight(
                    Heightmap.Types.MOTION_BLOCKING,
                    Mth.floor(targ.x),
                    Mth.floor(targ.z)
            );
            this.target = BlockPos.containing(targ.x, y, targ.z);
            this.path = this.mob.getNavigation().createPath(target, 1);
            if (path != null)
                this.mob.getNavigation().moveTo(path, 1.0);
            else {
                System.out.println("Got null path");
            }
        }

        @Override
        public void start() {
            super.start();
            makePath();
        }

        @Override
        public void tick() {
            // TODO: every x ticks, double check path
            if ((ticksSinceLastCheck--) < 0) {
                Path pth = this.mob.getNavigation().createPath(target, 1);
                ;
                if (pth != null)
                    this.path = pth;
                ticksSinceLastCheck = 5;
            }

            this.mob.getNavigation().moveTo(path, 1.0);

            LivingEntity target = this.mob.getTarget();
            if (target == null) {
                retreating = false;
                return;
            }

            if (
                    (
                            path == null ||
                                    this.mob.position().distanceTo(path.getEndNode().asVec3()) <= 5
                    )
            ) {
                makePath();
            } else if (
                    this.mob.distanceTo(target) >= 10
            ) {
                // allow attack to start again
                retreating = false;
                justFinishedRetreat = true;
            }
        }
    }

    class HimmeliteAttackGoal extends MeleeAttackGoal {
        public HimmeliteAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            double d0 = this.getAttackReachSqr(pEnemy);

            if (pDistToEnemySqr <= d0) {
                if (getBiteFactor() == 16) {
                    setBiteFactor(17);

                    this.resetAttackCooldown();
                    this.mob.swing(InteractionHand.MAIN_HAND);
                    this.mob.doHurtTarget(pEnemy);
                    // engage retreat upon attack
                    retreating = true;
                }
            }
        }

        @Override
        public void tick() {
            super.tick();
            // if there is no current path, then make a new path
            if (path == null || path.isDone()) {
                if (canContinueToUse()) {
                    LivingEntity target = this.mob.getTarget();
                    this.path = this.mob.getNavigation().createPath(target, 0);
                }
            }
        }

        @Override
        public boolean canUse() {
            // vanilla puts a delay on rechecking
            // skip that if the retreat just ended
            if (justFinishedRetreat) {
                justFinishedRetreat = false;
                LivingEntity target = this.mob.getTarget();
                if (target == null) {
                    return false;
                } else if (!target.isAlive()) {
                    return false;
                } else {
                    this.path = this.mob.getNavigation().createPath(target, 0);
                    if (this.path != null) {
                        return true;
                    } else {
                        return this.getAttackReachSqr(target) >= this.mob.distanceToSqr(target.getX(), target.getY(), target.getZ());
                    }
                }
            }
            return super.canUse() && !retreating;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && !retreating;
        }

    }

    // synced data
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BITE_FACTOR, 0);
    }

    // === BITE DATA ===
    public void setBiteFactor(int amt) {
        this.entityData.set(BITE_FACTOR, amt);
    }

    public void incrementBiteFactor(int amt) {
        this.entityData.set(BITE_FACTOR, getBiteFactor() + amt);
    }

    public int getBiteFactor() {
        return this.entityData.get(BITE_FACTOR);
    }

    public int getLastBiteFactor() {
        return lastBiteFactor;
    }

    public void updateBiteFactor() {
        updateLastFactor = true;
    }
}
