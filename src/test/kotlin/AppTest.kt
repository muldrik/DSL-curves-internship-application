import muldrik.curves.dsl.curve
import muldrik.curves.dsl.curves
import muldrik.curves.dsl.t
import muldrik.curves.api.*
import org.junit.jupiter.api.Test
import kotlin.math.abs

class AppTest {
    @Test
    fun `example curves`() {
        val result = curves {
            curve {
                range = 0..7
                x = t * 0
                y = t
                samples = 10
            }
            curve {
                range = -1..1
                x = 3 * (1 - t * t)
                y = 4 + (t + 1) * 3 / 2
                samples = 10
            }
            curve {
                range = -2..2
                x = 4 - t * t
                y = t + 2
                samples = 10
            }
        }
        val expected = mapOf(
            "x0" to listOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),
            "x1" to listOf(-0.0, 1.0799999999999996, 1.92, 2.52, 2.88, 3.0, 2.88, 2.5200000000000005, 1.9199999999999997, 1.0799999999999996),
            "x2" to listOf(-0.0, 1.4399999999999995, 2.56, 3.36, 3.84, 4.0, 3.84, 3.3600000000000003, 2.5599999999999996, 1.4399999999999995),
            "y0" to listOf(0.0, 0.7, 1.4, 2.1, 2.8, 3.5, 4.2, 4.9, 5.6, 6.3),
            "y1" to listOf(4.0, 4.3, 4.6, 4.9, 5.2, 5.5, 5.8, 6.1, 6.4, 6.7),
            "y2" to listOf(0.0, 0.3999999999999999, 0.8, 1.2, 1.6, 2.0, 2.4, 2.8, 3.2, 3.6)
        )
        val epsilon = 0.000001
        result.data!!.entries.forEach {
            val list = it.value
            if (list is List<*>) {
                list.map { it.toString().toDouble() }.forEachIndexed { index, d ->
                    assert(abs(d - expected[it.key.toString()]!![index]) < epsilon
                            || d == expected[it.key.toString()]!![index])
                }
            }
            else throw AssertionError("$list is not a valid list of coodrinates")
        }
    }
}