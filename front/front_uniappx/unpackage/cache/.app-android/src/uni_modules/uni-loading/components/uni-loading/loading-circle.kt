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
open class GenUniModulesUniLoadingComponentsUniLoadingLoadingCircle : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onMounted(fun() {
            this.init()
        }
        , __ins)
        onUnmounted(fun() {
            clearInterval(this.timer)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("ref" to _ctx.elId, "class" to "block", "style" to normalizeStyle(utsMapOf("width" to (_ctx.size + "px"), "height" to (_ctx.size + "px")))), null, 4)
    }
    open var speed: Number by `$props`
    open var size: Number by `$props`
    open var color: String by `$props`
    open var elId: Any? by `$data`
    open var timer: Number by `$data`
    open var iconsSize: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        uni.UNI740BA18.elId += 1
        val elID = "Uni_Load_Circle_" + uni.UNI740BA18.elId
        return utsMapOf("elId" to elID, "timer" to 0, "iconsSize" to computed<Number>(fun(): Number {
            return (this.size / 10) + 1
        }
        ))
    }
    open var init = ::gen_init_fn
    open fun gen_init_fn() {
        val refs = this.`$refs`[this.elId] as UniElement
        var ctx = refs.getDrawableContext()!!
        this.build_circular(ctx)
    }
    open var build_circular = ::gen_build_circular_fn
    open fun gen_build_circular_fn(ctx: DrawableContext) {
        var startAngle: Number = 0
        var rotate: Number = 0
        val ARC_LENGTH: Number = 359
        val center = this.size / 2
        val lineWidth = this.size / 10
        val duration: Number = 1200
        val interval = this.speed
        val ARC_MAX: Number = 358
        var startTime: Number = 0
        var foreward_end: Number = 0
        var reversal_end = ARC_MAX
        fun pogress_time(): Number {
            val currentTime = Date.now()
            val elapsedTime = currentTime - startTime
            val progress = elapsedTime / duration
            val easedProgress = easeInOutCubic(progress)
            return easedProgress
        }
        val draw = fun(){
            ctx.reset()
            ctx.beginPath()
            if (reversal_end == ARC_MAX) {
                foreward_end = Math.min(pogress_time() * ARC_LENGTH, ARC_LENGTH)
                if (foreward_end >= ARC_MAX) {
                    reversal_end = 0
                    foreward_end = ARC_MAX
                    startTime = Date.now()
                }
            }
            if (foreward_end == ARC_MAX) {
                reversal_end = Math.min(pogress_time() * ARC_LENGTH, ARC_LENGTH)
                if (reversal_end >= ARC_MAX) {
                    reversal_end = ARC_MAX
                    foreward_end = 0
                    startTime = Date.now()
                }
            }
            ctx.arc(center, center, center - lineWidth, startAngle + rotate + (reversal_end * Math.PI / 180), startAngle + rotate + (foreward_end * Math.PI / 180))
            ctx.lineWidth = lineWidth
            val fillColor = (if (this.color !== "") {
                this.color
            } else {
                "#666"
            }
            ).toString()
            ctx.strokeStyle = fillColor
            ctx.stroke()
            ctx.update()
            rotate += 0.05
        }
        this.timer = setInterval(fun(){
            return draw()
        }
        , interval)
    }
    companion object {
        var name = "circle"
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("block" to padStyleMapOf(utsMapOf("width" to 50, "height" to 50)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("speed" to utsMapOf("type" to "Number", "default" to 16), "size" to utsMapOf("type" to "Number", "default" to 20), "color" to utsMapOf("type" to "String", "default" to "#666")))
        var propsNeedCastKeys = utsArrayOf(
            "speed",
            "size",
            "color"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
