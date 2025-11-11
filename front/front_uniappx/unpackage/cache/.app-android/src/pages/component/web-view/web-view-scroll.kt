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
open class GenPagesComponentWebViewWebViewScroll : BasePage {
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
        var setup: (__props: GenPagesComponentWebViewWebViewScroll) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentWebViewWebViewScroll
            val _cache = __ins.renderCache
            val types = utsArrayOf(
                ItemType(value = 0, name = "all"),
                ItemType(value = 1, name = "vertical"),
                ItemType(value = 2, name = "horizontal"),
                ItemType(value = 3, name = "none")
            ) as UTSArray<ItemType>
            val type = ref("all")
            val onTypeChange = fun(value: Number){
                type.value = types[value].name
            }
            return fun(): Any? {
                val _component_enum_data = resolveEasyComponent("enum-data", GenComponentsEnumDataEnumDataClass)
                return createElementVNode(Fragment, null, utsArrayOf(
                    createVNode(_component_enum_data, utsMapOf("title" to "android-nested-scroll（默认值为all）", "items" to types, "onChange" to onTypeChange)),
                    createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "scroll-item-title"), "item" + toDisplayString(item), 1)
                            ))
                        }
                        ), 64),
                        createElementVNode("web-view", utsMapOf("class" to "web-view", "src" to "/hybrid/html/local.html", "android-nested-scroll" to unref(type)), null, 8, utsArrayOf(
                            "android-nested-scroll"
                        )),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "scroll-item-title"), "item" + toDisplayString(item), 1)
                            ))
                        }
                        ), 64),
                        createElementVNode("web-view", utsMapOf("class" to "web-view", "src" to "/hybrid/html/simple.html", "android-nested-scroll" to unref(type)), null, 8, utsArrayOf(
                            "android-nested-scroll"
                        )),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "scroll-item-title"), "item" + toDisplayString(item), 1)
                            ))
                        }
                        ), 64),
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("color" to "coral", "text-align" to "center", "margin-top" to "6px"))), "下面是测试嵌套横向滚动scroll-view的场景", 4),
                        createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-direction" to "row")), "direction" to "horizontal"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "scroll-item2"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "scroll-item-title2"), "item" + toDisplayString(item), 1)
                                ))
                            }
                            ), 64),
                            createElementVNode("web-view", utsMapOf("class" to "web-view", "src" to "/hybrid/html/local.html", "android-nested-scroll" to unref(type)), null, 8, utsArrayOf(
                                "android-nested-scroll"
                            )),
                            createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "scroll-item2"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "scroll-item-title2"), "item" + toDisplayString(item), 1)
                                ))
                            }
                            ), 64),
                            createElementVNode("web-view", utsMapOf("class" to "web-view", "src" to "/hybrid/html/simple.html", "android-nested-scroll" to unref(type)), null, 8, utsArrayOf(
                                "android-nested-scroll"
                            )),
                            createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                                return createElementVNode("view", utsMapOf("class" to "scroll-item2"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "scroll-item-title2"), "item" + toDisplayString(item), 1)
                                ))
                            }
                            ), 64)
                        ), 4),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(5, fun(item, __key, __index, _cached): Any {
                            return createElementVNode("view", utsMapOf("class" to "scroll-item"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "scroll-item-title"), "item" + toDisplayString(item), 1)
                            ))
                        }
                        ), 64)
                    ), 4)
                ), 64)
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
                return utsMapOf("web-view" to padStyleMapOf(utsMapOf("width" to 300, "height" to 300, "marginLeft" to 6, "marginRight" to 6, "marginTop" to 6, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#000000", "borderRightColor" to "#000000", "borderBottomColor" to "#000000", "borderLeftColor" to "#000000", "alignSelf" to "center")), "scroll-item" to padStyleMapOf(utsMapOf("marginLeft" to 6, "marginRight" to 6, "marginTop" to 6, "backgroundColor" to "#ffffff")), "scroll-item2" to padStyleMapOf(utsMapOf("width" to 80, "justifyContent" to "center", "marginLeft" to 6, "marginRight" to 6, "marginTop" to 6, "backgroundColor" to "#ffffff")), "scroll-item-title" to padStyleMapOf(utsMapOf("lineHeight" to "60px", "textAlign" to "center", "color" to "#555555")), "scroll-item-title2" to padStyleMapOf(utsMapOf("textAlign" to "center", "color" to "#555555")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
