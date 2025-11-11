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
open class GenPagesComponentPickerViewPickerView1 : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        val _component_picker_view_column = resolveComponent("picker-view-column")
        val _component_picker_view = resolveComponent("picker-view", true)
        val _component_boolean_data = resolveEasyComponent("boolean-data", GenComponentsBooleanDataBooleanDataClass)
        return createElementVNode("view", null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                "title"
            )),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "uni-title"), " 日期：" + toDisplayString(_ctx.year) + "年" + toDisplayString(_ctx.month) + "月" + toDisplayString(_ctx.day) + "日 ", 1)
            )),
            createVNode(_component_picker_view, utsMapOf("class" to "picker-view", "value" to _ctx.value, "onChange" to _ctx.bindChange, "indicator-style" to _ctx.indicatorStyle, "indicator-class" to _ctx.indicatorClass, "mask-style" to _ctx.maskStyle, "mask-class" to _ctx.maskClass, "mask-top-style" to _ctx.maskTopStyle, "mask-bottom-style" to _ctx.maskBottomStyle), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createVNode(_component_picker_view_column, utsMapOf("class" to "picker-view-column"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.years, fun(item, index, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "item", "key" to index), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(item) + "年", 1)
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_picker_view_column, utsMapOf("class" to "picker-view-column"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.months, fun(item, index, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "item", "key" to index), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(item) + "月", 1)
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1)),
                    createVNode(_component_picker_view_column, utsMapOf("class" to "picker-view-column"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.days, fun(item, index, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "item", "key" to index), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(item) + "日", 1)
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "_" to 1))
                )
            }
            ), "_" to 1), 8, utsArrayOf(
                "value",
                "onChange",
                "indicator-style",
                "indicator-class",
                "mask-style",
                "mask-class",
                "mask-top-style",
                "mask-bottom-style"
            )),
            createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "设置选择器中间选中框的样式", "onChange" to _ctx.setIndicatorStyle), null, 8, utsArrayOf(
                "onChange"
            )),
            createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "设置蒙层上半部分的样式", "onChange" to _ctx.setMaskTopStyle), null, 8, utsArrayOf(
                "onChange"
            )),
            createVNode(_component_boolean_data, utsMapOf("defaultValue" to false, "title" to "设置蒙层下半部分的样式", "onChange" to _ctx.setMaskBottomStyle), null, 8, utsArrayOf(
                "onChange"
            ))
        ))
    }
    open var title: String by `$data`
    open var years: UTSArray<Number> by `$data`
    open var year: Number by `$data`
    open var months: UTSArray<Number> by `$data`
    open var month: Number by `$data`
    open var days: UTSArray<Number> by `$data`
    open var day: Number by `$data`
    open var value: UTSArray<Number> by `$data`
    open var result: UTSArray<Number> by `$data`
    open var indicatorStyle: String by `$data`
    open var indicatorClass: String by `$data`
    open var maskStyle: String by `$data`
    open var maskClass: String by `$data`
    open var maskTopStyle: String by `$data`
    open var maskBottomStyle: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        val _years: UTSArray<Number> = utsArrayOf()
        val _year: Number = 2018
        val _months: UTSArray<Number> = utsArrayOf()
        val _month: Number = 1
        val _days: UTSArray<Number> = utsArrayOf()
        val _day: Number = 12
        run {
            var i: Number = 2000
            while(i <= _year){
                _years.push(i)
                i++
            }
        }
        run {
            var i: Number = 1
            while(i <= 12){
                _months.push(i)
                i++
            }
        }
        run {
            var i: Number = 1
            while(i <= 31){
                _days.push(i)
                i++
            }
        }
        return utsMapOf("title" to "picker-view", "years" to _years as UTSArray<Number>, "year" to _year as Number, "months" to _months as UTSArray<Number>, "month" to _month as Number, "days" to _days as UTSArray<Number>, "day" to _day as Number, "value" to utsArrayOf<Number>(_year - 2000, _month - 1, _day - 1), "result" to utsArrayOf<Number>(), "indicatorStyle" to "height: 50px;", "indicatorClass" to "", "maskStyle" to "", "maskClass" to "", "maskTopStyle" to "", "maskBottomStyle" to "")
    }
    open var setIndicatorStyle = ::gen_setIndicatorStyle_fn
    open fun gen_setIndicatorStyle_fn(checked: Boolean) {
        val extraStyle = "height: 50px;border:#ff5500 solid 1px;background:rgba(182, 179, 255, 0.4);"
        this.indicatorStyle = if (checked) {
            extraStyle
        } else {
            "height: 50px;"
        }
    }
    open var setIndicatorClass = ::gen_setIndicatorClass_fn
    open fun gen_setIndicatorClass_fn(checked: Boolean) {
        this.indicatorClass = if (checked) {
            "indicator-test"
        } else {
            ""
        }
    }
    open var setMaskStyle = ::gen_setMaskStyle_fn
    open fun gen_setMaskStyle_fn(checked: Boolean) {
        val extraMaskStyle = "background-image: linear-gradient(to bottom, #d8e5ff, rgba(216, 229, 255, 0));"
        this.maskStyle = if (checked) {
            extraMaskStyle
        } else {
            ""
        }
    }
    open var setMaskClass = ::gen_setMaskClass_fn
    open fun gen_setMaskClass_fn(checked: Boolean) {
        this.maskClass = if (checked) {
            "mask-test"
        } else {
            ""
        }
    }
    open var setMaskTopStyle = ::gen_setMaskTopStyle_fn
    open fun gen_setMaskTopStyle_fn(checked: Boolean) {
        val linearToTop = "background-image: linear-gradient(to bottom, #f4ff73, rgba(216, 229, 255, 0));"
        this.maskTopStyle = if (checked) {
            linearToTop
        } else {
            ""
        }
    }
    open var setMaskBottomStyle = ::gen_setMaskBottomStyle_fn
    open fun gen_setMaskBottomStyle_fn(checked: Boolean) {
        val linearToBottom = "background-image: linear-gradient(to top, #f4ff73, rgba(216, 229, 255, 0));"
        this.maskBottomStyle = if (checked) {
            linearToBottom
        } else {
            ""
        }
    }
    open var getEventCallbackNum = ::gen_getEventCallbackNum_fn
    open fun gen_getEventCallbackNum_fn(): Number {
        return state.eventCallbackNum
    }
    open var setEventCallbackNum = ::gen_setEventCallbackNum_fn
    open fun gen_setEventCallbackNum_fn(num: Number) {
        uni.UNI740BA18.setEventCallbackNum(num)
    }
    open var bindChange = ::gen_bindChange_fn
    open fun gen_bindChange_fn(e: UniPickerViewChangeEvent) {
        if ((e.target?.tagName ?: "").includes("PICKER-VIEW")) {
            this.setEventCallbackNum(state.eventCallbackNum + 1)
        }
        if (e.type === "change") {
            this.setEventCallbackNum(state.eventCallbackNum + 2)
        }
        val kVal = e.detail.value
        this.result = kVal
        this.year = this.years[kVal[0]]
        this.month = this.months[kVal[1]]
        this.day = this.days[kVal[2]]
    }
    open var setValue = ::gen_setValue_fn
    open fun gen_setValue_fn() {
        this.value = utsArrayOf<Number>(0, 1, 30)
    }
    open var setValue1 = ::gen_setValue1_fn
    open fun gen_setValue1_fn() {
        this.value = utsArrayOf<Number>(10, 10, 10)
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("picker-view" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 320, "marginTop" to 10, "marginBottom" to 20)), "item" to padStyleMapOf(utsMapOf("height" to 50)), "text" to padStyleMapOf(utsMapOf("lineHeight" to "50px", "textAlign" to "center")), "indicator-test" to padStyleMapOf(utsMapOf("height" to 50, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#ff5500", "borderRightColor" to "#ff5500", "borderBottomColor" to "#ff5500", "borderLeftColor" to "#ff5500", "backgroundImage" to "none", "backgroundColor" to "rgba(182,179,255,0.4)")), "mask-test" to padStyleMapOf(utsMapOf("backgroundImage" to "linear-gradient(to bottom, #d8e5ff, rgba(216, 229, 255, 0))")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
