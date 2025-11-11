
  const __sfc__ = defineComponent({
    data() {
      return {
        connected: false,
        connecting: false,
        socketTask: null as SocketTask | null,
        msg: '',
        platform: '',
        //自动化测试例专用
        jest_result: 0,
      }
    },
    computed: {
      showMsg() : string {
        if (this.connected) {
          if (this.msg.length > 0) {
            return '收到消息：' + this.msg
          } else {
            return '等待接收消息'
          }
        } else {
          return '尚未连接'
        }
      },
    },
    onLoad() {
      this.platform = uni.getDeviceInfo().platform as string
    },
    onUnload() {
      uni.hideLoading()
      let task = this.socketTask
      if (task != null) {
        task.close({
          code: 1000,
          reason: 'close reason from client',
          success: (res : any) => {
            console.log('uni.closeSocket success', res, " at pages/API/websocket/socketTask.uvue:60")
          },
          fail: (err : any) => {
            console.log('uni.closeSocket fail', err, " at pages/API/websocket/socketTask.uvue:63")
          },
        } as CloseSocketOptions)
      }
    },
    methods: {
      connect() {
        if (this.connected || this.connecting) {
          uni.showModal({
            content: '正在连接或者已经连接，请勿重复连接',
            showCancel: false,
          })
          return
        }
        this.connecting = true
        uni.showLoading({
          title: '连接中...',
        })
        this.socketTask = uni.connectSocket({
          url: 'wss://websocket.dcloud.net.cn',
          success: (res : any) => {
            // 这里是接口调用成功的回调，不是连接成功的回调，请注意
            console.log('uni.connectSocket success', res, " at pages/API/websocket/socketTask.uvue:85")
          },
          fail: (err : any) => {
            // 这里是接口调用失败的回调，不是连接失败的回调，请注意
            console.log('uni.connectSocket fail', err, " at pages/API/websocket/socketTask.uvue:89")
          },
        })
        this.socketTask?.onOpen((res : any) => {
          this.connecting = false
          this.connected = true
          uni.hideLoading()
          uni.showToast({
            icon: 'none',
            title: '连接成功',
          })
          console.log('onOpen', res, " at pages/API/websocket/socketTask.uvue:100")
        })
        this.socketTask?.onError((err : any) => {
          this.connecting = false
          this.connected = false
          uni.hideLoading()
          uni.showModal({
            content: '连接失败，可能是websocket服务不可用，请稍后再试',
            showCancel: false,
          })
          console.log('onError', err, " at pages/API/websocket/socketTask.uvue:110")
        })
        this.socketTask?.onMessage((res : OnSocketMessageCallbackResult) => {
			if(res.data instanceof ArrayBuffer){
				var int8 = new Int8Array(res.data)
				this.msg = int8.toString()
				console.log('onMessage', res, " at pages/API/websocket/socketTask.uvue:116")
			}else{
				this.msg = res.data as string
				console.log('onMessage', res, " at pages/API/websocket/socketTask.uvue:119")
			}
         
        })
        this.socketTask?.onClose((res : any) => {
          this.connected = false
          this.socketTask = null
          this.msg = ''
          console.log('onClose', res, " at pages/API/websocket/socketTask.uvue:127")
        })
      },
      send() {
        const data =
          'from ' +
          this.platform +
          ' : ' +
          parseInt(Math.random() * 10000 + '').toString()
        this.socketTask?.send({
          data,
          success: (res : any) => {
            console.log(res, " at pages/API/websocket/socketTask.uvue:139")
          },
          fail: (err : any) => {
            console.log(err, " at pages/API/websocket/socketTask.uvue:142")
          },
        } as SendSocketMessageOptions)
      },
	  sendArrayBuffer() {
	    const data = new ArrayBuffer(2)
		let int8= new Int8Array(data)
		int8[0]=1
		int8[1]=2
		
	    this.socketTask?.send({
	      data,
	      success: (res : any) => {
	        console.log(res, " at pages/API/websocket/socketTask.uvue:155")
	      },
	      fail: (err : any) => {
	        console.log(err, " at pages/API/websocket/socketTask.uvue:158")
	      },
	    } as SendSocketMessageOptions)
	  },
      close() {
        this.socketTask?.close({
          code: 1000,
          reason: 'close reason from client',
          success: (res : any) => {
            console.log('uni.closeSocket success', res, " at pages/API/websocket/socketTask.uvue:167")
          },
          fail: (err : any) => {
            console.log('uni.closeSocket fail', err, " at pages/API/websocket/socketTask.uvue:170")
          },
        } as CloseSocketOptions)
      },
      //自动化测试例专用
      jest_connectSocket() {
        this.socketTask = uni.connectSocket({
          url: 'wss://websocket.dcloud.net.cn',
          success: (_) => {
            this.jest_result++
          },
          fail: (_) => {
            this.jest_result = 0
          },
        })
        this.socketTask?.onOpen((_) => {
          const data =
            'from ' +
            this.platform +
            ' : ' +
            parseInt(Math.random() * 10000 + '').toString()
          this.socketTask?.send({
            data,
            success: (_) => {
              this.jest_result++
            },
            fail: (_) => {
              this.jest_result = 0
            },
          } as SendSocketMessageOptions)
        })
        this.socketTask?.onError((_) => {
          this.jest_result = 0;
        })
      }
    },
  })

export default __sfc__
function GenPagesAPIWebsocketSocketTaskRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode(Fragment, null, [
    createVNode(_component_page_head, utsMapOf({ title: "websocket通讯示例" })),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("text", utsMapOf({ class: "websocket-msg" }), toDisplayString(_ctx.showMsg), 1 /* TEXT */),
        createElementVNode("button", utsMapOf({
          class: "uni-btn-v",
          type: "primary",
          onClick: _ctx.connect
        }), " 连接websocket服务 ", 8 /* PROPS */, ["onClick"]),
        withDirectives(createElementVNode("button", utsMapOf({
          class: "uni-btn-v",
          type: "primary",
          onClick: _ctx.send
        }), " 发送一条消息 ", 8 /* PROPS */, ["onClick"]), [
          [vShow, _ctx.connected]
        ]),
        withDirectives(createElementVNode("button", utsMapOf({
          class: "uni-btn-v",
          type: "primary",
          onClick: _ctx.sendArrayBuffer
        }), " 发送一条ArrayBuffer消息,返回也是ArrayBuffer ", 8 /* PROPS */, ["onClick"]), [
          [vShow, _ctx.connected]
        ]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn-v",
          type: "primary",
          onClick: _ctx.close
        }), " 断开websocket服务 ", 8 /* PROPS */, ["onClick"]),
        createElementVNode("text", utsMapOf({ class: "websocket-tips" }), "发送消息后会收到一条服务器返回的消息（与发送的消息内容一致）")
      ])
    ])
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesAPIWebsocketSocketTaskStyles = [utsMapOf([["uni-btn-v", padStyleMapOf(utsMapOf([["paddingTop", 5], ["paddingRight", 0], ["paddingBottom", 5], ["paddingLeft", 0], ["marginTop", 10], ["marginRight", 0], ["marginBottom", 10], ["marginLeft", 0]]))], ["websocket-msg", padStyleMapOf(utsMapOf([["paddingTop", 40], ["paddingRight", 0], ["paddingBottom", 40], ["paddingLeft", 0], ["textAlign", "center"], ["fontSize", 14], ["lineHeight", "40px"], ["color", "#666666"]]))], ["websocket-tips", padStyleMapOf(utsMapOf([["paddingTop", 40], ["paddingRight", 0], ["paddingBottom", 40], ["paddingLeft", 0], ["textAlign", "center"], ["fontSize", 14], ["lineHeight", "24px"], ["color", "#666666"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
