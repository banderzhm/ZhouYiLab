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
import io.dcloud.uniapp.extapi.getElementById as uni_getElementById
import io.dcloud.uniapp.extapi.loadFontFace as uni_loadFontFace
open class GenPagesComponentTextTextLayout : BasePage {
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
    companion object {
        @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
        var setup: (__props: GenPagesComponentTextTextLayout) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentTextTextLayout
            val _cache = __ins.renderCache
            val title = "text-layout"
            val info = ref("")
            val info2 = ref("")
            val info3 = ref("")
            val info4 = ref("")
            val setTextLayout = fun(){
                val element = uni_getElementById("text") as UniTextElement
                val layout = UniTextLayout()
                layout.setText("HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言；")
                layout.setColor("red")
                layout.setFontFamily("AlimamaDaoLiTiTTF")
                layout.setFontSize("30px")
                layout.setFontStyle("italic")
                layout.setFontWeight("bold")
                layout.setLineHeight("3")
                layout.setTextShadow("2px 4px rgba(202,207,17,0.5)")
                layout.setTextDecorationLine("underline")
                val measureSize = layout.measure(UniLayoutConstraintSize())
                element.style.setProperty("width", measureSize.width)
                element.style.setProperty("height", measureSize.height)
                element.setTextLayout(layout)
                val size = element.getContentSize()
                info.value = "width: " + size.width + "px height: " + size.height + "px"
            }
            val setTextLayout2 = fun(){
                val element = uni_getElementById("text2") as UniTextElement
                val layout = UniTextLayout()
                layout.setText("HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言；")
                layout.setColor("red")
                layout.setLineHeight("3")
                layout.setTextAlign("center")
                val measureSize = layout.measure(UniLayoutConstraintSize(maxWidth = 300, minHeight = 100))
                element.style.setProperty("width", measureSize.width)
                element.style.setProperty("height", measureSize.height)
                element.setTextLayout(layout)
                val size = element.getContentSize()
                info2.value = "width: " + size.width + "px height: " + size.height + "px"
            }
            val setTextLayout3 = fun(){
                val element = uni_getElementById("text3") as UniTextElement
                val layout = UniTextLayout()
                layout.setText("HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言；")
                layout.setColor("red")
                layout.setLineHeight("30px")
                layout.setTextOverflow("ellipsis")
                layout.setWhiteSpace("nowrap")
                val measureSize = layout.measure(UniLayoutConstraintSize(maxWidth = 300))
                element.style.setProperty("width", measureSize.width)
                element.style.setProperty("height", measureSize.height)
                element.setTextLayout(layout)
                val size = element.getContentSize()
                info3.value = "width: " + size.width + "px height: " + size.height + "px"
            }
            val setTextLayout4 = fun(){
                val element = uni_getElementById("text4") as UniTextElement
                val layout = UniTextLayout()
                layout.setText("HBuilderX，轻巧、极速，极客编辑器；uts，大一统语言；")
                layout.setColor("red")
                layout.setFontSize("25px")
                val child = UniTextLayout()
                child.setText("uni-app x，终极跨平台方案；")
                layout.append(child)
                val child2 = UniTextLayout()
                child2.setText("uts，大一统语言；")
                child2.setColor("blue")
                child2.setFontSize("20px")
                layout.append(child2)
                val measureSize = layout.measure(UniLayoutConstraintSize(maxWidth = 300))
                element.style.setProperty("width", measureSize.width)
                element.style.setProperty("height", measureSize.height)
                element.setTextLayout(layout)
                val size = element.getContentSize()
                info4.value = "width: " + size.width + "px height: " + size.height + "px"
            }
            onLoad(fun(_options){
                uni_loadFontFace(LoadFontFaceOptions(family = "AlimamaDaoLiTiTTF", source = "url('/static/font/AlimamaDaoLiTi.otf')"))
            }
            )
            onReady(fun(){
                setTextLayout()
                setTextLayout2()
                setTextLayout3()
                setTextLayout4()
            }
            )
            return fun(): Any? {
                val _component_page_head = resolveEasyComponent("page-head", GenComponentsPageHeadPageHeadClass)
                return createElementVNode("scroll-view", utsMapOf("class" to "uni-flex-item uni-padding-wrap"), utsArrayOf(
                    createVNode(_component_page_head, utsMapOf("title" to title)),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "无约束宽高"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(unref(info)), 1)
                    )),
                    createElementVNode("view", null, utsArrayOf(
                        createElementVNode("text", utsMapOf("id" to "text", "class" to "text"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "约束宽高-多行"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(unref(info2)), 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("id" to "text2", "class" to "text"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "约束宽高-单行"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(unref(info3)), 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("id" to "text3", "class" to "text"))
                    )),
                    createElementVNode("view", utsMapOf("class" to "uni-title"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), "嵌套"),
                        createElementVNode("text", utsMapOf("class" to "uni-subtitle-text"), toDisplayString(unref(info4)), 1)
                    )),
                    createElementVNode("view", utsMapOf("class" to "text-box"), utsArrayOf(
                        createElementVNode("text", utsMapOf("id" to "text4", "class" to "text"))
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
                return utsMapOf("text-box" to padStyleMapOf(utsMapOf("alignItems" to "center")), "text" to padStyleMapOf(utsMapOf("backgroundColor" to "#ffffff")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
