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
import io.dcloud.uniapp.extapi.getAppBaseInfo as uni_getAppBaseInfo
import uts.sdk.modules.utsOpenSchema.openSchema
open class GenPagesTemplateAboutAbout : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            this.version = uni_getAppBaseInfo(null).appVersion ?: ""
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "class" to normalizeClass(if (_ctx.isDarkMode) {
            "theme-dark"
        } else {
            "theme-light"
        }
        )), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "qrcode", "src" to "https://web-ext-storage.dcloud.net.cn/uni-app-x/hello-uniappx-qrcode.png")),
                createElementVNode("text", utsMapOf("class" to "tip"), "扫码体验 uni-app x"),
                createElementVNode("text", utsMapOf("class" to "desc"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "code"), "uni-app x "),
                    createElementVNode("text", utsMapOf("class" to "desc"), "是下一代uni-app，基于uts的、兼容vue语法的跨平台开发框架。")
                )),
                createElementVNode("text", utsMapOf("class" to "source desc"), "本应用源码获取方式："),
                createElementVNode("view", utsMapOf("class" to "sourcelist"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "desc", "space" to "nbsp"), "1. "),
                    createElementVNode("text", utsMapOf("class" to "desc"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "desc"), "在 HBuilderX 中新建 uni-app 项目时选择 "),
                        createElementVNode("text", utsMapOf("class" to "code"), "Hello uni-app x"),
                        createElementVNode("text", utsMapOf("class" to "desc"), " 模板")
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "sourcelist"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "desc", "space" to "nbsp"), "2. "),
                    createElementVNode("text", utsMapOf("class" to "desc link", "onClick" to fun(){
                        _ctx.openUrl("https://gitcode.com/dcloud/hello-uni-app-x")
                    }
                    ), "https://gitcode.com/dcloud/hello-uni-app-x", 8, utsArrayOf(
                        "onClick"
                    ))
                ))
            )),
            createElementVNode("view", utsMapOf("class" to "panel"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "uni-panel-text title"), "服务协议"),
                createElementVNode("view", utsMapOf("class" to "item"), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding-left" to "18px", "border-top-style" to "none")), "class" to "uni-navigate-item", "hover-class" to "is--active", "onClick" to fun(){
                        _ctx.openUrl("https://dcloud.io/license/DCloud.html")
                    }
                    ), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-navigate-text"), "用户协议"),
                        createElementVNode("image", utsMapOf("src" to _ctx.arrowRightIcon, "class" to "uni-icon-size"), null, 8, utsArrayOf(
                            "src"
                        ))
                    ), 12, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("padding-left" to "18px")), "class" to "uni-navigate-item", "hover-class" to "is--active", "onClick" to fun(){
                        _ctx.openUrl("https://dcloud.io/license/hello-uni-app-x.html")
                    }
                    ), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-navigate-text"), "隐私政策"),
                        createElementVNode("image", utsMapOf("src" to _ctx.arrowRightIcon, "class" to "uni-icon-size"), null, 8, utsArrayOf(
                            "src"
                        ))
                    ), 12, utsArrayOf(
                        "onClick"
                    ))
                ))
            )),
            createElementVNode("text", utsMapOf("class" to "version"), "当前版本：" + toDisplayString(_ctx.version), 1)
        ), 6)
    }
    open var version: String by `$data`
    open var arrowRightIcon: String by `$data`
    open var isDarkMode: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("version" to "", "arrowRightIcon" to "/static/icons/arrow-right.png", "isDarkMode" to computed<Boolean>(fun(): Boolean {
            return state.isDarkMode
        }
        ))
    }
    open var openUrl = ::gen_openUrl_fn
    open fun gen_openUrl_fn(url: String) {
        openSchema(url)
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
                return utsMapOf("content" to padStyleMapOf(utsMapOf("width" to "100%", "paddingTop" to 16, "paddingRight" to 16, "paddingBottom" to 16, "paddingLeft" to 16, "alignItems" to "center")), "qrcode" to padStyleMapOf(utsMapOf("width" to 160, "height" to 160)), "tip" to padStyleMapOf(utsMapOf("marginTop" to 12, "marginRight" to 12, "marginBottom" to 12, "marginLeft" to 12, "fontSize" to 14, "color" to "var(--text-color)")), "desc" to padStyleMapOf(utsMapOf("fontSize" to 14, "lineHeight" to "18px", "color" to "var(--text-color)")), "code" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#e96900")), "source" to padStyleMapOf(utsMapOf("alignSelf" to "flex-start", "marginTop" to 18, "color" to "var(--text-color)")), "sourcelist" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignSelf" to "flex-start", "alignContent" to "flex-start")), "link" to padStyleMapOf(utsMapOf("color" to "#007AFF", "textDecorationLine" to "underline")), "version" to padStyleMapOf(utsMapOf("width" to "100%", "marginTop" to 18, "marginRight" to 0, "marginBottom" to 18, "marginLeft" to 0, "fontSize" to 14, "lineHeight" to "18px", "textAlign" to "center", "color" to "#cccccc")), "panel" to padStyleMapOf(utsMapOf("marginTop" to 18, "marginRight" to 18, "marginBottom" to 18, "marginLeft" to 18)), "title" to padStyleMapOf(utsMapOf("marginTop" to 8, "marginRight" to 8, "marginBottom" to 8, "marginLeft" to 8, "fontSize" to 14)), "item" to padStyleMapOf(utsMapOf("borderTopLeftRadius" to 6, "borderTopRightRadius" to 6, "borderBottomRightRadius" to 6, "borderBottomLeftRadius" to 6)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
