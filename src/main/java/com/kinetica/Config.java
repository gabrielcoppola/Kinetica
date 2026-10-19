package com.kinetica;

import java.util.List;

import net.neoforged.neoforge.common.ModConfigSpec;

/**
 * Configuração comum do Kinetica ({@code ModConfig.Type.COMMON}).
 *
 * <p>Arquivo gerado em {@code config/kinetica-common.toml}. Traduções das chaves
 * para a tela de config ficam em {@code assets/kinetica/lang/*.json}.
 *
 * <p>Docs NeoForge 26.1: Miscellaneous → Configuration.
 */
public final class Config {
    private Config() {}

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue DEBUG_LOGGING = BUILDER
            .comment("Registra logs detalhados do Kinetica (debug). Desligado em produção.")
            .define("debugLogging", false);

    public static final ModConfigSpec.BooleanValue BANNER_ON_STARTUP = BUILDER
            .comment("Escreve o banner do Kinetica (nome + versão) ao iniciar o mundo/servidor.")
            .define("bannerOnStartup", true);

    public static final ModConfigSpec.ConfigValue<List<? extends String>> DISABLED_FEATURES = BUILDER
            .comment("Desativa conteúdo do Kinetica por fase. Valores válidos: energia, materiais, maquinas, ferramentas, reator, agricultura, uu_matter, worldgen. Lista vazia = tudo ativo.")
            .defineListAllowEmpty("disabledFeatures", List.of(), () -> "", Config::isValidFeature);

    // ---- Fase 1 (Sistema de Energia): descomentar quando a API de energia existir ----
    //
    // public static final ModConfigSpec.IntValue EU_PER_TICK_LV = BUILDER
    //         .comment("Limite de EU/t dos cabos LV.")
    //         .defineInRange("euPerTickLV", 32, 1, Integer.MAX_VALUE);
    //
    // public static final ModConfigSpec.IntValue GLOBAL_GRID_RANGE = BUILDER
    //         .comment("Alcance do grid energético global.")
    //         .defineInRange("globalGridRange", 16, 1, 1024);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean isValidFeature(final Object obj) {
        return obj instanceof String feature && !feature.isBlank();
    }
}
