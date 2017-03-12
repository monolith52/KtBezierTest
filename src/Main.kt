import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import javafx.stage.WindowEvent

class Main () : Application() {
    override fun start(primaryStage: Stage?) {
        if (primaryStage == null) return
        val root: BorderPane = FXMLLoader.load(javaClass.getResource("Application.fxml"))
        val scene = Scene(root)
        scene.stylesheets.add(javaClass.getResource("Application.css").toExternalForm())
        primaryStage.scene = scene
        primaryStage.onCloseRequest =  EventHandler {
            Platform.exit()
            System.exit(0)
        }
        primaryStage.show()
    }
}


fun main(args: Array<String>) {
    Application.launch(Main::class.java, *args)
}