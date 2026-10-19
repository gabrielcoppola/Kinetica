package com.kinetica.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import com.kinetica.KineticaMod;

/**
 * Abas criativas do Kinetica.
 *
 * <p>A aba principal é populada automaticamente a partir de {@link KineticaItems#ITEMS},
 * de modo que novos itens aparecem na aba sem edição manual. Abas temáticas
 * (ex.: Máquinas na Fase 3, Ferramentas/Armaduras na Fase 5, Reator na Fase 6)
 * devem ser adicionadas aqui com {@code displayItems} próprio.
 *
 * <p>Docs NeoForge 26.1: Items → Creative Tabs (Custom Creative Tabs), Concepts → Registries.
 * Não esquecer das traduções em {@code assets/kinetica/lang/*.json}.
 */
public final class KineticaCreativeTabs {
    private KineticaCreativeTabs() {}

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KineticaMod.MOD_ID);

    /** Aba principal do Kinetica. */
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> KINETICA =
            CREATIVE_MODE_TABS.register("kinetica", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.kinetica.kinetica"))
                    .withTabsBefore(CreativeModeTabs.SEARCH)
                    .icon(() -> new ItemStack(Items.REDSTONE))
                    .displayItems((parameters, output) -> {
                        // Popula a aba com tudo que o Kinetica registrou em KineticaItems.
                        KineticaItems.ITEMS.getEntries()
                                .forEach(entry -> output.accept(entry.get()));
                    })
                    .build());
}
