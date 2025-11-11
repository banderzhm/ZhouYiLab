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
import io.dcloud.uniapp.extapi.pageScrollTo as uni_pageScrollTo
open class GenPagesAPIPageScrollToPageScrollTo : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "scroll-with-animation" to "true"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("button", utsMapOf("type" to "default", "class" to "btn-scrollTo", "onClick" to _ctx.scrollTo), " scrollTo ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("type" to "default", "class" to "btn-scrollToElement", "onClick" to _ctx.scrollToElement), " scrollToElement ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode(Fragment, null, RenderHelpers.renderList(10, fun(_, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "uni-list", "key" to index), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell list-item"), toDisplayString(index), 1)
                    ))
                }
                ), 64),
                createElementVNode("view", utsMapOf("class" to "custom-element"), "scrollTo-custom-element"),
                createElementVNode(Fragment, null, RenderHelpers.renderList(10, fun(_, index2, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "uni-list", "key" to index2), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell list-item"), toDisplayString(index2), 1)
                    ))
                }
                ), 64)
            ))
        ), 4)
    }
    open var title: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "pageScrollTo")
    }
    open var scrollTo = ::gen_scrollTo_fn
    open fun gen_scrollTo_fn() {
        uni_pageScrollTo(PageScrollToOptions(scrollTop = 100, duration = 300, success = fun(_){
            console.log("success", " at pages/API/page-scroll-to/page-scroll-to.uvue:39")
        }
        ))
    }
    open var scrollToElement = ::gen_scrollToElement_fn
    open fun gen_scrollToElement_fn() {
        uni_pageScrollTo(PageScrollToOptions(selector = ".custom-element", duration = 300, success = fun(_){
            console.log("success", " at pages/API/page-scroll-to/page-scroll-to.uvue:48")
        }
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
                return utsMapOf("list-item" to padStyleMapOf(utsMapOf("height" to 100, "paddingLeft" to 30)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
