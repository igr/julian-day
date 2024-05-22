# just displays the recipes
_default:
    @just --list

# clean the project
clean:
    ./gradlew clean

# build the project and run the tests
build:
    ./gradlew build

# run the tests
test:
    ./gradlew test

# build and assemble the jar
jar:
    ./gradlew clean build assemble --warning-mode all

# installs the library in the local maven repository
install:
    ./gradlew publishToMavenLocal

# release the library by creating a tag and pushing it to the remote repository
release-create version:
    git tag -a v{{version}} -m "Release version {{version}}"
    git push origin v{{version}}
    open https://github.com/igr/julian-day/tags

# closes and release staging repository, after the release is published
release-publish:
    ./gradlew findSonatypeStagingRepository closeSonatypeStagingRepository
    ./gradlew findSonatypeStagingRepository releaseSonatypeStagingRepository
