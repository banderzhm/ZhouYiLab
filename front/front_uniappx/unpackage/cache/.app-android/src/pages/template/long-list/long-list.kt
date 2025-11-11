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
import io.dcloud.uniapp.extapi.stopPullDownRefresh as uni_stopPullDownRefresh
open class GenPagesTemplateLongListLongList : BasePage {
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
        var setup: (__props: GenPagesTemplateLongListLongList) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateLongListLongList
            val _cache = __ins.renderCache
            fun gen_lerpNumber_fn(value1: Number, value2: Number, amount: Number): Number {
                return (value1 + (value2 - value1) * amount)
            }
            val lerpNumber = ::gen_lerpNumber_fn
            val swiperList = ref(utsArrayOf<SwiperViewItem3>(SwiperViewItem3(id = "updated", type = "UpdatedDate", name = "最新上架", preload = true), SwiperViewItem3(id = "free", type = "FreeHot", name = "免费热榜", preload = false), SwiperViewItem3(id = "payment", type = "PaymentHot", name = "付费热榜", preload = false), SwiperViewItem3(id = "hot", type = "HotList", name = "热门总榜", preload = false)))
            val swiperIndex = ref(0)
            val pageScrollView = ref<UniScrollViewElement?>(null)
            val header = ref<UniElement?>(null)
            val tabScroll = ref<UniScrollViewElement?>(null)
            val indicator = ref<UniElement?>(null)
            val swiper = ref<UniSwiperElement?>(null)
            val swipertab = ref<UTSArray<UniTextElement>?>(null)
            val longPageRef = ref<UTSArray<ComponentPublicInstance>?>(null)
            val headerHeight = ref(0)
            val animationFinishIndex = ref(0)
            val swiperWidth = ref(0)
            val swiperTabsRect = ref(utsArrayOf<SwiperTabsItem2>())
            fun gen_onStartNestedScroll_fn(): Boolean {
                return true
            }
            val onStartNestedScroll = ::gen_onStartNestedScroll_fn
            fun gen_onNestedPreScroll_fn(event: NestedPreScrollEvent) {
                val deltaY = event.deltaY
                val scrollTop = pageScrollView.value!!.scrollTop
                if (deltaY > 0) {
                    if (scrollTop < headerHeight.value) {
                        val difference = headerHeight.value - scrollTop - deltaY
                        if (difference > 0) {
                            pageScrollView.value!!.scrollBy(event.deltaX, deltaY)
                            event.consumed(event.deltaX, deltaY)
                        } else {
                            val top: Number = deltaY + difference
                            event.consumed(event.deltaX, top.toFloat())
                            pageScrollView.value!!.scrollBy(event.deltaX, top.toFloat())
                        }
                    }
                } else if (deltaY < 0) {
                    if (scrollTop > 0) {
                        event.consumed(event.deltaX, deltaY)
                        pageScrollView.value!!.scrollBy(event.deltaX, deltaY)
                    }
                }
            }
            val onNestedPreScroll = ::gen_onNestedPreScroll_fn
            fun gen_updateTabIndicator_fn(current_index: Number, move_to_index: Number, percentage: Number) {
                val current_size = swiperTabsRect.value[current_index]
                val move_to_size = swiperTabsRect.value[move_to_index]
                val indicator_line_x = lerpNumber(current_size.x, move_to_size.x, percentage)
                val indicator_line_w = lerpNumber(current_size.w, move_to_size.w, percentage)
                val x = indicator_line_x + indicator_line_w / 2
                indicator.value?.style?.setProperty("transform", "translateX(" + x + "px) scaleX(" + indicator_line_w + ")")
                val scroll_x = x - swiperWidth.value / 2
                tabScroll.value!!.scrollLeft = scroll_x
            }
            val updateTabIndicator = ::gen_updateTabIndicator_fn
            fun gen_initSwiperItemData_fn(index: Number) {
                if (!swiperList.value[index].preload) {
                    swiperList.value[index].preload = true
                    (longPageRef.value as UTSArray<ComponentPublicInstance>)[index].`$callMethod`("loadData", null)
                }
            }
            val initSwiperItemData = ::gen_initSwiperItemData_fn
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
                updateTabIndicator(current_index, move_to_index, percentage)
                initSwiperItemData(move_to_index)
            }
            val onSwiperTransition = ::gen_onSwiperTransition_fn
            fun gen_cacheTabItemsSize_fn(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        swiperTabsRect.value.length = 0
                        val tabs = swipertab.value as UTSArray<UniElement>
                        run {
                            var i: Number = 0
                            while(i < tabs.length){
                                val element = tabs[i]
                                val x = element.offsetLeft
                                val w = element.offsetWidth
                                swiperTabsRect.value.push(SwiperTabsItem2(x = x, w = w))
                                i++
                            }
                        }
                })
            }
            val cacheTabItemsSize = ::gen_cacheTabItemsSize_fn
            fun gen_setSwiperIndex_fn(index: Number, updateIndicator: Boolean) {
                if (swiperIndex.value === index) {
                    return
                }
                swiperIndex.value = index
                initSwiperItemData(index)
                if (updateIndicator) {
                    updateTabIndicator(index, index, 1)
                }
            }
            val setSwiperIndex = ::gen_setSwiperIndex_fn
            fun gen_onTabClick_fn(index: Number) {
                setSwiperIndex(index, false)
            }
            val onTabClick = ::gen_onTabClick_fn
            fun gen_onSwiperAnimationfinish_fn(e: SwiperAnimationFinishEvent) {
                setSwiperIndex(e.detail.current, true)
                animationFinishIndex.value = e.detail.current
            }
            val onSwiperAnimationfinish = ::gen_onSwiperAnimationfinish_fn
            onReady(fun(){
                headerHeight.value = (header.value as UniElement).offsetHeight
                swiper.value!!.getBoundingClientRectAsync()!!.then(fun(res: DOMRect): UTSPromise<Unit> {
                    swiperWidth.value = res.width
                    return cacheTabItemsSize()
                }
                ).then(fun(){
                    updateTabIndicator(swiperIndex.value, swiperIndex.value, 1)
                }
                )
            }
            )
            onPullDownRefresh(fun(){
                (longPageRef.value as UTSArray<ComponentPublicInstance>)[swiperIndex.value].`$callMethod`("refreshData", fun(){
                    uni_stopPullDownRefresh()
                }
                )
            }
            )
            return fun(): Any? {
                return createElementVNode("scroll-view", utsMapOf("ref_key" to "pageScrollView", "ref" to pageScrollView, "class" to "page", "bounces" to false, "onStartnestedscroll" to onStartNestedScroll, "onNestedprescroll" to onNestedPreScroll), utsArrayOf(
                    createElementVNode("view", utsMapOf("ref_key" to "header", "ref" to header, "class" to "search-bar"), utsArrayOf(
                        createElementVNode("input", utsMapOf("placeholder" to "搜索...", "maxlength" to "-1"))
                    ), 512),
                    createElementVNode("view", utsMapOf("class" to "swiper-list"), utsArrayOf(
                        createElementVNode("scroll-view", utsMapOf("ref_key" to "tabScroll", "ref" to tabScroll, "class" to "swiper-tabs", "direction" to "horizontal", "show-scrollbar" to false), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "flex-row", "style" to normalizeStyle(utsMapOf("align-self" to "flex-start"))), utsArrayOf(
                                createElementVNode(Fragment, null, RenderHelpers.renderList(unref(swiperList), fun(item, index, __index, _cached): Any {
                                    return createElementVNode("text", utsMapOf("ref_for" to true, "ref_key" to "swipertab", "ref" to swipertab, "space" to "nbsp", "class" to normalizeClass(utsArrayOf(
                                        "swiper-tabs-item",
                                        if (unref(swiperIndex) == index) {
                                            "swiper-tabs-item-active"
                                        } else {
                                            ""
                                        }
                                    )), "key" to index, "onClick" to fun(){
                                        onTabClick(index)
                                    }
                                    ), toDisplayString(item.name), 11, utsArrayOf(
                                        "onClick"
                                    ))
                                }
                                ), 128)
                            ), 4),
                            createElementVNode("view", utsMapOf("ref_key" to "indicator", "ref" to indicator, "class" to "swiper-tabs-indicator"), null, 512)
                        ), 512),
                        createElementVNode("swiper", utsMapOf("ref_key" to "swiper", "ref" to swiper, "class" to "swiper-view", "current" to unref(swiperIndex), "onTransition" to onSwiperTransition, "onAnimationfinish" to onSwiperAnimationfinish), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(unref(swiperList), fun(item, index, __index, _cached): Any {
                                return createElementVNode("swiper-item", utsMapOf("class" to "swiper-item", "key" to index), utsArrayOf(
                                    createVNode(unref(GenPagesTemplateLongListLongListPageClass), utsMapOf("ref_for" to true, "ref_key" to "longPageRef", "ref" to longPageRef, "type" to item.type, "preload" to item.preload), null, 8, utsArrayOf(
                                        "type",
                                        "preload"
                                    ))
                                ))
                            }
                            ), 128)
                        ), 40, utsArrayOf(
                            "current"
                        ))
                    ))
                ), 544)
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
                return utsMapOf("flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "page" to padStyleMapOf(utsMapOf("flex" to 1)), "search-bar" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "swiper-list" to padStyleMapOf(utsMapOf("height" to "100%")), "swiper-tabs" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff", "flexDirection" to "column")), "swiper-tabs-item" to padStyleMapOf(utsMapOf("color" to "#555555", "fontSize" to 16, "paddingTop" to 12, "paddingRight" to 25, "paddingBottom" to 12, "paddingLeft" to 25, "whiteSpace" to "nowrap")), "swiper-tabs-item-active" to padStyleMapOf(utsMapOf("color" to "#007AFF")), "swiper-tabs-indicator" to padStyleMapOf(utsMapOf("width" to 1, "height" to 2, "backgroundColor" to "#007AFF")), "swiper-view" to padStyleMapOf(utsMapOf("flex" to 1)), "swiper-item" to padStyleMapOf(utsMapOf("height" to "100%")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
