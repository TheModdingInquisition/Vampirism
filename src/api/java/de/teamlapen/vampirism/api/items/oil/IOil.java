package de.teamlapen.vampirism.api.items.oil;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.List;

public interface IOil extends IForgeRegistryEntry<IOil> {

    /**
     * whether the entity should be effected by the oil
     */
    boolean canEffect(ItemStack stack, LivingEntity entity);

    /**
     * calculates the bonus damage for the entity
     */
    float getAdditionalDamage(ItemStack stack, LivingEntity entity, float damage);

    String getName(String item);

    int getMaxDuration(ItemStack stack);

    void getDescription(ItemStack stack, List<ITextComponent> tooltips);

    int getColor();
}
