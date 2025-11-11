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
open class GenPagesComponentGlobalPropertiesGlobalProperties : BasePage {
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
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("view", utsMapOf("id" to _ctx.generalId, "class" to normalizeClass(_ctx.generalClass), "name" to _ctx.generalName, "title" to _ctx.generalTitle, "data-test" to _ctx.generalData, "style" to normalizeStyle(_ctx.generalStyle), "ref" to "general-target"), utsArrayOf(
                        createElementVNode("text", null, "id: " + toDisplayString(_ctx.generalId), 1),
                        createElementVNode("text", null, "class: " + toDisplayString(_ctx.generalClass), 1),
                        createElementVNode("text", null, "name: " + toDisplayString(_ctx.generalName), 1),
                        createElementVNode("text", null, "title: " + toDisplayString(_ctx.generalTitle), 1),
                        createElementVNode("text", null, "data-test: " + toDisplayString(_ctx.generalData), 1),
                        createElementVNode("text", null, "style: " + toDisplayString(_ctx.generalStyle), 1)
                    ), 14, utsArrayOf(
                        "id",
                        "name",
                        "title",
                        "data-test"
                    )),
                    createElementVNode("view", utsMapOf("class" to "btn btn-style uni-common-mt", "onClick" to _ctx.validateGeneralAttributes), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "btn-inner"), toDisplayString(_ctx.validateGeneralAttrText), 1)
                    ), 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to "btn btn-ref uni-common-mt", "onClick" to _ctx.changeHeight), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "btn-inner"), toDisplayString(_ctx.changeHeightByRefText), 1)
                    ), 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("view", utsMapOf("class" to "view-class", "hover-class" to _ctx.hoverClass, "ref" to "view-target"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "按下 50 ms 后背景变红"),
                        createElementVNode("text", utsMapOf("class" to "text"), "抬起 400 ms 后背景恢复")
                    ), 8, utsArrayOf(
                        "hover-class"
                    )),
                    createElementVNode("view", utsMapOf("class" to "view-class", "hover-class" to _ctx.hoverClass, "hover-start-time" to 1000, "hover-stay-time" to 1000, "ref" to "view-target"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text"), "按下 1000 ms 后背景变红"),
                        createElementVNode("text", utsMapOf("class" to "text"), "抬起 1000 ms 后背景恢复")
                    ), 8, utsArrayOf(
                        "hover-class"
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var generalId: String by `$data`
    open var generalClass: String by `$data`
    open var generalName: String by `$data`
    open var generalTitle: String by `$data`
    open var generalData: String by `$data`
    open var generalStyle: String by `$data`
    open var validateGeneralAttrText: String by `$data`
    open var hoverClass: String by `$data`
    open var validateViewAttrText: String by `$data`
    open var changeHeightByRefText: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "global-properties", "generalId" to "general-id", "generalClass" to "general-class", "generalName" to "general-name", "generalTitle" to "general-title", "generalData" to "general-data", "generalStyle" to "background-color: aqua", "validateGeneralAttrText" to "验证基础属性", "hoverClass" to "hover-class", "validateViewAttrText" to "验证 view 属性", "changeHeightByRefText" to "通过 ref 修改高度")
    }
    open var validateGeneralAttributes = ::gen_validateGeneralAttributes_fn
    open fun gen_validateGeneralAttributes_fn() {
        val generalTarget = this.`$refs`["general-target"] as UniElement
        val generalId = generalTarget.getAttribute("id")
        if (generalId != this.generalId) {
            this.validateGeneralAttrText = "基础属性 id 验证失败"
            return
        }
        if (!generalTarget.classList.includes("general-class")) {
            this.validateGeneralAttrText = "基础属性 class 验证失败"
            return
        }
        val generalName = generalTarget.getAttribute("name")
        if (generalName != this.generalName) {
            this.validateGeneralAttrText = "基础属性 name 验证失败"
            return
        }
        val generalTitle = generalTarget.getAttribute("title")
        if (generalTitle != this.generalTitle) {
            this.validateGeneralAttrText = "基础属性 title 验证失败"
            return
        }
        val generalData = generalTarget.getAttribute("data-test")
        if (generalData != this.generalData) {
            this.validateGeneralAttrText = "基础属性 data-test 验证失败"
            return
        }
        this.validateGeneralAttrText = "基础属性验证成功"
    }
    open var changeHeight = ::gen_changeHeight_fn
    open fun gen_changeHeight_fn() {
        val generalTarget = this.`$refs`["general-target"] as UniElement
        this.changeHeightByRefText = "已通过 ref 修改高度"
        generalTarget.style.setProperty("height", "200px")
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
                return utsMapOf("btn" to padStyleMapOf(utsMapOf("height" to 50, "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#409eff", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5)), "btn-inner" to padStyleMapOf(utsMapOf("color" to "#ffffff")), "general-class" to padStyleMapOf(utsMapOf("marginLeft" to 40, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "width" to 260, "height" to 160, "backgroundColor" to "#FAEBD7")), "view-class" to padStyleMapOf(utsMapOf("marginTop" to 20, "marginRight" to 0, "marginBottom" to 0, "marginLeft" to 50, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "width" to 240, "height" to 100, "backgroundColor" to "#FAEBD7")), "text" to utsMapOf(".view-class " to utsMapOf("marginTop" to 5, "textAlign" to "center")), "hover-class" to padStyleMapOf(utsMapOf("backgroundColor" to "#FF0000")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
