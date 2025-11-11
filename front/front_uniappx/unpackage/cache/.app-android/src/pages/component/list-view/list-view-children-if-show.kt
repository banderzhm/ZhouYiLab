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
open class GenPagesComponentListViewListViewChildrenIfShow : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            this.init()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "p-10"), utsArrayOf(
            createElementVNode("button", utsMapOf("id" to "init-btn", "class" to "uni-btn", "onClick" to _ctx.init), "init", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("id" to "clear-btn", "class" to "uni-btn", "onClick" to _ctx.clear), "clear", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                "uni-common-mt list-view-container",
                utsMapOf(" p-10" to (_ctx.list.length > 0))
            ))), utsArrayOf(
                createElementVNode("list-view", null, utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, index, __index, _cached): Any {
                        return createElementVNode(Fragment, null, utsArrayOf(
                            createElementVNode("list-item", utsMapOf("id" to "toggle-children-show-btn", "class" to normalizeClass(utsMapOf("uni-common-mt" to (index > 0))), "onClick" to fun(){
                                _ctx.toggleChildrenShow(item.id)
                            }
                            ), utsArrayOf(
                                createElementVNode("text", null, "toggle children isShow")
                            ), 10, utsArrayOf(
                                "onClick"
                            )),
                            if (isTrue(_ctx.expandIds.includes(item.id))) {
                                createElementVNode(Fragment, utsMapOf("key" to 0), RenderHelpers.renderList(item.children, fun(child, __key, __index, _cached): Any {
                                    return createElementVNode("list-item", utsMapOf("id" to "list-item-child", "class" to "mt-5"), utsArrayOf(
                                        createElementVNode("text", null, toDisplayString(child.id), 1)
                                    ))
                                }), 256)
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ), 64)
                    }
                    ), 256)
                ))
            ), 2)
        ))
    }
    open var list: UTSArray<List> by `$data`
    open var expandIds: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("list" to utsArrayOf<List>(), "expandIds" to utsArrayOf<String>())
    }
    open var init = ::gen_init_fn
    open fun gen_init_fn() {
        this.list = utsArrayOf<List>(List(id = "1", children = utsArrayOf(
            Child(id = "1-1"),
            Child(id = "1-2"),
            Child(id = "1-3")
        )), List(id = "2", children = utsArrayOf(
            Child(id = "2-1"),
            Child(id = "2-2"),
            Child(id = "2-3")
        )), List(id = "3", children = utsArrayOf(
            Child(id = "3-1"),
            Child(id = "3-2"),
            Child(id = "3-3")
        )))
        this.expandIds = utsArrayOf(
            this.list[0].id
        )
    }
    open var clear = ::gen_clear_fn
    open fun gen_clear_fn() {
        this.list = utsArrayOf<List>()
        this.expandIds = utsArrayOf<String>()
    }
    open var toggleChildrenShow = ::gen_toggleChildrenShow_fn
    open fun gen_toggleChildrenShow_fn(id: String) {
        val index = this.expandIds.findIndex(fun(item): Boolean {
            return item == id
        }
        )
        if (index == -1) {
            this.expandIds.push(id)
        } else {
            this.expandIds.splice(index, 1)
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
                return utsMapOf("p-10" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "list-view-container" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff")), "mt-5" to padStyleMapOf(utsMapOf("marginTop" to 5)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
