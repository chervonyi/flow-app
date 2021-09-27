package room106.app.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import room106.app.flow.ui.theme.Blue
import room106.app.flow.ui.theme.Grey
import room106.app.flow.ui.theme.Transparent
import room106.app.flow.ui.theme.White

@Composable
fun Navigation() {
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(Screen.MainScreen.route) { MainScreen(navController) }
        composable(Screen.MessageScreen.route) { MessageScreen(navController) }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
    ) {
        Text(text = "MainScreen")
        FlowButton(
            onClick = {
                navController.navigate(Screen.MessageScreen.route)
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            text = "Prepare your next message"
        )
    }
}

@Composable
fun MessageScreen(navController: NavController) {
    val focusManager = LocalFocusManager.current

    var name by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
        .padding(24.dp)
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(text = "Name")} ,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            })
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextField(
            value = message,
            onValueChange = {
                message = it
            },
            label = { Text(text = "Message")} ,
            modifier = Modifier
                .fillMaxWidth().height(120.dp),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.primary,
                backgroundColor = MaterialTheme.colors.surface,
                textColor = MaterialTheme.colors.onSurface
            ),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
            })
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SecondaryButton(
                onClick = {
                    navController.navigate(Screen.MainScreen.route)
                },
                text = "Dismiss"
            )

            FlowButton(
                onClick = {
                    navController.navigate(Screen.MainScreen.route)
                },
                text = "Done"
            )
        }
    }
}

@Composable
fun FlowButton(
    onClick: (() -> Unit),
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )) {
        Text(text = text, color = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun SecondaryButton(
    onClick: (() -> Unit),
    modifier: Modifier = Modifier,
    text: String = ""
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        )) {
        Text(text = text, color = MaterialTheme.colors.secondary)
    }
}




