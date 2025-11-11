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
import io.dcloud.uniapp.extapi.rpx2px as uni_rpx2px
open class GenPagesAPIRpx2pxRpx2px : BasePage {
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
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "item-k"), "输入:"),
                        createElementVNode("text", utsMapOf("class" to "item-v"), toDisplayString(_ctx.rpxValue) + "rpx", 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "item-k"), "返回:"),
                        createElementVNode("text", utsMapOf("class" to "item-v"), toDisplayString(_ctx.pxValue) + "px", 1)
                    ))
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("button", utsMapOf("id" to "convert", "onClick" to _ctx.rpx2px), "转换", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var rpxValue: Number by `$data`
    open var pxValue: Number by `$data`
    open var result: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "rpx2px", "rpxValue" to 750, "pxValue" to 0, "result" to false)
    }
    open var rpx2px = ::gen_rpx2px_fn
    open fun gen_rpx2px_fn() {
        this.pxValue = uni_rpx2px(this.rpxValue)
        val windowInfo = uni_getWindowInfo()
        if (windowInfo.windowWidth == this.pxValue) {
            this.result = true
        }
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "item" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "item-k" to padStyleMapOf(utsMapOf("width" to 72, "lineHeight" to 2)), "item-v" to padStyleMapOf(utsMapOf("fontWeight" to "bold", "lineHeight" to 2)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
