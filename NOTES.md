## Week 5 Wed
1. How long did your first build take, roughly? And the second?
2. In dark mode, what changed colour on its own?
3. What is one thing on your screen right now that you don't understand yet?

1(Ans). The first build took me like 5 minutes, the second took like 2.
2(Ans). In dark mode, the backgrounds of apps and uis would change from a white/light color to a dark color.
3(Ans). One thing on my screen I do not understand is the scaffold function (the name does not sound familliar at all)
 
## Week 5 Friday
- I changed .fillMaxWidth() to .fillMaxSize() and the container stretched to the bottom of the screen instead of cutting it off after the final text

## Week 6 Wednesday

- Questions: 1. Paste the Logcat lines from your broken counter. 2. In your own words: why did count
change but the screen didn't? 3. What does remember do? What would happen without it?

1. count is now 1
   count is now 2
   count is now 3

2. the variable count was updated but the screen was not because we were using a plain variable without a compose state. Composable cannot see these.

3. remember ensures that when recomposing your values do not reinitialize (resetting them to their original values). Without it the value would essentially remain static.

## Week 6 Friday Lab

- putting name.length < 3 -> "Too short — at least 3 characters" before the isEmpty() line in the when is wrong is because empty language entries are 0 characters 
  prompting the too short message instead of the enter a language message.

- The rule I added myself excludes entries from containing numbers. I added it because language names don't contain numbers (to my knowledge at least).

| I typed | What the app did | Correct? |
|---|---|---|
| (nothing) | Add button greyed out | yes |
| "   " | Add button greyed out | yes |
| ab | Showed "Too short - at least 3 characters" | yes |
| ThisIsAnExtremelyLongLanguageNameToTestTheCharacterLimitField | Text box cut off input at 30 characters | yes |
| 12345 | Showed "Numbers are not allowed, letters only" | yes |
| First Language | Accepted input, Add button enabled | yes |
| ENGLISH | Prevented add, flagged duplicate name | yes |
| Latin | Successfully added language to list | yes |

## Week 6 Wed Lab

- Questions: 1. After rotating, which screen were you on? 2. Were your two new items still there? 3.
Look at how currentScreen and trails are each created in CampusAppScreen. Explain the difference in one or
two sentences

1. After rotating I was still on listScreen

2. No, they were removed

3. remember saves in app memory which survives recompositions however when rotating the screen everything initializes again; 
   since this is the case, remembersaveable is used to prevent the value from resetting during initialization. 

## Week 13 Wed Lab

1. What triggers refresh() when InterestsScreen first opens?

- The "LaunchedEffect(Unit){...}" function runs automatically and calls "onRefresh()".

2. What triggers refresh() when the button is tapped?

- When the button is tapped the onClick action makes the same "onRefresh()" call that is made when Interests Screen is first opened.

3. Why does InterestsScreen receive onRefresh instead of the ViewModel?

- The ViewModel is owned by MainActivity so simply a call is passed to separate the ViewModel from the UI. 

4. What is the difference between a successful [] response and a network failure?

- A successful [] response means that the supabase table was successfully reached however there was no data to pull (corresponding message e.g. "No items yet.", while a network failure shows an error message stating that it is unable to reach the host destination (opposed to simply crashing).

5. How did you prove Android and the website use the same Supabase data?

- Updating the supabase table would load changes on both the Android app and the website.
