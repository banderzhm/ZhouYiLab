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
open class GenUniModulesUniIconsComponentsUniIconsUniIcons : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onCreated(fun() {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("text", utsMapOf("class" to "uni-icons", "style" to normalizeStyle(_ctx.styleObj)), utsArrayOf(
            renderSlot(_ctx.`$slots`, "default", UTSJSONObject(), fun(): UTSArray<Any> {
                return utsArrayOf(
                    toDisplayString(_ctx.unicode)
                )
            }
            )
        ), 4)
    }
    open var type: String by `$props`
    open var color: String by `$props`
    open var size: Number by `$props`
    open var fontFamily: String by `$props`
    open var unicode: String by `$data`
    open var iconSize: String by `$data`
    open var styleObj: UTSJSONObject by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("unicode" to computed<String>(fun(): String {
            var codes = fontData.find(fun(item: IconsDataItem): Boolean {
                return item.font_class == this.type
            }
            )
            if (codes != null) {
                return codes.unicode
            }
            return ""
        }
        ), "iconSize" to computed<String>(fun(): String {
            val size = this.size
            return this.getFontSize(size as Number)
        }
        ), "styleObj" to computed<UTSJSONObject>(fun(): UTSJSONObject {
            if (this.fontFamily !== "") {
                return let {
                    object : UTSJSONObject() {
                        var color = it.color
                        var fontSize = it.iconSize
                        var fontFamily = it.fontFamily
                    }
                }
            }
            return let {
                object : UTSJSONObject() {
                    var color = it.color
                    var fontSize = it.iconSize
                }
            }
        }
        ))
    }
    open var getFontSize = ::gen_getFontSize_fn
    open fun gen_getFontSize_fn(size: Number): String {
        return size + "px"
    }
    companion object {
        var name = "uni-icons"
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("uni-icons" to padStyleMapOf(utsMapOf("fontFamily" to "UniIconsFontFamily", "fontSize" to 18, "fontStyle" to "normal", "color" to "#333333")), "@FONT-FACE" to utsMapOf("0" to utsMapOf("fontFamily" to "UniIconsFontFamily", "src" to "url('/assets/uniicons.32e978a5.ttf')")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("type" to utsMapOf("type" to "String", "default" to ""), "color" to utsMapOf("type" to "String", "default" to "#333333"), "size" to utsMapOf("type" to "Number", "default" to 16), "fontFamily" to utsMapOf("type" to "String", "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "type",
            "color",
            "size",
            "fontFamily"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
