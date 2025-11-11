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
open class GenPagesTemplateCustomRefresherRefreshBoxRefreshBox : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("list-item", utsMapOf("slot" to "refresher", "class" to "refresh-box"), utsArrayOf(
            if (_ctx.state == 2) {
                createElementVNode("image", utsMapOf("key" to 0, "class" to "refresh-icon", "src" to "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/refresh-box-run.gif", "mode" to "widthFix"))
            } else {
                createCommentVNode("v-if", true)
            }
            ,
            createElementVNode("text", utsMapOf("class" to "tip-text"), toDisplayString(_ctx.text[_ctx.state]), 1)
        ))
    }
    open var state: Number by `$props`
    open var methods: Any? by `$props`
    open var text: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("text" to utsArrayOf(
            "继续下拉执行刷新",
            "释放立即刷新",
            "刷新中",
            ""
        ))
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("refresh-box" to padStyleMapOf(utsMapOf("justifyContent" to "center", "alignItems" to "center", "flexDirection" to "row", "height" to 30)), "refresh-icon" to padStyleMapOf(utsMapOf("width" to 20, "height" to 20, "marginTop" to 5, "marginRight" to 5, "marginBottom" to 5, "marginLeft" to 5)), "tip-text" to padStyleMapOf(utsMapOf("color" to "#888888", "fontSize" to 12)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("state" to utsMapOf("type" to "Number", "default" to 0), "methods" to utsMapOf()))
        var propsNeedCastKeys = utsArrayOf(
            "state"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
