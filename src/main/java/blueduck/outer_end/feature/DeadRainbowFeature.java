package blueduck.outer_end.feature;

import blueduck.outer_end.registry.OuterEndBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DeadRainbowFeature extends Feature<NoneFeatureConfiguration> {
    public DeadRainbowFeature(Codec<NoneFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        double sizex = (context.random().nextInt(20) + 10) * (context.random().nextInt()*2-1);
        double sizez = (context.random().nextInt(20) - 10) * (context.random().nextInt()*2-1);
        int height = context.random().nextInt(15) + 20;
        //y = (x^2+z^2)
        for (int i = 0; i < Math.abs(sizex); i++) {
            for (int k = 0; k < Math.abs(sizez); k++) {
                int imult = sizex < 0 ?  -1 : 1;
                int kmult = sizez < 0 ?  -1 : 1;
//                BlockPos pos = context.origin().offset(i * imult, (int) (height/((Math.sqrt(((int)(Math.abs(sizex)-i))^2+(Math.abs(sizez)-k)^2)))), k * kmult);
//                context.level().setBlock(pos, OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
//                context.level().setBlock(pos.offset(Direction.NORTH.getNormal()), OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
//                context.level().setBlock(pos.offset(Direction.EAST.getNormal()), OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
//                context.level().setBlock(pos.offset(Direction.SOUTH.getNormal()), OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
//                context.level().setBlock(pos.offset(Direction.WEST.getNormal()), OuterEndBlocks.VIOLITE.get().defaultBlockState(), Block.UPDATE_NONE);
            }

        }
        return true;


    }

}
