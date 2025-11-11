@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsOpenSchema
import android.content.Intent
import android.net.Uri
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
typealias OpenSchema = (url: String) -> Unit
typealias CanOpenURL = (url: String) -> Boolean
val openSchema: OpenSchema = fun(url: String) {
    if (canOpenURL(url)) {
        val context = UTSAndroid.getUniActivity()!!
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setData(uri)
        context.startActivity(intent)
    } else {
        console.error("[uts-openSchema] url param Error：", JSON.stringify(url), " at uni_modules/uts-openSchema/utssdk/app-android/index.uts:14")
    }
}
val canOpenURL: CanOpenURL = fun(url: String): Boolean {
    if (UTSAndroid.`typeof`(url) === "string" && url.length > 0) {
        val context = UTSAndroid.getUniActivity()!!
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        return if (intent.resolveActivity(context.packageManager) != null) {
            true
        } else {
            false
        }
    } else {
        return false
    }
}
