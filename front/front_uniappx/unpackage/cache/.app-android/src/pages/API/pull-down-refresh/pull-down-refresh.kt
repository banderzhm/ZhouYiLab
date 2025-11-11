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
import io.dcloud.uniapp.extapi.startPullDownRefresh as uni_startPullDownRefresh
import io.dcloud.uniapp.extapi.stopPullDownRefresh as uni_stopPullDownRefresh
open class GenPagesAPIPullDownRefreshPullDownRefresh : BasePage {
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
            uni_startPullDownRefresh(null)
            this.initData()
        }
        , __ins)
        onReachBottom(fun() {
            console.log("onReachBottom", " at pages/API/pull-down-refresh/pull-down-refresh.uvue:30")
            if (this.max > 40) {
                this.loadMoreText = "没有更多数据了!"
                return
            }
            this.showLoadMore = true
            setTimeout(fun(){
                this.setListData()
            }
            , 300)
        }
        , __ins)
        onPullDownRefresh(fun() {
            console.log("onPullDownRefresh", " at pages/API/pull-down-refresh/pull-down-refresh.uvue:41")
            this.pulldownRefreshTriggered = true
            this.initData()
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.data, fun(num, index, __index, _cached): Any {
                    return createElementVNode("text", utsMapOf("class" to "text", "key" to index), "list - " + toDisplayString(num), 1)
                }
                ), 128),
                if (isTrue(_ctx.showLoadMore)) {
                    createElementVNode("view", utsMapOf("key" to 0), toDisplayString(_ctx.loadMoreText), 1)
                } else {
                    createCommentVNode("v-if", true)
                }
            ))
        ), 4)
    }
    open var data: UTSArray<Number> by `$data`
    open var loadMoreText: String by `$data`
    open var showLoadMore: Boolean by `$data`
    open var max: Number by `$data`
    open var pulldownRefreshTriggered: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("data" to utsArrayOf<Number>(), "loadMoreText" to "加载中...", "showLoadMore" to false, "max" to 0, "pulldownRefreshTriggered" to false)
    }
    open var initData = ::gen_initData_fn
    open fun gen_initData_fn() {
        setTimeout(fun(){
            this.max = 0
            this.data = utsArrayOf()
            var data: UTSArray<Number> = utsArrayOf()
            this.max += 20
            run {
                var i: Number = this.max - 19
                while(i < this.max + 1){
                    data.push(i)
                    i++
                }
            }
            this.data = this.data.concat(data)
            uni_stopPullDownRefresh()
        }
        , 1000)
    }
    open var setListData = ::gen_setListData_fn
    open fun gen_setListData_fn() {
        var data: UTSArray<Number> = utsArrayOf()
        this.max += 10
        run {
            var i: Number = this.max - 9
            while(i < this.max + 1){
                data.push(i)
                i++
            }
        }
        this.data = this.data.concat(data)
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
                return utsMapOf("text" to padStyleMapOf(utsMapOf("marginTop" to 6, "marginRight" to 0, "marginBottom" to 6, "marginLeft" to 0, "width" to "100%", "backgroundColor" to "#ffffff", "height" to 52, "lineHeight" to "52px", "textAlign" to "center", "color" to "#555555", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
