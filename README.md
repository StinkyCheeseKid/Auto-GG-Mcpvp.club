# Auto GG Mod

This is a Fabric Minecraft mod that automatically sends "gg" in chat when a match completes.

## Setup

1. Make sure you have Java 17 or higher installed
2. Clone this repository
3. Run `./gradlew genSources` to generate Minecraft sources
4. Run `./gradlew build` to build the mod

## Development

- The main mod class is `AutoGGMod.java` in `src/main/java/com/example/autogg/`
- Mod metadata is in `src/main/resources/fabric.mod.json`
- Dependencies and versions are configured in `gradle.properties`

## Running in Development

1. Run `./gradlew runClient` to start the game with your mod
2. Run `./gradlew runServer` to start a server with your mod

## Building

Run `./gradlew build` to create a JAR file in `build/libs/`

## How It Works

The mod automatically detects when a "Match Complete" message appears in chat and sends "gg" in response. It includes a flag to prevent sending multiple "gg" messages in the same match.

## Customization

1. Update the mod ID, name, and description in `fabric.mod.json`
2. Change the package name in `gradle.properties` (maven_group)
3. Modify the trigger text in `AutoGGMod.java` if needed
4. Add additional auto-response functionality

## Dependencies

- Fabric Loader
- Fabric API
- Minecraft 1.21.1
- Java 17+

## License

This template is provided under the MIT License.
