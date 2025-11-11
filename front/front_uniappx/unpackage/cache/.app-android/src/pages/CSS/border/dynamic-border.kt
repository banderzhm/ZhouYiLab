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
open class GenPagesCSSBorderDynamicBorder : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "15px", "border-radius" to "10px", "background-color" to "white"))), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(utsArrayOf(
                    0,
                    1,
                    2,
                    3,
                    4,
                    5,
                    6,
                    7,
                    8,
                    9
                ), fun(index, __key, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("key" to index, "class" to normalizeClass((if (index < 9) {
                        "bb1"
                    } else {
                        ""
                    }
                    ) + " " + (if (_ctx.currentIndex == 0) {
                        "btlr10 btrr10"
                    } else {
                        if (_ctx.currentIndex == 9) {
                            "bblr10 bbrr10"
                        } else {
                            ""
                        }
                    }
                    )), "style" to normalizeStyle(utsArrayOf(
                        utsMapOf("flex-direction" to "row", "align-items" to "center", "padding" to "15px"),
                        if (index == _ctx.currentIndex) {
                            "background-color :#57BE6A;"
                        } else {
                            ""
                        }
                    )), "onClick" to fun(){
                        _ctx.changeIndex(index)
                    }
                    ), utsArrayOf(
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsArrayOf(
                                utsMapOf("font-size" to "14px"),
                                if (index == _ctx.currentIndex) {
                                    "color: #FFFFFF;"
                                } else {
                                    ""
                                }
                            ))), toDisplayString(index), 5)
                        ))
                    ), 14, utsArrayOf(
                        "onClick"
                    ))
                }
                ), 64)
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "15px", "margin-top" to "20px"))), utsArrayOf(
                createElementVNode("text", null, "动态切换 border 为空值"),
                createElementVNode("text", utsMapOf("onClick" to _ctx.setBorderBlank, "class" to "common", "style" to normalizeStyle(_ctx.style)), toDisplayString(if (_ctx.isSelect) {
                    "选中"
                } else {
                    "未选中"
                }
                ), 13, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 4)
    }
    open var currentIndex: Number by `$data`
    open var style: String by `$data`
    open var isSelect: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("currentIndex" to 0, "style" to "" as String, "isSelect" to computed<Boolean>(fun(): Boolean {
            return this.style == defaultStyle
        }
        ))
    }
    open var changeIndex = ::gen_changeIndex_fn
    open fun gen_changeIndex_fn(index: Number) {
        this.currentIndex = index
    }
    open var setBorderBlank = ::gen_setBorderBlank_fn
    open fun gen_setBorderBlank_fn() {
        this.style = if (this.style == "") {
            defaultStyle
        } else {
            ""
        }
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
                return utsMapOf("common" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "width" to 120, "textAlign" to "center", "marginTop" to 10)), "bb1" to padStyleMapOf(utsMapOf("borderBottomWidth" to "1rpx", "borderBottomStyle" to "solid", "borderBottomColor" to "#EEEEEE")), "btlr10" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to "10rpx")), "btrr10" to padStyleMapOf(utsMapOf("borderTopRightRadius" to "10rpx")), "bblr10" to padStyleMapOf(utsMapOf("borderBottomLeftRadius" to "10rpx")), "bbrr10" to padStyleMapOf(utsMapOf("borderBottomRightRadius" to "10rpx")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
