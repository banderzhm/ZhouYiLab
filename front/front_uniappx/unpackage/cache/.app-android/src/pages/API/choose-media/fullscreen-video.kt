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
import io.dcloud.uniapp.extapi.`$emit` as uni__emit
import io.dcloud.uniapp.extapi.`$once` as uni__once
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
open class GenPagesAPIChooseMediaFullscreenVideo : BasePage {
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
        onLoad(fun(options: OnLoadOptions) {
            uni__once("__ONRECEIVEURL", fun(value: UTSJSONObject){
                this.url = value["url"] as String
                this.coverPath = value["cover"] as String
            }
            )
            uni__emit("__ONFULLVIDEOLOAD", null)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "back", "onClick" to _ctx.back, "class" to "nav-back"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "back-img", "src" to "/static/template/scroll-fold-nav/back.png", "mode" to "widthFix"))
            ), 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("video", utsMapOf("ref" to "video", "class" to "video-box", "src" to _ctx.url, "controls" to "false", "autoplay" to "true", "show-progress" to "false", "show-fullscreen-btn" to "false", "show-play-btn" to "false", "show-center-play-btn" to "false"), null, 8, utsArrayOf(
                "src"
            ))
        ), 4)
    }
    open var url: String by `$data`
    open var coverPath: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("url" to "", "coverPath" to "")
    }
    open var back = ::gen_back_fn
    open fun gen_back_fn() {
        uni_navigateBack(null)
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
                return utsMapOf("nav-back" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 35, "backgroundColor" to "rgba(220,220,220,0.8)", "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "marginTop" to 6, "marginRight" to 6, "marginBottom" to 6, "marginLeft" to 6, "width" to 32, "height" to 32, "justifyContent" to "center", "alignItems" to "center", "zIndex" to 10)), "back-img" to utsMapOf(".nav-back " to utsMapOf("width" to 18, "height" to 18)), "video-box" to padStyleMapOf(utsMapOf("width" to "100%", "flex" to 1, "height" to "100%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
