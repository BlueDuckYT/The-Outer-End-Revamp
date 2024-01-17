package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.RotatableFeaturePart;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.data.BlockTagsProvider;

import java.util.Random;

public class AzureTreeFeature extends Feature<NoneFeatureConfiguration> {
    public AzureTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> reader) {
        if ((reader.level().getBlockState(reader.origin()).is(Blocks.AIR) || reader.level().getBlockState(reader.origin()).is(OuterEndBlocks.AZURE_BUD.get())) && reader.level().getBlockState(reader.origin().below()).is(TagKey.create(Registries.BLOCK, new ResourceLocation("outer_end:end_plantable_on"))) && !reader.level().getBlockState(reader.origin().below()).is(OuterEndBlocks.AZURE_STAMEN.get()))
            generateTree(reader);
        else
            return false;
        return true;
    }

    private static ChunkAccess chunkGenerating = null;

    private static final Direction[] offsets = new Direction[] { Direction.NORTH, Direction.UP, Direction.NORTH, Direction.UP, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.WEST, null, Direction.EAST, null, Direction.WEST, null, Direction.NORTH, Direction.UP, Direction.EAST, Direction.UP, Direction.DOWN, Direction.WEST, Direction.WEST, Direction.UP, };

    private static final RotatableFeaturePart petalFoldPart = new RotatableFeaturePart(offsets);




    public static void generateTree(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if (context.random().nextDouble() < .0675) {
            generateTreeLarge(context);
        } else if (context.random().nextDouble() < 0.25) {
            generateTreeSmallest(context);
        } else {
            generateTreeSmall(context);
        }
    }

    public static void generateTreeLarge(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        RandomSource rand = context.random();

        int yPos;

        // Removes the sapling
        setBlock(world, pos, OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        int height = rand.nextInt(4) + 8;
        for (yPos = 0; yPos <= height; yPos++) {
            // Gen center log
            if (yPos < height)
                setLog(world, pos.offset(0, yPos, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

            // Gen surrounding logs
            if (yPos <= 1 || yPos >= height - 1) {
                setLog(world, pos.offset(1, yPos, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
                setLog(world, pos.offset(-1, yPos, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
                setLog(world, pos.offset(0, yPos, 1), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
                setLog(world, pos.offset(0, yPos, -1), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
            }
        }
        yPos -= 1;

        // Generate petals (helper class because holy cow this would be probably like
        // 200-400 lines of code elsewise)
        BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);
        petalFoldPart.generate(Direction.NORTH, pos.offset(0, yPos, 1), (posPlace) -> setLeaves(world, posPlace, leavesState));
        petalFoldPart.generate(Direction.SOUTH, pos.offset(0, yPos, -1), (posPlace) -> setLeaves(world, posPlace, leavesState));
        petalFoldPart.generate(Direction.EAST, pos.offset(-1, yPos, 0), (posPlace) -> setLeaves(world, posPlace, leavesState));
        petalFoldPart.generate(Direction.WEST, pos.offset(1, yPos, 0), (posPlace) -> setLeaves(world, posPlace, leavesState));

        // Generate stamen
        setLeaves(world, pos.offset(0, yPos, 0), OuterEndBlocks.AZURE_STAMEN.get().defaultBlockState());
        for (int stamenPosY = 0; stamenPosY < rand.nextInt(3) + 3; stamenPosY++) {
            setLeaves(world, pos.offset(0, yPos + stamenPosY + 1, 0), OuterEndBlocks.AZURE_STAMEN.get().defaultBlockState());
        }
    }

    public static void generateTreeSmall(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        RandomSource rand = context.random();
        int yPos;

        // Removes the sapling
        setBlock(world, pos, OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        // Gen center logs
        int height = rand.nextInt(4) + 6;
        for (yPos = 0; yPos < height; yPos++)
            setLog(world, pos.offset(0, yPos, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        // Gen surrounding logs
        setLog(world, pos.offset(1, 0, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
        setLog(world, pos.offset(-1, 0, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
        setLog(world, pos.offset(0, 0, 1), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
        setLog(world, pos.offset(0, 0, -1), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        // Gen transitional leaves
        {
            BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);
            setLeaves(world, pos.offset(1, yPos - 1, 0), leavesState);
            setLeaves(world, pos.offset(-1, yPos - 1, 0), leavesState);
            setLeaves(world, pos.offset(0, yPos - 1, 1), leavesState);
            setLeaves(world, pos.offset(0, yPos - 1, -1), leavesState);
        }

        // Gen + shape of leaves
        for (int horizontalOffset = 0; horizontalOffset <= 2; horizontalOffset++) {
            BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, horizontalOffset + 1);
            setLeaves(world, pos.offset(horizontalOffset, yPos, 0), leavesState);
            setLeaves(world, pos.offset(-horizontalOffset, yPos, 0), leavesState);
            setLeaves(world, pos.offset(0, yPos, horizontalOffset), leavesState);
            setLeaves(world, pos.offset(0, yPos, -horizontalOffset), leavesState);
        }

        // Gen Corner Leaves
        for (int yOff = 0; yOff <= 1; yOff++) {
            BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 3);
            setLeaves(world, pos.offset(1, yPos + yOff, 1), leavesState);
            setLeaves(world, pos.offset(1, yPos + yOff, -1), leavesState);
            setLeaves(world, pos.offset(-1, yPos + yOff, -1), leavesState);
            setLeaves(world, pos.offset(-1, yPos + yOff, 1), leavesState);
        }

        // Gen Stamen
        setLeaves(world, pos.offset(0, yPos + 1, 0), OuterEndBlocks.AZURE_STAMEN.get().defaultBlockState());
    }

    public static void generateTreeSmallest(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        RandomSource rand = context.random();
        int yPos;

        // Removes the sapling
        setBlock(world, pos, OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        // Gen center logs
        int height = rand.nextInt(3) + 4;
        for (yPos = 0; yPos < height; yPos++)
            setLog(world, pos.offset(0, yPos, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        // Gen surrounding logs
        setLog(world, pos.offset(1, 0, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
        setLog(world, pos.offset(-1, 0, 0), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
        setLog(world, pos.offset(0, 0, 1), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());
        setLog(world, pos.offset(0, 0, -1), OuterEndBlocks.AZURE_STEM.get().defaultBlockState());

        // Gen transitional leaves
        {
            BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);
            setLeaves(world, pos.offset(1, yPos - 1, 0), leavesState);
            setLeaves(world, pos.offset(-1, yPos - 1, 0), leavesState);
            setLeaves(world, pos.offset(0, yPos - 1, 1), leavesState);
            setLeaves(world, pos.offset(0, yPos - 1, -1), leavesState);
        }

        // Gen + shape of leaves
        for (int horizontalOffset = 0; horizontalOffset <= 2; horizontalOffset++) {
            BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, horizontalOffset + 1);
            setLeaves(world, pos.offset(horizontalOffset, yPos, 0), leavesState);
            setLeaves(world, pos.offset(-horizontalOffset, yPos, 0), leavesState);
            setLeaves(world, pos.offset(0, yPos, horizontalOffset), leavesState);
            setLeaves(world, pos.offset(0, yPos, -horizontalOffset), leavesState);
        }

        // Gen Corner Leaves
        for (int yOff = 0; yOff <= 1; yOff++) {
            BlockState leavesState = OuterEndBlocks.AZURE_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 3);
            setLeaves(world, pos.offset(1, yPos + yOff, 1), leavesState);
            setLeaves(world, pos.offset(1, yPos + yOff, -1), leavesState);
            setLeaves(world, pos.offset(-1, yPos + yOff, -1), leavesState);
            setLeaves(world, pos.offset(-1, yPos + yOff, 1), leavesState);
        }

        // Gen Stamen
        setLeaves(world, pos.offset(0, yPos + 1, 0), OuterEndBlocks.AZURE_STAMEN.get().defaultBlockState());
    }

    public static void setLeaves(WorldGenLevel world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).canBeReplaced())
            setBlock(world, pos, state);
    }

    public static void setLog(WorldGenLevel world, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).canBeReplaced())
            setBlock(world, pos, state);
    }

    public static void setBlock(WorldGenLevel world, BlockPos pos, BlockState state) {
        if (world instanceof WorldGenRegion) {
            if (chunkGenerating == null || !chunkGenerating.getPos().equals(new ChunkPos(pos)))
                chunkGenerating = ((WorldGenRegion) world).getChunk(pos);
            chunkGenerating.setBlockState(pos, state, false);
        } else {
            world.setBlock(pos, state, 3);
        }
    }
}