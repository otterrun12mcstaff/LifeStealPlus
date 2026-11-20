# LifeStealPlus

A client-side Minecraft Java **1.21.11** Fabric mod that adds a tools menu.

Press `-` (the `_` key) in game to open the menu, then click a module to switch it on or off.
The key can be changed in Options → Controls → Miscellaneous.

## Modules

| Module | What it does |
| --- | --- |
| Miner | Breaks the block you are looking at. |
| Tunnel 3x3 | Breaks a 3×3 face in front of you. |
| Tunnel 1x2 | Player-height tunnel. |
| Shaft | Digs straight down. |
| Staircase | Digs a descending stair. |
| Vein Miner | Clears connected blocks of the same type. |
| Tree Feller | Fells the whole tree you look at. |
| Auto Farm | Harvests mature crops nearby. |
| Nuker | Clears the blocks around you. |
| Torch Placer | Places torches in dark spots. |

## Build

```
gradle build
```

The mod jar lands in `build/libs/lifestealplus-1.0.0.jar`.

Requires Fabric Loader 0.20.1+ and Fabric API for 1.21.11. Versions live in `gradle.properties`.
