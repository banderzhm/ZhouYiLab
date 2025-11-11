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
import io.dcloud.uniapp.extapi.closeDialogPage as uni_closeDialogPage
open class GenPagesAPIGetUniVerifyManagerFullWebviewPage : BasePage {
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
            this.url = options["url"] as String
            this.title = options["title"] as String
            this.animationType = options["animationType"] as String
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "background"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "nav"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "left_content"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "left_icon", "onClick" to fun(){
                            _ctx.onBack()
                        }
                        ), toDisplayString(_ctx.backIcon), 9, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "title_content"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(_ctx.title), 1)
                    ))
                )),
                createElementVNode("web-view", utsMapOf("class" to "web_container", "src" to _ctx.url), null, 8, utsArrayOf(
                    "src"
                ))
            ))
        ))
    }
    open var url: String by `$data`
    open var title: String by `$data`
    open var animationType: String by `$data`
    open var backIcon: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("url" to "", "title" to "", "animationType" to "slide-out-bottom", "backIcon" to "\ue601")
    }
    open var onBack = ::gen_onBack_fn
    open fun gen_onBack_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`, animationType = this.animationType, success = fun(res) {
            console.log("closeThisDialog success", res, " at pages/API/get-uni-verify-manager/full-webview-page.uvue:40")
        }
        , fail = fun(err) {
            console.log("closeThisDialog fail", err, " at pages/API/get-uni-verify-manager/full-webview-page.uvue:43")
        }
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
                return utsMapOf("background" to padStyleMapOf(utsMapOf("backgroundColor" to "#007aff", "width" to "100%", "height" to "100%")), "nav" to padStyleMapOf(utsMapOf("height" to 45, "width" to "100%", "position" to "relative", "flexDirection" to "row", "justifyContent" to "space-between")), "left_content" to padStyleMapOf(utsMapOf("justifyContent" to "center", "alignItems" to "center", "width" to 40, "height" to "100%")), "left_icon" to padStyleMapOf(utsMapOf("color" to "#FFFFFF", "top" to 5, "height" to "100%", "fontFamily" to "uni-icon", "fontSize" to 26)), "title_content" to padStyleMapOf(utsMapOf("position" to "absolute", "height" to "100%", "top" to 0, "bottom" to 0, "left" to 45, "right" to 45, "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center")), "title" to padStyleMapOf(utsMapOf("color" to "#FFFFFF", "fontSize" to 17)), "container" to padStyleMapOf(utsMapOf("paddingTop" to CSS_VAR_STATUS_BAR_HEIGHT, "width" to "100%", "position" to "absolute", "height" to "100%")), "web_container" to padStyleMapOf(utsMapOf("backgroundColor" to "#f1f1f1", "paddingTop" to 10, "flex" to 1, "width" to "100%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
