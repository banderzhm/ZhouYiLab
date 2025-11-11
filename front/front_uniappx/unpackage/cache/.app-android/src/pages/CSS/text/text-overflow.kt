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
open class GenPagesCSSTextTextOverflow : BasePage {
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
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "500px", "padding" to "0 30px", "background-color" to "gray", "justify-content" to "center"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "margin-bottom-10"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "font-weight-bold"), "text-overflow:clip white-space:nowrap"),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "clip", "white-space" to "nowrap"))), toDisplayString(_ctx.multiLineText), 5)
                )),
                createElementVNode("view", utsMapOf("class" to "margin-bottom-10"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "font-weight-bold"), "text-overflow:ellipsis white-space:nowrap"),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "ellipsis", "white-space" to "nowrap"))), toDisplayString(_ctx.singleLineText), 5),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "ellipsis", "white-space" to "nowrap"))), toDisplayString(_ctx.multiLineText), 5)
                )),
                createElementVNode("view", utsMapOf("class" to "margin-bottom-10"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "font-weight-bold"), "white-space:normal"),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("white-space" to "normal"))), toDisplayString(_ctx.multiLineText), 5)
                )),
                createElementVNode("view", utsMapOf("class" to "margin-bottom-10", "style" to normalizeStyle(utsMapOf("overflow" to "visible"))), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "font-weight-bold"), "white-space: nowrap"),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("white-space" to "nowrap", "align-self" to "flex-start"))), toDisplayString(_ctx.multiLineText), 5)
                ), 4),
                createElementVNode("view", utsMapOf("class" to "margin-bottom-10"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "font-weight-bold"), "任意宽度截断（100px、200px、300px）"),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "ellipsis", "white-space" to "nowrap", "width" to "100px"))), toDisplayString(_ctx.multiLineText), 5),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "ellipsis", "white-space" to "nowrap", "width" to "200px"))), toDisplayString(_ctx.multiLineText), 5),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "ellipsis", "white-space" to "nowrap", "width" to "300px"))), toDisplayString(_ctx.multiLineText), 5)
                )),
                createElementVNode("view", utsMapOf("class" to "margin-bottom-10"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "font-weight-bold"), "lines:1 text-overflow:ellipsis"),
                    createElementVNode("text", utsMapOf("class" to "font-size-20", "style" to normalizeStyle(utsMapOf("text-overflow" to "ellipsis", "lines" to "1"))), toDisplayString(_ctx.multiLineText), 5)
                ))
            ), 4)
        ), 4)
    }
    open var multiLineText: String by `$data`
    open var singleLineText: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("multiLineText" to "HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言", "singleLineText" to "uts，大一统语言（单行文本）")
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
                return utsMapOf("font-size-20" to padStyleMapOf(utsMapOf("fontSize" to 20)), "font-weight-bold" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "margin-bottom-10" to padStyleMapOf(utsMapOf("marginBottom" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
