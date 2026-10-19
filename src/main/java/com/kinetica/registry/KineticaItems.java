package com.kinetica.registry;

import net.neoforged.neoforge.registries.DeferredRegister;

import com.kinetica.KineticaMod;

/**
 * Registro de itens do Kinetica.
 *
 * <p>Centraliza o {@link DeferredRegister.Items} da namespace {@code kinetica}.
 * Itens de bloco devem ser criados junto do bloco correspondente usando
 * {@code ITEMS.registerSimpleBlockItem(...)}.
 *
 * <p>Docs NeoForge 26.1: Concepts → Registries, Items → Items ({@code DeferredRegister.Items}).
 * Energia em itens (Fase 5) usa Data Components, não NBT.
 */
public final class KineticaItems {
    private KineticaItems() {}

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(KineticaMod.MOD_ID);

    // ---- Templates (descomente ao criar conteúdo) ----
    //
    // public static final DeferredItem<Item> COPPER_INGOT = ITEMS.registerSimpleItem(
    //         "copper_ingot", p -> p.stacksTo(64));
    //
    // public static final DeferredItem<Item> BATTERY_RE = ITEMS.registerSimpleItem(
    //         "battery_re", p -> p.stacksTo(1));
}
