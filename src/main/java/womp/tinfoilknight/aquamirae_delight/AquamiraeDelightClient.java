package womp.tinfoilknight.aquamirae_delight;

import com.obscuria.aquamirae.client.models.ModelGoldenMoth;
import com.obscuria.aquamirae.client.particle.ShineParticle;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.client.particle.SuspendedTownParticle;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import womp.tinfoilknight.aquamirae_delight.renderers.FunctionalJarRenderer;
import womp.tinfoilknight.aquamirae_delight.renderers.GoldenMothAnimalRenderer;

@Mod.EventBusSubscriber(modid = AquamiraeDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class AquamiraeDelightClient {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(
                AquamiraeDelight.GOLDEN_MOTH.get(),
                GoldenMothAnimalRenderer::new
        );
        event.registerBlockEntityRenderer(
                AquamiraeDelight.JAR_BLOCK_ENTITY.get(),
                FunctionalJarRenderer::new
        );
    }
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(AquamiraeDelight.SHINE_HEART.get(), ShineParticle::provider);
        event.registerSpriteSet(AquamiraeDelight.SHINE_GLINT.get(), SuspendedTownParticle.HappyVillagerProvider::new);
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

