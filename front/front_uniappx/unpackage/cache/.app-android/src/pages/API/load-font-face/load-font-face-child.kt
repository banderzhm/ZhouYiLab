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
open class GenPagesAPILoadFontFaceLoadFontFaceChild : BasePage {
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "loadFontFace-child")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "font-size-20"), "上一页全局加载字体："),
                createElementVNode("text", utsMapOf("class" to "font-size-20 line-height-40", "style" to normalizeStyle(utsMapOf("font-family" to "UniFontFamily"))), "font-family: uni.ttf", 4),
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row"))), utsArrayOf(
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-family" to "UniFontFamily", "font-size" to "20px"))), toDisplayString(_ctx.uniIcon1), 5),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "5px", "margin-right" to "20px", "line-height" to "22px"))), "\\ue100", 4),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-family" to "UniFontFamily", "font-size" to "20px"))), toDisplayString(_ctx.uniIcon2), 5),
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "5px", "line-height" to "22px"))), "\\ue101", 4)
                ), 4),
                createElementVNode("text", utsMapOf("class" to "uni-common-mt font-size-20"), "上一页非全局加载字体（不生效为正常）："),
                createElementVNode("text", utsMapOf("class" to "font-size-20 line-height-40", "style" to normalizeStyle(utsMapOf("font-family" to "AlimamaDaoLiTiTTF"))), "font-family: 阿里妈妈刀隶体-ttf", 4),
                createElementVNode("text", utsMapOf("class" to "font-size-20 line-height-40", "style" to normalizeStyle(utsMapOf("font-family" to "AlimamaDaoLiTiOTF"))), "font-family: 阿里妈妈刀隶体-otf", 4)
            ))
        ), 64)
    }
    open var uniIcon1: String by `$data`
    open var uniIcon2: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("uniIcon1" to "\ue100", "uniIcon2" to "\ue101")
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
                return utsMapOf("font-size-20" to padStyleMapOf(utsMapOf("fontSize" to 20)), "line-height-40" to padStyleMapOf(utsMapOf("lineHeight" to "40px")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
