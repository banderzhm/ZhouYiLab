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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIAnimateAnimate : BasePage {
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
            this.mainView = uni_getElementById("main")
            var roll = uni_getElementById("roll")
            roll!!.animate(utsArrayOf(
                UniAnimationKeyframe(transform = "translateX(0) rotate(0)"),
                UniAnimationKeyframe(transform = "translateX(200px) rotate(540deg)")
            ), UniAnimationOption(duration = 2000, direction = "alternate", easing = "ease-in-out", iterations = Infinity))
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("view", utsMapOf("id" to "main", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown", "transform" to "scale(1)"))), null, 4),
            createElementVNode("button", utsMapOf("onClick" to _ctx.startAnimate), "开始动画", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.pauseAnimate), "暂停动画", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.resumeAnimate), "恢复动画", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("button", utsMapOf("onClick" to _ctx.cancelAnimate), "取消动画", 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("image", utsMapOf("src" to "/static/uni.png", "id" to "roll", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "margin" to "10px"))), null, 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改宽度", 4),
                createElementVNode("view", utsMapOf("id" to "widthProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.widthProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改高度", 4),
                createElementVNode("view", utsMapOf("id" to "height1", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.heightProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改margin", 4),
                createElementVNode("view", utsMapOf("id" to "marginProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.marginProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改padding", 4),
                createElementVNode("view", utsMapOf("id" to "paddingProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.paddingProperty), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "50px", "background-color" to "black"))), null, 4)
                ), 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改border颜色", 4),
                createElementVNode("view", utsMapOf("id" to "borderProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown", "border-width" to "10px", "border-color" to "black", "border-style" to "solid")), "onClick" to _ctx.borderProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改transform", 4),
                createElementVNode("view", utsMapOf("id" to "transformProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.transformProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改position", 4),
                createElementVNode("view", utsMapOf("id" to "positionProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.positionProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改背景色和宽度", 4),
                createElementVNode("view", utsMapOf("id" to "backgroundAndWidthProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.backgroundAndWidthProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "执行的动画只有一个值1", 4),
                createElementVNode("view", utsMapOf("id" to "oneProperty1", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.oneProperty1), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "执行的动画只有一个值2", 4),
                createElementVNode("view", utsMapOf("id" to "oneProperty2", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.oneProperty2), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改背景色和margin-left(关键帧)", 4),
                createElementVNode("view", utsMapOf("id" to "backgroundAndMarginLeftProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.backgroundAndMarginLeftProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改背景色和transform(关键帧)", 4),
                createElementVNode("view", utsMapOf("id" to "backgroundAndTransformProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.backgroundAndTransformProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改背景色(关键帧)", 4),
                createElementVNode("view", utsMapOf("id" to "backgroundProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.backgroundProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改opacity(关键帧)", 4),
                createElementVNode("view", utsMapOf("id" to "opacityProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown")), "onClick" to _ctx.opacityProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("border-radius" to "5px", "margin" to "4px", "padding" to "4px", "border-style" to "solid", "background-color" to "#eee", "border-color" to "#eee"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("margin-bottom" to "4px"))), "修改border-color和margin-left(关键帧)", 4),
                createElementVNode("view", utsMapOf("id" to "borderColorMarginLeftProperty", "style" to normalizeStyle(utsMapOf("width" to "100px", "height" to "100px", "background-color" to "brown", "border-width" to "5px", "border-style" to "solid")), "onClick" to _ctx.borderColorMarginLeftProperty), null, 12, utsArrayOf(
                    "onClick"
                ))
            ), 4)
        ), 4)
    }
    open var mainView: UniElement? by `$data`
    open var animation: UniAnimation? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("mainView" to null as UniElement?, "animation" to null as UniAnimation?)
    }
    open var startAnimate = ::gen_startAnimate_fn
    open fun gen_startAnimate_fn() {
        this.animation = this.mainView!!.animate(utsArrayOf(
            UniAnimationKeyframe(transform = "scale(1)", transformOrigin = "0px 0px"),
            UniAnimationKeyframe(transform = "scale(0)", transformOrigin = "50px 50px"),
            UniAnimationKeyframe(transform = "scale(1)", transformOrigin = "100px 100px")
        ), UniAnimationOption(duration = 5000))
        this.animation!!.oncancel = fun(_: UniAnimationPlaybackEvent){
            uni_showToast(ShowToastOptions(title = "动画被取消了"))
        }
        this.animation!!.onfinish = fun(_: UniAnimationPlaybackEvent){
            uni_showToast(ShowToastOptions(title = "动画播放完成"))
        }
    }
    open var pauseAnimate = ::gen_pauseAnimate_fn
    open fun gen_pauseAnimate_fn() {
        this.animation?.pause()
    }
    open var resumeAnimate = ::gen_resumeAnimate_fn
    open fun gen_resumeAnimate_fn() {
        this.animation?.play()
    }
    open var cancelAnimate = ::gen_cancelAnimate_fn
    open fun gen_cancelAnimate_fn() {
        this.animation?.cancel()
    }
    open var widthProperty = ::gen_widthProperty_fn
    open fun gen_widthProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(width = utsArrayOf(
            "100px",
            "200px",
            "100px"
        )), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var heightProperty = ::gen_heightProperty_fn
    open fun gen_heightProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(height = utsArrayOf(
            "100px",
            "200px"
        )), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var marginProperty = ::gen_marginProperty_fn
    open fun gen_marginProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(margin = utsArrayOf(
            "8px",
            "16px",
            "32px"
        )), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var paddingProperty = ::gen_paddingProperty_fn
    open fun gen_paddingProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(padding = utsArrayOf(
            "0px",
            "16px",
            "32px",
            "0px"
        )), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var backgroundProperty = ::gen_backgroundProperty_fn
    open fun gen_backgroundProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(offset = 0.3, backgroundColor = "yellow"),
            UniAnimationKeyframe(offset = 0.6, backgroundColor = "red"),
            UniAnimationKeyframe(backgroundColor = "blue")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var borderProperty = ::gen_borderProperty_fn
    open fun gen_borderProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(offset = 0.3, borderColor = "yellow"),
            UniAnimationKeyframe(offset = 0.6, borderColor = "pink"),
            UniAnimationKeyframe(borderColor = "blue")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var transformProperty = ::gen_transformProperty_fn
    open fun gen_transformProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(transform = "translateX(0px) scale(1) rotate(0deg)"),
            UniAnimationKeyframe(transform = "translateX(100px)"),
            UniAnimationKeyframe(transform = "scale(0.8) rotate(180deg)")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var positionProperty = ::gen_positionProperty_fn
    open fun gen_positionProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(left = utsArrayOf(
            "0px",
            "16px",
            "32px",
            "0px"
        )), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var backgroundAndWidthProperty = ::gen_backgroundAndWidthProperty_fn
    open fun gen_backgroundAndWidthProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(width = utsArrayOf(
            "100px",
            "200px"
        ), backgroundColor = utsArrayOf(
            "red",
            "yellow",
            "blue"
        )), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var backgroundAndMarginLeftProperty = ::gen_backgroundAndMarginLeftProperty_fn
    open fun gen_backgroundAndMarginLeftProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(offset = 0.2, backgroundColor = "red"),
            UniAnimationKeyframe(marginLeft = "10px"),
            UniAnimationKeyframe(marginLeft = "20px"),
            UniAnimationKeyframe(marginLeft = "30px", backgroundColor = "pink")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var backgroundAndTransformProperty = ::gen_backgroundAndTransformProperty_fn
    open fun gen_backgroundAndTransformProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(offset = 0.2, backgroundColor = "red"),
            UniAnimationKeyframe(transform = "translate(30px,0px)"),
            UniAnimationKeyframe(transform = "translate(50px,0px)"),
            UniAnimationKeyframe(transform = "translate(100px,0px)", backgroundColor = "pink")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var oneProperty1 = ::gen_oneProperty1_fn
    open fun gen_oneProperty1_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(UniAnimationKeyframe(backgroundColor = "green"), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var oneProperty2 = ::gen_oneProperty2_fn
    open fun gen_oneProperty2_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(backgroundColor = "blue")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var borderColorMarginLeftProperty = ::gen_borderColorMarginLeftProperty_fn
    open fun gen_borderColorMarginLeftProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(borderColor = "red", marginLeft = "0px", offset = 0),
            UniAnimationKeyframe(marginLeft = "20px", offset = 0.5),
            UniAnimationKeyframe(marginLeft = "60px", borderColor = "yellow", offset = 1)
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
    }
    open var opacityProperty = ::gen_opacityProperty_fn
    open fun gen_opacityProperty_fn(e: UniPointerEvent) {
        e.currentTarget?.animate(utsArrayOf(
            UniAnimationKeyframe(offset = 0.3, opacity = "1"),
            UniAnimationKeyframe(offset = 0.6, opacity = "0.1"),
            UniAnimationKeyframe(opacity = "1")
        ), UniAnimationOption(duration = 1000, fill = "forwards"))
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
                return utsMapOf("view-margin" to padStyleMapOf(utsMapOf("marginTop" to 8, "marginRight" to 8, "marginBottom" to 8, "marginLeft" to 8)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
