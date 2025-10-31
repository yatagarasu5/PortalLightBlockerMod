# PortalLightBlockerMod
## Easy EOL (Null-Light Portals)

A simple Fabric mod for Minecraft 1.21.1+ that stops Nether Portals from emitting light.

## 📖 About

This mod has one single purpose: **it sets the light emission level of Nether Portal blocks to 0.**

The vanilla Nether Portal block emits a light level of 11. This prevents hostile mobs (which require block light 0) from spawning on adjacent blocks, forcing technical players to use complex, bug-based "light suppression" mechanics for high-efficiency portal farms.

This mod removes the need for those bugs by simply turning off the portal's light source, allowing mobs to spawn on adjacent blocks naturally (as long as skylight is also 0).

## ✨ Features

* **Nether Portal Light Level:** Changed from `11` to `0`.
* **No Bugs/Exploits:** Achieves the "End of Light" (EOL) farm effect using a clean, targeted code change instead of relying on light suppression glitches.
* **Server-Side:** This mod only needs to be installed on the server for its effect. Clients do not need it (though it is safe for them to have).
* **Vanilla Compatible:** Does not add any new blocks, items, or global rules.

## 💾 Installation (For Players)

1.  Ensure you have the [Fabric Loader](https://fabricmc.net/use/installer/) installed.
2.  Download the latest `.jar` file from the [Releases page](https://github.com/Renoceros/PortalLightBlockerMod/releases).
3.  Place the `.jar` file into your `mods` folder.

That's it! There are no configs or commands.

---

## 🛠️ Building from Source (For Developers)

If you want to build the mod yourself:

### 1. Prerequisites

* **Java 21 (JDK):** This project requires Java 21 to build. We recommend [Adoptium Temurin 21 (LTS)](https://adoptium.net/temurin/releases/?version=21).
* **Git:** Used to clone the repository.

### 2. Clone & Build

From your command line, run the following commands:

```bash
# 1. Clone the repository
git clone [https://github.com/YOUR-USERNAME/YOUR-REPO.git](https://github.com/YOUR-USERNAME/YOUR-REPO.git)
cd YOUR-REPO

# 2. Build the mod using the Gradle wrapper
# (On Linux/macOS)
./gradlew build

# (On Windows)
.\gradlew.bat build