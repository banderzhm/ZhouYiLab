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
open class GenPagesComponentGlobalEventsGlobalEvents : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "触摸方块测试相关事件")),
            createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt container"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "target", "id" to "touch-target", "onTouchstart" to _ctx.onTouchStart, "onTouchcancel" to _ctx.onTouchCancel, "onTouchmove" to _ctx.onTouchMove, "onTouchend" to _ctx.onTouchEnd), null, 40, utsArrayOf(
                    "onTouchstart",
                    "onTouchcancel",
                    "onTouchmove",
                    "onTouchend"
                )),
                createElementVNode("view", utsMapOf("class" to "target", "id" to "longpress-target", "onClick" to utsArrayOf(
                    _ctx.onTap,
                    _ctx.onClick
                ), "onLongpress" to _ctx.onLongPress), null, 40, utsArrayOf(
                    "onClick",
                    "onLongpress"
                )),
                if (_ctx.touchStartEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 0), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "touchStart Event: "),
                        createElementVNode("text", utsMapOf("class" to "title2"), "touches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchStartEvent!!.touches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128),
                        createElementVNode("text", utsMapOf("class" to "title2 uni-common-mt"), "changedTouches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchStartEvent!!.changedTouches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-start-changed-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.touchCancelEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 1), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "touchCancel Event: "),
                        createElementVNode("text", utsMapOf("class" to "title2"), "touches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchCancelEvent!!.touches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128),
                        createElementVNode("text", utsMapOf("class" to "title2 uni-common-mt"), "changedTouches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchCancelEvent!!.changedTouches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-cancel-changed-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.touchMoveEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 2), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "touchMove Event: "),
                        createElementVNode("text", utsMapOf("class" to "title2"), "touches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchMoveEvent!!.touches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128),
                        createElementVNode("text", utsMapOf("class" to "title2 uni-common-mt"), "changedTouches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchMoveEvent!!.changedTouches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-move-changed-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.touchEndEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 3), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "touchEnd Event: "),
                        createElementVNode("text", utsMapOf("class" to "title2"), "touches: "),
                        if (_ctx.touchEndEvent!!.touches.length > 0) {
                            createElementVNode(Fragment, utsMapOf("key" to 0), RenderHelpers.renderList(_ctx.touchEndEvent!!.touches, fun(touch, index, __index, _cached): Any {
                                return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "identifier: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-identifier"), toDisplayString(touch.identifier), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "pageX: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-page-x"), toDisplayString(touch.pageX), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "pageY: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-page-y"), toDisplayString(touch.pageY), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "clientX: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-client-x"), toDisplayString(touch.clientX), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "clientY: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-client-y"), toDisplayString(touch.clientY), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "screenX: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "screenY: "),
                                        createElementVNode("text", utsMapOf("id" to "touch-end-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                    ))
                                ), 64)
                            }), 128)
                        } else {
                            createCommentVNode("v-if", true)
                        },
                        createElementVNode("text", utsMapOf("class" to "title2 uni-common-mt"), "changedTouches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.touchEndEvent!!.changedTouches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "touch-end-changed-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.longPressEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 4), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "longPress Event: "),
                        createElementVNode("text", utsMapOf("class" to "title2"), "touches: "),
                        if (_ctx.longPressEvent!!.touches.length > 0) {
                            createElementVNode(Fragment, utsMapOf("key" to 0), RenderHelpers.renderList(_ctx.longPressEvent!!.touches, fun(touch, index, __index, _cached): Any {
                                return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "identifier: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-identifier"), toDisplayString(touch.identifier), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "pageX: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-page-x"), toDisplayString(touch.pageX), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "pageY: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-page-y"), toDisplayString(touch.pageY), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "clientX: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-client-x"), toDisplayString(touch.clientX), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "clientY: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-client-y"), toDisplayString(touch.clientY), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "screenX: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                    )),
                                    createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                        createElementVNode("text", null, "screenY: "),
                                        createElementVNode("text", utsMapOf("id" to "long-press-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                    ))
                                ), 64)
                            }), 128)
                        } else {
                            createCommentVNode("v-if", true)
                        },
                        createElementVNode("text", utsMapOf("class" to "title2 uni-common-mt"), "changedTouches: "),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.longPressEvent!!.changedTouches, fun(touch, index, __index, _cached): Any {
                            return createElementVNode(Fragment, utsMapOf("key" to index), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "title3"), "touch[" + toDisplayString(index) + "]:", 1),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "identifier: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-identifier"), toDisplayString(touch.identifier), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageX: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-page-x"), toDisplayString(touch.pageX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "pageY: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-page-y"), toDisplayString(touch.pageY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientX: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-client-x"), toDisplayString(touch.clientX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "clientY: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-client-y"), toDisplayString(touch.clientY), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenX: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-screen-x"), toDisplayString(touch.screenX), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                                    createElementVNode("text", null, "screenY: "),
                                    createElementVNode("text", utsMapOf("id" to "long-press-changed-touch-screen-y"), toDisplayString(touch.screenY), 1)
                                ))
                            ), 64)
                        }), 128)
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.tapEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 5), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "tap Event: "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                            createElementVNode("text", null, "x: "),
                            createElementVNode("text", utsMapOf("id" to "tap-event-x"), toDisplayString(_ctx.tapEvent!!.x), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                            createElementVNode("text", null, "y: "),
                            createElementVNode("text", utsMapOf("id" to "tap-event-y"), toDisplayString(_ctx.tapEvent!!.y), 1)
                        ))
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (_ctx.clickEvent != null) {
                    createElementVNode("view", utsMapOf("key" to 6), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "title1"), "click Event: "),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                            createElementVNode("text", null, "x: "),
                            createElementVNode("text", utsMapOf("id" to "click-event-x"), toDisplayString(_ctx.clickEvent!!.x), 1)
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                            createElementVNode("text", null, "y: "),
                            createElementVNode("text", utsMapOf("id" to "click-event-y"), toDisplayString(_ctx.clickEvent!!.y), 1)
                        ))
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
            ))
        ), 4)
    }
    open var title: String by `$data`
    open var touchStartEvent: TouchEvent? by `$data`
    open var touchCancelEvent: TouchEvent? by `$data`
    open var touchMoveEvent: TouchEvent? by `$data`
    open var longPressEvent: TouchEvent? by `$data`
    open var touchEndEvent: TouchEvent? by `$data`
    open var tapEvent: PointerEvent? by `$data`
    open var clickEvent: PointerEvent? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "global-events", "touchStartEvent" to null as TouchEvent?, "touchCancelEvent" to null as TouchEvent?, "touchMoveEvent" to null as TouchEvent?, "longPressEvent" to null as TouchEvent?, "touchEndEvent" to null as TouchEvent?, "tapEvent" to null as PointerEvent?, "clickEvent" to null as PointerEvent?)
    }
    open var onTouchStart = ::gen_onTouchStart_fn
    open fun gen_onTouchStart_fn(e: TouchEvent) {
        this.touchStartEvent = e
        console.log("onTouchStart", e, " at pages/component/global-events/global-events.uvue:449")
    }
    open var onTouchCancel = ::gen_onTouchCancel_fn
    open fun gen_onTouchCancel_fn(e: TouchEvent) {
        this.touchCancelEvent = e
        console.log("onTouchCancel", " at pages/component/global-events/global-events.uvue:453")
    }
    open var onTouchMove = ::gen_onTouchMove_fn
    open fun gen_onTouchMove_fn(e: TouchEvent) {
        this.touchMoveEvent = e
        console.log("onTouchMove", e, " at pages/component/global-events/global-events.uvue:457")
    }
    open var onLongPress = ::gen_onLongPress_fn
    open fun gen_onLongPress_fn(e: TouchEvent) {
        this.longPressEvent = e
        console.log("onLongPress", e, " at pages/component/global-events/global-events.uvue:461")
    }
    open var onTouchEnd = ::gen_onTouchEnd_fn
    open fun gen_onTouchEnd_fn(e: TouchEvent) {
        this.touchEndEvent = e
        console.log("onTouchEnd", e, " at pages/component/global-events/global-events.uvue:465")
    }
    open var onTap = ::gen_onTap_fn
    open fun gen_onTap_fn(e: PointerEvent) {
        this.tapEvent = e
        console.log("onTap", e, " at pages/component/global-events/global-events.uvue:469")
    }
    open var onClick = ::gen_onClick_fn
    open fun gen_onClick_fn(e: PointerEvent) {
        this.clickEvent = e
        console.log("onClick", e, " at pages/component/global-events/global-events.uvue:473")
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("paddingBottom" to 10)), "target" to padStyleMapOf(utsMapOf("marginTop" to 20, "marginRight" to 0, "marginBottom" to 0, "marginLeft" to 50, "width" to 200, "height" to 100, "backgroundColor" to "#00FFFF")), "title1" to padStyleMapOf(utsMapOf("marginTop" to 15, "fontSize" to 20)), "title2" to padStyleMapOf(utsMapOf("marginTop" to 10, "fontSize" to 18)), "title3" to padStyleMapOf(utsMapOf("marginTop" to 5, "fontSize" to 16)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
