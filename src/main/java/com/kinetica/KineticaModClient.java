package com.kinetica;

import net.minecraft.client.Minecraft;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

/**
 * Entrypoint exclusivo de cliente físico do Kinetica.
 *
 * <p>Nunca referencie esta classe a partir do código comum: o servidor dedicado não
 * carrega o pacote {@code net.minecraft.client} e quebraria com {@code NoClassDefFoundError}.
 * Use {@code @Mod(value = ..., dist = Dist.CLIENT)} (físico) e
 * {@code @EventBusSubscriber(value = Dist.CLIENT)} para handlers estáticos de cliente.
 *
 * <p>Docs NeoForge 26.1: Concepts → Sides, Concepts → Events (Sided Events),
 * Miscellaneous → Configuration (tela de config).
 */
@Mod(value = KineticaMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = KineticaMod.MOD_ID, value = Dist.CLIENT)
public final class KineticaModClient {
    public KineticaModClient(ModContainer container) {
        // Habilita Mods → Kinetica → Config (traduções em assets/kinetica/lang/*.json).
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        KineticaMod.LOGGER.info("[{}] client setup — Kinetica v{}, jogador: {}",
                KineticaMod.MOD_ID,
                KineticaMod.MOD_VERSION,
                Minecraft.getInstance().getUser().getName());
    }
}
