import muldrik.curves.api.*
import muldrik.curves.dsl.curve
import muldrik.curves.dsl.curves




fun main() {
    val a = List(10) {0}
    val curves = curves {
        curve {
            range = 0..7
            x = t * 0
            y = t
        }
        curve {
            range = -1..1
            x = 3 * (1 - t * t)
            y = 4 + (t + 1) * 3 / 2
        }
        curve {
            range = -2..2
            x = 4 - t * t
            y = t + 2
        }
    }
    println(curves)
    drawCurves(curves)
}

