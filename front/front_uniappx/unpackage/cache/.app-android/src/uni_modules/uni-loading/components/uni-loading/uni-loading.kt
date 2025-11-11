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
open class GenUniModulesUniLoadingComponentsUniLoadingUniLoading : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onCreated(fun() {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_LoadingCircle = resolveComponent("LoadingCircle")
        return createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
            "uni-loading-main",
            utsMapOf("load-inline" to (_ctx.`$slots`["default"] == null))
        ))), utsArrayOf(
            if (isTrue(_ctx.loading)) {
                createElementVNode(Fragment, utsMapOf("key" to 0), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "default"),
                    if (_ctx.`$slots`["default"] == null) {
                        createElementVNode(Fragment, utsMapOf("key" to 0), utsArrayOf(
                            createVNode(_component_LoadingCircle, utsMapOf("speed" to 16, "size" to _ctx.loadWidth, "color" to _ctx.color), null, 8, utsArrayOf(
                                "size",
                                "color"
                            )),
                            if (isTrue(_ctx.text)) {
                                createElementVNode("text", utsMapOf("key" to 0, "class" to "inline-text", "style" to normalizeStyle(utsMapOf("color" to _ctx.color))), toDisplayString(_ctx.text), 5)
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ), 64)
                    } else {
                        createElementVNode("view", utsMapOf("key" to 1, "class" to "uni-loading-mask", "style" to normalizeStyle(utsMapOf("backgroundColor" to _ctx.background))), utsArrayOf(
                            createVNode(_component_LoadingCircle, utsMapOf("speed" to 16, "size" to _ctx.loadWidth, "color" to _ctx.color), null, 8, utsArrayOf(
                                "size",
                                "color"
                            )),
                            if (isTrue(_ctx.text)) {
                                createElementVNode("text", utsMapOf("key" to 0, "class" to "block-text", "style" to normalizeStyle(utsMapOf("color" to _ctx.color))), toDisplayString(_ctx.text), 5)
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ), 4)
                    }
                ), 64)
            } else {
                renderSlot(_ctx.`$slots`, "default", utsMapOf("key" to 1))
            }
        ), 2)
    }
    open var loading: Boolean by `$props`
    open var type: String by `$props`
    open var iconType: String by `$props`
    open var size: Number by `$props`
    open var text: String by `$props`
    open var background: String by `$props`
    open var color: String by `$props`
    open var loadWidth: Number by `$data`
    open var styles: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("loadWidth" to computed<Number>(fun(): Number {
            var width = this.size
            if (width == 0) {
                return 20
            }
            return width
        }
        ), "styles" to computed<String>(fun(): String {
            return "width:" + this.loadWidth + "px;height:" + this.loadWidth + "px;"
        }
        ))
    }
    companion object {
        var name = "uni-loading"
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("uni-loading-main" to utsMapOf("" to utsMapOf("position" to "relative"), ".load-inline" to utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center")), "block-text" to padStyleMapOf(utsMapOf("marginTop" to 8, "fontSize" to 14)), "inline-text" to padStyleMapOf(utsMapOf("marginLeft" to 8, "fontSize" to 14)), "uni-loading-mask" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to "100%", "height" to "100%", "top" to 0, "left" to 0, "display" to "flex", "justifyContent" to "center", "alignItems" to "center", "backgroundColor" to "rgba(0,0,0,0.3)", "zIndex" to 2)), "uni-load" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center")), "load-text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#ffffff", "marginTop" to 12)), "image" to utsMapOf(".uni-load " to utsMapOf("width" to "100%", "height" to "100%")), "load-image" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%")), "load-ani" to padStyleMapOf(utsMapOf("transitionProperty" to "transform", "transitionDuration" to "0.1s", "transitionTimingFunction" to "linear", "transform" to "rotate(0deg)")), "@TRANSITION" to utsMapOf("load-ani" to utsMapOf("property" to "transform", "duration" to "0.1s", "timingFunction" to "linear")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("loading" to utsMapOf("type" to "Boolean", "default" to true), "type" to utsMapOf("type" to "String", "default" to ""), "iconType" to utsMapOf("type" to "String", "default" to "gear-filled"), "size" to utsMapOf("type" to "Number", "default" to 0), "text" to utsMapOf("type" to "String", "default" to ""), "background" to utsMapOf("type" to "String", "default" to "rgba(255,255,255,0.6)"), "color" to utsMapOf("type" to "String", "default" to "#333")))
        var propsNeedCastKeys = utsArrayOf(
            "loading",
            "type",
            "iconType",
            "size",
            "text",
            "background",
            "color"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf("LoadingCircle" to GenUniModulesUniLoadingComponentsUniLoadingLoadingCircleClass)
    }
}
