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
open class GenCloudObjTodo : InternalUniCloudCloudObjectCaller {
    constructor(obj: InternalUniCloudCloudObject) : super(obj)
    open fun add(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("add", _getArgs(*do_not_transform_spread))
    }
    @JvmName("add1")
    inline fun <reified T> add(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("add", _getArgs(*do_not_transform_spread))
    }
    open fun randomFail(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("randomFail", _getArgs(*do_not_transform_spread))
    }
    @JvmName("randomFail1")
    inline fun <reified T> randomFail(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("randomFail", _getArgs(*do_not_transform_spread))
    }
    open fun fail(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("fail", _getArgs(*do_not_transform_spread))
    }
    @JvmName("fail1")
    inline fun <reified T> fail(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("fail", _getArgs(*do_not_transform_spread))
    }
    open fun failWithNumberErrCode(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("failWithNumberErrCode", _getArgs(*do_not_transform_spread))
    }
    @JvmName("failWithNumberErrCode1")
    inline fun <reified T> failWithNumberErrCode(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("failWithNumberErrCode", _getArgs(*do_not_transform_spread))
    }
    open fun success(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("success", _getArgs(*do_not_transform_spread))
    }
    @JvmName("success1")
    inline fun <reified T> success(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("success", _getArgs(*do_not_transform_spread))
    }
}
