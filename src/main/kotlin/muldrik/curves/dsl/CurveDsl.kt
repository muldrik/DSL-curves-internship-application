package muldrik.curves.dsl

import jetbrains.letsPlot.coord_fixed
import jetbrains.letsPlot.geom.geom_path
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.lets_plot
import muldrik.curves.api.Curve
import muldrik.curves.api.fn


/**
 * Universal linear function that is used to build polynomial expressions
 */
val t = fun(t: Double) = t


/**
 * Generate equally spaced points
 * @param samples number of points
 * @param range range and using
 * @param coordinateFun function
 */
fun sample(coordinateFun: fn, range: IntRange, samples: Int): List<Double> {
    val l = range.first.toDouble()
    val r = range.last.toDouble()
    return List(samples) { index ->
        coordinateFun (l + (r - l) * index / samples)
    }
}

/**
 * Converts a curve to a data type accepted by the plotting library
 */

fun curveToPath(curve: Curve) = geom_path(
    color = curve.color,
    alpha = curve.alpha,
    size = curve.size,
    showLegend = false,
){
    x = "x${curve.id}"
    y = "y${curve.id}"
}

/**
 * Creates multiple curves and joins them into a single plot
 */

fun curves(settings: PlotBuilder.() -> Unit): Plot {
    val curves = PlotBuilder().apply(settings)
    val data = curves.curves.associateBy({ "x${it.id}" }, { sample(it.xFun, it.range, it.samples) }) +
            curves.curves.associateBy({ "y${it.id}" }, { sample(it.yFun, it.range, it.samples) })
    var pathBuilder = lets_plot(data)
    curves.curves.forEach{
        pathBuilder += curveToPath(it)
    }
    pathBuilder += coord_fixed(1.0)
    return pathBuilder
}


/**
 * An object to create extension functions on.
 * Stores the information needed to create a curve
 * Default parameters are the same as in the api but can be freely modified without affecting the api
 */
class CurveBuilder {
    lateinit var range: IntRange
    lateinit var x: fn
    lateinit var y: fn
    var samples = 500
    var color: String = "red"
    var alpha: Double = 0.9
    var size: Double = 2.0
}

/**
 * An object to create extension functions on.
 * Stores the created curves
 * Can be easily modified without changing the api
 */
class PlotBuilder {
    var currentIndex = 0
    val curves = mutableListOf<Curve>()
}

fun PlotBuilder.curve(settings: CurveBuilder.() -> Unit) {
    val curve = CurveBuilder().apply(settings)
    this.curves.add(Curve(curve.range, curve.x, curve.y, this.currentIndex++,
                    curve.samples,
                    curve.color,
                    curve.alpha,
                    curve.size))
}