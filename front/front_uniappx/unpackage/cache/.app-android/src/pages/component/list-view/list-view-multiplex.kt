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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
open class GenPagesComponentListViewListViewMultiplex : BasePage {
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
            this.listViewElement = uni_getElementById<UniListViewElement>("listview")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_switch = resolveComponent("switch")
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("scroll-view", utsMapOf("direction" to "horizontal", "style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "button_item", "onClick" to _ctx.delayShow), "测试延时显示", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "button_item", "onClick" to _ctx.switchItemContent), "修改item子元素", 8, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            withDirectives(createElementVNode("list-view", utsMapOf("id" to "listview", "style" to normalizeStyle(utsMapOf("flex" to "1")), "show-scrollbar" to "false", "onScrolltolower" to _ctx.onScrollTolower), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.item_count, fun(index, __key, __index, _cached): Any {
                    return createElementVNode("list-item", utsMapOf("id" to ("item_" + index), "class" to "item", "onClick" to fun(){
                        _ctx.itemClick(index)
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                            createElementVNode("text", null, utsArrayOf(
                                "item-------",
                                createElementVNode("text", null, toDisplayString(index), 1)
                            )),
                            createElementVNode("scroll-view", utsMapOf("direction" to "horizontal", "show-scrollbar" to "false", "class" to "scroll_item"), utsArrayOf(
                                createElementVNode("text", null, "scroll-view" + toDisplayString(index) + "：", 1),
                                createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(tab, __key, __index, _cached): Any {
                                    return createElementVNode("text", utsMapOf("class" to "tip_text"), "元素" + toDisplayString(tab), 1)
                                }
                                ), 64)
                            ))
                        ), 4),
                        withDirectives(createElementVNode("text", null, utsArrayOf(
                            "item-------",
                            createElementVNode("text", null, toDisplayString(index), 1)
                        ), 512), utsArrayOf(
                            utsArrayOf(
                                vShow,
                                _ctx.displayArrow
                            )
                        )),
                        createVNode(_component_switch, utsMapOf("checked" to true))
                    ), 8, utsArrayOf(
                        "id",
                        "onClick"
                    ))
                }
                ), 256)
            ), 44, utsArrayOf(
                "onScrolltolower"
            )), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.list_show
                )
            ))
        ), 4)
    }
    open var item_count: Number by `$data`
    open var list_show: Boolean by `$data`
    open var listViewElement: UniListViewElement? by `$data`
    open var displayArrow: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("item_count" to 20, "list_show" to true, "listViewElement" to null as UniListViewElement?, "displayArrow" to false)
    }
    open var onScrollTolower = ::gen_onScrollTolower_fn
    open fun gen_onScrollTolower_fn(_0: ScrollToLowerEvent) {
        setTimeout(fun(){
            this.item_count += 20
        }
        , 300)
    }
    open var listViewScrollByY = ::gen_listViewScrollByY_fn
    open fun gen_listViewScrollByY_fn(y: Number) {
        this.listViewElement?.scrollBy(0, y)
    }
    open var itemClick = ::gen_itemClick_fn
    open fun gen_itemClick_fn(index: Number) {
        console.log("itemTextClick---" + index, " at pages/component/list-view/list-view-multiplex.uvue:48")
    }
    open var delayShow = ::gen_delayShow_fn
    open fun gen_delayShow_fn() {
        this.list_show = !this.list_show
        this.item_count += 20
        setTimeout(fun(){
            this.list_show = !this.list_show
        }
        , 400)
    }
    open var switchItemContent = ::gen_switchItemContent_fn
    open fun gen_switchItemContent_fn() {
        this.displayArrow = !this.displayArrow
        this.modifyItemPadding(1)
    }
    open var modifyItemPadding = ::gen_modifyItemPadding_fn
    open fun gen_modifyItemPadding_fn(index: Number) {
        var element = uni_getElementById("item_" + index)
        element?.style?.setProperty("padding", "0px")
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
                return utsMapOf("item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginTop" to 0, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "backgroundColor" to "#fdfdfd", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "button_item" to padStyleMapOf(utsMapOf("width" to 200)), "scroll_item" to padStyleMapOf(utsMapOf("flex" to 1, "flexDirection" to "row", "overflow" to "hidden", "marginLeft" to 10)), "tip_text" to padStyleMapOf(utsMapOf("borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopLeftRadius" to 3, "borderTopRightRadius" to 3, "borderBottomRightRadius" to 3, "borderBottomLeftRadius" to 3, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "marginTop" to 0, "marginRight" to 10, "marginBottom" to 0, "marginLeft" to 10)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
