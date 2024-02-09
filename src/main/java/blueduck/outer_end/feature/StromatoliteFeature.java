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

public class StromatoliteFeature extends Feature<NoneFeatureConfiguration> {


    public StromatoliteFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        if ((context.level().isEmptyBlock(context.origin()))) {

            if (context.origin().getY() < 20 || (!context.level().getBlockState(context.origin().below()).is(Blocks.END_STONE) && !context.level().getBlockState(context.origin().below()).is(OuterEndBlocks.ANCIENT_ICE.get()))) {
                return false;
            }



            List<BlockPos> points = ShapeUtil.generateSolidSphere((float) context.random().nextDouble()+1);

            for (BlockPos point : points) {
                List<BlockPos> points2 = ShapeUtil.generateSolidSphere((float) context.random().nextDouble()+1);
                for (BlockPos point2 : points2) {
                    BlockPos pointPos = new BlockPos(context.origin().getX() + point.getX() + point2.getX(), context.origin().getY() + point.getY() + point2.getY(), context.origin().getZ() + point.getZ() + point2.getZ());
                    if (context.level().getBlockState(pointPos).is(Blocks.AIR) || !context.level().getBlockState(pointPos).isCollisionShapeFullBlock(context.level(), pointPos) && context.random().nextDouble() > 0.05) {
                        context.level().setBlock(pointPos, OuterEndBlocks.STROMATOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
                    }
                }
            }
                return true;
        } else {
            return false;
        }
    }


}
