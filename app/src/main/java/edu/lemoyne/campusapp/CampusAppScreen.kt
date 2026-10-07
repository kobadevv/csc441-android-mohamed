package edu.lemoyne.campusapp

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.lemoyne.campusapp.ui.theme.CampusAppTheme

//--- Class 7: Step 1: A counter that remembers
@Composable
fun CounterDemo() {
    var count by remember { mutableStateOf(0) }
//    var count = 0

    Button(onClick = {
        count++
//        println("count is now $count")
    }) {
        Text(text = "Tapped $count times")
    }
}

// --- Class 9: Step 2: one owner for the data

@Composable
fun CampusAppScreen(modifier: Modifier = Modifier) {
    //--- Class 7: Step 2: the list lives in state
    val languages = remember {
        mutableStateListOf("English", "Arabic", "French", "Japanese")
    }
    // --- Class 9: Step 4: which screen is showing is just state ---
    var currentScreen by rememberSaveable { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> HomeScreen(
            languages = languages,
            onAddLanguage = { languages.add(it) },
            onSeeAll = { currentScreen = "list"}
        )
        "list" -> ListScreen(
            languages = languages,
            onBack = { currentScreen = "home"},
            modifier = modifier
        )
    }
}

//--- Class 6: Step 1: my own screen ---
@Composable
fun HomeScreen(
    languages: MutableList<String>,
    onAddLanguage: (String) -> Unit,
    onSeeAll:() -> Unit,
    modifier: Modifier = Modifier
) {

    //--- Class 7: Step 3: what's typed lives in state ---
    var newLanguage by remember { mutableStateOf("") }

    //--- Class 8: Step 2: the error message lives in state too ---
    var error by remember { mutableStateOf<String?>(null) }

    //--- Class 6: Step 3: a column, so things stack ---
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
//        CounterDemo()
        // --- Lab 6 · Task 3: a picture of my own ---
        Image(
            painter = painterResource(id = R.drawable.streak),
            contentDescription = "September anki streak",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        //--- Class 6: Step 4: real styling ---
        Text(
            text = "Language App",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        //--- Lab 6: Step 1: Make the screen properly yours ---
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "My languages list:",
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        // --- Class 7: Step 3: the text field ---
        OutlinedTextField(
            value = newLanguage,
            // --- Class 8: Step 3: the field itself pushes back ---

            onValueChange = {
                newLanguage = it.take(MAX_NAME_LENGTH)
                error = null
            },
            label = { Text("Language name") },
            singleLine = true,
            isError = error != null,
            modifier = Modifier.fillMaxWidth()
        )

        error?.let { errorMsg ->
            Text(
                text = errorMsg,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp
            )
        }

        // --- Lab 7 · Task 4: a live character counter ---
        Text(
            text = "${newLanguage.length} / ${MAX_NAME_LENGTH}",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        //--- Class 7: Step 4: the button changes the state ---
        Button (onClick = {
            //--- Class 8: Step 3: check before you add ---

            val problem = validateLanguageName(input = newLanguage, existingLanguages = languages)
            if (problem == null) {
                // --- Class 9: Step 2: ask the owner to add it
                onAddLanguage(newLanguage.trim())
                newLanguage = ""
            } else {
                error = problem
            }
        },
            // --- Class 8: Step 4: the sign on the door, not the lock ---
            enabled = newLanguage.isNotBlank()
        ) {
            Text("Add")
        }

        //--- Class 7: Step 2: draw whatever is in the list ---
        Text(
            // --- Lab 7 · Task 2: singular and plural ---
            text = if (languages.size == 1) "1 Language" else "${languages.size} Languages",
            fontWeight = FontWeight.Bold
        )

        // --- Lab 6 · Task 2: footer ---
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Last updated October 2026",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        // --- Class 9: Step 5: a way to the second screen ---
        Button(
            onClick = onSeeAll

            ) {
                Text(text = "See all languages")
            }
    }
}

// --- Class 9: Step 3: the second screen ---
@Composable
fun ListScreen(
    languages: List<String>,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // --- Class 9: Step 6: the phone ---
    BackHandler {onBack}
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) {
            Text(text = "Back")
        }

        Text(
            text = "All Languages",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        for (language in languages) {
            Text(text = language, fontSize = 18.sp)
        }
    }
}
const val MAX_NAME_LENGTH = 30

//--- Class 8: Step 1: one rule book for language names ---
fun validateLanguageName(input: String, existingLanguages:List<String>): String? {
    val name = input.trim()
    return when {
        name.isEmpty() -> "Enter a language"
        // --- Lab 8 · Task 1: A minimum length ---
        name.length < 3 -> "Too short — at least 3 characters"
        // --- Lab 8 · Task 2: A rule of my own ---
        name.any { it.isDigit() } -> "Numbers are not allowed, letters only"
        name.length > MAX_NAME_LENGTH -> "Keep it to 40 characters or fewer"
        existingLanguages.any { it.equals(name, ignoreCase = true) } -> "$name is already on the list"
        else -> null
    }
}

//--- Class 6: Step 2: Preview ---
@Preview
@Composable
fun HomeScreenPreview() {
    CampusAppTheme() {
       // HomeScreen()
    }
}

// --- Lab 6 · Task 4: dark mode preview ---
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenDarkPreview() {
    CampusAppTheme {
        Surface {
          //  HomeScreen()
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ListScreenPreview() {
//    CampusAppTheme(
//        val languages = remember {
//            mutableStateListOf("English", "Arabic", "French", "Japanese")
//        },
//        onBack = {}
//    )
//}

