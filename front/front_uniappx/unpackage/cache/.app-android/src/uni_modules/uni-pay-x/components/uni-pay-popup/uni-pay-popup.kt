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
open class GenUniModulesUniPayXComponentsUniPayPopupUniPayPopup : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        this.`$watch`(fun(): Any? {
            return this.isShow
        }
        , fun(newVal: Boolean) {
            if (newVal && this.isOpen == false) {
                this.isOpen = true
            }
        }
        , WatchOptions(immediate = true))
        this.`$watch`(fun(): Any? {
            return this.isOpen
        }
        , fun(newVal: Boolean) {
            if (newVal && this.isShow == false) {
                this.isShow = true
            }
        }
        , WatchOptions(immediate = true))
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return if (isTrue(_ctx.isOpen)) {
            withDirectives(createElementVNode("view", utsMapOf("key" to 0, "class" to normalizeClass(utsArrayOf(
                "popup-root",
                "popup-" + _ctx.type
            )), "onClick" to _ctx.clickMask), utsArrayOf(
                createElementVNode("view", utsMapOf("onClick" to withModifiers(fun(){}, utsArrayOf(
                    "stop"
                )), "class" to "popup-box"), utsArrayOf(
                    renderSlot(_ctx.`$slots`, "default")
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ), 10, utsArrayOf(
                "onClick"
            )), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.isShow
                )
            ))
        } else {
            createCommentVNode("v-if", true)
        }
    }
    open var maskClick: Boolean by `$props`
    open var type: String by `$props`
    open var isShow: Boolean by `$data`
    open var isOpen: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("isShow" to false, "isOpen" to false)
    }
    @get:JvmName("getOpen0")
    @set:JvmName("setOpen0")
    open var open = ::gen_open_fn
    open fun gen_open_fn() {
        this.isOpen = true
    }
    open var clickMask = ::gen_clickMask_fn
    open fun gen_clickMask_fn() {
        if (this.maskClick == true) {
            this.`$emit`("clickMask")
            this.close()
        }
    }
    open var close = ::gen_close_fn
    open fun gen_close_fn(): Unit {
        this.isOpen = false
        this.`$emit`("close")
    }
    open var hiden = ::gen_hiden_fn
    open fun gen_hiden_fn() {
        this.isShow = false
    }
    @get:JvmName("getShow1")
    @set:JvmName("setShow1")
    open var show = ::gen_show_fn
    open fun gen_show_fn() {
        this.isShow = true
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("popup-root" to padStyleMapOf(utsMapOf("position" to "fixed", "top" to 0, "left" to 0, "width" to "100%", "height" to "100%", "flex" to 1, "backgroundColor" to "rgba(0,0,0,0.4)", "justifyContent" to "center", "alignItems" to "center", "zIndex" to 99)), "popup-box" to padStyleMapOf(utsMapOf("width" to "100%", "justifyContent" to "center", "alignItems" to "center")), "popup-bottom" to padStyleMapOf(utsMapOf("justifyContent" to "flex-end")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf("close" to null, "clickMask" to null)
        var props = normalizePropsOptions(utsMapOf("maskClick" to utsMapOf("type" to "Boolean", "default" to true), "type" to utsMapOf("type" to "String", "default" to "center")))
        var propsNeedCastKeys = utsArrayOf(
            "maskClick",
            "type"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
