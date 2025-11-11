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
open class GenPagesComponentListViewIssue16126 : BasePage {
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
            this.listViewElement = this.`$refs`["listview"] as UniElement
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("list-view", utsMapOf("ref" to "listview", "style" to normalizeStyle(_ctx.listViewStyle), "onScrolltolower" to _ctx.loadMore, "scroll-into-view" to _ctx.intoview, "scroll-top" to _ctx.scrolltop), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.dataList, fun(index, __key, __index, _cached): Any {
                    return createElementVNode("list-item", utsMapOf("class" to "listItem", "key" to index, "id" to ("item" + index), "onClick" to _ctx.changeSize, "type" to "1"), utsArrayOf(
                        createElementVNode("text", null, toDisplayString(index), 1)
                    ), 8, utsArrayOf(
                        "id",
                        "onClick"
                    ))
                }
                ), 128),
                createElementVNode("list-item", utsMapOf("class" to "listItem"), utsArrayOf(
                    createElementVNode("text", null, "加载更多中...")
                ))
            ), 44, utsArrayOf(
                "onScrolltolower",
                "scroll-into-view",
                "scroll-top"
            ))
        ), 4)
    }
    open var listViewStyle: String by `$data`
    open var dataList: Number by `$data`
    open var intoview: String by `$data`
    open var scrolltop: Number by `$data`
    open var listViewElement: UniElement? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("listViewStyle" to "flex:1; width: 100%; heigth: 100%", "dataList" to 10, "intoview" to "", "scrolltop" to 0, "listViewElement" to null as UniElement?)
    }
    open var changeSize = ::gen_changeSize_fn
    open fun gen_changeSize_fn() {
        if (this.listViewStyle == "flex:1; width: 100%; heigth: 100%") {
            this.listViewStyle = "flex:1; width: 50%; heigth: 100%;"
        } else {
            this.listViewStyle = "flex:1; width: 100%; heigth: 100%"
        }
    }
    open var loadMore = ::gen_loadMore_fn
    open fun gen_loadMore_fn() {
        setTimeout(fun(){
            this.dataList += 10
        }
        , 1000)
    }
    open var setScrollTop = ::gen_setScrollTop_fn
    open fun gen_setScrollTop_fn(value: Number) {
        this.scrolltop = value
    }
    open var getScrollTop = ::gen_getScrollTop_fn
    open fun gen_getScrollTop_fn(): Number {
        var ret = this.listViewElement?.scrollTop ?: -1
        console.log(ret, " at pages/component/list-view/issue-16126.uvue:47")
        return ret
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
                return utsMapOf("listView" to padStyleMapOf(utsMapOf("flex" to 1)), "listItem" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 100, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 0, "borderLeftWidth" to 1, "borderTopColor" to "#FF0000", "borderRightColor" to "#FF0000", "borderBottomColor" to "#FF0000", "borderLeftColor" to "#FF0000", "alignItems" to "center", "justifyContent" to "center")), "banner" to padStyleMapOf(utsMapOf("height" to 180, "overflow" to "hidden", "position" to "relative", "backgroundColor" to "#cccccc")), "banner-img" to padStyleMapOf(utsMapOf("width" to "100%")), "banner-title" to padStyleMapOf(utsMapOf("maxHeight" to 42, "overflow" to "hidden", "position" to "absolute", "left" to 15, "bottom" to 15, "width" to "90%", "fontSize" to 16, "fontWeight" to "400", "lineHeight" to "21px", "color" to "#FFFFFF")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
