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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
open class GenPagesCSSBackgroundBackgroundColor : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesCSSBackgroundBackgroundColor) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesCSSBackgroundBackgroundColor
            val _cache = __ins.renderCache
            val jumpToCheckBoxWithBackgroundColor = fun(){
                uni_navigateTo(NavigateToOptions(url = "/pages/CSS/background/background-color-check-box"))
            }
            return fun(): Any? {
                return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: red"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "red"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: #ff0000"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "#ff0000"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: #f00"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "#f00"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: #ff000080"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "#ff000080"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: #f008"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "#f008"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: rgb(255, 0, 0)"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(255, 0, 0)"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: rgba(255, 0, 0, 0.5)"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "rgba(255, 0, 0, 0.5)"))), null, 4)
                        )),
                        createElementVNode("view", null, utsArrayOf(
                            createElementVNode("text", null, "background-color: #ff000080"),
                            createElementVNode("view", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("background-color" to "#ff000080"))), null, 4)
                        )),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "30px", "margin-right" to "30px"))), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "onClick" to jumpToCheckBoxWithBackgroundColor), "应用background-color示例")
                        ), 4)
                    ), 4)
                ), 4)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("common" to padStyleMapOf(utsMapOf("width" to 250, "height" to 250)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
