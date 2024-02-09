package blueduck.outer_end.item;

import blueduck.outer_end.entity.CrystalArrow;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class CrystalArrowItem extends ArrowItem {
    Supplier<EntityType> arrowType;
    public CrystalArrowItem(Item.Properties p_43235_, Supplier<EntityType> type) {
        super(p_43235_);
        arrowType = type;
    }

    public AbstractArrow createArrow(Level p_43237_, ItemStack p_43238_, LivingEntity p_43239_) {
        return new CrystalArrow(arrowType.get(), p_43237_, p_43239_, p_43238_);
    }
}