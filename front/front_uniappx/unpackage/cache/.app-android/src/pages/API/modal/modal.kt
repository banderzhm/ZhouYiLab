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
import io.dcloud.uniapp.extapi.hideModal as uni_hideModal
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIModalModal : BasePage {
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
        onLoad(fun(options: OnLoadOptions) {
            if (options["onLoadShowModal"] == "false") {
                return
            }
            uni_showModal(ShowModalOptions(title = "onLoad 调用示例,请手动取消"))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        val _component_switch = resolveComponent("switch")
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                    createVNode(_component_radio_group, utsMapOf("onChange" to _ctx.radioChange), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.items, fun(item, index, __index, _cached): Any {
                                return createVNode(_component_radio, utsMapOf("class" to normalizeClass(utsArrayOf(
                                    "uni-list-cell uni-list-cell-pd",
                                    if (index < _ctx.items.length - 1) {
                                        "uni-list-cell-line"
                                    } else {
                                        ""
                                    }
                                )), "key" to item.value, "value" to item.value, "checked" to (index === _ctx.current)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        toDisplayString(item.name)
                                    )
                                }
                                ), "_" to 2), 1032, utsArrayOf(
                                    "class",
                                    "value",
                                    "checked"
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1), 8, utsArrayOf(
                        "onChange"
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "是否显示过长文字"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.showLongContent, "onChange" to _ctx.showLongContentChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "是否显示取消按钮"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.showCancelSelect, "onChange" to _ctx.showCancelChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "定制取消文案"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.cancelTextSelect, "onChange" to _ctx.cancelTextChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "定制确认文案"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.confirmTextSelect, "onChange" to _ctx.confirmTextChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "测试非法的颜色"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.illegalColorSelect, "onChange" to _ctx.illegalColorChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "测试超长的按钮文本"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.illegalButtonTextSelect, "onChange" to _ctx.illegalButtonTextChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "是否显示输入框"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.editableSelect, "onChange" to _ctx.editableChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "是否定制输入提示词"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.placeholderTextSelect, "onChange" to _ctx.placeholderTextChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode("text", null, "complete 执行结果：" + toDisplayString(_ctx.timesShowRet), 1),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "primary", "onClick" to _ctx.modalTap, "id" to "btn-modal-show"), " modal单次弹出 ", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "default", "onClick" to _ctx.modalTapTimes, "id" to "btn-modal-show-multitime"), " modal多次弹出 ", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "default", "onClick" to _ctx.closeAllModal, "id" to "btn-modal-hide-all"), " 延迟3s关闭全部弹窗 ", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "default", "onClick" to _ctx.closeLastModal, "id" to "btn-modal-hide-last"), " 延迟3s关闭最后一个弹窗 ", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("text", null, " success/fail 执行结果：" + toDisplayString(_ctx.exeRet), 1)
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var showCancelSelect: Boolean by `$data`
    open var showLongContent: Boolean by `$data`
    open var illegalColorSelect: Boolean by `$data`
    open var illegalButtonTextSelect: Boolean by `$data`
    open var cancelTextSelect: Boolean by `$data`
    open var confirmTextSelect: Boolean by `$data`
    open var editableSelect: Boolean by `$data`
    open var placeholderTextSelect: Boolean by `$data`
    open var exeRet: String by `$data`
    open var lastModal: UniPage? by `$data`
    open var items: UTSArray<ItemType4> by `$data`
    open var current: Number by `$data`
    open var timesShowRet: UTSArray<Any> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "modal", "showCancelSelect" to false, "showLongContent" to false, "illegalColorSelect" to false, "illegalButtonTextSelect" to false, "cancelTextSelect" to false, "confirmTextSelect" to false, "editableSelect" to false, "placeholderTextSelect" to false, "exeRet" to "", "lastModal" to null as UniPage?, "items" to utsArrayOf<ItemType4>(ItemType4(value = "标题", name = "有标题"), ItemType4(value = "", name = "无标题"), ItemType4(value = "超长标题测试内容，测试超过显示最大范围之后的样式-超长标题测试内容，测试超过显示最大范围之后的样式", name = "超长标题")), "current" to 0, "timesShowRet" to utsArrayOf<Any>())
    }
    open var showLongContentChange = ::gen_showLongContentChange_fn
    open fun gen_showLongContentChange_fn(e: UniSwitchChangeEvent) {
        this.showLongContent = e.detail.value
    }
    open var illegalColorChange = ::gen_illegalColorChange_fn
    open fun gen_illegalColorChange_fn(e: UniSwitchChangeEvent) {
        this.illegalColorSelect = e.detail.value
    }
    open var illegalButtonTextChange = ::gen_illegalButtonTextChange_fn
    open fun gen_illegalButtonTextChange_fn(e: UniSwitchChangeEvent) {
        this.illegalButtonTextSelect = e.detail.value
    }
    open var showCancelChange = ::gen_showCancelChange_fn
    open fun gen_showCancelChange_fn(e: UniSwitchChangeEvent) {
        this.showCancelSelect = e.detail.value
    }
    open var cancelTextChange = ::gen_cancelTextChange_fn
    open fun gen_cancelTextChange_fn(e: UniSwitchChangeEvent) {
        this.cancelTextSelect = e.detail.value
    }
    open var confirmTextChange = ::gen_confirmTextChange_fn
    open fun gen_confirmTextChange_fn(e: UniSwitchChangeEvent) {
        this.confirmTextSelect = e.detail.value
    }
    open var editableChange = ::gen_editableChange_fn
    open fun gen_editableChange_fn(e: UniSwitchChangeEvent) {
        this.editableSelect = e.detail.value
    }
    open var placeholderTextChange = ::gen_placeholderTextChange_fn
    open fun gen_placeholderTextChange_fn(e: UniSwitchChangeEvent) {
        this.editableSelect = e.detail.value
        this.placeholderTextSelect = e.detail.value
    }
    open var radioChange = ::gen_radioChange_fn
    open fun gen_radioChange_fn(e: UniRadioGroupChangeEvent) {
        run {
            var i: Number = 0
            while(i < this.items.length){
                if (this.items[i].value === e.detail.value) {
                    this.current = i
                    break
                }
                i++
            }
        }
    }
    open var closeLastModal = ::gen_closeLastModal_fn
    open fun gen_closeLastModal_fn() {
        var that = this
        setTimeout(fun() {
            uni_hideModal(HideModalOptions(modalPage = that.lastModal, success = fun(res){
                that.exeRet = JSON.stringify(res)
            }
            , fail = fun(res){
                that.exeRet = JSON.stringify(res)
            }
            , complete = fun(res){
                that.timesShowRet.push(res)
            }
            ))
        }
        , 3000)
    }
    open var closeAllModal = ::gen_closeAllModal_fn
    open fun gen_closeAllModal_fn() {
        var that = this
        setTimeout(fun() {
            uni_hideModal(HideModalOptions(modalPage = null, success = fun(res){
                that.exeRet = JSON.stringify(res)
            }
            , fail = fun(res){
                that.exeRet = JSON.stringify(res)
            }
            , complete = fun(res){
                that.timesShowRet.push(res)
            }
            ))
        }
        , 3000)
    }
    open var modalTapTimes = ::gen_modalTapTimes_fn
    open fun gen_modalTapTimes_fn() {
        this.timesShowRet = utsArrayOf()
        this.modalTap()
        this.modalTap()
        var that = this
        setTimeout(fun() {
            that.modalTap()
        }
        , 200)
    }
    open var modalTap = ::gen_modalTap_fn
    open fun gen_modalTap_fn() {
        this.timesShowRet = utsArrayOf()
        var cancelTextVal: String? = null
        var cancelColorVal = ""
        if (this.cancelTextSelect) {
            cancelTextVal = "修改后的取消文本"
            cancelColorVal = "#ff00ff"
        }
        var confirmTextVal: String? = null
        var confirmColorVal = ""
        if (this.confirmTextSelect) {
            confirmTextVal = "修改后的确定文本"
            confirmColorVal = "#0ff"
        }
        var placeholderTextVal = ""
        var contentVal = "弹窗内容，告知当前状态、信息和解决方法，描述文字尽量控制在三行内"
        if (this.showLongContent) {
            contentVal = "弹窗内容，告知当前状态、信息和解决方法，描述文字原则上可以无限多！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
        }
        if (this.placeholderTextSelect) {
            placeholderTextVal = "定制提示信息"
        }
        if (this.illegalColorSelect) {
            cancelColorVal = "#WW00ff"
            confirmColorVal = "#0ffff"
        }
        if (this.illegalButtonTextSelect) {
            confirmTextVal = "超长版文字-修改后的确定文本-超长版本文字"
            cancelTextVal = "超长版文字-修改后的取消文本-超长版本文字"
        }
        var op = ShowModalOptions(title = this.items[this.current].value, editable = this.editableSelect, placeholderText = placeholderTextVal, content = contentVal, showCancel = this.showCancelSelect, cancelText = cancelTextVal, cancelColor = cancelColorVal, confirmText = confirmTextVal, confirmColor = confirmColorVal, success = fun(res){
            this.exeRet = JSON.stringify(res)
        }
        , fail = fun(res){
            this.exeRet = JSON.stringify(res)
        }
        , complete = fun(res){
            this.timesShowRet.push(res)
        }
        )
        this.lastModal = uni_showModal(op)
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
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
