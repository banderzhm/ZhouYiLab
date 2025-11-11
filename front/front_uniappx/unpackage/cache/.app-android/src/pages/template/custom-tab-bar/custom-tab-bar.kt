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
import io.dcloud.uniapp.extapi.`$off` as uni__off
import io.dcloud.uniapp.extapi.`$on` as uni__on
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesTemplateCustomTabBarCustomTabBar : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {
            uni__on("tabchange", this.onTabPageEvent)
        }
        , __ins)
        onReady(fun() {
            (this.`$refs`["tabview"] as UniElement).getBoundingClientRectAsync()!!.then(fun(res: DOMRect){
                this.tabViewHeight = res.height
            }
            )
        }
        , __ins)
        onUnload(fun() {
            uni__off("tabchange", this.onTabPageEvent)
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_tab1 = resolveComponent("tab1")
        val _component_tab2 = resolveComponent("tab2")
        return createElementVNode("view", utsMapOf("class" to "tabs"), utsArrayOf(
            createElementVNode("view", utsMapOf("ref" to "tabview", "class" to "tab-view"), utsArrayOf(
                if (isTrue(_ctx.tabList[0].init)) {
                    createVNode(_component_tab1, utsMapOf("key" to 0, "ref" to "tab1", "class" to "tab-page", "style" to normalizeStyle(utsMapOf("visibility" to if (_ctx.selectedIndex == 0) {
                        "visible"
                    } else {
                        "hidden"
                    }))), null, 8, utsArrayOf(
                        "style"
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
                ,
                if (isTrue(_ctx.tabList[1].init)) {
                    createVNode(_component_tab2, utsMapOf("key" to 1, "ref" to "tab2", "class" to "tab-page", "style" to normalizeStyle(utsMapOf("visibility" to if (_ctx.selectedIndex == 1) {
                        "visible"
                    } else {
                        "hidden"
                    }))), null, 8, utsArrayOf(
                        "style"
                    ))
                } else {
                    createCommentVNode("v-if", true)
                }
            ), 512),
            createElementVNode("view", utsMapOf("ref" to "tabbar", "class" to "tab-bar"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "tab-item", "onClick" to fun(){
                    _ctx.onTabClick(0)
                }
                ), utsArrayOf(
                    createElementVNode("view", utsMapOf("ref" to "tab-item1", "class" to "tab-item-content"), utsArrayOf(
                        if (isTrue(_ctx.displayArrow)) {
                            createElementVNode("text", utsMapOf("key" to 0, "class" to normalizeClass(utsArrayOf(
                                "tab-item-icon tab-item-arrow uni-icon",
                                if (_ctx.selectedIndex == 0) {
                                    "tab-item-text-active"
                                } else {
                                    ""
                                }
                            ))), toDisplayString("\ue6bd"), 2)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                        ,
                        if (isTrue(!_ctx.displayArrow)) {
                            createElementVNode("text", utsMapOf("key" to 1, "class" to normalizeClass(utsArrayOf(
                                "tab-item-icon uni-icon",
                                if (_ctx.selectedIndex == 0) {
                                    "tab-item-text-active"
                                } else {
                                    ""
                                }
                            ))), toDisplayString("\ue644"), 2)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                        ,
                        if (isTrue(!_ctx.displayArrow)) {
                            createElementVNode("text", utsMapOf("key" to 2, "class" to normalizeClass(utsArrayOf(
                                "tab-item-text",
                                if (_ctx.selectedIndex == 0) {
                                    "tab-item-text-active"
                                } else {
                                    ""
                                }
                            ))), " 首页 ", 2)
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    ), 512)
                ), 8, utsArrayOf(
                    "onClick"
                )),
                createElementVNode("view", null, utsArrayOf(
                    createElementVNode("image", utsMapOf("class" to "concave-image", "mode" to "heightFix", "src" to "/static/template/custom-tab-bar/concave.png"))
                )),
                createElementVNode("view", utsMapOf("class" to "tab-item", "onClick" to fun(){
                    _ctx.onTabClick(1)
                }
                ), utsArrayOf(
                    createElementVNode("view", utsMapOf("ref" to "tab-item2", "class" to "tab-item-content"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                            "tab-item-icon uni-icon",
                            if (_ctx.selectedIndex == 1) {
                                "tab-item-text-active"
                            } else {
                                ""
                            }
                        ))), toDisplayString("\ue699"), 2),
                        createElementVNode("text", utsMapOf("class" to normalizeClass(utsArrayOf(
                            "tab-item-text",
                            if (_ctx.selectedIndex == 1) {
                                "tab-item-text-active"
                            } else {
                                ""
                            }
                        ))), " 我的 ", 2)
                    ), 512)
                ), 8, utsArrayOf(
                    "onClick"
                ))
            ), 512),
            createElementVNode("view", utsMapOf("class" to "btn-plus", "onClick" to _ctx.onPlusClick), utsArrayOf(
                createElementVNode("text", utsMapOf("class" to "btn-plus-text"), "+")
            ), 8, utsArrayOf(
                "onClick"
            ))
        ))
    }
    open var tabList: UTSArray<TabItem> by `$data`
    open var selectedIndex: Number by `$data`
    open var displayArrow: Boolean by `$data`
    open var lastTab1Top: Number by `$data`
    open var tabViewHeight: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("tabList" to utsArrayOf<TabItem>(TabItem(init = true, preload = false), TabItem(init = false, preload = false)), "selectedIndex" to 0, "displayArrow" to false, "lastTab1Top" to 0, "tabViewHeight" to 0)
    }
    open var onTabClick = ::gen_onTabClick_fn
    open fun gen_onTabClick_fn(index: Number) {
        if (this.selectedIndex == index && index == 0 && this.displayArrow == true) {
            this.displayArrow = false
            (this.`$refs`["tab1"]!! as ComponentPublicInstance).`$callMethod`("scrollTop", 0)
        } else if (index != 0) {
            this.displayArrow = false
        } else if (index == 0 && this.selectedIndex != 0) {
            this.displayArrow = this.lastTab1Top > this.tabViewHeight
        }
        this.setSelectedIndex(index)
    }
    open var onTabPageEvent = ::gen_onTabPageEvent_fn
    open fun gen_onTabPageEvent_fn(top: Number) {
        this.displayArrow = top > this.tabViewHeight
        this.lastTab1Top = top
    }
    open var setSelectedIndex = ::gen_setSelectedIndex_fn
    open fun gen_setSelectedIndex_fn(index: Number) {
        if (this.selectedIndex === index) {
            return
        }
        if (!this.tabList[index].init) {
            this.tabList[index].init = true
        }
        this.selectedIndex = index
    }
    open var onPlusClick = ::gen_onPlusClick_fn
    open fun gen_onPlusClick_fn() {
        uni_showModal(ShowModalOptions(title = "提示", content = "你点击了 +", showCancel = false))
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
                return utsMapOf("uni-icon" to padStyleMapOf(utsMapOf("fontFamily" to "UniIcon", "fontSize" to 16, "fontStyle" to "normal")), "tabs" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#ffffff", "overflow" to "visible")), "tab-view" to padStyleMapOf(utsMapOf("flex" to 1)), "tab-page" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to "100%", "height" to "100%")), "tab-bar" to padStyleMapOf(utsMapOf("flexDirection" to "row", "height" to 56, "overflow" to "visible")), "tab-item" to padStyleMapOf(utsMapOf("flex" to 1, "position" to "relative", "backgroundColor" to "#1e90ff", "overflow" to "visible")), "tab-item-content" to padStyleMapOf(utsMapOf("marginTop" to "auto", "marginRight" to "auto", "marginBottom" to "auto", "marginLeft" to "auto", "transitionProperty" to "transform", "transitionDuration" to "0.3s")), "tab-item-icon" to padStyleMapOf(utsMapOf("color" to "#cccccc", "fontSize" to 12, "textAlign" to "center", "marginBottom" to 4)), "tab-item-text" to padStyleMapOf(utsMapOf("color" to "#cccccc", "fontSize" to 12, "textAlign" to "center")), "tab-item-text-active" to padStyleMapOf(utsMapOf("color" to "#ffffff")), "tab-item-arrow" to padStyleMapOf(utsMapOf("!fontSize" to 30, "fontWeight" to "bold")), "concave-image" to padStyleMapOf(utsMapOf("height" to 56)), "btn-plus" to padStyleMapOf(utsMapOf("position" to "absolute", "width" to 70, "height" to 70, "bottom" to 21, "borderTopLeftRadius" to 50, "borderTopRightRadius" to 50, "borderBottomRightRadius" to 50, "borderBottomLeftRadius" to 50, "backgroundColor" to "#FE5722", "boxShadow" to "0 0 4px rgba(0, 0, 0, 0.5)", "alignSelf" to "center", "alignItems" to "center", "justifyContent" to "center", "overflow" to "visible")), "btn-plus-text" to padStyleMapOf(utsMapOf("color" to "#ffffff", "fontSize" to 32)), "@FONT-FACE" to utsMapOf("0" to utsMapOf("fontFamily" to "UniIcon", "src" to "url('/static/fonts/uni-icon.ttf')")), "@TRANSITION" to utsMapOf("tab-item-content" to utsMapOf("property" to "transform", "duration" to "0.3s")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf("tab1" to GenPagesTemplateCustomTabBarCustomTabBarTab1Class, "tab2" to GenPagesTemplateCustomTabBarCustomTabBarTab2Class)
    }
}
