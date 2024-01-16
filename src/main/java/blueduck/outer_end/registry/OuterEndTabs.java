package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheOuterEnd.MODID);

    public static final RegistryObject<CreativeModeTab> MOD_TAB = CREATIVE_TABS.register("outer_end_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + TheOuterEnd.MODID))
                    .icon(OuterEndItems.SPECTRAGEL.get()::getDefaultInstance)
                    .displayItems((displayParams, output) -> {
                        for (var item : OuterEndItems.BLOCK_ITEMS.getEntries()) {
                            output.accept(item.get());
                        }
                        for (var item : OuterEndItems.ITEMS.getEntries()) {
                            output.accept(item.get());
                        }
                    })
                    .build()
    );

}
