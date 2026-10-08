# Naming notes

## Corrections to earlier names (evidence in brackets)
* `House.bk`: was `gameMode`, now `phase`. Values 0..4 = playing / roof / round over / result / intro; it is
  the round state. The real game mode is `House.e`, now `gameMode` (5 = city-tower, 6 = quick).
  [`e == 5` pairs with `saveTowerCityMode` and `CityMode.startPlacement`; `e == 6` with `saveTowerQuickMode`
  and the high-score submission]
* `House.cos(int)` -> `negCos`. `k(i) = sin(i - 90 deg) = -cos(i)` (the table built in `initSinTable` is a sine).
* `House.difficultyRamp` (`k[]` = {10,20,30,40}) -> `goalByTowerType` (`blockGoal = k[towerType - 1]`).
* `House.assignCitizens` -> `evacuateFloor` (citizens on a floor bail out when that floor is removed).
* `CityMode.isCityModeActive` -> `isTowerGameActive`; field `d` -> `towerGameActive`. [`House` restores the
  tower game and sets screenState 3 when it is true]

## Inferred, lower confidence
`CityMode`: `launchDelay`, `placementResultTimer`, `placementInProgress`, `unlockedSpecialTier`,
`unlockedBonusTier`, `cursorActive`, `flashColorIndex`, `targetCellEmpty`, `buildingHintPending`, `hudBarSprite`.
`House`: `cityInitPending`, `landingBounce`, `towerInstability`, `menuCloudInit`, `reservedFlagA/B`,
`reservedValue`, `unusedValue` (only saved/loaded, never read for logic), instance fields `J R S cL t`
(still unnamed) and the sprite field names (identified by looking at the PNGs).
