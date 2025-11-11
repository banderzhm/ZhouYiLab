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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
open class GenPagesAPIElementRequestFullscreenElementRequestFullscreen : BasePage {
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
            this.fullscreenElement = uni_getElementById("fullscreen") as UniElement
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        return createElementVNode("scroll-view", utsMapOf("class" to "content", "direction" to "vertical"), utsArrayOf(
            createElementVNode("view", utsMapOf("id" to "fullscreen", "class" to "view1", "onClick" to _ctx.fullscreen, "onFullscreenchange" to _ctx.fullscreenchange, "onFullscreenerror" to _ctx.fullscreenerror), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "white"))), toDisplayString(_ctx.text), 5)
            ), 40, utsArrayOf(
                "onClick",
                "onFullscreenchange",
                "onFullscreenerror"
            )),
            createVNode(_component_enum_data, utsMapOf("items" to _ctx.orientation_enum, "title" to "orientation", "onChange" to _ctx.radio_change_orientation), null, 8, utsArrayOf(
                "items",
                "onChange"
            )),
            createVNode(_component_enum_data, utsMapOf("items" to _ctx.navigationUI_enum, "title" to "navigationUI", "onChange" to _ctx.radio_change_ui), null, 8, utsArrayOf(
                "items",
                "onChange"
            ))
        ))
    }
    open var orientation_enum: UTSArray<ItemType> by `$data`
    open var navigationUI_enum: UTSArray<ItemType> by `$data`
    open var text: String by `$data`
    open var fullscreenElement: UniElement? by `$data`
    open var isFullscreen: Boolean by `$data`
    open var orientation: String by `$data`
    open var navigationUI: String by `$data`
    open var fullscreenchangeCount: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("orientation_enum" to utsArrayOf<ItemType>(ItemType(value = 0, name = "auto"), ItemType(value = 1, name = "landscape"), ItemType(value = 2, name = "landscape-primary"), ItemType(value = 3, name = "landscape-secondary"), ItemType(value = 4, name = "portrait")), "navigationUI_enum" to utsArrayOf<ItemType>(ItemType(value = 0, name = "auto"), ItemType(value = 1, name = "hide"), ItemType(value = 2, name = "show")), "text" to "点击进入全屏", "fullscreenElement" to null as UniElement?, "isFullscreen" to false, "orientation" to "landscape", "navigationUI" to "hide", "fullscreenchangeCount" to 0)
    }
    open var getCurrentPage = ::gen_getCurrentPage_fn
    open fun gen_getCurrentPage_fn(): UniPage {
        val pages = getCurrentPages()
        return pages[pages.length - 1]
    }
    @get:JvmName("getFullscreen0")
    @set:JvmName("setFullscreen0")
    open var fullscreen = ::gen_fullscreen_fn
    open fun gen_fullscreen_fn() {
        if (this.isFullscreen) {
            val page = this.getCurrentPage()
            page.exitFullscreen(ExitFullscreenOptions(success = fun(){
                this.text = "点击进入全屏"
            }, fail = fun(err){
                console.log("fail", err, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:42")
            }, complete = fun(_result){
                console.log("complete", " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:45")
            }))
        } else {
            this.fullscreenElement?.requestFullscreen(RequestFullscreenOptions(navigationUI = this.navigationUI, orientation = this.orientation, success = fun(){
                this.text = "点击退出全屏"
            }
            , fail = fun(err){
                console.log("fail", err, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:56")
            }
            , complete = fun(_result){
                console.log("complete", " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:59")
            }
            ))
        }
        this.isFullscreen = !this.isFullscreen
    }
    open var fullscreenchange = ::gen_fullscreenchange_fn
    open fun gen_fullscreenchange_fn(e: UniEvent) {
        console.log(e.type, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:67")
        this.fullscreenchangeCount++
        console.log(this.fullscreenchangeCount, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:69")
    }
    open var fullscreenerror = ::gen_fullscreenerror_fn
    open fun gen_fullscreenerror_fn(e: UniEvent) {
        console.log(e.type, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:72")
    }
    open var radio_change_orientation = ::gen_radio_change_orientation_fn
    open fun gen_radio_change_orientation_fn(checked: Number) {
        console.log(checked, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:75")
        this.orientation = this.orientation_enum[checked]["name"] as String
    }
    open var radio_change_ui = ::gen_radio_change_ui_fn
    open fun gen_radio_change_ui_fn(checked: Number) {
        console.log(checked, " at pages/API/element-request-fullscreen/element-request-fullscreen.uvue:79")
        this.navigationUI = this.navigationUI_enum[checked]["name"] as String
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
                return utsMapOf("content" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#f0f0f0")), "view1" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 150, "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#000000")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
