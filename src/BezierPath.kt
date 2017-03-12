import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Point2D
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class BezierPath (val p1: Point2D, val p2: Point2D, val p3: Point2D, val p4: Point2D) {
    var from: DoubleProperty = SimpleDoubleProperty(0.0)
    var to: DoubleProperty = SimpleDoubleProperty(1.0)
    var step = 0.02

    fun ofX(t: Double): Double {
        val inv = 1.0 - t
        return  t * t * t * p4.x +
                3 * t * t * inv * p3.x +
                3 * t * inv * inv * p2.x +
                inv * inv * inv * p1.x
    }

    fun ofY(t: Double): Double {
        val inv = 1.0 - t
        return t * t * t * p4.y +
                3 * t * t * inv * p3.y +
                3 * t * inv * inv * p2.y +
                inv * inv * inv * p1.y
    }

    fun draw(gc: GraphicsContext, color: Color, width: Double) {
        //		gc.setStroke(Color.RED);
        //		gc.setLineWidth(1.0d);
        //		gc.strokeLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        //		gc.setStroke(Color.BLUE);
        //		gc.strokeLine(p3.getX(), p3.getY(), p4.getX(), p4.getY());

        gc.stroke = color
        gc.lineWidth = width
        var t = from.get()
        while (t < to.get()) {
            val t2 = Math.min(t + step, to.get())
            gc.strokeLine(ofX(t), ofY(t), ofX(t2), ofY(t2))
            t += step
        }
    }

    fun getFrom(): Double {
        return from.get()
    }

    fun setFrom(v: Double) {
        from.set(Math.max(0.0, Math.min(1.0, v)))
    }

    fun fromProperty(): DoubleProperty {
        return from
    }

    fun getTo(): Double {
        return to.get()
    }

    fun setTo(v: Double) {
        to.set(Math.max(0.0, Math.min(1.0, v)))
    }

    fun toProperty(): DoubleProperty {
        return to
    }
}
