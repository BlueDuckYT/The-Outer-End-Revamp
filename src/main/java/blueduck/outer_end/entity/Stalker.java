package blueduck.outer_end.entity;

import blueduck.outer_end.registry.OuterEndEntities;
import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Stalker extends Animal implements NeutralMob {


    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    public int remainingPersistentAngerTime;
    @javax.annotation.Nullable
    private UUID persistentAngerTarget;

    private static final EntityDataAccessor<String> COLOR = SynchedEntityData.defineId(Stalker.class, EntityDataSerializers.STRING);


    public Stalker(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25D, Ingredient.of(OuterEndItems.AZURE_BERRIES.get()), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, (double)0.18F);
    }
    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_29835_, DifficultyInstance p_29836_, MobSpawnType p_29837_, @javax.annotation.Nullable SpawnGroupData p_29838_, @javax.annotation.Nullable CompoundTag p_29839_) {
        String col = new String[]{"rose","mint","cobalt"}[random.nextInt(3)];
        this.setColor(col);
//        p_29839_.putString("color", col);
        return super.finalizeSpawn(p_29835_, p_29836_, p_29837_, p_29838_, p_29839_);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COLOR, "cobalt");
    }

    public void addAdditionalSaveData(CompoundTag p_29864_) {
        super.addAdditionalSaveData(p_29864_);
        p_29864_.putString("color", getColor());
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setColor(nbt.getString("color"));
    }

    public String getColor() {
        return this.entityData.get(COLOR);
    }

    public void setColor(String input) {
        this.entityData.set(COLOR, input);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_) {
        return OuterEndEntities.STALKER.get().create(p_146743_);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.remainingPersistentAngerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_21673_) {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_21672_) {
        this.persistentAngerTarget = p_21672_;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }
    protected float getStandingEyeHeight(Pose p_28295_, EntityDimensions p_28296_) {
        return this.isBaby() ? p_28296_.height * 0.95F : 1.3F;
    }

    public boolean isFood(ItemStack p_27600_) {
        return p_27600_.is(OuterEndItems.AZURE_BERRIES.get());
    }
}
