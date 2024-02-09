package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.joml.Vector3d;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.EnumSet;

public class Spectrafly extends PathfinderMob implements FlyingAnimal {

    private final FlyingPathNavigation pathNavigator;
    public Spectrafly(EntityType<? extends PathfinderMob> p_21368_, Level p_21369_) {
        super(p_21368_, p_21369_);
        pathNavigator = (FlyingPathNavigation) createNavigation(p_21369_);
    }


    public void registerGoals() {
        this.goalSelector.addGoal(3, new Spectrafly.ParrotWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(0, new Spectrafly.BeeWanderGoal());
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));

        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 20));
    }
    @Override
    public boolean isFlying() {
        return !this.onGround();
    }
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double)0.6F).add(Attributes.MOVEMENT_SPEED, (double)0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    protected PathNavigation createNavigation(Level p_27815_) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_27815_);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(false);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }
    protected void checkFallDamage(double p_29370_, boolean p_29371_, BlockState p_29372_, BlockPos p_29373_) {
    }

    public boolean canMate(Animal p_29381_) {
        return false;
    }

    @Nullable
    public AgeableMob getBreedOffspring(ServerLevel p_148993_, AgeableMob p_148994_) {
        return null;
    }

    static class ParrotWanderGoal extends WaterAvoidingRandomFlyingGoal {
        public ParrotWanderGoal(PathfinderMob p_186224_, double p_186225_) {
            super(p_186224_, p_186225_);
        }

        @Nullable
        protected Vec3 getPosition() {
            Vec3 vec3 = null;
            if (this.mob.isInWater()) {
                vec3 = LandRandomPos.getPos(this.mob, 15, 15);
            }

            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3 = this.getTreePos();
            }

            return vec3 == null ? super.getPosition() : vec3;
        }

        @Nullable
        private Vec3 getTreePos() {
            BlockPos blockpos = this.mob.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            BlockPos.MutableBlockPos blockpos$mutableblockpos1 = new BlockPos.MutableBlockPos();

            for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - 3.0D), Mth.floor(this.mob.getY() - 6.0D), Mth.floor(this.mob.getZ() - 3.0D), Mth.floor(this.mob.getX() + 3.0D), Mth.floor(this.mob.getY() + 6.0D), Mth.floor(this.mob.getZ() + 3.0D))) {
                if (!blockpos.equals(blockpos1)) {
                    BlockState blockstate = this.mob.level().getBlockState(blockpos$mutableblockpos1.setWithOffset(blockpos1, Direction.DOWN));
                    boolean flag = blockstate.getBlock() instanceof LeavesBlock || blockstate.is(BlockTags.LOGS) || blockstate.is(OuterEndBlocks.AZURE_STAMEN.get());
                    if (flag && this.mob.level().isEmptyBlock(blockpos1) && this.mob.level().isEmptyBlock(blockpos$mutableblockpos.setWithOffset(blockpos1, Direction.UP))) {
                        return Vec3.atBottomCenterOf(blockpos1);
                    }
                }
            }

            return null;
        }
    }

    class BeeWanderGoal extends Goal {
        private static final int WANDER_THRESHOLD = 22;

        BeeWanderGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return Spectrafly.this.navigation.isDone() && Spectrafly.this.random.nextInt(10) == 0;
        }

        public boolean canContinueToUse() {
            return Spectrafly.this.navigation.isInProgress();
        }

        public void start() {
            Vec3 vec3 = this.findPos();
            if (vec3 != null) {
                Spectrafly.this.navigation.moveTo(Spectrafly.this.navigation.createPath(BlockPos.containing(vec3), 1), 1.0D);
            }

        }

        @Nullable
        private Vec3 findPos() {
            Vec3 vec3 = Spectrafly.this.getViewVector(0.0F);

            int i = 8;
            Vec3 vec32 = HoverRandomPos.getPos(Spectrafly.this, 8, 7, vec3.x, vec3.z, ((float)Math.PI / 2F), 3, 1);
            return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(Spectrafly.this, 8, 4, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2F));
        }
    }

    public static boolean canSpawn(EntityType<Spectrafly> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        return checkMobSpawnRules(entityType, level, type, pos, rand);
    }

//    @Override
//    public void tick() {
////        if (this.getEntityWorld().isRemote || this.isAIDisabled()) {
////            super.tick();
////            if (FMLEnvironment.dist.isClient()) {
////                Color thisColor = new Color(this.getColor());
////                try {
////                    Color biomeColor = new Color(getColor(
////                            ForgeRegistries.BIOMES.getValue(
////                                    world.func_242406_i(this.getPosition()).orElse(Biomes.PLAINS).getLocation()
////                            )
////                    ));
////                    this.setColor(
////                            new Color(
////                                    (int)MathHelper.lerp(0.1f,thisColor.getRed(),biomeColor.getRed()),
////                                    (int)MathHelper.lerp(0.1f,thisColor.getGreen(),biomeColor.getGreen()),
////                                    (int)MathHelper.lerp(0.1f,thisColor.getBlue(),biomeColor.getBlue())
////                            ).getRGB()
////                    );
////                } catch (Throwable ignored) {
////                }
////            }
////            return;
////        }
//        this.fallDistance = 0;
//        Level world = this.level();
//        boolean isAboveBlock = world.getHeight(Heightmap.Types.MOTION_BLOCKING,this.getPosition(1).y >= 4;
//        if (
//                ((target == null || pathNavigator.getPath()==null || pathNavigator.getPath().isFinished()) && ((((shouldRepathfind() && this.onGround)||(this.getLastDamageSource()!=null))) || !isAboveBlock))
////						|| true
//        ) {
//            pathNavigator.clearPath();
//            pathNavigator.resetRangeMultiplier();
//            pathNavigator.setPath(getPath(), 1);
//        }
//        if (!pathNavigator.hasPath()) this.target = null;
//        this.setOnGround(pathNavigator.);
//        if (pathNavigator.getPath() != null && pathNavigator.getPath().getCurrentPathIndex() < pathNavigator.getPath().getCurrentPathLength() && target != null) {
//            Vector3d targ = pathNavigator.getPath().getVectorFromIndex(this, pathNavigator.getPath().getCurrentPathIndex());
//            Vector3d vector3d2 = new Vector3d(
//                    targ.getX(),
//                    targ.getY(),
//                    targ.getZ()
//            ).subtract(this.getPositionVec());
//            Vector3d vector3d3 = new Vector3d(
//                    target.getX(),
//                    target.getY(),
//                    target.getZ()
//            ).subtract(this.getPositionVec());
//            vector3d3 = vector3d3.mul(1,0,1);
//            pathNavigator.tick();
//            if (
//                    vector3d2.mul(1,0,1).squareDistanceTo(new Vector3d(0,0,0)) <=
//                            1
//            ) {
//                this.onGround = true;
//                Vector3d vector3d = new Vector3d(this.getMotion().x, Math.max(-0.1, this.getMotion().getY()-0.1f), this.getMotion().z);
//
//                boolean reachedTarget = this.getPosition().offset(Direction.DOWN,this.getPosition().getY()).equals(pathNavigator.getTargetPos().offset(Direction.DOWN, pathNavigator.getTargetPos().getY()));
//                if (this.getPosY() >= targ.getY() - 1) {
//                    if (!reachedTarget) pathNavigator.getPath().incrementPathIndex();
//                    if (reachedTarget || pathNavigator.getPath().getCurrentPathIndex() >= pathNavigator.getPath().getCurrentPathLength()) {
//                        if (this.getPosition().offset(Direction.DOWN,this.getPosition().getY()).distanceSq(new BlockPos(target.getX(),0,target.getZ())) >= 1) {
//                            pathNavigator.setPath(getPath(),1);
//                        } else {
//                            if (!this.world.getBlockState(this.getPosition().down()).isAir()) pathNavigator.clearPath();
//                            //TODO: figure out how to remove this and have it still work
////							this.setPosition(this.target.getX(),this.getPosY(),this.target.getZ());
//                            target = null;
//                        }
//                    }
//                    this.setMotion(vector3d);
//                }
//            }
//            if (target != null) {
//                this.onGround = false;
//                boolean isAbove =
//                        (this.getPositionVec().y >=
//                                (
//                                        target.getY()
//                                                + vector3d3.squareDistanceTo(new Vector3d(0, 0, 0))/128f
//                                ) &&
//                                0 >= (vector3d2.getY()+1)
//                        );
////				System.out.println(vector3d2.getY()+10);
////				System.out.println(isAbove);
////				System.out.println(
////						!isAbove?Math.max(-0.1,this.getMotion().y+0.1f):1
////				);
//                this.setMotion(
//                        MathHelper.lerp(0.1f, this.getMotion().x, vector3d2.normalize().x * 1.1f),
//                        !isAbove ?
//                                Math.max(-0.1,this.getMotion().y+0.1f) :
//                                MathHelper.lerp(0.2f, Math.max(-0.1, this.getMotion().getY()), -0.1f)
//                        ,
//                        MathHelper.lerp(0.1f, this.getMotion().z, vector3d2.normalize().z * 1.1f)
//                );
//            }
//        }
//        if (this.getPosY() <= 3)
//            this.setMotion(this.getMotion().x,MathHelper.lerp(0.1f,this.getMotion().y,-0.05f),this.getMotion().z);
//        if (this.getPosY() <= 2.9)
//            this.setMotion(this.getMotion().x,MathHelper.lerp(0.1f,this.getMotion().y,-0.01),this.getMotion().z);
//        if (this.getPosY() <= 2.8)
//            this.setMotion(this.getMotion().x,(this.getMotion().y+0.5f),this.getMotion().z);
//
//        if (this.getLeashed()) {
//            Vector3d pos = this.getLeashHolder().getPositionVec();
//            if (this.getDistanceSq(pos) >= 20)
//                this.setMotion(pos.subtract(this.getPositionVec()).scale(0.1f).add(this.getMotion()));
//            if (this.getDistanceSq(pos) >= 256) this.clearLeashed(true,true);
//        }
//
//        super.tick();
//    }


}
