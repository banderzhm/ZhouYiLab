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
open class GenCloudObjUniPayCo : InternalUniCloudCloudObjectCaller {
    constructor(obj: InternalUniCloudCloudObject) : super(obj)
    open fun createOrder(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("createOrder", _getArgs(*do_not_transform_spread))
    }
    @JvmName("createOrder1")
    inline fun <reified T> createOrder(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("createOrder", _getArgs(*do_not_transform_spread))
    }
    open fun payNotify(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("payNotify", _getArgs(*do_not_transform_spread))
    }
    @JvmName("payNotify1")
    inline fun <reified T> payNotify(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("payNotify", _getArgs(*do_not_transform_spread))
    }
    open fun getOrder(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("getOrder", _getArgs(*do_not_transform_spread))
    }
    @JvmName("getOrder1")
    inline fun <reified T> getOrder(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("getOrder", _getArgs(*do_not_transform_spread))
    }
    open fun refund(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("refund", _getArgs(*do_not_transform_spread))
    }
    @JvmName("refund1")
    inline fun <reified T> refund(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("refund", _getArgs(*do_not_transform_spread))
    }
    open fun getRefund(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("getRefund", _getArgs(*do_not_transform_spread))
    }
    @JvmName("getRefund1")
    inline fun <reified T> getRefund(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("getRefund", _getArgs(*do_not_transform_spread))
    }
    open fun closeOrder(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("closeOrder", _getArgs(*do_not_transform_spread))
    }
    @JvmName("closeOrder1")
    inline fun <reified T> closeOrder(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("closeOrder", _getArgs(*do_not_transform_spread))
    }
    open fun getOpenid(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("getOpenid", _getArgs(*do_not_transform_spread))
    }
    @JvmName("getOpenid1")
    inline fun <reified T> getOpenid(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("getOpenid", _getArgs(*do_not_transform_spread))
    }
    open fun getPayProviderFromCloud(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("getPayProviderFromCloud", _getArgs(*do_not_transform_spread))
    }
    @JvmName("getPayProviderFromCloud1")
    inline fun <reified T> getPayProviderFromCloud(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("getPayProviderFromCloud", _getArgs(*do_not_transform_spread))
    }
    open fun getProviderAppId(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("getProviderAppId", _getArgs(*do_not_transform_spread))
    }
    @JvmName("getProviderAppId1")
    inline fun <reified T> getProviderAppId(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("getProviderAppId", _getArgs(*do_not_transform_spread))
    }
    open fun verifyReceiptFromAppleiap(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("verifyReceiptFromAppleiap", _getArgs(*do_not_transform_spread))
    }
    @JvmName("verifyReceiptFromAppleiap1")
    inline fun <reified T> verifyReceiptFromAppleiap(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("verifyReceiptFromAppleiap", _getArgs(*do_not_transform_spread))
    }
    open fun wxpayVirtualNotify(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("wxpayVirtualNotify", _getArgs(*do_not_transform_spread))
    }
    @JvmName("wxpayVirtualNotify1")
    inline fun <reified T> wxpayVirtualNotify(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("wxpayVirtualNotify", _getArgs(*do_not_transform_spread))
    }
    open fun requestWxpayVirtualApi(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("requestWxpayVirtualApi", _getArgs(*do_not_transform_spread))
    }
    @JvmName("requestWxpayVirtualApi1")
    inline fun <reified T> requestWxpayVirtualApi(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("requestWxpayVirtualApi", _getArgs(*do_not_transform_spread))
    }
    open fun test(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("test", _getArgs(*do_not_transform_spread))
    }
    @JvmName("test1")
    inline fun <reified T> test(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("test", _getArgs(*do_not_transform_spread))
    }
}
