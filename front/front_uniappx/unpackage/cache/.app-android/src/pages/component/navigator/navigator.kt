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
open class GenPagesComponentNavigatorNavigator : BasePage {
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
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_navigator = resolveComponent("navigator", true)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createVNode(_component_navigator, utsMapOf("id" to "navigate", "class" to "navigator", "url" to "navigate?title=navigate"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), "跳转到新页面")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("id" to "redirect", "class" to "navigator", "url" to "redirect?title=redirect", "open-type" to "redirect"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), "在当前页打开redirect")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("id" to "switchTab", "class" to "navigator", "url" to "/pages/tabBar/template", "open-type" to "switchTab"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), "切换到模板选项卡switchTab")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("id" to "reLaunch", "class" to "navigator", "url" to "/pages/tabBar/component", "open-type" to "reLaunch"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), "关闭所有页面回首页reLaunch")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("id" to "reLaunch", "class" to "navigator", "open-type" to "navigateBack"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), "返回上一页navigateBack")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("id" to "navigateToErrorPage", "class" to "navigator", "url" to "/pages/error-page/error-page"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), " 打开不存在的页面 ")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("id" to "navigateToErrorPage", "class" to "navigator"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "default"), " 未指定URL的跳转 ")
                    )
                }
                ), "_" to 1)),
                createVNode(_component_navigator, utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row", "justify-content" to "space-between"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode("text", null, "两端对齐样式测试"),
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "20px", "height" to "20px", "background-color" to "aqua"))), null, 4)
                    )
                }
                ), "_" to 1), 8, utsArrayOf(
                    "style"
                ))
            ))
        ), 64)
    }
    open var title: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "navigator")
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
                return utsMapOf("navigator" to padStyleMapOf(utsMapOf("marginBottom" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
