package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
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
import net.minecraft.world.level.pathfinder.Path;
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
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 20));
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double) 0.6F).add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ATTACK_DAMAGE, 2.0D).add(Attributes.FOLLOW_RANGE, 48.0D);
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

    public static boolean canSpawn(EntityType<Spectrafly> entityType, ServerLevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource rand) {
        return checkMobSpawnRules(entityType, level, type, pos, rand);
    }

    private BlockPos target = null;

    public boolean shouldRepathfind() {
        int modTick = this.tickCount % 1000;
        if (modTick <= 10) return true;
        if (modTick > 250 && modTick < 500) {
            return random.nextInt(400) < 2;
        }
        return false;
    }

    public Path getPath() {
        BlockPos pos = target;
        if (pos == null) {
            for (int i = 0; i <= 32; i++) {
                int x1 = (int) this.getX() + random.nextInt(64) - 32;
                int z1 = (int) this.getZ() + random.nextInt(64) - 32;
                int y = level().getHeight(Heightmap.Types.MOTION_BLOCKING, x1, z1);
                boolean shouldFind = random.nextBoolean();
                if (y == 0) continue;
                // TODO: biome tags?
                if (pos != null) {
                    shouldFind = shouldFind &&
                            (level().getBiome(new BlockPos(x1, y, z1)).unwrapKey().get().location().toString().equals("outer_end:azure_forest") ||
                                    !level().getBiome(pos).unwrapKey().get().location().toString().equals("outer_end:azure_forest"))
                    ;
                    shouldFind = shouldFind || (
                            level().getBiome(new BlockPos(x1, y, z1)).unwrapKey().get().location().toString().equals("outer_end:azure_forest") &&
                                    !level().getBiome(pos).unwrapKey().get().location().toString().equals("outer_end:azure_forest")
                    );
                }
                if (shouldFind && pos == null)
                    pos = new BlockPos(x1, y, z1);
                int searchDist = 8;
                // 80% chance to favor landing on a stamen
                if (random.nextInt(100) < 80) {
                    for (int xOff = -searchDist; xOff <= searchDist; xOff++) {
                        int x = x1 + xOff;
                        for (int zOff = -searchDist; zOff <= searchDist; zOff++) {
                            int z = z1 + zOff;
                            int y1 = level().getHeight(Heightmap.Types.MOTION_BLOCKING, x1, z1) + 1;
                            if (level().getBlockState(new BlockPos(x, y1, z).below()).getBlock().equals(OuterEndBlocks.AZURE_STAMEN.get()))
                                if (pos == null || y1 > pos.getY()) pos = new BlockPos(x, y1, z);
                        }
                    }
                }
            }
            if (pos == null) {
                int x = (int) this.getX() + random.nextInt(64) - 32;
                int z = (int) this.getZ() + random.nextInt(64) - 32;
                int y = level().getHeight(Heightmap.Types.MOTION_BLOCKING, x, z);
                pos = new BlockPos(x, Math.max(8, y), z);
            }
        }
//		pos = new BlockPos(-107, 6, 275);
        while (!level().getBlockState(pos).isAir())
            pos = pos.above();
        Path path = pathNavigator.createPath(pos, 1);
        target = pos;
        return path;
    }

    @Override
    public void tick() {
        if (this.getY() < 1) {
            this.hurt(
                    level().damageSources().fellOutOfWorld(),
                    5
            );
        }
        if (this.level().isClientSide || this.isNoAi()) {
            super.tick();
//            if (FMLEnvironment.dist.isClient()) {
//                Color thisColor = new Color(this.getColor());
//                try {
//                    Color biomeColor = new Color(getColor(
//                            ForgeRegistries.BIOMES.getValue(
//                                    world.func_242406_i(this.getPosition()).orElse(Biomes.PLAINS).getLocation()
//                            )
//                    ));
//                    this.setColor(
//                            new Color(
//                                    (int)MathHelper.lerp(0.1f,thisColor.getRed(),biomeColor.getRed()),
//                                    (int)MathHelper.lerp(0.1f,thisColor.getGreen(),biomeColor.getGreen()),
//                                    (int)MathHelper.lerp(0.1f,thisColor.getBlue(),biomeColor.getBlue())
//                            ).getRGB()
//                    );
//                } catch (Throwable ignored) {
//                }
//            }
            return;
        }
        this.fallDistance = 0;
        boolean isAboveBlock = level().getHeight(Heightmap.Types.MOTION_BLOCKING, Mth.floor(this.position().x), Mth.floor(this.position().z)) >= 4;
        if (
                ((target == null || pathNavigator.getPath() == null || pathNavigator.getPath().isDone()) && ((((shouldRepathfind() && this.onGround()) || (this.getLastDamageSource() != null))) || !isAboveBlock))
//						|| true
        ) {
//            pathNavigator.clearPath();
            pathNavigator.resetMaxVisitedNodesMultiplier();
            pathNavigator.moveTo(getPath(), 1);
        }
        if (!pathNavigator.isInProgress()) this.target = null;
        this.setOnGround(pathNavigator.isDone());
        if (pathNavigator.getPath() != null && pathNavigator.getPath().getNextNodeIndex() < pathNavigator.getPath().getNodeCount() && target != null) {
            Vec3 targ = pathNavigator.getPath().getEntityPosAtNode(this, pathNavigator.getPath().getNextNodeIndex());
            Vec3 vector3d2 = new Vec3(
                    targ.x,
                    targ.y,
                    targ.z
            ).subtract(this.position());
            Vec3 vector3d3 = new Vec3(
                    target.getX(),
                    target.getY(),
                    target.getZ()
            ).subtract(this.position());
            vector3d3 = vector3d3.multiply(1, 0, 1);
            pathNavigator.tick();
            if (
                    vector3d2.multiply(1, 0, 1).distanceTo(new Vec3(0, 0, 0)) <=
                            1
            ) {
                this.setOnGround(true);
                Vec3 vector3d = new Vec3(this.getDeltaMovement().x, Math.max(-0.1, this.getDeltaMovement().y - 0.1f), this.getDeltaMovement().z);

                boolean reachedTarget = this.blockPosition().relative(Direction.DOWN, this.blockPosition().getY()).equals(pathNavigator.getTargetPos().relative(Direction.DOWN, pathNavigator.getTargetPos().getY()));
                if (this.getY() >= targ.y() - 1) {
                    if (!reachedTarget) pathNavigator.getPath().advance();
                    if (reachedTarget || pathNavigator.getPath().getNextNodeIndex() >= pathNavigator.getPath().getNodeCount()) {
                        if (this.blockPosition().relative(Direction.DOWN, this.blockPosition().getY()).distSqr(new BlockPos(target.getX(), 0, target.getZ())) >= 1) {
                            pathNavigator.moveTo(getPath(), 1);
                        } else {
                            if (!this.level().getBlockState(this.blockPosition().below()).isAir()) pathNavigator.stop();
                            //TODO: figure out how to remove this and have it still work
//							this.setPosition(this.target.getX(),this.getPosY(),this.target.getZ());
                            target = null;
                        }
                    }
                    this.setDeltaMovement(vector3d);
                }
            }
            if (target != null) {
                this.setOnGround( false);
                boolean isAbove =
                        (this.position().y >=
                                (
                                        target.getY()
                                                + vector3d3.distanceTo(new Vec3(0, 0, 0)) / 128f
                                ) &&
                                0 >= (vector3d2.y + 1)
                        );
//				System.out.println(vector3d2.getY()+10);
//				System.out.println(isAbove);
//				System.out.println(
//						!isAbove?Math.max(-0.1,this.getMotion().y+0.1f):1
//				);
                this.setDeltaMovement(
                        Mth.lerp(0.1f, this.getDeltaMovement().x, vector3d2.normalize().x * 1.1f),
                        !isAbove ?
                                Math.max(-0.1, this.getDeltaMovement().y + 0.1f) :
                                Mth.lerp(0.2f, Math.max(-0.1, this.getDeltaMovement().y), -0.1f)
                        ,
                        Mth.lerp(0.1f, this.getDeltaMovement().z, vector3d2.normalize().z * 1.1f)
                );
            }
        }
        if (this.getY() <= 3)
            this.setDeltaMovement(this.getDeltaMovement().x, Mth.lerp(0.1f, this.getDeltaMovement().y, -0.05f), this.getDeltaMovement().z);
        if (this.getY() <= 2.9)
            this.setDeltaMovement(this.getDeltaMovement().x, Mth.lerp(0.1f, this.getDeltaMovement().y, -0.01), this.getDeltaMovement().z);
        if (this.getY() <= 2.8)
            this.setDeltaMovement(this.getDeltaMovement().x, (this.getDeltaMovement().y + 0.5f), this.getDeltaMovement().z);

        if (this.isLeashed()) {
            Vec3 pos = this.getLeashHolder().position();
            if (this.distanceToSqr(pos) >= 20)
                this.setDeltaMovement(pos.subtract(this.position()).scale(0.1f).add(this.getDeltaMovement()));
            if (this.distanceToSqr(pos) >= 256) this.dropLeash(true, true);
        }

        if (pathNavigator.isInProgress()) {
            goalSelector.getRunningGoals().forEach((goal) -> {
                goal.stop();
            });
        }

        super.tick();
    }

//    public static int getColor(Biome biome) {
//        if (biome == null || biome.getRegistryName() == null) {
//            return 0;
//        }
//        if (
//                biome.getRegistryName().toString().equals("minecraft:crimson_forest")
//        ) {
//            return new Color(255, 15, 15).getRGB();
//        } else if (
//                biome.getRegistryName().toString().equals("minecraft:warped_forest")
//        ) {
//            return new Color(19, 112, 118).getRGB();
//        } else if (
//                biome.getRegistryName().toString().equals("minecraft:nether_wastes")
//        ) {
//            return new Color(100, 16, 16).getRGB();
//        } else if (
//                biome.getRegistryName().toString().equals("minecraft:soul_sand_valley")
//        ) {
//            return new Color(41, 35, 34).getRGB();
//        } else if (
//                biome.getRegistryName().toString().equals("minecraft:flower_forest")
//        ) {
//            return new Color(252, 233, 78).getRGB();
//        } else if (
//                biome.getRegistryName().toString().startsWith("minecraft:desert")
//        ) {
//            return new Color(244, 216, 120).getRGB();
//        } else if (
//                biome.getCategory().equals(Biome.Category.THEEND) &&
//                        biome.getRegistryName().getNamespace().equals("minecraft")
//        ) {
//            return new Color(255, 255, 255).getRGB();
//        } else if (
//                biome.getCategory().equals(Biome.Category.OCEAN) &&
//                        biome.getRegistryName().getNamespace().equals("minecraft")
//        ) {
//            return new Color(89, 115, 165).getRGB();
//        } else if (
//                biome.getRegistryName().toString().equals("minecraft:basalt_deltas")
//        ) {
//            return new Color(115, 120, 128).getRGB();
//        }
//        return biome.getGrassColor(0, 0);
//    }


}
