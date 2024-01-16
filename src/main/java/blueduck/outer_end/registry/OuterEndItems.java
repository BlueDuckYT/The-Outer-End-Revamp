package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheOuterEnd.MODID);
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TheOuterEnd.MODID);

    public static final RegistryObject<Item> SPECTRAGEL = ITEMS.register("spectragel", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROSE_CRYSTAL_SHARD = ITEMS.register("rose_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MINT_CRYSTAL_SHARD = ITEMS.register("mint_crystal_shard", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COBALT_CRYSTAL_SHARD = ITEMS.register("cobalt_crystal_shard", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MUSIC_DISC_GALACTIC_WAVE = ITEMS.register("music_disc_galactic_wave", () -> new RecordItem(15, () -> OuterEndSounds.DISC_GALACTIC_WAVE.get(), new Item.Properties().stacksTo(1), 3160));
    public static final RegistryObject<Item> MUSIC_DISC_UNKNOWN_FRONTIER = ITEMS.register("music_disc_unknown_frontier", () -> new RecordItem(15, () -> OuterEndSounds.DISC_UNKNOWN_FRONTIER.get(), new Item.Properties().stacksTo(1), 1320));


}
