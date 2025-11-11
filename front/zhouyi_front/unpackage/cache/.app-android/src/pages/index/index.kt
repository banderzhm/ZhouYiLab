@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uni.UNICA45710
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
open class GenPagesIndexIndex : BasePage {
    constructor(__ins: ComponentInternalInstance, __renderer: String?) : super(__ins, __renderer) {
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("image", utsMapOf("class" to "logo", "src" to "/static/logo.png")),
            createElementVNode("view", utsMapOf("class" to "text-area"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(_ctx.title), 1)
            )),
            createElementVNode("view", utsMapOf("class" to "menu-area"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "menu-item", "onClick" to fun(){
                    _ctx.navigateTo("/pages/daluren/daluren")
                }
                ), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "menu-icon"), "☯️"),
                    createElementVNode("text", utsMapOf("class" to "menu-title"), "大六壬排盘"),
                    createElementVNode("text", utsMapOf("class" to "menu-desc"), "传统术数排盘工具")
                ), 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", utsMapOf("class" to "menu-item disabled"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "menu-icon"), "🔮"),
                    createElementVNode("text", utsMapOf("class" to "menu-title"), "六爻起卦"),
                    createElementVNode("text", utsMapOf("class" to "menu-desc"), "即将推出")
                )),
                createElementVNode("view", utsMapOf("class" to "menu-item disabled"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "menu-icon"), "⭐"),
                    createElementVNode("text", utsMapOf("class" to "menu-title"), "紫微斗数"),
                    createElementVNode("text", utsMapOf("class" to "menu-desc"), "即将推出")
                )),
                createElementVNode("view", utsMapOf("class" to "menu-item disabled"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "menu-icon"), "🎋"),
                    createElementVNode("text", utsMapOf("class" to "menu-title"), "八字排盘"),
                    createElementVNode("text", utsMapOf("class" to "menu-desc"), "即将推出")
                )),
                createElementVNode("view", utsMapOf("class" to "menu-item", "onClick" to fun(){
                    _ctx.navigateTo("/pages/settings/settings")
                }
                ), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "menu-icon"), "🎨"),
                    createElementVNode("text", utsMapOf("class" to "menu-title"), "主题设置"),
                    createElementVNode("text", utsMapOf("class" to "menu-desc"), "六种主题 · 五行配色")
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "周易实验室")
    }
    open var navigateTo = ::gen_navigateTo_fn
    open fun gen_navigateTo_fn(url: String) {
        uni_navigateTo(NavigateToOptions(url = url))
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#f8f9fa")), "logo" to padStyleMapOf(utsMapOf("height" to 100, "width" to 100, "marginTop" to 60, "marginRight" to "auto", "marginBottom" to 25, "marginLeft" to "auto")), "text-area" to padStyleMapOf(utsMapOf("textAlign" to "center", "marginBottom" to 40)), "title" to padStyleMapOf(utsMapOf("fontSize" to 24, "color" to "#212529", "textAlign" to "center")), "menu-area" to padStyleMapOf(utsMapOf("paddingTop" to 0, "paddingRight" to 24, "paddingBottom" to 0, "paddingLeft" to 24)), "menu-item" to utsMapOf("" to utsMapOf("backgroundColor" to "#ffffff", "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "marginBottom" to 16, "boxShadow" to "0 2px 8px rgba(0, 0, 0, 0.08)", "flexDirection" to "row", "alignItems" to "center", "position" to "relative"), ".disabled" to utsMapOf("opacity" to 0.5)), "menu-icon" to padStyleMapOf(utsMapOf("fontSize" to 32, "marginRight" to 16)), "menu-title" to padStyleMapOf(utsMapOf("fontSize" to 18, "color" to "#212529", "marginBottom" to 4)), "menu-desc" to padStyleMapOf(utsMapOf("fontSize" to 13, "color" to "#6c757d", "position" to "absolute", "left" to 68, "bottom" to 18)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
