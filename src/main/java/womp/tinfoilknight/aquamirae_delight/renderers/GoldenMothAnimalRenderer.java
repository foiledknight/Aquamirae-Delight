package womp.tinfoilknight.aquamirae_delight.renderers;

import com.obscuria.aquamirae.client.AquamiraeLayers;
import com.obscuria.aquamirae.client.models.ModelGoldenMoth;
import com.obscuria.aquamirae.common.entities.GoldenMoth;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.AquamiraeDelightClient;
import womp.tinfoilknight.aquamirae_delight.entities.GoldenMothAnimal;
import womp.tinfoilknight.aquamirae_delight.models.GoldenMothAnimalModel;

public class GoldenMothAnimalRenderer extends MobRenderer<GoldenMothAnimal, GoldenMothAnimalModel<GoldenMothAnimal>> {
    public GoldenMothAnimalRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldenMothAnimalModel<>(context.bakeLayer(AquamiraeDelightClient.GOLDEN_MOTH_LAYERS)), 0.2F);
        this.addLayer(new EyesLayer<GoldenMothAnimal, GoldenMothAnimalModel<GoldenMothAnimal>>(this) {
            public @NotNull RenderType renderType() {
                return RenderType.eyes(ResourceLocation.fromNamespaceAndPath("aquamirae", "textures/entity/golden_moth_overlay.png"));
            }
        });
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull GoldenMothAnimal entity) {
        return ResourceLocation.fromNamespaceAndPath("aquamirae", "textures/entity/golden_moth.png");
    }
}
