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
open class GenPagesAPIGetAppGetApp : BasePage {
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
            this.lifeCycleNum = state.lifeCycleNum
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "padding-bottom" to "20px"))), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to "getApp")),
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                    createElementVNode("button", utsMapOf("onClick" to _ctx.getGlobalData), "get globalData", 8, utsArrayOf(
                        "onClick"
                    )),
                    if (isTrue(_ctx.originGlobalData.str.length)) {
                        createElementVNode(Fragment, utsMapOf("key" to 0), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt bold"), "初始的 globalData:"),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData string: " + toDisplayString(_ctx.originGlobalData.str), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData number: " + toDisplayString(_ctx.originGlobalData.num), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData boolean: " + toDisplayString(_ctx.originGlobalData.bool), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData object: " + toDisplayString(_ctx.originGlobalData.obj), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData null: " + toDisplayString(_ctx.originGlobalData.`null`), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData array: " + toDisplayString(_ctx.originGlobalData.arr), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData Set: " + toDisplayString(_ctx.originGlobalData.mySet), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData Map: " + toDisplayString(_ctx.originGlobalData.myMap), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData func 返回值: " + toDisplayString(_ctx.originGlobalDataFuncRes), 1)
                        ), 64)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("button", utsMapOf("onClick" to _ctx.setGlobalData, "class" to "uni-common-mt"), " set globalData ", 8, utsArrayOf(
                        "onClick"
                    )),
                    if (isTrue(_ctx.newGlobalData.bool)) {
                        createElementVNode(Fragment, utsMapOf("key" to 1), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt bold"), "更新后的 globalData:"),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData string: " + toDisplayString(_ctx.newGlobalData.str), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData number: " + toDisplayString(_ctx.newGlobalData.num), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData boolean: " + toDisplayString(_ctx.newGlobalData.bool), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData object: " + toDisplayString(_ctx.newGlobalData.obj), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData null: " + toDisplayString(_ctx.newGlobalData.`null`), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData array: " + toDisplayString(_ctx.newGlobalData.arr), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData Set: " + toDisplayString(_ctx.newGlobalData.mySet), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData Map: " + toDisplayString(_ctx.newGlobalData.myMap), 1),
                            createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "globalData func 返回值: " + toDisplayString(_ctx.newGlobalDataFuncRes), 1)
                        ), 64)
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "点击按钮调用 App.uvue methods"),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "increaseLifeCycleNum 方法"),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx._increaseLifeCycleNum), " increase lifeCycleNum ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "lifeCycleNum: " + toDisplayString(_ctx.lifeCycleNum), 1),
                    createElementVNode("button", utsMapOf("class" to "uni-common-mt", "onClick" to _ctx.getAndroidApplication), " getAndroidApplication ", 8, utsArrayOf(
                        "onClick"
                    )),
                    createElementVNode("text", utsMapOf("class" to "uni-common-mt"), "androidApplication is null: " + toDisplayString(_ctx.androidApplication == null), 1)
                ))
            ))
        ), 4)
    }
    open var originGlobalData: MyGlobalData by `$data`
    open var originGlobalDataFuncRes: String by `$data`
    open var newGlobalData: MyGlobalData by `$data`
    open var newGlobalDataFuncRes: String by `$data`
    open var lifeCycleNum: Number by `$data`
    open var androidApplication: Any? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("originGlobalData" to MyGlobalData(str = "", num = 0, bool = false, obj = object : UTSJSONObject() {
            var str = ""
            var num: Number = 0
            var bool = false
        }, `null` = null, arr = utsArrayOf<Number>(), mySet = utsArrayOf<String>(), myMap = UTSJSONObject(), func = fun(): String {
            return ""
        }
        ), "originGlobalDataFuncRes" to "", "newGlobalData" to MyGlobalData(str = "", num = 0, bool = false, obj = object : UTSJSONObject() {
            var str = ""
            var num: Number = 0
            var bool = false
        }, `null` = null, arr = utsArrayOf<Number>(), mySet = utsArrayOf<String>(), myMap = UTSJSONObject(), func = fun(): String {
            return ""
        }
        ), "newGlobalDataFuncRes" to "", "lifeCycleNum" to 0, "androidApplication" to null as Any?)
    }
    open var getGlobalData = ::gen_getGlobalData_fn
    open fun gen_getGlobalData_fn() {
        val app = getApp()
        this.originGlobalData.str = app.globalData.str
        this.originGlobalData.num = app.globalData.num
        this.originGlobalData.bool = app.globalData.bool
        this.originGlobalData.obj = app.globalData.obj
        this.originGlobalData.`null` = app.globalData.`null`
        this.originGlobalData.arr = app.globalData.arr
        app.globalData.mySet.forEach(fun(value: String){
            this.originGlobalData.mySet.push(value)
        }
        )
        app.globalData.myMap.forEach(fun(value: Any, key: String){
            this.originGlobalData.myMap[key] = value
        }
        )
        this.originGlobalData.func = app.globalData.func
        this.originGlobalDataFuncRes = this.originGlobalData.func()
    }
    open var setGlobalData = ::gen_setGlobalData_fn
    open fun gen_setGlobalData_fn() {
        val app = getApp()
        app.globalData.str = "new globalData str"
        app.globalData.num = 100
        app.globalData.bool = true
        app.globalData.obj = object : UTSJSONObject() {
            var str = "new globalData obj str"
            var num: Number = 200
            var bool = true
        }
        app.globalData.`null` = "not null"
        app.globalData.arr = utsArrayOf(
            1,
            2,
            3
        )
        app.globalData.mySet = Set(utsArrayOf(
            "a",
            "b",
            "c"
        ))
        app.globalData.myMap = Map(utsArrayOf(
            utsArrayOf(
                "a",
                1
            ),
            utsArrayOf(
                "b",
                2
            ),
            utsArrayOf(
                "c",
                3
            )
        ))
        app.globalData.func = fun(): String {
            return "new globalData func"
        }
        this.newGlobalData.str = app.globalData.str
        this.newGlobalData.num = app.globalData.num
        this.newGlobalData.bool = app.globalData.bool
        this.newGlobalData.obj = app.globalData.obj
        this.newGlobalData.`null` = app.globalData.`null`
        this.newGlobalData.arr = app.globalData.arr
        app.globalData.mySet.forEach(fun(value: String){
            this.newGlobalData.mySet.push(value)
        }
        )
        app.globalData.myMap.forEach(fun(value: Any, key: String){
            this.newGlobalData.myMap[key] = value
        }
        )
        this.newGlobalData.func = app.globalData.func
        this.newGlobalDataFuncRes = this.newGlobalData.func()
    }
    open var _increaseLifeCycleNum = ::gen__increaseLifeCycleNum_fn
    open fun gen__increaseLifeCycleNum_fn() {
        val app = getApp()
        app.vm!!.increaseLifeCycleNum()
        this.lifeCycleNum = state.lifeCycleNum
    }
    open var setLifeCycleNum = ::gen_setLifeCycleNum_fn
    open fun gen_setLifeCycleNum_fn(num: Number) {
        uni.UNI740BA18.setLifeCycleNum(num)
    }
    open var getAndroidApplication = ::gen_getAndroidApplication_fn
    open fun gen_getAndroidApplication_fn(): Boolean {
        val app = getApp()
        this.androidApplication = app.getAndroidApplication()
        return this.androidApplication != null
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
                return utsMapOf("bold" to padStyleMapOf(utsMapOf("fontWeight" to "bold")), "hr" to padStyleMapOf(utsMapOf("borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#cccccc")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
