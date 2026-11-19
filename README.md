# Example Mod

A Minecraft Java Edition **1.21.11** mod built with [Fabric](https://fabricmc.net/).

## Versions

| Component | Version |
| --- | --- |
| Minecraft | 1.21.11 |
| Fabric Loader | 0.20.1 |
| Fabric API | 0.141.6+1.21.11 |
| Fabric Loom | 1.13.3 |
| Mappings | Mojang official |
| Java | 21 |
| Gradle | 9.1.0 (wrapper included) |

Check <https://fabricmc.net/develop> for current versions.

## Requirements

Only a **JDK 21** is needed — Gradle is provided by the wrapper.

## Build

```sh
./gradlew build
```

The mod jar is written to `build/libs/`. Copy it into the `mods/` folder of a
Fabric 1.21.11 profile (Fabric Loader 0.20.1+, Fabric API installed).

## Run

```sh
./gradlew runClient   # launches the game with the mod loaded
./gradlew runServer   # launches a dedicated server
```

## Project layout

```
src/main/java/com/example/examplemod/         server + common code (main entrypoint)
src/client/java/com/example/examplemod/client/ client-only code (client entrypoint)
src/main/resources/fabric.mod.json             mod metadata and entrypoints
src/main/resources/assets/examplemod/          assets (lang, textures, icon)
gradle.properties                              all versions live here
```

`splitEnvironmentSourceSets()` is enabled in `build.gradle`, so client-only code
must live under `src/client/java` — it is never loaded on a dedicated server.

## Renaming the mod

This is a template named `examplemod`. To make it yours, update:

1. `gradle.properties` — `group` (e.g. `com.yourname`)
2. `settings.gradle` — `rootProject.name`
3. `build.gradle` — the `mods { "examplemod" { ... } }` block
4. `src/main/resources/fabric.mod.json` — `id`, `name`, entrypoint class names
5. `ExampleMod.MOD_ID`
6. the package directories under `src/main/java` and `src/client/java`
7. `src/main/resources/assets/examplemod/`

## Adding mixins

No mixin config is set up yet. To add one, create `examplemod.mixins.json`
(`"package": "com.example.examplemod.mixin"`, `"compatibilityLevel": "JAVA_21"`),
reference it from `"mixins"` in `fabric.mod.json`, and add a `mixin` package with
your mixin classes.
