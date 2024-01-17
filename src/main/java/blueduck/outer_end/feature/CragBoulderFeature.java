package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class CragBoulderFeature extends Feature<NoneFeatureConfiguration> {


    public CragBoulderFeature(Codec<NoneFeatureConfiguration> p_65786_) {
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
            Direction[] offsets = new Direction[]{Direction.UP, Direction.DOWN, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};

            if (context.origin().getY() < 20) {
                return false;
            }

            int height = -5 + context.random().nextInt(5);
            int diameter = 7 + context.random().nextInt(5);

            List<BlockPos> points = ShapeUtil.generateSolidSphere((float) diameter / 2);

            for (BlockPos point : points) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                if (context.level().getBlockState(pointPos).is(Blocks.AIR) || !context.level().getBlockState(pointPos).isCollisionShapeFullBlock(context.level(), pointPos) && context.random().nextDouble() > 0.05) {
                    context.level().setBlock(pointPos, OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
                }
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
            if ((diameter / 2) - 4 > 0) {
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) (diameter / 2) - 4f);
                for (BlockPos point : points2) {
                    BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                    context.level().setBlock(pointPos, blockSet[0].defaultBlockState(), Block.UPDATE_NONE);
                }
            }
//            List<BlockPos> points3 = ShapeUtil.generateSolidSphere((float) (diameter / 2) - 4);
//
//            for (BlockPos point : points3) {
//                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
//                context.level().setBlock(pointPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_NONE);
//            }
//            for (BlockPos point : points3) {
//                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
//                for (Direction dir : offsets) {
//                    if (context.level().getBlockState(pointPos.offset(dir.getNormal())).isFaceSturdy(context.level(), pointPos.offset(dir.getNormal()), dir) && context.level().getBlockState(pointPos.offset(dir.getNormal())).isCollisionShapeFullBlock(context.level(), pointPos.offset(dir.getNormal())) && context.random().nextDouble() < 0.05) {
//                        context.level().setBlock(pointPos, blockSet[1].defaultBlockState().setValue(AmethystClusterBlock.FACING, dir.getOpposite()), Block.UPDATE_NONE);
//                    }
//                }
//            }
                return true;
        } else {
            return false;
        }
    }


}
