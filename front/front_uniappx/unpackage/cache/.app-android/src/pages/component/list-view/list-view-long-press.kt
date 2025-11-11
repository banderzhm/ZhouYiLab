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
import io.dcloud.uniapp.extapi.showToast as uni_showToast
open class GenPagesComponentListViewListViewLongPress : BasePage {
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
        var setup: (__props: GenPagesComponentListViewListViewLongPress) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesComponentListViewListViewLongPress
            val _cache = __ins.renderCache
            val messageView = ref<UniElement?>(null)
            val bottom = ref<UniElement?>(null)
            val avatarWidth = ref(38)
            val scrollTop = ref(0)
            val message = ref("")
            val showBottom = ref(false)
            val isFocus = ref(false)
            val loading = ref(false)
            val audio = ref(false)
            val recording = ref(false)
            val messageList = ref(utsArrayOf<MessageItem>())
            val screenWidth = computed(fun(): Number {
                return 1080
            }
            )
            val clearData = fun(){
                if (messageList.value.length >= 40) {
                    messageList.value.splice(39, messageList.value.length - 1)
                }
            }
            val listItemLongPress = fun(index: Number){
                uni_showToast(ShowToastOptions(title = "触发长按:" + index, position = "bottom"))
            }
            val loadData = fun(){
                loading.value = true
                setTimeout(fun(){
                    loading.value = false
                    var length = messageList.value.length
                    run {
                        var i: Number = 0
                        while(i < 20){
                            var item = MessageItem(id = length + i, avatar = "/static/uni.png", left = true, content = "这是一些占位消息", height = 0)
                            messageList.value.push(item)
                            i++
                        }
                    }
                }
                , 800)
            }
            val getMessage = fun(){
                var idList: UTSArray<Number> = utsArrayOf()
                run {
                    var index: Number = 0
                    while(index < 20){
                        var item = MessageItem(id = index, avatar = "/static/uni.png", left = false, content = "\u8FD9\u662F\u4E00\u6761\u6D88\u606F" + index, height = 0)
                        messageList.value.push(item)
                        idList.push(item.id)
                        index++
                    }
                }
            }
            onReady(fun(){
                getMessage()
            }
            )
            return fun(): Any? {
                val _component_uni_loading = resolveEasyComponent("uni-loading", GenUniModulesUniLoadingComponentsUniLoadingUniLoadingClass)
                return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), utsArrayOf(
                    createElementVNode("scroll-view", utsMapOf("show-scrollbar" to false, "scroll-with-animation" to true, "style" to normalizeStyle(utsMapOf("flex" to "1", "padding-bottom" to "10px"))), utsArrayOf(
                        createElementVNode("list-view", utsMapOf("ref_key" to "messageView", "ref" to messageView, "onScrolltoupper" to clearData, "onScrolltolower" to loadData, "scroll-with-animation" to true, "show-scrollbar" to false, "style" to normalizeStyle(utsMapOf("flex" to "1", "transform" to "rotate(180deg)"))), utsArrayOf(
                            createElementVNode(Fragment, null, RenderHelpers.renderList(unref(messageList), fun(item, index, __index, _cached): Any {
                                return createElementVNode("list-item", utsMapOf("onLongpress" to fun(){
                                    listItemLongPress(index)
                                }
                                , "key" to ("listItem" + index), "id" to ("listItem" + item.id), "style" to normalizeStyle(utsMapOf("transform" to "rotate(180deg)", "padding" to "10px 12px")), "type" to "0"), utsArrayOf(
                                    createElementVNode("view", utsMapOf("class" to "left-row"), utsArrayOf(
                                        createElementVNode("image", utsMapOf("fade-show" to true, "src" to item.avatar, "style" to normalizeStyle(utsMapOf("width" to ("" + unref(avatarWidth) + "px"), "height" to ("" + unref(avatarWidth) + "px"), "borderRadius" to ("" + unref(avatarWidth) + "px")))), null, 12, utsArrayOf(
                                            "src"
                                        )),
                                        createElementVNode("view", utsMapOf("id" to ("listItemContent" + item.id), "class" to "flex-row content", "style" to normalizeStyle(utsMapOf("padding" to "9px 11px 9px 11px", "border-radius" to "7px", "align-items" to "center", "margin-left" to "8px", "background-color" to "#3c99ff"))), utsArrayOf(
                                            createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "13px", "line-height" to "20px", "color" to "#fff"))), toDisplayString(item.content), 5)
                                        ), 12, utsArrayOf(
                                            "id"
                                        )),
                                        createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1"))), null, 4)
                                    ))
                                ), 44, utsArrayOf(
                                    "onLongpress",
                                    "id"
                                ))
                            }
                            ), 128),
                            createElementVNode("list-item", utsMapOf("slot" to "load-more", "type" to "3", "class" to "loading"), utsArrayOf(
                                createVNode(_component_uni_loading, utsMapOf("loading" to unref(loading), "color" to "#999"), null, 8, utsArrayOf(
                                    "loading"
                                ))
                            ))
                        ), 36)
                    ), 4)
                ), 4)
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
                return utsMapOf("flex-row" to padStyleMapOf(utsMapOf("display" to "flex", "flexDirection" to "row")), "left-row" to padStyleMapOf(utsMapOf("flexDirection" to "row-reverse")), "content" to padStyleMapOf(utsMapOf("marginTop" to 0, "marginRight" to 8, "marginBottom" to 0, "marginLeft" to 8)), "loading" to padStyleMapOf(utsMapOf("height" to 60, "justifyContent" to "center", "alignItems" to "center")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
