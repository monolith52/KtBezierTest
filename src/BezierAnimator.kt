import javafx.geometry.Point2D
import javafx.scene.paint.Color
import javafx.scene.canvas.GraphicsContext
import javax.swing.Spring.width
import javafx.animation.Timeline
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.event.EventHandler
import javafx.util.Duration

class BezierAnimator () {
    var bezier1: BezierPath
    var bezier2: BezierPath
    var color: Color
    var width: Double

    init {
        bezier1 = initialRandBezier()
        bezier2 = nextRandBezier(bezier1)
        color = Color(Math.random() * 0.7 + 0.3, Math.random() * 0.7 + 0.3, Math.random() * 0.7 + 0.3, 1.0)
        width = 8.0
    }

    fun animate() {
        val animation = Timeline(
                KeyFrame(Duration(0.0), KeyValue(bezier1.fromProperty(), 0.0)),
                KeyFrame(Duration(500.0), KeyValue(bezier1.fromProperty(), 1.0)),
                KeyFrame(Duration(0.0), KeyValue(bezier2.toProperty(), 0.0)),
                KeyFrame(Duration(500.0), KeyValue(bezier2.toProperty(), 1.0))
        )
        animation.onFinished = EventHandler {
            animation.stop()
            bezier1 = bezier2
            bezier2 = nextRandBezier(bezier1)
            bezier2.setTo(0.0)
            animate()
        }
        animation.playFromStart()
    }

    fun draw(gc: GraphicsContext) {
        bezier1.draw(gc, color, width)
        bezier2.draw(gc, color, width)
    }

    private fun getRandPoint(): Point2D {
        return Point2D(Math.random() * 400.0, Math.random() * 400.0)
    }

    private fun initialRandBezier(): BezierPath {
        val p3 = getRandPoint()
        var p4 = getRandPoint()
        p4 = p4.add((p3.x - p4.x) / 2, (p3.y - p4.y) / 2)
        return BezierPath(getRandPoint(), getRandPoint(), p3, p4)
    }

    private fun nextRandBezier(base: BezierPath): BezierPath {
        val p2 = base.p4.add(base.p4.subtract(base.p3))
        val p3 = getRandPoint()
        var p4 = getRandPoint()
        p4 = p4.add((p3.x - p4.x) / 2, (p3.y - p4.y) / 2)
        return BezierPath(base.p4, p2, p3, p4)
    }
}