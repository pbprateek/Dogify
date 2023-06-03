import androidx.compose.ui.window.ComposeUIViewController
import com.example.dogify.navigation.App
import platform.UIKit.UIViewController


fun IosMainApp():UIViewController = ComposeUIViewController {
    App()
}
