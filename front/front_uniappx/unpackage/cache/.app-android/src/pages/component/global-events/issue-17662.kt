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
open class GenPagesComponentGlobalEventsIssue17662 : BasePage {
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
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("list-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "scroll-top" to _ctx.scrollTop), utsArrayOf(
            createElementVNode(Fragment, null, RenderHelpers.renderList(50, fun(item, __key, __index, _cached): Any {
                return createElementVNode("list-item", utsMapOf("key" to item, "onLongpress" to _ctx.longpress_item, "style" to normalizeStyle(utsMapOf("border-bottom-style" to "solid", "border-bottom-width" to "1px", "border-bottom-color" to "black"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100%", "height" to "40px", "background-color" to "antiquewhite"))), utsArrayOf(
                        createElementVNode("text", utsMapOf("onLongpress" to _ctx.longpress_text, "style" to normalizeStyle(utsMapOf("font-size" to "10px", "width" to "100px", "height" to "100%", "background-color" to "red"))), toDisplayString(item), 45, utsArrayOf(
                            "onLongpress"
                        ))
                    ), 4)
                ), 44, utsArrayOf(
                    "onLongpress"
                ))
            }
            ), 64)
        ), 12, utsArrayOf(
            "scroll-top"
        ))
    }
    open var longpressItem: Boolean by `$data`
    open var longpressText: Boolean by `$data`
    open var isStopPropagation: Boolean by `$data`
    open var scrollTop: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("longpressItem" to false, "longpressText" to false, "isStopPropagation" to false, "scrollTop" to 0)
    }
    open var longpress_item = ::gen_longpress_item_fn
    open fun gen_longpress_item_fn(_e: UniTouchEvent) {
        console.log("longpress listItem", " at pages/component/global-events/issue-17662.uvue:25")
        this.longpressItem = true
    }
    open var longpress_text = ::gen_longpress_text_fn
    open fun gen_longpress_text_fn(e: UniTouchEvent) {
        console.log("longpress text", " at pages/component/global-events/issue-17662.uvue:29")
        this.longpressText = true
        if (this.isStopPropagation) {
            e.stopPropagation()
        }
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
