# OpenCityBloxx

**OpenCityBloxx** is an educational reverse-engineering and source reconstruction of the classic Nokia / Digital Chocolate mobile game **City Bloxx** (J2ME / MIDP 2.0 / M3G).

The purpose of this project is software preservation, study of mid-2000s mobile game architectures, and understanding the mathematics and graphics rendering behind early mobile physics and stacking games.

## Preview

![ss](preview/1.png)
![ss](preview/2.png)
![ss](preview/3.png)
![ss](preview/4.png)
![ss](preview/5.png)
![ss](preview/6.png)
![ss](preview/7.png)

---

## Legal & Educational Disclaimer

> [!IMPORTANT]
> **This project is created strictly for non-commercial, educational, historical preservation, and research purposes.**
>
> - **City Bloxx**, **Tower Bloxx**, and all related original trademarks, artwork, sound assets, and game concepts are the intellectual property of **Digital Chocolate** and/or **Nokia**.
> - This repository contains a clean-room style reverse-engineering and decompilation reconstruction created to analyze legacy J2ME algorithms, physics models, and file formats for interoperability and preservation.
> - This project is not affiliated with, endorsed by, or sponsored by Nokia, Digital Chocolate, or any of their successors or affiliates.
> - No copyright infringement is intended. If you are a rights holder and have concerns regarding any material contained in this repository, please contact the maintainers.

---

## Features & Architecture

The codebase has been decompiled, cleaned, and de-obfuscated into readable, maintainable Java classes running on modern desktop JDKs (JDK 11+) via lightweight compatibility wrappers:

- **City Building Mode (`CityMode`)**: Full 5x5 city grid logic, neighborhood synergy scoring, population thresholds, unlocks, and placement impact/smoke animations.
- **Tower Stacking Mode (`House`)**: Crane pendulum and rope physics, wind simulation, block collision detection, combo streaks, citizen parachuting, and roof placement.
- **3D Graphics Subsystem (`Renderer3D`, `Mesh3D`)**: JSR-184 / M3G software emulation layer and camera projections.
- **Audio & Effects (`SoundPlayer`, `Vibra`)**: MIDI audio playback and haptic vibration triggers.
- **Save System & Persistence (`Storage`)**: Record Management System (RMS) persistence for settings, city progress, and high score tables (`HoF`).
- **UI Engine (`Ui`, `MenuController`, `HighScores`)**: In-game HUD, paginated dialogs, softkeys, text wrapping, and leaderboard input.

### Class Mapping Overview

| Original Obfuscated | Reconstructed Class | Description & Responsibilities |
|---|---|---|
| `House` | `House` | Core game loop: tower stacking, crane/rope physics, weather, camera tracking |
| `GameMIDlet` | `GameMIDlet` | MIDlet lifecycle, smooth frame dt loop, global key routing |
| `k` | `CityMode` | City grid mode (5x5 plots, population thresholds, synergies, placement VFX) |
| `a` | `ModalScreen` | Modal screen marker interface |
| `e` | `Screen` | Screen interface (update, paint, key events, transitions) |
| `m` | `ICanvas` | Canvas interface abstraction |
| `p` | `GameCanvas` | Platform canvas implementation |
| `b` | `Vibra` | Haptic vibration abstraction |
| `c` | `PlatformFactory` | Factory for canvas, sound, vibra, and storage components |
| `d` | `Mesh3D` | 3D mesh wrapper |
| `n` | `Renderer3D` | 3D scene rendering, viewport clipping, and matrix projections |
| `o` | `SoundPlayer` | MIDI music player and sound effects |
| `f` | `Storage` | RMS record store persistence (`settings`, `citymode`, `HoF`) |
| `g` | `Resources` | Archive reader (`r0`) and multilingual string table lookup |
| `h` | `HighScores` | Leaderboards, high score formatting, and player name entry |
| `i` | `Ui` | In-game UI, dialogs, softkey controls, and text layout |
| `j` | `MenuController` | Menu definitions loader (`m`), settings, and navigation controller |
| `com.nokia.mid.appl.bloxx.a` | `Lang` | Localization parser and platform validation |

---

## Building and Running

### Prerequisites
- **JDK 11** or newer installed and available on your system `PATH`.

### Build
Run the provided build script:
```cmd
build.bat
```
This compiles the source code with UTF-8 encoding and packages `CityBloxx.jar`.

### Run
Launch the game directly:
```cmd
run.bat
```

### Controls
| Key | Action |
|---|---|
| **Arrow Keys** / **W, A, S, D** | Directional navigation / Move cursor |
| **Enter** / **Space** / **5** | Drop block / Select / Place building |
| **Z** / **F1** | Left Soft Key (Menu / Select / Confirm) |
| **X** / **Esc** / **F2** | Right Soft Key (Back / Exit / Pause) |
| **0 - 9** | Numeric input / Cheats |

### Easter Eggs & Cheats
- In City Mode, enter the classic keypad sequence **`626428826`** on the number keys to unlock all building types and max city level (note: saves are disabled while cheat mode is active to protect progress integrity).

---

## Directory Structure
- `src/` - Reconstructed Java sources and standard compatibility stubs (`jme/`).
- `res/` - Original JAR game assets, MIDI sound files, and language localization packs.
- `docs/` - In-depth technical documentation (`RESOURCES.md`, `STRING_IDS.md`, `VERIFICATION.md`).
- `build/` - Build configurations and metadata.
