# Purificati Magicae
## Setting up the project
1. Clone the repository using SSH or HTTPS.
HTTPS: `git clone --recursive https://git.pearx.ru/PearXTeam/PurificatiMagicae.git`
SSH: `git clone --recursive git@pearx.ru:PearXTeam/PurificatiMagicae.git`
2. Open the cloned directory.
3. Setup the workspace:
Windows: `gradlew.bat setupDecompWorkspace`
*nix: `./gradlew setupDecompWorkspace`
4. Create "run" directory: `mkdir run`
5. Open IDEA, import build.gradle. Uncheck "Create separate module per source set".
6. Wait some time until all background tasks finished.
7. Create two run configurations of type "Application". In both configurations, select "PurificatiMagicae" in "Use classpath of module:" and type at the end of "Working directory:" "\run" on Windows and "/run" on *nix. In first configuration, use "GradleStart" as main class. It's a client configuration. In second configuration, use "GradleStartServer" as main class. It's a server configuration.
8. That's all.
## Building the project
1. Clone the repository using SSH or HTTPS.
HTTPS: `git clone --recursive https://git.pearx.ru/PearXTeam/PurificatiMagicae.git`
SSH: `git clone --recursive git@pearx.ru:PearXTeam/PurificatiMagicae.git`
2. Open the cloned directory.
3. Build the project:
Windows: `gradlew.bat build`
*nix: `./gradlew build`
4. That's all.
All .jars will be available in "build/libs" directory.