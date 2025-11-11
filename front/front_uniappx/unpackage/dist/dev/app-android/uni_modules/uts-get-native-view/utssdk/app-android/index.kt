@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsGetNativeView
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.widget.AppCompatEditText
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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
typealias CanWebViewGoBack = (elementId: String) -> Boolean
typealias CanWebViewGoForward = (elementId: String) -> Boolean
typealias HasNativeView = (elementId: String) -> Boolean
typealias CheckWebViewNativeView = (elementId: String) -> Boolean
typealias CheckInputNativeView = (elementId: String) -> Boolean
typealias CheckViewNativeView = (elementId: String) -> Boolean
val canWebViewGoBack: CanWebViewGoBack = fun(elementId: String): Boolean {
    val view = uni_getElementById(elementId)?.getAndroidView<WebView>()
    return if (view == null) {
        false
    } else {
        view.canGoBack()
    }
}
val canWebViewGoForward: CanWebViewGoForward = fun(elementId: String): Boolean {
    val view = uni_getElementById(elementId)?.getAndroidView<WebView>()
    return if (view == null) {
        false
    } else {
        view.canGoForward()
    }
}
val hasNativeView: HasNativeView = fun(elementId: String): Boolean {
    val view = uni_getElementById(elementId)?.getAndroidView<android.view.View>()
    return view != null
}
val checkWebViewNativeView: CheckWebViewNativeView = fun(elementId: String): Boolean {
    val element = uni_getElementById(elementId)
    val view = element?.getAndroidView<android.view.View>()
    if (view != null && view is WebView) {
        return true
    }
    return false
}
val checkInputNativeView: CheckInputNativeView = fun(elementId: String): Boolean {
    val view = uni_getElementById(elementId)?.getAndroidView<AppCompatEditText>()
    return view != null
}
val checkTextareaNativeView: CheckViewNativeView = fun(elementId: String): Boolean {
    val view = uni_getElementById(elementId)?.getAndroidView<AppCompatEditText>()
    return view != null
}
val checkViewNativeView: CheckViewNativeView = fun(elementId: String): Boolean {
    val view = uni_getElementById(elementId)?.getAndroidView<ViewGroup>()
    return view != null
}
