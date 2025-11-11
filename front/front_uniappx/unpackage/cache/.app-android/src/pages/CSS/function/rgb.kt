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
open class GenPagesCSSFunctionRgb : BasePage {
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
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("text", utsMapOf("class" to "intro-text"), "RGB 是一种颜色表示方法，通过调整红(Red)、绿(Green)、蓝(Blue)三个通道的值（0-255）来产生不同的颜色。"),
            createElementVNode("view", utsMapOf("class" to "section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "section-title"), "数值表示 (0-255)"),
                createElementVNode("view", utsMapOf("class" to "color-grid"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "color-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "color-box", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(255, 0, 0)"))), null, 4),
                        createElementVNode("text", utsMapOf("class" to "color-name"), "红色"),
                        createElementVNode("text", utsMapOf("class" to "color-value"), "rgb(255, 0, 0)")
                    )),
                    createElementVNode("view", utsMapOf("class" to "color-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "color-box", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(0, 255, 0)"))), null, 4),
                        createElementVNode("text", utsMapOf("class" to "color-name"), "绿色"),
                        createElementVNode("text", utsMapOf("class" to "color-value"), "rgb(0, 255, 0)")
                    )),
                    createElementVNode("view", utsMapOf("class" to "color-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "color-box", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(0, 0, 255)"))), null, 4),
                        createElementVNode("text", utsMapOf("class" to "color-name"), "蓝色"),
                        createElementVNode("text", utsMapOf("class" to "color-value"), "rgb(0, 0, 255)")
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "section"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "color-grid"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "color-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "color-box", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(255, 255, 0)"))), null, 4),
                        createElementVNode("text", utsMapOf("class" to "color-name"), "黄色"),
                        createElementVNode("text", utsMapOf("class" to "color-value"), "rgb(255, 255, 0)")
                    )),
                    createElementVNode("view", utsMapOf("class" to "color-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "color-box", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(255, 0, 255)"))), null, 4),
                        createElementVNode("text", utsMapOf("class" to "color-name"), "品红"),
                        createElementVNode("text", utsMapOf("class" to "color-value"), "rgb(255, 0, 255)")
                    )),
                    createElementVNode("view", utsMapOf("class" to "color-item"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "color-box", "style" to normalizeStyle(utsMapOf("background-color" to "rgb(0, 255, 255)"))), null, 4),
                        createElementVNode("text", utsMapOf("class" to "color-name"), "青色"),
                        createElementVNode("text", utsMapOf("class" to "color-value"), "rgb(0, 255, 255)")
                    ))
                ))
            ))
        ))
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "intro-text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#333333", "marginBottom" to 30, "lineHeight" to 1.4)), "section" to padStyleMapOf(utsMapOf("marginBottom" to 30)), "section-title" to padStyleMapOf(utsMapOf("fontSize" to 18, "fontWeight" to "bold", "marginBottom" to 15)), "color-grid" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap", "justifyContent" to "space-between")), "color-item" to padStyleMapOf(utsMapOf("width" to "30%", "marginBottom" to 20)), "color-box" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 100, "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8, "marginBottom" to 8)), "color-name" to padStyleMapOf(utsMapOf("fontSize" to 16, "fontWeight" to "bold", "marginBottom" to 4)), "color-value" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#666666")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
