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
open class GenPagesCSSVariableCustomVariable : BasePage {
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
        return createElementVNode("view", utsMapOf("id" to "var_1", "style" to normalizeStyle(utsMapOf("flex" to "1", "--var_margin" to "2px")), "class" to normalizeClass(utsArrayOf(
            "var_1",
            _ctx.var_bind
        ))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "var_1_1_1"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "firstParagraph"), "此段落应有蓝色背景和绿色文本。")
                )),
                createElementVNode("text", utsMapOf("class" to "secondParagraph"), "此段落应有黑色半透明背景和黄色文本。")
            )),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "var(--var_width_invalid, 375rpx)", "background-color" to "yellowgreen"))), "无效值width应为375rpx", 4),
            createElementVNode("view", utsMapOf("id" to "chanageVarBox", "class" to normalizeClass(_ctx.text_style_var_def)), utsArrayOf(
                if (isTrue(_ctx.text_style_var_1_on)) {
                    createElementVNode("text", utsMapOf("key" to 0, "class" to "text_style"), "font-size:26px;style:normal;color:#ccc;background-color:#488cff")
                } else {
                    createElementVNode("text", utsMapOf("key" to 1, "class" to "text_style"), "font-size:16px;style:italic;color:#803390;background-color:#48ff32")
                }
            ), 2),
            createElementVNode("view", utsMapOf("class" to "view_layout_style_container", "id" to "view_container"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "view_layout_style")),
                createElementVNode("view", utsMapOf("class" to "view_layout_style", "style" to normalizeStyle(utsMapOf("background-color" to "blue", "--var_align_self" to "flex-start"))), null, 4)
            )),
            createElementVNode("button", utsMapOf("id" to "changeVarButton", "onClick" to fun(){
                _ctx.do_change_var()
            }
            ), "修改自定义变量", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", utsMapOf("class" to "test-v-if"), utsArrayOf(
                if (isTrue(_ctx.var_v_if)) {
                    createElementVNode("view", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("height" to "var(--var_height)", "background-color" to "var(--var_backgroundColor)"))), null, 4)
                } else {
                    createCommentVNode("v-if", true)
                }
            )),
            createElementVNode("button", utsMapOf("class" to "test-v-if-button", "onClick" to fun(){
                _ctx.do_vif()
            }
            ), "v-if测试", 8, utsArrayOf(
                "onClick"
            ))
        ), 6)
    }
    open var statusbar: Boolean by `$data`
    open var var_bind: String by `$data`
    open var var_v_if: Boolean by `$data`
    open var text_style_var_def: String by `$data`
    open var text_style_var_1_on: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("statusbar" to false, "var_bind" to "var_1_bind_1", "var_v_if" to false, "text_style_var_def" to "text_style_var_1", "text_style_var_1_on" to true)
    }
    open var do_change_var = ::gen_do_change_var_fn
    open fun gen_do_change_var_fn() {
        var view_container = uni_getElementById("view_container")
        if (this.text_style_var_1_on) {
            this.text_style_var_def = "text_style_var_2"
            this.text_style_var_1_on = false
            view_container?.style?.setProperty("--var_flex_direction", "row")
            view_container?.style?.setProperty("--var_justify_content", "flex-end")
            view_container?.style?.setProperty("--var_margin", "")
            view_container?.style?.setProperty("background-color", "var(--background-color)")
        } else {
            this.text_style_var_def = "text_style_var_1"
            this.text_style_var_1_on = true
            view_container?.style?.setProperty("--var_margin", "10px")
            view_container?.style?.setProperty("--var_flex_direction", "column")
            view_container?.style?.setProperty("--var_justify_content", "center")
            view_container?.style?.setProperty("background-color", "#FFF")
        }
        this.var_bind = if ((this.var_bind == "var_1_bind_2")) {
            "var_1_bind_1"
        } else {
            "var_1_bind_2"
        }
    }
    open var do_vif = ::gen_do_vif_fn
    open fun gen_do_vif_fn() {
        this.var_v_if = !this.var_v_if
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
                return utsMapOf("var_1" to padStyleMapOf(utsMapOf("paddingLeft" to "env(safe-area-inset-left)", "paddingRight" to "env(safe-area-inset-right)", "-FontColor" to "#00ff00", "-Var_width" to "375rpx", "-Var_backgroundColor" to "rgba(0, 0, 0, .5)")), "var_1_bind_1" to padStyleMapOf(utsMapOf("-Var_height" to "30px")), "var_1_bind_2" to padStyleMapOf(utsMapOf("-Var_height" to "60px")), "var_1_1_1" to padStyleMapOf(utsMapOf("-Var_backgroundColor" to "#0000ff")), "content" to padStyleMapOf(utsMapOf("flex" to 1)), "firstParagraph" to padStyleMapOf(utsMapOf("backgroundColor" to "var(--var_backgroundColor)", "color" to "var(--font-color)")), "secondParagraph" to padStyleMapOf(utsMapOf("-FontColor" to "yellow", "backgroundColor" to "var(--var_backgroundColor)", "color" to "var(--font-color)")), "text_style_var_1" to padStyleMapOf(utsMapOf("-TextFontSize" to "26px", "-TextFontStyle" to "normal", "-TextColor" to "#ccc", "-TextBackgroundColor" to "#488cff")), "text_style_var_2" to padStyleMapOf(utsMapOf("-TextFontSize" to "16px", "-TextFontStyle" to "italic", "-TextColor" to "#803390", "-TextBackgroundColor" to "#48ff32")), "text_style" to padStyleMapOf(utsMapOf("fontFamily" to "var(--swiper-item-font-family)", "fontSize" to "var(--text-font-size)", "fontStyle" to "var(--text-font-style)", "color" to "var(--text-color)", "backgroundColor" to "var(--text-background-color)")), "view_layout_style_container" to padStyleMapOf(utsMapOf("-Var_flex_direction" to "column", "-Var_align_self" to "center", "-Var_justify_content" to "center", "-Var_border" to "2px red solid", "-Var_border2" to "2px green solid", "-BackgroundColor" to "#CCC", "-Var_height" to "110px", "-Var_margin" to "10px", "height" to "var(--var_height)", "flexDirection" to "var(--var_flex_direction)", "justifyContent" to "var(--var_justify_content)", "marginTop" to "var(--var_margin)", "marginRight" to "var(--var_margin)", "marginBottom" to "var(--var_margin)", "marginLeft" to "var(--var_margin)")), "view_layout_style" to padStyleMapOf(utsMapOf("width" to 50, "height" to 50, "backgroundColor" to "#A52A2A", "alignSelf" to "var(--var_align_self)")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
