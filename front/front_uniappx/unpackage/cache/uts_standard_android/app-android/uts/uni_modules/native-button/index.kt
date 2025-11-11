@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.nativeButton
import android.widget.Button
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
interface INativeButtonContext {
    fun updateText(text: String)
}
open class NativeButton : IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("NativeButton", "uni_modules/native-button/utssdk/app-android/index.uts", 3, 14)
    }
    open var `$element`: UniNativeViewElement
    constructor(element: UniNativeViewElement){
        this.`$element` = element
        this.bindView()
    }
    open var button: Button? = null
    open fun bindView() {
        this.button = Button(this.`$element`.getAndroidActivity()!!)
        this.button?.setAllCaps(false)
        this.button?.setOnClickListener(fun(_){
            val detail: UTSJSONObject = object : UTSJSONObject(UTSSourceMapPosition("detail", "uni_modules/native-button/utssdk/app-android/index.uts", 18, 19)) {
            }
            val event = UniNativeViewEvent("customClick", detail)
            this.`$element`.dispatchEvent(event)
        }
        )
        this.`$element`.bindAndroidView(this.button!!)
    }
    open fun updateText(text: String) {
        this.button?.setText(text)
    }
    open fun destroy() {}
}
open class NativeButtonContext : INativeButtonContext, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("NativeButtonContext", "uni_modules/native-button/utssdk/app-android/index.uts", 35, 7)
    }
    private var button: Button? = null
    constructor(button: Button){
        this.button = button
    }
    override fun updateText(text: String) {
        this.button?.setText(text)
    }
}
fun iterateElement(homeElement: UniElement): UniElement? {
    if ("NATIVE-VIEW" == homeElement.nodeName) {
        return homeElement
    }
    for(perChildEle in resolveUTSValueIterator(homeElement.children)){
        var findEle = iterateElement(perChildEle)
        if (findEle != null) {
            return findEle
        }
    }
    return null
}
fun createNativeButtonContext(id: String, ins: ComponentPublicInstance? = null): INativeButtonContext? {
    if (ins == null) {
        val pages = getCurrentPages()
        if (pages.length > 0) {
            val page = pages[pages.length - 1]
            val rootViewElement = page.getElementById(id)
            if (rootViewElement != null) {
                val nativeViewElement = iterateElement(rootViewElement)
                if (nativeViewElement != null) {
                    return NativeButtonContext(nativeViewElement.getAndroidView<android.view.View>()!! as Button)
                }
            }
        }
    } else {
        if (ins.`$el` != null) {
            val nativeViewElement = iterateElement(ins.`$el` as UniElement)
            if (nativeViewElement != null) {
                return NativeButtonContext(nativeViewElement.getAndroidView<android.view.View>()!! as Button)
            }
        }
    }
    return null
}
