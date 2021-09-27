package room106.app.flow

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object MessageScreen: Screen("message_screen")
}
