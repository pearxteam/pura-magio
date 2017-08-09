# Purificati Magicae
## Preparing the project
1: Create a directory for the workspace: `mkdir purmag`

2: Open the created directory.

3: Clone the Purificati Magicae repository.

HTTPS: `git clone --recursive https://git.pearx.ru/PearXTeam/PurificatiMagicae.git`

SSH: `git clone --recursive git@pearx.ru:PearXTeam/PurificatiMagicae.git`

4: Clone the PearXLibMC repository.

HTTPS: `git clone --recursive https://github.com/PearXTeam/PearXLibMC.git`

SSH: `git clone --recursive git@github.com:PearXTeam/PearXLibMC.git`

## Setting up the project for development.
1: Execute the instructions in the "Preparing the project" section.

2: Open the cloned Purificati Magicae directory.

3: Setup the workspace:

Windows: `gradlew.bat setupDecompWorkspace`

*nix: `./gradlew setupDecompWorkspace`

4: Create "run" directory: `mkdir run`

5: Open IDEA, import build.gradle. Uncheck "Create separate module per source set".

6: Wait some time until all background tasks finished.

7: Create two run configurations of type "Application". In both configurations, select "PurificatiMagicae" in "Use classpath of module:" and type at the end of "Working directory:" "\run" on Windows and "/run" on *nix. In first configuration, use "GradleStart" as main class. It's a client configuration. In second configuration, use "GradleStartServer" as main class. It's a server configuration.

## Building the project
1: Execute the instructions in the "Preparing the project" section.

2: Open the cloned Purificati Magicae directory.

3: Build the project:

Windows: `gradlew.bat build`

*nix: `./gradlew build`

All the .jar files will be available in the "build/libs" directory.