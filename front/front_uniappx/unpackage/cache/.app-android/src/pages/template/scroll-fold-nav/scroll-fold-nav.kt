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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesTemplateScrollFoldNavScrollFoldNav : BasePage {
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
            this.statusBarHeight = uni_getWindowInfo().statusBarHeight
            this.nviBarHeight = NAVIBARHEIGHT + this.statusBarHeight
        }
        , __ins)
        onReady(fun() {
            this.naviElement = this.`$refs`["navigatorbar"] as UniElement
            this.searchElement = this.`$refs`["searchbar"] as UniElement
            this.titleElement = this.`$refs`["title"] as UniElement
            this.seatElement = this.`$refs`["seatbar"] as UniElement
            this.setStyle()
        }
        , __ins)
        onResize(fun(_: OnResizeOptions) {
            this.statusBarHeight = uni_getWindowInfo().statusBarHeight
            this.nviBarHeight = NAVIBARHEIGHT + this.statusBarHeight
            this.setStyle()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("onScroll" to _ctx.onScroll, "class" to "page", "show-scrollbar" to "false"), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "seatbar", "class" to "seatbar"), null, 512),
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "1. 当前示例监听了 scroll-view 的 scroll 事件 ，滚动页面实时监听scrollTop。"),
                    createElementVNode("text", utsMapOf("class" to "text"), "2. 使用 ref 直接获取元素的节点，并在 scroll 事件中通过节点的 setProperty 方法来修改搜索导航栏的高度、位置和背景颜色等样式，从而达到滚动折叠的效果。"),
                    createElementVNode("text", utsMapOf("class" to "text"), "3. 请向上\\向下滚动页面观察效果。")
                )),
                createElementVNode(Fragment, null, RenderHelpers.renderList(20, fun(item, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "content-item", "key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "content-" + toDisplayString(item), 1)
                    ))
                }
                ), 64)
            )),
            createElementVNode("view", utsMapOf("ref" to "navigatorbar", "class" to "navigatorbar"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "titlebar"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "backview", "onClick" to _ctx.back), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "back", "src" to "/static/template/scroll-fold-nav/back.png", "mode" to "widthFix"))
                    ), 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("text", utsMapOf("ref" to "title", "class" to "title"), "DCloud 为开发者而生", 512)
                )),
                createElementVNode("view", utsMapOf("ref" to "searchbar", "class" to "searchbar", "onClick" to _ctx.search), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "searchimg", "src" to "/static/template/scroll-fold-nav/search.png", "mode" to "widthFix")),
                    createElementVNode("text", utsMapOf("class" to "searchinput"), "请输入你要搜索的内容"),
                    createElementVNode("text", utsMapOf("class" to "searchbutton"), "搜索")
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ), 512)
        ), 40, utsArrayOf(
            "onScroll"
        ))
    }
    open var statusBarHeight: Number by `$data`
    open var nviBarHeight: Number by `$data`
    open var naviElement: UniElement? by `$data`
    open var titleElement: UniElement? by `$data`
    open var searchElement: UniElement? by `$data`
    open var seatElement: UniElement? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("statusBarHeight" to 0, "nviBarHeight" to 0, "naviElement" to null as UniElement?, "titleElement" to null as UniElement?, "searchElement" to null as UniElement?, "seatElement" to null as UniElement?)
    }
    open var onScroll = ::gen_onScroll_fn
    open fun gen_onScroll_fn(e: ScrollEvent) {
        var offset = if (e.detail.scrollTop > SEARCHBARHEIGHT) {
            SEARCHBARHEIGHT
        } else {
            e.detail.scrollTop
        }
        this.naviElement?.style?.setProperty("height", (this.nviBarHeight - offset) + "px")
        this.titleElement?.style?.setProperty("opacity", (1 - offset / SEARCHBARHEIGHT).toString(10))
        this.searchElement?.style?.setProperty("left", (if ((offset < 0)) {
            0
        } else {
            BACKWIDTH * offset / SEARCHBARHEIGHT
        }
        ) + "px")
    }
    open var back = ::gen_back_fn
    open fun gen_back_fn() {
        uni_navigateBack(null)
    }
    open var search = ::gen_search_fn
    open fun gen_search_fn() {
        uni_showToast(ShowToastOptions(title = "暂不支持", icon = "none"))
    }
    open var setStyle = ::gen_setStyle_fn
    open fun gen_setStyle_fn() {
        this.naviElement?.style?.setProperty("padding-top", this.statusBarHeight + "px")
        this.naviElement?.style?.setProperty("height", (NAVIBARHEIGHT + this.statusBarHeight) + "px")
        this.seatElement?.style?.setProperty("height", (NAVIBARHEIGHT + this.statusBarHeight) + "px")
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#f5f5f5")), "navigatorbar" to padStyleMapOf(utsMapOf("position" to "fixed", "paddingTop" to 35, "height" to 124, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#efefef", "width" to "100%", "backgroundColor" to "#f5f5f5")), "titlebar" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignItems" to "center", "height" to 44)), "backview" to padStyleMapOf(utsMapOf("width" to 44, "height" to 44, "justifyContent" to "center", "alignItems" to "center")), "back" to padStyleMapOf(utsMapOf("width" to 20)), "title" to padStyleMapOf(utsMapOf("marginTop" to 0, "marginRight" to 2, "marginBottom" to 0, "marginLeft" to 2)), "searchbar" to padStyleMapOf(utsMapOf("position" to "absolute", "bottom" to 2, "left" to 0, "right" to 0, "backgroundColor" to "#FFFFFF", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#fbdf0d", "borderRightColor" to "#fbdf0d", "borderBottomColor" to "#fbdf0d", "borderLeftColor" to "#fbdf0d", "height" to 32, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "marginTop" to 6, "marginRight" to 12, "marginBottom" to 6, "marginLeft" to 12, "paddingTop" to 8, "paddingRight" to 8, "paddingBottom" to 8, "paddingLeft" to 8, "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "center")), "searchimg" to padStyleMapOf(utsMapOf("width" to 15)), "searchinput" to padStyleMapOf(utsMapOf("flexGrow" to 1, "fontSize" to 12, "color" to "#666666")), "searchbutton" to padStyleMapOf(utsMapOf("fontSize" to 12, "backgroundColor" to "#ff6900", "color" to "#FFFFFF", "paddingTop" to 5, "paddingRight" to 8, "paddingBottom" to 5, "paddingLeft" to 8, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100)), "seatbar" to padStyleMapOf(utsMapOf("height" to 124)), "content" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 15, "paddingBottom" to 5, "paddingLeft" to 15)), "content-item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginTop" to 5, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to "20px")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
