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
open class GenPagesCSSOverflowOverflow : BasePage {
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
    @Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
    override fun `$render`(): Any? {
        val _cache = this.`$`.renderCache
        return createElementVNode("scroll-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=hidden效果子元素是view border圆角", 4),
            createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "box-hidden-border-radius"), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "150px", "background-color" to "greenyellow"))), null, 4)
                ))
            )),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=hidden效果 子元素是view border边框", 4),
            createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "box-hidden-border-width"), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "150px", "background-color" to "greenyellow"))), null, 4)
                ))
            )),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=visible效果 子元素是view border圆角", 4),
            createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "box-visible-border-radius"), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "150px", "background-color" to "greenyellow"))), null, 4)
                ))
            )),
            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=visible效果 子元素是view border边框", 4),
            createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                createElementVNode("view", utsMapOf("class" to "box-visible-border-width"), utsArrayOf(
                    createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "150px", "background-color" to "greenyellow"))), null, 4)
                ))
            )),
            createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex-grow" to "1"))), utsArrayOf(
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=hidden效果 子元素是text", 4),
                createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "box-hidden-border-radius"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text1"), "ABCDEFG")
                    ))
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=visible效果 子元素是text", 4),
                createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "box-visible-border-radius"), utsArrayOf(
                        createElementVNode("text", utsMapOf("class" to "text1"), "ABCDEFG")
                    ))
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=hidden效果 子元素是image", 4),
                createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "box-hidden-border-radius"), utsArrayOf(
                        createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "150px", "height" to "150px")), "src" to "/static/uni.png"), null, 4)
                    ))
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=visible效果 子元素是image", 4),
                createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "box-visible-border-radius"), utsArrayOf(
                        createElementVNode("image", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "150px", "height" to "150px")), "src" to "/static/uni.png"), null, 4)
                    ))
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=visible效果 子元素是view 父是scroll-view", 4),
                createElementVNode("scroll-view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "box-visible-border-radius"), utsArrayOf(
                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "50px", "height" to "150px", "background-color" to "greenyellow"))), null, 4)
                    ))
                )),
                createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "15px"))), "overflow=visible 设置阴影显示文字", 4),
                createElementVNode("view", utsMapOf("class" to "backgroundview"), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "", "style" to normalizeStyle(utsMapOf("overflow" to "visible", "width" to "100px", "height" to "100px", "background-color" to "green", "box-shadow" to "10px 10px #000", "margin-top" to "10px"))), utsArrayOf(
                        createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("width" to "170px", "background-color" to "greenyellow"))), "文字文字文字文字文字", 4)
                    ), 4)
                ))
            ), 4)
        ), 4)
    }
    companion object {
        val styles: Map<String, Map<String, Map<String, Any>>> by lazy {
            normalizeCssStyles(utsArrayOf(
                styles0
            ), utsArrayOf(
                GenApp.styles
            ))
        }
        val styles0: Map<String, Map<String, Map<String, Any>>>
            get() {
                return utsMapOf("backgroundview" to padStyleMapOf(utsMapOf("width" to 300, "height" to 300, "marginBottom" to 20, "backgroundColor" to "#FFFFFF", "justifyContent" to "center", "alignItems" to "center")), "box-hidden-border-radius" to padStyleMapOf(utsMapOf("width" to 100, "height" to 100, "borderTopLeftRadius" to 20, "borderTopRightRadius" to 20, "borderBottomRightRadius" to 20, "borderBottomLeftRadius" to 20, "overflow" to "hidden", "backgroundColor" to "#008000")), "box-hidden-border-width" to padStyleMapOf(utsMapOf("width" to 100, "height" to 100, "borderTopWidth" to 2, "borderRightWidth" to 2, "borderBottomWidth" to 2, "borderLeftWidth" to 2, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "backgroundColor" to "#008000")), "box-visible-border-radius" to padStyleMapOf(utsMapOf("width" to 100, "height" to 100, "borderTopLeftRadius" to 20, "borderTopRightRadius" to 20, "borderBottomRightRadius" to 20, "borderBottomLeftRadius" to 20, "overflow" to "visible", "backgroundColor" to "#008000")), "box-visible-border-width" to padStyleMapOf(utsMapOf("width" to 100, "height" to 100, "borderTopWidth" to 2, "borderRightWidth" to 2, "borderBottomWidth" to 2, "borderLeftWidth" to 2, "borderTopStyle" to "solid", "borderRightStyle" to "solid", "borderBottomStyle" to "solid", "borderLeftStyle" to "solid", "overflow" to "visible", "backgroundColor" to "#008000")), "text1" to padStyleMapOf(utsMapOf("fontSize" to 50, "lines" to 1)))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
