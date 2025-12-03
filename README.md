#  Elude Europa

**Elude Europa** is a **2D Action RPG Shooter** developed as part of my university project for the Object-Oriented Application Design (PAOO) course. The game is set on Jupiter's moon, Europa, where the player must fight through procedurally generated levels to escape alien captivity.

##  Story & Objective

You play as a human captured by the **Horfus** , a hostile alien race inhabiting Europa, who intend to perform experiments on you. The goal is to escape the moon using a spaceship while surviving the alien onslaught.

The journey is divided into 3 main stages:
1.  **The Escape:** Wake up in a capsule and break out of the detention facility (Level 1).
2.  **The Courtyard:** Navigate the facility's exterior to find an escape route (Level 2).
3.  **The Spaceship:** Locate a ship, breach the control room, and blast off (Level 3).

##  Gameplay Mechanics

The game features **dungeon crawler** mechanics where rooms are **randomly generated** for every run, ensuring a unique experience each time you play.

* **Progression:** To advance, you must explore rooms, find the **Key** to the final door, and defeat the **Level Boss**.
* **Health System:** You start with **3 Hearts**. If your HP drops to 0, the level resets, and you lose your current progress.
* **Combat:** A dynamic shooter system where you must dodge enemy attacks while managing your own positioning and aim.

###  Items & Upgrades
As you explore, you can find items dropped by enemies or hidden in rooms:
* **Consumables:** **Health Potions** (restore 1 heart) and **Bombs** (destroy enemies or obstacles).
* **Passive Items:** These boost your stats (Tier I - IV). Examples include *Hearth of Vitality* (Max HP), *Boots of Agility* (Speed), or *Projectile of Pain* (Damage).

## Enemies
The game features a variety of enemies with unique behaviors, like:
* **Horfus:** Standard melee enemy.
* **Fly:** Low HP but very fast.
* **Fred:** Burrows underground and pops up beneath the player.
* **Horfus Guard:** Ranged enemy that shoots projectiles.
* **Horfus Bomber:** Explodes on contact or death.

Each level concludes with a unique Boss Fight, such as **Horlock** or **Horcerrer**.

##  Controls

The game uses a classic keyboard layout for movement and combat:

| Action | Key | Description |
| :--- | :---: | :--- |
| **Move** | `W`, `A`, `S`, `D` | Move the character Up, Left, Down, Right |
| **Shoot** | `↑`, `↓`, `←`, `→` | Fire projectiles in the specified direction |
| **Interact** | `E` | Pick up items or interact with objects |
| **Bomb** | `SPACE` | Throw a bomb |
| **Menu** | `ESC` | Pause game / Open menu |

---

##  Installation & Setup

1.  Clone the repository:
    ```bash
    git clone [https://github.com/pavelstefanok/Elude-Europa-2D-Game.git](https://github.com/pavelstefanok/Elude-Europa-2D-Game.git)
    ```
2.  Open the project in your preferred IDE .
3.  Ensure the Java SDK is configured correctly.
4.  Run the `Main` class to start the game.

---
