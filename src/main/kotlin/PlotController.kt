import jetbrains.datalore.base.registration.Disposable
import jetbrains.datalore.plot.MonolithicCommon
import jetbrains.datalore.vis.swing.batik.DefaultPlotPanelBatik
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.intern.toSpec
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities


/**
 * A simplified version of an example controller from lets-plot-kotlin tutorial at
 * https://github.com/JetBrains/lets-plot-kotlin/blob/master/demo/jvm-batik/src/main/kotlin/minimalDemo/Main.kt
 * Can be easily extended to include multiple plots to switch between
 */
class PlotController(
    private val plot: Plot,
    initialPreserveAspectRadio: Boolean
) {
    var plotContainerPanel: JPanel? = null
    var preserveAspectRadio: Boolean = initialPreserveAspectRadio
        set(value) {
            field = value
            rebuildPlotComponent()
        }

    fun rebuildPlotComponent() {
        plotContainerPanel?.let {
            val container = plotContainerPanel!!
            for (component in container.components) {
                if (component is Disposable) {
                    component.dispose()
                }
            }
            container.removeAll()

            container.add(createPlotPanel())
            container.revalidate()
        }
    }

    private fun createPlotPanel(): JPanel {
        val rawSpec = plot.toSpec()
        val processedSpec = MonolithicCommon.processRawSpecs(rawSpec, frontendOnly = false)

        return DefaultPlotPanelBatik(
            processedSpec = processedSpec,
            preserveAspectRatio = preserveAspectRadio,
            preferredSizeFromPlot = false,
            repaintDelay = 10,
        ) { messages ->
            for (message in messages) {
                println("[Example App] $message")
            }
        }
    }
}

/**
 * Creates a window and draws a plot on it. Can be extended to include additional boxes, buttons
 * and text elements
 */

fun drawCurves(plot: Plot) {
    val controller = PlotController(
        plot,
        true
    )
    val window = JFrame("Curves dsl")
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.contentPane.layout = BoxLayout(window.contentPane, BoxLayout.Y_AXIS)

    val plotContainerPanel = JPanel(GridLayout())
    window.contentPane.add(plotContainerPanel)

    controller.plotContainerPanel = plotContainerPanel
    controller.rebuildPlotComponent()

    SwingUtilities.invokeLater {
        window.pack()
        window.size = Dimension(1600, 900)
        window.setLocationRelativeTo(null)
        window.isVisible = true
    }
}