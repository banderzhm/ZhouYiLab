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
import io.dcloud.uniapp.extapi.getWindowInfo as uni_getWindowInfo
open class GenPagesAPIGetWindowInfoWindowArea : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPIGetWindowInfoWindowArea) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIGetWindowInfoWindowArea
            val _cache = __ins.renderCache
            val statusBarArea = ref(StatusBarArea(width = 0, height = 0))
            val cutoutArea = ref(utsArrayOf<CutoutArea>())
            val safeArea = ref(SafeArea1(top = 0, left = 0, width = 0, height = 0))
            val bottomNavigationIndicatorArea = ref(BottomNavigationIndicatorArea(width = 0, height = 0))
            val isStatusBarShow = ref(false)
            val isBottomNavigationIndicatorShow = ref(false)
            val getWindowInfo = fun(){
                val info = uni_getWindowInfo()
                statusBarArea.value.width = info.windowWidth
                statusBarArea.value.height = info.safeAreaInsets.top
                cutoutArea.value.length = 0
                (info.cutoutArea ?: utsArrayOf()).forEach(fun(item){
                    cutoutArea.value.push(CutoutArea(top = item.top, left = item.left, width = item.right - item.left, height = item.bottom - item.top))
                }
                )
                safeArea.value.top = info.safeArea.top
                safeArea.value.left = info.safeArea.left
                safeArea.value.width = info.safeArea.width
                safeArea.value.height = info.safeArea.height
                bottomNavigationIndicatorArea.value.width = info.windowWidth
                bottomNavigationIndicatorArea.value.height = info.safeAreaInsets.bottom
            }
            onReady(fun(){
                val pages = getCurrentPages()
                isStatusBarShow.value = !(pages[pages.length - 1].getPageStyle()["hideStatusBar"] as Boolean)
                isBottomNavigationIndicatorShow.value = !(pages[pages.length - 1].getPageStyle()["hideBottomNavigationIndicator"] as Boolean)
                getWindowInfo()
            }
            )
            onResize(fun(_: OnResizeOptions){
                getWindowInfo()
            }
            )
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    if (isTrue(unref(statusBarArea).width > 0 && unref(statusBarArea).height > 0)) {
                        createElementVNode("view", utsMapOf("key" to 0, "id" to "statusBar", "class" to "statusBar", "style" to normalizeStyle(utsMapOf("width" to unref(statusBarArea).width, "height" to unref(statusBarArea).height))), null, 4)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    if (unref(cutoutArea).length > 0) {
                        createElementVNode(Fragment, utsMapOf("key" to 1), RenderHelpers.renderList(unref(cutoutArea), fun(item, _, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("id" to "cutoutArea", "class" to "cutoutArea", "style" to normalizeStyle(utsMapOf("top" to item.top, "left" to item.left, "width" to item.width, "height" to item.height))), null, 4)
                        }), 256)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("view", utsMapOf("id" to "safeArea", "class" to "safeArea", "style" to normalizeStyle(utsMapOf("top" to unref(safeArea).top, "left" to unref(safeArea).left, "width" to unref(safeArea).width, "height" to unref(safeArea).height))), null, 4),
                    if (isTrue(unref(bottomNavigationIndicatorArea).width > 0 && unref(bottomNavigationIndicatorArea).height > 0)) {
                        createElementVNode("view", utsMapOf("key" to 2, "id" to "bottomNavigationIndicator", "class" to "bottomNavigationIndicator", "style" to normalizeStyle(utsMapOf("width" to unref(bottomNavigationIndicatorArea).width, "height" to unref(bottomNavigationIndicatorArea).height))), null, 4)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "justify-content" to "center", "align-items" to "center"))), utsArrayOf(
                        if (isTrue(unref(statusBarArea).width > 0 && unref(statusBarArea).height > 0)) {
                            createElementVNode("view", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("margin" to "5px 0"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "red"))), "系统状态栏区域", 4)
                            ), 4)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                        ,
                        if (unref(cutoutArea).length > 0) {
                            createElementVNode("view", utsMapOf("key" to 1, "style" to normalizeStyle(utsMapOf("margin" to "5px 0"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "orange"))), "摄像头区域", 4)
                            ), 4)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                        ,
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("margin" to "5px 0"))), utsArrayOf(
                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "green"))), "安全区域", 4)
                        ), 4),
                        if (isTrue(unref(bottomNavigationIndicatorArea).width > 0 && unref(bottomNavigationIndicatorArea).height > 0)) {
                            createElementVNode("view", utsMapOf("key" to 2, "style" to normalizeStyle(utsMapOf("margin" to "5px 0"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "blue"))), "系统导航栏区域", 4)
                            ), 4)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    ), 4)
                ), 4)
            }
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("statusBar" to padStyleMapOf(utsMapOf("position" to "absolute", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FF0000", "borderRightColor" to "#FF0000", "borderBottomColor" to "#FF0000", "borderLeftColor" to "#FF0000", "borderTopWidth" to 4, "borderRightWidth" to 4, "borderBottomWidth" to 4, "borderLeftWidth" to 4)), "cutoutArea" to padStyleMapOf(utsMapOf("position" to "absolute", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#FFA500", "borderRightColor" to "#FFA500", "borderBottomColor" to "#FFA500", "borderLeftColor" to "#FFA500", "borderTopWidth" to 4, "borderRightWidth" to 4, "borderBottomWidth" to 4, "borderLeftWidth" to 4)), "safeArea" to padStyleMapOf(utsMapOf("position" to "absolute", "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#008000", "borderRightColor" to "#008000", "borderBottomColor" to "#008000", "borderLeftColor" to "#008000", "borderTopWidth" to 4, "borderRightWidth" to 4, "borderBottomWidth" to 4, "borderLeftWidth" to 4)), "bottomNavigationIndicator" to padStyleMapOf(utsMapOf("position" to "absolute", "bottom" to 0, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#0000FF", "borderRightColor" to "#0000FF", "borderBottomColor" to "#0000FF", "borderLeftColor" to "#0000FF", "borderTopWidth" to 4, "borderRightWidth" to 4, "borderBottomWidth" to 4, "borderLeftWidth" to 4)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
