package de.teamlapen.vampirism.mixin.client;

import de.teamlapen.vampirism.client.render.RenderHandler;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderType.class)
public abstract class MixinRenderType extends RenderState {

    public MixinRenderType(String nameIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, setupTaskIn, clearTaskIn);
    }

    @Redirect(method = "Lnet/minecraft/client/renderer/RenderType;getArmorCutoutNoCull(Lnet/minecraft/util/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;" , at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType$State$Builder;build(Z)Lnet/minecraft/client/renderer/RenderType$State;"))
    private static RenderType.State sds(RenderType.State.Builder builder, boolean outlineIn){
        if (RenderHandler.renderPlayerTransparent) {
            builder.transparency(TRANSLUCENT_TRANSPARENCY);
        }
        return builder.build(outlineIn);
    }
}
