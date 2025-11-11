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
open class GenPagesComponentStickyHeaderStickyHeader : BasePage {
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
            this.loadListData()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("list-view", utsMapOf("scroll-y" to true, "class" to "page", "bounces" to "false", "show-scrollbar" to "false", "scroll-top" to _ctx.scroll_top_input, "refresher-enabled" to _ctx.refresher_enabled_boolean, "refresher-triggered" to _ctx.refresher_triggered_boolean, "onRefresherrefresh" to _ctx.list_view_refresherrefresh), utsArrayOf(
            createElementVNode("list-item", utsMapOf("type" to "1"), utsArrayOf(
                createElementVNode("swiper", utsMapOf("indicator-dots" to "true", "circular" to "true", "style" to normalizeStyle(utsMapOf("height" to "240px"))), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(3, fun(i, __key, __index, _cached): Any {
                        return createElementVNode("swiper-item", utsMapOf("item-id" to (i + "")), utsArrayOf(
                            createElementVNode("image", utsMapOf("src" to "/static/shuijiao.jpg", "style" to normalizeStyle(utsMapOf("height" to "240px", "width" to "100%"))), null, 4),
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("position" to "absolute"))), toDisplayString(i), 5)
                        ), 8, utsArrayOf(
                            "item-id"
                        ))
                    }
                    ), 64)
                ), 4)
            )),
            createElementVNode("list-item", utsMapOf("class" to "content-item", "type" to "2"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "text"), "向上滑动页面，体验sticky-header吸顶效果。")
            )),
            createElementVNode("sticky-section", null, utsArrayOf(
                createElementVNode("sticky-header", null, utsArrayOf(
                    createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("background-color" to "#f5f5f5", "flex-direction" to "row")), "direction" to "horizontal", "show-scrollbar" to false), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("align-self" to "flex-start", "flex-direction" to "row"))), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.sift_item, fun(name, index, __index, _cached): Any {
                                return createElementVNode("text", utsMapOf("ref_for" to true, "ref" to "swipertab", "class" to "sift-item", "onClick" to fun(){
                                    _ctx.clickTH(index)
                                }
                                ), toDisplayString(name), 9, utsArrayOf(
                                    "onClick"
                                ))
                            }
                            ), 256)
                        ), 4)
                    ), 4)
                )),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list_item, fun(item, index, __index, _cached): Any {
                    return createElementVNode("list-item", utsMapOf("key" to index, "class" to "content-item", "type" to "3"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(item), 1)
                    ))
                }
                ), 128)
            ))
        ), 40, utsArrayOf(
            "scroll-top",
            "refresher-enabled",
            "refresher-triggered",
            "onRefresherrefresh"
        ))
    }
    open var sift_item: UTSArray<String> by `$data`
    open var list_item: UTSArray<String> by `$data`
    open var refresher_enabled_boolean: Boolean by `$data`
    open var refresher_triggered_boolean: Boolean by `$data`
    open var scroll_top_input: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("sift_item" to utsArrayOf(
            "排序",
            "筛选"
        ), "list_item" to utsArrayOf<String>(), "refresher_enabled_boolean" to true, "refresher_triggered_boolean" to false, "scroll_top_input" to 0)
    }
    open var list_view_refresherrefresh = ::gen_list_view_refresherrefresh_fn
    open fun gen_list_view_refresherrefresh_fn() {
        console.log("下拉刷新被触发 ", " at pages/component/sticky-header/sticky-header.uvue:51")
        this.refresher_triggered_boolean = true
        setTimeout(fun(){
            this.refresher_triggered_boolean = false
        }
        , 1500)
    }
    open var confirm_scroll_top_input = ::gen_confirm_scroll_top_input_fn
    open fun gen_confirm_scroll_top_input_fn(value: Number) {
        this.scroll_top_input = value
    }
    open var clickTH = ::gen_clickTH_fn
    open fun gen_clickTH_fn(index: Number) {
        console.log("点击表头：" + index, " at pages/component/sticky-header/sticky-header.uvue:61")
    }
    open var loadListData = ::gen_loadListData_fn
    open fun gen_loadListData_fn() {
        var lists: UTSArray<String> = utsArrayOf()
        run {
            var i: Number = 0
            while(i < 40){
                lists.push("item---" + i)
                i++
            }
        }
        this.list_item = lists
    }
    open var clearListData = ::gen_clearListData_fn
    open fun gen_clearListData_fn() {
        this.list_item = utsArrayOf()
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#f5f5f5")), "content-item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginBottom" to 10, "backgroundColor" to "#ffffff")), "text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to "20px")), "sift-item" to padStyleMapOf(utsMapOf("color" to "#555555", "fontSize" to 16, "paddingTop" to 12, "paddingRight" to 15, "paddingBottom" to 12, "paddingLeft" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
