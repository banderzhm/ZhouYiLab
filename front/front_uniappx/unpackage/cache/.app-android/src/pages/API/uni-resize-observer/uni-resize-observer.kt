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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
open class GenPagesAPIUniResizeObserverUniResizeObserver : BasePage {
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
        onBackPress(fun(_: OnBackPressOptions): Boolean {
            if (this.resizeObserver != null) {
                this.resizeObserver!!.disconnect()
            }
            return false
        }
        , __ins)
        onReady(fun() {
            if (this.resizeObserver == null) {
                this.resizeObserver = UniResizeObserver(fun(entries: UTSArray<UniResizeObserverEntry>){
                    entries.forEach(fun(entry){
                        if (entry.target == this.outBoxElement) {
                            this.outBoxSizeInfo = this.analysisResizeObserverEntry(entry)
                            this.outBoxElementOnResize = true
                        } else if (entry.target == this.innerBoxElement) {
                            this.innerBoxSizeInfo = this.analysisResizeObserverEntry(entry)
                        }
                    }
                    )
                }
                )
                this.outBoxElement = uni_getElementById("outBox")
                if (this.outBoxElement != null) {
                    this.resizeObserver!!.observe(this.outBoxElement!!)
                }
                this.innerBoxElement = uni_getElementById("innerBox")
                if (this.innerBoxElement != null) {
                    this.resizeObserver!!.observe(this.innerBoxElement!!)
                }
                this.boxDisplay = true
            }
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", null, utsArrayOf(
            createElementVNode("text", null, "点击蓝色或红色方块区域会修改元素宽高"),
            withDirectives(createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("align-items" to "center", "justify-content" to "center", "margin" to "10px"))), utsArrayOf(
                createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "140px", "height" to "140px", "background-color" to "blue", "align-items" to "center", "justify-content" to "center", "padding" to "5px")), "id" to "outBox", "onClick" to _ctx.outBoxClick), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "80px", "height" to "80px", "background-color" to "red", "padding" to "5px")), "id" to "innerBox", "onClick" to _ctx.innerBoxClick), null, 12, utsArrayOf(
                        "onClick"
                    ))
                ), 12, utsArrayOf(
                    "onClick"
                ))
            ), 4), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.boxDisplay
                )
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.revertBoxSize), "还原修改前元素宽高", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.toggleDisplay), toDisplayString(if (_ctx.boxDisplay) {
                "隐藏元素"
            } else {
                "显示元素"
            }
            ), 9, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.cancelListen), "停止监听", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.goOnListen), "恢复监听", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("view", null, utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "info-text"), "蓝色方块元素:"),
                createElementVNode("view", utsMapOf("class" to "info-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "info-text"), toDisplayString(_ctx.outBoxSizeInfo), 1)
                )),
                createElementVNode("text", utsMapOf("class" to "info-text", "style" to normalizeStyle(utsMapOf("margin-top" to "20px"))), "红色方块元素:", 4),
                createElementVNode("view", utsMapOf("class" to "info-item"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "info-text"), toDisplayString(_ctx.innerBoxSizeInfo), 1)
                ))
            ))
        ))
    }
    open var outBoxSizeInfo: String by `$data`
    open var innerBoxSizeInfo: String by `$data`
    open var offset: Number by `$data`
    open var boxDisplay: Boolean by `$data`
    open var outBoxElement: UniElement? by `$data`
    open var innerBoxElement: UniElement? by `$data`
    open var resizeObserver: UniResizeObserver? by `$data`
    open var outBoxElementOnResize: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("outBoxSizeInfo" to "", "innerBoxSizeInfo" to "", "offset" to 2, "boxDisplay" to false, "outBoxElement" to null as UniElement?, "innerBoxElement" to null as UniElement?, "resizeObserver" to null as UniResizeObserver?, "outBoxElementOnResize" to false)
    }
    open var innerBoxClick = ::gen_innerBoxClick_fn
    open fun gen_innerBoxClick_fn() {
        if (this.innerBoxElement != null) {
            this.innerBoxElement!!.style.setProperty("width", this.innerBoxElement!!.offsetWidth + this.offset + "px")
            this.innerBoxElement!!.style.setProperty("height", this.innerBoxElement!!.offsetWidth + this.offset + "px")
        }
    }
    open var outBoxClick = ::gen_outBoxClick_fn
    open fun gen_outBoxClick_fn() {
        if (this.outBoxElement != null) {
            this.outBoxElement!!.style.setProperty("width", this.outBoxElement!!.offsetWidth + this.offset + "px")
            this.outBoxElement!!.style.setProperty("height", this.outBoxElement!!.offsetWidth + this.offset + "px")
        }
    }
    open var revertBoxSize = ::gen_revertBoxSize_fn
    open fun gen_revertBoxSize_fn() {
        if (this.outBoxElement != null) {
            this.outBoxElement!!.style.setProperty("width", "140px")
            this.outBoxElement!!.style.setProperty("height", "140px")
        }
        if (this.innerBoxElement != null) {
            this.innerBoxElement!!.style.setProperty("width", "80px")
            this.innerBoxElement!!.style.setProperty("height", "80px")
        }
    }
    open var setOutBoxMarginLeft = ::gen_setOutBoxMarginLeft_fn
    open fun gen_setOutBoxMarginLeft_fn(value: String) {
        if (this.outBoxElement != null) {
            this.outBoxElementOnResize = false
            this.outBoxElement!!.style.setProperty("margin-left", value)
        }
    }
    open var toggleDisplay = ::gen_toggleDisplay_fn
    open fun gen_toggleDisplay_fn() {
        this.boxDisplay = !this.boxDisplay
    }
    open var analysisResizeObserverEntry = ::gen_analysisResizeObserverEntry_fn
    open fun gen_analysisResizeObserverEntry_fn(entry: UniResizeObserverEntry): String {
        val contentBoxSize = entry.contentBoxSize[0]
        val borderBoxSize = entry.borderBoxSize[0]
        val devicePixelContentBoxSize = entry.devicePixelContentBoxSize[0]
        return "borderBoxSize: \n{blockSize:" + borderBoxSize.blockSize + ", inlineSize:" + borderBoxSize.inlineSize + "}\n" + "contentBoxSize: \n{blockSize:" + contentBoxSize.blockSize + ", inlineSize:" + contentBoxSize.inlineSize + "}\n" + "devicePixelContentBoxSize: \n{blockSize:" + devicePixelContentBoxSize.blockSize + ", inlineSize:" + devicePixelContentBoxSize.inlineSize + "}\n" + "contentRect: \n{x:" + entry.contentRect.x + ", y:" + entry.contentRect.y + ", width:" + entry.contentRect.width + ", height:" + entry.contentRect.height + "}"
    }
    open var cancelListen = ::gen_cancelListen_fn
    open fun gen_cancelListen_fn() {
        this.resizeObserver!!.unobserve(this.outBoxElement!!)
        this.resizeObserver!!.unobserve(this.innerBoxElement!!)
    }
    open var goOnListen = ::gen_goOnListen_fn
    open fun gen_goOnListen_fn() {
        this.resizeObserver!!.observe(this.outBoxElement!!)
        this.resizeObserver!!.observe(this.innerBoxElement!!)
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
                return utsMapOf("info-item" to padStyleMapOf(utsMapOf("flexDirection" to "row")), "info-text" to padStyleMapOf(utsMapOf("fontSize" to 14)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
