package blueduck.outer_end;

import blueduck.outer_end.events.ClientEvents;
import blueduck.outer_end.registry.*;
import com.teamabnormals.blueprint.core.util.BiomeUtil;
import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheOuterEnd.MODID)
public class TheOuterEnd
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "outer_end";

    public static RegistryHelper HELPER = new RegistryHelper(MODID);

    public TheOuterEnd()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        OuterEndBlocks.BLOCKS.register(bus);
        OuterEndItems.BLOCK_ITEMS.register(bus);
        OuterEndItems.ITEMS.register(bus);
        HELPER.register(bus);
        OuterEndBlockEntities.BLOCK_ENTITIES.register(bus);
        OuterEndEntities.ENTITIES.register(bus);
        OuterEndSounds.SOUNDS.register(bus);
        OuterEndFeatures.FEATURES.register(bus);
        OuterEndStructures.STRUCTURES.register(bus);
        OuterEndTabs.CREATIVE_TABS.register(bus);

        bus.addListener(this::clientSetup);
        bus.addListener(this::commonSetup);

    }

    private void clientSetup(final FMLClientSetupEvent event) {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientEvents::setup);
    }
    private void commonSetup(final FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TheOuterEnd.MODID, "azure_bud")), .35F);
        ComposterBlock.COMPOSTABLES.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TheOuterEnd.MODID, "azure_leaves")), .9F);
        ComposterBlock.COMPOSTABLES.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TheOuterEnd.MODID, "azure_sprouts")), .35F);
        ComposterBlock.COMPOSTABLES.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TheOuterEnd.MODID, "ender_roots")), .35F);
        ComposterBlock.COMPOSTABLES.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(TheOuterEnd.MODID, "tall_ender_roots")), .65F);


    }
}
