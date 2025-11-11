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
open class GenPagesTemplateLongListBatchLongListBatch : BasePage {
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
        var setup: (__props: GenPagesTemplateLongListBatchLongListBatch) -> Any? = fun(__props): Any? {
            val __ins = getCurrentInstance()!!
            val _ctx = __ins.proxy as GenPagesTemplateLongListBatchLongListBatch
            val _cache = __ins.renderCache
            class Jobs : IUTSSourceMap {
                override fun `__$getOriginalPosition`(): UTSSourceMapPosition {
                    return UTSSourceMapPosition("Jobs", "pages/template/long-list-batch/long-list-batch.uvue", 24, 9)
                }
                private var jobs: UTSArray<(() -> UTSPromise<Unit>)> = utsArrayOf()
                var paused: Boolean = true
                constructor(){}
                fun add(job: () -> UTSPromise<Unit>) {
                    this.jobs.push(job)
                }
                fun pause() {
                    this.paused = true
                }
                private fun execute() {
                    if (this.paused) {
                        return
                    }
                    if (this.jobs.length == 0) {
                        this.paused = true
                        return
                    }
                    val job = this.jobs.shift()
                    if (job != null) {
                        job().then(fun(){
                            this.execute()
                        }
                        )
                    }
                }
                fun resume() {
                    if (!this.paused) {
                        return
                    }
                    this.paused = false
                    setTimeout(fun(){
                        this.execute()
                    }
                    , 0)
                }
                fun reset() {
                    this.jobs = utsArrayOf()
                    this.paused = true
                }
                val done: Boolean
                    get(): Boolean {
                        return this.jobs.length == 0
                    }
            }
            fun gen_delay_fn(time: Number): UTSPromise<Unit> {
                return UTSPromise(fun(resolve, _reject){
                    setTimeout(fun(){
                        resolve(Unit)
                    }
                    , time)
                }
                )
            }
            val delay = ::gen_delay_fn
            val bigList = ref(utsArrayOf<Item9>())
            val list = ref(utsArrayOf<Item9>())
            val jobs = ref<Jobs>(Jobs())
            val batchSize = ref<Number>(100)
            val scrolling = ref<Boolean>(false)
            val refresherTriggered = ref<Boolean>(false)
            val scrollendTimeout = ref<Number>(-1)
            val pulling = ref<Boolean>(false)
            fun init(autoResumeJobs: Boolean = true) {
                val batchCount = Math.ceil(bigList.value.length / batchSize.value)
                run {
                    var i: Number = 0
                    while(i < batchCount){
                        val start = i * batchSize.value
                        val end = Math.min(start + batchSize.value, bigList.value.length)
                        jobs.value.add(fun(): UTSPromise<Unit> {
                            return wrapUTSPromise(suspend {
                                    list.value.push(*bigList.value.slice(start, end).toTypedArray())
                                    await(nextTick())
                                    await(delay(100))
                            })
                        }
                        )
                        i++
                    }
                }
                if (autoResumeJobs) {
                    jobs.value.resume()
                }
            }
            val onScrollEnd = fun(){
                scrolling.value = false
                jobs.value.resume()
            }
            val onScroll = fun(){
                clearTimeout(scrollendTimeout.value)
                scrollendTimeout.value = setTimeout(fun(){
                    onScrollEnd()
                }
                , 100)
                if (scrolling.value) {
                    return
                }
                scrolling.value = true
                jobs.value.pause()
            }
            val onScrollToLower = fun(){
                if (!jobs.value.done) {
                    return
                }
            }
            val onRefresh = fun(){
                jobs.value.reset()
                refresherTriggered.value = true
                setTimeout(fun(){
                    refresherTriggered.value = false
                    list.value.splice(0, list.value.length)
                    init(false)
                }
                , 500)
            }
            val onRefresherPulling = fun(){
                if (pulling.value) {
                    return
                }
                pulling.value = true
                jobs.value.pause()
            }
            val onRefresherRestore = fun(){
                pulling.value = false
                jobs.value.resume()
            }
            run {
                var i: Number = 0
                while(i < 2000){
                    bigList.value.push(Item9(id = i, name = "Wifi_" + i, info = "\u4FE1\u53F7\u5F3A\u5EA6: -" + (Math.floor(Math.random() * 60) + 40) + " db, \u5B89\u5168\u6027: WPA/WPA2/WPA3-Personal"))
                    i++
                }
            }
            onReady(fun(){
                init()
            }
            )
            return fun(): Any? {
                return createElementVNode("view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1", "background-color" to "aliceblue"))), utsArrayOf(
                    createElementVNode("view", utsMapOf("class" to "tips"), "list-view组件虽然在渲染层有recycle机制，但长列表的vnode/Element太多也会造成初始化卡顿。 本示例有2000条数据，通过分批创建列表项，减少初始化卡顿。并通过闲时创建机制避免影响列表滚动等UI操作"),
                    createElementVNode("list-view", utsMapOf("style" to normalizeStyle(utsMapOf("flex" to "1")), "refresher-enabled" to true, "refresher-triggered" to unref(refresherTriggered), "onScroll" to onScroll, "onScrolltolower" to onScrollToLower, "onRefresherrefresh" to onRefresh, "onRefresherpulling" to onRefresherPulling, "onRefresherrestore" to onRefresherRestore), utsArrayOf(
                        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(list), fun(item, index, __index, _cached): Any {
                            return createElementVNode("list-item", utsMapOf("class" to "item", "key" to (index + "_" + item.id)), utsArrayOf(
                                createElementVNode("view", null, utsArrayOf(
                                    createElementVNode("text", null, toDisplayString(item.name), 1)
                                )),
                                createElementVNode("view", null, utsArrayOf(
                                    createElementVNode("text", utsMapOf("style" to normalizeStyle(utsMapOf("font-size" to "12px", "color" to "#999999"))), toDisplayString(item.info), 5)
                                ))
                            ))
                        }
                        ), 128)
                    ), 44, utsArrayOf(
                        "refresher-triggered"
                    ))
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
                return utsMapOf("tips" to padStyleMapOf(utsMapOf("marginTop" to 10, "marginRight" to 10, "marginBottom" to 10, "marginLeft" to 10, "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "backgroundColor" to "#FFFFFF")), "item" to padStyleMapOf(utsMapOf("marginTop" to 5, "marginRight" to 10, "marginBottom" to 5, "marginLeft" to 10, "paddingTop" to 10, "paddingRight" to 10, "paddingBottom" to 10, "paddingLeft" to 10, "borderTopLeftRadius" to 5, "borderTopRightRadius" to 5, "borderBottomRightRadius" to 5, "borderBottomLeftRadius" to 5, "backgroundColor" to "#FFFFFF")))
            }
        var inheritAttrs = true
        var inject: Map<String, Map<String, Any?>> = utsMapOf()
        var emits: Map<String, Any?> = utsMapOf()
        var props = normalizePropsOptions(utsMapOf())
        var propsNeedCastKeys: UTSArray<String> = utsArrayOf()
        var components: Map<String, CreateVueComponent> = utsMapOf()
    }
}
