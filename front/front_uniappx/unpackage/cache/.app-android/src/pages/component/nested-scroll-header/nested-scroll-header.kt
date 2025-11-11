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
open class GenPagesComponentNestedScrollHeaderNestedScrollHeader : BasePage {
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
            var lists: UTSArray<String> = utsArrayOf()
            run {
                var i: Number = 0
                while(i < 30){
                    lists.push("item---" + i)
                    i++
                }
            }
            this.scrollData = lists
        }
        , __ins)
        onReady(fun() {
            this.num = 3
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "type" to "nested", "direction" to "vertical"), utsArrayOf(
            createElementVNode("nested-scroll-header", null, utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "scroll-header-tiem1"), utsArrayOf(
                    createElementVNode("text", null, "会渲染的nested-scroll-header")
                )),
                createElementVNode("view", utsMapOf("class" to "scroll-header-tiem1"), utsArrayOf(
                    createElementVNode("text", null, "不会渲染nested-scroll-header，因为 nested-scroll-header 只会渲染第一个子节点")
                ))
            )),
            createElementVNode("nested-scroll-header", null, utsArrayOf(
                createElementVNode("swiper", utsMapOf("ref" to "header", "indicator-dots" to "true", "circular" to "true"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.num, fun(i, __key, __index, _cached): Any {
                        return createElementVNode("swiper-item", utsMapOf("item-id" to i), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "scroll-header-tiem2"), utsArrayOf(
                                createElementVNode("text", null, "如果存在多个头部节点，那么就使用多个 nested-scroll-header 来将其包裹")
                            ))
                        ), 8, utsArrayOf(
                            "item-id"
                        ))
                    }
                    ), 256)
                ), 512)
            )),
            createElementVNode("nested-scroll-body", null, utsArrayOf(
                createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "associative-container" to "nested-scroll-view"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.scrollData, fun(key, __key, __index, _cached): Any {
                        return createElementVNode("view", null, utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "scroll-item-title"), toDisplayString(key), 1)
                            ))
                        ))
                    }
                    ), 256)
                ), 4)
            ))
        ), 4)
    }
    open var scrollData: UTSArray<String> by `$data`
    open var num: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("scrollData" to utsArrayOf<String>(), "num" to 0)
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
                return utsMapOf("scroll-item" to padStyleMapOf(utsMapOf("marginLeft" to 6, "marginRight" to 6, "marginTop" to 6, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4)), "scroll-item-title" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 60, "lineHeight" to "60px", "textAlign" to "center", "color" to "#555555")), "scroll-header-tiem1" to padStyleMapOf(utsMapOf("height" to 200, "backgroundColor" to "#66ccff", "alignItems" to "center", "justifyContent" to "center")), "scroll-header-tiem2" to padStyleMapOf(utsMapOf("height" to 100, "backgroundColor" to "#89ff8d", "alignItems" to "center", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
