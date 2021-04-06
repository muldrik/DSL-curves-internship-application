package muldrik.curves.api

typealias fn = (t: Double) -> Double
val t = fun(t: Double) = t
operator fun fn.plus(other: fn): fn = fun (t: Double) = this.invoke(t) + other.invoke(t)
operator fun fn.minus(other: fn): fn = fun (t: Double) = this.invoke(t) - other.invoke(t)
operator fun fn.times(other: fn): fn = fun (t: Double) = this.invoke(t) * other.invoke(t)
operator fun fn.div(other: fn): fn = fun (t: Double) = this.invoke(t) / other.invoke(t)
operator fun fn.plus(other: Number): fn = fun (t: Double) = this.invoke(t) + other.toDouble()
operator fun fn.minus(other: Number): fn = fun (t: Double) = this.invoke(t) - other.toDouble()
operator fun fn.times(other: Number): fn = fun (t: Double) = this.invoke(t) * other.toDouble()
operator fun fn.div(other: Number): fn = fun (t: Double) = this.invoke(t) / other.toDouble()
operator fun Number.plus(other: fn): fn = other + this
operator fun Number.minus(other: fn): fn = other - this
operator fun Number.times(other: fn): fn = other * this
operator fun Number.div(other: fn): fn = other / this

data class Curve(val range: IntRange, val xFun: fn, val yFun: fn,
                 val id: Int = 0,
                 val samples: Int = 400,
                 val color: String = "red",
                 val alpha: Double = 0.3,
                 val size: Double = 3.0)

