package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import com.mojang.datafixers.util.Pair;
import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheOuterEnd.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheOuterEnd.MODID);

    public static final ItemSubRegistryHelper HELPER = TheOuterEnd.HELPER.getSubHelper(ForgeRegistries.ITEMS);

    public static final Pair<RegistryObject<Item>, RegistryObject<Item>> AZURE_BOATS = HELPER.createBoatAndChestBoatItem("azure", OuterEndBlocks.AZURE_PLANKS, false);
    public static final RegistryObject<Item> SPECTRAGEL = ITEMS.register("spectragel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLORAL_PASTE = ITEMS.register("floral_paste", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AZURE_BERRIES = ITEMS.register("azure_berries", () -> new ItemNameBlockItem(OuterEndBlocks.AZURE_BERRY_VINE_TOP.get(), new Item.Properties().food(new FoodProperties.Builder().nutrition(3).saturationMod(.1f).build())));

    public static final RegistryObject<Item> ROSE_CRYSTAL_SHARD = ITEMS.register("rose_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MINT_CRYSTAL_SHARD = ITEMS.register("mint_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_SHARD = ITEMS.register("cobalt_crystal_shard", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STALKER_MEAT = ITEMS.register("stalker_meat", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STALKER_STEAK = ITEMS.register("cooked_stalker_meat", () -> new Item(new Item.Properties()));

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


}
