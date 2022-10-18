# Maven
> It is a project management tool

## Uses
- Collects all JAR files based on dependencies selected
- Handles class / build path
- Provides **standard directory structure**
- Maven projects are portable

## How maven works?
![image](https://user-images.githubusercontent.com/60386381/196372678-eabaf633-98e3-4903-969a-3dabd1c3f6eb.png)

## POM File
- Is in in xml format - `pom.xml`
- POM stands for - Project Object Model
- All dependencies are defined inside the POM

## Project Coordinates
- Uniquely identify a project
- Contents of coordinates:
    | Name   | Description    |
    |--------------- | --------------- |
    | Group ID | Name of company / organization. Convention is to use the domain name in reverse |
    | Artifact ID | Name of the project |
    | Version | A specific version release |

## Dependency Coordinates
- Used to find the dependency version
- Content of coorindates is same as project coordinates

### How to find dependency coordinates?
- Visit the dependency page

Or

- [Visit maven repositories](http://mvnrepository.com)

## Archetypes (Collection of starter files)
- Can be used to create maven projects
- Contains template for a given maven project

## Repository Types
- Local Repositories
    - Located on developers computer
    - Maven searches local repo before central repo
- Central Repositories
    - Requires internet connection
    - [Central Repo Location](https://repo.maven.apcahe.org/maven2/)

## Resources
- [Maven Documentation](https://maven.apache.org/guides/)