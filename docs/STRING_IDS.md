# String ids -> language table (lang.en-US)

`Resources.getString(id)` maps a game string id to a language-table index (`Resources.lookupString`, recovered from bytecode),
then `Lang.getString(index, args)` reads that entry from `lang.<locale>` and substitutes `%U` / `%0U`,`%1U`... .

Kinds: plain = fixed text; pass = caller args substituted; keys = defaults to soft-key/5-key names when no args; pair = key name + args[0].

| string id | lang index | kind | English text |
|---|---|---|---|
| 0 | 30 | plain | City Bloxx |
| 2 | 1 | keys | Build the highest and most stable building possible.\n\nAt the bottom you can see the current height of the building, the number of tries you have left, and the current population of the building.\n\nPress key %U to drop the block. The higher your building, the more people you get for each placed block. The number of people also depends on how well the block is placed.\n\nCentering a block perfectly on top of another fills the combo meter. Placing more blocks before the meter empties adds to the combo, and perfect drops refill the meter. Every block placed while a combo is active increases the combo score. The combo score is added to the population score when the combo ends. |
| 3 | 0 | keys | Your goal is to create a Megalopolis. To reach that goal, build towers and place them wisely on the city map. Increasing your city's population and level unlocks new building types. You can see the current city level and population at the top of the screen. In the top right corner is the population of the tower you have just built and the tower it's going to replace.\n\nWhen you are building a tower, your aim is to reach a target height. The target height is shown in the lower left corner. The better you place the roof, the bigger your bonus. You have 3 chances to finish the tower, if you fail, the building can be placed in the city without the roof.\n\nAfter finishing a tower, you can choose a location on one of the flashing tiles on the city map and place the tower with key %U. Old buildings can be replaced with new ones. An unwanted tower can be destroyed in the demolishing lot on the left. |
| 4 | 43 | plain | Select |
| 5 | 39 | plain | OK |
| 6 | 37 | plain | Menu |
| 7 | 31 | plain | Back |
| 8 | 32 | plain | Cancel |
| 9 | 44 | plain | Yes |
| 10 | 38 | plain | No |
| 15 | 41 | plain | Continue Build city |
| 17 | 40 | plain | New game |
| 19 | 36 | plain | Instructions |
| 22 | 34 | plain | Exit |
| 34 | 23 | pair | Build a tower with as many occupants as possible. Press key %0U to drop the apartment blocks. Only %1U misses allowed. |
| 35 | 24 | plain | You started a combo by centering a block precisely on the one below it. As long as the combo meter at the top stays active you get more people for each placed block. Precise drops refill the combo meter. |
| 36 | 4 | plain | You've bought yourself a piece of land to fulfill your dream of building a thriving metropolis. |
| 37 | 5 | plain | Your investors have supplied a crane and building materials for you to create Residential towers. |
| 38 | 7 | keys | Press key %U to select the blue Residential tower on the left. |
| 39 | 19 | keys | Place the tower anywhere on the map. Move the tower with keys %0U, %1U, %2U, %3U and place it with key %4U. |
| 40 | 18 | plain | The meter on top shows the current city population. Your main goal is to build as crowded a city as possible through the 20 milestones. Check your progress by looking at the milestone counter on the upper left corner. |
| 41 | 8 | pass | First milestone reached! More than %U citizens have settled in your city. |
| 42 | 9 | plain | The orange horizontal line above the city map shows how many people you need for the next city level. |
| 43 | 67 | pass | More than %U citizens have moved in |
| 44 | 68 | pass | Congratulations! Your %0U is now a %1U. |
| 45 | 69 | plain | Your city is now a Tiny town |
| 46 | 6 | plain | The comparison icons at the top right corner of the screen show how many people you have in your new building and the building you are about to replace. |
| 47 | 14 | keys | Tax money flows in and you can now afford a new tower type! Use keys %0U and %1U to select between blue Residential towers and red Commercial towers. |
| 48 | 15 | plain | Buildings have specific rules as to where they can be placed. Red towers may only be placed next to blue towers. The flashing tiles show where they can be placed. |
| 49 | 16 | plain | You can now afford green Office towers! A green tower needs to have a red and a blue neighbor. |
| 50 | 17 | plain | Congratulations! You can now afford the tallest building. The yellow Luxury towers require a red, blue and green neighbor before they can be placed. |
| 51 | 20 | pass | Special roofs for %U are available. You get the special roof if you build the tower well enough. |
| 52 | 13 | plain | Congratulations! Your city has reached the final level due to your amazing construction and management skills. Thank you for playing! |
| 53 | 66 | pass | All sectors in your %U are full. Citizens arrange a parade for you. |
| 54 | 3 | pass | Now it's time to do some serious thinking and reach for the ultimate goal: to create a city of %U people. |
| 55 | 21 | pair | Press key %0U to drop the apartments. You're allowed %1U misses before the construction is canceled. The better you build, the more people will move in. Good luck! |
| 56 | 70 | plain | You ran out of retries before you reached the target height |
| 57 | 22 | plain | Excellent! You've built with such skill that this tower is awarded a trophy roof. A trophy roof gives a population bonus for the tower it covers. |
| 58 | 10 | pass | More than %U citizens have moved in! Your city has received a lot of recognition in the national news. |
| 59 | 11 | pass | More than %U citizens have settled in! Your city has been voted as the greatest place to live in a national poll. |
| 60 | 12 | plain | The people of your city have built a statue in your honor. Congratulations! |
| 62 | 54 | pass | %0U\nChallenge: population of %1U |
| 63 | 55 | pass | Get more than %U citizens to unlock this tower type |
| 64 | 50 | keys | Press key %U to demolish the current building |
| 65 | 51 | keys | Press key %U to place the tower here |
| 66 | 52 | plain | You cannot place the tower here |
| 67 | 59 | pass | Population increased by %U citizens |
| 68 | 60 | plain | The new building had no effect on overall population |
| 69 | 58 | pass | Population decreased by %U citizens |
| 70 | 56 | plain | No place to build this type of tower |
| 71 | 71 | plain | Tiny town |
| 72 | 72 | plain | Small town |
| 73 | 73 | plain | Town |
| 74 | 74 | plain | Small city |
| 75 | 75 | plain | Medium city |
| 76 | 76 | plain | Big city |
| 77 | 77 | plain | Capital |
| 78 | 78 | plain | Metropolis |
| 79 | 79 | plain | Megalopolis |
| 80 | 45 | plain | Height |
| 81 | 28 | plain | Reset city |
| 82 | 65 | plain | Population |
| 83 | 91 | plain | Residential tower |
| 84 | 85 | plain | Commercial tower |
| 85 | 87 | plain | Office tower |
| 86 | 89 | plain | Luxury tower |
| 87 | 92 | plain | Residential towers |
| 88 | 86 | plain | Commercial towers |
| 89 | 88 | plain | Office towers |
| 90 | 90 | plain | Luxury towers |
| 91 | 64 | plain | Quick game |
| 92 | 63 | plain | Build city |
| 93 | 83 | pass | Population: %U |
| 94 | 81 | pass | Tower height: %U |
| 95 | 80 | pass | Longest combo: %U |
| 96 | 82 | plain | New record! |
| 98 | 25 | plain | Starting a new game erases your current game and resets the progress and population score in your city. Proceed? |
| 99 | 53 | plain | Can be placed anywhere |
| 100 | 61 | plain | Required neighbor:\nblue tower |
| 101 | 57 | plain | Required neighbors:\nblue and red tower |
| 102 | 62 | plain | Required neighbors:\nblue, red and green tower |
| 118 | 2 | plain | Starting a new game may end your current game. Are you sure? |
| 146 | 84 | plain | Enter name |
| 150 | 33 | plain | Clear |
| 151 | 35 | plain | High scores |
| 152 | 93 | plain | 0 |
| 153 | 42 | plain | Continue Quick game |
| 154 | 46 | plain | All-time high: |
| 155 | 47 | plain | Name |
| 156 | 48 | plain | Population |
| 157 | 49 | plain | Tower height |
| 158 | 27 | plain | Old buildings can be replaced with new ones. An unwanted tower can be destroyed in the demolishing lot on the left. |
| 159 | 26 | plain | Starting a new game erases your current game. Proceed? |
| 160 | 29 | plain | Reset Quick game |
