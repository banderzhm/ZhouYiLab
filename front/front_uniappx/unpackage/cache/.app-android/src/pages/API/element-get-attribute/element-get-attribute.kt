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
open class GenPagesAPIElementGetAttributeElementGetAttribute : BasePage {
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
            this.boxTarget = this.`$refs`["box"] as UniElement
            this.scrollViewTarget = this.`$refs`["scrollView"] as UniElement
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_child = resolveComponent("child")
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "15px"))), utsArrayOf(
                createElementVNode("view", utsMapOf("id" to "box", "ref" to "box"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "元素的id：" + toDisplayString(_ctx.attrId), 1),
                    createElementVNode("text", utsMapOf("class" to "uni-title-text"), "元素的背景色样式值：" + toDisplayString(_ctx.propertyValue), 1),
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "小程序端：getAttribute 获取元素的属性值，目前仅支持id、style"),
                    createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "App端：getAttribute 不支持获取 class、style 属性")
                ), 512),
                createElementVNode("button", utsMapOf("onClick" to _ctx.getAttributeId), "getAttribute获取元素的id", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.setStyle), "setProperty设置背景色", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("onClick" to _ctx.getPropertyValue), "getPropertyValue获取背景色值", 8, utsArrayOf(
                    "onClick"
                )),
                createVNode(_component_child, utsMapOf("id" to "child", "ref" to "child"), null, 512),
                createElementVNode("button", utsMapOf("onClick" to _ctx.getBoundingClientRectAsyncChild), "获取自定义组件child元素信息", 8, utsArrayOf(
                    "onClick"
                )),
                if (isTrue(_ctx.rectInfo)) {
                    createElementVNode("view", utsMapOf("key" to 0, "class" to "rect-info"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "x: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.x), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "y: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.y), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "width: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.width), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "height: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.height), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "left: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.left), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "top: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.top), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "right: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.right), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "node-info-item"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "node-info-item-k"), "bottom: "),
                            createElementVNode("text", utsMapOf("class" to "node-info-item-v"), toDisplayString(_ctx.rectInfo.bottom), 1)
                        ))
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                createElementVNode("scroll-view", utsMapOf("ref" to "scrollView", "class" to "scroll-view_H", "direction" to "horizontal"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "scroll-view-item_H uni-bg-red"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "A")
                    )),
                    createElementVNode("view", utsMapOf("class" to "scroll-view-item_H uni-bg-green"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "B")
                    )),
                    createElementVNode("view", utsMapOf("class" to "scroll-view-item_H uni-bg-blue"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "C")
                    ))
                ), 512),
                createElementVNode("button", utsMapOf("onClick" to _ctx.scrollTo), "scrollTo设置left滚动200px", 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ))
    }
    open var boxTarget: UniElement? by `$data`
    open var scrollViewTarget: UniElement? by `$data`
    open var attrId: String by `$data`
    open var attrStyle: String by `$data`
    open var propertyValue: String by `$data`
    open var rectInfo: DomRectType1 by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("boxTarget" to null as UniElement?, "scrollViewTarget" to null as UniElement?, "attrId" to "", "attrStyle" to "", "propertyValue" to "", "rectInfo" to DomRectType1(x = 0, y = 0, width = 0, height = 0, left = 0, top = 0, right = 0, bottom = 0))
    }
    open var getBoundingClientRectAsyncChild = ::gen_getBoundingClientRectAsyncChild_fn
    open fun gen_getBoundingClientRectAsyncChild_fn() {
        val childEl = uni_getElementById("child")!!
        childEl.getBoundingClientRectAsync()!!.then(fun(rect: DOMRect){
            console.log("rect: ", rect, " at pages/API/element-get-attribute/element-get-attribute.uvue:114")
            this.rectInfo = DomRectType1(x = rect.x, y = rect.y, width = rect.width, height = rect.height, left = rect.left, top = rect.top, right = rect.right, bottom = rect.bottom)
        }
        )
    }
    open var getAttributeId = ::gen_getAttributeId_fn
    open fun gen_getAttributeId_fn() {
        this.attrId = this.boxTarget!!.getAttribute("id") ?: ""
    }
    open var setStyle = ::gen_setStyle_fn
    open fun gen_setStyle_fn() {
        this.boxTarget!!.style.setProperty("background-color", "#FFF000")
    }
    open var getPropertyValue = ::gen_getPropertyValue_fn
    open fun gen_getPropertyValue_fn() {
        this.propertyValue = this.boxTarget!!.style.getPropertyValue("background-color")
    }
    open var getAttributeStyle = ::gen_getAttributeStyle_fn
    open fun gen_getAttributeStyle_fn() {
        this.attrStyle = this.boxTarget!!.getAttribute("style") ?: ""
    }
    open var scrollTo = ::gen_scrollTo_fn
    open fun gen_scrollTo_fn() {
        this.scrollViewTarget!!.scrollTo(200, 0)
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
                return utsMapOf("scroll-view_H" to padStyleMapOf(utsMapOf("width" to "100%", "flexDirection" to "row", "marginTop" to 15)), "scroll-view-item_H" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 150, "justifyContent" to "center", "alignItems" to "center")), "text" to padStyleMapOf(utsMapOf("fontSize" to 18, "color" to "#ffffff")), "rect-info" to padStyleMapOf(utsMapOf("marginTop" to 15, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "flexDirection" to "column")), "node-info-item" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "node-info-item-k" to padStyleMapOf(utsMapOf("width" to 72, "lineHeight" to 2)), "node-info-item-v" to padStyleMapOf(utsMapOf("fontWeight" to "bold", "lineHeight" to 2)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf("child" to GenPagesAPIElementGetAttributeChildClass)
    }
}
