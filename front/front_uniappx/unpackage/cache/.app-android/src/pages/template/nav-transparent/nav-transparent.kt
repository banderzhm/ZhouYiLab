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
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.request as uni_request
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesTemplateNavTransparentNavTransparent : BasePage {
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
        var setup: (__props: GenPagesTemplateNavTransparentNavTransparent) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateNavTransparentNavTransparent
            val _cache = __ins.renderCache
            val th_item = ref(utsArrayOf(
                "排序",
                "筛选"
            ))
            val refresherTriggered = ref(false)
            val banner = ref(Banner())
            val listData = ref(utsArrayOf<Item6>())
            val last_id = ref("")
            val isWideScreen = ref(false)
            val currentIndex = ref(0)
            val post_id = ref("")
            val cover = ref("")
            val title = ref("")
            val firstDetailContent = ref("")
            val navbar = ref<UniElement?>(null)
            val backRef = ref<UniElement?>(null)
            val textRef = ref<UniElement?>(null)
            val iconColor = ref("#999")
            val navbarHeight = ref(0)
            val tabRef = ref<UniElement?>(null)
            val tabTop = ref(0)
            fun gen_getBanner_fn() {
                var data: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("data", "pages/template/nav-transparent/nav-transparent.uvue", 92, 9)) {
                    var column = "id,post_id,title,author_name,cover,published_at"
                }
                uni_request<Banner>(RequestOptions(url = "https://unidemo.dcloud.net.cn/api/banner/36kr", data = data, success = fun(data){
                    refresherTriggered.value = false
                    if (data.statusCode == 200) {
                        val result = data.data
                        if (result != null) {
                            banner.value = result
                        }
                    }
                }
                , fail = fun(e){
                    console.log("fail", e, " at pages/template/nav-transparent/nav-transparent.uvue:108")
                }
                ))
            }
            val getBanner = ::gen_getBanner_fn
            fun gen_getList_fn() {
                var url = "https://unidemo.dcloud.net.cn/api/news?column=id,post_id,title,author_name,cover,published_at"
                uni_request<UTSArray<Item6>>(RequestOptions(url = url, method = "GET", success = fun(res){
                    if (res.statusCode == 200) {
                        val result = res.data
                        if (result != null) {
                            listData.value = result
                        }
                        refresherTriggered.value = false
                    }
                }
                , fail = fun(res){
                    console.log("fail", res, " at pages/template/nav-transparent/nav-transparent.uvue:133")
                    refresherTriggered.value = false
                }
                ))
            }
            val getList = ::gen_getList_fn
            fun gen_clickTH_fn(index: Number) {
                uni_showModal(ShowModalOptions(content = "点击表头项：" + index, showCancel = false))
            }
            val clickTH = ::gen_clickTH_fn
            fun gen_onRefresherrefresh_fn() {
                refresherTriggered.value = true
                getBanner()
                getList()
            }
            val onRefresherrefresh = ::gen_onRefresherrefresh_fn
            fun gen_contentScroll_fn(event: UniScrollEvent) {
                val top = event.detail.scrollTop
                val THRESHOLD: Number = 100
                var transparent_count = parseFloat((top / THRESHOLD).toFixed(2))
                if (transparent_count >= 1) {
                    transparent_count = 1
                    iconColor.value = "#fff"
                } else {
                    iconColor.value = "#999"
                }
                navbar.value!!.style.setProperty("background-color", "rgba(0, 122, 255," + transparent_count + ")")
                backRef.value!!.style.setProperty("background-color", "rgba(255, 255, 255," + (1 - transparent_count) + ")")
                textRef.value!!.style.setProperty("opacity", transparent_count.toString(10))
                if (tabTop.value <= top) {
                    val stickyTran = top - tabTop.value
                    tabRef.value!!.style.setProperty("transform", "translateY(" + stickyTran + "px)")
                }
            }
            val contentScroll = ::gen_contentScroll_fn
            fun gen_calcStickyTop_fn(): UTSPromise<Unit> {
                return wrapUTSPromise(suspend {
                        val stickyRect = await((tabRef.value!! as UniElement).getBoundingClientRectAsync()!!)
                        val navbarRect = await((navbar.value!! as UniElement).getBoundingClientRectAsync()!!)
                        navbarHeight.value = navbarRect.height
                        tabTop.value = stickyRect.top - navbarHeight.value
                })
            }
            val calcStickyTop = ::gen_calcStickyTop_fn
            val back = fun(){
                uni_navigateBack(NavigateBackOptions())
            }
            val isDarkMode = computed(fun(): Boolean {
                return state.isDarkMode
            }
            )
            onLoad(fun(_options){
                getBanner()
                getList()
            }
            )
            onReady(fun(){
                calcStickyTop()
            }
            )
            return fun(): Any? {
                val _component_uni_icons = resolveEasyComponent("uni-icons", GenUniModulesUniIconsComponentsUniIconsUniIconsClass)
                return createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                    "uni-container",
                    if (unref(isDarkMode)) {
                        "theme-dark"
                    } else {
                        "theme-light"
                    }
                )), "style" to normalizeStyle(utsMapOf("flex" to "1", "padding" to "0px"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("ref_key" to "navbar", "ref" to navbar, "class" to "custom-navbar"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "status-bar")),
                        createElementVNode("view", utsMapOf("class" to "custom-navbar-inner"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "left-content"), utsArrayOf(
                                createElementVNode("view", utsMapOf("ref_key" to "backRef", "ref" to backRef, "class" to "left-content-inner", "onClick" to back), utsArrayOf(
                                    createVNode(_component_uni_icons, utsMapOf("style" to normalizeStyle(utsMapOf("margin-left" to "-1px")), "type" to "back", "color" to unref(iconColor)), null, 8, utsArrayOf(
                                        "style",
                                        "color"
                                    ))
                                ), 512)
                            )),
                            createElementVNode("view", utsMapOf("class" to "custom-navbar-content"), utsArrayOf(
                                createElementVNode("text", utsMapOf("ref_key" to "textRef", "ref" to textRef, "class" to "content-inner-text"), " 标题 ", 512)
                            ))
                        ))
                    ), 512),
                    createElementVNode("scroll-view", utsMapOf("class" to "list-container", "direction" to "vertical", "refresher-enabled" to "true", "onRefresherrefresh" to onRefresherrefresh, "refresher-triggered" to unref(refresherTriggered), "onScroll" to contentScroll), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "banner"), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "banner-img", "src" to unref(banner).cover), null, 8, utsArrayOf(
                                "src"
                            )),
                            createElementVNode("text", utsMapOf("class" to "banner-title"), toDisplayString(unref(banner).title), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "list-tab", "ref_key" to "tabRef", "ref" to tabRef), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "th-item"), utsArrayOf(
                                createElementVNode(Fragment, null, RenderHelpers.renderList(unref(th_item), fun(name, index, __index, _cached): Any {
                                    return createElementVNode("text", utsMapOf("key" to index, "onClick" to fun(){
                                        clickTH(index)
                                    }
                                    , "class" to "th-item-text"), toDisplayString(name), 9, utsArrayOf(
                                        "onClick"
                                    ))
                                }
                                ), 128)
                            ))
                        ), 512),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(listData), fun(value, index, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("key" to index, "class" to "uni-list-cell", "hover-class" to "uni-list-cell-hover"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-media-list"), utsArrayOf(
                                    if (isTrue(unref(isWideScreen))) {
                                        createElementVNode("image", utsMapOf("key" to 0, "class" to "uni-media-list-logo", "src" to value.cover), null, 8, utsArrayOf(
                                            "src"
                                        ))
                                    } else {
                                        createElementVNode("image", utsMapOf("key" to 1, "class" to "uni-media-list-logo", "src" to value.cover), null, 8, utsArrayOf(
                                            "src"
                                        ))
                                    }
                                    ,
                                    createElementVNode("view", utsMapOf("class" to "uni-media-list-body"), utsArrayOf(
                                        createElementVNode("text", utsMapOf("class" to "uni-media-list-text-top"), toDisplayString(value.title), 1),
                                        createElementVNode("view", utsMapOf("class" to "uni-media-list-text-bottom"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "uni-media-list-text"), toDisplayString(value.author_name), 1),
                                            createElementVNode("text", utsMapOf("class" to "uni-media-list-text"), toDisplayString(value.published_at), 1)
                                        ))
                                    ))
                                ))
                            ))
                        }
                        ), 128)
                    ), 40, utsArrayOf(
                        "refresher-triggered"
                    ))
                ), 6)
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
                return utsMapOf("list-container" to padStyleMapOf(utsMapOf("position" to "relative", "width" to "100%", "backgroundColor" to "var(--list-background-color, #ffffff)", "flex" to 1)), "th-item" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 44, "backgroundColor" to "var(--list-background-color, #ffffff)", "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center")), "th-item-text" to padStyleMapOf(utsMapOf("marginRight" to 20, "color" to "var(--text-color, #333333)")), "banner" to padStyleMapOf(utsMapOf("height" to 180, "overflow" to "hidden", "position" to "relative", "backgroundColor" to "var(--background-color, #f8f8f8)")), "banner-img" to padStyleMapOf(utsMapOf("width" to "100%")), "banner-title" to padStyleMapOf(utsMapOf("maxHeight" to 42, "overflow" to "hidden", "position" to "absolute", "left" to 15, "bottom" to 15, "width" to "90%", "fontSize" to 16, "fontWeight" to "400", "lineHeight" to "21px", "color" to "#FFFFFF")), "uni-media-list" to padStyleMapOf(utsMapOf("paddingTop" to 11, "paddingRight" to 15, "paddingBottom" to 11, "paddingLeft" to 15, "boxSizing" to "border-box", "display" to "flex", "width" to "100%", "flexDirection" to "row")), "uni-media-list-logo" to padStyleMapOf(utsMapOf("width" to 90, "height" to 70)), "uni-media-list-body" to padStyleMapOf(utsMapOf("flex" to 1, "paddingLeft" to 7, "justifyContent" to "space-around")), "uni-media-list-text-top" to padStyleMapOf(utsMapOf("fontSize" to 14, "overflow" to "hidden", "color" to "var(--text-color, #333333)")), "uni-media-list-text-bottom" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between")), "uni-media-list-text" to padStyleMapOf(utsMapOf("color" to "#9D9D9F", "fontSize" to 13)), "status-bar" to padStyleMapOf(utsMapOf("height" to CSS_VAR_STATUS_BAR_HEIGHT)), "custom-navbar" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 0, "left" to 0, "right" to 0, "zIndex" to 10, "backgroundColor" to "rgba(0,122,255,0)")), "custom-navbar-inner" to padStyleMapOf(utsMapOf("position" to "relative", "flexDirection" to "row", "justifyContent" to "space-between", "height" to 45)), "left-content" to padStyleMapOf(utsMapOf("display" to "flex", "justifyContent" to "center", "alignItems" to "center", "width" to 40, "height" to "100%")), "left-content-inner" to padStyleMapOf(utsMapOf("display" to "flex", "justifyContent" to "center", "alignItems" to "center", "width" to 28, "height" to 28, "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 40, "borderTopRightRadius" to 40, "borderBottomRightRadius" to 40, "borderBottomLeftRadius" to 40)), "custom-navbar-content" to padStyleMapOf(utsMapOf("position" to "absolute", "height" to "100%", "top" to 0, "bottom" to 0, "left" to 45, "right" to 45, "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center")), "content-inner-text" to padStyleMapOf(utsMapOf("opacity" to 0, "color" to "#f5f5f5")), "list-tab" to padStyleMapOf(utsMapOf("position" to "relative", "zIndex" to 9)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
