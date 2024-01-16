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

    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_BREAK = SOUNDS.register("block.outer_end.violite.break", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.break")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_STEP = SOUNDS.register("block.outer_end.violite.step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.step")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_PLACE = SOUNDS.register("block.outer_end.violite.place", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.place")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_HIT = SOUNDS.register("block.outer_end.violite.hit", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.hit")));
    public static final RegistryObject<SoundEvent> BLOCK_VIOLITE_FALL = SOUNDS.register("block.outer_end.violite.fall", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.fall")));

    public static final SoundType VIOLITE_SOUND = new SoundType(1.0F, 1.5F,
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.break")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.step")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.place")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.hit")),
            SoundEvent.createVariableRangeEvent(new ResourceLocation("outer_end", "block.outer_end.violite.fall")));


}
