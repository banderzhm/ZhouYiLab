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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDb : VueComponent {
    constructor(__ins: ComponentInternalInstance) : super(__ins) {
        onCreated(fun() {
            this.pagination.current = this.pageCurrent
            this.pagination.size = this.pageSize
            this.`$watch`(fun(): Any {
                return utsArrayOf(
                    this.pageCurrent,
                    this.pageSize,
                    this.collection,
                    this.field,
                    this.getcount,
                    this.orderby,
                    this.where,
                    this.groupby,
                    this.groupField,
                    this.distinct
                )
            }
            , fun(newValue: UTSArray<Any>, oldValue: UTSArray<Any>){
                this.pagination.size = this.pageSize
                if (newValue[0] !== oldValue[0]) {
                    this.pagination.current = this.pageCurrent
                }
                if (this.loadtime == LOAD_MODE_MANUAL) {
                    return
                }
                var needReset = false
                run {
                    var i: Number = 2
                    while(i < newValue.length){
                        if (newValue[i] !== oldValue[i]) {
                            needReset = true
                            break
                        }
                        i++
                    }
                }
                if (needReset) {
                    this.clear()
                    this.reset()
                }
                this.get(null)
            }
            )
            if (!this.manual && this.loadtime == LOAD_MODE_AUTO && this.dataList.length == 0) {
                if (UTSAndroid.`typeof`(this.collection) == "string") {
                    val collectionString = this.collection as String
                    if (collectionString.length == 0) {
                        return
                    }
                } else if (UTSArray.isArray(this.collection)) {
                    val collectionArray = this.collection as UTSArray<Any>
                    if (collectionArray.length == 0) {
                        return
                    }
                }
                this.get(null)
            }
        }
        , __ins)
        onMounted(fun() {
            val uniCloudDBElement = this.`$refs`["UniCloudDB"] as UniCloudDBElement
            uniCloudDBElement.dataList = this.dataList
            uniCloudDBElement.onLoadData = this.loadData
            uniCloudDBElement.onLoadMore = this.loadMore
            uniCloudDBElement.onAdd = this.add
            uniCloudDBElement.onUpdate = this.update
            uniCloudDBElement.onRemove = this.remove
        }
        , __ins)
    }
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _ctx = this
        val _cache = this.`$`.renderCache
        return createElementVNode("uni-cloud-db-element", utsMapOf("ref" to "UniCloudDB"), utsArrayOf(
            renderSlot(_ctx.`$slots`, "default", GenNodeModulesDcloudioUniComponentsLibXUnicloudDbUnicloudDbSlotDataDefault(data = _ctx.dataList, loading = _ctx.loading, hasMore = _ctx.hasMore, pagination = _ctx.pagination, error = _ctx.error))
        ), 512)
    }
    open var collection: Any by `$props`
    open var field: String by `$props`
    open var orderby: String by `$props`
    open var where: Any by `$props`
    open var pageData: String by `$props`
    open var pageCurrent: Number by `$props`
    open var pageSize: Number by `$props`
    open var getcount: Boolean by `$props`
    open var gettree: Any by `$props`
    open var gettreepath: Boolean by `$props`
    open var startwith: String by `$props`
    open var limitlevel: Number by `$props`
    open var groupby: String by `$props`
    open var groupField: String by `$props`
    open var distinct: Boolean by `$props`
    open var pageIndistinct: Boolean by `$props`
    open var foreignKey: String by `$props`
    open var loadtime: String by `$props`
    open var manual: Boolean by `$props`
    open var ssrKey: String by `$props`
    open var dataList: UTSArray<UTSJSONObject> by `$data`
    open var loading: Boolean by `$data`
    open var hasMore: Boolean by `$data`
    open var isEnded: Boolean by `$data`
    open var pagination: UniCloudDBComponentPaginationType by `$data`
    open var error: UniCloudError? by `$data`
    @Suppress("USELESS_CAST")
    override fun data(): Map<String, Any?> {
        return utsMapOf("dataList" to utsArrayOf<UTSJSONObject>(), "loading" to false, "hasMore" to false, "isEnded" to false, "pagination" to UniCloudDBComponentPaginationType(current = 1, size = 20, count = 0), "error" to null as UniCloudError?)
    }
    open var loadData = ::gen_loadData_fn
    open fun gen_loadData_fn(options: UniCloudDBComponentLoadDataOptions): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                var clear = (options.clear != null && options.clear == true)
                if (clear == true) {
                    if (this.pageData == PAGE_MODE_REPLACE) {
                        this.clear()
                    }
                    this.reset()
                }
                await(this.get(options))
        })
    }
    open var loadMore = ::gen_loadMore_fn
    open fun gen_loadMore_fn() {
        if (this.isEnded || this.loading) {
            return
        }
        if (this.pageData == PAGE_MODE_ADD) {
            this.pagination.current++
        }
        this.get(null)
    }
    open var refresh = ::gen_refresh_fn
    open fun gen_refresh_fn() {
        this.clear()
        this.get(null)
    }
    open var clear = ::gen_clear_fn
    open fun gen_clear_fn() {
        this.isEnded = false
        this.dataList.length = 0
    }
    open var reset = ::gen_reset_fn
    open fun gen_reset_fn() {
        this.pagination.current = 1
    }
    open var get = ::gen_get_fn
    open fun gen_get_fn(options: UniCloudDBComponentLoadDataOptions?): UTSPromise<Unit> {
        return wrapUTSPromise(suspend {
                var loadAfterClear = false
                if (options != null && options.clear != null && options.clear == true) {
                    loadAfterClear = true
                }
                if (options != null && options.current != null) {
                    this.pagination.current = options.current!!
                }
                this.error = null
                this.loading = true
                await(this.getExec().then(fun(res: UniCloudDBGetResult){
                    val data = res.data
                    val count = res.count
                    this.isEnded = if ((count != null)) {
                        (this.pagination.current * this.pagination.size >= count)
                    } else {
                        (data.length < this.pageSize)
                    }
                    this.hasMore = !this.isEnded
                    if (this.getcount && count != null) {
                        this.pagination.count = count
                    }
                    this._dispatchEvent(EVENT_LOAD, data)
                    if (loadAfterClear || this.pageData == PAGE_MODE_REPLACE) {
                        this.dataList = data
                    } else {
                        this.dataList.push(*data.toTypedArray())
                    }
                    options?.success?.invoke(res)
                }
                ).`catch`(fun(err: Any?){
                    this._requestFail(err, null)
                    options?.fail?.invoke(err)
                }
                ).then(fun(){
                    this.loading = false
                    options?.complete?.invoke()
                }
                , fun(){
                    this.loading = false
                    options?.complete?.invoke()
                }
                ))
        })
    }
    open var add = ::gen_add_fn
    open fun gen_add_fn(value: UTSJSONObject, options: UniCloudDBComponentAddOptions) {
        this._needLoading(options.needLoading, options.loadingTitle)
        val db = uniCloud.databaseForJQL()
        db.collection(this._getMainCollection()).add(value).then<Unit>(fun(res: UniCloudDBAddResult){
            options.success?.invoke(res)
            this._isShowToast(options.showToast ?: false, options.toastTitle ?: "add success")
        }
        ).`catch`(fun(err){
            this._requestFail(err, options.fail)
        }
        ).then(fun(){
            this._requestComplete(options.complete, options.needLoading)
        }
        , fun(){
            this._requestComplete(options.complete, options.needLoading)
        }
        )
    }
    open var update = ::gen_update_fn
    open fun gen_update_fn(id: String, value: UTSJSONObject, options: UniCloudDBComponentUpdateOptions) {
        if (options.needConfirm == true) {
            uni_showModal(ShowModalOptions(title = options.confirmTitle, content = options.confirmContent, showCancel = true, success = fun(res){
                if (res.confirm) {
                    this._update(id, value, options)
                }
            }))
        } else {
            this._update(id, value, options)
        }
    }
    open var remove = ::gen_remove_fn
    open fun gen_remove_fn(id: Any, options: UniCloudDBComponentRemoveOptions) {
        val ids = if (UTSArray.isArray(id)) {
            (id as UTSArray<Any>)
        } else {
            utsArrayOf(
                id
            )
        }
        if (options.needConfirm == true) {
            uni_showModal(ShowModalOptions(title = options.confirmTitle, content = options.confirmContent, showCancel = true, success = fun(res){
                if (res.confirm) {
                    this._remove(ids, options)
                }
            }))
        } else {
            this._remove(ids, options)
        }
    }
    open var _update = ::gen__update_fn
    open fun gen__update_fn(id: String, value: UTSJSONObject, options: UniCloudDBComponentUpdateOptions) {
        this._needLoading(options.needLoading, options.loadingTitle)
        val db = uniCloud.databaseForJQL()
        db.collection(this._getMainCollection()).doc(id).update(value).then(fun(res){
            options.success?.invoke(res)
            this._isShowToast(options.showToast ?: false, options.toastTitle ?: "update success")
        }
        ).`catch`(fun(err: Any?){
            this._requestFail(err, options.fail)
        }
        ).then(fun(){
            this._requestComplete(options.complete, options.needLoading)
        }
        , fun(){
            this._requestComplete(options.complete, options.needLoading)
        }
        )
    }
    open var _remove = ::gen__remove_fn
    open fun gen__remove_fn(ids: UTSArray<Any>, options: UniCloudDBComponentRemoveOptions) {
        this._needLoading(options.needLoading, options.loadingTitle)
        val db = uniCloud.databaseForJQL()
        val dbCommand = db.command
        db.collection(this._getMainCollection()).where(object : UTSJSONObject() {
            var _id = dbCommand.`in`(ids)
        }).remove().then(fun(res){
            options.success?.invoke(res)
            if (this.pageData == PAGE_MODE_REPLACE) {
                this.refresh()
            } else {
                this._removeData(ids)
            }
        }
        ).`catch`(fun(err: Any?){
            this._requestFail(err, options.fail)
        }
        ).then(fun(){
            this._requestComplete(options.complete, options.needLoading)
        }
        , fun(){
            this._requestComplete(options.complete, options.needLoading)
        }
        )
    }
    open var _removeData = ::gen__removeData_fn
    open fun gen__removeData_fn(ids: UTSArray<Any>) {
        val il = ids.slice(0)
        val dl = this.dataList
        run {
            var i = dl.length - 1
            while(i >= 0){
                val index = il.indexOf(dl[i]["_id"])
                if (index >= 0) {
                    dl.splice(i, 1)
                    il.splice(index, 1)
                }
                i--
            }
        }
    }
    open var _isShowToast = ::gen__isShowToast_fn
    open fun gen__isShowToast_fn(showToast: Boolean, title: String) {
        if (showToast == true) {
            uni_showToast(ShowToastOptions(title = title))
        }
    }
    open var _needLoading = ::gen__needLoading_fn
    open fun gen__needLoading_fn(needLoading: Boolean?, title: String?) {
        if (needLoading == true) {
            uni_showLoading(ShowLoadingOptions(mask = true, title = title ?: ""))
        }
    }
    open var _requestFail = ::gen__requestFail_fn
    open fun gen__requestFail_fn(err: Any?, callback: FailCallback?) {
        callback?.invoke(err)
        this.error = err as UniCloudError
        this.`$emit`(EVENT_ERROR, err)
    }
    open var _requestComplete = ::gen__requestComplete_fn
    open fun gen__requestComplete_fn(callback: CompleteCallback?, needLoading: Boolean?) {
        callback?.invoke()
        if (needLoading == true) {
            uni_hideLoading()
        }
    }
    open var getExec = ::gen_getExec_fn
    open fun gen_getExec_fn(): UTSPromise<UniCloudDBGetResult> {
        return this.getTemp()
    }
    open var getTemp = ::gen_getTemp_fn
    open fun gen_getTemp_fn(): UTSPromise<UniCloudDBGetResult> {
        var db = uniCloud.databaseForJQL()
        var collection = if (UTSArray.isArray(this.collection)) {
            db.collection(*(this.collection as UTSArray<Any>).toTypedArray())
        } else {
            db.collection(this.collection)
        }
        var filter: UniCloudDBFilter? = null
        if (this.foreignKey.length > 0) {
            filter = collection.foreignKey(this.foreignKey)
        }
        if (UTSAndroid.`typeof`(this.where) == "string") {
            val whereString = this.where as String
            if (whereString.length > 0) {
                filter = if ((filter != null)) {
                    filter.where(this.where as String)
                } else {
                    collection.where(this.where as String)
                }
            }
        } else if (UTSAndroid.`typeof`(this.where) == "object") {
            filter = if ((filter != null)) {
                filter.where(this.where)
            } else {
                collection.where(this.where)
            }
        }
        var query: UniCloudDBQuery? = null
        if (this.field.length > 0) {
            query = if ((filter != null)) {
                filter.field(this.field)
            } else {
                collection.field(this.field)
            }
        }
        if (this.groupby.length > 0) {
            if (query != null) {
                query = query.groupBy(this.groupby)
            } else if (filter != null) {
                query = filter.groupBy(this.groupby)
            }
        }
        if (this.groupField.length > 0) {
            if (query != null) {
                query = query.groupField(this.groupField)
            } else if (filter != null) {
                query = filter.groupField(this.groupField)
            }
        }
        if (this.distinct == true) {
            if (query != null) {
                query = query.distinct(this.field)
            } else if (filter != null) {
                query = filter.distinct(this.field)
            }
        }
        if (this.orderby.length > 0) {
            if (query != null) {
                query = query.orderBy(this.orderby)
            } else if (filter != null) {
                query = filter.orderBy(this.orderby)
            } else {
                query = collection.orderBy(this.orderby)
            }
        }
        val size = this.pagination.size
        val current = this.pagination.current
        val skipSize = size * (current - 1)
        if (query != null) {
            query = query.skip(skipSize).limit(size)
        } else if (filter != null) {
            query = filter.skip(skipSize).limit(size)
        } else {
            query = collection.skip(skipSize).limit(size)
        }
        val getOptions: UTSJSONObject = UTSJSONObject()
        val treeOptions: UTSJSONObject = let {
            object : UTSJSONObject() {
                var limitLevel = it.limitlevel
                var startWith = it.startwith
            }
        }
        if (this.getcount == true) {
            getOptions["getCount"] = this.getcount
        }
        if (UTSAndroid.`typeof`(this.gettree) == "string") {
            val getTreeString = this.gettree as String
            if (getTreeString.length > 0) {
                getOptions["getTree"] = treeOptions
            }
        } else if (UTSAndroid.`typeof`(this.gettree) == "object") {
            getOptions["getTree"] = treeOptions
        }
        if (this.gettreepath == true) {
            getOptions["getTreePath"] = treeOptions
        }
        return query.get(getOptions)
    }
    open var _getMainCollection = ::gen__getMainCollection_fn
    open fun gen__getMainCollection_fn(): String {
        if (UTSAndroid.`typeof`(this.collection) === "string") {
            return (this.collection as String).split(",")[0]
        }
        if (UTSArray.isArray(this.collection)) {
            val array = this.collection as UTSArray<Any>
            val index = array[0] as UTSJSONObject
            val collection = index.getString("\$db[0].\$param[0]")
            return collection ?: ""
        }
        return ""
    }
    open var _dispatchEvent = ::gen__dispatchEvent_fn
    open fun gen__dispatchEvent_fn(type: String, data: UTSArray<UTSJSONObject>) {
        this.`$emit`(type, data, this.isEnded, let {
            object : UTSJSONObject() {
                var current = it.pagination.current
                var size = it.pagination.size
                var count = it.pagination.count
            }
        })
    }
    companion object {
        init {
            registerComponent()
        }
        var name = "UniCloudDB"
        fun registerComponent() {
            io.dcloud.uniapp.UniSDKEngine.registerUniComponent("uni-cloud-db-element", io.dcloud.uniapp.ui.component.ViewComponent::class.java, UniCloudDBElement::class.java)
        }
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf())
        }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf("collection" to utsMapOf("type" to utsArrayOf(
            "String",
            "Object"
        ), "default" to ""), "field" to utsMapOf("type" to "String", "default" to ""), "orderby" to utsMapOf("type" to "String", "default" to ""), "where" to utsMapOf("type" to utsArrayOf(
            "String",
            "Object"
        ), "default" to ""), "pageData" to utsMapOf("type" to "String", "default" to "add"), "pageCurrent" to utsMapOf("type" to "Number", "default" to 1), "pageSize" to utsMapOf("type" to "Number", "default" to 20), "getcount" to utsMapOf("type" to "Boolean", "default" to false), "gettree" to utsMapOf("type" to utsArrayOf(
            "String",
            "Object"
        ), "default" to ""), "gettreepath" to utsMapOf("type" to "Boolean", "default" to false), "startwith" to utsMapOf("type" to "String", "default" to ""), "limitlevel" to utsMapOf("type" to "Number", "default" to 10), "groupby" to utsMapOf("type" to "String", "default" to ""), "groupField" to utsMapOf("type" to "String", "default" to ""), "distinct" to utsMapOf("type" to "Boolean", "default" to false), "pageIndistinct" to utsMapOf("type" to "Boolean", "default" to false), "foreignKey" to utsMapOf("type" to "String", "default" to ""), "loadtime" to utsMapOf("type" to "String", "default" to "auto"), "manual" to utsMapOf("type" to "Boolean", "default" to false), "ssrKey" to utsMapOf("type" to "String", "default" to "")))
        var propsNeedCastKeys = utsArrayOf(
            "collection",
            "field",
            "orderby",
            "where",
            "pageData",
            "pageCurrent",
            "pageSize",
            "getcount",
            "gettree",
            "gettreepath",
            "startwith",
            "limitlevel",
            "groupby",
            "groupField",
            "distinct",
            "pageIndistinct",
            "foreignKey",
            "loadtime",
            "manual",
            "ssrKey"
        )
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
