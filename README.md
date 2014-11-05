gnom-gnom-utils
===============

A mod for Minecraft that adds useful things (pronounced /ɡnom/, /ɲom/)

# Blocks
## Fabricator: Used to mass fabricate blocks/items.
  - GUI pops up.
    - 3x3 crafting grid.
    - 3 buttons for modes of operation
      - Always active (when materials are present, run)
      - Active on redstone signal
      - Active on pulse
  - Pulls items from adjoining chests to craft whatever the user puts in crafting grid.
    - Pulls items from chest on the left (while facing forward)
    - Puts items into chest on the right (when facing forward)

## Damara's Remedy: Similar to farmland block, but supports all plants and plants grows faster when the blocks are stacked.
  - ~~semi-dificult crafting recipe.~~
  - ~~block emmits light no higher than whats needed to stop uprooting.~~
  - ~~loop through -y if top block is a growable block.~~
  - ~~multiplies growth speed by amount of stacked remedy blocks.~~
  - 3 types. Manual, semi-automatic, automatic.
    - ~~Manual = have to gather and replant crops yourself.~~
    - Semi = redstone pulse to break and replant crops.
    - Auto = automatically breaks and replants crops when mature.
  - alternative recipe when fcraft mod is installed, recipe requires a bucket of mako


## Sortivator: sorts contents of ajacent chests.
  - one side has different texture.
    - texture faces player when block is placed.
  - when recieving redstone signal.
    - sorts inventoty on side with the different texture (inventory tweaks).
    - delay between sorts to relieve load.
    - gets the inventory of the nearby chest
      - makes a list of all the unique items within the chest and how many of them there are
      - replaces slots from the start of the inventory with the items it had, in order of apearance.
      - deletes items in slots after where the sorting finished.



# ITEMS
## Block-Finder: Used to find blocks around the player.
  - GUI pops up:
    -Block Id/Name/Value
      - include wildcards?
      - include multiple entries?
    - Search radius (x, y, z) from your position
    - Uses Experience from the player as Power
    


-------------------------
CONCEPTS
-------------------------

