# Language Log
This is an Android app that lets users keep track of different languages they have learned or are studying.

## Screens
**Home**
Users can see a summary of their tracked languages, add new languages using a text field, and navigate to the list or about screen.
**List**
Users can scroll through their languages, remove individual languages, or clear the entire list.
**About**
Users can see what the app is for and who made it.

## Input rules
| Input | What the app says |
|---|---|
(empty) | Enter a language |
Less than 3 characters | Too short — at least 3 characters |
Contains numbers | Numbers are not allowed, letters only |
More than 30 characters | Keep it to 30 characters or fewer |
Duplicate language | [Language] is already on the list |

## How to run
1. Clone this repo
2. Open the cloned folder in Android Studio
3. Wait for Gradle sync, then Run

## Known limitations
If the user rotates the phone, the current state is lost and the app resets (populating language list with original entries, removing new ones).
