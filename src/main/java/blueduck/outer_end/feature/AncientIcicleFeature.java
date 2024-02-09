package blueduck.outer_end.feature;

import blueduck.outer_end.feature.helpers.ShapeUtil;
import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;

public class AncientIcicleFeature extends Feature<NoneFeatureConfiguration> {


    public AncientIcicleFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {

            if (context.origin().getY() < 20) {
                return false;
            }
            int length = 6 + context.random().nextInt(8);
            float radius = length/3f;

            int startHeight = -1;

            for (int j = 1; j < 100; j++) {
                if (context.level().getBlockState(new BlockPos(context.origin().getX(), j, context.origin().getZ())).is(OuterEndBlocks.ANCIENT_ICE_COLUMN.get()) || context.level().getBlockState(new BlockPos(context.origin().getX(), j, context.origin().getZ())).is(OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get())) {
                    return false;
                }

                if (context.level().getBlockState(new BlockPos(context.origin().getX(), j, context.origin().getZ())).canOcclude()) {
                    startHeight = j;
                    break;
                }
            }
            if (startHeight == -1) {
                return false;
            }

            BlockPos newOrigin = new BlockPos(context.origin().getX(), startHeight, context.origin().getZ());
            boolean glowing = context.random().nextDouble() > 0.8;


            List<BlockPos> points = ShapeUtil.generateSolidCircle(radius);


            for (BlockPos point : points) {
                if (!context.level().getBlockState(newOrigin.offset(point.getX(), 1, point.getZ())).canOcclude()) {
                    return false;
                }
            }

            for (BlockPos point : points) {
                List<BlockPos> line = ShapeUtil.generateLine(newOrigin.offset(point.getX(), 0, point.getZ()), newOrigin.offset(0,-length,0));

                for (int i = 0; i < line.size(); i++) {
                    BlockPos pos = line.get(i);
                    if (!context.level().getBlockState(pos).canOcclude()) {
                        context.level().setBlock(pos, glowing ? OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get().defaultBlockState() : OuterEndBlocks.ANCIENT_ICE_COLUMN.get().defaultBlockState(), Block.UPDATE_NONE);
                    }
                }

            }

            for (BlockPos point : points) {
                for (int i = -1; i < 2; i++) {
                    if (context.level().getBlockState(newOrigin.offset(point.getX(), i, point.getZ())).is(OuterEndBlocks.ANCIENT_ICE_COLUMN.get()) && !context.level().getBlockState(newOrigin.offset(point.getX(), i+1, point.getZ())).is(OuterEndBlocks.ANCIENT_ICE_COLUMN.get())) {
                        context.level().setBlock(newOrigin.offset(point.getX(), i, point.getZ()), OuterEndBlocks.ANCIENT_ICE_CAP.get().defaultBlockState(), Block.UPDATE_NONE);
                    }
                    if (context.level().getBlockState(newOrigin.offset(point.getX(), i, point.getZ())).is(OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get()) && !context.level().getBlockState(newOrigin.offset(point.getX(), i+1, point.getZ())).is(OuterEndBlocks.GLOWING_ANCIENT_ICE_COLUMN.get())) {
                        context.level().setBlock(newOrigin.offset(point.getX(), i, point.getZ()), OuterEndBlocks.GLOWING_ANCIENT_ICE_CAP.get().defaultBlockState(), Block.UPDATE_NONE);
                    }
                }

            }



            return true;
        } else {
            return false;
        }
    }


}
