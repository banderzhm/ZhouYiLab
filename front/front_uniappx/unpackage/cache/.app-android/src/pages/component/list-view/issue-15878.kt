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
open class GenPagesComponentListViewIssue15878 : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("list-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "refresher-enabled" to "true", "onRefresherrefresh" to _ctx.onRefresh, "refresher-triggered" to _ctx.refresherTriggered, "refresher-default-style" to "none"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(1000, fun(item, __key, __index, _cached): Any {
                    return createElementVNode("list-item", utsMapOf("class" to "item"), toDisplayString(item), 1)
                }
                ), 64),
                createElementVNode("list-item", utsMapOf("class" to "refresher", "slot" to "refresher"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "refresher-text"), "自定义下拉刷新")
                ))
            ), 44, utsArrayOf(
                "onRefresherrefresh",
                "refresher-triggered"
            ))
        ), 4)
    }
    open var refresherTriggered: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("refresherTriggered" to false)
    }
    open var onRefresh = ::gen_onRefresh_fn
    open fun gen_onRefresh_fn() {
        this.refresherTriggered = true
        setTimeout(fun(){
            this.refresherTriggered = false
        }
        , 5000)
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
                return utsMapOf("item" to padStyleMapOf(utsMapOf("height" to 50)), "refresher" to padStyleMapOf(utsMapOf("height" to 45)), "refresher-text" to padStyleMapOf(utsMapOf("lineHeight" to "45px", "textAlign" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
