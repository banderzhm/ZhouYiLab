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
open class GenPagesComponentSwiperSwiperListView : BasePage {
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
            createElementVNode("view", utsMapOf("class" to "content-item"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "text"), "左右滑动页面，体验swiper嵌套list-view效果。")
            )),
            createElementVNode("swiper", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "current" to _ctx.currentVal, "onChange" to _ctx.swiperChange), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(3, fun(index, __key, __index, _cached): Any {
                    return createElementVNode("swiper-item", null, utsArrayOf(
                        createElementVNode("list-view", utsMapOf("id" to ("list" + index), "style" to normalizeStyle(utsMapOf("flex" to "1")), "refresher-enabled" to "true", "onRefresherrefresh" to _ctx.onRefresherrefresh, "refresher-triggered" to _ctx.refresherTriggeredArray[index - 1], "scroll-top" to _ctx.scrollTop), utsArrayOf(
                            createElementVNode("sticky-header", null, utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "header"), "上下滑动体验吸顶效果 swiper-item" + toDisplayString(index), 1)
                            )),
                            createElementVNode(Fragment, null, RenderHelpers.renderList(40, fun(itemIndex, __key, __index, _cached): Any {
                                return createElementVNode("list-item", utsMapOf("class" to "item"), utsArrayOf(
                                    createElementVNode("text", null, "item----------" + toDisplayString(itemIndex), 1)
                                ))
                            }
                            ), 64)
                        ), 44, utsArrayOf(
                            "id",
                            "onRefresherrefresh",
                            "refresher-triggered",
                            "scroll-top"
                        ))
                    ))
                }
                ), 64)
            ), 44, utsArrayOf(
                "current",
                "onChange"
            ))
        ), 4)
    }
    open var swiperCurrentIndex: Number by `$data`
    open var currentVal: Number by `$data`
    open var scrollTop: Number by `$data`
    open var refresherTriggeredArray: UTSArray<Boolean> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("swiperCurrentIndex" to 0, "currentVal" to 0, "scrollTop" to 0, "refresherTriggeredArray" to utsArrayOf<Boolean>(false, false, false))
    }
    open var swiperChange = ::gen_swiperChange_fn
    open fun gen_swiperChange_fn(e: SwiperChangeEvent) {
        this.swiperCurrentIndex = e.detail.current
    }
    open var onRefresherrefresh = ::gen_onRefresherrefresh_fn
    open fun gen_onRefresherrefresh_fn() {
        this.refresherTriggeredArray[this.swiperCurrentIndex] = true
        setTimeout(fun(){
            this.refresherTriggeredArray[this.swiperCurrentIndex] = false
        }
        , 100)
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
                return utsMapOf("item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginTop" to 0, "marginRight" to 0, "marginBottom" to 5, "marginLeft" to 0, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#666666", "lineHeight" to "20px")), "content-item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginBottom" to 10, "backgroundColor" to "#ffffff")), "header" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffaa00", "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "textAlign" to "center", "color" to "#ffffff")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
