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
import io.dcloud.uniapp.extapi.createVideoContext as uni_createVideoContext
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
import io.dcloud.uniapp.extapi.navigateBack as uni_navigateBack
open class GenPagesTemplateSwiperVerticalVideoSwiperVerticalVideo : BasePage {
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
        onCreated(fun() {
            this.list = this.getData()
            this.visibleList = this.list.slice(0, 3)
        }
        , __ins)
        onReady(fun() {
            this.doPlay(0)
            var backElement = this.`$refs`["back"] as UniElement
            backElement.style.setProperty("top", uni_getWindowInfo().statusBarHeight + "px")
        }
        , __ins)
        onPageShow(fun() {
            currentPageIsShow = true
        }
        , __ins)
        onPageHide(fun() {
            currentPageIsShow = false
            console.log("pages-onHide", " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:113")
            this.doPause(this.current)
        }
        , __ins)
        onUnload(fun() {
            this.doPause(this.current)
        }
        , __ins)
        this.`$watch`(fun(): Any? {
            return this.current
        }
        , fun(current: Number, oldCurrent: Number) {
            var changeNumber = current - oldCurrent
            if (changeNumber == 1 || changeNumber == -2) {
                this.index++
            } else {
                this.index--
            }
            if (Math.abs(changeNumber) == 2) {
                page = Math.floor(this.index / 3)
                if (this.list.length < 3 * page + 3) {
                    var list: UTSArray<ListItem1> = this.getData()
                    this.list.push(*list.toTypedArray())
                }
                var visibleList = this.list.slice(3 * page, 3 * page + 3)
                this.visibleList = visibleList
            }
            this.state.forEach(fun(_: String, index: Number){
                if (index === current) {
                    this.doPlay(current)
                } else {
                    this.doStop(index)
                    console.log("index:" + index + "已被执行停止", " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:94")
                }
            }
            )
        }
        )
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("view", utsMapOf("class" to "page"), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "back", "onClick" to _ctx.back, "class" to "nav-back"), utsArrayOf(
                createElementVNode("image", utsMapOf("class" to "back-img", "src" to "/static/template/scroll-fold-nav/back.png", "mode" to "widthFix"))
            ), 8, utsArrayOf(
                "onClick"
            )),
            createElementVNode("swiper", utsMapOf("class" to "swiper", "current" to _ctx.current, "circular" to (_ctx.index != 0), "vertical" to true, "onChange" to _ctx.onSwiperChange, "onTransition" to _ctx.onTransition), utsArrayOf(
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.visibleList, fun(item, i, __index, _cached): Any {
                    return createElementVNode("swiper-item", utsMapOf("class" to "swiper-item", "key" to i), utsArrayOf(
                        createElementVNode("video", utsMapOf("onClick" to fun(){
                            _ctx.changeState(i)
                        }
                        , "ref_for" to true, "ref" to "video", "class" to "video-box", "id" to ("video-" + i), "onLoadstart" to fun(){
                            _ctx.onLoadstart(i)
                        }
                        , "src" to item.src, "poster" to item.poster_src, "autoplay" to false, "show-progress" to false, "show-fullscreen-btn" to false, "show-play-btn" to false, "show-center-play-btn" to false, "loop" to true, "onPlay" to fun(){
                            _ctx.onPlay(i)
                        }
                        , "onPause" to fun(){
                            _ctx.onPause(i)
                        }
                        , "http-cache" to "true"), null, 40, utsArrayOf(
                            "onClick",
                            "id",
                            "onLoadstart",
                            "src",
                            "poster",
                            "onPlay",
                            "onPause"
                        )),
                        createElementVNode("view", utsMapOf("class" to "video-cover", "onClick" to fun(){
                            _ctx.changeState(i)
                        }
                        ), utsArrayOf(
                            if (_ctx.state[i] === "pause") {
                                createElementVNode("image", utsMapOf("key" to 0, "class" to "play-btn", "src" to "/static/template/swiper-vertical-video/play.png", "mode" to "widthFix"))
                            } else {
                                createCommentVNode("v-if", true)
                            }
                        ), 8, utsArrayOf(
                            "onClick"
                        )),
                        if (isTrue(_ctx.showDebugInfo)) {
                            createElementVNode("view", utsMapOf("key" to 0, "class" to "video-info"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "video-info-text"), "容器：第 " + toDisplayString(i) + " 个", 1),
                                createElementVNode("text", utsMapOf("class" to "video-info-text"), "内容：" + toDisplayString(item.content), 1)
                            ))
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    ))
                }
                ), 128)
            ), 40, utsArrayOf(
                "current",
                "circular",
                "onChange",
                "onTransition"
            )),
            if (isTrue(_ctx.showDebugInfo)) {
                createElementVNode("view", utsMapOf("key" to 0, "class" to "debug-info"), utsArrayOf(
                    createElementVNode("text", utsMapOf("class" to "status-text"), "debug-info 播放状态:"),
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.state, fun(value, index, __index, _cached): Any {
                        return createElementVNode("text", utsMapOf("class" to "status-text"), "第" + toDisplayString(index + 1) + "个:" + toDisplayString(value), 1)
                    }), 256)
                ))
            } else {
                createCommentVNode("v-if", true)
            }
        ))
    }
    open var `$videoContextMap`: Map<String, VideoContext> by `$data`
    open var list: UTSArray<ListItem1> by `$data`
    open var visibleList: UTSArray<ListItem1> by `$data`
    open var current: Number by `$data`
    open var index: Number by `$data`
    open var state: UTSArray<String> by `$data`
    open var showDebugInfo: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("\$videoContextMap" to Map<String, VideoContext>(), "list" to utsArrayOf<ListItem1>(), "visibleList" to utsArrayOf<ListItem1>(), "current" to 0 as Number, "index" to 0, "state" to utsArrayOf<String>("pause", "unPlay", "unPlay"), "showDebugInfo" to false as Boolean)
    }
    open var changeState = ::gen_changeState_fn
    open fun gen_changeState_fn(index: Number) {
        if (this.state[index] === "play") {
            this.doPause(index)
        } else {
            this.doPlay(this.current)
        }
    }
    open var onLoadstart = ::gen_onLoadstart_fn
    open fun gen_onLoadstart_fn(index: Number) {
        console.error("onLoadstart  video" + index, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:128")
    }
    open var getVideoContext = ::gen_getVideoContext_fn
    open fun gen_getVideoContext_fn(index: Number): VideoContext {
        val videoContextMap = this.`$data`["\$videoContextMap"] as Map<String, VideoContext>
        var videoContext: VideoContext? = videoContextMap.get("video-" + index)
        if (videoContext == null) {
            videoContext = uni_createVideoContext("video-" + index, this) as VideoContext
            videoContextMap.set("video-" + index, videoContext)
        }
        return videoContext
    }
    open var doPlay = ::gen_doPlay_fn
    open fun gen_doPlay_fn(index: Number) {
        console.log("doPlay  video" + index, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:140")
        this.getVideoContext(index).play()
    }
    open var doStop = ::gen_doStop_fn
    open fun gen_doStop_fn(index: Number) {
        console.log("doStop  video-" + index, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:144")
        this.getVideoContext(index).stop()
        setTimeout(fun(){
            this.state[index] = "unPlay"
        }
        , 1000)
    }
    open var doPause = ::gen_doPause_fn
    open fun gen_doPause_fn(index: Number) {
        this.getVideoContext(index).pause()
        console.log("doPause  video-" + index, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:153")
    }
    open var onPause = ::gen_onPause_fn
    open fun gen_onPause_fn(index: Number) {
        this.state[index] = "pause"
        console.log("onPause", index, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:157")
    }
    open var onPlay = ::gen_onPlay_fn
    open fun gen_onPlay_fn(index: Number) {
        if (this.current != index || !currentPageIsShow) {
            this.onPause(index)
        } else {
            this.state[index] = "play"
            console.log("onPlay", index, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:164")
        }
    }
    open var getData = ::gen_getData_fn
    open fun gen_getData_fn(): UTSArray<ListItem1> {
        var videoUrlList = if (uni.UNI740BA18.state.netless) {
            utsArrayOf(
                "/static/test-video/10second-demo.mp4",
                "/static/test-video/10second-demo.mp4",
                "/static/test-video/10second-demo.mp4"
            )
        } else {
            utsArrayOf<String>("https://web-ext-storage.dcloud.net.cn/uni-app-x/video/uts-5-16.mp4", "https://web-ext-storage.dcloud.net.cn/uni-app-x/video/uni-ai-5-16.mp4", "https://web-ext-storage.dcloud.net.cn/uni-app-x/video/uni-verify-5-16.mp4")
        }
        var posterSrcList = if (uni.UNI740BA18.state.netless) {
            utsArrayOf(
                "/static/shuijiao.jpg",
                "/static/logo.jpg",
                "/static/shuijiao.jpg"
            )
        } else {
            utsArrayOf<String>("https://web-ext-storage.dcloud.net.cn/uni-app-x/dark-uni-uts-01.png", "https://web-ext-storage.dcloud.net.cn/uni-app-x/dark-uni-ai-01.png", "https://web-ext-storage.dcloud.net.cn/uni-app-x/dark-uni-verify-01.jpg")
        }
        var list = utsArrayOf<ListItem1>()
        run {
            var i: Number = 0
            while(i < 6){
                var index = this.list.length + i
                var listItem = ListItem1(_id = "a00" + index, content = "这是第" + index + "条数据，url地址" + videoUrlList[i % 3], src = videoUrlList[i % 3], poster_src = posterSrcList[i % 3])
                list.push(listItem)
                i++
            }
        }
        return list
    }
    open var onSwiperChange = ::gen_onSwiperChange_fn
    open fun gen_onSwiperChange_fn(e: SwiperChangeEvent) {
        this.current = e.detail.current
    }
    open var onTransition = ::gen_onTransition_fn
    open fun gen_onTransition_fn() {}
    open var back = ::gen_back_fn
    open fun gen_back_fn() {
        uni_navigateBack(NavigateBackOptions(success = fun(result) {
            console.log("navigateBack success", result.errMsg, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:216")
        }
        , fail = fun(error) {
            console.log("navigateBack fail", error.errMsg, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:219")
        }
        , complete = fun(result) {
            console.log("navigateBack complete", result.errMsg, " at pages/template/swiper-vertical-video/swiper-vertical-video.uvue:222")
        }
        ))
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1)), "swiper" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%")), "swiper-item" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "position" to "relative")), "video-box" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%")), "video-cover" to padStyleMapOf(utsMapOf("width" to "100%", "height" to "100%", "position" to "absolute", "justifyContent" to "center", "alignItems" to "center", "alignContent" to "center")), "play-btn" to padStyleMapOf(utsMapOf("width" to 40, "height" to 40)), "video-info" to padStyleMapOf(utsMapOf("position" to "absolute", "bottom" to 0, "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "video-info-text" to padStyleMapOf(utsMapOf("fontSize" to 14, "color" to "#FF0000", "lineHeight" to "20px")), "debug-info" to padStyleMapOf(utsMapOf("position" to "fixed", "top" to 15, "width" to "100%", "backgroundColor" to "rgba(255,255,255,0.3)")), "status-text" to padStyleMapOf(utsMapOf("color" to "#FF0000", "paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15)), "nav-back" to padStyleMapOf(utsMapOf("position" to "absolute", "top" to 35, "backgroundColor" to "rgba(220,220,220,0.8)", "borderTopLeftRadius" to 100, "borderTopRightRadius" to 100, "borderBottomRightRadius" to 100, "borderBottomLeftRadius" to 100, "marginTop" to 6, "marginRight" to 6, "marginBottom" to 6, "marginLeft" to 6, "width" to 32, "height" to 32, "justifyContent" to "center", "alignItems" to "center", "zIndex" to 10)), "back-img" to utsMapOf(".nav-back " to utsMapOf("width" to 18, "height" to 18)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
