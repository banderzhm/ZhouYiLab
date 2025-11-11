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
open class GenPagesTemplatePullZoomImagePullZoomImage : BasePage {
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
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("onClick" to _ctx.back, "class" to "nav-back"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "back-img", "src" to "/static/template/pull-zoom-image/back.png", "mode" to "widthFix"))
            ), 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "refresher-enabled" to true, "refresher-default-style" to "none", "onRefresherpulling" to _ctx.onRefresherpulling, "refresher-threshold" to 300, "refresher-max-drag-distance" to 299, "onScroll" to _ctx.onScroll, "bounces" to false), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "head-img-box-2", "ref" to "head-img-box-2"), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "img", "ref" to "head-img-2", "src" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/pull-zoom-image-head-img.jpg", "mode" to "scaleToFill"), null, 512)
                ), 512),
                createElementVNode("view", utsMapOf("class" to "user-info", "ref" to "user-info"), utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "user-avatar", "src" to default7, "mode" to "widthFix")),
                    createElementVNode("view", utsMapOf("class" to "font-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "username"), "uni-app-x"),
                        createElementVNode("text", utsMapOf("class" to "slogan"), "一次开发，多端覆盖")
                    ))
                ), 512),
                createElementVNode("view", utsMapOf("class" to "list-box"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(30, fun(item, index, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("class" to "item", "key" to index), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(item) + ". 占位", 1)
                        ))
                    }
                    ), 64)
                )),
                createElementVNode("view", utsMapOf("slot" to "refresher"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "head-img-box-1"), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "img", "ref" to "head-img-1", "src" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/pull-zoom-image-head-img.jpg", "mode" to "scaleToFill"), null, 512)
                    ))
                ))
            ), 44, utsArrayOf(
                "onRefresherpulling",
                "onScroll"
            ))
        ), 4)
    }
    open var `$elementMap`: Map<String, UniElement> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("\$elementMap" to Map<String, UniElement>())
    }
    open var onScroll = ::gen_onScroll_fn
    open fun gen_onScroll_fn(e: ScrollEvent) {
        val scrollTop = e.detail.scrollTop
        var y: Number = scrollTop - 110
        var s: Number = (100 - scrollTop / 3) / 100
        if (y < 0) {
            y = 0
        }
        if (s < 0.7) {
            s = 0.7
        }
        var x: Number = (1 - s) * -100
        this.setElementStyle("user-info", "transform", "translate(" + x + "px, " + (y + (s - 1) * -50) + "px) scale(" + s + ")")
        this.setElementStyle("head-img-box-2", "transform", "translateY(" + y + "px)")
    }
    open var onRefresherpulling = ::gen_onRefresherpulling_fn
    open fun gen_onRefresherpulling_fn(e: RefresherEvent) {
        var pullingDistance: Number = e.detail.dy
        this.setElementStyle("head-img-1", "transform", "scale(" + (pullingDistance / 200 + 1) + ")")
        this.setElementStyle("head-img-2", "transform", "scale(" + (pullingDistance / 200 + 1) + ")")
    }
    open var setElementStyle = ::gen_setElementStyle_fn
    open fun gen_setElementStyle_fn(refName: String, propertyName: String, propertyStyle: Any): Unit {
        var element: UniElement? = (this.`$data`["\$elementMap"] as Map<String, UniElement>).get(refName)
        if (element == null) {
            element = this.`$refs`[refName] as UniElement
            (this.`$data`["\$elementMap"] as Map<String, UniElement>).set(refName, element)
        }
        element.style.setProperty(propertyName, propertyStyle)
    }
    open var back = ::gen_back_fn
    open fun gen_back_fn() {
        uni_navigateBack(NavigateBackOptions(success = fun(result) {
            console.log("navigateBack success", result.errMsg, " at pages/template/pull-zoom-image/pull-zoom-image.uvue:79")
        }
        , fail = fun(error) {
            console.log("navigateBack fail", error.errMsg, " at pages/template/pull-zoom-image/pull-zoom-image.uvue:82")
        }
        , complete = fun(result) {
            console.log("navigateBack complete", result.errMsg, " at pages/template/pull-zoom-image/pull-zoom-image.uvue:85")
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
                return utsMapOf("head-img-box-1" to padStyleMapOf(utsMapOf("position" to "relative", "height" to 300)), "head-img-box-2" to padStyleMapOf(utsMapOf("position" to "relative", "height" to 200, "zIndex" to 9)), "img" to utsMapOf(".head-img-box-1 " to utsMapOf("position" to "absolute", "width" to "100%", "height" to 600, "top" to 0), ".head-img-box-2 " to utsMapOf("position" to "absolute", "width" to "100%", "height" to 600, "bottom" to -100)), "user-info" to padStyleMapOf(utsMapOf("marginTop" to -110, "flex" to 1, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "flexDirection" to "row", "zIndex" to 10)), "user-avatar" to utsMapOf(".user-info " to utsMapOf("width" to 75, "height" to 75, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "borderTopWidth" to 3, "borderRightWidth" to 3, "borderBottomWidth" to 3, "borderLeftWidth" to 3, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FFFFFF", "borderRightColor" to "#FFFFFF", "borderBottomColor" to "#FFFFFF", "borderLeftColor" to "#FFFFFF")), "font-box" to utsMapOf(".user-info " to utsMapOf("flexDirection" to "column", "justifyContent" to "space-around", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "username" to utsMapOf(".user-info " to utsMapOf("fontSize" to 26, "color" to "#FFFFFF")), "slogan" to utsMapOf(".user-info " to utsMapOf("fontSize" to 16, "color" to "#FFFFFF")), "list-box" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFFFF", "zIndex" to 1)), "item" to utsMapOf(".list-box " to utsMapOf("paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "marginTop" to 5, "marginRight" to 5, "marginBottom" to 5, "marginLeft" to 5, "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "rgba(220,220,220,0.3)", "borderRightColor" to "rgba(220,220,220,0.3)", "borderBottomColor" to "rgba(220,220,220,0.3)", "borderLeftColor" to "rgba(220,220,220,0.3)")), "text" to utsMapOf(".list-box " to utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to "20px")), "nav-back" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 30, "left" to 10, "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "width" to 28, "height" to 28, "justifyContent" to "center", "alignItems" to "center", "zIndex" to 10)), "back-img" to utsMapOf(".nav-back " to utsMapOf("width" to 18, "height" to 18)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
