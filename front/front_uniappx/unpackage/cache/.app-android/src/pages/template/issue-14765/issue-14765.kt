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
import io.dcloud.uniapp.extapi.createSelectorQuery as uni_createSelectorQuery
open class GenPagesTemplateIssue14765Issue14765 : BasePage {
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
            uni_createSelectorQuery().`in`(this).select("#page-id-2").boundingClientRect().exec(fun(ret){
                console.log(ret, " at pages/template/issue-14765/issue-14765.uvue:26")
                this.testNode2 = ret.length > 0
                if (this.testNode2) {
                    console.log("Comp2测试符合预期", " at pages/template/issue-14765/issue-14765.uvue:32")
                } else {
                    console.error("Comp2测试不符合预期", " at pages/template/issue-14765/issue-14765.uvue:34")
                }
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_Comp1 = resolveComponent("Comp1")
        val _component_Comp2 = resolveComponent("Comp2")
        return createElementVNode("view", null, utsArrayOf(
            createVNode(_component_Comp1, utsMapOf("id" to _ctx.id1, "onReady" to _ctx.onComp1Ready), null, 8, utsArrayOf(
                "id",
                "onReady"
            )),
            createVNode(_component_Comp2, utsMapOf("id" to _ctx.id2), null, 8, utsArrayOf(
                "id"
            ))
        ))
    }
    open var id1: String by `$data`
    open var id2: String by `$data`
    open var testNode1: Boolean by `$data`
    open var testNode2: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("id1" to "page-id-1", "id2" to "page-id-2", "testNode1" to false, "testNode2" to false)
    }
    open var onComp1Ready = ::gen_onComp1Ready_fn
    open fun gen_onComp1Ready_fn(testNode1: Boolean) {
        this.testNode1 = testNode1
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf("Comp1" to GenPagesTemplateIssue14765Comp1Class, "Comp2" to GenPagesTemplateIssue14765Comp2Class)
    }
}
