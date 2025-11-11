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
import io.dcloud.uniapp.extapi.clearStorage as uni_clearStorage
import io.dcloud.uniapp.extapi.clearStorageSync as uni_clearStorageSync
import io.dcloud.uniapp.extapi.getStorage as uni_getStorage
import io.dcloud.uniapp.extapi.getStorageInfo as uni_getStorageInfo
import io.dcloud.uniapp.extapi.getStorageInfoSync as uni_getStorageInfoSync
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync
import io.dcloud.uniapp.extapi.navigateTo as uni_navigateTo
import io.dcloud.uniapp.extapi.removeStorage as uni_removeStorage
import io.dcloud.uniapp.extapi.removeStorageSync as uni_removeStorageSync
import io.dcloud.uniapp.extapi.setStorage as uni_setStorage
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync
import io.dcloud.uniapp.extapi.showModal as uni_showModal
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesAPIStorageStorage : BasePage {
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
        return createElementVNode("scroll-view", utsMapOf("class" to "page-scroll-view"), utsArrayOf(
            createElementVNode("view", null, utsArrayOf(
                createVNode(_component_page_head, utsMapOf("title" to _ctx.title), null, 8, utsArrayOf(
                    "title"
                )),
                createElementVNode("view", utsMapOf("class" to "uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-list"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell uni-list-cell-line"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-label"), "key")
                            )),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                createElementVNode("input", utsMapOf("class" to "uni-input", "type" to "text", "placeholder" to "请输入key", "name" to "key", "value" to _ctx.key, "maxlength" to "-1", "onInput" to _ctx.keyChange), null, 40, utsArrayOf(
                                    "value",
                                    "onInput"
                                ))
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-list-cell"), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-left"), utsArrayOf(
                                createElementVNode("view", utsMapOf("class" to "uni-label"), "value")
                            )),
                            createElementVNode("view", utsMapOf("class" to "uni-list-cell-db"), utsArrayOf(
                                createElementVNode("input", utsMapOf("class" to "uni-input", "type" to "text", "placeholder" to "请输入value", "name" to "data", "value" to if (UTSAndroid.`typeof`(_ctx.data) === "string") {
                                    _ctx.data as String
                                } else {
                                    JSON.stringify(_ctx.data)
                                }
                                , "maxlength" to "-1", "onInput" to _ctx.dataChange), null, 40, utsArrayOf(
                                    "value",
                                    "onInput"
                                ))
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-getStorageInfoASync", "type" to "primary", "onClick" to _ctx.getStorageInfo), " 获取存储概述信息-异步 ", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-getStorageInfoSync", "onClick" to _ctx.getStorageInfoSync), " 获取存储概述信息-同步 ", 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("text", utsMapOf("class" to "uni-list-cell-db-text"), toDisplayString(_ctx.storageInfo), 1),
                        createElementVNode("view", utsMapOf("class" to "uni-flex uni-row"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.strMock), " 填充字符串 ", 12, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.complexMock), " 填充复杂对象 ", 12, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-flex uni-row"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.numberMock), " 填充整型 ", 12, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.floatMock), " 填充浮点型 ", 12, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-flex uni-row"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.jsonLikeMock), " 填充json字符串 ", 12, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.longLikeMock), " 填充整数字符串 ", 12, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-flex uni-row"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.floatLikeMock), " 填充浮点字符串 ", 12, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("type" to "default", "style" to normalizeStyle(utsMapOf("width" to "50%")), "onClick" to _ctx.negativeLikeMock), " 填充负数字符串 ", 12, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-flex uni-row"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "default", "class" to "uni-btn btn-complexStaticTest", "style" to normalizeStyle(utsMapOf("width" to "100%")), "onClick" to _ctx.complexStaticTest), " 字面量读写测试 ", 12, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-padding-wrap"), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "uni-btn btn-setstorageAsync", "onClick" to _ctx.setStorage), " 存储数据-异步 ", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-getstorageAsync", "onClick" to _ctx.getStorage), "读取数据-异步", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-removeStorageInfoASync", "onClick" to _ctx.removeStorage), "移除数据-异步", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-clearStorageInfoASync", "onClick" to _ctx.clearStorage), "清理数据-异步", 8, utsArrayOf(
                                "onClick"
                            ))
                        )),
                        createElementVNode("view", utsMapOf("class" to "uni-btn-v"), utsArrayOf(
                            createElementVNode("button", utsMapOf("type" to "primary", "class" to "uni-btn btn-setstorageSync", "onClick" to _ctx.setStorageSync), " 存储数据-同步 ", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-getstorageSync", "onClick" to _ctx.getStorageSync), "读取数据-同步", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-removeStorageInfoSync", "onClick" to _ctx.removeStorageSync), " 移除数据-同步 ", 8, utsArrayOf(
                                "onClick"
                            )),
                            createElementVNode("button", utsMapOf("class" to "uni-btn btn-clearStorageInfoSync", "onClick" to _ctx.clearStorageSync), " 清理数据-同步 ", 8, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    ))
                )),
                createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.goto), "前往storage管理器", 8, utsArrayOf(
                    "onClick"
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var key: String by `$data`
    open var data: Any by `$data`
    open var apiGetData: Any? by `$data`
    open var storageInfo: String by `$data`
    open var staticComplexRet: Boolean by `$data`
    open var jest_saveUTSJSONObjectSyncResult: Number by `$data`
    open var jest_saveUTSJSONObjectAsyncResult: Number by `$data`
    open var jest_saveUTSJSONObjectArraySyncResult: Number by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "get/set/clearStorage", "key" to "", "data" to "" as Any, "apiGetData" to "" as Any?, "storageInfo" to "", "staticComplexRet" to false, "jest_saveUTSJSONObjectSyncResult" to 0, "jest_saveUTSJSONObjectAsyncResult" to 0, "jest_saveUTSJSONObjectArraySyncResult" to 0)
    }
    open var getStorageInfo = ::gen_getStorageInfo_fn
    open fun gen_getStorageInfo_fn() {
        uni_getStorageInfo(GetStorageInfoOptions(success = fun(res){
            this.apiGetData = res
            this.storageInfo = JSON.stringify(res)
        }
        ))
    }
    open var getStorageInfoSync = ::gen_getStorageInfoSync_fn
    open fun gen_getStorageInfoSync_fn() {
        try {
            val res = uni_getStorageInfoSync()
            this.apiGetData = res
            this.storageInfo = JSON.stringify(res)
        }
         catch (e: Throwable) {
            console.log(e, " at pages/API/storage/storage.uvue:138")
        }
    }
    open var jsonLikeMock = ::gen_jsonLikeMock_fn
    open fun gen_jsonLikeMock_fn() {
        this.key = "key_" + Math.random()
        this.data = JSON.stringify(object : UTSJSONObject() {
            var name = "james"
            var age: Number = 12
            var from = "american"
        })
    }
    open var longLikeMock = ::gen_longLikeMock_fn
    open fun gen_longLikeMock_fn() {
        this.key = "key_" + Math.random()
        this.data = "1234567890"
    }
    open var floatLikeMock = ::gen_floatLikeMock_fn
    open fun gen_floatLikeMock_fn() {
        this.key = "key_" + Math.random()
        this.data = "321456.1234567890"
    }
    open var negativeLikeMock = ::gen_negativeLikeMock_fn
    open fun gen_negativeLikeMock_fn() {
        this.key = "key_" + Math.random()
        this.data = "-321456"
    }
    open var strMock = ::gen_strMock_fn
    open fun gen_strMock_fn() {
        this.key = "key_" + Math.random()
        this.data = "测试字符串数据，长度为16个字符"
    }
    open var complexStaticTest = ::gen_complexStaticTest_fn
    open fun gen_complexStaticTest_fn() {
        uni_setStorageSync("key_complexStaticMock", object : UTSJSONObject() {
            var name = "张三"
            var age: Number = 12
        })
        var savedData = uni_getStorageSync("key_complexStaticMock")
        this.staticComplexRet = false
        if (savedData is UTSJSONObject) {
            if ((savedData as UTSJSONObject).getNumber("age") == 12) {
                this.staticComplexRet = true
                uni_showToast(ShowToastOptions(icon = "success", title = "测试通过"))
            }
        }
    }
    open var complexMock = ::gen_complexMock_fn
    open fun gen_complexMock_fn() {
        this.key = "key_" + Math.random()
        var jsonObj: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("jsonObj", "pages/API/storage/storage.uvue", 185, 13)) {
            var name = "张三"
            var age: Number = 12
            var classMate = utsArrayOf(
                object : UTSJSONObject() {
                    var id: Number = 1001
                    var name = "李四"
                },
                object : UTSJSONObject() {
                    var id: Number = 1002
                    var name = "jack ma"
                }
            )
        }
        this.data = jsonObj
    }
    open var numberMock = ::gen_numberMock_fn
    open fun gen_numberMock_fn() {
        this.key = "key_" + Math.random()
        this.data = 10011
    }
    open var floatMock = ::gen_floatMock_fn
    open fun gen_floatMock_fn() {
        this.key = "key_" + Math.random()
        this.data = 3.1415926535893384626
    }
    open var keyChange = ::gen_keyChange_fn
    open fun gen_keyChange_fn(e: InputEvent) {
        this.key = e.detail.value
    }
    open var dataChange = ::gen_dataChange_fn
    open fun gen_dataChange_fn(e: InputEvent) {
        this.data = e.detail.value
    }
    open var getStorage = ::gen_getStorage_fn
    open fun gen_getStorage_fn() {
        var key = this.key
        if (key.length == 0) {
            uni_showModal(ShowModalOptions(title = "读取数据失败", content = "key 不能为空", showCancel = false))
        } else {
            var that = this
            uni_getStorage(GetStorageOptions(key = key, success = fun(res){
                that.apiGetData = res.data
                var desc: String = UTSAndroid.`typeof`(this.apiGetData)
                if ("object" == desc) {
                    desc = desc + ": " + JSON.stringify(this.apiGetData)
                } else {
                    desc = desc + ": " + this.apiGetData
                }
                uni_showModal(ShowModalOptions(title = "读取数据成功", content = desc, showCancel = false))
            }
            , fail = fun(_){
                uni_showModal(ShowModalOptions(title = "读取数据失败", content = "找不到 key 对应的数据", showCancel = false))
            }
            ))
        }
    }
    open var getStorageSync = ::gen_getStorageSync_fn
    open fun gen_getStorageSync_fn() {
        var key = this.key
        if (key.length == 0) {
            uni_showModal(ShowModalOptions(title = "读取数据失败", content = "key 不能为空", showCancel = false))
        } else {
            this.apiGetData = uni_getStorageSync(key)
            var desc: String = UTSAndroid.`typeof`(this.apiGetData)
            if ("object" == desc) {
                desc = desc + ": " + JSON.stringify(this.apiGetData)
            } else {
                desc = desc + ": " + this.apiGetData
            }
            uni_showModal(ShowModalOptions(title = "读取数据成功", content = desc, showCancel = false))
        }
    }
    open var setStorage = ::gen_setStorage_fn
    open fun gen_setStorage_fn() {
        var key = this.key
        var data = this.data
        if (key.length == 0) {
            uni_showModal(ShowModalOptions(title = "保存数据失败", content = "key 不能为空", showCancel = false))
        } else {
            uni_setStorage(SetStorageOptions(key = key, data = data, success = fun(_){
                uni_showModal(ShowModalOptions(title = "存储数据成功", showCancel = false))
            }
            , fail = fun(_){
                uni_showModal(ShowModalOptions(title = "储存数据失败!", showCancel = false))
            }
            ))
        }
    }
    open var setStorageSync = ::gen_setStorageSync_fn
    open fun gen_setStorageSync_fn() {
        var key = this.key
        var data = this.data
        if (key.length == 0) {
            uni_showModal(ShowModalOptions(title = "保存数据失败", content = "key 不能为空", showCancel = false))
        } else {
            uni_setStorageSync(key, data)
            uni_showModal(ShowModalOptions(title = "存储数据成功", showCancel = false))
        }
    }
    open var removeStorage = ::gen_removeStorage_fn
    open fun gen_removeStorage_fn() {
        uni_removeStorage(RemoveStorageOptions(key = this.key, success = fun(_){
            uni_showModal(ShowModalOptions(title = "移除数据成功", showCancel = false))
        }
        , fail = fun(_){
            uni_showModal(ShowModalOptions(title = "移除数据失败", showCancel = false))
        }
        ))
    }
    open var removeStorageSync = ::gen_removeStorageSync_fn
    open fun gen_removeStorageSync_fn() {
        uni_removeStorageSync(this.key)
        uni_showModal(ShowModalOptions(title = "移除数据成功", showCancel = false))
    }
    open var clearStorage = ::gen_clearStorage_fn
    open fun gen_clearStorage_fn() {
        this.key = ""
        this.data = ""
        uni_clearStorage(ClearStorageOptions(success = fun(_) {
            uni_showModal(ShowModalOptions(title = "清除数据成功", showCancel = false))
        }
        , fail = fun(_) {
            uni_showModal(ShowModalOptions(title = "清除数据失败", showCancel = false))
        }
        ))
    }
    open var clearStorageSync = ::gen_clearStorageSync_fn
    open fun gen_clearStorageSync_fn() {
        this.key = ""
        this.data = ""
        uni_clearStorageSync()
        uni_showModal(ShowModalOptions(title = "清除数据成功", content = " ", showCancel = false))
    }
    open var jest_saveUTSJSONObject = ::gen_jest_saveUTSJSONObject_fn
    open fun gen_jest_saveUTSJSONObject_fn() {
        val key = "test_key_saveUTSJSONObject"
        uni_setStorageSync(key, object : UTSJSONObject() {
            var a = object : UTSJSONObject() {
                var b: Number = 1
            }
        })
        val dataSync = uni_getStorageSync(key) as UTSJSONObject
        val dataSyncA = dataSync["a"] as UTSJSONObject
        this.jest_saveUTSJSONObjectSyncResult = dataSyncA.get("b") as Number
        uni_getStorage(GetStorageOptions(key = key, success = fun(res){
            val dataAsync = res.data as UTSJSONObject
            val dataAsyncA = dataAsync["a"] as UTSJSONObject
            this.jest_saveUTSJSONObjectAsyncResult = dataAsyncA.get("b") as Number
            console.log("this.jest_saveUTSJSONObjectSyncResult: " + this.jest_saveUTSJSONObjectSyncResult, " at pages/API/storage/storage.uvue:392")
            console.log("this.jest_saveUTSJSONObjectAsyncResult: " + this.jest_saveUTSJSONObjectAsyncResult, " at pages/API/storage/storage.uvue:393")
        }
        ))
    }
    open var jest_saveUTSJSONObjectArray = ::gen_jest_saveUTSJSONObjectArray_fn
    open fun gen_jest_saveUTSJSONObjectArray_fn() {
        val key = "test_key_saveUTSJSONObjectArray"
        uni_setStorageSync(key, utsArrayOf<UTSJSONObject>(object : UTSJSONObject() {
            var a: Number = 1
        }))
        val dataSync = uni_getStorageSync(key) as UTSArray<UTSJSONObject>
        this.jest_saveUTSJSONObjectArraySyncResult = dataSync[0].get("a") as Number
    }
    open var goto = ::gen_goto_fn
    open fun gen_goto_fn() {
        uni_navigateTo(NavigateToOptions(url = "/pages/API/storage/storagemanage"))
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(), utsArrayOf(
                GenApp.styles
            ))
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
