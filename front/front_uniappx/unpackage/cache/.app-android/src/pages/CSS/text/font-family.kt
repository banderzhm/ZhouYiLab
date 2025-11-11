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
open class GenPagesCSSTextFontFamily : BasePage {
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
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("font-family" to "monospace"))), "font-family: monospace", 4),
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("font-family" to "cursive"))), "font-family: cursive", 4),
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("font-family" to "AlimamaDaoLiTiTTF"))), "font-family: 阿里妈妈刀隶体-ttf（网络字体下载后生效）", 4),
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("font-family" to "AlimamaDaoLiTiOTF"))), "font-family: 阿里妈妈刀隶体-otf", 4),
                createElementVNode("text", utsMapOf("class" to "common", "style" to normalizeStyle(utsMapOf("font-family" to "AlimamaDaoLiTiOTF", "font-weight" to "bold", "font-style" to "italic"))), "font-family: 阿里妈妈刀隶体-otf（粗斜体）", 4),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-family" to "UniFontFamily"))), "style 加载非 static 目录字体文件：" + toDisplayString(_ctx.uniIcon), 5)
            )),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "24px 12px"))), utsArrayOf(
                createElementVNode("button", utsMapOf("type" to "default", "onClick" to _ctx.openUniIcon), "内置字体图标uni-icon示例", 8, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 64)
    }
    open var uniIcon: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("uniIcon" to "\ue100")
    }
    open var openUniIcon = ::gen_openUniIcon_fn
    open fun gen_openUniIcon_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/CSS/text/font-family-icon"))
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
                return utsMapOf("common" to padStyleMapOf(utsMapOf("fontSize" to 20, "lineHeight" to "40px")), "@FONT-FACE" to utsMapOf("0" to utsMapOf("fontFamily" to "AlimamaDaoLiTiTTF", "src" to "url('https://qiniu-web-assets.dcloud.net.cn/uni-app-x/static/font/AlimamaDaoLiTi.ttf')"), "1" to utsMapOf("fontFamily" to "AlimamaDaoLiTiOTF", "src" to "url('/static/font/AlimamaDaoLiTi.otf')"), "2" to utsMapOf("fontFamily" to "UniFontFamily", "src" to "url('/assets/uni.173d2b94.ttf')")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
