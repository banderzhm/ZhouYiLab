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
import io.dcloud.uniapp.extapi.`$off` as uni__off
import io.dcloud.uniapp.extapi.`$on` as uni__on
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.stopPullDownRefresh as uni_stopPullDownRefresh
open class GenPagesComponentUnicloudDbUnicloudDbContactsList : BasePage {
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
        onReady(fun() {
            uni__on(UNICLOUD_DB_CONTACTS_DELETE, this.onDataChange)
            this.uniCloudElement = this.`$refs`["udb"] as UniCloudDBElement
            this.uniCloudElement!!.loadData()
        }
        , __ins)
        onUnload(fun() {
            uni__off(UNICLOUD_DB_CONTACTS_ADD, this.onDataChange)
            uni__off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange)
            uni__off(UNICLOUD_DB_CONTACTS_DELETE, this.onDataChange)
        }
        , __ins)
        onPageShow(fun() {
            uni__off(UNICLOUD_DB_CONTACTS_ADD, this.onDataChange)
            uni__off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange)
        }
        , __ins)
        onPullDownRefresh(fun() {
            this.uniCloudElement!!.loadData(object : UTSJSONObject() {
                var clear = true
                var success = fun(_: UniCloudDBGetResult){
                    uni_stopPullDownRefresh()
                }
            })
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_uni_loading = resolveEasyComponent("uni-loading", GenUniModulesUniLoadingComponentsUniLoadingUniLoadingClass)
        val _component_unicloud_db = resolveEasyComponent("unicloud-db", GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDbClass)
        return createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
            createVNode(_component_unicloud_db, utsMapOf("ref" to "udb", "class" to "udb", "collection" to _ctx.collection, "page-size" to 15, "getcount" to true, "loadtime" to "manual"), utsMapOf("default" to withScopedSlotCtx(fun(slotProps: GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDbSlotDataDefault): UTSArray<Any> {
                val data = slotProps.data
                val pagination = slotProps.pagination
                val loading = slotProps.loading
                val hasMore = slotProps.hasMore
                val error = slotProps.error
                return utsArrayOf(
                    if (error != null) {
                        createElementVNode("view", utsMapOf("key" to 0, "class" to "error"), toDisplayString(error.errMsg), 1)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("scroll-view", utsMapOf("ref" to "listView", "class" to "list-view", "onScrolltolower" to fun(){
                        _ctx.loadMore()
                    }
                    ), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(data, fun(item, _, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "list-item", "onClick" to fun(){
                                _ctx.gotoDetailPage(item["_id"] as String)
                            }
                            ), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "list-item-fill"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "name"), toDisplayString(item["username"]), 1),
                                    createElementVNode("text", utsMapOf("class" to "mobile"), toDisplayString(item["mobile"]), 1)
                                ))
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        }
                        ), 256)
                    ), 40, utsArrayOf(
                        "onScrolltolower"
                    )),
                    if (isTrue(loading)) {
                        createVNode(_component_uni_loading, utsMapOf("key" to 1, "class" to "loading", "color" to "#999", "text" to "正在加载..."))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    if (data.length > 0) {
                        createElementVNode("view", utsMapOf("key" to 2, "class" to "pagination"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "pagination-item"), toDisplayString(data.length) + " / " + toDisplayString(pagination.count), 1)
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                )
            }
            ), "_" to 1), 8, utsArrayOf(
                "collection"
            )),
            createElementVNode("view", utsMapOf("class" to "btn-plus", "onClick" to _ctx.gotoAddPage), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "btn-plus-text"), "+")
            ), 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var collection: Any? by `$data`
    open var uniCloudElement: UniCloudDBElement? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("collection" to COLLECTION_NAME, "uniCloudElement" to null as UniCloudDBElement?)
    }
    open var loadMore = ::gen_loadMore_fn
    open fun gen_loadMore_fn() {
        this.uniCloudElement!!.loadMore()
    }
    open var gotoAddPage = ::gen_gotoAddPage_fn
    open fun gen_gotoAddPage_fn() {
        uni__on(UNICLOUD_DB_CONTACTS_ADD, this.onDataChange)
        uni_navigateTo(NavigateToOptions(url = "./add"))
    }
    open var gotoDetailPage = ::gen_gotoDetailPage_fn
    open fun gen_gotoDetailPage_fn(id: String) {
        uni__on(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange)
        uni_navigateTo(NavigateToOptions(url = "./detail?id=" + id))
    }
    open var onDataChange = ::gen_onDataChange_fn
    open fun gen_onDataChange_fn(_0: String) {
        this.uniCloudElement!!.loadData(object : UTSJSONObject() {
            var clear = true
        })
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "flexDirection" to "column")), "loading" to padStyleMapOf(utsMapOf("marginTop" to 20, "marginRight" to 20, "marginBottom" to 20, "marginLeft" to 20, "alignSelf" to "center")), "udb" to padStyleMapOf(utsMapOf("flex" to 1, "flexDirection" to "column")), "list-view" to padStyleMapOf(utsMapOf("flex" to 1, "flexDirection" to "column")), "list-item" to padStyleMapOf(utsMapOf("flexDirection" to "row", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "backgroundColor" to "#ffffff", "marginBottom" to 1)), "mobile" to padStyleMapOf(utsMapOf("marginTop" to 5)), "btn-plus" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to 64, "height" to 64, "right" to 20, "bottom" to 20, "alignItems" to "center", "justifyContent" to "center", "backgroundColor" to "#1e90ff", "borderTopLeftRadius" to 64, "borderTopRightRadius" to 64, "borderBottomRightRadius" to 64, "borderBottomLeftRadius" to 64)), "btn-plus-text" to padStyleMapOf(utsMapOf("fontSize" to 30, "color" to "#ffffff")), "pagination" to padStyleMapOf(utsMapOf("alignItems" to "center", "backgroundColor" to "#f8f8f8", "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
