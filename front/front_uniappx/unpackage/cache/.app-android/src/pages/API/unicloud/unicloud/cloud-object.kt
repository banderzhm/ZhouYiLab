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
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIUnicloudUnicloudCloudObject : BasePage {
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
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.addTodo), "添加Todo", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.addTodoWithGeneric), " 添加Todo传入泛型 ", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.randomFail), "随机触发失败重试", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.fail), "云对象失败调用", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.failWithNumberErrCode), " 云对象数字错误码 ", 8, utsArrayOf(
                            "onClick"
                        ))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-btn-v uni-common-mt"), utsArrayOf(
                        createElementVNode("button", utsMapOf("type" to "primary", "onClick" to _ctx.success), "云对象成功调用", 8, utsArrayOf(
                            "onClick"
                        ))
                    ))
                ))
            ))
        ))
    }
    open var title: String by `$data`
    open var todoTitle: String by `$data`
    open var todoContent: String by `$data`
    open var returnTodoTitle: String by `$data`
    open var returnTodoContent: String by `$data`
    open var genericDemoReturnTodoTitle: String by `$data`
    open var genericDemoReturnTodoContent: String by `$data`
    open var failErrCode: String by `$data`
    open var failErrSubject: String by `$data`
    open var failErrDetailTips: String by `$data`
    open var failNumberErrCode: Number by `$data`
    open var successErrCode: Number by `$data`
    open var isUniTest: Boolean by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("title" to "请求云对象", "todoTitle" to "学习编程", "todoContent" to "熟悉uts语法", "returnTodoTitle" to "", "returnTodoContent" to "", "genericDemoReturnTodoTitle" to "", "genericDemoReturnTodoContent" to "", "failErrCode" to "", "failErrSubject" to "", "failErrDetailTips" to "", "failNumberErrCode" to 0, "successErrCode" to -1, "isUniTest" to false)
    }
    open var notify = ::gen_notify_fn
    open fun gen_notify_fn(content: String, title: String) {
        if (!this.isUniTest) {
            uni_showModal(ShowModalOptions(title = title, content = content, showCancel = false))
        } else {
            console.log(title, content, " at pages/API/unicloud/unicloud/cloud-object.uvue:65")
        }
    }
    open var addTodo = ::gen_addTodo_fn
    open fun gen_addTodo_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val todo = uniCloud.importObject("todo", UniCloudImportObjectOptions(customUI = this.isUniTest), GenCloudObjTodo::class.java)
                val title = this.todoTitle
                val content = this.todoContent
                await(todo.add<UTSJSONObject>(title, content).then(fun(res: UTSJSONObject){
                    this.returnTodoTitle = res["title"] as String
                    this.returnTodoContent = res["content"] as String
                    this.notify(res["showMessage"] as String, "提示")
                }
                ).`catch`(fun(err: Any?){
                    console.log(err, " at pages/API/unicloud/unicloud/cloud-object.uvue:79")
                    val error = err as UniCloudError
                    console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:81")
                }
                ))
        })
    }
    open var addTodoWithGeneric = ::gen_addTodoWithGeneric_fn
    open fun gen_addTodoWithGeneric_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                open class AddTodoResult (
                    @JsonNotNull
                    open var title: String,
                    @JsonNotNull
                    open var content: String,
                    @JsonNotNull
                    open var showMessage: String,
                ) : UTSObject(), IUTSSourceMap {
                    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
                        return UTSSourceMapPosition("AddTodoResult", "pages/API/unicloud/unicloud/cloud-object.uvue", 85, 14)
                    }
                }
                val todo = uniCloud.importObject("todo", UniCloudImportObjectOptions(customUI = this.isUniTest), GenCloudObjTodo::class.java)
                val title = this.todoTitle
                val content = this.todoContent
                await(todo.add<AddTodoResult>(title, content).then(fun(res: AddTodoResult){
                    this.genericDemoReturnTodoTitle = res.title
                    this.genericDemoReturnTodoContent = res.content
                    this.notify(res.showMessage, "提示")
                }
                ).`catch`(fun(err: Any?){
                    console.log(err, " at pages/API/unicloud/unicloud/cloud-object.uvue:100")
                    val error = err as UniCloudError
                    console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:102")
                }
                ))
        })
    }
    open var randomFail = ::gen_randomFail_fn
    open fun gen_randomFail_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val todoObj = uniCloud.importObject("todo", UniCloudImportObjectOptions(errorOptions = UniCloudImportObjectErrorOptions(retry = true)), GenCloudObjTodo::class.java)
                await(todoObj.randomFail().then(fun(res: UTSJSONObject){
                    this.notify(res["showMessage"] as String, "提示")
                }
                ).`catch`(fun(err: Any?){
                    val error = err as UniCloudError
                    console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:115")
                }
                ))
        })
    }
    open var fail = ::gen_fail_fn
    open fun gen_fail_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val todo = uniCloud.importObject("todo", UniCloudImportObjectOptions(customUI = this.isUniTest), GenCloudObjTodo::class.java)
                await(todo.fail().then(fun(res: UTSJSONObject){
                    this.notify("todo.fail应调用失败，此处错误的触发了成功回调", "错误")
                    console.log("todo.fail: ", res, " at pages/API/unicloud/unicloud/cloud-object.uvue:124")
                }
                ).`catch`(fun(err: Any?){
                    val error = err as UniCloudError
                    this.failErrCode = error.errCode as String
                    this.failErrSubject = error.errSubject as String
                    val detail = error.detail
                    if (detail != null && detail["tips"] != null) {
                        this.failErrDetailTips = detail["tips"] as String
                    }
                    console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:133")
                }
                ))
        })
    }
    open var failWithNumberErrCode = ::gen_failWithNumberErrCode_fn
    open fun gen_failWithNumberErrCode_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val todo = uniCloud.importObject("todo", UniCloudImportObjectOptions(customUI = this.isUniTest), GenCloudObjTodo::class.java)
                await(todo.failWithNumberErrCode().then(fun(res: UTSJSONObject){
                    this.notify("todo.fail应调用失败，此处错误的触发了成功回调", "错误")
                    console.log("todo.fail: ", res, " at pages/API/unicloud/unicloud/cloud-object.uvue:142")
                }
                ).`catch`(fun(err: Any?){
                    val error = err as UniCloudError
                    this.failNumberErrCode = error.errCode as Number
                    console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:146")
                }
                ))
        })
    }
    open var success = ::gen_success_fn
    open fun gen_success_fn(): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                val todo = uniCloud.importObject("todo", UniCloudImportObjectOptions(customUI = this.isUniTest), GenCloudObjTodo::class.java)
                await(todo.success().then(fun(res: UTSJSONObject){
                    this.successErrCode = res["errCode"] as Number
                    this.notify(res["showMessage"] as String, "提示")
                }
                ).`catch`(fun(err: Any?){
                    val error = err as UniCloudError
                    console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:158")
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
