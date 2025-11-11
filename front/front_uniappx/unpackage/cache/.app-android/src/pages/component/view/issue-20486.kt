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
open class GenPagesComponentViewIssue20486 : BasePage {
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
    open var getTestViewY: () -> Number
        get() {
            return unref(this.`$exposed`["getTestViewY"]) as () -> Number
        }
        set(value) {
            setRefValue(this.`$exposed`, "getTestViewY", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentViewIssue20486, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentViewIssue20486
            val _cache = __ins.renderCache
            val isDarkMode = ref(false)
            val testViewY = ref(0)
            onReady(fun(){
                var ele = uni_getElementById("testView") as UniElement
                val currentPage = getCurrentInstance()!!.proxy!!.`$page`
                testViewY.value = ele.getBoundingClientRect().y + currentPage.statusBarHeight + 44
                console.log(testViewY.value, " at pages/component/view/issue-20486.uvue:21")
            }
            )
            val toggleTheme = fun(){
                isDarkMode.value = !isDarkMode.value
            }
            fun gen_getTestViewY_fn(): Number {
                return testViewY.value
            }
            val getTestViewY = ::gen_getTestViewY_fn
            __expose(utsMapOf("getTestViewY" to getTestViewY))
            return fun(): Any? {
                return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "class" to normalizeClass(if (unref(isDarkMode)) {
                    "dark"
                } else {
                    "light"
                }
                )), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to normalizeClass(if (unref(isDarkMode)) {
                            "darkText"
                        } else {
                            "lightText"
                        }
                        )), "当前模式：" + toDisplayString(if (unref(isDarkMode)) {
                            "dark"
                        } else {
                            "light"
                        }
                        ), 3)
                    )),
                    createElementVNode("view", utsMapOf("id" to "testView", "hover-class" to "btn-hover", "class" to "btn", "onClick" to toggleTheme), utsArrayOf(
                        createElementVNode("text", null, "切换:" + toDisplayString(if (unref(isDarkMode)) {
                            "dark"
                        } else {
                            "light"
                        }
                        ) + " (应该无背景色)", 1)
                    ))
                ), 6)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("dark" to padStyleMapOf(utsMapOf("-LBoxBgColor" to "#000")), "lightText" to padStyleMapOf(utsMapOf("color" to "#000000")), "darkText" to padStyleMapOf(utsMapOf("color" to "#FFFFFF")), "btn" to padStyleMapOf(utsMapOf("height" to 50, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "btn-hover" to padStyleMapOf(utsMapOf("backgroundColor" to "var(--l-btn-hover-color, red)")), "box" to padStyleMapOf(utsMapOf("width" to 140, "height" to 140, "backgroundColor" to "var(--l-box-bg-color, #f5f5f5)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
