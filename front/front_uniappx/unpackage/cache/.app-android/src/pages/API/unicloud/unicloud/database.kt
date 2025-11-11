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
import io.dcloud.uniapp.extapi.hideLoading as uni_hideLoading
import io.dcloud.uniapp.extapi.showLoading as uni_showLoading
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIUnicloudUnicloudDatabase : BasePage {
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
                createElementVNode("view", utsMapOf("class" to "uni-padding-wrap uni-common-mt"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbAdd), "新增单条数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbBatchAdd), "新增多条数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbUpdate), "更新数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbGet), "where传字符串获取数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbGetWithCommand), "where传对象获取数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbRemove), "删除数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbLookupInit), "初始化联表查询数据", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbLookup), "联表查询", 8, utsArrayOf(
                            "onClick"
                        )),
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.dbMultiSend), "合并查询查询", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var addId: String by `$data`
    open var batchAddIds: UTSArray<String> by `$data`
    open var batchAddinserted: Number by `$data`
    open var updateUpdated: Number by `$data`
    open var getData: UTSArray<UTSJSONObject> by `$data`
    open var getWithCommandData: UTSArray<UTSJSONObject> by `$data`
    open var removeDeleted: Number by `$data`
    open var lookupData: UTSArray<UTSJSONObject> by `$data`
    open var multiSendSuccessCount: Number by `$data`
    open var isUniTest: Boolean by `$data`
    open var dataTag: String by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "ClientDB", "addId" to "", "batchAddIds" to utsArrayOf<String>(), "batchAddinserted" to 0, "updateUpdated" to 0, "getData" to utsArrayOf<UTSJSONObject>(), "getWithCommandData" to utsArrayOf<UTSJSONObject>(), "removeDeleted" to 0, "lookupData" to utsArrayOf<UTSJSONObject>(), "multiSendSuccessCount" to 0, "isUniTest" to false, "dataTag" to computed<String>(fun(): String {
            return if (this.isUniTest) {
                "default-tag" + Date.now()
            } else {
                "default-tag"
            }
        }
        ))
    }
    open var notify = ::gen_notify_fn
    open fun gen_notify_fn(content: String, title: String) {
        if (!this.isUniTest) {
            uni_showModal(ShowModalOptions(title = title, content = content, showCancel = false))
        } else {
            console.log(title, content, " at pages/API/unicloud/unicloud/database.uvue:57")
        }
    }
    open var dbAdd = ::gen_dbAdd_fn
    open fun gen_dbAdd_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("type").add(let {
                    object : UTSJSONObject() {
                        var num: Number = 1
                        var tag = it.dataTag
                        var date = Date()
                    }
                }).then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:73")
                    this.addId = res.id
                    this.notify("\u65B0\u589E\u6210\u529F\uFF0Cid: " + res.id, "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbBatchAdd = ::gen_dbBatchAdd_fn
    open fun gen_dbBatchAdd_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("type").add(utsArrayOf(
                    let {
                        object : UTSJSONObject() {
                            var num: Number = 2
                            var tag = it.dataTag
                        }
                    },
                    let {
                        object : UTSJSONObject() {
                            var num: Number = 3
                            var tag = it.dataTag
                        }
                    }
                )).then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:98")
                    console.log("JSON.stringify(res.inserted)", JSON.stringify(res.inserted), " at pages/API/unicloud/unicloud/database.uvue:99")
                    this.batchAddIds = res.ids
                    this.batchAddinserted = res.inserted
                    this.notify("\u65B0\u589E\u6210\u529F\u6761\u6570" + res.inserted + ", id\u5217\u8868: " + res.ids.join(","), "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbGet = ::gen_dbGet_fn
    open fun gen_dbGet_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("type").where("tag == \"" + this.dataTag + "\"").field("num, tag").orderBy("num desc").skip(1).limit(2).get().then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:126")
                    this.getData = res.data
                    this.notify("\u83B7\u53D6\u6210\u529F\uFF0C\u53D6\u5230\u4E86" + res.data.length + "\u6761\u6570\u636E", "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbGetWithCommand = ::gen_dbGetWithCommand_fn
    open fun gen_dbGetWithCommand_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("type").where(let {
                    object : UTSJSONObject() {
                        var num = db.command.gt(1)
                        var tag = it.dataTag
                    }
                }).field("num, tag").orderBy("num desc").skip(1).limit(2).get().then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:153")
                    this.getWithCommandData = res.data
                    this.notify("\u83B7\u53D6\u6210\u529F\uFF0C\u53D6\u5230\u4E86" + res.data.length + "\u6761\u6570\u636E", "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbUpdate = ::gen_dbUpdate_fn
    open fun gen_dbUpdate_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("type").where("tag == \"" + this.dataTag + "\"").update(object : UTSJSONObject() {
                    var num: Number = 4
                }).then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:177")
                    this.updateUpdated = res.updated
                    this.notify("\u66F4\u65B0\u6210\u529F\uFF0C\u66F4\u65B0\u4E86" + res.updated + "\u6761\u6570\u636E", "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbRemove = ::gen_dbRemove_fn
    open fun gen_dbRemove_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("type").where("tag == \"" + this.dataTag + "\"").remove().then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:199")
                    this.removeDeleted = res.deleted
                    this.notify("\u5220\u9664\u6210\u529F\uFF0C\u5220\u6389\u4E86" + res.deleted + "\u6761\u6570\u636E", "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbLookupInit = ::gen_dbLookupInit_fn
    open fun gen_dbLookupInit_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                await(db.collection("local").where("tag == \"" + this.dataTag + "\"").remove().then(fun(): UTSPromise<UniCloudDBRemoveResult> {
                    return db.collection("foreign").where("tag == \"" + this.dataTag + "\"").remove()
                }
                ).then(fun(): UTSPromise<UniCloudDBBatchAddResult> {
                    return db.collection("local").add(utsArrayOf(
                        let {
                            object : UTSJSONObject() {
                                var id = "local_1"
                                var name = "local_1_name"
                                var tag = it.dataTag
                                var foreign_id = "foreign_1"
                            }
                        },
                        let {
                            object : UTSJSONObject() {
                                var id = "local_2"
                                var name = "local_2_name"
                                var tag = it.dataTag
                                var foreign_id = "foreign_2"
                            }
                        }
                    ))
                }
                ).then(fun(): UTSPromise<UniCloudDBBatchAddResult> {
                    return db.collection("foreign").add(utsArrayOf(
                        let {
                            object : UTSJSONObject() {
                                var id = "foreign_1"
                                var name = "foreign_1_name"
                                var tag = it.dataTag
                            }
                        },
                        let {
                            object : UTSJSONObject() {
                                var id = "foreign_2"
                                var name = "foreign_2_name"
                                var tag = it.dataTag
                            }
                        }
                    ))
                }
                ).then(fun(_): Unit {
                    uni_hideLoading()
                    this.notify("数据初始化成功", "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    console.error(err, " at pages/API/unicloud/unicloud/database.uvue:254")
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbLookup = ::gen_dbLookup_fn
    open fun gen_dbLookup_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                uni_showLoading(ShowLoadingOptions(title = "加载中..."))
                val db = uniCloud.databaseForJQL()
                val local = db.collection("local").where("tag == \"" + this.dataTag + "\"").getTemp()
                val foreign = db.collection("foreign").where("tag == \"" + this.dataTag + "\"").getTemp()
                await(db.collection(local, foreign).get().then(fun(res){
                    uni_hideLoading()
                    console.log(res, " at pages/API/unicloud/unicloud/database.uvue:274")
                    this.lookupData = res.data
                    this.notify("\u8054\u8868\u67E5\u8BE2\u6210\u529F\uFF0C\u53D6\u5230\u4E86" + res.data.length + "\u6761\u6570\u636E", "提示")
                }
                ).`catch`(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
    }
    open var dbMultiSend = ::gen_dbMultiSend_fn
    open fun gen_dbMultiSend_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val db = uniCloud.databaseForJQL()
                val temp1 = db.collection("type").where("tag == \"default-tag\"").getTemp()
                val temp2 = db.collection("type").where("tag == \"default-tag\"").getTemp()
                await(db.multiSend(temp1, temp2).then<Unit>(fun(res){
                    uni_hideLoading()
                    var successCount: Number = 0
                    run {
                        var i: Number = 0
                        while(i < res.dataList.length){
                            val item = res.dataList[i]
                            if (item.errCode == 0) {
                                console.log("\u7B2C" + i + "\u4E2A\u8BF7\u6C42\u67E5\u8BE2\u5230" + item.data!!.length + "\u6761\u6570\u636E", " at pages/API/unicloud/unicloud/database.uvue:301")
                                successCount++
                            } else {
                                console.error("\u7B2C" + i + "\u4E2A\u8BF7\u6C42\u67E5\u8BE2\u5931\u8D25\uFF0C\u9519\u8BEF\u4FE1\u606F\uFF1A" + item.data!!.length, " at pages/API/unicloud/unicloud/database.uvue:304")
                            }
                            i++
                        }
                    }
                    this.multiSendSuccessCount = successCount
                    this.notify("\u5408\u5E76\u67E5\u8BE2\u6210\u529F\uFF0C\u6210\u529F\u67E5\u8BE2\u7684\u8BED\u53E5\u6761\u6570\u4E3A\uFF1A" + successCount, "提示")
                }
                ).`catch`<Unit>(fun(err: Any?){
                    uni_hideLoading()
                    val error = err as UniCloudError
                    console.error(err, " at pages/API/unicloud/unicloud/database.uvue:313")
                    this.notify(error.errMsg, "错误")
                }
                ))
        })
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
