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
open class GenPagesTemplateIssue14765Comp1 : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onMounted(fun() {
            uni_createSelectorQuery().`in`(this).select("#comp1-id").boundingClientRect().exec(fun(ret){
                console.log(ret, " at pages/template/issue-14765/Comp1.uvue:25")
                this.testNode1 = ret.length > 0
                if (this.testNode1) {
                    console.log("Comp1测试符合预期", " at pages/template/issue-14765/Comp1.uvue:31")
                } else {
                    console.error("Comp1测试不符合预期", " at pages/template/issue-14765/Comp1.uvue:33")
                }
                this.`$emit`("ready", this.testNode1)
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("id" to _ctx.compId, "ref" to "uxToastItemRef"), utsArrayOf(
            createElementVNode("text", null, "hello"),
            renderSlot(_ctx.`$slots`, "default")
        ), 8, utsArrayOf(
            "id"
        ))
    }
    open var id: String by `$props`
    open var compId: String by `$data`
    open var testNode1: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("compId" to "comp1-id", "testNode1" to false)
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf("ready" to null)
        var props = normalizePropsOptions(utsMapOf("id" to utsMapOf("type" to "String", "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "id"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
