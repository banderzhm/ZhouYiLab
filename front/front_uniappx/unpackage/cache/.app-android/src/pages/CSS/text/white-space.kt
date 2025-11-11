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
open class GenPagesCSSTextWhiteSpace : BasePage {
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
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "10px 0px", "background-color" to "gray", "justify-content" to "center")), "direction" to "horizontal"), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "text", "style" to normalizeStyle(_ctx.textStyle)), toDisplayString(_ctx.multiLineText), 5)
            ), 4),
            createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "content"), utsArrayOf(
                    createVNode(_component_enum_data, utsMapOf("items" to _ctx.white_space, "title" to "white-space", "onChange" to _ctx.radio_change), null, 8, utsArrayOf(
                        "items",
                        "onChange"
                    ))
                ))
            ), 4)
        ), 4)
    }
    open var textStyle: String by `$data`
    open var white_space: UTSArray<ItemType> by `$data`
    open var multiLineText: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("textStyle" to "white-space: normal;", "white_space" to utsArrayOf<ItemType>(ItemType(value = 0, name = "normal"), ItemType(value = 1, name = "nowrap"), ItemType(value = 2, name = "pre"), ItemType(value = 3, name = "pre-wrap"), ItemType(value = 4, name = "pre-line"), ItemType(value = 5, name = "break-spaces")), "multiLineText" to "HBuilderX\uFF0C\n	\u8F7B\u5DE7\u3001\n		\u6781\u901F\uFF0C\n			\u6781\u5BA2\u7F16\u8F91\u5668\uFF1B\n				uni-app x\uFF0C\n					\u7EC8\u6781\u8DE8\u5E73\u53F0\u65B9\u6848\uFF1B\n				uts\uFF0C\n			\u5927\u4E00\u7EDF\u8BED\u8A00\n\nHBuilderX\uFF0C\u8F7B\u5DE7\u3001\u6781\u901F\uFF0C\u6781\u5BA2\u7F16\u8F91\u5668\uFF1Buni-app x\uFF0C\u7EC8\u6781\u8DE8\u5E73\u53F0\u65B9\u6848\uFF1Buts\uFF0C\u5927\u4E00\u7EDF\u8BED\u8A00")
    }
    open var radio_change = ::gen_radio_change_fn
    open fun gen_radio_change_fn(checked: Number) {
        this.textStyle = "white-space: " + this.white_space[checked].name + ";"
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
                return utsMapOf("text" to padStyleMapOf(utsMapOf("fontSize" to 20, "alignSelf" to "flex-start")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
