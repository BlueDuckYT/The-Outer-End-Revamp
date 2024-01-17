package blueduck.outer_end.events;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.entity.*;
import blueduck.outer_end.registry.OuterEndEntities;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheOuterEnd.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(OuterEndEntities.PURPUR_GOLEM.get(), PurpurGolem.createAttributes().build());
        event.put(OuterEndEntities.HIMMELITE.get(), Himmelite.createAttributes().build());
        event.put(OuterEndEntities.ENTOMBED.get(), Entombed.createAttributes().build());
        event.put(OuterEndEntities.STALKER.get(), Stalker.createAttributes().build());
        event.put(OuterEndEntities.SPECTRAFLY.get(), Spectrafly.createAttributes().build());

    }

}
