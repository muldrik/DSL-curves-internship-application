package muldrik.curves.dsl

import jetbrains.letsPlot.coord_fixed
import jetbrains.letsPlot.geom.geom_path
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.PosKind
import jetbrains.letsPlot.intern.layer.PosOptions
import jetbrains.letsPlot.lets_plot
import muldrik.curves.api.Curve
import muldrik.curves.api.fn


fun sample(coordFun: fn, range: IntRange, samples: Int): List<Double> {
    val l = range.first.toDouble()
    val r = range.last.toDouble()
    return List(samples) { index ->
        coordFun (l + (r - l) * index / samples)
    }
}

fun curveToPath(curve: Curve) = geom_path(
    color = curve.color,
    alpha = curve.alpha,
    size = curve.size,
    showLegend = false,
){
    x = "x${curve.id}"
    y = "y${curve.id}"
}

fun curves(settings: PlotBuilder.() -> Unit): Map<String, Plot> {
    val curves = PlotBuilder().apply(settings)
    val data = curves.curves.associateBy({ "x${it.id}" }, { sample(it.xFun, it.range, it.samples) }) +
            curves.curves.associateBy({ "y${it.id}" }, { sample(it.yFun, it.range, it.samples) })
    var pathBuilder = lets_plot(data)
    curves.curves.forEach{
        pathBuilder += curveToPath(it)
    }
    pathBuilder += coord_fixed(1.0)
    return mapOf(curves.name to pathBuilder)
}

class CurveBuilder {
    lateinit var range: IntRange
    lateinit var x: fn
    lateinit var y: fn
    var samples = 400
    var color: String = "red"
    var alpha: Double = 0.3
    var size: Double = 3.0
}

class PlotBuilder {
    var currentIndex = 0
    val curves = mutableListOf<Curve>()
    var name = "Curves"
}

fun PlotBuilder.curve(settings: CurveBuilder.() -> Unit) {
    val curve = CurveBuilder().apply(settings)
    this.curves.add(Curve(curve.range, curve.x, curve.y, this.currentIndex++,
                    curve.samples,
                    curve.color,
                    curve.alpha,
                    curve.size))
    println(currentIndex)
}