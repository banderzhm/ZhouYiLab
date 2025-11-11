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
import io.dcloud.uniapp.extapi.getProviderSync as uni_getProviderSync
open class GenPagesAPIProviderProvider : BasePage {
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
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.serviceList, fun(item, index, __index, _cached): Any {
                    return createElementVNode("view", utsMapOf("class" to "service-item", "key" to index), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "service-name"), toDisplayString(item.name) + ":", 1),
                        createElementVNode("view", utsMapOf("class" to "provider-list"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(item.provider, fun(item2, index2, __index, _cached): Any {
                                return createElementVNode("text", utsMapOf("class" to "provider-item", "key" to index2), toDisplayString(item2) + " " + toDisplayString(if (item.providerObj.length > 0) {
                                    ":" + JSON.stringify(item.providerObj[index2])
                                } else {
                                    ""
                                }
                                ), 1)
                            }
                            ), 128)
                        ))
                    ))
                }
                ), 128),
                createElementVNode("button", utsMapOf("class" to "btn-get-provider", "type" to "primary", "onClick" to _ctx.getProvider), "getProviderSync", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var flag: Boolean by `$data`
    open var serviceList: UTSArray<ProviderItem> by `$data`
    open var providerIds: UTSArray<String> by `$data`
    open var providerObjects: UTSArray<String> by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "provider", "flag" to false, "serviceList" to utsArrayOf<ProviderItem>(ProviderItem(service = "payment", name = "支付", provider = utsArrayOf(), providerObj = utsArrayOf()), ProviderItem(service = "location", name = "定位", provider = utsArrayOf(), providerObj = utsArrayOf())), "providerIds" to utsArrayOf<String>(), "providerObjects" to utsArrayOf<String>())
    }
    open var getProvider = ::gen_getProvider_fn
    open fun gen_getProvider_fn() {
        this.providerObjects = utsArrayOf()
        this.serviceList.forEach(fun(item: ProviderItem){
            var provider = uni_getProviderSync(GetProviderSyncOptions(service = item.service))
            provider.providerIds.forEach(fun(value){
                this.providerIds.push(value)
            }
            )
            this.updateProvider(item.service, provider.providerIds, provider.providerObjects)
        }
        )
    }
    open var updateProvider = ::gen_updateProvider_fn
    open fun gen_updateProvider_fn(service: String, provider: UTSArray<String>?, uniProvider: UTSArray<UniProvider>) {
        val item: ProviderItem? = this.serviceList.find(fun(item: ProviderItem): Boolean {
            return item.service == service
        }
        )
        if (item != null && provider != null) {
            item.provider = provider
            item.providerObj = uniProvider
            item.providerObj.forEach(fun(obj){
                this.providerObjects.push(obj.description)
            }
            )
        }
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "service-item" to padStyleMapOf(utsMapOf("marginTop" to 10)), "service-name" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "provider-list" to padStyleMapOf(utsMapOf("marginLeft" to 32)), "provider-item" to padStyleMapOf(utsMapOf("lineHeight" to 1.5)), "btn-get-provider" to padStyleMapOf(utsMapOf("marginTop" to 30)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
