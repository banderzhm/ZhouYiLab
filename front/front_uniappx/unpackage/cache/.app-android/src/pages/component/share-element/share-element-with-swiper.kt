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
open class GenPagesComponentShareElementShareElementWithSwiper : BasePage {
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
            createVNode(_component_page_head, utsMapOf("title" to "share-element")),
            createElementVNode("view", utsMapOf("class" to "main"), utsArrayOf(
                createElementVNode("swiper", utsMapOf("circular" to "true"), utsArrayOf(
                    createElementVNode("swiper-item", null, utsArrayOf(
                        createElementVNode("share-element", utsMapOf("class" to "share-element", "share-key" to "left", "shuttle-on-pop" to _ctx.shuttleOnPopType, "transition-on-gesture" to _ctx.transitionOnGesture, "shuttle-on-push" to _ctx.shuttleOnPushType, "easing-function" to _ctx.easingFunctionType, "onClick" to fun(){
                            _ctx.openPage()
                        }
                        ), utsArrayOf(
                            createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "150px")), "src" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-1.jpg"), null, 4)
                        ), 8, utsArrayOf(
                            "shuttle-on-pop",
                            "transition-on-gesture",
                            "shuttle-on-push",
                            "easing-function",
                            "onClick"
                        ))
                    ))
                ))
            )),
            createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.openPage, "class" to "button"), " 打开share-element页面 ", 8, utsArrayOf(
                "onClick"
            ))
        ), 64)
    }
    open var transitionOnGesture: Boolean by `$data`
    open var shuttleOnPopType: ShuttleOnType1 by `$data`
    open var shuttleOnPushType: ShuttleOnType1 by `$data`
    open var shuttleOnTypeList: UTSArray<String> by `$data`
    open var openAnimationType: ShareElementOpenAnimationType1 by `$data`
    open var openAnimationTypeList: UTSArray<String> by `$data`
    open var easingFunctionType: ShareElementEasingFunctionType1 by `$data`
    open var easingFunctionTypeList: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("transitionOnGesture" to true, "shuttleOnPopType" to "to" as ShuttleOnType1, "shuttleOnPushType" to "to" as ShuttleOnType1, "shuttleOnTypeList" to utsArrayOf(
            "from",
            "to"
        ), "openAnimationType" to "slide-in-right" as ShareElementOpenAnimationType1, "openAnimationTypeList" to utsArrayOf(
            "auto",
            "none",
            "slide-in-right",
            "slide-in-left",
            "slide-in-top",
            "slide-in-bottom",
            "fade-in",
            "pop-in",
            "zoom-out",
            "zoom-fade-out"
        ), "easingFunctionType" to "ease" as ShareElementEasingFunctionType1, "easingFunctionTypeList" to utsArrayOf(
            "ease",
            "ease-in",
            "ease-out",
            "ease-in-out",
            "linear"
        ))
    }
    open var openPage = ::gen_openPage_fn
    open fun gen_openPage_fn() {
        uni_navigateTo(NavigateToOptions(animationType = this.openAnimationType, url = "/pages/component/share-element/share-element-to?shuttleOnPush=" + this.shuttleOnPushType + "&transitionOnGesture=" + this.transitionOnGesture))
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
                return utsMapOf("ml-10" to padStyleMapOf(utsMapOf("marginLeft" to 10)), "choose-property-title" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "choose-property-type-radio-group" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")), "bottomWrap" to padStyleMapOf(utsMapOf("width" to "100%", "bottom" to 0, "height" to 80, "position" to "fixed")), "bottom" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#007aff")), "main" to padStyleMapOf(utsMapOf("paddingTop" to 5, "paddingRight" to 0, "paddingBottom" to 5, "paddingLeft" to 0, "alignItems" to "center", "justifyContent" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
