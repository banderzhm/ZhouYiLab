@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsButton
import android.text.TextUtils
import android.view.View
import android.widget.Button
import io.dcloud.uniapp.*
import io.dcloud.uniapp.UniSDKEngine
import io.dcloud.uniapp.appframe.PageProxy
import io.dcloud.uniapp.dom.node.DomNode
import io.dcloud.uniapp.dom.node.PageNode
import io.dcloud.uniapp.extapi.*
import io.dcloud.uniapp.framework.*
import io.dcloud.uniapp.interfaces.INodeData
import io.dcloud.uniapp.runtime.*
import io.dcloud.uniapp.ui.component.IComponentData
import io.dcloud.uniapp.vue.*
import io.dcloud.uniapp.vue.shared.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import io.dcloud.uts.component.*
import io.dcloud.uts.component.UTSComponent
import io.dcloud.uts.component.UTSSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
open class UtsButtonComponent : UTSComponent<Button> {
    constructor(proxy: PageProxy, componentData: IComponentData) : super(proxy, componentData)
    open var buttontext: String = "点击触发"
    override fun created() {}
    override fun NVBeforeLoad() {}
    override fun NVLoad(): Button {
        var button = Button(`$androidContext`!!)
        button.setText("点击触发")
        button.setOnClickListener(ButtonClickListener(this))
        return button
    }
    override fun NVLoaded() {}
    override fun NVLayouted() {}
    override fun NVBeforeUnload() {}
    override fun NVUnloaded() {}
    override fun unmounted() {}
    override fun NVMeasure(size: UTSSize): UTSSize {
        return size
    }
    open fun doSomething(param: String) {
        console.log(param, " at uni_modules/uts-button/utssdk/app-android/index.vue:91")
    }
    open fun privateMethod() {}
    override fun `$init`() {
        this.`$watch`<String>("buttontext", fun(newValue, oldValue){
            if (!TextUtils.isEmpty(newValue) && newValue != oldValue) {
                this.`$el`?.setText(newValue)
            }
        }
        )
    }
    companion object {
        var name = "uts-button"
        init {
            io.dcloud.uniapp.UniSDKEngine.registerUniComponent("uts-button", UtsButtonComponent::class.java, UtsButtonElement::class.java)
        }
    }
}
open class UtsButtonElement : DomNode {
    constructor(data: INodeData, pageNode: PageNode) : super(data, pageNode)
    open fun doSomething(param: String) {
        return (getComponent() as UtsButtonComponent).doSomething(param)
    }
}
open class ButtonClickListener : View.OnClickListener {
    private var comp: UTSComponent<Button>
    constructor(comp: UTSComponent<Button>) : super() {
        this.comp = comp
    }
    override fun onClick(v: View?) {
        console.log("按钮被点击", " at uni_modules/uts-button/utssdk/app-android/index.vue:186")
        this.comp.`$emit`("buttonclick")
    }
}
