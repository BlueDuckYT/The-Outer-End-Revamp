package blueduck.outer_end.block.entity;

import blueduck.outer_end.registry.OuterEndBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class OEHangingSignBlockEntity extends SignBlockEntity {
    public OEHangingSignBlockEntity(BlockPos p_155700_, BlockState p_155701_) {
        super(OuterEndBlockEntities.MOD_HANGING_SIGN.get(), p_155700_, p_155701_);
    }

    @Override
    public BlockEntityType<?> getType() {
        return OuterEndBlockEntities.MOD_HANGING_SIGN.get();
    }

}
