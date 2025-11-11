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
import io.dcloud.uniapp.extapi.createCanvasContextAsync as uni_createCanvasContextAsync
open class GenPagesComponentCanvasCanvasChild : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onMounted(fun() {
            uni_createCanvasContextAsync(CreateCanvasContextAsyncOptions(id = "canvas1", component = this, success = fun(_: CanvasContext){
                this.testCounter++
                this._dispatchEvent()
            }
            ))
            uni_createCanvasContextAsync(CreateCanvasContextAsyncOptions(id = "canvas2", component = this, success = fun(_: CanvasContext){
                this.testCounter++
                this._dispatchEvent()
            }
            ))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode(Fragment, null, utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("canvas", utsMapOf("id" to "canvas1"))
            )),
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("canvas", utsMapOf("id" to "canvas2"))
            )),
            createElementVNode("view", null, toDisplayString(_ctx.testCounter), 1)
        ), 64)
    }
    open var testCounter: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("testCounter" to 0)
    }
    open var _dispatchEvent = ::gen__dispatchEvent_fn
    open fun gen__dispatchEvent_fn() {
        if (this.testCounter == 2) {
            uni__emit("canvasChildReady", null)
        }
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
