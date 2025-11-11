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
open class GenPagesComponentSliderSliderInSwiper : BasePage {
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
            run {
                var i: Number = 0
                while(i < 3){
                    val space = " ".repeat(i)
                    this.swiperList.push(SwiperViewItem(title = "Tab " + space + i))
                    i++
                }
            }
        }
        , __ins)
        onReady(fun() {
            this.tabScrollView = this.`$refs`["tabScroll"] as UniElement
            this.indicatorNode = this.`$refs`["indicator"] as UniElement
            (this.`$refs`["swiper"] as UniElement).getBoundingClientRectAsync()!!.then(fun(res: DOMRect): UTSPromise<Unit> {
                this.swiperWidth = res.width
                return this.cacheTabItemsSize()
            }
            ).then(fun(){
                this.updateTabIndicator(this.swiperIndex, this.swiperIndex, 1)
            }
            )
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_slider = resolveComponent("slider")
        return createElementVNode("view", utsMapOf("class" to "swiper-list"), utsArrayOf(
            createElementVNode("scroll-view", utsMapOf("ref" to "tabScroll", "class" to "swiper-tabs", "direction" to "horizontal", "show-scrollbar" to false), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "flex-row"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.swiperList, fun(item, index, __index, _cached): Any {
                        return createElementVNode("text", utsMapOf("ref_for" to true, "ref" to "swipertab", "space" to "nbsp", "class" to normalizeClass(utsArrayOf(
                            "swiper-tabs-item",
                            if (_ctx.swiperIndex == index) {
                                "swiper-tabs-item-active"
                            } else {
                                ""
                            }
                        )), "key" to index, "onClick" to fun(){
                            _ctx.onTabClick(index)
                        }
                        ), toDisplayString(item.title), 11, utsArrayOf(
                            "onClick"
                        ))
                    }
                    ), 128)
                )),
                createElementVNode("view", utsMapOf("ref" to "indicator", "class" to "swiper-tabs-indicator"), null, 512)
            ), 512),
            createElementVNode("swiper", utsMapOf("ref" to "swiper", "class" to "swiper-view", "current" to _ctx.swiperIndex, "onTransition" to _ctx.onSwiperTransition, "onAnimationfinish" to _ctx.onSwiperAnimationfinish), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.swiperList, fun(_, index, __index, _cached): Any {
                    return createElementVNode("swiper-item", utsMapOf("class" to "swiper-item", "key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title"), "显示当前value"),
                        createVNode(_component_slider, utsMapOf("value" to 50, "show-value" to true)),
                        createElementVNode("text", utsMapOf("class" to "uni-title"), "设置步进:step=10跳动"),
                        createVNode(_component_slider, utsMapOf("value" to 60, "step" to 10)),
                        createElementVNode("text", utsMapOf("class" to "uni-title"), "浮点步进:step=0.01跳动"),
                        createVNode(_component_slider, utsMapOf("value" to 0.5, "min" to 0, "max" to 1, "step" to 0.01, "show-value" to true)),
                        createElementVNode("text", utsMapOf("class" to "uni-title"), "设置最小/最大值"),
                        createVNode(_component_slider, utsMapOf("value" to 100, "min" to 50, "max" to 200, "show-value" to true)),
                        createElementVNode("text", utsMapOf("class" to "uni-title"), "不同颜色和大小的滑块"),
                        createVNode(_component_slider, utsMapOf("id" to "slider-custom-color-and-size", "value" to _ctx.sliderValue, "backgroundColor" to _ctx.sliderBackgroundColor, "activeColor" to _ctx.sliderActiveColor, "activeBackgroundColor" to _ctx.sliderActiveColor, "blockColor" to _ctx.sliderBlockColor, "foreColor" to _ctx.sliderBlockColor, "block-size" to _ctx.sliderBlockSize), null, 8, utsArrayOf(
                            "value",
                            "backgroundColor",
                            "activeColor",
                            "activeBackgroundColor",
                            "blockColor",
                            "foreColor",
                            "block-size"
                        ))
                    ))
                }
                ), 128)
            ), 40, utsArrayOf(
                "current",
                "onTransition",
                "onAnimationfinish"
            ))
        ))
    }
    open var swiperList: UTSArray<SwiperViewItem> by `$data`
    open var swiperIndex: Number by `$data`
    open var tabScrollView: UniElement? by `$data`
    open var indicatorNode: UniElement? by `$data`
    open var animationFinishIndex: Number by `$data`
    open var swiperWidth: Number by `$data`
    open var swiperTabsRect: UTSArray<SwiperTabsItem> by `$data`
    open var sliderValue: Number by `$data`
    open var sliderBlockSize: Number by `$data`
    open var sliderBackgroundColor: String by `$data`
    open var sliderActiveColor: String by `$data`
    open var sliderBlockColor: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("swiperList" to utsArrayOf<SwiperViewItem>(), "swiperIndex" to 0, "tabScrollView" to null as UniElement?, "indicatorNode" to null as UniElement?, "animationFinishIndex" to 0, "swiperWidth" to 0, "swiperTabsRect" to utsArrayOf<SwiperTabsItem>(), "sliderValue" to 50, "sliderBlockSize" to 20, "sliderBackgroundColor" to "#000000", "sliderActiveColor" to "#FFCC33", "sliderBlockColor" to "#8A6DE9")
    }
    open var onTabClick = ::gen_onTabClick_fn
    open fun gen_onTabClick_fn(index: Number) {
        this.setSwiperIndex(index, false)
    }
    open var onSwiperTransition = ::gen_onSwiperTransition_fn
    open fun gen_onSwiperTransition_fn(e: SwiperTransitionEvent) {
        val offset_x = e.detail.dx
        val current_offset_x = offset_x % this.swiperWidth
        val current_offset_i = offset_x / this.swiperWidth
        val current_index = this.animationFinishIndex + parseInt(current_offset_i + "")
        var move_to_index = current_index
        if (current_offset_x > 0 && move_to_index < this.swiperList.length - 1) {
            move_to_index += 1
        } else if (current_offset_x < 0 && move_to_index > 0) {
            move_to_index -= 1
        }
        val percentage = Math.abs(current_offset_x) / this.swiperWidth
        if (current_index != move_to_index) {
            this.updateTabIndicator(current_index, move_to_index, percentage)
        }
    }
    open var onSwiperAnimationfinish = ::gen_onSwiperAnimationfinish_fn
    open fun gen_onSwiperAnimationfinish_fn(e: SwiperAnimationFinishEvent) {
        this.setSwiperIndex(e.detail.current, true)
        this.animationFinishIndex = e.detail.current
    }
    open var cacheTabItemsSize = ::gen_cacheTabItemsSize_fn
    open fun gen_cacheTabItemsSize_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                this.swiperTabsRect.length = 0
                val tabs = this.`$refs`["swipertab"] as UTSArray<UniElement>
                run {
                    var i: Number = 0
                    while(i < tabs.length){
                        val element = tabs[i]
                        val x = element.offsetLeft
                        val w = element.offsetWidth
                        this.swiperTabsRect.push(SwiperTabsItem(x = x, w = w))
                        i++
                    }
                }
        })
    }
    open var setSwiperIndex = ::gen_setSwiperIndex_fn
    open fun gen_setSwiperIndex_fn(index: Number, updateIndicator: Boolean) {
        if (this.swiperIndex === index) {
            return
        }
        this.swiperIndex = index
        if (updateIndicator) {
            this.updateTabIndicator(index, index, 1)
        }
    }
    open var updateTabIndicator = ::gen_updateTabIndicator_fn
    open fun gen_updateTabIndicator_fn(current_index: Number, move_to_index: Number, percentage: Number) {
        val current_size = this.swiperTabsRect[current_index]
        val move_to_size = this.swiperTabsRect[move_to_index]
        val indicator_line_x = lerpNumber(current_size.x, move_to_size.x, percentage)
        val indicator_line_w = lerpNumber(current_size.w, move_to_size.w, percentage)
        val x = indicator_line_x + indicator_line_w / 2
        this.indicatorNode?.style?.setProperty("transform", "translateX(" + x + "px) scaleX(" + indicator_line_w + ")")
        val scroll_x = x - this.swiperWidth / 2
        this.tabScrollView!!.scrollLeft = scroll_x
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
                return utsMapOf("flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignSelf" to "flex-start")), "swiper-list" to padStyleMapOf(utsMapOf("flex" to 1)), "swiper-tabs" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff")), "swiper-tabs-item" to padStyleMapOf(utsMapOf("color" to "#555555", "fontSize" to 16, "paddingTop" to 12, "paddingRight" to 25, "paddingBottom" to 12, "paddingLeft" to 25, "whiteSpace" to "nowrap")), "swiper-tabs-item-active" to padStyleMapOf(utsMapOf("color" to "#007AFF")), "swiper-tabs-indicator" to padStyleMapOf(utsMapOf("width" to 1, "height" to 2, "backgroundColor" to "#007AFF")), "swiper-view" to padStyleMapOf(utsMapOf("flex" to 1)), "swiper-item" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
