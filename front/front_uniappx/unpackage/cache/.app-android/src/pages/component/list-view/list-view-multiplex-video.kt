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
open class GenPagesComponentListViewListViewMultiplexVideo : BasePage {
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
        return createElementVNode("list-view", utsMapOf("class" to "list", "onScrolltolower" to _ctx.onScrollTolower), utsArrayOf(
            createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.item_count, fun(_, index, __index, _cached): Any {
                return createElementVNode("list-item", utsMapOf("class" to "list-item"), utsArrayOf(
                    createElementVNode("text", null, "第" + toDisplayString(index + 1) + "个视频", 1),
                    createElementVNode("video", utsMapOf("class" to "video", "src" to _ctx.src, "controls" to true, "onPause" to _ctx.onPause), null, 40, utsArrayOf(
                        "src",
                        "onPause"
                    ))
                ))
            }
            ), 256)
        ), 40, utsArrayOf(
            "onScrolltolower"
        ))
    }
    open var item_count: Number by `$data`
    open var src: String by `$data`
    open var isTesting: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("item_count" to 10, "src" to "https://qiniu-web-assets.dcloud.net.cn/video/sample/2minute-demo.mp4", "isTesting" to false)
    }
    open var onPause = ::gen_onPause_fn
    open fun gen_onPause_fn(e: UniEvent) {
        console.log(JSON.stringify(e), " at pages/component/list-view/list-view-multiplex-video.uvue:27")
    }
    open var onScrollTolower = ::gen_onScrollTolower_fn
    open fun gen_onScrollTolower_fn(_0: ScrollToLowerEvent) {
        setTimeout(fun(){
            if (this.isTesting && this.item_count >= 10) {
                return
            }
            this.item_count += 10
        }
        , 300)
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
                return utsMapOf("list" to padStyleMapOf(utsMapOf("flex" to 1)), "list-item" to padStyleMapOf(utsMapOf("backgroundColor" to "#FFFFFF", "marginBottom" to 30)), "video" to padStyleMapOf(utsMapOf("width" to "100%", "height" to 200)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
