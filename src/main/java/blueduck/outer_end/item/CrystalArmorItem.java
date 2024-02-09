package blueduck.outer_end.item;

import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CrystalArmorItem extends ArmorItem {
    public CrystalArmorItem(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (hasCrystalSet(player)) {
            if (hasRoseSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20, 0, true, true));
            }
            if (hasMintSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 0, true, true));
                player.addEffect(new MobEffectInstance(MobEffects.JUMP, 20, 0, true, true));
            }
            if (hasCobaltSet(player)) {
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 0, true, true));
            }
            if (level.dimension().location().getPath().equals("the_end") && player.getY() < 1) {
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 4, true, true));
                if (player.getDeltaMovement().y < 0) {
                    player.setDeltaMovement(player.getDeltaMovement().x, player.getDeltaMovement().y * -1, player.getDeltaMovement().z);
                }
            }
        }
    }

    public boolean hasCrystalSet(Player player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof CrystalArmorItem && player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof CrystalArmorItem && player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof CrystalArmorItem && player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof CrystalArmorItem);
    }
    public boolean hasRoseSet(Player player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).is(OuterEndItems.ROSE_CRYSTAL_HELMET.get()) && player.getItemBySlot(EquipmentSlot.CHEST).is(OuterEndItems.ROSE_CRYSTAL_CHESTPLATE.get()) && player.getItemBySlot(EquipmentSlot.LEGS).is(OuterEndItems.ROSE_CRYSTAL_LEGGINGS.get()) && player.getItemBySlot(EquipmentSlot.FEET).is(OuterEndItems.ROSE_CRYSTAL_BOOTS.get()));
    }
    public boolean hasMintSet(Player player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).is(OuterEndItems.MINT_CRYSTAL_HELMET.get()) && player.getItemBySlot(EquipmentSlot.CHEST).is(OuterEndItems.MINT_CRYSTAL_CHESTPLATE.get()) && player.getItemBySlot(EquipmentSlot.LEGS).is(OuterEndItems.MINT_CRYSTAL_LEGGINGS.get()) && player.getItemBySlot(EquipmentSlot.FEET).is(OuterEndItems.MINT_CRYSTAL_BOOTS.get()));
    }
    public boolean hasCobaltSet(Player player) {
        return (player.getItemBySlot(EquipmentSlot.HEAD).is(OuterEndItems.COBALT_CRYSTAL_HELMET.get()) && player.getItemBySlot(EquipmentSlot.CHEST).is(OuterEndItems.COBALT_CRYSTAL_CHESTPLATE.get()) && player.getItemBySlot(EquipmentSlot.LEGS).is(OuterEndItems.COBALT_CRYSTAL_LEGGINGS.get()) && player.getItemBySlot(EquipmentSlot.FEET).is(OuterEndItems.COBALT_CRYSTAL_BOOTS.get()));
    }
}
