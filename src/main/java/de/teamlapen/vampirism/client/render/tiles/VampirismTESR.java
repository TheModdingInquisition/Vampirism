package de.teamlapen.vampirism.client.render.tiles;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

/**
 * TESR with a few util methods
 */
@OnlyIn(Dist.CLIENT)
abstract class VampirismTESR<T extends TileEntity> extends TileEntityRenderer<T> {


    public VampirismTESR(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    /**
     * Rotates the block to fit the enum facing.
     * ONLY CALL THIS IF THE BLOCK HAS A {@link HorizontalBlock#HORIZONTAL_FACING} PROPERTY
     *
     * @param tile
     */
    protected void adjustRotatePivotViaState(@Nullable TileEntity tile, MatrixStack matrixStack) {
        if (tile == null) return;
        Direction dir = Direction.NORTH;
        if (tile.getLevel() != null)
            dir = tile.getLevel().getBlockState(tile.getBlockPos()).getValue(HorizontalBlock.FACING);
        matrixStack.mulPose(Vector3f.YP.rotationDegrees((dir.get2DDataValue() - 2) * -90));
    }
}
