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
import io.dcloud.uniapp.extapi.arrayBufferToBase64 as uni_arrayBufferToBase64
import io.dcloud.uniapp.extapi.base64ToArrayBuffer as uni_base64ToArrayBuffer
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIBase64Base64 : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.arrayBufferToBase64), " arrayBufferToBase64 ", 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("button", utsMapOf("class" to "btnstyle", "type" to "primary", "onClick" to _ctx.base64ToArrayBuffer), " base64ToArrayBuffer ", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var arrayBufferToBase64Res: String by `$data`
    open var base64ToArrayBufferRes: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("arrayBufferToBase64Res" to "", "base64ToArrayBufferRes" to "")
    }
    open var arrayBufferToBase64 = ::gen_arrayBufferToBase64_fn
    open fun gen_arrayBufferToBase64_fn() {
        val arraybuffer = ArrayBuffer(3)
        val uint8 = Uint8Array(arraybuffer)
        uint8.set(utsArrayOf(
            11,
            22,
            33
        ))
        val base64 = uni_arrayBufferToBase64(arraybuffer)
        console.log(base64, " at pages/API/base64/base64.uvue:27")
        this.arrayBufferToBase64Res = base64
        uni_showToast(ShowToastOptions(title = this.arrayBufferToBase64Res))
    }
    open var base64ToArrayBuffer = ::gen_base64ToArrayBuffer_fn
    open fun gen_base64ToArrayBuffer_fn() {
        val base64 = "CxYh"
        val arrayBuffer = uni_base64ToArrayBuffer(base64)
        val uint8 = Uint8Array(arrayBuffer)
        console.log(uint8.toString(), " at pages/API/base64/base64.uvue:37")
        this.base64ToArrayBufferRes = uint8.toString()
        uni_showToast(ShowToastOptions(title = this.base64ToArrayBufferRes))
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
                return utsMapOf("logo" to padStyleMapOf(utsMapOf("height" to 100, "width" to 100, "marginTop" to 100, "marginRight" to "auto", "marginBottom" to 25, "marginLeft" to "auto")), "title" to padStyleMapOf(utsMapOf("fontSize" to 18, "color" to "#8f8f94", "textAlign" to "center")), "btnstyle" to padStyleMapOf(utsMapOf("marginTop" to 4, "marginRight" to 4, "marginBottom" to 4, "marginLeft" to 4)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
