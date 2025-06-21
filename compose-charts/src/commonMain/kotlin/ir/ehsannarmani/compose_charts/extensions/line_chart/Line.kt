package ir.ehsannarmani.compose_charts.extensions.line_chart

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import ir.ehsannarmani.compose_charts.utils.calculateOffset

data class PathData(
    val path: Path,
    val xPositions: List<Double>,
    val startIndex: Int,
    val endIndex: Int
)

fun DrawScope.getLinePath(
    dataPointsY: List<Float>,
    maxValueY: Float,
    minValueY: Float,
    rounded: Boolean = true,
    size: Size? = null,
    startIndex : Int,
    endIndex: Int,
    dataPointsX: List<Float> = emptyList(),
    maxValueX: Float = 0f,
    minValueX: Float = 0f,
): PathData {

    val _size = size ?: this.size
    val path = Path()
    if (dataPointsY.isEmpty()) return PathData(
        path = path,
        xPositions = emptyList(),
        0,
        Int.MAX_VALUE
    )
    val calculateHeight = { value: Float ->
        calculateOffset(
            maxValue = maxValueY.toDouble(),
            minValue = minValueY.toDouble(),
            total = _size.height,
            value = value
        )
    }
    val calculateWidth = { value: Float ->
        calculateOffset(
            maxValue = maxValueX.toDouble(),
            minValue = minValueX.toDouble(),
            total = _size.width,
            value = value
        )
    }

    val xPositions = mutableListOf<Double>()

    if (dataPointsX.isEmpty() || dataPointsX.size != dataPointsY.size) {
        if (startIndex == 0) {
            path.moveTo(0f, (_size.height - calculateHeight(dataPointsY[0])).toFloat())
        } else {
            val x = (startIndex * (_size.width / (dataPointsY.size - 1)))
            val y = _size.height - calculateHeight(dataPointsY[startIndex]).toFloat()
            path.moveTo(x, y)
        }


        for (i in 0 until dataPointsY.size - 1) {
            val x1 = (i * (_size.width / (dataPointsY.size - 1)))
            val y1 = _size.height - calculateHeight(dataPointsY[i]).toFloat()
            val x2 = ((i + 1) * (_size.width / (dataPointsY.size - 1)))
            val y2 = _size.height - calculateHeight(dataPointsY[i + 1]).toFloat()

            if (i in startIndex..<endIndex) {
                if (rounded) {
                    val cx = (x1 + x2) / 2f
                    path.cubicTo(x1 = cx, y1 = y1, x2 = cx, y2 = y2, x3 = x2, y3 = y2)
                } else {
                    path.cubicTo(x1, y1, x1, y1, (x1 + x2) / 2, (y1 + y2) / 2)
                    path.cubicTo((x1 + x2) / 2, (y1 + y2) / 2, x2, y2, x2, y2)
                }
            }

            xPositions.add(x1.toDouble())
        }
    } else {
        if (startIndex == 0) {
            path.moveTo(
                (_size.width - calculateWidth(dataPointsX[0])).toFloat(),
                (_size.height - calculateHeight(dataPointsY[0])).toFloat()
            )
        } else {
            val x = _size.width - calculateWidth(dataPointsX[startIndex]).toFloat()
            val y = _size.height - calculateHeight(dataPointsY[startIndex]).toFloat()
            path.moveTo(x, y)
        }
        for (i in 0 until dataPointsY.size - 1) {
            val x1 = _size.width - calculateWidth(dataPointsX[i]).toFloat()
            val y1 = _size.height - calculateHeight(dataPointsY[i]).toFloat()
            val x2 = _size.width - calculateWidth(dataPointsX[i + 1]).toFloat()
            val y2 = _size.height - calculateHeight(dataPointsY[i + 1]).toFloat()

            if (i in startIndex..<endIndex) {
                if (rounded) {
                    val cx = (x1 + x2) / 2f
                    path.cubicTo(x1 = cx, y1 = y1, x2 = cx, y2 = y2, x3 = x2, y3 = y2)
                } else {
                    path.cubicTo(x1, y1, x1, y1, (x1 + x2) / 2, (y1 + y2) / 2)
                    path.cubicTo((x1 + x2) / 2, (y1 + y2) / 2, x2, y2, x2, y2)
                }
            }

            xPositions.add(x1.toDouble())
        }
    }
    xPositions.add(_size.width.toDouble())


    return PathData(path = path, xPositions = xPositions,startIndex,endIndex)
}