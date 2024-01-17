package blueduck.outer_end.block;

import blueduck.outer_end.registry.OuterEndBlocks;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.Shapes;

public class AzureBerryVineTop extends GrowingPlantHeadBlock {
    public AzureBerryVineTop(Properties p_53928_) {
        super(p_53928_, Direction.UP, Shapes.block(), true, .025);
    }

    @Override
    public int getBlocksToGrowWhenBonemealed(RandomSource p_221341_) {
        return 2;
    }

    @Override
    public boolean canGrowInto(BlockState p_53968_) {
        return p_53968_.is(Blocks.AIR);
    }

    @Override
    public Block getBodyBlock() {
        return OuterEndBlocks.AZURE_BERRY_VINE.get();
    }
}
