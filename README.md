# Kinetica

> Reconstrução moderna do **IndustrialCraft² 2.8** para **NeoForge 26.1**.

**Descrição curta (para o repositório):**
Kinetica: reconstrução do IndustrialCraft² 2.8 para NeoForge 26.1 (Minecraft). Sistema de energia EU, cabos, máquinas, ferramentas e armaduras elétricas, reator nuclear, agricultura, UU-Matter e worldgen — migrado fase a fase da API Forge legada para o NeoForge moderno.

---

## Sobre o projeto

O **Kinetica** (kinema = movimento, -ica = sufixo industrial) traz o conteúdo do
IndustrialCraft² 2.8.170-ex112 (Minecraft 1.12 / Forge, ~4.928 classes) para o NeoForge 26.1,
reimplementando o conteúdo na API atual em vez de portar o código antigo.

A migração é organizada em fases sequenciais (cada fase depende da anterior), planejadas no vault
do Obsidian em `Migracao-IC2/Índice - Migração IC2.md`, com referências cruzadas para a wiki do
IC² (`IndustrialCraft 2/2.8 (MC 1.12)/`) e a documentação do NeoForge 26.1 (`NeoForge/26.1/`).

Princípios da migração:

- **Reimplementar, não transliterar** — o IC² 1.12 usa Tile/Handlers, NBT em itens e registradores
  imperativos; o NeoForge 26.1 usa Capabilities, Data Components, Codecs e `DeferredRegister`.
- **Fase por fase**, sem pular etapas (energia antes de máquinas, materiais antes de ferramentas).
- **Paridade de jogabilidade** com o IC² 2.8, deixando espaço para expandir o conteúdo depois.

## Estado atual

| Fase | Conteúdo | Status |
|------|----------|--------|
| 0 | Fundação: entrypoints, registries, config, aba criativa | 🟡 80% |
| 1 | Sistema de energia (EU, grid, cabos, transformadores) | ⬜ |
| 2 | Materiais e recursos (minérios, dusts, plates, circuits, fluids) | ⬜ |
| 3 | Máquinas básicas (Extractor, Macerator, Compressor, Furadeira, Fermenter, Miner) | ⬜ |
| 4 | Máquinas avançadas e geradores | ⬜ |
| 5 | Ferramentas e armaduras elétricas | ⬜ |
| 6 | Reator nuclear e turbina de vapor | ⬜ |
| 7 | Agricultura e genética de crops | ⬜ |
| 8 | UU-Matter, Teraforming, máquinas rotativas e fluidos | ⬜ |
| 9 | Worldgen, JEI/REI, guide book, networking e otimização | ⬜ |

Última atualização do README: **2026-10-19** — Fase 0 (classes principais do mod concluídas,
`./gradlew build` validado).

> **Regra de manutenção:** este README é atualizado ao fim de **cada** implementação concluída
> (tabela de fases, última atualização e estrutura de pacotes).

## Stack

| Componente | Versão |
|---|---|
| Minecraft | 26.1 (`minecraft_version_range=[26.1]`) |
| NeoForge | 26.1.0.19-beta |
| Java | 25 (toolchain) |
| Build | Gradle + ModDevGradle 2.0.148 |
| Mod ID | `kinetica` |
| Pacote base | `com.kinetica` |
| Group ID | `com.kinetica` |
| Licença | All Rights Reserved |

Nomes Mojang oficiais (Mojang mappings, sem ofuscação) — ver seção de licenças abaixo.

## Estrutura de pacotes (Fase 0)

```
com.kinetica
├── KineticaMod            entrypoint comum: @Mod("kinetica"), registra os DeferredRegisters,
│                          handlers de lifecycle e o ModConfigSpec
├── KineticaModClient      entrypoint de cliente físico: @Mod(value=..., dist=Dist.CLIENT),
│                          FMLClientSetupEvent e tela de config no menu Mods
├── KineticaGameEvents     eventos do jogo (NeoForge.EVENT_BUS) via @EventBusSubscriber
├── Config                 ModConfigSpec COMMON: debugLogging, bannerOnStartup, disabledFeatures
└── registry
    ├── KineticaBlocks        DeferredRegister.Blocks (namespace "kinetica")
    ├── KineticaItems         DeferredRegister.Items
    └── KineticaCreativeTabs  DeferredRegister<CreativeModeTab> — aba "kinetica",
                              populada a partir de KineticaItems.ITEMS
```

Recursos: `src/main/resources/assets/kinetica/lang/{en_us,pt_br}.json`
(títulos da aba criativa e da tela de config). Metadata: `src/main/templates/META-INF/neoforge.mods.toml`
(expandida a partir de `gradle.properties`).

Convenções de código:

- Subpacotes agrupados por função (`registry`, `client`, `data`, `server`, futuro `api`/`core`).
- Sufixo de tipo no nome da classe (`XxxBlock`, `XxxItem`, `XxxMenu`, `XxxBlockEntity`).
- Código de cliente isolado em entrypoint/subpacote `client` (servidor dedicado não carrega
  `net.minecraft.client`).
- Verificar `Level#isClientSide()` antes de lógica de jogo; `Dist` para código de cliente físico.
- Um único método por tarefa (ex.: sempre `DeferredRegister`, nunca `RegisterEvent` junto).

## Comandos

```bash
./gradlew build            # gera o JAR em build/libs/kinetica-<versão>.jar
./gradlew runClient        # cliente de desenvolvimento
./gradlew runServer        # servidor dedicado de desenvolvimento (requer run/eula.txt)
./gradlew runData          # data generation (assets/data packs)
./gradlew publish          # publica no maven local ./repo
gradlew --refresh-dependencies   # força refresh do cache, se faltar biblioteca
```

## Configuração

Arquivo gerado em `config/kinetica-common.toml`:

| Chave | Padrão | Descrição |
|---|---|---|
| `debugLogging` | `false` | Logs detalhados do Kinetica |
| `bannerOnStartup` | `true` | Banner com nome + versão ao iniciar o mundo |
| `disabledFeatures` | `[]` | Desativa conteúdo por fase (`energia`, `materiais`, `maquinas`, `ferramentas`, `reator`, `agricultura`, `uu_matter`, `worldgen`) |

Chaves de tradução da tela de config ficam em `assets/kinetica/lang/*.json`.

## Referências de desenvolvimento

- Planejamento e checklist por fase: vault Obsidian `Migracao-IC2/Índice - Migração IC2.md`
- Wiki do IC² 2.8 (MC 1.12): `IndustrialCraft 2/2.8 (MC 1.12)/`
- Documentação NeoForge 26.1: `NeoForge/26.1/` (português, extraída de [docs.neoforged.net](https://docs.neoforged.net/), MIT)
- Migração de API vanilla 1.21.11 → 26.1: `NeoForge/26.1/16 Porting Primer/Minecraft 1.21.11 → 26.1 Mod Migration Primer.md` (CC BY 4.0)
- Changelog do NeoForge 26.1: https://neoforged.net/news/26.1release/

## Licenças

- Nomes de métodos e campos do Minecraft usam os **Mojang mappings**, cobertos por licença própria:
  https://github.com/NeoForged/NeoForm/blob/main/Mojang.md
- O template do MDK é disponibilizado sob a licença de `TEMPLATE_LICENSE.txt`.
- O IndustrialCraft² é projeto da sua respectiva equipe; este projeto é uma reimplementação
  independente, sem incluir código ou assets originais do IC².

## Comunidade NeoForge

- Docs: https://docs.neoforged.net/
- Discord: https://discord.neoforged.net/
