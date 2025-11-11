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
open class GenCloudObjUniPushCo : InternalUniCloudCloudObjectCaller {
    constructor(obj: InternalUniCloudCloudObject) : super(obj)
    open fun sendPushMessage(vararg do_not_transform_spread: Any?): UTSPromise<UTSJSONObject> {
        return _obj.callMethod("sendPushMessage", _getArgs(*do_not_transform_spread))
    }
    @JvmName("sendPushMessage1")
    inline fun <reified T> sendPushMessage(vararg do_not_transform_spread: Any?): UTSPromise<T> {
        return _obj.callMethod<T>("sendPushMessage", _getArgs(*do_not_transform_spread))
    }
}
