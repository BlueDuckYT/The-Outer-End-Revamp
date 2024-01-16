package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.block.EndSaplingBlock;
import blueduck.outer_end.block.EnderDoublePlant;
import blueduck.outer_end.block.EnderTallGrassBlock;
import blueduck.outer_end.block.TangledVioliteBlock;
import blueduck.outer_end.feature.helpers.AzureTreeGrower;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class OuterEndBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TheOuterEnd.MODID);

    public static final RegistryObject<Block> AZURE_STEM = registerBlock("azure_stem", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_AZURE_STEM = registerBlock("azure_stripped_stem", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> AZURE_PITH = registerBlock("azure_pith", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_AZURE_PITH = registerBlock("azure_stripped_pith", () -> new RotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> AZURE_PLANKS = registerBlock("azure_planks", () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> AZURE_SLAB = registerBlock("azure_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> AZURE_STAIRS = registerBlock("azure_stairs", () -> new StairBlock(() -> AZURE_PLANKS.get().defaultBlockState(), Block.Properties.copy(Blocks.OAK_STAIRS)));
    public static final RegistryObject<Block> AZURE_DOOR = registerBlock("azure_door", () -> new DoorBlock(Block.Properties.copy(Blocks.OAK_PLANKS).noOcclusion(), BlockSetType.MANGROVE));
    public static final RegistryObject<Block> AZURE_TRAPDOOR = registerBlock("azure_trapdoor", () -> new TrapDoorBlock(Block.Properties.copy(Blocks.OAK_PLANKS).noOcclusion(), BlockSetType.MANGROVE));
    public static final RegistryObject<Block> AZURE_FENCE = registerBlock("azure_fence", () -> new FenceBlock(Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> AZURE_FENCE_GATE = registerBlock("azure_fence_gate", () -> new FenceGateBlock(Block.Properties.copy(Blocks.OAK_PLANKS), WoodType.MANGROVE));
    public static final RegistryObject<Block> AZURE_BUTTON = registerBlock("azure_button", () -> new ButtonBlock(Block.Properties.copy(Blocks.OAK_BUTTON), BlockSetType.MANGROVE, 30, true));
    public static final RegistryObject<Block> AZURE_PRESSURE_PLATE = registerBlock("azure_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.copy(Blocks.OAK_PRESSURE_PLATE), BlockSetType.MANGROVE));
    public static final RegistryObject<Block> AZURE_LEAVES = registerBlock("azure_leaves", () -> new LeavesBlock(Block.Properties.copy(Blocks.MANGROVE_LEAVES)));
    public static final RegistryObject<Block> AZURE_STAMEN = registerBlock("azure_stamen", () -> new Block(Block.Properties.copy(Blocks.SHROOMLIGHT)));
    public static final RegistryObject<Block> AZURE_BUD = registerBlock("azure_bud", () -> new EndSaplingBlock(new AzureTreeGrower(), Block.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> AZURE_GRASS = registerBlock("azure_grass", () -> new Block(Block.Properties.copy(Blocks.END_STONE)));
    public static final RegistryObject<Block> AZURE_SPROUTS = registerBlock("azure_sprouts", () -> new EnderTallGrassBlock(Block.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.POINTED_DRIPSTONE)));
    public static final RegistryObject<Block> ENDER_ROOTS = registerBlock("ender_roots", () -> new EnderTallGrassBlock(Block.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.POINTED_DRIPSTONE)));
    public static final RegistryObject<Block> TALL_ENDER_ROOTS = registerBlock("tall_ender_roots", () -> new EnderDoublePlant(Block.Properties.copy(Blocks.TALL_GRASS).sound(SoundType.POINTED_DRIPSTONE)));
    public static final RegistryObject<Block> AZURE_VINES = registerBlock("azure_vines", () -> new VineBlock(Block.Properties.copy(Blocks.MANGROVE_LEAVES)));

    public static final RegistryObject<Block> HIMMEL_BLOCK = registerBlock("himmel_block", () -> new Block(Block.Properties.copy(Blocks.PURPUR_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> HIMMEL_PILLAR = registerBlock("himmel_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(HIMMEL_BLOCK.get())));
    public static final RegistryObject<Block> HIMMEL_SLAB = registerBlock("himmel_slab", () -> new SlabBlock(Block.Properties.copy(HIMMEL_BLOCK.get())));
    public static final RegistryObject<Block> HIMMEL_STAIRS = registerBlock("himmel_stairs", () -> new StairBlock(() -> HIMMEL_BLOCK.get().defaultBlockState(), Block.Properties.copy(HIMMEL_BLOCK.get())));


    public static final RegistryObject<Block> VIOLITE = registerBlock("violite", () -> new Block(Block.Properties.of().mapColor(MapColor.COLOR_PURPLE).explosionResistance(6.0f).requiresCorrectToolForDrops().strength(2.0f).sound(OuterEndSounds.VIOLITE_SOUND)));
    public static final RegistryObject<Block> VIOLITE_SLAB = registerBlock("violite_slab", () -> new SlabBlock(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_STAIRS = registerBlock("violite_stairs", () -> new StairBlock(() -> VIOLITE.get().defaultBlockState(), Block.Properties.copy(VIOLITE.get())));


    public static final RegistryObject<Block> VIOLITE_BRICKS = registerBlock("violite_bricks", () -> new Block(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_BRICK_SLAB = registerBlock("violite_brick_slab", () -> new SlabBlock(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_BRICK_STAIRS = registerBlock("violite_brick_stairs", () -> new StairBlock(() -> VIOLITE_BRICKS.get().defaultBlockState(), Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_BRICK_WALL = registerBlock("violite_brick_wall", () -> new WallBlock(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_BRICK_PILLAR = registerBlock("violite_brick_pillar", () -> new RotatedPillarBlock(Block.Properties.copy(VIOLITE.get())));


    public static final RegistryObject<Block> MOSSY_VIOLITE_BRICKS = registerBlock("mossy_violite_bricks", () -> new Block(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> MOSSY_VIOLITE_BRICK_SLAB = registerBlock("mossy_violite_brick_slab", () -> new SlabBlock(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> MOSSY_VIOLITE_BRICK_STAIRS = registerBlock("mossy_violite_brick_stairs", () -> new StairBlock(() -> MOSSY_VIOLITE_BRICKS.get().defaultBlockState(), Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> MOSSY_VIOLITE_BRICK_WALL = registerBlock("mossy_violite_brick_wall", () -> new WallBlock(Block.Properties.copy(VIOLITE.get())));

    public static final RegistryObject<Block> CRACKED_VIOLITE_BRICKS = registerBlock("cracked_violite_bricks", () -> new Block(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> CHISELED_VIOLITE_BRICKS = registerBlock("chiseled_violite_bricks", () -> new Block(Block.Properties.copy(VIOLITE.get())));

    public static final RegistryObject<Block> VIOLITE_TILES = registerBlock("violite_tiles", () -> new Block(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_TILE_SLAB = registerBlock("violite_tile_slab", () -> new SlabBlock(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> VIOLITE_TILE_STAIRS = registerBlock("violite_tile_stairs", () -> new StairBlock(() -> VIOLITE_TILES.get().defaultBlockState(), Block.Properties.copy(VIOLITE.get())));


    public static final RegistryObject<Block> MOSSY_VIOLITE_TILES = registerBlock("mossy_violite_tiles", () -> new Block(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> MOSSY_VIOLITE_TILE_SLAB = registerBlock("mossy_violite_tile_slab", () -> new SlabBlock(Block.Properties.copy(VIOLITE.get())));
    public static final RegistryObject<Block> MOSSY_VIOLITE_TILE_STAIRS = registerBlock("mossy_violite_tile_stairs", () -> new StairBlock(() -> MOSSY_VIOLITE_TILES.get().defaultBlockState(), Block.Properties.copy(VIOLITE.get())));

    public static final RegistryObject<Block> CRACKED_VIOLITE_TILES = registerBlock("cracked_violite_tiles", () -> new Block(Block.Properties.copy(VIOLITE.get())));


    public static final RegistryObject<Block> ROSE_CRYSTAL = registerBlock("rose_crystal", () -> new Block(Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PINK).lightLevel((e) -> {return 10;}).noOcclusion()));
    public static final RegistryObject<Block> MINT_CRYSTAL = registerBlock("mint_crystal", () -> new Block(Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel((e) -> {return 10;}).noOcclusion()));
    public static final RegistryObject<Block> COBALT_CRYSTAL = registerBlock("cobalt_crystal", () -> new Block(Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel((e) -> {return 10;}).noOcclusion()));

    public static final RegistryObject<Block> ROSE_CRYSTAL_BUD = registerBlock("rose_crystal_bud", () -> new AmethystClusterBlock(12,2, Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PINK).lightLevel((e) -> {return 7;})));
    public static final RegistryObject<Block> MINT_CRYSTAL_BUD = registerBlock("mint_crystal_bud", () -> new AmethystClusterBlock(12,2, Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel((e) -> {return 7;})));
    public static final RegistryObject<Block> COBALT_CRYSTAL_BUD = registerBlock("cobalt_crystal_bud", () -> new AmethystClusterBlock(12,2, Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel((e) -> {return 7;})));

    public static final RegistryObject<Block> ROSE_CRYSTAL_LAMP = registerBlock("rose_crystal_lamp", () -> new Block(Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_PINK).lightLevel((e) -> {return 15;}).noOcclusion()));
    public static final RegistryObject<Block> MINT_CRYSTAL_LAMP = registerBlock("mint_crystal_lamp", () -> new Block(Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).lightLevel((e) -> {return 15;}).noOcclusion()));
    public static final RegistryObject<Block> COBALT_CRYSTAL_LAMP = registerBlock("cobalt_crystal_lamp", () -> new Block(Block.Properties.copy(Blocks.AMETHYST_BLOCK).mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel((e) -> {return 15;}).noOcclusion()));

    public static final RegistryObject<Block> ROSE_TANGLED_VIOLITE = registerBlock("rose_tangled_violite", () -> new TangledVioliteBlock(Block.Properties.copy(VIOLITE.get()), ROSE_CRYSTAL_BUD.get()));
    public static final RegistryObject<Block> MINT_TANGLED_VIOLITE = registerBlock("mint_tangled_violite", () -> new TangledVioliteBlock(Block.Properties.copy(VIOLITE.get()), MINT_CRYSTAL_BUD.get()));
    public static final RegistryObject<Block> COBALT_TANGLED_VIOLITE = registerBlock("cobalt_tangled_violite", () -> new TangledVioliteBlock(Block.Properties.copy(VIOLITE.get()), COBALT_CRYSTAL_BUD.get()));

    public static final RegistryObject<Block> ROSE_ROOTS = registerBlock("rose_roots", () -> new AmethystClusterBlock(12,2, Block.Properties.copy(VIOLITE.get()).mapColor(MapColor.COLOR_PINK).noCollission().instabreak()));
    public static final RegistryObject<Block> MINT_ROOTS = registerBlock("mint_roots", () -> new AmethystClusterBlock(12,2, Block.Properties.copy(VIOLITE.get()).mapColor(MapColor.COLOR_LIGHT_GREEN).noCollission().instabreak()));
    public static final RegistryObject<Block> COBALT_ROOTS = registerBlock("cobalt_roots", () -> new AmethystClusterBlock(12,2, Block.Properties.copy(VIOLITE.get()).mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().instabreak()));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return OuterEndItems.BLOCK_ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

}
