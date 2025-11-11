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
open class GenPagesComponentStickySectionStickySection : BasePage {
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
        onLoad(fun(_: OnLoadOptions) {}, __ins)
        onReady(fun() {
            this.initSectionArray()
            this.isReady = true
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode(Fragment, null, utsArrayOf(
            createVNode(_component_page_head, utsMapOf("title" to "sticky-section")),
            createElementVNode("list-view", utsMapOf("id" to "list-view", "ref" to "list-view", "show-scrollbar" to "false", "class" to "page", "scroll-into-view" to _ctx.scrollIntoView, "onScroll" to _ctx.onScroll, "onScrollend" to _ctx.onScrollEnd, "bounces" to "false", "refresher-enabled" to "true", "refresher-triggered" to _ctx.refresherTriggered, "onRefresherrefresh" to _ctx.onRefresherrefresh), utsArrayOf(
                createElementVNode("list-item", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "10px", "margin" to "5px 0", "align-items" to "center")), "type" to 20), utsArrayOf(
                    createElementVNode("button", utsMapOf("onClick" to fun(){
                        _ctx.gotoStickyHeader("C")
                    }
                    , "size" to "mini"), "跳转到id为C的sticky-header位置上", 8, utsArrayOf(
                        "onClick"
                    ))
                ), 4),
                createElementVNode("list-item", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "10px", "margin" to "5px 0", "align-items" to "center")), "type" to 20), utsArrayOf(
                    createElementVNode("button", utsMapOf("onClick" to fun(){
                        _ctx.appendSectionItem(0)
                    }
                    , "size" to "mini"), "第一组 section 新增5条内容", 8, utsArrayOf(
                        "onClick"
                    ))
                ), 4),
                createElementVNode("list-item", utsMapOf("style" to normalizeStyle(utsMapOf("padding" to "10px", "margin" to "5px 0", "align-items" to "center")), "type" to 20), utsArrayOf(
                    createElementVNode("button", utsMapOf("onClick" to fun(){
                        _ctx.deleteSection()
                    }
                    , "size" to "mini"), "删除第一组 section", 8, utsArrayOf(
                        "onClick"
                    ))
                ), 4),
                createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.sectionArray, fun(section, __key, __index, _cached): Any {
                    return createElementVNode("sticky-section", utsMapOf("key" to section.name, "padding" to _ctx.sectionPadding, "push-pinned-header" to true), utsArrayOf(
                        createElementVNode("sticky-header", utsMapOf("id" to section.name), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "sticky-header-text"), toDisplayString(section.name), 1)
                        ), 8, utsArrayOf(
                            "id"
                        )),
                        createElementVNode(Fragment, null, RenderHelpers.renderList(section.list, fun(list, __key, __index, _cached): Any {
                            return createElementVNode("list-item", utsMapOf("key" to list.text, "name" to list.text, "class" to "content-item", "type" to 10), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "text"), toDisplayString(list.text), 1)
                            ), 8, utsArrayOf(
                                "name"
                            ))
                        }
                        ), 128)
                    ), 8, utsArrayOf(
                        "padding"
                    ))
                }
                ), 128),
                if (_ctx.sectionArray.length > 0) {
                    createElementVNode("list-item", utsMapOf("key" to 0, "style" to normalizeStyle(utsMapOf("padding" to "10px", "margin" to "5px 0", "align-items" to "center")), "type" to 30), utsArrayOf(
                        createElementVNode("button", utsMapOf("onClick" to _ctx.toTop, "size" to "mini"), "回到顶部", 8, utsArrayOf(
                            "onClick"
                        ))
                    ), 4)
                } else {
                    createCommentVNode("v-if", true)
                }
            ), 40, utsArrayOf(
                "scroll-into-view",
                "onScroll",
                "onScrollend",
                "refresher-triggered",
                "onRefresherrefresh"
            ))
        ), 64)
    }
    open var data: UTSArray<String> by `$data`
    open var sectionPadding: UTSArray<Number> by `$data`
    open var scrollIntoView: String by `$data`
    open var scrolling: Boolean by `$data`
    open var sectionArray: UTSArray<sectionData> by `$data`
    open var appendId: Number by `$data`
    open var refresherTriggered: Boolean by `$data`
    open var isReady: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("data" to utsArrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N"
        ), "sectionPadding" to utsArrayOf<Number>(0, 10, 0, 10), "scrollIntoView" to "", "scrolling" to false, "sectionArray" to utsArrayOf<sectionData>(), "appendId" to 0, "refresherTriggered" to false, "isReady" to false)
    }
    open var initSectionArray = ::gen_initSectionArray_fn
    open fun gen_initSectionArray_fn() {
        this.sectionArray = utsArrayOf<sectionData>()
        console.log("initSectionArray start", this.sectionArray.length, " at pages/component/sticky-section/sticky-section.uvue:62")
        this.data.forEach(fun(key){
            val list = utsArrayOf<sectionListItem>()
            run {
                var i: Number = 1
                while(i < 11){
                    val item = sectionListItem(text = key + "--item--content----" + i)
                    list.push(item)
                    i++
                }
            }
            val data = sectionData(name = key, list = list)
            this.sectionArray.push(data)
        }
        )
        console.log("initSectionArray end", this.sectionArray[0].name, " at pages/component/sticky-section/sticky-section.uvue:73")
    }
    open var toTop = ::gen_toTop_fn
    open fun gen_toTop_fn() {
        this.scrollIntoView = ""
        uni_getElementById("list-view")!!.scrollTop = 0
    }
    open var listViewScrollByY = ::gen_listViewScrollByY_fn
    open fun gen_listViewScrollByY_fn(y: Number) {
        val listview = this.`$refs`["list-view"] as UniElement
        listview.scrollTop = y
    }
    open var gotoStickyHeader = ::gen_gotoStickyHeader_fn
    open fun gen_gotoStickyHeader_fn(id: String) {
        this.scrollIntoView = id
    }
    open var onScroll = ::gen_onScroll_fn
    open fun gen_onScroll_fn() {
        this.scrolling = true
    }
    open var onScrollEnd = ::gen_onScrollEnd_fn
    open fun gen_onScrollEnd_fn() {
        this.scrolling = false
        if (this.scrollIntoView != "") {
            this.scrollIntoView = ""
        }
    }
    open var appendSectionItem = ::gen_appendSectionItem_fn
    open fun gen_appendSectionItem_fn(index: Number) {
        val data = this.sectionArray[index]
        this.appendId++
        val list = utsArrayOf<sectionListItem>(sectionListItem(text = data.name + "--item--content----new1--" + this.appendId), sectionListItem(text = data.name + "--item--content----new2--" + this.appendId), sectionListItem(text = data.name + "--item--content----new3--" + this.appendId), sectionListItem(text = data.name + "--item--content----new4--" + this.appendId), sectionListItem(text = data.name + "--item--content----new5--" + this.appendId))
        data.list.unshift(*list.toTypedArray())
    }
    open var deleteSection = ::gen_deleteSection_fn
    open fun gen_deleteSection_fn() {
        this.sectionArray.shift()
    }
    open var onRefresherrefresh = ::gen_onRefresherrefresh_fn
    open fun gen_onRefresherrefresh_fn(_0: UniRefresherEvent) {
        this.refresherTriggered = true
        setTimeout(fun(){
            this.initSectionArray()
            this.refresherTriggered = false
        }
        , 1000)
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
                return utsMapOf("page" to padStyleMapOf(utsMapOf("flex" to 1, "backgroundColor" to "#f5f5f5")), "sticky-header-text" to padStyleMapOf(utsMapOf("fontSize" to 16, "paddingTop" to 8, "paddingRight" to 8, "paddingBottom" to 8, "paddingLeft" to 8, "color" to "#959595", "backgroundColor" to "#f5f5f5")), "content-item" to padStyleMapOf(utsMapOf("paddingTop" to 15, "paddingRight" to 15, "paddingBottom" to 15, "paddingLeft" to 15, "marginBottom" to 10, "backgroundColor" to "#ffffff")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
