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
import io.dcloud.uniapp.extapi.getSystemInfoSync as uni_getSystemInfoSync
import io.dcloud.uniapp.extapi.hideActionSheet as uni_hideActionSheet
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.setAppTheme as uni_setAppTheme
import io.dcloud.uniapp.extapi.showActionSheet as uni_showActionSheet
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIActionSheetActionSheet : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            uni_showActionSheet(ShowActionSheetOptions(title = "onLoad 调用示例,请手动取消", itemList = utsArrayOf(
                "item1",
                "item2"
            ), fail = fun(res){
                uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
                console.log("onLoad showActionSheet fail", res, " at pages/API/action-sheet/action-sheet.uvue:103")
            }
            , complete = fun(res){
                uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
                console.log("onLoad showActionSheet complete", res, " at pages/API/action-sheet/action-sheet.uvue:108")
            }
            ))
            this.originTheme = uni_getSystemInfoSync().appTheme
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
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "自定义 titleColor"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.titleColorCustom, "onChange" to _ctx.titleColorChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "自定义 itemColor"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.itemColorCustom, "onChange" to _ctx.itemColorChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "超长文本和空文本 item"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.itemContentLarge, "onChange" to _ctx.itemContentLargeChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "超过6个 item"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.itemNumLargeSelect, "onChange" to _ctx.itemNumLargeChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "自定义 cancelText"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.cancelTextCustom, "onChange" to _ctx.cancelTextChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "自定义 cancelColor"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.cancelColorCustom, "onChange" to _ctx.cancelColorChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-pd"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), "自定义 backgroundColor"),
                        createVNode(_component_switch, utsMapOf("checked" to _ctx.backgroundColorCustom, "onChange" to _ctx.backgroundColorChange), null, 8, utsArrayOf(
                            "checked",
                            "onChange"
                        ))
                    ))
                )),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                        createElementVNode("button", utsMapOf("class" to "uni-btn-v", "type" to "default", "onClick" to _ctx.showActionSheet, "id" to "btn-action-sheet-show"), "弹出actionSheet", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("class" to "uni-btn-v uni-btn", "type" to "default", "onClick" to _ctx.showActionSheetAndShowAgainInCallback, "id" to "btn-action-sheet-show"), "showActionSheet 并在回调中再次 showActionSheet", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                ))
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var titleColorCustom: Boolean by `$data`
    open var itemColorCustom: Boolean by `$data`
    open var itemContentLarge: Boolean by `$data`
    open var itemNumLargeSelect: Boolean by `$data`
    open var cancelTextCustom: Boolean by `$data`
    open var cancelColorCustom: Boolean by `$data`
    open var backgroundColorCustom: Boolean by `$data`
    open var showErrorToast: Boolean by `$data`
    open var items: UTSArray<ItemType3> by `$data`
    open var current: Number by `$data`
    open var originTheme: String? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "action-sheet", "titleColorCustom" to false, "itemColorCustom" to false, "itemContentLarge" to false, "itemNumLargeSelect" to false, "cancelTextCustom" to false, "cancelColorCustom" to false, "backgroundColorCustom" to false, "showErrorToast" to true, "items" to utsArrayOf<ItemType3>(ItemType3(value = "标题", name = "有标题"), ItemType3(value = "", name = "无标题"), ItemType3(value = "超长标题测试内容，测试超过显示最大范围之后的样式-超长标题测试内容，测试超过显示最大范围之后的样式", name = "超长标题")), "current" to 0, "originTheme" to null as String?)
    }
    open var setThemeAuto = ::gen_setThemeAuto_fn
    open fun gen_setThemeAuto_fn() {
        uni_setAppTheme(SetAppThemeOptions(theme = "auto"))
    }
    open var resetTheme = ::gen_resetTheme_fn
    open fun gen_resetTheme_fn() {
        val originTheme = this.originTheme
        if (originTheme != null) {
            uni_setAppTheme(SetAppThemeOptions(theme = originTheme))
        }
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
    open var titleColorChange = ::gen_titleColorChange_fn
    open fun gen_titleColorChange_fn(e: UniSwitchChangeEvent) {
        this.titleColorCustom = e.detail.value
    }
    open var itemContentLargeChange = ::gen_itemContentLargeChange_fn
    open fun gen_itemContentLargeChange_fn(e: UniSwitchChangeEvent) {
        this.itemContentLarge = e.detail.value
    }
    open var itemColorChange = ::gen_itemColorChange_fn
    open fun gen_itemColorChange_fn(e: UniSwitchChangeEvent) {
        this.itemColorCustom = e.detail.value
    }
    open var itemNumLargeChange = ::gen_itemNumLargeChange_fn
    open fun gen_itemNumLargeChange_fn(e: UniSwitchChangeEvent) {
        this.itemNumLargeSelect = e.detail.value
    }
    open var cancelTextChange = ::gen_cancelTextChange_fn
    open fun gen_cancelTextChange_fn(e: UniSwitchChangeEvent) {
        this.cancelTextCustom = e.detail.value
    }
    open var cancelColorChange = ::gen_cancelColorChange_fn
    open fun gen_cancelColorChange_fn(e: UniSwitchChangeEvent) {
        this.cancelColorCustom = e.detail.value
    }
    open var backgroundColorChange = ::gen_backgroundColorChange_fn
    open fun gen_backgroundColorChange_fn(e: UniSwitchChangeEvent) {
        this.backgroundColorCustom = e.detail.value
    }
    open var showActionSheet = ::gen_showActionSheet_fn
    open fun gen_showActionSheet_fn() {
        val options = ShowActionSheetOptions(title = this.items[this.current].value, itemList = utsArrayOf(
            "item1",
            "item2",
            "item3",
            "item4"
        ), success = fun(res){
            console.log(res.tapIndex, " at pages/API/action-sheet/action-sheet.uvue:166")
            uni_showToast(ShowToastOptions(title = "点击了第" + res.tapIndex + "个选项", icon = "none"))
        }
        , fail = fun(error){
            if (this.showErrorToast) {
                uni_showToast(ShowToastOptions(title = error.errMsg, icon = "none"))
            }
            console.log(error, " at pages/API/action-sheet/action-sheet.uvue:179")
        }
        )
        if (this.itemContentLarge) {
            options.itemList = utsArrayOf(
                "两个黄鹂鸣翠柳，一行白鹭上青天。窗含西岭千秋雪，门泊东吴万里船",
                "水光潋滟晴方好,山色空蒙雨亦奇。 欲把西湖比西子,淡妆浓抹总相宜",
                ""
            )
        }
        if (this.itemNumLargeSelect) {
            val arr: UTSArray<String> = utsArrayOf()
            run {
                var i: Number = 0
                while(i < 10){
                    arr.push("\u4E24\u4E2A\u9EC4\u9E42\u9E23\u7FE0\u67F3\uFF0C\u4E00\u884C\u767D\u9E6D\u4E0A\u9752\u5929 " + (i + 1))
                    i++
                }
            }
            options.itemList = arr
        }
        if (this.titleColorCustom) {
            options.titleColor = "#007AFF"
        }
        if (this.itemColorCustom) {
            options.itemColor = "#ff00ff"
        }
        if (this.cancelTextCustom) {
            options.cancelText = "custom cancel"
        }
        if (this.cancelColorCustom) {
            options.cancelColor = "#007AFF"
        }
        if (this.backgroundColorCustom) {
            options.backgroundColor = "#ccc"
        }
        uni_showActionSheet(options)
    }
    open var showActionSheetAndShowAgainInCallback = ::gen_showActionSheetAndShowAgainInCallback_fn
    open fun gen_showActionSheetAndShowAgainInCallback_fn() {
        uni_showActionSheet(ShowActionSheetOptions(title = "第一个", itemList = utsArrayOf(
            "1",
            "2",
            "3"
        ), complete = fun(_) {
            uni_showActionSheet(ShowActionSheetOptions(title = "第二个", itemList = utsArrayOf(
                "a",
                "b",
                "c"
            ), complete = fun(res){
                console.log("showActionSheetAndShowAgainInCallback complete", res, " at pages/API/action-sheet/action-sheet.uvue:219")
            }
            ))
        }
        ))
    }
    open var getLifeCycleNum = ::gen_getLifeCycleNum_fn
    open fun gen_getLifeCycleNum_fn(): Number {
        return state.lifeCycleNum
    }
    open var setLifeCycleNum = ::gen_setLifeCycleNum_fn
    open fun gen_setLifeCycleNum_fn(num: Number) {
        return uni.UNI740BA18.setLifeCycleNum(num)
    }
    open var hideActionSheet = ::gen_hideActionSheet_fn
    open fun gen_hideActionSheet_fn() {
        uni_hideActionSheet()
    }
    open var showActionSheetAndNavigateBackInSuccessCallback = ::gen_showActionSheetAndNavigateBackInSuccessCallback_fn
    open fun gen_showActionSheetAndNavigateBackInSuccessCallback_fn() {
        uni_showActionSheet(ShowActionSheetOptions(title = "showActionSheetAndNavigateBackInSuccessCallback", itemList = utsArrayOf(
            "1",
            "2",
            "3"
        ), success = fun(res){
            console.log("wzf showActionSheetAndNavigateBackInSuccessCallback success", res, " at pages/API/action-sheet/action-sheet.uvue:249")
            uni.UNI740BA18.setLifeCycleNum(state.lifeCycleNum + 1)
            uni_navigateBack(null)
        }
        , fail = fun(error){
            console.log("wzf showActionSheetAndNavigateBackInSuccessCallback fail", error, " at pages/API/action-sheet/action-sheet.uvue:254")
        }
        ))
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
