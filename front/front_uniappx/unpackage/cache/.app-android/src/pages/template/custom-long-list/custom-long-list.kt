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
open class GenPagesTemplateCustomLongListCustomLongList : BasePage {
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
        var setup: (__props: GenPagesTemplateCustomLongListCustomLongList) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateCustomLongListCustomLongList
            val _cache = __ins.renderCache
            val title = ref("自定义复用滚动列表")
            val list = reactive<UTSArray<Item8>>(utsArrayOf<Item8>())
            val refresherEnabled = ref(true)
            val refresherTriggered = ref(false)
            val hasMore = ref(true)
            val minCode: Number = 0x4E00
            val maxCode: Number = 0x9FFF
            val codeRange = maxCode - minCode
            fun generateChineseString(index: Number, length: Number = 4): String {
                var result = ""
                run {
                    var i: Number = 0
                    while(i < length){
                        val code = (index * length + i) % codeRange + minCode
                        result += String.fromCharCode(code)
                        i++
                    }
                }
                return result
            }
            fun gen_loadData_fn() {
                run {
                    var i: Number = 0
                    while(i < 200){
                        val index = list.length
                        val tags: UTSArray<String> = utsArrayOf()
                        val tagCount: Number = 4
                        run {
                            var i: Number = 0
                            while(i < tagCount){
                                tags.push(generateChineseString(index * tagCount + i, 4))
                                i++
                            }
                        }
                        list.push(Item8(img = "https://web-ext-storage.dcloud.net.cn/hello-uni-app-x/drop-card-" + (index % 3 + 1) + ".jpg", name = "Name_" + index, info = "Info_" + index, tags = tags))
                        i++
                    }
                }
            }
            val loadData = ::gen_loadData_fn
            onMounted(fun(){
                loadData()
            }
            )
            fun gen_onRefresherRefresh_fn() {
                refresherTriggered.value = true
                setTimeout(fun(){
                    list.splice(0, list.length)
                    loadData()
                    refresherTriggered.value = false
                }
                , 1000)
            }
            val onRefresherRefresh = ::gen_onRefresherRefresh_fn
            fun gen_onScrollToLower_fn() {
                if (list.length >= 5000) {
                    hasMore.value = false
                } else {
                    loadData()
                }
            }
            val onScrollToLower = ::gen_onScrollToLower_fn
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                val _component_navigator = resolveComponent("navigator")
                val _component_custom_recycle_view_buttons = resolveEasyComponent("custom-recycle-view-buttons", GenComponentsCustomRecycleViewButtonsCustomRecycleViewButtonsClass)
                val _component_uni_recycle_item = resolveEasyComponent("uni-recycle-item", GenUniModulesUniRecycleViewComponentsUniRecycleItemUniRecycleItemClass)
                val _component_uni_recycle_view = resolveEasyComponent("uni-recycle-view", GenUniModulesUniRecycleViewComponentsUniRecycleViewUniRecycleViewClass)
                return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to unref(title)), null, 8, utsArrayOf(
                        "title"
                    )),
                    createElementVNode("view", utsMapOf("class" to "tips"), "滚动期间对子组件回收复用。此示例限制列表子项固定高度。注意回收复用引发的副作用，详情参考custom-recycle-view-buttons组件内的错误用法。"),
                    createVNode(_component_uni_recycle_view, utsMapOf("itemHeight" to 140, "list" to unref(list), "style" to normalizeStyle(utsMapOf("flex" to "1")), "onScrolltolower" to onScrollToLower, "onRefresherrefresh" to onRefresherRefresh, "refresher-enabled" to unref(refresherEnabled), "refresher-triggered" to unref(refresherTriggered)), utsMapOf("default" to withScopedSlotCtx(fun(slotProps: Record<String, Any?>): UTSArray<Any> {
                        val layoutItems = slotProps["layoutItems"]
                        val items = slotProps["items"]
                        return utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList((items as UTSArray<Item8>), fun(item, index, __index, _cached): Any {
                                return createVNode(_component_uni_recycle_item, utsMapOf("key" to (layoutItems as UTSArray<LayoutItem>)[index].id, "offset" to (layoutItems as UTSArray<LayoutItem>)[index].offset), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                    return utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "item-wrapper"), utsArrayOf(
                                            createVNode(_component_navigator, utsMapOf("hover-stay-time" to 0, "url" to ("/pages/detail/detail?name=" + item.name)), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                return utsArrayOf(
                                                    createElementVNode("view", utsMapOf("class" to "item-content"), utsArrayOf(
                                                        createElementVNode("image", utsMapOf("class" to "item-image", "src" to item.img, "mode" to "aspectFill"), null, 8, utsArrayOf(
                                                            "src"
                                                        )),
                                                        createElementVNode("view", utsMapOf("class" to "item-text"), utsArrayOf(
                                                            createElementVNode("view", null, utsArrayOf(
                                                                createElementVNode("text", utsMapOf("class" to "item-name"), toDisplayString(item.name), 1)
                                                            )),
                                                            createElementVNode("view", null, utsArrayOf(
                                                                createElementVNode("text", utsMapOf("class" to "item-info"), toDisplayString(if (item.name.length % 2 == 0) {
                                                                    "name长度为偶数"
                                                                } else {
                                                                    "name长度为奇数"
                                                                }
                                                                ), 1)
                                                            )),
                                                            createElementVNode("view", null, utsArrayOf(
                                                                createElementVNode("text", utsMapOf("class" to "item-info"), toDisplayString(item.info), 1)
                                                            )),
                                                            createElementVNode("view", null, utsArrayOf(
                                                                createElementVNode("text", utsMapOf("class" to "item-info"), toDisplayString(if (item.info.length % 2 == 0) {
                                                                    "info长度为偶数"
                                                                } else {
                                                                    "info长度为奇数"
                                                                }
                                                                ), 1)
                                                            )),
                                                            createElementVNode("view", null, utsArrayOf(
                                                                createElementVNode("text", utsMapOf("class" to "item-info"), toDisplayString(if ((item.info.length + item.name.length) % 2 == 0) {
                                                                    "name+info长度为偶数"
                                                                } else {
                                                                    "name+info长度为奇数"
                                                                }
                                                                ), 1)
                                                            )),
                                                            if (item.tags.length > 0) {
                                                                createElementVNode("view", utsMapOf("key" to 0, "class" to "item-tags"), utsArrayOf(
                                                                    createElementVNode("text", utsMapOf("class" to "item-info"), "随机tag："),
                                                                    createElementVNode(Fragment, null, RenderHelpers.renderList(item.tags, fun(tag, index, __index, _cached): Any {
                                                                        return createElementVNode("text", utsMapOf("class" to "item-tag", "key" to index), toDisplayString(tag), 1)
                                                                    }), 128)
                                                                ))
                                                            } else {
                                                                createCommentVNode("v-if", true)
                                                            }
                                                        ))
                                                    ))
                                                )
                                            }
                                            ), "_" to 2), 1032, utsArrayOf(
                                                "url"
                                            )),
                                            createVNode(_component_custom_recycle_view_buttons, utsMapOf("name" to item.name), null, 8, utsArrayOf(
                                                "name"
                                            ))
                                        ))
                                    )
                                }
                                ), "_" to 2), 1032, utsArrayOf(
                                    "offset"
                                ))
                            }
                            ), 128)
                        )
                    }
                    ), "load-more" to withSlotCtx(fun(): UTSArray<Any> {
                        return utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "load-more"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "load-more-text"), toDisplayString(if (unref(hasMore)) {
                                    "加载中..."
                                } else {
                                    "没有更多"
                                }
                                ), 1)
                            ))
                        )
                    }
                    ), "_" to 1), 8, utsArrayOf(
                        "list",
                        "style",
                        "refresher-enabled",
                        "refresher-triggered"
                    ))
                ), 4)
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
                return utsMapOf("tips" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10, "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "backgroundColor" to "#FFFFFF")), "item-wrapper" to padStyleMapOf(utsMapOf("height" to 140, "justifyContent" to "center", "boxSizing" to "border-box", "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#cccccc", "paddingTop" to 0, "paddingRight" to 15, "paddingBottom" to 0, "paddingLeft" to 15)), "item-content" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "flex-start")), "item-image" to padStyleMapOf(utsMapOf("width" to 80, "height" to 80)), "item-text" to padStyleMapOf(utsMapOf("flexDirection" to "column", "marginLeft" to 5)), "item-name" to padStyleMapOf(utsMapOf("fontSize" to 14)), "item-info" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#999999")), "item-tags" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center")), "item-tag" to padStyleMapOf(utsMapOf("backgroundColor" to "#F0F8FF", "color" to "#999999", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#999999", "borderRightColor" to "#999999", "borderBottomColor" to "#999999", "borderLeftColor" to "#999999", "borderTopLeftRadius" to 3, "borderTopRightRadius" to 3, "borderBottomRightRadius" to 3, "borderBottomLeftRadius" to 3, "paddingTop" to 0, "paddingRight" to 2, "paddingBottom" to 0, "paddingLeft" to 2, "fontSize" to 12, "lineHeight" to "16px", "marginRight" to 2)), "load-more-text" to padStyleMapOf(utsMapOf("color" to "#cccccc", "fontSize" to 12, "textAlign" to "center", "lineHeight" to "50px")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
