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
open class GenPagesTemplateSwiperList2SwiperList2 : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesTemplateSwiperList2SwiperList2) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateSwiperList2SwiperList2
            val _cache = __ins.renderCache
            fun gen_lerpNumber_fn(value1: Number, value2: Number, amount: Number): Number {
                return (value1 + (value2 - value1) * amount)
            }
            val lerpNumber = ::gen_lerpNumber_fn
            val swiperList = ref(utsArrayOf<SwiperViewItem2>())
            val swiperIndex = ref(0)
            val tabScroll = ref<UniScrollViewElement?>(null)
            val swipertab = ref<UTSArray<UniTextElement>?>(null)
            val swiper = ref<UniSwiperElement?>(null)
            val animationFinishIndex = ref(0)
            val swiperWidth = ref(0)
            fun gen_updateTabIndicator_fn(current_index: Number, move_to_index: Number, percentage: Number) {
                if (percentage == 0) {
                    return
                }
                val min_ratio: Number = 1
                val max_ratio: Number = 1.3
                val tabs = swipertab.value as UTSArray<UniTextElement>
                val current_node = tabs[current_index]
                val move_to_node = tabs[move_to_index]
                val current_scale = lerpNumber(min_ratio, max_ratio, 1 - percentage)
                current_node.style.setProperty("transform", "scale(" + current_scale + ")")
                val move_to_scale = lerpNumber(min_ratio, max_ratio, percentage)
                move_to_node.style.setProperty("transform", "scale(" + move_to_scale + ")")
                val target_x = lerpNumber(current_node.offsetLeft, move_to_node.offsetLeft, percentage)
                val center_x = target_x + move_to_node.offsetWidth / 2 - swiperWidth.value / 2
                tabScroll.value!!.scrollLeft = center_x
            }
            val updateTabIndicator = ::gen_updateTabIndicator_fn
            fun gen_setSwiperIndex_fn(index: Number, updateIndicator: Boolean) {
                if (swiperIndex.value === index) {
                    return
                }
                swiperIndex.value = index
                if (updateIndicator) {
                    updateTabIndicator(index, index, 1)
                }
            }
            val setSwiperIndex = ::gen_setSwiperIndex_fn
            fun gen_onTabClick_fn(index: Number) {
                setSwiperIndex(index, false)
            }
            val onTabClick = ::gen_onTabClick_fn
            fun gen_onSwiperTransition_fn(e: SwiperTransitionEvent) {
                val offset_x = e.detail.dx
                val current_offset_x = offset_x % swiperWidth.value
                val current_offset_i = offset_x / swiperWidth.value
                val current_index = animationFinishIndex.value + parseInt(current_offset_i + "")
                var move_to_index = current_index
                if (current_offset_x > 0 && move_to_index < swiperList.value.length - 1) {
                    move_to_index += 1
                } else if (current_offset_x < 0 && move_to_index > 0) {
                    move_to_index -= 1
                }
                val percentage = Math.abs(current_offset_x) / swiperWidth.value
                if (current_index != move_to_index) {
                    updateTabIndicator(current_index, move_to_index, percentage)
                }
            }
            val onSwiperTransition = ::gen_onSwiperTransition_fn
            fun gen_onSwiperAnimationfinish_fn(e: SwiperAnimationFinishEvent) {
                setSwiperIndex(e.detail.current, true)
                animationFinishIndex.value = e.detail.current
            }
            val onSwiperAnimationfinish = ::gen_onSwiperAnimationfinish_fn
            onLoad(fun(_options){
                run {
                    var i: Number = 0
                    while(i < 4){
                        swiperList.value.push(SwiperViewItem2(title = "Tab " + i))
                        i++
                    }
                }
            }
            )
            onReady(fun(){
                (swiper.value as UniSwiperElement).getBoundingClientRectAsync()!!.then(fun(res: DOMRect){
                    swiperWidth.value = res.width
                    updateTabIndicator(swiperIndex.value, swiperIndex.value, 1)
                }
                )
            }
            )
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to "swiper-list"), utsArrayOf(
                    createElementVNode("scroll-view", utsMapOf("ref_key" to "tabScroll", "ref" to tabScroll, "class" to "swiper-tabs", "direction" to "horizontal", "show-scrollbar" to false), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "flex-row", "style" to normalizeStyle(utsMapOf("align-self" to "flex-start"))), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(unref(swiperList), fun(item, index, __index, _cached): Any {
                                return createElementVNode("text", utsMapOf("ref_for" to true, "ref_key" to "swipertab", "ref" to swipertab, "class" to normalizeClass(utsArrayOf(
                                    "swiper-tabs-item",
                                    if (unref(swiperIndex) == index) {
                                        "swiper-tabs-item-active"
                                    } else {
                                        ""
                                    }
                                )), "key" to index, "onClick" to fun(){
                                    onTabClick(index)
                                }
                                ), toDisplayString(item.title), 11, utsArrayOf(
                                    "onClick"
                                ))
                            }
                            ), 128)
                        ), 4)
                    ), 512),
                    createElementVNode("swiper", utsMapOf("ref_key" to "swiper", "ref" to swiper, "class" to "swiper-view", "current" to unref(swiperIndex), "onTransition" to onSwiperTransition, "onAnimationfinish" to onSwiperAnimationfinish), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(swiperList), fun(_, index, __index, _cached): Any {
                            return createElementVNode("swiper-item", utsMapOf("class" to "swiper-item", "key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "swiper-item-text"), toDisplayString(index), 1)
                            ))
                        }
                        ), 128)
                    ), 40, utsArrayOf(
                        "current"
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
                return utsMapOf("flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "swiper-list" to padStyleMapOf(utsMapOf("flex" to 1)), "swiper-tabs-item" to padStyleMapOf(utsMapOf("color" to "#555555", "fontSize" to 16, "marginTop" to 15, "marginRight" to 25, "marginBottom" to 5, "marginLeft" to 25, "whiteSpace" to "nowrap")), "swiper-tabs-item-active" to padStyleMapOf(utsMapOf("color" to "#000000")), "swiper-view" to padStyleMapOf(utsMapOf("flex" to 1)), "swiper-item" to padStyleMapOf(utsMapOf("flex" to 1, "alignItems" to "center", "justifyContent" to "center")), "swiper-item-text" to padStyleMapOf(utsMapOf("fontSize" to 72, "fontWeight" to "bold")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
