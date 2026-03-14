package womp.tinfoilknight.aquamirae_delight;

import com.obscuria.aquamirae.client.models.ModelGoldenMoth;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.particle.ShineHeartParticle;
import womp.tinfoilknight.aquamirae_delight.renderers.GoldenMothAnimalRenderer;

@Mod.EventBusSubscriber(modid = AquamiraeDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AquamiraeDelightClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                AquamiraeDelight.GOLDEN_MOTH.get(),
                GoldenMothAnimalRenderer::new
        );
    }
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(AquamiraeDelight.SHINE_HEART.get(), ShineHeartParticle::provider);
    }
    public static final ModelLayerLocation GOLDEN_MOTH_LAYERS = registerModelLayer("golden_moth");
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.@NotNull RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GOLDEN_MOTH_LAYERS, ModelGoldenMoth::createBodyLayer);
    }


    @Contract("_ -> new")
    private static @NotNull ModelLayerLocation registerModelLayer(String name) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(AquamiraeDelight.MODID, name), "main");
    }
}

