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
import uts.sdk.modules.uniOpenLocation.OpenLocationOptions as OpenLocationOptions1
import uts.sdk.modules.uniOpenLocation.openLocation as uni_openLocation
open class GenPagesAPIOpenLocationOpenLocation : BasePage {
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
    open var dialogPagesNum: DialogPagesNum
        get() {
            return unref(this.`$exposed`["dialogPagesNum"]) as DialogPagesNum
        }
        set(value) {
            setRefValue(this.`$exposed`, "dialogPagesNum", value)
        }
    open var openLocation: () -> Unit
        get() {
            return unref(this.`$exposed`["openLocation"]) as () -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "openLocation", value)
        }
    open var pageSetLifeCycleNum: (value: Number) -> Unit
        get() {
            return unref(this.`$exposed`["pageSetLifeCycleNum"]) as (value: Number) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "pageSetLifeCycleNum", value)
        }
    open var getLifeCycleNum: () -> Number
        get() {
            return unref(this.`$exposed`["getLifeCycleNum"]) as () -> Number
        }
        set(value) {
            setRefValue(this.`$exposed`, "getLifeCycleNum", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPIOpenLocationOpenLocation, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIOpenLocationOpenLocation
            val _cache = __ins.renderCache
            val title = ref("openLocation")
            val longitude = ref(116.39747)
            val latitude = ref(39.9085)
            val name = ref("天安门")
            val address = ref("北京市东城区东长安街")
            val dialogPagesNum = reactive(DialogPagesNum(value = -1))
            onPageShow(fun(){
                console.log("Page Show", " at pages/API/open-location/open-location.uvue:71")
                setLifeCycleNum(state.lifeCycleNum + 1)
            }
            )
            onPageHide(fun(){
                console.log("Page Hide", " at pages/API/open-location/open-location.uvue:77")
                setLifeCycleNum(state.lifeCycleNum - 1)
            }
            )
            val test = fun(){
                val pages = getCurrentPages()
                val page = pages[pages.length - 1]
                val dialogPages = page.getDialogPages()
                dialogPagesNum.value = dialogPages.length
            }
            val openLocation = fun(){
                uni_openLocation(OpenLocationOptions1(longitude = longitude.value, latitude = latitude.value, name = name.value, address = address.value))
                setTimeout(fun(){
                    test()
                }
                , 500)
            }
            val pageSetLifeCycleNum = fun(value: Number){
                setLifeCycleNum(value)
            }
            val getLifeCycleNum = fun(): Number {
                return state.lifeCycleNum
            }
            __expose(utsMapOf("dialogPagesNum" to dialogPagesNum, "openLocation" to openLocation, "pageSetLifeCycleNum" to pageSetLifeCycleNum, "getLifeCycleNum" to getLifeCycleNum))
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                val _component_form = resolveComponent("form")
                return createElementVNode("view", null, utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to unref(title)), null, 8, utsArrayOf(
                        "title"
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                        createVNode(_component_form, utsMapOf("onSubmit" to openLocation), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "uni-label"), "经度")
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                            createElementVNode("input", utsMapOf("modelValue" to unref(longitude), "onInput" to fun(`$event`: InputEvent){
                                                trySetRefValue(longitude, looseToNumber(`$event`.detail.value))
                                            }
                                            , "class" to "uni-input", "type" to "text", "disabled" to true), null, 40, utsArrayOf(
                                                "modelValue"
                                            ))
                                        ))
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "uni-label"), "纬度")
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                            createElementVNode("input", utsMapOf("modelValue" to unref(latitude), "onInput" to fun(`$event`: InputEvent){
                                                trySetRefValue(latitude, looseToNumber(`$event`.detail.value))
                                            }
                                            , "class" to "uni-input", "type" to "text", "disabled" to true), null, 40, utsArrayOf(
                                                "modelValue"
                                            ))
                                        ))
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "uni-label"), "位置名称")
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                            createElementVNode("input", utsMapOf("modelValue" to unref(name), "onInput" to fun(`$event`: InputEvent){
                                                trySetRefValue(name, `$event`.detail.value)
                                            }
                                            , "class" to "uni-input", "type" to "text", "disabled" to true), null, 40, utsArrayOf(
                                                "modelValue"
                                            ))
                                        ))
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), utsArrayOf(
                                            createElementVNode("view", utsMapOf("class" to "uni-label"), "详细位置")
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                            createElementVNode("input", utsMapOf("modelValue" to unref(address), "onInput" to fun(`$event`: InputEvent){
                                                trySetRefValue(address, `$event`.detail.value)
                                            }
                                            , "class" to "uni-input", "type" to "text", "disabled" to true), null, 40, utsArrayOf(
                                                "modelValue"
                                            ))
                                        ))
                                    ))
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "tips"), "注意：需要正确配置地图服务商的Key才能正常显示位置"),
                                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                                        createElementVNode("button", utsMapOf("type" to "primary", "formType" to "submit"), "查看位置")
                                    ))
                                ))
                            )
                        }
                        ), "_" to 1))
                    ))
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("uni-list-cell-left" to padStyleMapOf(utsMapOf("paddingTop" to 0, "paddingRight" to 15, "paddingBottom" to 0, "paddingLeft" to 15)), "tips" to padStyleMapOf(utsMapOf("fontSize" to 12, "marginTop" to 15, "opacity" to 0.8)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
