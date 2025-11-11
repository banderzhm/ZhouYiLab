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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
open class GenPagesTemplateScrollStickyScrollSticky : BasePage {
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
        onReady(fun() {
            this.calcStickyTop()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("ref" to "scroll", "onScroll" to _ctx.onScroll, "class" to "page", "bounces" to "false"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "content-item"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "text"), "向上滑动页面，体验元素吸顶效果。")
            )),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, index, __index, _cached): Any {
                return createElementVNode("view", utsMapOf("key" to index, "class" to "content-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "first content-" + toDisplayString(item), 1)
                ))
            }
            ), 128),
            createElementVNode("view", utsMapOf("ref" to "sticky", "class" to "search"), utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to "/static/template/scroll-fold-nav/search.png", "style" to normalizeStyle(utsMapOf("width" to "15px")), "mode" to "widthFix"), null, 4),
                    createElementVNode("text", utsMapOf("class" to "search-tip-text"), "请输入你要搜索的内容")
                ), 4),
                createElementVNode("text", utsMapOf("class" to "search-btn"), "搜索")
            ), 512),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, index, __index, _cached): Any {
                return createElementVNode("view", utsMapOf("key" to index, "class" to "content-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "second content-" + toDisplayString(item), 1)
                ))
            }
            ), 128),
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.list, fun(item, index, __index, _cached): Any {
                return createElementVNode("view", utsMapOf("key" to index, "class" to "content-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "text"), "second content-" + toDisplayString(item), 1)
                ))
            }
            ), 128)
        ), 40, utsArrayOf(
            "onScroll"
        ))
    }
    open var list: UTSArray<String> by `$data`
    open var stickyTop: Number by `$data`
    open var stickyTran: Number by `$data`
    open var scrollTop: Number by `$data`
    open var stickyNode: UniElement? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("list" to utsArrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15"
        ), "stickyTop" to 0, "stickyTran" to 0, "scrollTop" to 0, "stickyNode" to null as UniElement?)
    }
    open var onScroll = ::gen_onScroll_fn
    open fun gen_onScroll_fn(e: ScrollEvent) {
        if (e.detail.scrollTop > this.stickyTop) {
            var stickyTran = e.detail.scrollTop - this.stickyTop
            if (stickyTran != this.stickyTran) {
                this.stickyNode?.style?.setProperty("transform", "translateY(" + stickyTran + "px)")
            }
            this.stickyTran = stickyTran
        } else {
            this.stickyNode?.style?.setProperty("transform", "")
            this.stickyTran = 0
        }
    }
    open var back = ::gen_back_fn
    open fun gen_back_fn() {
        uni_navigateBack(NavigateBackOptions(success = fun(result) {
            console.log("navigateBack success", result.errMsg, " at pages/template/scroll-sticky/scroll-sticky.uvue:56")
        }
        , fail = fun(error) {
            console.log("navigateBack fail", error.errMsg, " at pages/template/scroll-sticky/scroll-sticky.uvue:59")
        }
        , complete = fun(result) {
            console.log("navigateBack complete", result.errMsg, " at pages/template/scroll-sticky/scroll-sticky.uvue:62")
        }
        ))
    }
    open var calcStickyTop = ::gen_calcStickyTop_fn
    open fun gen_calcStickyTop_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                this.stickyNode = this.`$refs`["sticky"] as UniElement
                val stickyRect = await((this.`$refs`["sticky"] as UniElement).getBoundingClientRectAsync()!!)
                val scrollRect = await((this.`$refs`["scroll"] as UniElement).getBoundingClientRectAsync()!!)
                this.stickyTop = stickyRect.top - scrollRect.top
                console.log(stickyRect, scrollRect, " at pages/template/scroll-sticky/scroll-sticky.uvue:74")
        })
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 0, "paddingRight" to 15, "paddingBottom" to 0, "paddingLeft" to 15, "backgroundColor" to "#f5f5f5")), "content-item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginTop" to 5, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to "20px")), "search" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFFFF", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#fbdf0d", "borderRightColor" to "#fbdf0d", "borderBottomColor" to "#fbdf0d", "borderLeftColor" to "#fbdf0d", "height" to 35, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "marginTop" to 0, "marginRight" to 12, "marginBottom" to 0, "marginLeft" to 12, "paddingTop" to 8, "paddingRight" to 8, "paddingBottom" to 8, "paddingLeft" to 8, "flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between", "zIndex" to 100)), "search-tip-text" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#666666")), "search-btn" to padStyleMapOf(utsMapOf("fontSize" to 12, "backgroundColor" to "#ff6900", "color" to "#FFFFFF", "paddingTop" to 5, "paddingRight" to 8, "paddingBottom" to 5, "paddingLeft" to 8, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
