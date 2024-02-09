package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndSounds {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TheOuterEnd.MODID);


    public static final RegistryObject<SoundEvent> DISC_GALACTIC_WAVE = SOUNDS.register("music.outer_end.disc.galactic_wave", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "music.outer_end.disc.galactic_wave")));
    public static final RegistryObject<SoundEvent> DISC_UNKNOWN_FRONTIER = SOUNDS.register("music.outer_end.disc.unknown_frontier", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "music.outer_end.disc.unknown_frontier")));

    public static final RegistryObject<SoundEvent> MUSIC_AZURE_FOREST = SOUNDS.register("music.outer_end.azure", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "music.outer_end.azure")));
    public static final RegistryObject<SoundEvent> MUSIC_CRYSTAL_CRAG = SOUNDS.register("music.outer_end.crystal_crag", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "music.outer_end.crystal_crag")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_BREAK = SOUNDS.register("block.outer_end.violite.break", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.break")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_STEP = SOUNDS.register("block.outer_end.violite.step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.step")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_PLACE = SOUNDS.register("block.outer_end.violite.place", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.place")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_HIT = SOUNDS.register("block.outer_end.violite.hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.hit")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_FALL = SOUNDS.register("block.outer_end.violite.fall", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.fall")));

    public static final RegistryObject<SoundEvent> ENTITY_HIMMELITE_IDLE = SOUNDS.register("entity.outer_end.himmelite.idle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "entity.outer_end.himmelite.idle")));
    public static final RegistryObject<SoundEvent> ENTITY_HIMMELITE_HURT = SOUNDS.register("entity.outer_end.himmelite.hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "entity.outer_end.himmelite.hurt")));
    public static final RegistryObject<SoundEvent> ENTITY_HIMMELITE_DEATH = SOUNDS.register("entity.outer_end.himmelite.death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "entity.outer_end.himmelite.death")));

    public static final RegistryObject<SoundEvent> ENTITY_ENTOMBED_IDLE = SOUNDS.register("entity.outer_end.entombed.idle", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "entity.outer_end.entombed.idle")));
    public static final RegistryObject<SoundEvent> ENTITY_ENTOMBED_HURT = SOUNDS.register("entity.outer_end.entombed.hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "entity.outer_end.entombed.hurt")));
    public static final RegistryObject<SoundEvent> ENTITY_ENTOMBED_DEATH = SOUNDS.register("entity.outer_end.entombed.death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "entity.outer_end.entombed.death")));


    public static final SoundType VIOLITE_SOUND = new SoundType(1.0F, 1.5F,
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.break")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.step")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.place")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.hit")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.fall")));


}
