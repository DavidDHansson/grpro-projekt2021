# Design fase

## Classes
---
### Media/Movie/Series
- `abstract class Media`
- `class Movie extends Media`
- `class Series extends Media`

## Scenes
---
### ProfileScene
---
#### Model
- `getUsers()`
- `removeUser()`
#### View
- 4 knapper - en for hver profil
#### Controller

### HomeScene
---
#### Model
- `[Media]`
- `getMedia()` - læser txt filer og konverterer til classes
- `searchWithQuery(String query)`
- `addToMyList(Media m)` - update with a user manager
- `removeFromMyList(Media m)` - update with a user manager
#### View
- Searchbar
- Profil knap - route til `ProfileScene`
- "Min liste" af `Media`
        - Actionevents på media, som router til `MediaViewerScene`
        - Kan slettes
- Liste af `Media`
        - Actionevents på media, som router til `MediaViewerScene`
        - Kan tiføres til min liste
#### Controller

### MediaViewerScene
---
#### Model
- `Media`
#### View
- Play/pause button
#### Controller


