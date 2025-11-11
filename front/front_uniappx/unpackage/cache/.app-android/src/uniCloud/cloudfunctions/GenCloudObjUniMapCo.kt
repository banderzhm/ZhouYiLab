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
open class GenCloudObjUniMapCo : InternalUniCloudCloudObjectCaller {
    constructor(obj: InternalUniCloudCloudObject) : super(obj)
    open fun chooseLocation(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("chooseLocation", _getArgs(*do_not_transform_spread))
    }
    @JvmName("chooseLocation1")
    inline fun <reified T> chooseLocation(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("chooseLocation", _getArgs(*do_not_transform_spread))
    }
    open fun location2address(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("location2address", _getArgs(*do_not_transform_spread))
    }
    @JvmName("location2address1")
    inline fun <reified T> location2address(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("location2address", _getArgs(*do_not_transform_spread))
    }
    open fun address2location(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("address2location", _getArgs(*do_not_transform_spread))
    }
    @JvmName("address2location1")
    inline fun <reified T> address2location(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("address2location", _getArgs(*do_not_transform_spread))
    }
    open fun translate(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("translate", _getArgs(*do_not_transform_spread))
    }
    @JvmName("translate1")
    inline fun <reified T> translate(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("translate", _getArgs(*do_not_transform_spread))
    }
    open fun ip2location(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("ip2location", _getArgs(*do_not_transform_spread))
    }
    @JvmName("ip2location1")
    inline fun <reified T> ip2location(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("ip2location", _getArgs(*do_not_transform_spread))
    }
    open fun inputtips(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("inputtips", _getArgs(*do_not_transform_spread))
    }
    @JvmName("inputtips1")
    inline fun <reified T> inputtips(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("inputtips", _getArgs(*do_not_transform_spread))
    }
    open fun search(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("search", _getArgs(*do_not_transform_spread))
    }
    @JvmName("search1")
    inline fun <reified T> search(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("search", _getArgs(*do_not_transform_spread))
    }
    open fun districtSearch(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("districtSearch", _getArgs(*do_not_transform_spread))
    }
    @JvmName("districtSearch1")
    inline fun <reified T> districtSearch(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("districtSearch", _getArgs(*do_not_transform_spread))
    }
    open fun route(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("route", _getArgs(*do_not_transform_spread))
    }
    @JvmName("route1")
    inline fun <reified T> route(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("route", _getArgs(*do_not_transform_spread))
    }
    open fun clearPoi(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("clearPoi", _getArgs(*do_not_transform_spread))
    }
    @JvmName("clearPoi1")
    inline fun <reified T> clearPoi(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("clearPoi", _getArgs(*do_not_transform_spread))
    }
    open fun initStatic001(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("initStatic001", _getArgs(*do_not_transform_spread))
    }
    @JvmName("initStatic0011")
    inline fun <reified T> initStatic001(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("initStatic001", _getArgs(*do_not_transform_spread))
    }
    open fun initDynamics001(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("initDynamics001", _getArgs(*do_not_transform_spread))
    }
    @JvmName("initDynamics0011")
    inline fun <reified T> initDynamics001(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("initDynamics001", _getArgs(*do_not_transform_spread))
    }
    open fun getPolyline(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("getPolyline", _getArgs(*do_not_transform_spread))
    }
    @JvmName("getPolyline1")
    inline fun <reified T> getPolyline(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("getPolyline", _getArgs(*do_not_transform_spread))
    }
    open fun updateMyLocation(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("updateMyLocation", _getArgs(*do_not_transform_spread))
    }
    @JvmName("updateMyLocation1")
    inline fun <reified T> updateMyLocation(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("updateMyLocation", _getArgs(*do_not_transform_spread))
    }
    open fun test(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("test", _getArgs(*do_not_transform_spread))
    }
    @JvmName("test1")
    inline fun <reified T> test(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("test", _getArgs(*do_not_transform_spread))
    }
}
