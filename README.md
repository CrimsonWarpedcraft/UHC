# UHC
A simple UHC plugin!

[![Build and Artifact](https://github.com/CrimsonWarpedcraft/UHC/actions/workflows/artifact.yml/badge.svg)](https://github.com/CrimsonWarpedcraft/UHC/actions/workflows/artifact.yml)

## Features
- Moving configurable world border
- Reset world to first day
- Disable player passive regeneration
- Reset player stats and inventory
- Player health displayed in player list
- Disable totem of undying
- Disable villager damage and trades
- Removed suspicious stew recipe
- Block new players from joining after a game has started

## Commands
`/uhc start` - Starts the game

## Permissions
`uhc.admin` - Allows players to use administrative commands (Default: op)

## Contributing
### General workflow
1. First, pull any changes from `main` to make sure you're up-to-date
2. Create a branch from `main`
    * Give your branch a name that describes your change (e.g. add-scoreboard)
    * Focus on one change per branch
    * Keep your commits small, and write descriptive commit messages
3. When you're ready, create a pull request to `main` with a descriptive title, and listing any changes made in its description
    * Link any issues that your pull request is related to as well

#### Example:
```
Create scoreboard for total points

ADDED - Scoreboard displayed in-game at game end  
CHANGED - Updated `StorageManager` class to persist scoreboard data
```

After the pull request has been reviewed, approved, and passes all automated checks, it will be merged into main.

### Building locally
Thanks to [Gradle](https://gradle.org/), building locally is easy no matter what platform you're on. Simply run the following command:

#### macOS/Linux/Unix/
`./gradlew build`

#### Windows
`gradlew.bat build`

This build step will also run all checks, making sure your code is clean.
