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
open class GenPagesCSSBorderBorderStyle : BasePage {
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
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-style: dashed"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-width" to "5px", "border-style" to "dashed"))), null, 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-left-style: dashed"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-left-width" to "5px", "border-left-style" to "dashed"))), null, 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-top-style: dashed"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-top-width" to "5px", "border-top-style" to "dashed"))), null, 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-right-style: dotted"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-right-width" to "5px", "border-right-style" to "dotted"))), null, 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-bottom-style: dotted"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-bottom-width" to "5px", "border-bottom-style" to "dotted"))), null, 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-style: solid (缺省 border-width)"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-style" to "solid"))), null, 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "border-style: none"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("border-style" to "none", "border-width" to "5px"))), null, 4)
                )),
                createElementVNode("view", utsMapOf("onClick" to _ctx.changeBorderStyle), utsArrayOf(
                    createElementVNode("text", null, "border-style: 点击切换"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(_ctx.borderStyle)), null, 4)
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 4)
    }
    open var isSolid: Boolean by `$data`
    open var borderStyle: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("isSolid" to false, "borderStyle" to "border-style: none; border-width: 5px;")
    }
    open var changeBorderStyle = ::gen_changeBorderStyle_fn
    open fun gen_changeBorderStyle_fn() {
        this.isSolid = !this.isSolid
        val solid = "border-style: solid; border-width: 5px;"
        val none = ""
        this.borderStyle = if (this.isSolid) {
            solid
        } else {
            none
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
                return utsMapOf("common" to padStyleMapOf(utsMapOf("width" to 250, "height" to 250, "backgroundColor" to "#808080")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
