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
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.reLaunch as uni_reLaunch
open class GenPagesTabBarCSS : BasePage {
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
        val _component_u_link = resolveEasyComponent("u-link", GenComponentsULinkULinkClass)
        val _component_uni_collapse_item = resolveEasyComponent("uni-collapse-item", GenComponentsUniCollapseItemUniCollapseItemClass)
        val _component_uni_collapse = resolveEasyComponent("uni-collapse", GenComponentsUniCollapseUniCollapseClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "enable-back-to-top" to "true"), utsArrayOf(
            createElementVNode("view", utsMapOf("class" to normalizeClass(utsArrayOf(
                "uni-container",
                if (_ctx.isDarkMode) {
                    "theme-dark"
                } else {
                    "theme-light"
                }
            ))), utsArrayOf(
                if (isTrue(!_ctx.hasLeftWin)) {
                    createElementVNode("view", utsMapOf("key" to 0, "class" to "uni-header-logo"), utsArrayOf(
                        createElementVNode("image", utsMapOf("class" to "uni-header-image", "src" to "/static/cssIndex.png"))
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (isTrue(!_ctx.hasLeftWin)) {
                    createElementVNode("view", utsMapOf("key" to 1, "class" to "uni-text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "hello-text"), "uni-app x目前已支持的CSS属性，展示样式仅供参考，文档详见："),
                        createVNode(_component_u_link, utsMapOf("href" to "https://uniapp.dcloud.io/uni-app-x/css/", "text" to "https://uniapp.dcloud.io/uni-app-x/css/", "inWhiteList" to true), null, 8, utsArrayOf(
                            "href",
                            "text"
                        ))
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                createVNode(_component_uni_collapse, null, utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                    return utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.menu, fun(menuItem, __key, __index, _cached): Any {
                            return createVNode(_component_uni_collapse_item, utsMapOf("ref_for" to true, "ref" to "category", "key" to menuItem.id, "title" to menuItem.name, "class" to "uni-panel"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                return utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(menuItem.items, fun(childMenuItem, __key, __index, _cached): Any {
                                        return createElementVNode(Fragment, utsMapOf("key" to childMenuItem.id), utsArrayOf(
                                            if (childMenuItem.items.length == 0) {
                                                createElementVNode("view", utsMapOf("key" to 0, "class" to "uni-navigate-item pl", "hover-class" to "is--active", "onClick" to fun(){
                                                    _ctx.goPage("/" + childMenuItem.path)
                                                }), utsArrayOf(
                                                    createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                                                        "uni-navigate-text",
                                                        utsMapOf("left-win-active" to (_ctx.leftWinActive === childMenuItem.path && _ctx.hasLeftWin))
                                                    ))), toDisplayString(childMenuItem.style["navigationBarTitleText"]), 3),
                                                    createElementVNode("image", utsMapOf("src" to _ctx.arrowRightIcon, "class" to "uni-icon-size"), null, 8, utsArrayOf(
                                                        "src"
                                                    ))
                                                ), 8, utsArrayOf(
                                                    "onClick"
                                                ))
                                            } else {
                                                createVNode(_component_uni_collapse, utsMapOf("key" to 1, "style" to normalizeStyle(utsMapOf("width" to "100%", "border-top" to "1px solid #f0f0f0"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                    return utsArrayOf(
                                                        createVNode(_component_uni_collapse_item, utsMapOf("title" to childMenuItem.name, "class" to "uni-panel", "style" to normalizeStyle(utsMapOf("margin-bottom" to "0"))), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                            return utsArrayOf(
                                                                createElementVNode(Fragment, null, RenderHelpers.renderList(childMenuItem.items, fun(grandChildMenuItem, __key, __index, _cached): Any {
                                                                    return createElementVNode("view", utsMapOf("class" to "uni-navigate-item pl", "hover-class" to "is--active", "key" to grandChildMenuItem.path, "onClick" to fun(){
                                                                        _ctx.goPage("/" + grandChildMenuItem.path)
                                                                    }
                                                                    ), utsArrayOf(
                                                                        createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                                                                            "uni-navigate-text",
                                                                            utsMapOf("left-win-active" to (_ctx.leftWinActive === grandChildMenuItem.path && _ctx.hasLeftWin))
                                                                        ))), toDisplayString(grandChildMenuItem.style["navigationBarTitleText"]), 3),
                                                                        createElementVNode("image", utsMapOf("src" to _ctx.arrowRightIcon, "class" to "uni-icon-size"), null, 8, utsArrayOf(
                                                                            "src"
                                                                        ))
                                                                    ), 8, utsArrayOf(
                                                                        "onClick"
                                                                    ))
                                                                }
                                                                ), 128)
                                                            )
                                                        }
                                                        ), "_" to 2), 1032, utsArrayOf(
                                                            "title",
                                                            "style"
                                                        ))
                                                    )
                                                }
                                                ), "_" to 2), 1032, utsArrayOf(
                                                    "style"
                                                ))
                                            }
                                        ), 64)
                                    }
                                    ), 128)
                                )
                            }
                            ), "_" to 2), 1032, utsArrayOf(
                                "title"
                            ))
                        }
                        ), 128)
                    )
                }
                ), "_" to 1))
            ), 2)
        ), 4)
    }
    open var menu: UTSArray<MenuItem> by `$data`
    open var arrowRightIcon: String by `$data`
    open var hasLeftWin: Boolean by `$data`
    open var leftWinActive: String by `$data`
    open var isDarkMode: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("menu" to menu2 as UTSArray<MenuItem>, "arrowRightIcon" to "/static/icons/arrow-right.png", "hasLeftWin" to computed<Boolean>(fun(): Boolean {
            return !state.noMatchLeftWindow
        }
        ), "leftWinActive" to computed<String>(fun(): String {
            return state.leftWinActive.slice(1)
        }
        ), "isDarkMode" to computed<Boolean>(fun(): Boolean {
            return state.isDarkMode
        }
        ))
    }
    open var goPage = ::gen_goPage_fn
    open fun gen_goPage_fn(url: String) {
        if (this.hasLeftWin) {
            uni_reLaunch(ReLaunchOptions(url = url))
        } else {
            uni_navigateTo(NavigateToOptions(url = url))
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
                return utsMapOf("pl" to padStyleMapOf(utsMapOf("paddingLeft" to 18)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
