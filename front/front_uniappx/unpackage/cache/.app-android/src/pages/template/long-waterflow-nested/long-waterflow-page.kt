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
import io.dcloud.uniapp.extapi.request as uni_request
open class GenPagesTemplateLongWaterflowNestedLongWaterflowPage : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {}
    open var type: String by `$props`
    open var preload: Boolean by `$props`
    open var id: String by `$props`
    open var refreshData: (loadComplete: (() -> Unit)?) -> Unit
        get() {
            return unref(this.`$exposed`["refreshData"]) as (loadComplete: (() -> Unit)?) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "refreshData", value)
        }
    open var loadData: (loadComplete: (() -> Unit)?) -> Unit
        get() {
            return unref(this.`$exposed`["loadData"]) as (loadComplete: (() -> Unit)?) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "loadData", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesTemplateLongWaterflowNestedLongWaterflowPage, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateLongWaterflowNestedLongWaterflowPage
            val _cache = __ins.renderCache
            val SERVER_URL = "https://unidemo.dcloud.net.cn/plugin/uniappx-plugin-list"
            val PAGE_SIZE: Number = 10
            val props = __props
            val loading = ref(false)
            val datagrid = ref(utsArrayOf<flowItem>())
            val isEnded = ref(false)
            val loadingError = ref("")
            val currentPage = ref(1)
            val loadingText = computed(fun(): String {
                if (loading.value) {
                    return "加载中..."
                } else if (isEnded.value) {
                    return "没有更多了"
                } else if (loadingError.value.length > 0) {
                    return loadingError.value
                } else {
                    return ""
                }
            }
            )
            fun gen_loadData_fn(loadComplete: (() -> Unit)?) {
                if (loading.value || isEnded.value) {
                    return
                }
                loading.value = true
                uni_request<ResponseDataType1>(RequestOptions(url = SERVER_URL, data = object : UTSJSONObject() {
                    var type = props.type
                    var page = currentPage.value
                    var page_size = PAGE_SIZE
                }, success = fun(res){
                    val responseData = res.data
                    if (responseData == null) {
                        return
                    }
                    datagrid.value.push(*responseData.data.toTypedArray())
                    if (responseData.data.length == 0) {
                        isEnded.value = true
                    } else {
                        currentPage.value++
                    }
                }
                , fail = fun(err){
                    loadingError.value = err.errMsg
                }
                , complete = fun(_){
                    loading.value = false
                    if (loadComplete != null) {
                        loadComplete()
                    }
                }
                ))
            }
            val loadData = ::gen_loadData_fn
            fun gen_refreshData_fn(loadComplete: (() -> Unit)?) {
                datagrid.value.length = 0
                currentPage.value = 1
                loadData(loadComplete)
            }
            val refreshData = ::gen_refreshData_fn
            fun gen_convertToStarUnicode_fn(score: Number): String {
                val fill_code = "\ue879"
                val half_code = "\ue87a"
                val null_code = "\ue87b"
                val fillStarCount = parseInt(score / 10 % 10 + "")
                val halfStarCount = if (score % 10 >= 5) {
                    1
                } else {
                    0
                }
                val nullStarCount = 5 - fillStarCount - halfStarCount
                var result = ""
                if (fillStarCount > 0) {
                    result += fill_code.repeat(fillStarCount)
                }
                if (halfStarCount > 0) {
                    result += half_code.repeat(halfStarCount)
                }
                if (nullStarCount > 0) {
                    result += null_code.repeat(nullStarCount)
                }
                return result
            }
            val convertToStarUnicode = ::gen_convertToStarUnicode_fn
            onMounted(fun(){
                if (props.preload) {
                    loadData(null)
                }
            }
            )
            __expose(utsMapOf("refreshData" to refreshData, "loadData" to loadData))
            return fun(): Any? {
                val _component_uni_loading = resolveEasyComponent("uni-loading", GenUniModulesUniLoadingComponentsUniLoadingUniLoadingClass)
                return createElementVNode("waterflow", utsMapOf("id" to _ctx.id, "class" to "grid", "bounces" to false, "scroll-y" to true, "custom-nested-scroll" to true, "main-axis-gap" to "8px", "cross-axis-gap" to "8px", "cross-axis-count" to "2", "onScrolltolower" to fun(){
                    loadData(null)
                }
                , "associative-container" to "nested-scroll-view"), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(unref(datagrid), fun(item, _, __index, _cached): Any {
                        return createElementVNode("flow-item", utsMapOf("class" to "flow-item", "key" to item.plugin_id, "type" to "1"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "flow-item-icon"), utsArrayOf(
                                createElementVNode("image", utsMapOf("class" to "flow-item-icon-image", "src" to item.plugin_img_link, "mode" to "aspectFit"), null, 8, utsArrayOf(
                                    "src"
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "flow-item-fill"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "flex-row"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "title"), toDisplayString(item.plugin_name), 1)
                                )),
                                createElementVNode("view", null, utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "description-text"), toDisplayString(item.plugin_intro), 1)
                                )),
                                createElementVNode("text", utsMapOf("class" to "icon-star"), toDisplayString(convertToStarUnicode(item.score)), 1),
                                createElementVNode("view", utsMapOf("class" to "tag-grid"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(item.tags.slice(0, 1), fun(item2, index2, __index, _cached): Any {
                                        return createElementVNode("text", utsMapOf("class" to "tag-item", "key" to index2), toDisplayString(item2), 1)
                                    }
                                    ), 128)
                                )),
                                createElementVNode("view", utsMapOf("class" to "flex-row update-date"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "author"), toDisplayString(item.author_name), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "flex-row update-date"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "update-date-text"), "更新日期"),
                                    createElementVNode("text", utsMapOf("class" to "update-date-value"), toDisplayString(item.update_date), 1)
                                ))
                            ))
                        ))
                    }
                    ), 128),
                    createElementVNode("flow-item", utsMapOf("slot" to "load-more", "class" to "loading", "type" to "6"), utsArrayOf(
                        createVNode(_component_uni_loading, utsMapOf("loading" to unref(loading), "color" to "#999", "text" to unref(loadingText)), null, 8, utsArrayOf(
                            "loading",
                            "text"
                        ))
                    ))
                ), 40, utsArrayOf(
                    "id",
                    "onScrolltolower"
                ))
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("grid" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#f7f7f7")), "flow-item" to padStyleMapOf(utsMapOf("flexDirection" to "column", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "backgroundColor" to "#ffffff")), "flow-item-icon" to padStyleMapOf(utsMapOf("position" to "relative")), "flow-item-icon-image" to padStyleMapOf(utsMapOf("width" to "100%")), "flow-item-fill" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 5, "paddingRight" to 5, "paddingBottom" to 5, "paddingLeft" to 5)), "description-text" to padStyleMapOf(utsMapOf("fontSize" to 13, "color" to "#666666", "lineHeight" to "19px")), "icon-star" to padStyleMapOf(utsMapOf("fontFamily" to "UtsStarIcons", "fontSize" to 16, "fontStyle" to "normal", "color" to "#ffca3e", "letterSpacing" to 3)), "tag-grid" to padStyleMapOf(utsMapOf("flexDirection" to "row", "marginTop" to 5)), "tag-item" to padStyleMapOf(utsMapOf("fontSize" to 12, "backgroundColor" to "#EFF9F0", "color" to "#639069", "borderTopLeftRadius" to 20, "borderTopRightRadius" to 20, "borderBottomRightRadius" to 20, "borderBottomLeftRadius" to 20, "marginRight" to 5, "paddingTop" to 2, "paddingRight" to 5, "paddingBottom" to 2, "paddingLeft" to 5)), "update-date" to padStyleMapOf(utsMapOf("marginTop" to 10)), "update-date-text" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#888888")), "update-date-value" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#777777", "marginLeft" to 5)), "author" to padStyleMapOf(utsMapOf("fontSize" to 12, "color" to "#008000")), "loading" to padStyleMapOf(utsMapOf("paddingTop" to 30, "paddingRight" to 30, "paddingBottom" to 30, "paddingLeft" to 30, "alignItems" to "center", "height" to 80)), "flex-row" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "@FONT-FACE" to utsMapOf("0" to utsMapOf("fontFamily" to "UtsStarIcons", "src" to "url('/static/fonts/icon-star.ttf')")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("type" to utsMapOf("type" to "String", "default" to ""), "preload" to utsMapOf("type" to "Boolean", "default" to false), "id" to utsMapOf("type" to "String", "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "type",
            "preload",
            "id"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
