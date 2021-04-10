package muldrik.curves.api

/**
 * Extensions to allow arithmetic operations on polynomial functions.
 * Api is supposed to be modified only for major changes,
 * otherwise configuring only the DSL is preferred
 */
typealias fn = (t: Double) -> Double
operator fun fn.unaryMinus(): fn = fun(t: Double) = -this.invoke(t)
operator fun fn.plus(other: fn): fn = fun (t: Double) = this.invoke(t) + other.invoke(t)
operator fun fn.minus(other: fn): fn = fun (t: Double) = this.invoke(t) - other.invoke(t)
operator fun fn.times(other: fn): fn = fun (t: Double) = this.invoke(t) * other.invoke(t)
operator fun fn.div(other: fn): fn = fun (t: Double) = this.invoke(t) / other.invoke(t)
operator fun fn.plus(other: Number): fn = fun (t: Double) = this.invoke(t) + other.toDouble()
operator fun fn.minus(other: Number): fn = fun (t: Double) = this.invoke(t) - other.toDouble()
operator fun fn.times(other: Number): fn = fun (t: Double) = this.invoke(t) * other.toDouble()
operator fun fn.div(other: Number): fn = fun (t: Double) = this.invoke(t) / other.toDouble()
operator fun Number.plus(other: fn): fn = other + this
operator fun Number.minus(other: fn): fn = -(other - this)
operator fun Number.times(other: fn): fn = other * this
operator fun Number.div(other: fn): fn = other / this

/**
 * Stores basic information about a curve
 * @param range Domain of the functions in terms of t
 * @param xFun Function for the x coordinate
 * @param yFun Function for the y coordinate
 * @param id Unique id to simplify naming required for the plotting library
 * @param samples Determines how precisely the plot is built
 * @param color Defines the color of the curve
 * @param alpha Defines the transparency of the curve. 1 - solid, 0 - transparent
 * @param size Defines how thick the curve appears
 */
data class Curve(val range: IntRange, val xFun: fn, val yFun: fn,
                 val id: Int = 0,
                 val samples: Int = 500,
                 val color: String = "red",
                 val alpha: Double = 0.9,
                 val size: Double = 2.0)

