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
import io.dcloud.uniapp.extapi.createSelectorQuery as uni_createSelectorQuery
open class GenPagesAPICreateSelectorQueryCreateSelectorQueryOnScroll : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("onScroll" to _ctx.onScroll, "style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode(Fragment, null, RenderHelpers.renderList(60, fun(i, __key, __index, _cached): Any {
                return createElementVNode("view", utsMapOf("class" to "content-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "item-" + toDisplayString(i), 1)
                ))
            }
            ), 64)
        ), 44, utsArrayOf(
            "onScroll"
        ))
    }
    open var count: Number by `$data`
    open var ret: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("count" to 0, "ret" to true)
    }
    open var onScroll = ::gen_onScroll_fn
    open fun gen_onScroll_fn() {
        this.count++
        console.log("scroll: ", this.count, " at pages/API/create-selector-query/create-selector-query-onScroll.uvue:23")
        uni_createSelectorQuery().`in`(this).selectAll("content-item").boundingClientRect().exec(fun(result){
            this.count--
        }
        )
        console.log("scroll: ", this.count, " at pages/API/create-selector-query/create-selector-query-onScroll.uvue:31")
        if (this.count > 0) {
            this.ret = false
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
