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
import io.dcloud.uniapp.extapi.`$emit` as uni__emit
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesComponentUnicloudDbUnicloudDbContactsAdd : BasePage {
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
            this.uniCloudElement = this.`$refs`["udb"] as UniCloudDBElement
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_unicloud_db = resolveEasyComponent("unicloud-db", GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDbClass)
        val _component_radio = resolveComponent("radio")
        val _component_radio_group = resolveComponent("radio-group")
        val _component_form = resolveComponent("form")
        return createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
            createVNode(_component_unicloud_db, utsMapOf("ref" to "udb", "collection" to _ctx.collection, "loadtime" to "manual"), null, 8, utsArrayOf(
                "collection"
            )),
            createVNode(_component_form, utsMapOf("onSubmit" to _ctx.onFormSubmit), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                return utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "form-item-label"), "姓名"),
                        createElementVNode("input", utsMapOf("class" to "form-item-input", "placeholder" to "姓名", "name" to "username"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "form-item-label"), "电话"),
                        createElementVNode("input", utsMapOf("class" to "form-item-input", "placeholder" to "电话", "name" to "mobile"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "form-item-label"), "邮箱"),
                        createElementVNode("input", utsMapOf("class" to "form-item-input", "placeholder" to "邮箱地址", "name" to "email"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "form-item-label"), "备注"),
                        createElementVNode("textarea", utsMapOf("class" to "form-item-input-comment", "placeholder" to "备注", "name" to "comment", "maxlength" to "-1"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "form-item"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "form-item-label"), "性别"),
                        createVNode(_component_radio_group, utsMapOf("class" to "radio-list", "name" to "gender"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                            return utsArrayOf(
                                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.genderList, fun(item, _, __index, _cached): Any {
                                    return createElementVNode("view", utsMapOf("class" to "radio-item", "key" to item.value), utsArrayOf(
                                        createVNode(_component_radio, utsMapOf("value" to item.value), null, 8, utsArrayOf(
                                            "value"
                                        )),
                                        createElementVNode("text", null, toDisplayString(item.text), 1)
                                    ))
                                }
                                ), 128)
                            )
                        }
                        ), "_" to 1))
                    )),
                    createElementVNode("button", utsMapOf("class" to "btn-add", "type" to "primary", "form-type" to "submit"), "保存")
                )
            }
            ), "_" to 1), 8, utsArrayOf(
                "onSubmit"
            ))
        ))
    }
    open var collection: Any? by `$data`
    open var genderList: UTSArray<GenderType> by `$data`
    open var uniCloudElement: UniCloudDBElement? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("collection" to COLLECTION_NAME, "genderList" to GenderList as UTSArray<GenderType>, "uniCloudElement" to null as UniCloudDBElement?)
    }
    open var onFormSubmit = ::gen_onFormSubmit_fn
    open fun gen_onFormSubmit_fn(e: UniFormSubmitEvent) {
        val formData = e.detail.value
        val genderString = formData["gender"] as String
        formData["gender"] = if ((genderString.length > 0)) {
            parseInt(genderString)
        } else {
            -1
        }
        console.log("formData", formData, " at pages/component/unicloud-db/unicloud-db/contacts/add.uvue:54")
        this.uniCloudElement!!.add(formData, object : UTSJSONObject() {
            var showToast = false
            var needLoading = true
            var loadingTitle = "正在保存..."
            var success = fun(_: UniCloudDBAddResult){
                uni__emit(UNICLOUD_DB_CONTACTS_ADD, "")
                setTimeout(fun(){
                    uni_navigateBack(null)
                }
                , 500)
            }
            var fail = fun(err: Any?){
                val error = err as UniCloudError
                uni_showModal(ShowModalOptions(content = error.errMsg, showCancel = false))
            }
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "form-item" to padStyleMapOf(utsMapOf("flexDirection" to "row", "marginBottom" to 15, "alignItems" to "center")), "form-item-label" to padStyleMapOf(utsMapOf("width" to 45, "marginRight" to 10)), "form-item-input" to padStyleMapOf(utsMapOf("flex" to 1, "fontSize" to 14, "color" to "#666666", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#e5e5e5", "borderRightColor" to "#e5e5e5", "borderBottomColor" to "#e5e5e5", "borderLeftColor" to "#e5e5e5", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "height" to 34, "paddingLeft" to 8, "paddingRight" to 8)), "form-item-input-comment" to padStyleMapOf(utsMapOf("flex" to 1, "fontSize" to 14, "color" to "#666666", "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#e5e5e5", "borderRightColor" to "#e5e5e5", "borderBottomColor" to "#e5e5e5", "borderLeftColor" to "#e5e5e5", "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "paddingTop" to 8, "paddingRight" to 8, "paddingBottom" to 8, "paddingLeft" to 8)), "radio-list" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "radio-item" to padStyleMapOf(utsMapOf("flexDirection" to "row", "marginRight" to 30, "alignItems" to "center")), "btn-add" to padStyleMapOf(utsMapOf("marginTop" to 50)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
