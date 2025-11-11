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
import io.dcloud.uniapp.extapi.getDeviceInfo as uni_getDeviceInfo
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.request as uni_request
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesTemplateListNewsListNews : BasePage {
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
        var setup: (__props: GenPagesTemplateListNewsListNews) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateListNewsListNews
            val _cache = __ins.renderCache
            val th_item = ref(utsArrayOf(
                "排序",
                "筛选"
            ))
            val refresherTriggered = ref(false)
            val banner = ref(Banner1())
            val listData = ref(utsArrayOf<Item7>())
            val last_id = ref("")
            val isWideScreen = ref(false)
            val currentIndex = ref(0)
            val post_id = ref("")
            val cover = ref("")
            val title = ref("")
            val firstDetailContent = ref("")
            fun gen_checkScreenWidth_fn() {
                val deviceType = uni_getDeviceInfo(null).deviceType
                val windowWidth = uni_getWindowInfo().windowWidth
                isWideScreen.value = (deviceType === "pad" || deviceType === "pc" || windowWidth > 700)
            }
            val checkScreenWidth = ::gen_checkScreenWidth_fn
            fun gen_getBanner_fn() {
                var data: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("data", "pages/template/list-news/list-news.uvue", 95, 9)) {
                    var column = "id,post_id,title,author_name,cover,published_at"
                }
                uni_request<Banner1>(RequestOptions(url = "https://unidemo.dcloud.net.cn/api/banner/36kr", data = data, success = fun(data){
                    refresherTriggered.value = false
                    if (data.statusCode == 200) {
                        val result = data.data
                        if (result != null) {
                            banner.value = result
                        }
                    }
                }
                , fail = fun(e){
                    console.log("fail", e, " at pages/template/list-news/list-news.uvue:111")
                }
                ))
            }
            val getBanner = ::gen_getBanner_fn
            fun gen_goDetail_fn(e: Item7, key: Number) {
                currentIndex.value = key
                val detail = e
                post_id.value = detail.post_id
                cover.value = detail.cover
                title.value = detail.title
                if (!isWideScreen.value) {
                    cover.value = btoa(cover.value)
                    uni_navigateTo(NavigateToOptions(url = "/pages/template/list-news/detail/detail?post_id=" + post_id.value + "&cover=" + cover.value + "&title=" + title.value + "&shareKey=image_" + key))
                }
            }
            val goDetail = ::gen_goDetail_fn
            fun gen_getList_fn() {
                var url = "https://unidemo.dcloud.net.cn/api/news?column=id,post_id,title,author_name,cover,published_at"
                uni_request<UTSArray<Item7>>(RequestOptions(url = url, method = "GET", success = fun(res){
                    if (res.statusCode == 200) {
                        val result = res.data
                        if (result != null) {
                            listData.value = result
                            if (isWideScreen.value && listData.value.length > 0) {
                                goDetail(listData.value[0], 0)
                                val first = listData.value[0]
                                uni_request<Any>(RequestOptions(url = "https://unidemo.dcloud.net.cn/api/news/36kr/" + first.post_id, success = fun(res){
                                    if (res.statusCode == 200) {
                                        val result = res.data as UTSJSONObject
                                        firstDetailContent.value = result["content"] as String
                                    }
                                }
                                ))
                            }
                        }
                        refresherTriggered.value = false
                    }
                }
                , fail = fun(res){
                    console.log("fail", res, " at pages/template/list-news/list-news.uvue:172")
                    refresherTriggered.value = false
                }
                ))
            }
            val getList = ::gen_getList_fn
            fun gen_bannerClick_fn(e: Banner1) {
                val detail = e
                val post_id_val = detail.post_id
                var cover_val = detail.cover ?: ""
                cover_val = btoa(cover_val)
                val title_val = detail.title
                uni_navigateTo(NavigateToOptions(url = "/pages/template/list-news/detail/detail?post_id=" + post_id_val + "&cover=" + cover_val + "&title=" + title_val))
            }
            val bannerClick = ::gen_bannerClick_fn
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
            val currentDetailContent = computed(fun(): String {
                if (isWideScreen.value && listData.value.length > 0 && post_id.value === listData.value[0].post_id && firstDetailContent.value !== "") {
                    return firstDetailContent.value
                }
                return ""
            }
            )
            val isDarkMode = computed(fun(): Boolean {
                return state.isDarkMode
            }
            )
            onLoad(fun(_options){
                checkScreenWidth()
                getBanner()
                getList()
            }
            )
            onResize(fun(_options){
                checkScreenWidth()
            }
            )
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                    "uni-container",
                    utsMapOf("flex-row" to unref(isWideScreen)),
                    if (unref(isDarkMode)) {
                        "theme-dark"
                    } else {
                        "theme-light"
                    }
                )), "style" to normalizeStyle(utsMapOf("flex" to "1", "padding" to "0px"))), utsArrayOf(
                    createElementVNode("list-view", utsMapOf("class" to normalizeClass(utsArrayOf(
                        "list-container",
                        utsMapOf("list-narrow" to unref(isWideScreen))
                    )), "refresher-enabled" to "true", "onRefresherrefresh" to onRefresherrefresh, "refresher-triggered" to unref(refresherTriggered), "enable-back-to-top" to "true"), utsArrayOf(
                        createElementVNode("list-item", utsMapOf("class" to "banner", "onClick" to fun(){
                            bannerClick(unref(banner))
                        }
                        , "type" to "1"), utsArrayOf(
                            createElementVNode("image", utsMapOf("class" to "banner-img", "src" to unref(banner).cover), null, 8, utsArrayOf(
                                "src"
                            )),
                            createElementVNode("text", utsMapOf("class" to "banner-title"), toDisplayString(unref(banner).title), 1)
                        ), 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("sticky-section", null, utsArrayOf(
                            createElementVNode("sticky-header", null, utsArrayOf(
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
                            )),
                            createElementVNode(Fragment, null, RenderHelpers.renderList(unref(listData), fun(value, index, __index, _cached): Any {
                                return createElementVNode("list-item", utsMapOf("key" to index, "type" to "2"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell", "hover-class" to "uni-list-cell-hover", "onClick" to fun(){
                                        goDetail(value, index)
                                    }
                                    ), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "uni-media-list"), utsArrayOf(
                                            if (isTrue(unref(isWideScreen))) {
                                                createElementVNode("image", utsMapOf("key" to 0, "class" to "uni-media-list-logo", "src" to value.cover), null, 8, utsArrayOf(
                                                    "src"
                                                ))
                                            } else {
                                                createElementVNode("share-element", utsMapOf("key" to 1, "share-key" to ("image_" + index)), utsArrayOf(
                                                    createElementVNode("image", utsMapOf("class" to "uni-media-list-logo", "src" to value.cover), null, 8, utsArrayOf(
                                                        "src"
                                                    ))
                                                ), 8, utsArrayOf(
                                                    "share-key"
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
                                    ), 8, utsArrayOf(
                                        "onClick"
                                    ))
                                ))
                            }
                            ), 128)
                        ))
                    ), 42, utsArrayOf(
                        "refresher-triggered"
                    )),
                    if (isTrue(unref(isWideScreen))) {
                        createElementVNode("view", utsMapOf("key" to 0, "class" to "detail-container"), utsArrayOf(
                            if (isTrue(unref(post_id) != "" && unref(title) != "" && unref(cover) != "")) {
                                createVNode(unref(GenPagesTemplateListNewsDetailDetailClass), utsMapOf("key" to unref(post_id), "post_id" to unref(post_id), "cover" to unref(cover), "title" to unref(title), "isWideScreen" to unref(isWideScreen), "firstDetailContent" to unref(currentDetailContent)), null, 8, utsArrayOf(
                                    "post_id",
                                    "cover",
                                    "title",
                                    "isWideScreen",
                                    "firstDetailContent"
                                ))
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
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
                return utsMapOf("flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "list-container" to padStyleMapOf(utsMapOf("width" to "100%", "backgroundColor" to "var(--list-background-color,#ffffff)", "flex" to 1)), "list-narrow" to padStyleMapOf(utsMapOf("width" to 100)), "th-item" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 44, "backgroundColor" to "var(--list-background-color,#ffffff)", "flexDirection" to "row", "justifyContent" to "center", "alignItems" to "center")), "th-item-text" to padStyleMapOf(utsMapOf("marginRight" to 20, "color" to "var(--text-color,#333333)")), "detail-container" to padStyleMapOf(utsMapOf("flex" to 1, "minWidth" to 0, "paddingTop" to 20, "paddingRight" to 20, "paddingBottom" to 20, "paddingLeft" to 20, "backgroundColor" to "var(--background-color,#f8f8f8)")), "banner" to padStyleMapOf(utsMapOf("height" to 180, "overflow" to "hidden", "position" to "relative", "backgroundColor" to "var(--background-color,#f8f8f8)")), "banner-img" to padStyleMapOf(utsMapOf("width" to "100%")), "banner-title" to padStyleMapOf(utsMapOf("maxHeight" to 42, "overflow" to "hidden", "position" to "absolute", "left" to 15, "bottom" to 15, "width" to "90%", "fontSize" to 16, "fontWeight" to "400", "lineHeight" to "21px", "color" to "#FFFFFF")), "uni-media-list" to padStyleMapOf(utsMapOf("paddingTop" to 11, "paddingRight" to 15, "paddingBottom" to 11, "paddingLeft" to 15, "boxSizing" to "border-box", "display" to "flex", "width" to "100%", "flexDirection" to "row")), "uni-media-list-logo" to padStyleMapOf(utsMapOf("width" to 90, "height" to 70)), "uni-media-list-body" to padStyleMapOf(utsMapOf("flex" to 1, "paddingLeft" to 7, "justifyContent" to "space-around")), "uni-media-list-text-top" to padStyleMapOf(utsMapOf("fontSize" to 14, "lines" to 2, "overflow" to "hidden", "color" to "var(--text-color,#333333)")), "uni-media-list-text-bottom" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "justifyContent" to "space-between")), "uni-media-list-text" to padStyleMapOf(utsMapOf("color" to "#9D9D9F", "fontSize" to 13)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
