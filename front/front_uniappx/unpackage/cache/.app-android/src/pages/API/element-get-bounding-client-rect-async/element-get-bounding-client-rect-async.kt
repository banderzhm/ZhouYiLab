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
open class GenPagesAPIElementGetBoundingClientRectAsyncElementGetBoundingClientRectAsync : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "page", "id" to "page"), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("button", utsMapOf("class" to "btn btn-get-all-node-info", "onClick" to _ctx.getBoundingClientRectAsync), "getBoundingClientRectAsync", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("id" to "rect-test", "ref" to "rect-test", "class" to "rect-test"), null, 512),
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
            ))
        ))
    }
    open var title: String by `$data`
    open var rectInfo: DomRectType by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "getBoundingClientRectAsync", "rectInfo" to DomRectType(x = 0, y = 0, width = 0, height = 0, left = 0, top = 0, right = 0, bottom = 0))
    }
    open var getBoundingClientRectAsync = ::gen_getBoundingClientRectAsync_fn
    open fun gen_getBoundingClientRectAsync_fn() {
        (this.`$refs`["rect-test"] as UniElement).getBoundingClientRectAsync()!!.then(fun(rect: DOMRect){
            this.rectInfo = DomRectType(x = rect.x, y = rect.y, width = rect.width, height = rect.height, left = rect.left, top = rect.top, right = rect.right, bottom = rect.bottom)
        }
        )
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "btn" to padStyleMapOf(utsMapOf("marginTop" to 15)), "rect-test" to padStyleMapOf(utsMapOf("marginTop" to 15, "height" to 100, "backgroundColor" to "#1E90FF")), "rect-info" to padStyleMapOf(utsMapOf("marginTop" to 15, "flexDirection" to "column")), "node-info-item" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "node-info-item-k" to padStyleMapOf(utsMapOf("width" to 72, "lineHeight" to 2)), "node-info-item-v" to padStyleMapOf(utsMapOf("fontWeight" to "bold", "lineHeight" to 2)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
