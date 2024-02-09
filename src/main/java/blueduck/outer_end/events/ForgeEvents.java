package blueduck.outer_end.events;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = TheOuterEnd.MODID)
public class ForgeEvents {

    @SubscribeEvent
    public static void registerLoot(LootTableLoadEvent event) {
        ResourceLocation name = event.getName();

        if (name.equals(new ResourceLocation("minecraft", "chests/end_city_treasure"))) {
            LootPool pool = event.getTable().getPool("main");
            if (pool != null) {
                addEntry(pool, getInjectEntry(new ResourceLocation("outer_end", "chests/end_city_treasure"), 10, 0));

            }
        }
    }
    private static LootPoolEntryContainer getInjectEntry(ResourceLocation location, int weight, int quality) {
        return LootTableReference.lootTableReference(location).setWeight(weight).setQuality(quality).build();
    }

    private static void addEntry(LootPool pool, LootPoolEntryContainer entry) {
        ArrayList<LootPoolEntryContainer> lootPoolEntriesArray = new ArrayList<>(List.of(pool.entries));
        ArrayList<LootPoolEntryContainer> newLootEntries = new ArrayList<>(lootPoolEntriesArray);
        newLootEntries.add(entry);
        pool.entries = newLootEntries.toArray(new LootPoolEntryContainer[]{});
    }

    @SubscribeEvent
    public static void shieldBlockEvent(ShieldBlockEvent event) {
        if (event.getEntity().getUseItem().is(OuterEndItems.SHULKER_SHIELD.get()) && event.getDamageSource().getDirectEntity() instanceof LivingEntity) {
            ((LivingEntity) event.getDamageSource().getDirectEntity()).addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 1));
            event.getDamageSource().getDirectEntity().hurt(event.getDamageSource().getDirectEntity().damageSources().thorns(event.getDamageSource().getDirectEntity()), 5);
        }
    }

}
