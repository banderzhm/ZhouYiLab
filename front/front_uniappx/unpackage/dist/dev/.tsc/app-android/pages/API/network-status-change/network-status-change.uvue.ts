import _easycom_page_head from '@/components/page-head/page-head.vue'

const __sfc__ = defineComponent({
  __name: 'network-status-change',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const isConnected = ref(false)
  const networkType = ref('unknown')
  const listenStatus = ref('未监听')
  const networkCallback = ref<((result : OnNetworkStatusChangeCallbackResult) => void) | null>(null)
  // 获取当前网络状态
  const getCurrentNetworkStatus = () => {
    uni.getNetworkType({
      success: (res) => {
        networkType.value = res.networkType
        isConnected.value = res.networkType != 'none'
      },
      fail: () => {
        console.log('获取网络状态失败', " at pages/API/network-status-change/network-status-change.uvue:37")
        networkType.value = 'unknown'
        isConnected.value = false
      }
    })
  }
  // 开始监听
  const startListen = () => {
    if (networkCallback.value != null) {
      listenStatus.value = '正在监听'
      return
    }
    networkCallback.value = (result : OnNetworkStatusChangeCallbackResult) => {
      console.log('网络状态: ', result, " at pages/API/network-status-change/network-status-change.uvue:50");
      isConnected.value = result.isConnected
      networkType.value = result.networkType
      if (!result.isConnected) {
        networkType.value = 'none'
      }
    }
    uni.onNetworkStatusChange(networkCallback.value)
    // console.log('开始监听网络状态')
    listenStatus.value = '正在监听'
  }
  // 停止监听
  const stopListen = () => {
    if (networkCallback.value == null) {
      listenStatus.value = '未监听'
      return
    }
    uni.offNetworkStatusChange(networkCallback.value)
    networkCallback.value = null
    // console.log('停止监听网络状态')
    listenStatus.value = '停止监听'
  }

  onLoad(() => {
    getCurrentNetworkStatus()
  })
  onUnload(() => {
    stopListen()
  })

return (): any | null => {

const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "networkStatusChange" })),
    createElementVNode("view", utsMapOf({ class: "uni-list-cell-padding status-box" }), [
      createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "网络状态"),
      createElementVNode("view", utsMapOf({ class: "uni-row uni-common-mt" }), [
        createElementVNode("text", utsMapOf({ class: "uni-hello-text" }), "是否连接："),
        createElementVNode("text", null, toDisplayString(unref(isConnected) ? '已连接' : '未连接'), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-row uni-common-mt" }), [
        createElementVNode("text", utsMapOf({ class: "uni-hello-text" }), "网络类型："),
        createElementVNode("text", null, toDisplayString(unref(networkType)), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-row uni-common-mt" }), [
        createElementVNode("text", utsMapOf({ class: "uni-hello-text" }), "监听状态："),
        createElementVNode("text", null, toDisplayString(unref(listenStatus)), 1 /* TEXT */)
      ])
    ]),
    createElementVNode("view", utsMapOf({ class: "uni-list-cell-padding uni-common-mt" }), [
      createElementVNode("button", utsMapOf({
        type: "primary",
        onClick: startListen
      }), "开始监听"),
      createElementVNode("button", utsMapOf({
        type: "default",
        class: "uni-common-mt",
        onClick: stopListen
      }), "停止监听")
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
}

})
export default __sfc__
const GenPagesAPINetworkStatusChangeNetworkStatusChangeStyles = [utsMapOf([["status-box", padStyleMapOf(utsMapOf([["backgroundColor", "#FFFFFF"], ["marginTop", 0], ["marginRight", 20], ["marginBottom", 0], ["marginLeft", 20]]))]])]
