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
import io.dcloud.uniapp.extapi.`$off` as uni__off
import io.dcloud.uniapp.extapi.`$on` as uni__on
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenComponentsThemeSettings : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onLoad(fun(_: OnLoadOptions) {
            this.currentTheme = themeStore.currentTheme
            this.enableWuxingColors = themeStore.enableWuxingColors
            uni__on("themeChanged", fun(theme: ThemeType){
                this.currentTheme = theme
            }
            )
        }
        , __ins)
        onUnload(fun() {
            uni__off("themeChanged", null)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_switch = resolveComponent("switch")
        return createElementVNode("view", utsMapOf("class" to "theme-settings"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "settings-header"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "settings-title"), "主题设置")
            )),
            createElementVNode("view", utsMapOf("class" to "settings-section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "section-title"), "选择主题"),
                createElementVNode("view", utsMapOf("class" to "theme-grid"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "theme-card",
                        utsMapOf("theme-active" to (_ctx.currentTheme === "light"))
                    )), "onClick" to fun(){
                        _ctx.selectTheme("light")
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "theme-preview theme-preview-light")),
                        createElementVNode("text", utsMapOf("class" to "theme-name"), "浅色主题")
                    ), 10, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "theme-card",
                        utsMapOf("theme-active" to (_ctx.currentTheme === "dark"))
                    )), "onClick" to fun(){
                        _ctx.selectTheme("dark")
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "theme-preview theme-preview-dark")),
                        createElementVNode("text", utsMapOf("class" to "theme-name"), "深色主题")
                    ), 10, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "theme-card",
                        utsMapOf("theme-active" to (_ctx.currentTheme === "gold"))
                    )), "onClick" to fun(){
                        _ctx.selectTheme("gold")
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "theme-preview theme-preview-gold")),
                        createElementVNode("text", utsMapOf("class" to "theme-name"), "黑金主题")
                    ), 10, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "theme-card",
                        utsMapOf("theme-active" to (_ctx.currentTheme === "green"))
                    )), "onClick" to fun(){
                        _ctx.selectTheme("green")
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "theme-preview theme-preview-green")),
                        createElementVNode("text", utsMapOf("class" to "theme-name"), "护眼主题")
                    ), 10, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "theme-card",
                        utsMapOf("theme-active" to (_ctx.currentTheme === "tech"))
                    )), "onClick" to fun(){
                        _ctx.selectTheme("tech")
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "theme-preview theme-preview-tech")),
                        createElementVNode("text", utsMapOf("class" to "theme-name"), "科技主题")
                    ), 10, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "theme-card",
                        utsMapOf("theme-active" to (_ctx.currentTheme === "minimal"))
                    )), "onClick" to fun(){
                        _ctx.selectTheme("minimal")
                    }
                    ), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "theme-preview theme-preview-minimal")),
                        createElementVNode("text", utsMapOf("class" to "theme-name"), "简洁主题")
                    ), 10, utsArrayOf(
                        "onClick"
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "settings-section"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "setting-item"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "setting-label"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "label-text"), "五行配色"),
                        createElementVNode("text", utsMapOf("class" to "label-desc"), "按五行属性显示颜色")
                    )),
                    createVNode(_component_switch, utsMapOf("checked" to _ctx.enableWuxingColors, "onChange" to _ctx.onWuxingColorsChange), null, 8, utsArrayOf(
                        "checked",
                        "onChange"
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "settings-section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "section-title"), "五行颜色预览"),
                createElementVNode("view", utsMapOf("class" to "wuxing-preview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "wuxing-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "wuxing-mu"), "木"),
                        createElementVNode("text", utsMapOf("class" to "wuxing-label"), "#28a745")
                    )),
                    createElementVNode("view", utsMapOf("class" to "wuxing-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "wuxing-huo"), "火"),
                        createElementVNode("text", utsMapOf("class" to "wuxing-label"), "#dc3545")
                    )),
                    createElementVNode("view", utsMapOf("class" to "wuxing-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "wuxing-tu"), "土"),
                        createElementVNode("text", utsMapOf("class" to "wuxing-label"), "#8b7355")
                    )),
                    createElementVNode("view", utsMapOf("class" to "wuxing-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "wuxing-jin"), "金"),
                        createElementVNode("text", utsMapOf("class" to "wuxing-label"), "#ffd700")
                    )),
                    createElementVNode("view", utsMapOf("class" to "wuxing-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "wuxing-shui"), "水"),
                        createElementVNode("text", utsMapOf("class" to "wuxing-label"), "#0d6efd")
                    ))
                ))
            ))
        ))
    }
    open var currentTheme: ThemeType by `$data`
    open var enableWuxingColors: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("currentTheme" to "light" as ThemeType, "enableWuxingColors" to true)
    }
    open var selectTheme = ::gen_selectTheme_fn
    open fun gen_selectTheme_fn(theme: ThemeType) {
        this.currentTheme = theme
        themeStore.setTheme(theme)
        uni_showToast(ShowToastOptions(title = "主题已切换", icon = "success", duration = 1500))
    }
    open var onWuxingColorsChange = ::gen_onWuxingColorsChange_fn
    open fun gen_onWuxingColorsChange_fn(e: Any) {
        this.enableWuxingColors = e.detail.value
        themeStore.toggleWuxingColors()
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("theme-settings" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff", "borderTopLeftRadius" to 12, "borderTopRightRadius" to 12, "borderBottomRightRadius" to 12, "borderBottomLeftRadius" to 12, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "marginTop" to 16, "marginRight" to 16, "marginBottom" to 16, "marginLeft" to 16)), "settings-header" to padStyleMapOf(utsMapOf("marginBottom" to 20, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#dee2e6", "paddingBottom" to 12)), "settings-title" to padStyleMapOf(utsMapOf("fontSize" to 18, "color" to "#212529")), "settings-section" to padStyleMapOf(utsMapOf("marginBottom" to 24)), "setting-item" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "space-between", "alignItems" to "center", "paddingTop" to 16, "paddingRight" to 0, "paddingBottom" to 16, "paddingLeft" to 0, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#f1f3f5", "borderBottomWidth:last-child" to "medium", "borderBottomStyle:last-child" to "none", "borderBottomColor:last-child" to "#000000")), "setting-label" to padStyleMapOf(utsMapOf("flexDirection" to "column", "flex" to 1)), "label-text" to padStyleMapOf(utsMapOf("fontSize" to 16, "color" to "#212529", "marginBottom" to 4)), "label-desc" to padStyleMapOf(utsMapOf("fontSize" to 13, "color" to "#6c757d")), "section-title" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#495057", "marginBottom" to 12)), "theme-grid" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap", "justifyContent" to "space-between")), "theme-card" to padStyleMapOf(utsMapOf("width" to "48%", "marginBottom" to 12, "paddingTop" to 12, "paddingRight" to 12, "paddingBottom" to 12, "paddingLeft" to 12, "backgroundColor" to "#ffffff", "borderTopWidth" to 2, "borderRightWidth" to 2, "borderBottomWidth" to 2, "borderLeftWidth" to 2, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#e0e0e0", "borderRightColor" to "#e0e0e0", "borderBottomColor" to "#e0e0e0", "borderLeftColor" to "#e0e0e0", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8, "flexDirection" to "column", "alignItems" to "center")), "theme-active" to padStyleMapOf(utsMapOf("borderTopColor" to "#28a745", "borderRightColor" to "#28a745", "borderBottomColor" to "#28a745", "borderLeftColor" to "#28a745", "boxShadow" to "0 2px 8px rgba(40, 167, 69, 0.2)")), "theme-preview" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 60, "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6, "marginBottom" to 8)), "theme-preview-light" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(135deg, #f8f9fa 0%, #ffffff 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#dee2e6", "borderRightColor" to "#dee2e6", "borderBottomColor" to "#dee2e6", "borderLeftColor" to "#dee2e6")), "theme-preview-dark" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(135deg, #1a1d20 0%, #2d3338 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#495057", "borderRightColor" to "#495057", "borderBottomColor" to "#495057", "borderLeftColor" to "#495057")), "theme-preview-gold" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(135deg, #0a0a0a 0%, #1a1a1a 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to 2, "borderRightWidth" to 2, "borderBottomWidth" to 2, "borderLeftWidth" to 2, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#ffd700", "borderRightColor" to "#ffd700", "borderBottomColor" to "#ffd700", "borderLeftColor" to "#ffd700")), "theme-preview-green" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(135deg, #e8f5e9 0%, #f1f8f4 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#a5d6a7", "borderRightColor" to "#a5d6a7", "borderBottomColor" to "#a5d6a7", "borderLeftColor" to "#a5d6a7")), "theme-preview-tech" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(135deg, #0d1117 0%, #161b22 100%)", "backgroundColor" to "rgba(0,0,0,0)", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#00d9ff", "borderRightColor" to "#00d9ff", "borderBottomColor" to "#00d9ff", "borderLeftColor" to "#00d9ff", "boxShadow" to "0 0 10px rgba(0, 217, 255, 0.3)")), "theme-preview-minimal" to padStyleMapOf(utsMapOf("backgroundImage" to "none", "backgroundColor" to "#ffffff", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#e0e0e0", "borderRightColor" to "#e0e0e0", "borderBottomColor" to "#e0e0e0", "borderLeftColor" to "#e0e0e0")), "theme-name" to padStyleMapOf(utsMapOf("fontSize" to 13, "color" to "#212529")), "wuxing-preview" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "space-around", "paddingTop" to 16, "paddingRight" to 16, "paddingBottom" to 16, "paddingLeft" to 16, "backgroundColor" to "#f8f9fa", "borderTopLeftRadius" to 8, "borderTopRightRadius" to 8, "borderBottomRightRadius" to 8, "borderBottomLeftRadius" to 8)), "wuxing-item" to padStyleMapOf(utsMapOf("flexDirection" to "column", "alignItems" to "center")), "wuxing-label" to padStyleMapOf(utsMapOf("fontSize" to 10, "color" to "#6c757d")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
