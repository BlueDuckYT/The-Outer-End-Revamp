package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.item.*;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.EnumMap;

public class OuterEndItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheOuterEnd.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheOuterEnd.MODID);

    public static final ItemSubRegistryHelper HELPER = TheOuterEnd.HELPER.getSubHelper(ForgeRegistries.ITEMS);

    public static final Pair<RegistryObject<Item>, RegistryObject<Item>> AZURE_BOATS = HELPER.createBoatAndChestBoatItem("azure", OuterEndBlocks.AZURE_PLANKS, false);
    public static final RegistryObject<Item> SPECTRAGEL = ITEMS.register("spectragel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLORAL_PASTE = ITEMS.register("floral_paste", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AZURE_BERRIES = ITEMS.register("azure_berries", () -> new ItemNameBlockItem(OuterEndBlocks.AZURE_BERRY_VINE_TOP.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(.1f).build())));
    public static final RegistryObject<Item> BRINE_BUCKET = ITEMS.register("brine_bucket", () -> new SolidBucketItem(OuterEndBlocks.BRINE.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> OMINOUS_MIASMA_BUCKET = ITEMS.register("ominous_miasma_bucket", () -> new SolidBucketItem(OuterEndBlocks.OMINOUS_MIASMA.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, new Item.Properties().stacksTo(1)));


    public static final RegistryObject<Item> ROSE_CRYSTAL_SHARD = ITEMS.register("rose_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MINT_CRYSTAL_SHARD = ITEMS.register("mint_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_SHARD = ITEMS.register("cobalt_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_CRYSTALLINE_BERRIES = ITEMS.register("rose_crystalline_berries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 100, 1), 1).build())));
    public static final RegistryObject<Item> MINT_CRYSTALLINE_BERRIES = ITEMS.register("mint_crystalline_berries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 100, 1), 1).build())));
    public static final RegistryObject<Item> COBALT_CRYSTALLINE_BERRIES = ITEMS.register("cobalt_crystalline_berries", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).alwaysEat().effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 100, 1), 1).build())));

    public static final RegistryObject<Item> ROSE_CRYSTAL_ARROW = ITEMS.register("rose_crystal_arrow", () -> new CrystalArrowItem(new Item.Properties(), () -> OuterEndEntities.ROSE_CRYSTAL_ARROW.get()));
    public static final RegistryObject<Item> MINT_CRYSTAL_ARROW = ITEMS.register("mint_crystal_arrow", () -> new CrystalArrowItem(new Item.Properties(), () -> OuterEndEntities.MINT_CRYSTAL_ARROW.get()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_ARROW = ITEMS.register("cobalt_crystal_arrow", () -> new CrystalArrowItem(new Item.Properties(), () -> OuterEndEntities.COBALT_CRYSTAL_ARROW.get()));

    public static final RegistryObject<Item> ROSE_UPGRADE_TEMPLATE = ITEMS.register("rose_upgrade_template", () -> OuterEndSmithingTemplate.createRoseCrystalUpgradeTemplate());
    public static final RegistryObject<Item> MINT_UPGRADE_TEMPLATE = ITEMS.register("mint_upgrade_template", () -> OuterEndSmithingTemplate.createMintCrystalUpgradeTemplate());
    public static final RegistryObject<Item> COBALT_UPGRADE_TEMPLATE = ITEMS.register("cobalt_upgrade_template", () -> OuterEndSmithingTemplate.createCobaltCrystalUpgradeTemplate());


    public static final CrystalMaterial ROSE_ARMOR_MATERIAL = new CrystalMaterial("rose");
    public static final CrystalMaterial MINT_ARMOR_MATERIAL = new CrystalMaterial("mint");
    public static final CrystalMaterial COBALT_ARMOR_MATERIAL = new CrystalMaterial("cobalt");

    public static final RegistryObject<Item> ROSE_CRYSTAL_HELMET = ITEMS.register("rose_crystal_helmet", () -> new CrystalArmorItem(ROSE_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ROSE_CRYSTAL_CHESTPLATE = ITEMS.register("rose_crystal_chestplate", () -> new CrystalArmorItem(ROSE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ROSE_CRYSTAL_LEGGINGS = ITEMS.register("rose_crystal_leggings", () -> new CrystalArmorItem(ROSE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ROSE_CRYSTAL_BOOTS = ITEMS.register("rose_crystal_boots", () -> new CrystalArmorItem(ROSE_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> MINT_CRYSTAL_HELMET = ITEMS.register("mint_crystal_helmet", () -> new CrystalArmorItem(MINT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MINT_CRYSTAL_CHESTPLATE = ITEMS.register("mint_crystal_chestplate", () -> new CrystalArmorItem(MINT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MINT_CRYSTAL_LEGGINGS = ITEMS.register("mint_crystal_leggings", () -> new CrystalArmorItem(MINT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MINT_CRYSTAL_BOOTS = ITEMS.register("mint_crystal_boots", () -> new CrystalArmorItem(MINT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> COBALT_CRYSTAL_HELMET = ITEMS.register("cobalt_crystal_helmet", () -> new CrystalArmorItem(COBALT_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_CHESTPLATE = ITEMS.register("cobalt_crystal_chestplate", () -> new CrystalArmorItem(COBALT_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_LEGGINGS = ITEMS.register("cobalt_crystal_leggings", () -> new CrystalArmorItem(COBALT_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_BOOTS = ITEMS.register("cobalt_crystal_boots", () -> new CrystalArmorItem(COBALT_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Properties()));





    public static final RegistryObject<Item> STALKER_MEAT = ITEMS.register("stalker_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1f).build())));
    public static final RegistryObject<Item> STALKER_STEAK = ITEMS.register("cooked_stalker_meat", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(8).saturationMod(0.3f).build())));

    public static final RegistryObject<Item> LEVITATION_CORE = ITEMS.register("levitation_core", () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> HALITE_CRYSTAL = ITEMS.register("halite_crystal", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CURED_CHORUS_FRUIT = ITEMS.register("cured_chorus_fruit", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(4).saturationMod(0.1f).build())));
    public static final RegistryObject<Item> CURED_JERKY = ITEMS.register("cured_jerky", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(0.1f).build())));

    public static final RegistryObject<Item> SINKER_TOOTH = ITEMS.register("sinker_tooth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SINKER_DAGGER = ITEMS.register("sinker_dagger", () -> new SinkerDaggerItem(new Item.Properties()));

    public static final RegistryObject<Item> SINKER_SHURIKEN = ITEMS.register("sinker_shuriken", () -> new ShurikenItem(new Item.Properties()));
    public static final RegistryObject<Item> SHULKER_SHIELD = ITEMS.register("shulker_shield", () -> new ShulkerShield(new Item.Properties().durability(776)));


    public static final RegistryObject<Item> MUSIC_DISC_GALACTIC_WAVE = ITEMS.register("music_disc_galactic_wave", () -> new RecordItem(15, () -> OuterEndSounds.DISC_GALACTIC_WAVE.get(), new Item.Properties().stacksTo(1), 3160));
    public static final RegistryObject<Item> MUSIC_DISC_UNKNOWN_FRONTIER = ITEMS.register("music_disc_unknown_frontier", () -> new RecordItem(15, () -> OuterEndSounds.DISC_UNKNOWN_FRONTIER.get(), new Item.Properties().stacksTo(1), 1320));


    public static final RegistryObject<ForgeSpawnEggItem> PURPUR_GOLEM_SPAWN_EGG = ITEMS.register("purpur_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(OuterEndEntities.PURPUR_GOLEM, 10105599, 4794755,
                    new Item.Properties()));
    public static final RegistryObject<ForgeSpawnEggItem> HIMMELITE_SPAWN_EGG = ITEMS.register("himmelite_spawn_egg",
            () -> new ForgeSpawnEggItem(OuterEndEntities.HIMMELITE, 1994982, 5413563,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> ENTOMBED_SPAWN_EGG = ITEMS.register("entombed_spawn_egg",
            () -> new ForgeSpawnEggItem(OuterEndEntities.ENTOMBED, 1575190, 6031692,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> STALKER_SPAWN_EGG = ITEMS.register("stalker_spawn_egg",
            () -> new ForgeSpawnEggItem(OuterEndEntities.STALKER, 10105599, 7538267,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> SPECTRAFLY_SPAWN_EGG = ITEMS.register("spectrafly_spawn_egg",
            () -> new ForgeSpawnEggItem(OuterEndEntities.SPECTRAFLY, 3093129, 11056703,
                    new Item.Properties()));

    public static final RegistryObject<ForgeSpawnEggItem> SINKER_SPAWN_EGG = ITEMS.register("sinker_spawn_egg",
            () -> new ForgeSpawnEggItem(OuterEndEntities.SINKER, 3093129, 11056703,
                    new Item.Properties()));


}
