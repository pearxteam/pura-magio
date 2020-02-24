# Pura Magio
## Preparing the project
1: Clone the mod repository.

HTTPS: `git clone --recursive https://github.com/pearxteam/pura-magio`

SSH: `git clone --recursive git@pearx.ru:pearxteam/pura-magio.git`

## Setting up the project for development.
1: Make sure that the instructions in the "Preparing the project" section have been executed.

2: Open the cloned directory.

3: Setup the workspace:

Windows: `gradlew.bat setupDecompWorkspace`

*nix: `./gradlew setupDecompWorkspace`

4: Create "run" directory: `mkdir run`

5: Open IntelliJ IDEA, import build.gradle.kts as a Gradle project. Make sure "Create separate module per source set" is checked.

6: Wait some time until all the background tasks finished.

7: Create two run configurations of type "Application". In both configurations, select "PurificatiMagicae_main" in "Use classpath of module:" and type at the end of "Working directory:" "\run" on Windows and "/run" on *nix. In first configuration, use "GradleStart" as a main class. It's a client configuration. In the second configuration, use "GradleStartServer" as a main class. It's a server configuration.

## Building the project
1: Make sure that the instructions in the "Preparing the project" section have been executed.

2: Open the cloned directory.

3: Build the project:

Windows: `gradlew.bat build`

*nix: `./gradlew build`

All the .jar files will be available inside the "build/libs" directory.