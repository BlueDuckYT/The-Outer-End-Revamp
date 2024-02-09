package blueduck.outer_end.item;

import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class CrystalMaterialNetherite implements ArmorMaterial {
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private static final int[] ARMOR_ARRAY = new int[]{3, 6, 8, 3};

    private final String crystalType;
    public CrystalMaterialNetherite(String crystal) {
        crystalType = crystal;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type slot) {
        return MAX_DAMAGE_ARRAY[slot.getSlot().getIndex()] * 37;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type slot) {
        return ARMOR_ARRAY[slot.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return 25;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.of(OuterEndItems.ROSE_CRYSTAL_SHARD.get(),OuterEndItems.MINT_CRYSTAL_SHARD.get(), OuterEndItems.COBALT_CRYSTAL_SHARD.get());
    }

    @Override
    public String getName() {
        return "outer_end:" + crystalType;
    }


    @Override
    public float getToughness() {
        return 3.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.1F;
    }
}