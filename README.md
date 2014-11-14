gnom-gnom-utils
===============

A mod for Minecraft that adds useful things (pronounced /ɡnom/, /ɲom/)

![Banner](/utilsBanner.png)

# Blocks
## Fabricator: Used to mass fabricate blocks/items.
  - [ ] GUI pops up.
    - [ ] 3x3 crafting grid.
    - [ ] 3 buttons for modes of operation.
      - [ ] Always active (when materials are present, run).
      - [ ] Active on redstone signal.
      - [ ] Active on pulse.
  - [ ] Pulls items from adjoining chests to craft whatever the user puts in crafting grid.
    - [ ] Pulls items from chest on the left (while facing forward).
    - [ ] Puts items into chest on the right (when facing forward).
    - [X] can be disabled in config.


## Damara's Remedy: Similar to farmland block, but supports all plants and plants grows faster when the blocks are stacked.
  - [X] semi-dificult crafting recipe.
  - [X] block emmits light no higher than whats needed to stop uprooting.
  - [X] loop through -y if top block is a growable block.
  - [X] multiplies growth speed by amount of stacked remedy blocks.
  - [X] connected textures, changes side texture if its the top block or not.
  - [X] config to change effective stack height.
  - [ ] 3 types. Manual, semi-automatic, automatic.
    - [X] Manual = have to gather and replant crops yourself.
    - [ ] Semi = redstone pulse to break and replant crops.
    - [ ] Auto = automatically breaks and replants crops when mature.
  - [ ] alternative recipe when fcraft mod is installed, recipe requires a bucket of mako.
  - [X] can be disabled in config.


## Sortivator: sorts contents of ajacent chests.
  - [X] top and bottom of block has different texture.
  - [X] connected textures.
    - [X] texture faces player when block is placed.
  - [X] when recieving redstone signal.
    - [X] sorts inventoty on side with the different texture (inventory tweaks).
    - delay between sorts to relieve load.
    - [X] gets the inventory of the nearby chest.
      [X] makes a list of all the unique items within the chest and how many of them there are.
      - [X] replaces slots from the start of the inventory with the items it had, in order of apearance.
      - [X] deletes items in slots after where the sorting finished.
    - [ ] more efficent sorting algorithm, less linear.
    - [X] can be disabled in config.
    - [ ] add crafting recipe for the block, (thinking glass+zombiebrain+ice, would mean change to tile entity with new model of a glass block with a brain in it.) til then wood+woodengear(ore dictionary)+zombiebrain.
 

## Egg Timer: explodes to notify the user that a set period of time has elapsed or a specific time of day has come.
  - [ ] GUI pops up with
    - [ ] Time of day
    - or
    - [ ] Timer
  - [ ] Explodes when either the time of day has come or time set has elapsed.
    - [ ] 2 crafting recipies, one for explosion, one for non-explosion (metadata to tell it which)
  - [X] Can be disabled in config.
 

## Lazy Man's Crafting Table (or Intelligent Crafter) - instead of a 3x3 crafting grid, it shows all items craftable with the [ ] items that the user has in their inv.
  - [ ] Gui Pops up with a list of items.
    - [ ] items depnd on materials in users inventory.
    - [ ] multiple options for crafting.
      - [ ] click crafts single item.
      - [ ] shift click to craft one stack.
      - [ ] button to toggle craft all you can with the raw materials in your inventory.
    - [ ] Supported recipe types:
      - [ ] ShapedRecipes
      - [ ] ShapelessRecipes
      - [ ] RecipesArmorDyes
      - [ ] RecipeFireworks
      - [ ] RecipeBookCloning
      - [ ] RecipesMapCloning
    - [X] Can be disabled in config.

## Multi Block of Awesome Modular Ender Storage
  - [ ] Single smart block that acts as core. Bound to player. Only breakable by player.
  - [ ] Infinite amount of modular single "dumb" blocks with nbt data that store 1 item attach to the smart core.
  - [ ] Whole inventory can be accessed through some form of ender chest/pouch.
  - [ ] Renders a random item inside the block for trolling purposes.
  - [X] Can be disabled in config.

## Anti-Cobble Block - removes cobble from inventories
  - [ ] removes cobble from your inventory as you near it/run past it.
  - [ ] redstone control to enable deletion and not?
  - [X] can be disabled in config.

# ITEMS
## Block-Finder: Used to find blocks around the player.
  - [ ] Uses Experience from the player as a Power source?
  - [ ] GUI pops up:
    - [ ] Block Id/Name/Value
      - [ ] include wildcards?
      - [ ] include multiple entries?
    - [ ] Search radius (x, y, z) from your position.
    - [ ] extends map to display an "x marks the spot" for the items your looking for?
    - [X] can be disabled in config.
  

# MOBS
## CreeperMite
  - [X] silverfish creeper with imense speed that exploeds when close without delay.
  - [X] explosion is smaller than normal creeper.
  - [X] breaking grass/leaf blocks spawns the creepermite rather than stone.
  - [X] hurting the creepermite causes more to spawn from grass or leaf blocks like mining does.
  - [X] mite makes the hurt creeper hissing noise.
  - [X] can be disabled in config.


# ENCHANTMENTS
## prosperous Auto-Smelt
  - [X] fortune pickaxe that also smelts the ore from the ore block.
  - [X] smelts wooden logs aswell.
  - [X] can be disabled in config.


# Mod Support
## adding support for pipes, chests etc for other mods if they dont handle it.
  - [ ] Buildcraft pipes for the fabricator
  - [ ] thermal expansion chests, their stronboxes can lock, what doesn this do for the sortivator, will the game crash when accessing them?
  - [ ] agricultural mods, mods that add crops. complaints can fix this i guess :)

# Concepts
## Swapable hotbars
  - Swapable hotbars that swap 9x1 row of the inventory to the hotbar on keypress.
  - Rotation? or Swapable?
  
-------------------------
CONCEPTS
-------------------------
Packethandling
http://www.minecraftforge.net/forum/index.php/topic,20135.0.html
