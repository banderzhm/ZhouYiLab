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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
open class GenPagesComponentShareElementShareElementTo : BasePage {
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
        onLoad(fun(event: OnLoadOptions) {
            this.shuttleOnPush = event["shuttleOnPush"] as String
            this.transitionOnGesture = event["transitionOnGesture"] === "true"
            console.log("shuttleOnPush:" + this.shuttleOnPush + " transitionOnGesture:" + this.transitionOnGesture, " at pages/component/share-element/share-element-to.uvue:30")
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createElementVNode("share-element", utsMapOf("class" to "share-element", "share-key" to "left", "shuttle-on-pop" to _ctx.shuttleOnPush, "transition-on-gesture" to _ctx.transitionOnGesture, "onClick" to fun(){
                    _ctx.openPage()
                }
                ), utsArrayOf(
                    createElementVNode("image", utsMapOf("src" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-1.jpg", "mode" to "widthFix")),
                    createElementVNode("view", utsMapOf("class" to "text-label"), utsArrayOf(
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "white"))), "每一口，都是幸福", 4)
                    ))
                ), 8, utsArrayOf(
                    "shuttle-on-pop",
                    "transition-on-gesture",
                    "onClick"
                ))
            )),
            createElementVNode("share-element", utsMapOf("class" to "bottomWrap", "share-key" to "bottom", "onClick" to fun(){
                _ctx.openPage()
            }
            , "transitionOnGesture" to "true"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "bottom"), utsArrayOf(
                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "white"))), "share-element(底部固定)", 4)
                ))
            ), 8, utsArrayOf(
                "onClick"
            ))
        ), 64)
    }
    open var shuttleOnPush: String by `$data`
    open var transitionOnGesture: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("shuttleOnPush" to "to", "transitionOnGesture" to false)
    }
    open var openPage = ::gen_openPage_fn
    open fun gen_openPage_fn() {
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
                return utsMapOf("bottomWrap" to padStyleMapOf(utsMapOf("width" to "100%", "bottom" to 0, "height" to 80, "position" to "fixed")), "bottom" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#007aff")), "text-label" to padStyleMapOf(utsMapOf("position" to "absolute", "backgroundColor" to "#007aff", "opacity" to 0.8, "textAlign" to "center", "justifyContent" to "center", "alignItems" to "center", "bottom" to 0, "left" to 0, "right" to 0, "height" to 30)), "main" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 5, "paddingBottom" to 5, "paddingLeft" to 5, "alignItems" to "center", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
