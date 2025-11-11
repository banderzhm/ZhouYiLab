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
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
open class GenPagesTemplateSlider100Slider100 : BasePage {
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
    open var updateSliderValueTest: (value: Number) -> Unit
        get() {
            return unref(this.`$exposed`["updateSliderValueTest"]) as (value: Number) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "updateSliderValueTest", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesTemplateSlider100Slider100, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateSlider100Slider100
            val _cache = __ins.renderCache
            val title = ref("sliderx100通信性能测试(小程序卡为正常)")
            val sliderValue = ref(50)
            val safeAreaInsetsBottom = ref(0)
            val sliderRef = ref(utsArrayOf<UniSliderElement>())
            fun gen_updateSliderValue_fn(value: Number) {
                sliderRef.value.forEach(fun(item){
                    item.value = value
                }
                )
            }
            val updateSliderValue = ::gen_updateSliderValue_fn
            fun gen_sliderChange_fn(e: UniSliderChangeEvent) {
                updateSliderValue(e.detail.value)
            }
            val sliderChange = ::gen_sliderChange_fn
            fun gen_sliderChanging_fn(e: UniSliderChangeEvent) {
                updateSliderValue(e.detail.value)
            }
            val sliderChanging = ::gen_sliderChanging_fn
            onReady(fun(){
                safeAreaInsetsBottom.value = uni_getWindowInfo().safeAreaInsets.bottom
            }
            )
            val updateSliderValueTest = fun(value: Number){
                sliderValue.value = value
            }
            __expose(utsMapOf("updateSliderValueTest" to updateSliderValueTest))
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                val _component_slider = resolveComponent("slider")
                return createElementVNode(Fragment, null, utsArrayOf(
                    createElementVNode("scroll-view", utsMapOf("class" to "page"), utsArrayOf(
                        createVNode(_component_page_head, utsMapOf("title" to unref(title)), null, 8, utsArrayOf(
                            "title"
                        )),
                        createElementVNode("view", utsMapOf("class" to "grid-view"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(100, fun(_, index, __index, _cached): Any {
                                return createVNode(_component_slider, utsMapOf("ref_for" to true, "ref_key" to "sliderRef", "ref" to sliderRef, "class" to "slider", "key" to index, "onChanging" to sliderChanging, "onChange" to sliderChange, "value" to unref(sliderValue), "block-size" to 20, "show-value" to true), null, 8, utsArrayOf(
                                    "value"
                                ))
                            }
                            ), 64)
                        ))
                    )),
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to unref(safeAreaInsetsBottom)))), null, 4)
                ), 64)
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1)), "grid-view" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")), "slider" to padStyleMapOf(utsMapOf("width" to "25%", "marginTop" to 1, "marginLeft" to 0, "marginRight" to 0, "marginBottom" to 1)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
