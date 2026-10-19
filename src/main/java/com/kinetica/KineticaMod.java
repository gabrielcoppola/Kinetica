package com.kinetica;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;

import com.kinetica.registry.KineticaBlocks;
import com.kinetica.registry.KineticaCreativeTabs;
import com.kinetica.registry.KineticaItems;

/**
 * Classe principal do mod Kinetica (entrypoint comum, carregado em ambos os lados).
 *
 * <p>Responsabilidades (Fase 0 — Fundação do projeto):
 * <ul>
 *   <li>Registrar os {@code DeferredRegister}s no barramento do mod (blocos, itens, abas criativas).</li>
 *   <li>Registrar os handlers de ciclo de vida ({@link FMLCommonSetupEvent}, {@link FMLLoadCompleteEvent}).</li>
 *   <li>Registrar o {@code ModConfigSpec} do mod ({@link Config}).</li>
 * </ul>
 *
 * <p>Eventos do jogo (barramento {@code NeoForge.EVENT_BUS}) ficam em {@link KineticaGameEvents};
 * código exclusivo de cliente fica em {@link KineticaModClient}.
 *
 * <p>Docs NeoForge 26.1: Getting Started → Mod Files (entrypoints), Concepts → Events
 * (barramentos e lifecycle), Concepts → Registries (DeferredRegister), Concepts → Sides.
 */
@Mod(KineticaMod.MOD_ID)
public class KineticaMod {
    /** Deve corresponder exatamente a {@code mod_id} em {@code gradle.properties}. */
    public static final String MOD_ID = "kinetica";

    public static final Logger LOGGER = LogUtils.getLogger();

    /** Preenchida no construtor a partir do {@code ModContainer} (ambos os lados). */
    public static String MOD_VERSION = "0.0.0";

    public KineticaMod(IEventBus modEventBus, ModContainer modContainer) {
        MOD_VERSION = modContainer.getModInfo().getVersion().toString();

        // ---- Conteúdo (registries) ----
        KineticaBlocks.BLOCKS.register(modEventBus);
        KineticaItems.ITEMS.register(modEventBus);
        KineticaCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);

        // ---- Ciclo de vida (mod bus) ----
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(this::onLoadComplete);

        // ---- Configuração ----
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    /**
     * Setup comum a ambos os lados. Eventos de lifecycle são disparados em paralelo:
     * use {@code event.enqueueWork(Runnable)} para trabalho que exija a thread principal.
     */
    private void onCommonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("[{}] common setup — Kinetica v{} carregado (blocos={}, itens={}, abas={})",
                MOD_ID,
                MOD_VERSION,
                KineticaBlocks.BLOCKS.getEntries().size(),
                KineticaItems.ITEMS.getEntries().size(),
                KineticaCreativeTabs.CREATIVE_MODE_TABS.getEntries().size());

        if (Config.DEBUG_LOGGING.get()) {
            LOGGER.info("[{}] debug logging ativo", MOD_ID);
        }
    }

    private void onLoadComplete(FMLLoadCompleteEvent event) {
        LOGGER.info("[{}] load complete — fundação (Fase 0) concluída", MOD_ID);
    }

    /** Versão do mod, conforme {@code neoforge.mods.toml} / {@code gradle.properties}. */
    public String version() {
        return MOD_VERSION;
    }
}
