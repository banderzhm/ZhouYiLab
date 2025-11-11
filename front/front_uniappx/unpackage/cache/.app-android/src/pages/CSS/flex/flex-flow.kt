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
open class GenPagesCSSFlexFlexFlow : BasePage {
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
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "flex-flow: row nowrap"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("flex-flow" to "row nowrap"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4)
                    ), 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "flex-flow: row wrap"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("flex-flow" to "row wrap"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4)
                    ), 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "flex-flow: column nowrap"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("flex-flow" to "column nowrap"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4)
                    ), 4)
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("text", null, "flex-flow: column wrap"),
                    createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("flex-flow" to "column wrap"))), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "red", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "green", "flex-shrink" to "0"))), null, 4),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "blue", "flex-shrink" to "0"))), null, 4)
                    ), 4)
                ))
            ), 4)
        ), 4)
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
