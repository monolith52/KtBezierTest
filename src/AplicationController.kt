
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import java.util.ArrayList
import jdk.nashorn.internal.objects.NativeArray.forEach
import java.util.stream.IntStream
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class ApplicationController () {
    @FXML lateinit var canvas: Canvas

    var beziers = arrayListOf<BezierAnimator>()

    @FXML
    fun initialize() {
        (0..10).forEach { i -> beziers.add(BezierAnimator()) }
        Thread(Runnable { this.run() }).start()
        beziers.forEach { bezier -> bezier.animate() }
    }

    fun run() {
        while (true) {
            draw()
            try {
                Thread.sleep((1000 / 60).toLong())
            } catch (e: InterruptedException) {
            }

        }
    }

    fun draw() {
        Platform.runLater({
            val gc = canvas.getGraphicsContext2D()
            gc.setFill(Color.WHITE)
            gc.fillRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight())
            beziers.forEach { bezier -> bezier.draw(gc) }
        })
    }
}