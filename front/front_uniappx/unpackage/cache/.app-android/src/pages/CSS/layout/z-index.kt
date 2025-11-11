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
open class GenPagesCSSLayoutZIndex : BasePage {
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("position" to "absolute", "z-index" to "0"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "common fixed default"), utsArrayOf(
                        createElementVNode("text", null, "position: fixed"),
                        createElementVNode("text", null, "z-index: 10")
                    )),
                    createElementVNode("view", utsMapOf("class" to "common fixed specified"), utsArrayOf(
                        createElementVNode("text", null, "position: fixed"),
                        createElementVNode("text", null, "z-index: 5")
                    )),
                    createElementVNode("view", utsMapOf("class" to "common fixed floor"), utsArrayOf(
                        createElementVNode("text", null, "position: fixed"),
                        createElementVNode("text", null, "z-index: -1")
                    ))
                ), 4),
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("top" to "250px"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "red", "z-index" to "10"))), utsArrayOf(
                        createElementVNode("text", null, "z-index: 10")
                    ), 4),
                    createElementVNode("view", utsMapOf("ref" to "view", "class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "green", "z-index" to "5", "top" to "-37px", "left" to "87px")), "onClick" to fun(){
                        _ctx.changeZIndex(20)
                    }
                    ), utsArrayOf(
                        createElementVNode("text", null, "z-index: " + toDisplayString(_ctx.zIndex), 1),
                        createElementVNode("text", null, "点击修改z-index")
                    ), 12, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "blue", "top" to "-75px", "left" to "175px"))), utsArrayOf(
                        createElementVNode("text", null, "z-index: 0")
                    ), 4)
                ), 4),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "common fixed popup", "style" to normalizeStyle(utsMapOf("background-color" to "aqua", "z-index" to "5"))), utsArrayOf(
                            createElementVNode("text", null, "position: fixed"),
                            createElementVNode("text", null, "z-index: 5")
                        ), 4)
                    ))
                ))
            ), 4),
            if (isTrue(_ctx.autoTest)) {
                createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("z-index" to "1", "position" to "fixed"))), "111", 4),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "750rpx"))), "222", 4)
                ))
            } else {
                createCommentVNode("v-if", true)
            }
        ), 64)
    }
    open var zIndex: Number by `$data`
    open var autoTest: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("zIndex" to 5, "autoTest" to false)
    }
    open var changeZIndex = ::gen_changeZIndex_fn
    open fun gen_changeZIndex_fn(zIndex: Number) {
        this.zIndex = 20
        (this.`$refs`["view"] as UniElement).style.setProperty("z-index", zIndex)
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
                return utsMapOf("common" to padStyleMapOf(utsMapOf("width" to 125, "height" to 125, "justifyContent" to "center", "alignItems" to "center")), "fixed" to padStyleMapOf(utsMapOf("position" to "fixed")), "default" to padStyleMapOf(utsMapOf("backgroundColor" to "#FF0000", "zIndex" to 10, "top" to "var(--uni-safe-area-inset-top)", "left" to "var(--uni-safe-area-inset-left)")), "specified" to padStyleMapOf(utsMapOf("backgroundColor" to "#008000", "zIndex" to 5, "top" to 87, "left" to 87)), "floor" to padStyleMapOf(utsMapOf("backgroundColor" to "#D2691E", "top" to 250, "left" to 175, "zIndex" to -1)), "popup" to padStyleMapOf(utsMapOf("top" to 320, "left" to 87, "height" to 40)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
