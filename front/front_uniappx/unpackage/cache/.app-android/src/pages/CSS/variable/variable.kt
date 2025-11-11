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
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
open class GenPagesCSSVariableVariable : BasePage {
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
        onReady(fun() {
            this.statusBarHeight = uni_getWindowInfo().statusBarHeight
            this.statusBarHeight2 = this.`$page`.statusBarHeight
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "status-bar-height"), utsArrayOf(
                createElementVNode("text", null, "通过var(--status-bar-height)获取状态栏高度")
            )),
            createElementVNode("view", utsMapOf("class" to "status-bar-window", "style" to normalizeStyle(utsMapOf("height" to (_ctx.statusBarHeight + "px")))), utsArrayOf(
                createElementVNode("text", null, "通过uni.getWindowInfo获取状态栏高度")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "status-bar-unipage", "style" to normalizeStyle(utsMapOf("height" to (_ctx.statusBarHeight2 + "px")))), utsArrayOf(
                createElementVNode("text", null, "通过this.\$page.statusBarHeight获取状态栏高度")
            ), 4),
            createElementVNode("view", utsMapOf("class" to "window-top"), utsArrayOf(
                createElementVNode("text", null, "window top")
            )),
            createElementVNode("view", utsMapOf("class" to "window-bottom"), utsArrayOf(
                createElementVNode("text", null, "window bottom")
            )),
            createElementVNode("view", utsMapOf("class" to "uni-safe-area-inset-top"), utsArrayOf(
                createElementVNode("text", null, "height:var(--uni-safe-area-inset-top)")
            )),
            createElementVNode("view", utsMapOf("class" to "uni-safe-area-inset-left"), utsArrayOf(
                createElementVNode("text", null, "height:var(--uni-safe-area-inset-left)")
            )),
            createElementVNode("view", utsMapOf("class" to "uni-safe-area-inset-right"), utsArrayOf(
                createElementVNode("text", null, "height:var(--uni-safe-area-inset-right)")
            )),
            createElementVNode("view", utsMapOf("class" to "uni-safe-area-inset-bottom"), utsArrayOf(
                createElementVNode("text", null, "height:var(--uni-safe-area-inset-bottom)")
            )),
            createElementVNode("view", utsMapOf("class" to "uni-fixed-bottom"), utsArrayOf(
                createElementVNode("text", null, "此区域应显示在安全区域内")
            ))
        ))
    }
    open var statusBarHeight: Number by `$data`
    open var statusBarHeight2: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("statusBarHeight" to 0, "statusBarHeight2" to 0)
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1)), "status-bar-height" to padStyleMapOf(utsMapOf("height" to CSS_VAR_STATUS_BAR_HEIGHT, "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#FF0000")), "status-bar-window" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFF00", "alignItems" to "center", "justifyContent" to "center")), "status-bar-unipage" to padStyleMapOf(utsMapOf("alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#ADFF2F")), "window-top" to padStyleMapOf(utsMapOf("height" to CSS_VAR_WINDOW_TOP, "alignItems" to "center", "backgroundColor" to "#008000", "marginTop" to 2, "marginRight" to 0, "marginBottom" to 2, "marginLeft" to 0)), "window-bottom" to padStyleMapOf(utsMapOf("height" to CSS_VAR_WINDOW_BOTTOM, "alignItems" to "center", "backgroundColor" to "#0000FF", "marginTop" to 2, "marginRight" to 0, "marginBottom" to 2, "marginLeft" to 0)), "uni-safe-area-inset-top" to padStyleMapOf(utsMapOf("height" to "var(--uni-safe-area-inset-top)", "alignItems" to "center", "backgroundColor" to "#FFFF00", "marginTop" to 2, "marginRight" to 0, "marginBottom" to 2, "marginLeft" to 0)), "uni-safe-area-inset-left" to padStyleMapOf(utsMapOf("height" to "var(--uni-safe-area-inset-left)", "alignItems" to "center", "backgroundColor" to "#ADFF2F", "marginTop" to 2, "marginRight" to 0, "marginBottom" to 2, "marginLeft" to 0)), "uni-safe-area-inset-right" to padStyleMapOf(utsMapOf("height" to "var(--uni-safe-area-inset-right, 60px)", "alignItems" to "center", "backgroundColor" to "#8B4513", "marginTop" to 2, "marginRight" to 0, "marginBottom" to 2, "marginLeft" to 0)), "uni-safe-area-inset-bottom" to padStyleMapOf(utsMapOf("height" to "var(--uni-safe-area-inset-bottom)", "alignItems" to "center", "backgroundColor" to "#FA8072", "marginTop" to 2, "marginRight" to 0, "marginBottom" to 2, "marginLeft" to 0)), "uni-fixed-bottom" to padStyleMapOf(utsMapOf("position" to "fixed", "left" to "var(--uni-safe-area-inset-left)", "right" to "var(--uni-safe-area-inset-right)", "bottom" to "var(--uni-safe-area-inset-bottom)", "alignItems" to "center", "backgroundColor" to "#8A2BE2")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
