package com.kinetica;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;

/**
 * Handlers de eventos do jogo (barramento {@code NeoForge.EVENT_BUS}).
 *
 * <p>Registra-se automaticamente via {@code @EventBusSubscriber} — não chame
 * {@code NeoForge.EVENT_BUS.register(...)} para esta classe (registraria duas vezes).
 *
 * <p>Regra de lado lógico: sempre confira {@code level#isClientSide()} antes de aplicar
 * lógica de jogo. Eventos de renderização/tela pertencem a {@link KineticaModClient}.
 *
 * <p>Docs NeoForge 26.1: Concepts → Events, Concepts → Sides.
 */
@EventBusSubscriber(modid = KineticaMod.MOD_ID)
public final class KineticaGameEvents {
    private KineticaGameEvents() {}

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        if (Config.BANNER_ON_STARTUP.get()) {
            KineticaMod.LOGGER.info("Kinetica v{} >> mundo iniciado", KineticaMod.MOD_VERSION);
        }
        if (Config.DEBUG_LOGGING.get()) {
            KineticaMod.LOGGER.info("[{}] debug: servidor iniciando (blocos={} registrados)",
                    KineticaMod.MOD_ID, com.kinetica.registry.KineticaBlocks.BLOCKS.getEntries().size());
        }
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        if (Config.DEBUG_LOGGING.get()) {
            KineticaMod.LOGGER.info("[{}] debug: servidor encerrando", KineticaMod.MOD_ID);
        }
    }
}
