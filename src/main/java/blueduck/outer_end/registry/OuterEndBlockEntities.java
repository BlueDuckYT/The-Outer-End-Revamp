package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.block.entity.OEHangingSignBlockEntity;
import blueduck.outer_end.block.entity.OESignBlockEntity;
import com.teamabnormals.blueprint.core.util.registry.BlockEntitySubRegistryHelper;
import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TheOuterEnd.MODID);
    public static final BlockEntitySubRegistryHelper HELPER = TheOuterEnd.HELPER.getBlockEntitySubHelper();

    public static final RegistryObject<BlockEntityType<OESignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("azure_sign", () ->
                    BlockEntityType.Builder.of(OESignBlockEntity::new,
                            OuterEndBlocks.AZURE_SIGNS.getFirst().get(), OuterEndBlocks.AZURE_SIGNS.getSecond().get()).build(null));
    public static final RegistryObject<BlockEntityType<OEHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("azure_hanging_sign", () ->
                    BlockEntityType.Builder.of(OEHangingSignBlockEntity::new,
                            OuterEndBlocks.AZURE_HANGING_SIGNS.getFirst().get(), OuterEndBlocks.AZURE_HANGING_SIGNS.getSecond().get()).build(null));


}
