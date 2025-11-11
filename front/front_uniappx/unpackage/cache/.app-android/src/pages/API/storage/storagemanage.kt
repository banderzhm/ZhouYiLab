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
import io.dcloud.uniapp.extapi.clearStorageSync as uni_clearStorageSync
import io.dcloud.uniapp.extapi.getStorageInfoSync as uni_getStorageInfoSync
import io.dcloud.uniapp.extapi.getStorageSync as uni_getStorageSync
import io.dcloud.uniapp.extapi.removeStorageSync as uni_removeStorageSync
import io.dcloud.uniapp.extapi.setStorageSync as uni_setStorageSync
import io.dcloud.uniapp.extapi.showModal as uni_showModal
open class GenPagesAPIStorageStoragemanage : BasePage {
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
    open var editValue: String
        get() {
            return unref(this.`$exposed`["editValue"]) as String
        }
        set(value) {
            setRefValue(this.`$exposed`, "editValue", value)
        }
    open var editValueType: String
        get() {
            return unref(this.`$exposed`["editValueType"]) as String
        }
        set(value) {
            setRefValue(this.`$exposed`, "editValueType", value)
        }
    open var getStorageList: () -> StorageList
        get() {
            return unref(this.`$exposed`["getStorageList"]) as () -> StorageList
        }
        set(value) {
            setRefValue(this.`$exposed`, "getStorageList", value)
        }
    open var setTestMode: (kVal: Boolean) -> Unit
        get() {
            return unref(this.`$exposed`["setTestMode"]) as (kVal: Boolean) -> Unit
        }
        set(value) {
            setRefValue(this.`$exposed`, "setTestMode", value)
        }
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesAPIStorageStoragemanage, _arg1: SetupContext) -> Any? = fun(__props, ref1): Any? {
            var __expose = ref1.expose
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesAPIStorageStoragemanage
            val _cache = __ins.renderCache
            val storageList = ref(utsArrayOf<StorageItem>())
            val newKey = ref("")
            val newValue = ref("")
            val isEditing = ref(false)
            val editIndex = ref(-1)
            val detailItem = ref(StorageItem(key = "", value = "", type = ""))
            val editKey = ref("")
            val editValue = ref("")
            val editValueType = ref("Number")
            val showDetailDialog = ref(false)
            val showEditDialog = ref(false)
            val valueTypeOptions = utsArrayOf(
                "String",
                "Number",
                "Boolean",
                "Object",
                "Array"
            )
            val valueTypeDefaultMap = Map<String, String>()
            valueTypeDefaultMap.set("String", "")
            valueTypeDefaultMap.set("Number", "1")
            valueTypeDefaultMap.set("Boolean", "true")
            valueTypeDefaultMap.set("Object", "{\"name\": \"\u5F20\u4E09\",\"age\": 12}")
            valueTypeDefaultMap.set("Array", "[1, \"hello\", true, { \"key\": \"value\" }]")
            val isTestMode = ref(false)
            fun gen_getStorageList_fn(): StorageList {
                val list: StorageList = utsArrayOf()
                val storageInfo = uni_getStorageInfoSync()
                storageInfo.keys.forEach(fun(key: String){
                    val value = uni_getStorageSync(key)
                    var strValue: String? = null
                    var typeStr: String = UTSAndroid.`typeof`(value)
                    if (value != null) {
                        if (typeStr == "object") {
                            val jsonStr = JSON.stringify(value)
                            strValue = jsonStr
                            if (UTSArray.isArray(UTSAndroid.consoleDebugError(JSON.parse(jsonStr), " at pages/API/storage/storagemanage.uvue:129"))) {
                                typeStr = "Array"
                            } else {
                                typeStr = "Object"
                            }
                        } else if (typeStr == "boolean") {
                            strValue = if (value == true) {
                                "true"
                            } else {
                                "false"
                            }
                            typeStr = "Boolean"
                        } else if (typeStr == "number") {
                            strValue = value.toString()
                            typeStr = "Number"
                        } else {
                            strValue = value.toString()
                            typeStr = "String"
                        }
                    }
                    if (strValue != null) {
                        list.push(StorageItem(key = key, value = strValue, type = typeStr))
                    }
                }
                )
                return list
            }
            val getStorageList = ::gen_getStorageList_fn
            fun gen_setStorage_fn(key: String, value: Any) {
                try {
                    uni_setStorageSync(key, value)
                }
                 catch (e: Throwable) {
                    console.error("Storage set error:", e, " at pages/API/storage/storagemanage.uvue:160")
                }
            }
            val setStorage = ::gen_setStorage_fn
            fun gen_removeStorage_fn(key: String) {
                try {
                    uni_removeStorageSync(key)
                }
                 catch (e: Throwable) {
                    console.error("Storage remove error:", e, " at pages/API/storage/storagemanage.uvue:167")
                }
            }
            val removeStorage = ::gen_removeStorage_fn
            fun gen_clearStorage_fn() {
                try {
                    uni_clearStorageSync()
                }
                 catch (e: Throwable) {
                    console.error("Storage clear error:", e, " at pages/API/storage/storagemanage.uvue:174")
                }
            }
            val clearStorage = ::gen_clearStorage_fn
            fun gen_getStorage_fn(key: String): String? {
                try {
                    val value = uni_getStorageSync(key)
                    return if (value != null) {
                        value.toString()
                    } else {
                        null
                    }
                }
                 catch (e: Throwable) {
                    console.error("Storage get error:", e, " at pages/API/storage/storagemanage.uvue:182")
                    return null
                }
            }
            val getStorage = ::gen_getStorage_fn
            fun gen_refreshList_fn() {
                val list = getStorageList()
                console.log("list: ", list, " at pages/API/storage/storagemanage.uvue:189")
                if (!isEditing.value && editKey.value != "") {
                    val idx = list.findIndex(fun(item): Boolean {
                        return item.key === editKey.value
                    }
                    )
                    if (idx > 0) {
                        val spliced = list.splice(idx, 1)
                        if (spliced.length > 0) {
                            list.unshift(spliced[0])
                        }
                    }
                }
                storageList.value = list
            }
            val refreshList = ::gen_refreshList_fn
            fun gen_truncate_fn(value: String): String {
                if (value.length > 100) {
                    return value.slice(0, 100) + "..."
                }
                return value
            }
            val truncate = ::gen_truncate_fn
            fun gen_showDetail_fn(item: StorageItem) {
                detailItem.value = item
                showDetailDialog.value = true
            }
            val showDetail = ::gen_showDetail_fn
            fun gen_closeDetail_fn() {
                showDetailDialog.value = false
            }
            val closeDetail = ::gen_closeDetail_fn
            fun gen_openEditDialogNew_fn() {
                editKey.value = ""
                editValueType.value = "String"
                editValue.value = valueTypeDefaultMap.get("String") ?: ""
                isEditing.value = false
                editIndex.value = -1
                showEditDialog.value = true
            }
            val openEditDialogNew = ::gen_openEditDialogNew_fn
            fun gen_openEditDialogEdit_fn(item: StorageItem, index: Number) {
                editKey.value = item.key
                editValue.value = item.value
                isEditing.value = true
                editIndex.value = index
                editValueType.value = if (valueTypeOptions.indexOf(item.type) >= 0) {
                    item.type
                } else {
                    "String"
                }
                showEditDialog.value = true
                closeDetail()
            }
            val openEditDialogEdit = ::gen_openEditDialogEdit_fn
            fun gen_saveEdit_fn() {
                if (!isTestMode.value && (editKey.value.trim() === "" || editValue.value.trim() === "")) {
                    uni_showModal(ShowModalOptions(title = "提示", content = "Key 和 Value 都不能为空", showCancel = false))
                    return
                }
                var storeValue: Any
                when (editValueType.value) {
                    "Number" -> 
                        storeValue = parseFloat(editValue.value)
                    "Boolean" -> 
                        storeValue = (editValue.value === "true" || editValue.value === "1")
                    "Object" -> 
                        try {
                            val obj = UTSAndroid.consoleDebugError(JSON.parse(editValue.value), " at pages/API/storage/storagemanage.uvue:252")
                            storeValue = obj as UTSJSONObject
                        }
                         catch (e: Throwable) {
                            storeValue = UTSJSONObject()
                        }
                    "Array" -> 
                        try {
                            val arr = UTSAndroid.consoleDebugError(JSON.parse(editValue.value), " at pages/API/storage/storagemanage.uvue:260") as UTSArray<Any>
                            storeValue = arr
                        }
                         catch (e: Throwable) {
                            storeValue = utsArrayOf<Any>()
                        }
                    else -> 
                        storeValue = editValue.value
                }
                if (editIndex.value >= 0) {
                    val oldItem = storageList.value[editIndex.value]
                    if (oldItem.key != editKey.value) {
                        removeStorage(oldItem.key)
                    }
                    setStorage(editKey.value, storeValue)
                } else {
                    setStorage(editKey.value, storeValue)
                }
                refreshList()
                isEditing.value = false
                editIndex.value = -1
                editKey.value = ""
                editValue.value = ""
                editValueType.value = "String"
                showEditDialog.value = false
            }
            val saveEdit = ::gen_saveEdit_fn
            fun gen_closeEdit_fn() {
                showEditDialog.value = false
            }
            val closeEdit = ::gen_closeEdit_fn
            fun gen_handleDelete_fn(index: Number) {
                if (index >= 0 && index < storageList.value.length) {
                    val item = storageList.value[index]
                    removeStorage(item.key)
                    refreshList()
                    if (isEditing.value && editIndex.value == index) {
                        isEditing.value = false
                        editIndex.value = -1
                        editKey.value = ""
                        editValue.value = ""
                    }
                }
            }
            val handleDelete = ::gen_handleDelete_fn
            fun gen_confirmDelete_fn(index: Number) {
                if (!isTestMode.value) {
                    uni_showModal(ShowModalOptions(title = "确认操作", content = "确定要删除该项吗？", showCancel = true, cancelText = "取消", confirmText = "确定", success = fun(res){
                        if (res.confirm) {
                            showDetailDialog.value = false
                            handleDelete(index)
                        }
                    }))
                } else {
                    showDetailDialog.value = false
                    handleDelete(index)
                }
            }
            val confirmDelete = ::gen_confirmDelete_fn
            fun gen_handleClear_fn() {
                clearStorage()
                refreshList()
                isEditing.value = false
                editIndex.value = -1
                editKey.value = ""
                editValue.value = ""
            }
            val handleClear = ::gen_handleClear_fn
            fun gen_confirmClear_fn() {
                if (!isTestMode.value) {
                    uni_showModal(ShowModalOptions(title = "确认操作", content = "确定要清空所有数据吗？", showCancel = true, cancelText = "取消", confirmText = "确定", success = fun(res){
                        if (res.confirm) {
                            showDetailDialog.value = false
                            handleClear()
                        }
                    }))
                } else {
                    showDetailDialog.value = false
                    handleClear()
                }
            }
            val confirmClear = ::gen_confirmClear_fn
            fun gen_getDetailIndex_fn(): Number {
                return storageList.value.findIndex(fun(item): Boolean {
                    return item.key === detailItem.value.key
                }
                )
            }
            val getDetailIndex = ::gen_getDetailIndex_fn
            fun gen_onValueTypeChange_fn(e: UniRadioGroupChangeEvent) {
                val type = e.detail.value
                editValueType.value = type
                if (valueTypeDefaultMap.has(type)) {
                    editValue.value = valueTypeDefaultMap.get(type) ?: ""
                } else {
                    editValue.value = ""
                }
            }
            val onValueTypeChange = ::gen_onValueTypeChange_fn
            onLoad(fun(_options){
                refreshList()
            }
            )
            fun gen_setTestMode_fn(kVal: Boolean) {
                isTestMode.value = kVal
            }
            val setTestMode = ::gen_setTestMode_fn
            __expose(utsMapOf("editValue" to editValue, "editValueType" to editValueType, "getStorageList" to getStorageList, "setTestMode" to setTestMode))
            return fun(): Any? {
                val _component_radio = resolveComponent("radio")
                val _component_radio_group = resolveComponent("radio-group")
                return createElementVNode("view", utsMapOf("class" to "container"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "header"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-title-text"), "Storage管理器"),
                        createElementVNode("button", utsMapOf("class" to "btn btn-create", "type" to "primary", "onClick" to openEditDialogNew), "新建"),
                        if (unref(storageList).length > 0) {
                            createElementVNode("button", utsMapOf("key" to 0, "class" to "btn btn-clear", "type" to "default", "onClick" to confirmClear), "清空所有")
                        } else {
                            createCommentVNode("v-if", true)
                        }
                    )),
                    if (unref(storageList).length > 0) {
                        createElementVNode("list-view", utsMapOf("key" to 0, "class" to "list-view"), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(unref(storageList), fun(item, index, __index, _cached): Any {
                                return createElementVNode("list-item", utsMapOf("key" to item.key, "onClick" to fun(){
                                    showDetail(item)
                                }), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "item-block"), utsArrayOf(
                                        createElementVNode("view", utsMapOf("class" to "item-row"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "item-label"), "Key："),
                                            createElementVNode("text", utsMapOf("class" to "item-key"), toDisplayString(item.key), 1)
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "item-row"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "item-label"), "Data："),
                                            createElementVNode("text", utsMapOf("class" to "item-key"), toDisplayString(truncate(item.value)), 1)
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "item-row"), utsArrayOf(
                                            createElementVNode("text", utsMapOf("class" to "item-label"), "Type："),
                                            createElementVNode("text", utsMapOf("class" to "item-key"), toDisplayString(item.type), 1)
                                        )),
                                        createElementVNode("view", utsMapOf("class" to "item-row item-actions-row"), utsArrayOf(
                                            createElementVNode("button", utsMapOf("class" to "btn btn-delete", "type" to "default", "onClick" to withModifiers(fun(){
                                                confirmDelete(index)
                                            }, utsArrayOf(
                                                "stop"
                                            ))), "删除", 8, utsArrayOf(
                                                "onClick"
                                            )),
                                            createElementVNode("button", utsMapOf("class" to "btn btn-edit", "type" to "primary", "onClick" to withModifiers(fun(){
                                                openEditDialogEdit(item, index)
                                            }, utsArrayOf(
                                                "stop"
                                            ))), "编辑", 8, utsArrayOf(
                                                "onClick"
                                            ))
                                        ))
                                    ))
                                ), 8, utsArrayOf(
                                    "onClick"
                                ))
                            }), 128)
                        ))
                    } else {
                        createElementVNode("view", utsMapOf("key" to 1, "class" to "uni-center"), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "uni-hello-text"), "暂无数据")
                        ))
                    }
                    ,
                    if (isTrue(unref(showDetailDialog))) {
                        createElementVNode("view", utsMapOf("key" to 2, "class" to "dialog-mask", "onClick" to closeDetail), utsArrayOf(
                            createElementVNode("view", utsMapOf("class" to "dialog-content", "onClick" to withModifiers(fun(){}, utsArrayOf(
                                "stop"
                            ))), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "dialog-title"), "详情"),
                                createElementVNode("view", utsMapOf("class" to "detail-row"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "item-label"), "Key："),
                                    createElementVNode("text", utsMapOf("class" to "item-key"), toDisplayString(unref(detailItem).key), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "detail-row"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "item-label"), "Data："),
                                    createElementVNode("text", utsMapOf("class" to "item-key uni-list-cell-db-text"), toDisplayString(unref(detailItem).value), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "detail-row"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "item-label"), "Type："),
                                    createElementVNode("text", utsMapOf("class" to "item-key"), toDisplayString(unref(detailItem).type), 1)
                                )),
                                createElementVNode("view", utsMapOf("class" to "uni-common-mt popup-actions"), utsArrayOf(
                                    createElementVNode("button", utsMapOf("class" to "btn mr-20", "type" to "primary", "onClick" to fun(){
                                        openEditDialogEdit(unref(detailItem), getDetailIndex())
                                    }), "编辑", 8, utsArrayOf(
                                        "onClick"
                                    )),
                                    createElementVNode("button", utsMapOf("class" to "btn mr-20", "type" to "warn", "onClick" to fun(){
                                        confirmDelete(getDetailIndex())
                                    }), "删除", 8, utsArrayOf(
                                        "onClick"
                                    )),
                                    createElementVNode("button", utsMapOf("class" to "btn", "onClick" to closeDetail), "关闭")
                                ))
                            ), 8, utsArrayOf(
                                "onClick"
                            ))
                        ))
                    } else {
                        createCommentVNode("v-if", true)
                    }
                    ,
                    withDirectives(createElementVNode("view", utsMapOf("class" to "dialog-mask", "onClick" to closeEdit), utsArrayOf(
                        createElementVNode("view", utsMapOf("class" to "dialog-content", "onClick" to withModifiers(fun(){}, utsArrayOf(
                            "stop"
                        ))), utsArrayOf(
                            createElementVNode("text", utsMapOf("class" to "dialog-title"), toDisplayString(if (unref(isEditing)) {
                                "编辑"
                            } else {
                                "新建"
                            }
                            ), 1),
                            createElementVNode("view", utsMapOf("class" to "edit-row"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "edit-label"), "Key"),
                                createElementVNode("input", utsMapOf("modelValue" to unref(editKey), "onInput" to fun(`$event`: InputEvent){
                                    trySetRefValue(editKey, `$event`.detail.value)
                                }
                                , "placeholder" to "请输入key", "class" to "edit-input"), null, 40, utsArrayOf(
                                    "modelValue"
                                ))
                            )),
                            createElementVNode("view", utsMapOf("class" to "edit-row"), utsArrayOf(
                                createElementVNode("text", utsMapOf("class" to "edit-label"), "Value"),
                                createElementVNode("textarea", utsMapOf("modelValue" to unref(editValue), "onInput" to fun(`$event`: InputEvent){
                                    trySetRefValue(editValue, `$event`.detail.value)
                                }
                                , "placeholder" to "请输入value", "class" to "edit-textarea"), null, 40, utsArrayOf(
                                    "modelValue"
                                ))
                            )),
                            if (isTrue(!unref(isEditing))) {
                                createElementVNode("view", utsMapOf("key" to 0, "class" to "edit-row"), utsArrayOf(
                                    createElementVNode("text", utsMapOf("class" to "edit-label"), "类型"),
                                    createVNode(_component_radio_group, utsMapOf("class" to "edit-type-group", "onChange" to onValueTypeChange), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                        return utsArrayOf(
                                            createElementVNode(Fragment, null, RenderHelpers.renderList(valueTypeOptions, fun(vt, __key, __index, _cached): Any {
                                                return createVNode(_component_radio, utsMapOf("key" to vt, "value" to vt, "checked" to (unref(editValueType) === vt), "class" to "edit-type-radio"), utsMapOf("default" to withSlotCtx(fun(): UTSArray<Any> {
                                                    return utsArrayOf(
                                                        createElementVNode("text", null, toDisplayString(vt), 1)
                                                    )
                                                }), "_" to 2), 1032, utsArrayOf(
                                                    "value",
                                                    "checked"
                                                ))
                                            }), 64)
                                        )
                                    }), "_" to 1))
                                ))
                            } else {
                                createCommentVNode("v-if", true)
                            }
                            ,
                            createElementVNode("view", utsMapOf("class" to "popup-actions"), utsArrayOf(
                                createElementVNode("button", utsMapOf("class" to "btn mr-20 btn-cancel", "type" to "default", "onClick" to closeEdit), "取消"),
                                createElementVNode("button", utsMapOf("class" to "btn btn-save", "type" to "primary", "onClick" to saveEdit), "保存")
                            ))
                        ), 8, utsArrayOf(
                            "onClick"
                        ))
                    ), 512), utsArrayOf(
                        utsArrayOf(
                            vShow,
                            unref(showEditDialog)
                        )
                    ))
                ))
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
                return utsMapOf("container" to padStyleMapOf(utsMapOf("flexDirection" to "column", "backgroundImage" to "none", "backgroundColor" to "#f7f8fa", "flex" to 1)), "header" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignItems" to "center", "justifyContent" to "space-between", "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10)), "btn" to padStyleMapOf(utsMapOf("height" to 40, "fontSize" to 16)), "mr-20" to padStyleMapOf(utsMapOf("marginRight" to 20)), "list-view" to padStyleMapOf(utsMapOf("flex" to 1, "width" to "100%", "backgroundImage" to "none", "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 0, "borderTopRightRadius" to 0, "borderBottomRightRadius" to 0, "borderBottomLeftRadius" to 0, "paddingTop" to 0, "paddingRight" to 0, "paddingBottom" to 0, "paddingLeft" to 0)), "item-block" to padStyleMapOf(utsMapOf("paddingTop" to 13, "paddingRight" to 13, "paddingBottom" to 13, "paddingLeft" to 13, "borderBottomWidth" to 1, "borderBottomStyle" to "solid", "borderBottomColor" to "#e5e5e5", "backgroundImage" to "none", "backgroundColor" to "#ffffff")), "item-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignItems" to "center", "marginBottom" to 4)), "item-label" to padStyleMapOf(utsMapOf("color" to "#888888", "fontSize" to 14, "marginRight" to 4, "width" to 50)), "item-key" to padStyleMapOf(utsMapOf("color" to "#333333", "fontSize" to 15, "flex" to 1)), "item-actions-row" to padStyleMapOf(utsMapOf("justifyContent" to "space-between", "marginTop" to 6, "marginBottom" to 0)), "dialog-mask" to padStyleMapOf(utsMapOf("position" to "fixed", "left" to 0, "top" to 0, "right" to 0, "bottom" to 0, "backgroundImage" to "none", "backgroundColor" to "rgba(0,0,0,0.18)", "display" to "flex", "alignItems" to "center", "justifyContent" to "center", "zIndex" to 999)), "dialog-content" to padStyleMapOf(utsMapOf("backgroundImage" to "none", "backgroundColor" to "#ffffff", "borderTopLeftRadius" to 10, "borderTopRightRadius" to 10, "borderBottomRightRadius" to 10, "borderBottomLeftRadius" to 10, "paddingTop" to 20, "paddingRight" to 16, "paddingBottom" to 16, "paddingLeft" to 16, "minWidth" to 270, "maxWidth" to 345, "flexDirection" to "column", "alignItems" to "stretch", "position" to "relative", "boxShadow" to "none")), "dialog-title" to padStyleMapOf(utsMapOf("fontSize" to 16, "fontWeight" to "bold", "marginBottom" to 14, "textAlign" to "center", "color" to "#222222", "letterSpacing" to 1)), "detail-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignItems" to "flex-start", "marginBottom" to 9)), "popup-actions" to padStyleMapOf(utsMapOf("flexDirection" to "row", "justifyContent" to "flex-end", "marginTop" to 16, "marginRight" to 8)), "error-tip" to padStyleMapOf(utsMapOf("color" to "#FF3B30", "fontSize" to 15, "marginBottom" to 8, "textAlign" to "center", "fontWeight" to "bold")), "edit-row" to padStyleMapOf(utsMapOf("flexDirection" to "row", "alignItems" to "center", "marginBottom" to 9)), "edit-label" to padStyleMapOf(utsMapOf("minWidth" to 35, "color" to "#888888", "fontSize" to 15, "marginRight" to 6)), "edit-input" to padStyleMapOf(utsMapOf("flex" to 1, "height" to 40, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#cccccc", "borderRightColor" to "#cccccc", "borderBottomColor" to "#cccccc", "borderLeftColor" to "#cccccc", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "paddingTop" to 0, "paddingRight" to 6, "paddingBottom" to 0, "paddingLeft" to 6, "fontSize" to 15, "backgroundImage" to "none", "backgroundColor" to "#ffffff")), "edit-textarea" to padStyleMapOf(utsMapOf("flex" to 1, "minHeight" to 80, "borderTopWidth" to 1, "borderRightWidth" to 1, "borderBottomWidth" to 1, "borderLeftWidth" to 1, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "borderTopColor" to "#cccccc", "borderRightColor" to "#cccccc", "borderBottomColor" to "#cccccc", "borderLeftColor" to "#cccccc", "borderTopLeftRadius" to 4, "borderTopRightRadius" to 4, "borderBottomRightRadius" to 4, "borderBottomLeftRadius" to 4, "paddingTop" to 10, "paddingRight" to 6, "paddingBottom" to 10, "paddingLeft" to 6, "fontSize" to 15, "backgroundImage" to "none", "backgroundColor" to "#ffffff")), "edit-type-group" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row", "alignItems" to "center", "flexWrap" to "wrap", "width" to "90%")), "edit-type-radio" to padStyleMapOf(utsMapOf("marginRight" to 12, "marginBottom" to 6)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
