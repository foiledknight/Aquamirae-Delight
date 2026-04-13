package womp.tinfoilknight.aquamirae_delight.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.blocks.FunctionalJarBlockEntity;

public class FunctionalJarRenderer implements BlockEntityRenderer<FunctionalJarBlockEntity> {
    private final BlockEntityRendererProvider.Context context;
    public FunctionalJarRenderer(BlockEntityRendererProvider.Context ctx) {
        this.context = ctx;
    }

    @Override
    public void render(@NotNull FunctionalJarBlockEntity pBlockEntity, float pPartialTick,
                       @NotNull PoseStack pPoseStack, @NotNull MultiBufferSource pBuffer, int pPackedLight,
                       int pPackedOverlay) {
        FluidStack fluidStack = pBlockEntity.getFluidTank().getFluid();
        if (fluidStack.isEmpty())
            return;

        Level level = pBlockEntity.getLevel();
        if (level == null)
            return;

        BlockPos pos = pBlockEntity.getBlockPos();

        IClientFluidTypeExtensions fluidTypeExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
        ResourceLocation stillTexture = fluidTypeExtensions.getStillTexture(fluidStack);
        if (stillTexture == null)
            return;

        FluidState state = fluidStack.getFluid().defaultFluidState();

        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(stillTexture);
        int tintColor = fluidTypeExtensions.getTintColor(state, level, pos);

        VertexConsumer builder = pBuffer.getBuffer(ItemBlockRenderTypes.getRenderLayer(state));

        float pixelsFromTop = 5f;
        float pixelsFromBottom = 0.5f;

        float jarTop = pixelsFromTop * 0.125f;
        float jarBottom = pixelsFromBottom * 0.125f;

        float height = (((float) pBlockEntity.getFluidTank().getFluidAmount() / pBlockEntity.getFluidTank().getCapacity()) * jarTop) + jarBottom;

        drawQuad(builder, pPoseStack, 0.25f, height, 0.25f, 0.75f, height, 0.75f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), pPackedLight, tintColor);

        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.XN.rotationDegrees(180));
        pPoseStack.translate(0, -0.125, -1);
        drawQuad(builder, pPoseStack, 0.25f, jarBottom, 0.25f, 0.75f, jarBottom, 0.75f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), pPackedLight, tintColor);
        pPoseStack.popPose();

        drawQuad(builder, pPoseStack, 0.25f, jarBottom, 0.25f, 0.75f, height, 0.25f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), pPackedLight, tintColor);

        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(180));
        pPoseStack.translate(-1f, 0, -1.5f);
        drawQuad(builder, pPoseStack, 0.25f, jarBottom, 0.75f, 0.75f, height, 0.75f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), pPackedLight, tintColor);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(90));
        pPoseStack.translate(-1f, 0, 0);
        drawQuad(builder, pPoseStack, 0.25f, jarBottom, 0.25f, 0.75f, height, 0.25f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), pPackedLight, tintColor);
        pPoseStack.popPose();

        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YN.rotationDegrees(90));
        pPoseStack.translate(0, 0, -1f);
        drawQuad(builder, pPoseStack, 0.25f, jarBottom, 0.25f, 0.75f, height, 0.25f, sprite.getU0(), sprite.getV0(), sprite.getU1(), sprite.getV1(), pPackedLight, tintColor);
        pPoseStack.popPose();
    }

    private static void drawVertex(VertexConsumer builder, PoseStack poseStack, float x, float y, float z, float u, float v, int packedLight, int color) {
        builder.vertex(poseStack.last().pose(), x, y, z)
                .color(color)
                .uv(u, v)
                .uv2(packedLight)
                .normal(1, 0, 0)
                .endVertex();
    }

    private static void drawQuad(VertexConsumer builder, PoseStack poseStack, float x0, float y0, float z0, float x1, float y1, float z1, float u0, float v0, float u1, float v1, int packedLight, int color) {
        drawVertex(builder, poseStack, x0, y0, z0, u0, v0, packedLight, color);
        drawVertex(builder, poseStack, x0, y1, z1, u0, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y1, z1, u1, v1, packedLight, color);
        drawVertex(builder, poseStack, x1, y0, z0, u1, v0, packedLight, color);
    }
}
