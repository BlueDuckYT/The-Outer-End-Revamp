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

public class FrozenGeodeFeature extends Feature<NoneFeatureConfiguration> {


    public FrozenGeodeFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {


            if (context.origin().getY() < 20) {
                return false;
            }

            int height = -2 + context.random().nextInt(5);
            int diameter = 4 + context.random().nextInt(6);

            List<BlockPos> points = ShapeUtil.generateSolidSphere((float) diameter / 2);

            Block iceBlock = context.random().nextDouble() < 0.75 ? OuterEndBlocks.ANCIENT_ICE.get() : OuterEndBlocks.GLOWING_ANCIENT_ICE.get();

            for (BlockPos point : points) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                if (context.level().getBlockState(pointPos).is(Blocks.END_STONE)) {
                    context.level().setBlock(pointPos, OuterEndBlocks.ANCIENT_STONE.get().defaultBlockState(), Block.UPDATE_NONE);
                }
            }
            List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) (diameter / 2) - 1f);
            for (BlockPos point : points2) {
                BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX(), context.origin().getY() + point.getY() + height, context.origin().getZ() + point.getZ());
                if (context.level().getBlockState(pointPos).is(OuterEndBlocks.ANCIENT_STONE.get())) {
                    context.level().setBlock(pointPos, iceBlock.defaultBlockState(), Block.UPDATE_NONE);
                }
            }
            return true;
        } else {
            return false;
        }
    }


}
