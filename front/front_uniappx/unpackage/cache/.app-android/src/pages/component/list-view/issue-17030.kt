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
open class GenPagesComponentListViewIssue17030 : BasePage {
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
    open var addData: () -> Unit
        get() {
            return unref(this.`$exposed`["addData"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "addData", value)
        }
    open var getScrollHeight: () -> Number
        get() {
            return unref(this.`$exposed`["getScrollHeight"]) as () -> Number
        }
        set(value) {
            setRefValue(this.`$exposed`, "getScrollHeight", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentListViewIssue17030, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentListViewIssue17030
            val _cache = __ins.renderCache
            val arr = ref(utsArrayOf<Number>(1, 2, 3, 4, 5))
            val addData = fun(){
                arr.value.push(arr.value.length + 1)
            }
            val getScrollHeight = fun(): Number {
                val listViewElement = uni_getElementById("listView") as UniElement
                val scrollHeight = listViewElement.scrollHeight
                console.log(scrollHeight, " at pages/component/list-view/issue-17030.uvue:20")
                return scrollHeight
            }
            __expose(utsMapOf("addData" to addData, "getScrollHeight" to getScrollHeight))
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createElementVNode("list-view", utsMapOf("id" to "listView", "style" to normalizeStyle(utsMapOf("width" to "100%", "background-color" to "red"))), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(arr), fun(item, index, __index, _cached): Any {
                            return createElementVNode("list-item", utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(item), 1)
                            ))
                        }
                        ), 128)
                    ), 4)
                ), 4)
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
                return utsMapOf("title" to padStyleMapOf(utsMapOf("height" to 30, "fontSize" to 18, "color" to "#000000", "textAlign" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
