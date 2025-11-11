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
open class GenPagesAPIDialogPageDialog11 : BasePage {
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
        onReady(fun() {
            console.log("dialog 1-1 onReady", " at pages/API/dialog-page/dialog-1-1.uvue:23")
            setLifeCycleNum(state.lifeCycleNum + 2)
        }
        , __ins)
        onUnload(fun() {
            console.log("dialog 1-1 onUnload", " at pages/API/dialog-page/dialog-1-1.uvue:28")
            setLifeCycleNum(state.lifeCycleNum - 5)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("id" to "dialog1", "class" to "dialog-container"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "dialog-content"), utsArrayOf(
                createElementVNode("text", null, "title: " + toDisplayString(_ctx.title), 1),
                createElementVNode("button", utsMapOf("onClick" to _ctx.closeThisDialog), "Close", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "dialog 1-1")
    }
    open var closeThisDialog = ::gen_closeThisDialog_fn
    open fun gen_closeThisDialog_fn() {
        uni_closeDialogPage(CloseDialogPageOptions(dialogPage = this.`$page`))
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
                return utsMapOf("dialog-container" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "backgroundColor" to "rgba(0,0,0,0.3)", "display" to "flex", "justifyContent" to "center", "alignItems" to "center")), "dialog-content" to padStyleMapOf(utsMapOf("width" to "80%", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
