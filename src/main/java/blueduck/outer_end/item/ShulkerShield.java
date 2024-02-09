package blueduck.outer_end.item;

import blueduck.outer_end.registry.OuterEndItems;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.Level;

public class ShulkerShield extends ShieldItem {
    public ShulkerShield(Properties p_43089_) {
        super(p_43089_);
    }

    public boolean isValidRepairItem(ItemStack p_43091_, ItemStack p_43092_) {
        return p_43092_.is(Items.SHULKER_SHELL) || p_43092_.is(OuterEndItems.SINKER_TOOTH.get());
    }

    public void onUseTick(Level p_41428_, LivingEntity p_41429_, ItemStack p_41430_, int p_41431_) {
        p_41429_.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 60, 2));

    }
}
