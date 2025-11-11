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
open class GenPagesComponentUnicloudDbUnicloudDbContactsDetail : BasePage {
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
        onLoad(fun(options: OnLoadOptions) {
            this.collection = COLLECTION_NAME
            this.whereID = options["id"] as String
            this.where = "_id=='" + this.whereID + "'"
        }
        , __ins)
        onPageShow(fun() {
            uni__off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange)
        }
        , __ins)
        onUnload(fun() {
            uni__off(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange)
        }
        , __ins)
        onReady(fun() {
            this.uniCloudElement = this.`$refs`["udb"] as UniCloudDBElement
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_unicloud_db = resolveEasyComponent("unicloud-db", GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDbClass)
        return createElementVNode("scroll-view", utsMapOf("class" to "page"), utsArrayOf(
            createVNode(_component_unicloud_db, utsMapOf("ref" to "udb", "collection" to _ctx.collection, "where" to _ctx.where, "page-data" to "replace"), utsMapOf("default" to withScopedSlotCtx(fun(slotProps: GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDbSlotDataDefault): UTSArray<Any> {
                val data = slotProps.data
                val loading = slotProps.loading
                val error = slotProps.error
                return utsArrayOf(
                    if (error != null) {
                        createElementVNode("view", utsMapOf("key" to 0, "class" to "error"), toDisplayString(error.errMsg), 1)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    if (isTrue(loading)) {
                        createElementVNode("view", utsMapOf("key" to 1, "class" to "loading"), "正在加载...")
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    if (data.length > 0) {
                        createElementVNode("view", utsMapOf("key" to 2), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "form-item-label"), "姓名"),
                                createElementVNode("text", utsMapOf("class" to "form-item-input"), toDisplayString(data[0]["username"]), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "form-item-label"), "电话"),
                                createElementVNode("text", utsMapOf("class" to "form-item-input"), toDisplayString(data[0]["mobile"]), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "form-item-label"), "邮箱"),
                                createElementVNode("text", utsMapOf("class" to "form-item-input"), toDisplayString(data[0]["email"]), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "form-item-label"), "备注"),
                                createElementVNode("text", utsMapOf("class" to "form-item-input"), toDisplayString(data[0]["comment"]), 1)
                            )),
                            createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "form-item-label"), "性别"),
                                createElementVNode("text", utsMapOf("class" to "form-item-input"), toDisplayString(_ctx.displayGender(data[0].getNumber("gender"))), 1)
                            )),
                            createElementVNode("button", utsMapOf("type" to "default", "onClick" to fun(){
                                _ctx.gotoUpdatePage(data[0].getString("_id"))
                            }), "编辑", 8, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                )
            }
            ), "_" to 1), 8, utsArrayOf(
                "collection",
                "where"
            ))
        ))
    }
    open var collection: String by `$data`
    open var where: String by `$data`
    open var whereID: String by `$data`
    open var uniCloudElement: UniCloudDBElement? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("collection" to "", "where" to "", "whereID" to "", "uniCloudElement" to null as UniCloudDBElement?)
    }
    open var displayGender = ::gen_displayGender_fn
    open fun gen_displayGender_fn(value: Number?): String {
        val str = value ?: -1
        return (GenderList as UTSArray<GenderType>).find(fun(item: GenderType): Boolean {
            return item.value == str
        }
        )!!.text
    }
    open var gotoUpdatePage = ::gen_gotoUpdatePage_fn
    open fun gen_gotoUpdatePage_fn(id: String?) {
        uni__on(UNICLOUD_DB_CONTACTS_UPDATE, this.onDataChange)
        uni_navigateTo(NavigateToOptions(url = "./edit?id=" + id))
    }
    open var onDataChange = ::gen_onDataChange_fn
    open fun gen_onDataChange_fn(_0: String) {
        this.uniCloudElement!!.loadData()
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "loading" to padStyleMapOf(utsMapOf("alignItems" to "center")), "form-item" to padStyleMapOf(utsMapOf("flexDirection" to "row", "marginBottom" to 15, "alignItems" to "center", "paddingTop" to 8, "paddingRight" to 0, "paddingBottom" to 8, "paddingLeft" to 0)), "form-item-label" to padStyleMapOf(utsMapOf("width" to 45, "marginRight" to 10)), "form-item-input" to padStyleMapOf(utsMapOf("flex" to 1, "fontSize" to 14, "color" to "#666666")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
