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
open class GenPagesCSSBackgroundBackgroundImage : BasePage {
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
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "0px", "height" to "0px", "background-image" to "linear-gradient(to bottom,#f5f5f5,#eff2f5)"))), null, 4),
                createElementVNode("text", null, "不支持背景图片，仅支持linear-gradient方法"),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.directionData, fun(direction, __key, __index, _cached): Any {
                    return createElementVNode("view", null, utsArrayOf(
                        createElementVNode("text", null, "background-image: linear-gradient(" + toDisplayString(direction) + ", red, yellow)", 1),
                        createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-image" to if (_ctx.backgroundSelect) {
                            "linear-gradient(" + direction + ", red, yellow)"
                        } else {
                            ""
                        }
                        ))), null, 4)
                    ))
                }
                ), 256),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "style 动态切换 background"),
                    createElementVNode("view", utsMapOf("onClick" to _ctx.changeBgStyle, "class" to "common", "style" to normalizeStyle(_ctx.testStyle)), toDisplayString(_ctx.testStyle), 13, utsArrayOf(
                        "onClick"
                    ))
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "class 动态切换 background"),
                    createElementVNode("view", utsMapOf("onClick" to _ctx.changeBgClass, "class" to normalizeClass(utsArrayOf(
                        "common",
                        _ctx.testClass
                    ))), toDisplayString(_ctx.testClass), 11, utsArrayOf(
                        "onClick"
                    ))
                ))
            ))
        ), 4)
    }
    open var backgroundSelect: Boolean by `$data`
    open var directionData: UTSArray<String> by `$data`
    open var testStyle: String by `$data`
    open var testClass: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("backgroundSelect" to true, "directionData" to utsArrayOf(
            "to right",
            "to left",
            "to bottom",
            "to top",
            "to bottom left",
            "to bottom right",
            "to top left",
            "to top right"
        ), "testStyle" to "background:linear-gradient(to right, red, yellow)", "testClass" to "bg-color")
    }
    open var updateBackgroundSelect = ::gen_updateBackgroundSelect_fn
    open fun gen_updateBackgroundSelect_fn() {
        this.backgroundSelect = !this.backgroundSelect
    }
    open var changeBgStyle = ::gen_changeBgStyle_fn
    open fun gen_changeBgStyle_fn() {
        val isColor = this.testStyle == "background:blue"
        if (isColor) {
            this.setBackgroundImage()
        } else {
            this.setBackgroundColor()
        }
    }
    open var setBackgroundColor = ::gen_setBackgroundColor_fn
    open fun gen_setBackgroundColor_fn() {
        this.testStyle = "background:blue"
    }
    open var setBackgroundImage = ::gen_setBackgroundImage_fn
    open fun gen_setBackgroundImage_fn() {
        this.testStyle = "background:linear-gradient(to right, red, yellow)"
    }
    open var changeBgClass = ::gen_changeBgClass_fn
    open fun gen_changeBgClass_fn() {
        this.testClass = if (this.testClass == "bg-color") {
            "bg-image"
        } else {
            "bg-color"
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
                return utsMapOf("common" to padStyleMapOf(utsMapOf("width" to 250, "height" to 250)), "bg-color" to padStyleMapOf(utsMapOf("backgroundImage" to "none", "backgroundColor" to "#0000FF")), "bg-image" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(to right, red, yellow)", "backgroundColor" to "rgba(0,0,0,0)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
