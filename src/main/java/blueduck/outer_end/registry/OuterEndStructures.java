package blueduck.outer_end.registry;

import blueduck.outer_end.TheOuterEnd;
import blueduck.outer_end.structure.LargeJigsawStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OuterEndStructures {

   public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, TheOuterEnd.MODID);

   public static final RegistryObject<StructureType<LargeJigsawStructure>> LARGE_JIGSAW_STRUCTURE = STRUCTURES.register("large_jigsaw_structure", () -> () -> LargeJigsawStructure.CODEC);
   }
