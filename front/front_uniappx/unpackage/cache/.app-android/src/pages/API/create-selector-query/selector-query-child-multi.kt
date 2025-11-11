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
import io.dcloud.uniapp.extapi.createSelectorQuery as uni_createSelectorQuery
open class GenPagesAPICreateSelectorQuerySelectorQueryChildMulti : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onMounted(fun() {
            uni_createSelectorQuery().`in`(this).select(".selector-query-view").boundingClientRect().exec(fun(ret){
                this.text1 = JSON.stringify(ret, null, 2)
                if (ret.length == 1) {
                    this.selectCount = ret.length
                }
                this.testCounter++
                this._dispatchEvent()
            }
            )
            uni_createSelectorQuery().`in`(this).selectAll(".selector-query-view").boundingClientRect().exec(fun(ret){
                this.text2 = JSON.stringify(ret, null, 2)
                if (ret.length == 1) {
                    this.selectAllCount = (ret[0] as UTSArray<NodeInfo>).length
                }
                this.testCounter++
                this._dispatchEvent()
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "selector-query-view"), utsArrayOf(
                createElementVNode("text", null, "selector-query"),
                createElementVNode("text", utsMapOf("class" to "text red"), toDisplayString(_ctx.text1), 1)
            )),
            createElementVNode("view", utsMapOf("class" to "selector-query-view"), utsArrayOf(
                createElementVNode("text", null, "selector-query"),
                createElementVNode("text", utsMapOf("class" to "text green"), toDisplayString(_ctx.text2), 1)
            )),
            if (_ctx.text1.length > 0) {
                createElementVNode("view", utsMapOf("key" to 0), "1")
            } else {
                createCommentVNode("v-if", true)
            }
            ,
            createElementVNode("text", null, toDisplayString(_ctx.text3), 1)
        ), 64)
    }
    open var text1: String by `$data`
    open var text2: String by `$data`
    open var text3: String by `$data`
    open var viewCount: Number by `$data`
    open var selectCount: Number by `$data`
    open var selectAllCount: Number by `$data`
    open var show: Boolean by `$data`
    open var testCounter: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("text1" to "", "text2" to "", "text3" to "test-text-node", "viewCount" to 0, "selectCount" to 0, "selectAllCount" to 0, "show" to false, "testCounter" to 0)
    }
    open var _dispatchEvent = ::gen__dispatchEvent_fn
    open fun gen__dispatchEvent_fn() {
        if (this.testCounter == 2) {
            uni__emit("childReady", null)
        }
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("green" to padStyleMapOf(utsMapOf("borderTopWidth" to 3, "borderRightWidth" to 3, "borderBottomWidth" to 3, "borderLeftWidth" to 3, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#008000", "borderRightColor" to "#008000", "borderBottomColor" to "#008000", "borderLeftColor" to "#008000")), "red" to padStyleMapOf(utsMapOf("borderTopWidth" to 3, "borderRightWidth" to 3, "borderBottomWidth" to 3, "borderLeftWidth" to 3, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FF0000", "borderRightColor" to "#FF0000", "borderBottomColor" to "#FF0000", "borderLeftColor" to "#FF0000")), "view" to padStyleMapOf(utsMapOf("borderTopWidth" to 3, "borderRightWidth" to 3, "borderBottomWidth" to 3, "borderLeftWidth" to 3, "borderTopStyle" to "dashed", "borderRightStyle" to "dashed", "borderBottomStyle" to "dashed", "borderLeftStyle" to "dashed", "borderTopColor" to "#00FF00", "borderRightColor" to "#00FF00", "borderBottomColor" to "#00FF00", "borderLeftColor" to "#00FF00", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "text" to padStyleMapOf(utsMapOf("marginTop" to 20, "paddingTop" to 5, "paddingRight" to 5, "paddingBottom" to 5, "paddingLeft" to 5)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
