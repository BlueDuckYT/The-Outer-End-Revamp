package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;
import java.util.Optional;

public class CragSphereFeature extends Feature<NoneFeatureConfiguration> {


    public CragSphereFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {
            Block[] ROSE = {OuterEndBlocks.ROSE_CRYSTAL.get(), OuterEndBlocks.ROSE_CRYSTAL_BUD.get(), OuterEndBlocks.ROSE_ROOTS.get()};
            Block[] MINT = {OuterEndBlocks.MINT_CRYSTAL.get(), OuterEndBlocks.MINT_CRYSTAL_BUD.get(), OuterEndBlocks.MINT_ROOTS.get()};
            Block[] COBALT = {OuterEndBlocks.COBALT_CRYSTAL.get(), OuterEndBlocks.COBALT_CRYSTAL_BUD.get(), OuterEndBlocks.COBALT_ROOTS.get()};
            Block[][] all = new Block[][]{ROSE, MINT, COBALT};
            Block[] blockSet = all[context.random().nextInt(all.length)];
            Direction[] offsets = new Direction[] { Direction.UP, Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};



            int height = 34 + context.random().nextInt(25);
            int diameter = 7 + context.random().nextInt(12);

            List<BlockPos> points = ShapeUtil.generateSolidSphere((float) diameter / 2);

            for (BlockPos point : points) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                context.level().setBlock(pointPos, OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
            }
            List<BlockPos> points4 = ShapeUtil.generateSolidSphere((float) (diameter / 2) + 2);
            for (BlockPos point : points4) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                for (Direction dir : offsets) {
                    if (context.level().getBlockState(pointPos).is(Blocks.AIR) && context.level().getBlockState(pointPos.offset(dir.getNormal())).isFaceSturdy(context.level(), pointPos.offset(dir.getNormal()), dir) && context.level().getBlockState(pointPos.offset(dir.getNormal())).isCollisionShapeFullBlock(context.level(), pointPos.offset(dir.getNormal())) && context.random().nextDouble() < 0.025) {
                        context.level().setBlock(pointPos, blockSet[2].defaultBlockState().setValue(AmethystClusterBlock.FACING, dir.getOpposite()), Block.UPDATE_NONE);
                    }
                }
            }
            List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) (diameter / 2) - 2);
            for (BlockPos point : points2) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                context.level().setBlock(pointPos, blockSet[0].defaultBlockState(), Block.UPDATE_NONE);
            }
            List<BlockPos> points3 = ShapeUtil.generateSolidSphere((float) (diameter / 2) - 4);

            for (BlockPos point : points3) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                context.level().setBlock(pointPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);
            }
            for (BlockPos point : points3) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                for (Direction dir : offsets) {
                    if (context.level().getBlockState(pointPos.offset(dir.getNormal())).isFaceSturdy(context.level(), pointPos.offset(dir.getNormal()), dir) && context.level().getBlockState(pointPos.offset(dir.getNormal())).isCollisionShapeFullBlock(context.level(), pointPos.offset(dir.getNormal())) && context.random().nextDouble() < 0.05) {
                        context.level().setBlock(pointPos, blockSet[1].defaultBlockState().setValue(AmethystClusterBlock.FACING, dir.getOpposite()), Block.UPDATE_NONE);
                    }
                }
            }


            return true;
        } else {
            return false;
        }
    }


}
