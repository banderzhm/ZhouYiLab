@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNI740BA18
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
open class GenPagesAPIElementDrawElementDraw : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {
        onLoad(fun(_: OnLoadOptions) {
            stat_instance.onLoad(this)
        }
        , __ins)
        onPageShow(fun() {
            stat_instance.onShow(this)
        }
        , __ins)
        onPageHide(fun() {
            stat_instance.onHide(this)
        }
        , __ins)
        onUnload(fun() {
            stat_instance.onUnload(this)
        }
        , __ins)
        onPageShow(fun() {}, __ins)
        onReady(fun() {
            this.drawText()
            this.drawLines()
            this.drawCircles()
            this.drawStar()
            this.drawhouse()
            this.drawPoint()
            this.drawRect()
            this.drawArcTo()
        }
        , __ins)
        onUnload(fun() {
            y = 160
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-text-view")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-line-view")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-circle-view")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-dash-line")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-house")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-style")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-odd")),
                createElementVNode("view", utsMapOf("class" to "drawing", "id" to "draw-arcto"))
            ))
        ))
    }
    open var texts: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("texts" to utsArrayOf<String>("HBuilderX，轻巧、极速，极客编辑器", "uni-app x，终极跨平台方案", "uniCloud，js serverless云服务", "uts，大一统语言", "uniMPSdk，让你的App具备小程序能力", "uni-admin，开源、现成的全端管理后台", "uni-id，开源、全端的账户中心", "uni-pay，开源、云端一体、全平台的支付", "uni-ai，聚合ai能力", "uni-cms，开源、云端一体、全平台的内容管理平台", "uni-im，开源、云端一体、全平台的im即时消息", "uni统计，开源、完善、全平台的统计报表", "......"))
    }
    open var drawText = ::gen_drawText_fn
    open fun gen_drawText_fn() {
        var element = uni_getElementById("draw-text-view")!!
        var ctx = element.getDrawableContext()!!
        var width = element.getBoundingClientRect().width
        ctx.reset()
        ctx.font = "15px Arial"
        ctx.textAlign = "center"
        run {
            var i: Number = 0
            while(i < this.texts.length){
                var value = this.texts[i]
                if (i % 2 == 0) {
                    ctx.fillText(value, width / 2, (20 * (i + 1)))
                } else {
                    ctx.lineWidth = 0.5
                    ctx.strokeText(value, width / 2, (20 * (i + 1)))
                }
                i++
            }
        }
        ctx.update()
    }
    open var drawLines = ::gen_drawLines_fn
    open fun gen_drawLines_fn() {
        var ctx = uni_getElementById("draw-line-view")!!.getDrawableContext()!!
        ctx.reset()
        ctx.lineWidth = 10
        utsArrayOf(
            "round",
            "bevel",
            "miter"
        ).forEach(fun(join, i){
            ctx.lineJoin = join
            ctx.beginPath()
            ctx.moveTo(5, 10 + i * 40)
            ctx.lineTo(50, 50 + i * 40)
            ctx.lineTo(90, 10 + i * 40)
            ctx.lineTo(130, 50 + i * 40)
            ctx.lineTo(170, 10 + i * 40)
            ctx.stroke()
        }
        )
        ctx.lineWidth = 1
        var space: Number = 170
        ctx.strokeStyle = "#09f"
        ctx.beginPath()
        ctx.moveTo(10 + space, 10)
        ctx.lineTo(140 + space, 10)
        ctx.moveTo(10 + space, 140)
        ctx.lineTo(140 + space, 140)
        ctx.stroke()
        ctx.strokeStyle = "black"
        utsArrayOf(
            "butt",
            "round",
            "square"
        ).forEach(fun(lineCap, i){
            ctx.lineWidth = 15
            ctx.lineCap = lineCap
            ctx.beginPath()
            ctx.moveTo(25 + space + i * 50, 10)
            ctx.lineTo(25 + space + i * 50, 140)
            ctx.stroke()
        }
        )
        ctx.lineWidth = 1
        this.drawDashedLine(utsArrayOf(), ctx)
        this.drawDashedLine(utsArrayOf(
            2,
            2
        ), ctx)
        this.drawDashedLine(utsArrayOf(
            10,
            10
        ), ctx)
        this.drawDashedLine(utsArrayOf(
            20,
            5
        ), ctx)
        this.drawDashedLine(utsArrayOf(
            15,
            3,
            3,
            3
        ), ctx)
        this.drawDashedLine(utsArrayOf(
            20,
            3,
            3,
            3,
            3,
            3,
            3,
            3
        ), ctx)
        ctx.lineDashOffset = 18
        this.drawDashedLine(utsArrayOf(
            12,
            3,
            3
        ), ctx)
        ctx.lineDashOffset = 0
        ctx.setLineDash(utsArrayOf(
            0
        ))
        ctx.update()
    }
    open var drawDashedLine = ::gen_drawDashedLine_fn
    open fun gen_drawDashedLine_fn(pattern: UTSArray<Number>, ctx: DrawableContext) {
        ctx.beginPath()
        ctx.setLineDash(pattern)
        ctx.moveTo(0, y)
        ctx.lineTo(300, y)
        ctx.stroke()
        y += 15
    }
    open var drawCircles = ::gen_drawCircles_fn
    open fun gen_drawCircles_fn() {
        var ctx = uni_getElementById("draw-circle-view")!!.getDrawableContext()!!
        ctx.reset()
        run {
            var i: Number = 0
            while(i < 4){
                run {
                    var j: Number = 0
                    while(j < 3){
                        ctx.beginPath()
                        var x = 25 + j * 50
                        var y = 25 + i * 50
                        var radius: Number = 20
                        var startAngle: Number = 0
                        var endAngle = Math.PI + (Math.PI * j) / 2
                        var clockwise = if (i % 2 == 0) {
                            false
                        } else {
                            true
                        }
                        ctx.arc(x, y, radius, startAngle, endAngle, clockwise)
                        if (i > 1) {
                            ctx.fill()
                        } else {
                            ctx.stroke()
                        }
                        j++
                    }
                }
                i++
            }
        }
        ctx.update()
    }
    open var drawStar = ::gen_drawStar_fn
    open fun gen_drawStar_fn() {
        var ctx = uni_getElementById("draw-dash-line")!!.getDrawableContext()!!
        ctx.reset()
        ctx.beginPath()
        var horn: Number = 5
        var angle = (360 as Number) / horn
        var R: Number = 50
        var r: Number = 20
        var x: Number = 100
        var y: Number = 100
        run {
            var i: Number = 0
            while(i < horn){
                ctx.lineTo(Math.cos((18 + i * angle) / 180.0 * Math.PI) * R + x, -Math.sin((18 + i * angle) / 180.0 * Math.PI) * R + y)
                ctx.lineTo(Math.cos((54 + i * angle) / 180.0 * Math.PI) * r + x, -Math.sin((54 + i * angle) / 180.0 * Math.PI) * r + y)
                i++
            }
        }
        ctx.closePath()
        ctx.lineWidth = 3
        ctx.fillStyle = "#E4EF00"
        ctx.strokeStyle = "red"
        ctx.fill()
        ctx.stroke()
        ctx.lineWidth = 10
        ctx.beginPath()
        ctx.moveTo(170, 100)
        ctx.lineTo(255, 15)
        ctx.lineTo(340, 100)
        ctx.closePath()
        ctx.fill()
        ctx.strokeStyle = "blue"
        ctx.stroke()
        ctx.beginPath()
        ctx.moveTo(170, 145)
        ctx.lineTo(255, 45)
        ctx.lineTo(340, 145)
        ctx.closePath()
        ctx.fill()
        ctx.strokeStyle = "gray"
        ctx.stroke()
        ctx.moveTo(170, 190)
        ctx.lineTo(255, 90)
        ctx.lineTo(340, 190)
        ctx.closePath()
        ctx.fillStyle = "orange"
        ctx.fill()
        ctx.strokeStyle = "khaki"
        ctx.stroke()
        ctx.update()
    }
    open var hex = ::gen_hex_fn
    open fun gen_hex_fn(reassignedNum: Number): String {
        var num = reassignedNum
        if (num == 0) {
            return "00"
        }
        var hexChars = "0123456789ABCDEF"
        var result = ""
        while(num > 0){
            var remainder = Math.floor(num) % 16
            result = hexChars[remainder] + result
            num = Math.floor(Math.floor(num) / 16)
        }
        if (result.length == 1) {
            return "0" + result
        }
        return result
    }
    open var drawhouse = ::gen_drawhouse_fn
    open fun gen_drawhouse_fn() {
        var ctx = uni_getElementById("draw-house")!!.getDrawableContext()!!
        ctx.reset()
        ctx.lineWidth = 10
        ctx.strokeRect(75, 140, 150, 110)
        ctx.fillRect(130, 190, 40, 60)
        ctx.beginPath()
        ctx.moveTo(50, 140)
        ctx.lineTo(150, 60)
        ctx.lineTo(250, 140)
        ctx.closePath()
        ctx.stroke()
        ctx.update()
    }
    open var drawPoint = ::gen_drawPoint_fn
    open fun gen_drawPoint_fn() {
        var ctx = uni_getElementById("draw-style")!!.getDrawableContext()!!
        ctx.reset()
        run {
            var i: Number = 0
            while(i < 6){
                run {
                    var j: Number = 0
                    while(j < 6){
                        ctx.strokeStyle = "rgb(0," + Math.floor(255 - 42.5 * i) + "," + Math.floor(255 - 42.5 * j) + ")"
                        ctx.beginPath()
                        ctx.arc(12.5 + j * 25, 12.5 + i * 25, 10, 0, Math.PI * 2, true)
                        ctx.stroke()
                        j++
                    }
                }
                i++
            }
        }
        run {
            var i: Number = 0
            while(i < 6){
                run {
                    var j: Number = 0
                    while(j < 6){
                        ctx.fillStyle = "rgb(" + Math.floor(255 - 42.5 * i) + "," + Math.floor(255 - 42.5 * j) + ",0)"
                        ctx.fillRect(180 + j * 25, i * 25, 25, 25)
                        j++
                    }
                }
                i++
            }
        }
        ctx.update()
    }
    open var drawRect = ::gen_drawRect_fn
    open fun gen_drawRect_fn() {
        var ctx = uni_getElementById("draw-odd")!!.getDrawableContext()!!
        ctx.reset()
        ctx.moveTo(30, 90)
        ctx.lineTo(110, 20)
        ctx.lineTo(240, 130)
        ctx.lineTo(60, 130)
        ctx.lineTo(190, 20)
        ctx.lineTo(270, 90)
        ctx.closePath()
        ctx.fillStyle = "green"
        ctx.fill("evenodd")
        ctx.update()
    }
    open var drawArcTo = ::gen_drawArcTo_fn
    open fun gen_drawArcTo_fn() {
        var ctx = uni_getElementById("draw-arcto")!!.getDrawableContext()!!
        ctx.reset()
        ctx.beginPath()
        ctx.moveTo(50, 20)
        ctx.bezierCurveTo(230, 30, 150, 60, 50, 100)
        ctx.stroke()
        ctx.fillStyle = "blue"
        ctx.fillRect(50, 20, 10, 10)
        ctx.fillRect(50, 100, 10, 10)
        ctx.fillStyle = "red"
        ctx.fillRect(230, 30, 10, 10)
        ctx.fillRect(150, 70, 10, 10)
        ctx.update()
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("drawing" to padStyleMapOf(utsMapOf("height" to 275, "backgroundColor" to "#D3D3D3", "marginBottom" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
