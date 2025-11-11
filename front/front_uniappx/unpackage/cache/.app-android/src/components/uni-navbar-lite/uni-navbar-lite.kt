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
import uts.sdk.modules.uniStat.ReportOptions as ReportOptions
import io.dcloud.uniapp.extapi.loadFontFace as uni_loadFontFace
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import uts.sdk.modules.uniStat.report as uni_report
import io.dcloud.uniapp.extapi.setNavigationBarColor as uni_setNavigationBarColor
open class GenComponentsUniNavbarLiteUniNavbarLite : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var title: String by `$props`
    open var isLeft: Boolean by `$props`
    open var textColor: String by `$props`
    open var statusBar: Boolean by `$props`
    open var stat: Boolean by `$props`
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenComponentsUniNavbarLiteUniNavbarLite) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenComponentsUniNavbarLiteUniNavbarLite
            val _cache = __ins.renderCache
            val props = __props
            var statusBarHeight = ref(0)
            val unicode = computed(fun(): String {
                return "\ue600"
            }
            )
            onMounted(fun(){
                uni_loadFontFace(LoadFontFaceOptions(global = false, family = "UniIconsFontFamily", source = "url(\"" + default8 + "\")", success = fun(_) {}, fail = fun(err) {
                    console.log(err, " at components/uni-navbar-lite/uni-navbar-lite.uvue:56")
                }
                ))
                uni_setNavigationBarColor(SetNavigationBarColorOptions(frontColor = "#000000", backgroundColor = "#ffffff"))
                if (props.stat && props.title != "") {
                    uni_report(ReportOptions(name = "title", options = props.title))
                }
            }
            )
            val back = fun(){
                uni_navigateBack(NavigateBackOptions())
            }
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "uni-navbar"), utsArrayOf(
                    if (isTrue(_ctx.statusBar)) {
                        createElementVNode("view", utsMapOf("key" to 0, "class" to "status-bar"))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("view", utsMapOf("class" to "uni-navbar-inner"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "left-content", "onClick" to back), utsArrayOf(
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to _ctx.textColor)), "class" to "uni-icons"), toDisplayString(unref(unicode)), 5)
                        )),
                        createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                            "uni-navbar-content",
                            utsMapOf("is-left" to _ctx.isLeft)
                        ))), utsArrayOf(
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to _ctx.textColor))), utsArrayOf(
                                renderSlot(_ctx.`$slots`, "default", UTSJSONObject(), fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        toDisplayString(_ctx.title)
                                    )
                                }
                                )
                            ), 4)
                        ), 2),
                        createElementVNode("view", utsMapOf("class" to "right-content"), utsArrayOf(
                            renderSlot(_ctx.`$slots`, "right")
                        ))
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("uni-icons" to padStyleMapOf(utsMapOf("!fontFamily" to "UniIconsFontFamily", "fontSize" to 26, "fontStyle" to "normal", "color" to "#333333")), "status-bar" to padStyleMapOf(utsMapOf("height" to CSS_VAR_STATUS_BAR_HEIGHT)), "uni-navbar" to padStyleMapOf(utsMapOf("backgroundColor" to "#007aff")), "uni-navbar-inner" to padStyleMapOf(utsMapOf("position" to "relative", "flexDirection" to "row", "justifyContent" to "space-between", "height" to 45)), "left-content" to padStyleMapOf(utsMapOf("justifyContent" to "center", "alignItems" to "center", "width" to 40, "height" to "100%")), "right-content" to padStyleMapOf(utsMapOf("justifyContent" to "center", "alignItems" to "center", "width" to 40, "height" to "100%")), "uni-navbar-content" to padStyleMapOf(utsMapOf("position" to "absolute", "height" to "100%", "top" to 0, "bottom" to 0, "left" to 45, "right" to 45, "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center")), "is-left" to padStyleMapOf(utsMapOf("justifyContent" to "flex-start")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("title" to utsMapOf("type" to "String", "default" to ""), "isLeft" to utsMapOf("type" to "Boolean", "default" to false), "textColor" to utsMapOf("type" to "String", "default" to "#000"), "statusBar" to utsMapOf("type" to "Boolean", "default" to false), "stat" to utsMapOf("type" to "Boolean", "default" to false)))
        var propsNeedCastKeys = utsArrayOf(
            "title",
            "isLeft",
            "textColor",
            "statusBar",
            "stat"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
