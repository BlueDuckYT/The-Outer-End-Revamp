package blueduck.outer_end;

import blueduck.outer_end.registry.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheOuterEnd.MODID)
public class TheOuterEnd
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "outer_end";

    public TheOuterEnd()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        OuterEndBlocks.BLOCKS.register(bus);
        OuterEndItems.BLOCK_ITEMS.register(bus);
        OuterEndItems.ITEMS.register(bus);
        OuterEndSounds.SOUNDS.register(bus);
        OuterEndFeatures.FEATURES.register(bus);
        OuterEndStructures.STRUCTURES.register(bus);
        OuterEndTabs.CREATIVE_TABS.register(bus);


    }
}
