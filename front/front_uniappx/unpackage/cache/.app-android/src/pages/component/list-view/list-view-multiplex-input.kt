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
open class GenPagesComponentListViewListViewMultiplexInput : BasePage {
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
        return createElementVNode("list-view", utsMapOf("id" to "listview", "style" to normalizeStyle(utsMapOf("flex" to "1")), "show-scrollbar" to "false", "onScrolltolower" to _ctx.onScrollTolower), utsArrayOf(
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.item_count, fun(index, __key, __index, _cached): Any {
                return createElementVNode("list-item", utsMapOf("class" to "item", "onClick" to fun(){
                    _ctx.itemClick(index)
                }
                ), utsArrayOf(
                    createElementVNode("text", null, utsArrayOf(
                        "item-------",
                        createElementVNode("text", null, toDisplayString(index), 1)
                    )),
                    createElementVNode("input", utsMapOf("style" to normalizeStyle(utsMapOf("border-width" to "1px", "border-style" to "solid")), "placeholder" to (index + ""), "value" to if (index == 1) {
                        "\u7B2C\u4E00\u4E2A"
                    } else {
                        index
                    }
                    ), null, 12, utsArrayOf(
                        "placeholder",
                        "value"
                    ))
                ), 8, utsArrayOf(
                    "onClick"
                ))
            }
            ), 256)
        ), 44, utsArrayOf(
            "onScrolltolower"
        ))
    }
    open var item_count: Number by `$data`
    open var listViewElement: UniListViewElement? by `$data`
    open var isTesting: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("item_count" to 20, "listViewElement" to null as UniListViewElement?, "isTesting" to false)
    }
    open var onScrollTolower = ::gen_onScrollTolower_fn
    open fun gen_onScrollTolower_fn(_0: ScrollToLowerEvent) {
        setTimeout(fun(){
            if (this.isTesting && this.item_count > 20) {
                return
            }
            this.item_count += 20
        }
        , 300)
    }
    open var itemClick = ::gen_itemClick_fn
    open fun gen_itemClick_fn(index: Number) {
        console.log("itemTextClick---" + index, " at pages/component/list-view/list-view-multiplex-input.uvue:39")
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
                return utsMapOf("item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginTop" to 0, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "button_item" to padStyleMapOf(utsMapOf("width" to 200)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
