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
import io.dcloud.uniapp.extapi.`$emit` as uni__emit
import io.dcloud.uniapp.extapi.`$off` as uni__off
import io.dcloud.uniapp.extapi.`$on` as uni__on
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
open class GenPagesTemplateDropCardCardCard : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onMounted(fun() {
            screenWidth = uni_getWindowInfo().screenWidth
            screenHeight = uni_getWindowInfo().screenHeight
            setTimeout(fun(){
                this.setElementStyle("card", "height", screenHeight * 0.7 + "px")
                this.setElementStyle("card-img", "height", screenHeight * 0.7 + "px")
                this.initCardStyle()
            }
            , 200)
            uni__on("uni-drop-card-float", fun(){
                this.floatCount++
                this.initCardStyle()
            }
            )
        }
        , __ins)
        onUnmounted(fun() {
            uni__off("uni-drop-card-float", null)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "card", "ref" to "card", "onTouchstart" to fun(`$event`: Any){
            _ctx.touchstart(`$event` as TouchEvent)
        }
        , "onTouchmove" to fun(`$event`: Any){
            _ctx.touchmove(`$event` as TouchEvent)
        }
        , "onTouchend" to _ctx.touchend, "onTouchcancel" to _ctx.touchend), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "card-img", "ref" to "card-img", "src" to _ctx.img), null, 8, utsArrayOf(
                "src"
            )),
            createElementVNode("view", utsMapOf("class" to "state"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "state-icon like", "ref" to "state-icon-like", "src" to "/static/template/drop-card/like.png", "mode" to "widthFix"), null, 512),
                createElementVNode("image", utsMapOf("class" to "state-icon dislike", "ref" to "state-icon-dislike", "src" to "/static/template/drop-card/dislike.png", "mode" to "widthFix"), null, 512)
            ))
        ), 40, utsArrayOf(
            "onTouchstart",
            "onTouchmove",
            "onTouchend",
            "onTouchcancel"
        ))
    }
    open var img: String by `$props`
    open var cardIndex: Number by `$props`
    open var `$elementMap`: Map<String, UniElement> by `$data`
    open var x: Number by `$data`
    open var y: Number by `$data`
    open var floatCount: Number by `$data`
    open var movePercent: Number by `$data`
    open var likeOpacity: Number by `$data`
    open var dislikeOpacity: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("\$elementMap" to Map<String, UniElement>(), "x" to 0 as Number, "y" to 0 as Number, "floatCount" to 0 as Number, "movePercent" to computed<Number>(fun(): Number {
            return Math.abs(this.x) / (screenWidth / 2 * 3)
        }
        ), "likeOpacity" to computed<Number>(fun(): Number {
            return if (this.x < 0) {
                0
            } else {
                this.movePercent * 100
            }
        }
        ), "dislikeOpacity" to computed<Number>(fun(): Number {
            return if (this.x > 0) {
                0
            } else {
                this.movePercent * 100
            }
        }
        ))
    }
    open var initCardStyle = ::gen_initCardStyle_fn
    open fun gen_initCardStyle_fn() {
        var _index = (this.cardIndex + this.floatCount) % 3
        this.setElementStyle("card", "z-index", _index)
        this.setElementStyle("card", "margin-top", screenHeight * 0.15 - 30 * _index + "px")
        this.setElementStyle("card", "transform", "scale(" + (0.9 + 0.05 * _index) + ")")
    }
    open var setElementStyle = ::gen_setElementStyle_fn
    open fun gen_setElementStyle_fn(refName: String, propertyName: String, propertyStyle: Any): Unit {
        val elementMap = this.`$data`["\$elementMap"] as Map<String, UniElement>
        var element: UniElement? = elementMap.get(refName)
        if (element == null) {
            element = this.`$refs`[refName] as UniElement
            elementMap.set(refName, element)
        }
        element.style.setProperty(propertyName, propertyStyle)
    }
    open var touchstart = ::gen_touchstart_fn
    open fun gen_touchstart_fn(e: TouchEvent) {
        if (floating) {
            return
        }
        sX = e.touches[0].screenX
        sY = e.touches[0].screenY
        this.x = 0
        this.y = 0
        touchstartAfter = true
    }
    open var touchmove = ::gen_touchmove_fn
    open fun gen_touchmove_fn(e: TouchEvent) {
        if (!touchstartAfter || floating) {
            return
        }
        this.x += e.touches[0].screenX - sX
        this.y += e.touches[0].screenY - sY
        sX = e.touches[0].screenX
        sY = e.touches[0].screenY
        this.moveCard()
    }
    open var touchend = ::gen_touchend_fn
    open fun gen_touchend_fn() {
        touchstartAfter = false
        if (floating) {
            return
        }
        floating = true
        var k: Number = 0
        if (this.x > screenWidth / 10) {
            k = 1
        } else if (this.x < screenWidth * -1 / 10) {
            k = -1
        }
        val _this = this
        fun cardTo(x: Number, y: Number, callback: () -> Unit, speed: Number = 10) {
            var interval: Number = 0
            var acceleration: Number = 1
            interval = setInterval(fun(){
                acceleration += 0.2
                val dx = x - _this.x
                if (Math.abs(dx) < 1) {
                    _this.x = x
                } else {
                    _this.x += dx / speed * acceleration
                }
                val dy = y - _this.y
                if (Math.abs(dy) < 1) {
                    _this.y = y
                } else {
                    _this.y += dy / speed * acceleration
                }
                _this.moveCard()
                if (_this.x == x && _this.y == y) {
                    clearInterval(interval)
                    callback()
                }
            }
            , 16)
        }
        if (Math.floor(k) != 0) {
            cardTo(k * screenWidth * 1.3, this.y * 3, fun(){
                this.setElementStyle("state-icon-like", "opacity", 0)
                this.setElementStyle("state-icon-dislike", "opacity", 0)
                this.setElementStyle("card", "opacity", 0)
                setTimeout(fun(){
                    this.setElementStyle("card", "opacity", 1)
                }, 300)
                uni__emit("uni-drop-card-float", null)
                floating = false
            }, 8)
        } else {
            val _x: Number = this.x
            val _y: Number = this.y
            cardTo(Math.floor(_x * -0.05), Math.floor(_y * -0.05), fun(){
                cardTo(0, 0, fun(){
                    console.log("bounce", " at pages/template/drop-card/card/card.uvue:189")
                    floating = false
                }
                , 30)
            }
            )
        }
    }
    open var moveCard = ::gen_moveCard_fn
    open fun gen_moveCard_fn() {
        this.setElementStyle("card", "transform", "translate(" + this.x + "px," + this.y + "px) rotate(" + this.x / -30 + "deg) scale(1)")
        this.setElementStyle("state-icon-like", "opacity", if (this.x < 0) {
            0
        } else {
            this.movePercent * 10
        }
        )
        this.setElementStyle("state-icon-dislike", "opacity", if (this.x > 0) {
            0
        } else {
            this.movePercent * 10
        }
        )
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("card" to padStyleMapOf(utsMapOf("width" to "95%", "height" to 0, "position" to "absolute", "top" to 0, "marginTop" to 50, "marginRight" to 12, "marginBottom" to 0, "marginLeft" to 12, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "boxShadow" to "0 0 5px rgba(0, 0, 0, 0.1)", "backgroundColor" to "#FFFFFF", "transitionProperty" to "marginTop", "transitionDuration" to "300ms", "transitionTimingFunction" to "ease-in")), "card-img" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 0, "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10)), "state" to padStyleMapOf(utsMapOf("top" to 10, "left" to 10, "width" to "86%", "paddingTop" to 4, "paddingRight" to 4, "paddingBottom" to 4, "paddingLeft" to 4, "position" to "absolute", "flexDirection" to "row", "justifyContent" to "space-between")), "state-icon" to padStyleMapOf(utsMapOf("width" to 30, "height" to 30, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FFFFFF", "borderRightColor" to "#FFFFFF", "borderBottomColor" to "#FFFFFF", "borderLeftColor" to "#FFFFFF", "backgroundColor" to "#FFFFFF", "paddingTop" to 3, "paddingRight" to 3, "paddingBottom" to 3, "paddingLeft" to 3, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "boxShadow" to "0 0 1px #EBEBEB", "opacity" to 0)), "@TRANSITION" to utsMapOf("card" to utsMapOf("property" to "marginTop", "duration" to "300ms", "timingFunction" to "ease-in")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("img" to utsMapOf("type" to "String", "default" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-1.jpg"), "cardIndex" to utsMapOf("type" to "Number", "default" to 0)))
        var propsNeedCastKeys = utsArrayOf(
            "img",
            "cardIndex"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
