package de.teamlapen.vampirism.mixin.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.teamlapen.vampirism.client.render.RenderHandler;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.IWorldReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EntityRendererManager.class)
public abstract class MixinEntityRendererManager {

    @Shadow
    private static void renderShadow(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, Entity entityIn, float weightIn, float partialTicks, IWorldReader worldIn, float sizeIn) {
    }

    @Redirect(method = "renderEntityStatic(Lnet/minecraft/entity/Entity;DDDFFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/EntityRendererManager;renderShadow(Lcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;Lnet/minecraft/entity/Entity;FFLnet/minecraft/world/IWorldReader;F)V"))
    private void aaa(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, Entity entityIn, float weightIn, float partialTicks, IWorldReader worldIn, float sizeIn) {
        if (RenderHandler.renderPlayerTransparent) {
            weightIn *= RenderHandler.playerTransparency;
        }
        if (weightIn > 0) {
            renderShadow(matrixStackIn, bufferIn, entityIn, weightIn, partialTicks, worldIn, sizeIn);
        }
    }
}
