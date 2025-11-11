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
open class GenPagesComponentStickySectionIssues16118 : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("button", utsMapOf("onClick" to _ctx.switchDisplay), "显示内容", 8, utsArrayOf(
                "onClick"
            )),
            withDirectives(createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                createElementVNode("list-view", utsMapOf("style" to normalizeStyle(utsMapOf("height" to "200px"))), utsArrayOf(
                    createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.groupItems, fun(group, _, __index, _cached): Any {
                        return createElementVNode("sticky-section", null, utsArrayOf(
                            createElementVNode("sticky-header", utsMapOf("style" to normalizeStyle(utsMapOf("background-color" to "antiquewhite"))), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "group-title"), toDisplayString(group.title), 1)
                            ), 4),
                            createElementVNode("list-item", utsMapOf("class" to "content-group"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "content-box"), utsArrayOf(
                                    createElementVNode(Fragment, null, RenderHelpers.renderList(20, fun(index, __key, __index, _cached): Any {
                                        return createElementVNode("text", utsMapOf("class" to "item"), toDisplayString(index) + " item信息", 1)
                                    }
                                    ), 64)
                                ))
                            ))
                        ))
                    }
                    ), 256)
                ), 4)
            ), 4), utsArrayOf(
                utsArrayOf(
                    vShow,
                    _ctx.contentVisible
                )
            ))
        ), 4)
    }
    open var groupItems: UTSArray<ItemGroup> by `$data`
    open var contentVisible: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("groupItems" to utsArrayOf<ItemGroup>(), "contentVisible" to false)
    }
    open var switchDisplay = ::gen_switchDisplay_fn
    open fun gen_switchDisplay_fn() {
        if (this.contentVisible) {
            this.contentVisible = false
        } else {
            this.contentVisible = true
        }
        if (this.contentVisible) {
            setTimeout(fun(){
                this.groupItems = utsArrayOf<ItemGroup>(ItemGroup(title = "sticky-header"))
            }, 200)
        } else {
            setTimeout(fun(){
                this.groupItems = utsArrayOf()
            }
            , 200)
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
                return utsMapOf("group-title" to padStyleMapOf(utsMapOf("fontWeight" to "bold", "fontSize" to 14, "color" to "#4B515A", "paddingTop" to 8, "paddingRight" to 0, "paddingBottom" to 8, "paddingLeft" to 0, "backgroundColor" to "#FFFFFF")), "content-group" to padStyleMapOf(utsMapOf("flexDirection" to "row", "flexWrap" to "wrap")), "content-box" to padStyleMapOf(utsMapOf("width" to "100%", "position" to "relative")), "item" to padStyleMapOf(utsMapOf("paddingTop" to 10, "paddingRight" to 0, "paddingBottom" to 10, "paddingLeft" to 0, "backgroundColor" to "#efeda7")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
