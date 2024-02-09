package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class PrimordialPoolFeature extends Feature<NoneFeatureConfiguration> {


    public PrimordialPoolFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {

            if (context.origin().getY() < 20) {
                return false;
            }

            List<BlockPos> basePoints = ShapeUtil.generateSolidCircle(3.5f);
            for (BlockPos point : basePoints) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() - 1, context.origin().getZ() + point.getZ());
                if (context.level().getBlockState(pointPos).is(Blocks.AIR)) {
                    return false;
                }
            }

            int radius = context.random().nextInt(3) + 7;
            int height = context.random().nextInt(7) + 5;

            int depth = 0;

            List<BlockPos> points = ShapeUtil.generateCircle(radius);
            List<BlockPos> pointsmid = ShapeUtil.generateCircle(radius - 1);
            List<BlockPos> pointstop = ShapeUtil.generateCircle(radius - 2);

            for (BlockPos point : points) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY(), pointPos.getZ() + point2.getZ());

                    if (context.level().getBlockState(pointPos2.below()).is(Blocks.AIR)) {
                        boolean flag = true;
                        int k = 0;
                        while (flag && k < -15) {

                            if (context.level().getBlockState(pointPos2.below(-k - 1)).is(Blocks.AIR)) {
                                context.level().setBlock(pointPos2.below(-k - 1), getIceColumn(context), Block.UPDATE_NONE);
                                k--;
                            } else {
                                if (k < depth) depth = k;
                                flag = false;
                            }
                        }
                    }
                }
            }

            for (BlockPos point : points) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    for (int j = 0; j < height/3; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR) || !context.level().getBlockState(pointPos2).isCollisionShapeFullBlock(context.level(), pointPos2) && context.random().nextDouble() > 0.05) {
                            context.level().setBlock(pointPos2, getIceColumn(context), Block.UPDATE_NONE);
                        }
                    }
                }

            }
            for (BlockPos point : pointsmid) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    for (int j = (height/3); j < height * 2/3; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY()+j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR) || !context.level().getBlockState(pointPos2).isCollisionShapeFullBlock(context.level(), pointPos2) && context.random().nextDouble() > 0.05) {
                            context.level().setBlock(pointPos2, getIceColumn(context), Block.UPDATE_NONE);
                        }
                    }
                }

            }
            for (BlockPos point : pointstop) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    for (int j = height * 2/3; j < height; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY()+j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR) || !context.level().getBlockState(pointPos2).isCollisionShapeFullBlock(context.level(), pointPos2) && context.random().nextDouble() > 0.05) {
                            context.level().setBlock(pointPos2, getIceColumn(context), Block.UPDATE_NONE);
                        }
                    }
                }

            }



//            for (BlockPos point : points) {
//                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
//                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
//                for (BlockPos point2 : points2) {
//                    for (int j = 0; j < height + 1; j++) {
//                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY()+j, pointPos.getZ() + point2.getZ());
//                        if (context.level().getBlockState(pointPos2.above()).is(Blocks.AIR) && context.level().getBlockState(pointPos2).is(OuterEndBlocks.ANCIENT_ICE.get())) {
//                            context.level().setBlock(pointPos2, OuterEndBlocks.ANCIENT_ICE_CAP.get().defaultBlockState(), Block.UPDATE_NONE);
//                        }
//                        if (context.level().getBlockState(pointPos2.above()).is(Blocks.AIR) && context.level().getBlockState(pointPos2).is(OuterEndBlocks.GLOWING_ANCIENT_ICE.get())) {
//                            context.level().setBlock(pointPos2, OuterEndBlocks.GLOWING_ANCIENT_ICE_CAP.get().defaultBlockState(), Block.UPDATE_NONE);
//                        }
//                    }
//                }
//
//            }

            List<BlockPos> pointsSolid = ShapeUtil.generateSolidCircle(radius-1);
            List<BlockPos> pointsSolidMid = ShapeUtil.generateSolidCircle(radius-3);
            List<BlockPos> pointsSolidTop = ShapeUtil.generateSolidCircle(radius-4);

            for (BlockPos point : pointsSolid) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    for (int j = depth; j < height/3; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() +j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR)) {
                            context.level().setBlock(pointPos2, OuterEndBlocks.BRINE.get().defaultBlockState(), Block.UPDATE_NONE);
                        }
                        if (j == depth) {
                            context.level().setBlock(pointPos2, OuterEndBlocks.ANCIENT_STONE.get().defaultBlockState(), Block.UPDATE_NONE);

                        }

                    }

                }

            }

            for (BlockPos point : pointsSolidMid) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    for (int j = height/3; j < height * 2/3; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() +j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR)) {
                            context.level().setBlock(pointPos2, OuterEndBlocks.BRINE.get().defaultBlockState(), Block.UPDATE_NONE);
                        }

                    }

                }

            }

            for (BlockPos point : pointsSolidTop) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
                for (BlockPos point2 : points2) {
                    for (int j = height * 2/3; j < height - 1; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() +j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR)) {
                            context.level().setBlock(pointPos2, OuterEndBlocks.BRINE.get().defaultBlockState(), Block.UPDATE_NONE);
                        }

                    }

                }

            }

            List<BlockPos> pointsSolidBig = ShapeUtil.generateSolidCircle(radius+1);

            for (BlockPos point : pointsSolidBig) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) (1 + context.random().nextDouble()));
                for (BlockPos point2 : points2) {
                    for (int j = depth; j < height + 1; j++) {
                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY() + j, pointPos.getZ() + point2.getZ());
                        if (context.level().getBlockState(pointPos2.above()).is(Blocks.AIR) && context.level().getBlockState(pointPos2).is(OuterEndBlocks.ANCIENT_ICE_COLUMN.get())) {
                            context.level().setBlock(pointPos2, OuterEndBlocks.ANCIENT_ICE_CAP.get().defaultBlockState(), Block.UPDATE_NONE);
                        }
                        if (context.level().getBlockState(pointPos2.above()).is(Blocks.AIR) && context.level().getBlockState(pointPos2).is(OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get())) {
                            context.level().setBlock(pointPos2, OuterEndBlocks.GLOWING_ANCIENT_ICE_CAP.get().defaultBlockState(), Block.UPDATE_NONE);
                        }
                        if (j == depth && context.level().getBlockState(pointPos2.below()).is(Blocks.AIR) || context.level().getBlockState(pointPos2.below()).is(Blocks.END_STONE)) {
                            context.level().setBlock(pointPos2.below(), getBase(context), Block.UPDATE_NONE);
                        }
                    }
                }
            }
            List<BlockPos> pointsBaseStone = ShapeUtil.generateSolidCircle(radius+3);


            for (BlockPos point : pointsBaseStone) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) (1 + context.random().nextDouble()*(radius/3)));
                for (BlockPos point2 : points2) {
                    BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY(), pointPos.getZ() + point2.getZ());
                    if (context.level().getBlockState(pointPos2).is(Blocks.END_STONE)) {
                        context.level().setBlock(pointPos2, getBase(context), Block.UPDATE_NONE);
                    }
                }
            }

            //            for (BlockPos point : points3) {
//                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY(), context.origin().getZ() + point.getZ());
//                List<BlockPos> points2 = ShapeUtil.generateSolidSphere(1);
//                for (BlockPos point2 : points2) {
//                    for (int j = 0; j < height; j++) {
//                        BlockPos pointPos2 = new BlockPos(pointPos.getX() + point2.getX(), pointPos.getY() + point2.getY()+j, pointPos.getZ() + point2.getZ());
//                        if (context.level().getBlockState(pointPos2).is(Blocks.AIR) || !context.level().getBlockState(pointPos2).isCollisionShapeFullBlock(context.level(), pointPos2)) {
//                            context.level().setBlock(pointPos2, context.random().nextDouble() < .75 ? OuterEndBlocks.ANCIENT_ICE_COLUMN.get().defaultBlockState() : OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get().defaultBlockState(), Block.UPDATE_NONE);
//                        }
//                    }
//                }
//
//            }



                return true;
        } else {
            return false;
        }
    }

    public BlockState getIceColumn(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return context.random().nextDouble() < .75 ? OuterEndBlocks.ANCIENT_ICE_COLUMN.get().defaultBlockState() : OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get().defaultBlockState();
    }
    public BlockState getBase(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return context.random().nextDouble() < .75 ? OuterEndBlocks.ANCIENT_STONE.get().defaultBlockState() : context.random().nextDouble() < .75 ? OuterEndBlocks.ANCIENT_ICE.get().defaultBlockState() : OuterEndBlocks.GLOWING_ANCIENT_ICE.get().defaultBlockState();
    }


}
