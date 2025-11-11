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
open class GenPagesCSSTextFontFamilyIcon : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("align-items" to "center", "align-self" to "center"))), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "iconContainer"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.uniIcons, fun(item, __key, __index, _cached): Any {
                        return createElementVNode("view", utsMapOf("class" to "iconItem"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "icon", "style" to normalizeStyle(utsMapOf("font-weight" to _ctx.fontWeight, "font-style" to _ctx.fontStyle))), toDisplayString(String.fromCharCode(parseInt(item.code, 16))), 5),
                            createElementVNode("text", utsMapOf("class" to "code"), "\\u" + toDisplayString(item.code), 1),
                            createElementVNode("text", utsMapOf("class" to "name"), toDisplayString(item.name), 1)
                        ))
                    }
                    ), 256)
                ))
            ), 4)
        ), 4)
    }
    open var uniIcons: UTSArray<Icon> by `$data`
    open var fontWeight: String by `$data`
    open var fontStyle: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("uniIcons" to utsArrayOf<Icon>(Icon(name = "back", code = "E601"), Icon(name = "forward", code = "E600"), Icon(name = "share", code = "E602"), Icon(name = "favorites", code = "E604"), Icon(name = "home", code = "E605"), Icon(name = "more", code = "E606"), Icon(name = "close", code = "E650"), Icon(name = "down", code = "E661"), Icon(name = "circle", code = "EA01"), Icon(name = "warn", code = "EA0F"), Icon(name = "info", code = "EA03"), Icon(name = "info circle", code = "EA04"), Icon(name = "success", code = "EA06"), Icon(name = "success circle", code = "EA07"), Icon(name = "success no circle", code = "EA08"), Icon(name = "cancel circle", code = "EA0B"), Icon(name = "clear", code = "EA14"), Icon(name = "download", code = "EA19"), Icon(name = "waitnig", code = "EA1E"), Icon(name = "search", code = "EA23")), "fontWeight" to "normal", "fontStyle" to "normal")
    }
    open var changeStyle = ::gen_changeStyle_fn
    open fun gen_changeStyle_fn() {
        this.fontWeight = "bold"
        this.fontStyle = "italic"
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
                return utsMapOf("iconContainer" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap", "justifyContent" to "center")), "iconItem" to padStyleMapOf(utsMapOf("width" to 100, "height" to 150, "justifyContent" to "center", "alignItems" to "center")), "icon" to padStyleMapOf(utsMapOf("width" to 48, "height" to 48, "fontFamily" to "uni-icon", "fontSize" to 42, "lineHeight" to "48px", "color" to "#333333", "marginBottom" to 8)), "code" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#ff0000")), "name" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#000000")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
