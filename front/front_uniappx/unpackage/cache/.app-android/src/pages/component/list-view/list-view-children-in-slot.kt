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
open class GenPagesComponentListViewListViewChildrenInSlot : BasePage {
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
        val _component_list_view_wrapper = resolveComponent("list-view-wrapper")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "padding-bottom" to "20px"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "getApp")),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createVNode(_component_list_view_wrapper, null, utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, __key, __index, _cached): Any {
                                return createElementVNode("list-item", utsMapOf("key" to item), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text-in-list-item"), toDisplayString(item), 1)
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "second" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, __key, __index, _cached): Any {
                                return createElementVNode("list-item", utsMapOf("key" to item), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text-in-list-item"), toDisplayString(item), 1)
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1)),
                    createElementVNode("button", utsMapOf("id" to "add-btn", "class" to "uni-common-mt", "onClick" to _ctx.addItem), "add item", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("button", utsMapOf("id" to "empty-btn", "class" to "uni-common-mt", "onClick" to _ctx.emptyList), "empty list", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ), 4)
    }
    open var list: UTSArray<Number> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("list" to utsArrayOf(
            0,
            1,
            2
        ))
    }
    open var addItem = ::gen_addItem_fn
    open fun gen_addItem_fn() {
        this.list.push(this.list.length)
    }
    open var emptyList = ::gen_emptyList_fn
    open fun gen_emptyList_fn() {
        this.list = utsArrayOf()
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
        var components: Map<String, CreateVueComponent> = utsMapOf("ListViewWrapper" to GenPagesComponentListViewListViewWrapperClass)
    }
}
