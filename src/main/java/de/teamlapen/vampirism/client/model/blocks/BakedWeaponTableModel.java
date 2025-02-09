package de.teamlapen.vampirism.client.model.blocks;

import de.teamlapen.vampirism.blocks.WeaponTableBlock;
import de.teamlapen.vampirism.client.core.ClientEventHandler;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Extends the basic weapon table model, by a variable lava fluid level
 */
@OnlyIn(Dist.CLIENT)
public class BakedWeaponTableModel implements IBakedModel {

    public static final int FLUID_LEVELS = 5;

    /**
     * Stores a fluid level -> fluid model array
     * Filled when the fluid json model is loaded (in {@link ClientEventHandler#onModelBakeEvent(ModelBakeEvent)} )}
     */
    public static final IBakedModel[][] FLUID_MODELS = new IBakedModel[FLUID_LEVELS][4];

    private final IBakedModel baseModel;

    public BakedWeaponTableModel(IBakedModel baseModel) {
        this.baseModel = baseModel;
    }

    @Nonnull
    @Override
    public TextureAtlasSprite getParticleIcon() {
        return baseModel.getParticleIcon();
    }

    @Nonnull
    @Override
    public ItemOverrideList getOverrides() {
        return baseModel.getOverrides();
    }

    @Nonnull
    @Override
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @Nonnull Random rand) {
        List<BakedQuad> quads = new LinkedList<>(baseModel.getQuads(state, side, rand));
        int fluidLevel = state == null ? 0 : state.getValue(WeaponTableBlock.LAVA);

        if (fluidLevel > 0 && fluidLevel <= FLUID_LEVELS) {
            quads.addAll(FLUID_MODELS[fluidLevel - 1][state.getValue(WeaponTableBlock.FACING).get2DDataValue()].getQuads(state, side, rand));
        }

        return quads;
    }

    @Nonnull
    @Override
    public ItemCameraTransforms getTransforms() {
        return baseModel.getTransforms();
    }

    @Override
    public boolean isCustomRenderer() {
        return baseModel.isCustomRenderer();
    }

    @Override
    public boolean useAmbientOcclusion() {
        return baseModel.useAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return baseModel.isGui3d();
    }

    @Override
    public boolean usesBlockLight() {
        return baseModel.usesBlockLight();
    }
}
