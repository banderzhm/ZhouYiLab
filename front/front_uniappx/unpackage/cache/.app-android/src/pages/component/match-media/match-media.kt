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
open class GenPagesComponentMatchMediaMatchMedia : BasePage {
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
        val _component_match_media = resolveComponent("match-media", true)
        return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "subtitle"), "基础条件匹配"),
                createVNode(_component_match_media, utsMapOf("min-width" to "400"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "屏幕宽度 ≥ 400px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("max-width" to "600"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "屏幕宽度 ≤ 600px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("width" to "375"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "屏幕宽度 = 375px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("min-height" to "600"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "屏幕高度 ≥ 600px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("max-height" to "800"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "屏幕高度 ≤ 800px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("height" to "667"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "屏幕高度 = 667px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("orientation" to "portrait"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "竖屏 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("orientation" to "landscape"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "横屏 时显示")
                    )
                }
                ), "_" to 1))
            )),
            createElementVNode("view", utsMapOf("class" to "section"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "subtitle"), "组合条件匹配"),
                createVNode(_component_match_media, utsMapOf("min-width" to "500", "max-width" to "1000", "orientation" to "landscape"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "横屏 且宽度在 500px ~ 1000px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("min-height" to "200", "max-height" to "800", "orientation" to "portrait"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "竖屏 且高度在 200px ~ 800px 时显示")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_match_media, utsMapOf("min-width" to "300", "max-width" to "900", "min-height" to "400", "max-height" to "800", "orientation" to "portrait"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "demo-box"), "竖屏 且宽度在 300px ~ 900px，高度在 400px ~ 800px 时显示")
                    )
                }
                ), "_" to 1))
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20)), "section" to padStyleMapOf(utsMapOf("paddingTop" to 5)), "subtitle" to padStyleMapOf(utsMapOf("fontSize" to 20, "fontWeight" to "bold", "marginBottom" to 5)), "demo-box" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
