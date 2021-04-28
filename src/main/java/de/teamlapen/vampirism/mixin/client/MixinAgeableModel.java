package de.teamlapen.vampirism.mixin.client;

import de.teamlapen.vampirism.client.render.RenderHandler;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AgeableModel.class)
public abstract class MixinAgeableModel<T extends LivingEntity> extends EntityModel<T> {
    @ModifyVariable(method = "render(Lcom/mojang/blaze3d/matrix/MatrixStack;Lcom/mojang/blaze3d/vertex/IVertexBuilder;IIFFFF)V", at = @At("HEAD"), ordinal = 3)
    private float ds(float s) {
        return RenderHandler.renderPlayerTransparent ? s * RenderHandler.playerTransparency : s;
    }
}