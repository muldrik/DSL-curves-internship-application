import muldrik.curves.api.div
import muldrik.curves.api.minus
import muldrik.curves.api.plus
import muldrik.curves.api.times
import muldrik.curves.dsl.curve
import muldrik.curves.dsl.curves
import muldrik.curves.dsl.t

fun main() {
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
    drawCurves(curves)
}

