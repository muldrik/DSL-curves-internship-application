import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.geom.geom_histogram
import jetbrains.letsPlot.geom.geom_path
import jetbrains.letsPlot.lets_plot
import muldrik.curves.api.*
import muldrik.curves.dsl.curve
import muldrik.curves.dsl.curves
import muldrik.curves.dsl.sample
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

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

