package de.teamlapen.vampirism.mixin;

import de.teamlapen.vampirism.player.hunter.HunterPlayer;
import de.teamlapen.vampirism.util.Helper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity extends LivingEntity {

    public MixinPlayerEntity(EntityType<? extends LivingEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public boolean isDiscrete() {
        if (super.isDiscrete()) return true;
        PlayerEntity player = (PlayerEntity) (Object)this;
        return Helper.isHunter(player) && HunterPlayer.getOpt(player).map(p ->p.getSpecialAttributes().isDisguised()).orElse(false);
    }
}
