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
open class GenPagesCSSTransformRotate : BasePage {
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
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("class" to "page"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "trace"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "base reserve"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "rotate(20deg)"),
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "转变前位置")
                )),
                createElementVNode("view", utsMapOf("class" to "base reserve"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "rotateX(50deg)"),
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "转变前位置")
                )),
                createElementVNode("view", utsMapOf("class" to "base reserve"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "rotateY(50deg)"),
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "转变前位置")
                )),
                createElementVNode("view", utsMapOf("class" to "base reserve"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "rotateZ(50deg)"),
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "转变前位置")
                )),
                createElementVNode("view", utsMapOf("class" to "base reserve"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "rotateX(180deg)"),
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "转变前位置")
                )),
                createElementVNode("view", utsMapOf("class" to "base reserve"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "rotateY(180deg)"),
                    createElementVNode("text", utsMapOf("class" to "reserve-text"), "转变前位置")
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "base transform", "style" to normalizeStyle(utsMapOf("transform" to "rotate(20deg)"))), utsArrayOf(
                createElementVNode("text", null, "rotate(20deg)"),
                createElementVNode("text", null, "转变后位置")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "base transform", "style" to normalizeStyle(utsMapOf("transform" to "rotateX(50deg)"))), utsArrayOf(
                createElementVNode("text", null, "rotateX(50deg)"),
                createElementVNode("text", null, "转变后位置")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "base transform", "style" to normalizeStyle(utsMapOf("transform" to "rotateY(50deg)"))), utsArrayOf(
                createElementVNode("text", null, "rotateY(50deg)"),
                createElementVNode("text", null, "转变后位置")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "base transform", "style" to normalizeStyle(utsMapOf("transform" to "rotateZ(50deg)"))), utsArrayOf(
                createElementVNode("text", null, "rotateZ(50deg)"),
                createElementVNode("text", null, "转变后位置")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "base transform", "style" to normalizeStyle(utsMapOf("transform" to "rotateX(180deg)"))), utsArrayOf(
                createElementVNode("text", null, "rotateX(180deg)"),
                createElementVNode("text", null, "转变后位置")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "base transform", "style" to normalizeStyle(utsMapOf("transform" to "rotateY(180deg)"))), utsArrayOf(
                createElementVNode("text", null, "rotateY(180deg)"),
                createElementVNode("text", null, "转变后位置")
            ), 4)
        ))
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "alignItems" to "center")), "trace" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 0, "left" to 0, "width" to "100%", "alignItems" to "center")), "base" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10, "width" to 150, "height" to 150, "alignItems" to "center", "justifyContent" to "center")), "reserve" to padStyleMapOf(utsMapOf("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "dotted", "borderRightStyle" to "dotted", "borderBottomStyle" to "dotted", "borderLeftStyle" to "dotted", "borderTopColor" to "#558888", "borderRightColor" to "#558888", "borderBottomColor" to "#558888", "borderLeftColor" to "#558888", "backgroundColor" to "#dddddd")), "reserve-text" to padStyleMapOf(utsMapOf("color" to "#cccccc")), "transform" to padStyleMapOf(utsMapOf("borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#0000ff", "borderRightColor" to "#0000ff", "borderBottomColor" to "#0000ff", "borderLeftColor" to "#0000ff", "backgroundColor" to "rgba(0,255,255,0.5)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
