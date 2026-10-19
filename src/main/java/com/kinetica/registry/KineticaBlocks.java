package com.kinetica.registry;

import net.neoforged.neoforge.registries.DeferredRegister;

import com.kinetica.KineticaMod;

/**
 * Registro de blocos do Kinetica.
 *
 * <p>Centraliza o {@link DeferredRegister.Blocks} da namespace {@code kinetica}.
 * Novos blocos devem ser adicionados aqui (ou em sub-registros dedicados por fase,
 * ex.: {@code KineticaMachineBlocks} a partir da Fase 3) e aparecem automaticamente
 * na aba criativa do mod.
 *
 * <p>Docs NeoForge 26.1: Concepts → Registries (DeferredRegister), Blocks → Blocks
 * ({@code DeferredRegister.Blocks} helpers).
 */
public final class KineticaBlocks {
    private KineticaBlocks() {}

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(KineticaMod.MOD_ID);

    // ---- Templates (descomente ao criar conteúdo; Fase 2+ para minérios/máquinas) ----
    //
    // public static final DeferredBlock<Block> RUB_WOOD = BLOCKS.registerSimpleBlock(
    //         "rub_wood", p -> p.mapColor(MapColor.WOOD).strength(2.0f));
    //
    // public static final DeferredBlock<Block> ORE_COPPER_RAW = BLOCKS.registerSimpleBlock(
    //         "raw_copper_ore", p -> p.mapColor(MapColor.COLOR_ORANGE).strength(3.0f, 3.0f)
    //                 .requiresTool(true));
}
