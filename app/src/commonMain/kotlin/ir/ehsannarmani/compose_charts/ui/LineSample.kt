package ir.ehsannarmani.compose_charts.ui

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.extensions.format
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DotProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.LineProperties
import ir.ehsannarmani.compose_charts.models.PopupProperties
import ir.ehsannarmani.compose_charts.models.StrokeStyle

val gridProperties = GridProperties(
    xAxisProperties = GridProperties.AxisProperties(
        thickness = .2.dp,
        color = SolidColor(Color.Gray.copy(alpha = .5f)),
        style = StrokeStyle.Dashed(intervals = floatArrayOf(15f, 15f), phase = 10f),
    ),
    yAxisProperties = GridProperties.AxisProperties(
        thickness = .2.dp,
        color = SolidColor(Color.Gray.copy(alpha = .5f)),
        style = StrokeStyle.Dashed(intervals = floatArrayOf(15f, 15f), phase = 10f),
    ),
)
val dividerProperties = DividerProperties(
    xAxisProperties = LineProperties(
        thickness = .2.dp,
        color = SolidColor(Color.Gray.copy(alpha = .5f)),
        style = StrokeStyle.Dashed(intervals = floatArrayOf(15f, 15f), phase = 10f),
    ),
    yAxisProperties = LineProperties(
        thickness = .2.dp,
        color = SolidColor(Color.Gray.copy(alpha = .5f)),
        style = StrokeStyle.Dashed(intervals = floatArrayOf(15f, 15f), phase = 10f),
    )
)

@Composable
fun RowScope.LineSample() {
    val popupProperties = PopupProperties(
        textStyle = TextStyle(
            fontSize = 11.sp,
            color = Color.White,
            fontFamily = ubuntu
        ),
        contentBuilder = { dataIndex, valueIndex, value ->
            value.format(1) + " Million, lineIndex: $dataIndex, valueIndex: $valueIndex"
        },
        containerColor = Color(0xff414141)
    )
    val data = remember {
        listOf(
            Line(
                label = "Windows",
                valuesY = listOf(
                    75.0,
                    5.0,
                    70.0,
                    85.0,
                    0.0
                ),
                color = SolidColor(Color(0xFF2B8130)),
                firstGradientFillColor = Color(0xFF66BB6A).copy(alpha = .4f),
                secondGradientFillColor = Color.Transparent,
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(.5.dp),
                curvedEdges = true,
                popupProperties = popupProperties
            ),
            Line(
                label = "Linux",
                valuesY = listOf(
                    1.0,
                    19.0,
                    22.0,
                    0.0,
                    5.0
                ),
                color = SolidColor(Color(0xFFDA860C)),
                firstGradientFillColor = Color(0xFFFFA726).copy(alpha = .4f),
                secondGradientFillColor = Color.Transparent,
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(.5.dp),
                popupProperties = popupProperties.copy(enabled = false)
            ),
            Line(
                label = "MacOS",
                valuesY = listOf(
                    4.0,
                    40.0,
                    58.0,
                    38.0,
                    22.0
                ),
                color = SolidColor(Color(0xFF0F73C4)),
                firstGradientFillColor = Color(0xFF42A5F5).copy(alpha = .4f),
                secondGradientFillColor = Color.Transparent,
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(.5.dp),
                curvedEdges = true,
                popupProperties = popupProperties
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White,
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    },
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = false
            )
        }
    }
}

@Composable
fun RowScope.LineSampleFoxcoa() {

    val data = listOf(
        Line(
            label = "",
            valuesY = listOf(28.0, 41.0, 5.0, 10.0, 35.0),
            color = SolidColor(Color.White),
            firstGradientFillColor = Color.White.copy(alpha = .5f),
            secondGradientFillColor = Color.Transparent,
            strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
            gradientAnimationDelay = 1000,
            drawStyle = DrawStyle.Stroke(width = 2.dp),
            popupProperties = PopupProperties(
                containerColor = Color.White,
                contentHorizontalPadding = 20.dp,
                contentVerticalPadding = 20.dp,
                duration = 5000,
            ),
        )
    )
    Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
        LineChart(
            modifier = Modifier
                .wrapContentWidth()
                .height(300.dp)
                .padding(top = 100.dp),
            data = remember {
                data
            },
            labelHelperProperties = LabelHelperProperties(enabled = false),
            indicatorProperties = HorizontalIndicatorProperties(enabled = false),
            gridProperties = GridProperties(
                xAxisProperties = GridProperties.AxisProperties(
                    color = SolidColor(Color.White)
                ),
                yAxisProperties = GridProperties.AxisProperties(
                    color = SolidColor(Color.White)
                ),
            ),
            popupProperties = PopupProperties(
                enabled = true
            )
        )
    }
}

@Composable
fun RowScope.LineSample3() {
    val data = remember {
        listOf(
            Line(
                label = "Windows",
                valuesY = listOf(
                    88.0,
                    56.0,
                    70.0,
                    45.0,
                    26.0
                ),
                color = SolidColor(Color(0xffF7B731)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
                curvedEdges = false,
                dotProperties = DotProperties(
                    enabled = true,
                    color = SolidColor(Color(0xffE1E2EC)),
                    strokeWidth = 1.dp,
                    strokeColor = SolidColor(Color(0xffF7B731)),
                )
            ),
            Line(
                label = "Linux",
                valuesY = listOf(
                    30.0,
                    70.0,
                    45.0,
                    65.0,
                    17.0
                ),
                color = SolidColor(Color(0xff0FB9B1)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
                curvedEdges = false,
                dotProperties = DotProperties(
                    enabled = true,
                    color = SolidColor(Color(0xffE1E2EC)),
                    strokeWidth = 2.dp,
                    strokeColor = SolidColor(Color(0xff0FB9B1)),
                )
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        color = Color.White,
                        fontFamily = ubuntu
                    ),
                    mode = PopupProperties.Mode.PointMode(),
                    contentBuilder = { dataIndex, valueIndex, value ->
                        value.format(1) + " Million" + " - dataIdx: " + dataIndex + ", valueIdx: " + valueIndex
                    },
                    containerColor = Color(0xff414141)
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    }
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = false,
                labelProperties = LabelProperties(
                    enabled = true,
                    labels = listOf("Jan", "Feb", "Mar", "Apr", "May"),
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                ),
            )
        }
    }
}

@Composable
fun RowScope.LineSample4() {
    val data = remember {
        listOf(
            Line(
                label = "Windows",
                valuesY = MutableList(5) { (0..100).random().toDouble() },
                color = SolidColor(Color(0xFF2B8130)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
                curvedEdges = true,
            ),
            Line(
                label = "Linux",
                valuesY = MutableList(5) { (0..100).random().toDouble() },
                color = SolidColor(Color(0xFFE65100)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
            ),
            Line(
                label = "Android",
                valuesY = MutableList(5) { (0..100).random().toDouble() },
                color = SolidColor(Color(0xFFB71C1C)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        color = Color.White,
                        fontFamily = ubuntu,
                    ),
                    contentBuilder = { _, _, value ->
                        value.format(1) + " Million"
                    },
                    containerColor = Color(0xff414141)
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    }
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = true
            )
        }
    }
}

@Composable
fun RowScope.LineSample5() {
    val data = remember {
        listOf(
            Line(
                label = "Windows",
                valuesY = MutableList(5) { (0..100).random().toDouble() },
                color = SolidColor(Color(0xFFF7B731)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
            ),
            Line(
                label = "Linux",
                valuesY = MutableList(5) { (0..100).random().toDouble() },
                color = SolidColor(Color(0xFF0FB9B1)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(),
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White,
                    ),
                    contentBuilder = { _, _, value ->
                        value.format(1) + " Million"
                    },
                    containerColor = Color(0xff414141)
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    }
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = false,
                labelProperties = LabelProperties(
                    enabled = true,
                    labels = listOf("Jan", "Feb", "Mar", "Apr", "May"),
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                ),
            )
        }
    }
}

@Composable
fun RowScope.LineSample6() {
    val data = remember {
        listOf(
            Line(
                label = "Windows",
                valuesY = listOf(
                    67.0,
                    0.0,
                    88.0,
                    90.0,
                    95.0
                ),
                color = SolidColor(Color(0xFFFB8231)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(
                    width = 3.dp,
                    strokeStyle = StrokeStyle.Dashed(
                        intervals = floatArrayOf(10f, 10f),
                        phase = 15f
                    )
                ),
                dotProperties = DotProperties(
                    enabled = true,
                    color = SolidColor(Color(0xFFFB8231)),
                    strokeWidth = 2.dp,
                    radius = 3.dp,
                    strokeColor = SolidColor(Color(0xffffffff)),
                )
            ),
            Line(
                label = "Linux",
                valuesY = listOf(
                    98.0,
                    67.0,
                    15.0,
                    20.0,
                    75.0
                ),
                color = SolidColor(Color(0xff23AF92)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(
                    width = 3.dp,
                    strokeStyle = StrokeStyle.Dashed(
                        intervals = floatArrayOf(10f, 10f),
                        phase = 15f
                    )
                ),
                dotProperties = DotProperties(
                    enabled = true,
                    color = SolidColor(Color(0xff23AF92)),
                    strokeWidth = 2.dp,
                    radius = 3.dp,
                    strokeColor = SolidColor(Color(0xffffffff)),
                ),
                popupProperties = PopupProperties(enabled = false)
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = { _, _, value ->
                        value.format(1) + " Million"
                    },
                    containerColor = Color(0xff414141)
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    }
                ),
                labelProperties = LabelProperties(
                    enabled = true,
                    labels = listOf("Jan", "Feb", "Mar", "Apr", "May"),
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = true
            )
        }
    }
}

@Composable
fun RowScope.LineSample7() {
    val data = remember {
        listOf(
            Line(
                label = "Linux",
                valuesY = listOf(
                    71.0,
                    0.0,
                    100.0,
                    50.0,
                    50.0
                ),
                color = Brush.radialGradient(
                    listOf(
                        Color(0xFFFFB300),
                        Color(0xFFD81B60)
                    )
                ),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        color = Color.White,
                        fontFamily = ubuntu,
                    ),
                    contentBuilder = { _, _, value ->
                        value.format(1) + " Million"
                    },
                    containerColor = Color(0xff414141),
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    }
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = true
            )
        }
    }
}

@Composable
fun RowScope.LineSample8() {
    val data = remember {
        listOf(
            Line(
                label = "Linux",
                valuesY = listOf(
                    10.0,
                    20.0,
                    7.0,
                    35.0,
                    20.0
                ),
                color = SolidColor(Color(0xff5A47CF)),
                firstGradientFillColor = Color(0xff6655CF).copy(alpha = .5f),
                secondGradientFillColor = Color.Transparent,
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Stroke(3.dp)
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize(),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                dividerProperties = DividerProperties(enabled = false),
                gridProperties = GridProperties(enabled = false),
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        color = Color.White,
                        fontFamily = ubuntu,
                    ),
                    contentBuilder = { _, _, value ->
                        value.format(1) + " Million"
                    },
                    containerColor = Color(0xff414141)
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    enabled = false
                ),
                labelProperties = LabelProperties(enabled = false),
                labelHelperProperties = LabelHelperProperties(enabled = false),
                curvedEdges = true
            )
        }
    }
}

@Composable
fun RowScope.LineSample9() {
    val data = remember {
        listOf(
            Line(
                label = "Windows",
                valuesY = MutableList(5) { (0..100).random().toDouble() },
                color = SolidColor(Color(0xFFfd9644)),
                strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                gradientAnimationDelay = 1000,
                drawStyle = DrawStyle.Fill,
                curvedEdges = true
            ),
        )
    }
    Card(
        modifier = Modifier.height(270.dp).fillMaxWidth().weight(1f)
            .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xff2D2D2D)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(vertical = 12.dp)) {
            LineChart(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 22.dp),
                data = data,
                animationMode = AnimationMode.Together(delayBuilder = {
                    it * 500L
                }),
                gridProperties = gridProperties,
                dividerProperties = dividerProperties,
                popupProperties = PopupProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        color = Color.White,
                        fontFamily = ubuntu,
                    ),
                    contentBuilder = { _, _, value ->
                        value.format(1) + " Million"
                    },
                    containerColor = Color(0xff414141)
                ),
                indicatorProperties = HorizontalIndicatorProperties(
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontFamily = ubuntu, color = Color.White
                    ),
                    contentBuilder = {
                        it.format(1) + " M"
                    }
                ),
                labelHelperProperties = LabelHelperProperties(
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = ubuntu,
                        color = Color.White
                    )
                ),
                curvedEdges = false
            )
        }
    }
}
