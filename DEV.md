# Development notes

## Releasing a new version

+ Update the version number in `build.gradle`
+ Update the version number in `README.md`
+ Commit and push the changes
+ Run `just release-create <version>`
+ Create the release on GitHub
+ Wait for the CI to finish
+ Run `just release-publish`
