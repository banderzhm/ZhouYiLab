
  const __sfc__ = defineComponent({
    data() {
      return {
        title: '服务端通知通道',
        messages: [] as string[]
      }
    },
    methods: {
      async receiveMessage() {
        this.messages.splice(0, this.messages.length)
        const sseChannel = new uniCloud.SSEChannel()
        sseChannel.on('message', (message?: any | null) => {
          if(typeof message === 'string') {
            this.messages.push(message)
          }
        })
        sseChannel.on('end', (message?: any | null) => {
          if(typeof message === 'string') {
            this.messages.push(message)
          }
        })
        sseChannel.on('open', () => {
          console.log('sseChannel open', " at pages/API/unicloud/unicloud/sse-channel.uvue:40")
        })
        sseChannel.on('close', () => {
          console.log('sseChannel close', " at pages/API/unicloud/unicloud/sse-channel.uvue:43")
        })
        sseChannel.on('error', (error: UniCloudError) => {
          console.log('sseChannel error: ' + error.message, " at pages/API/unicloud/unicloud/sse-channel.uvue:46")
        })
        await sseChannel.open()
        const res = await uniCloud.callFunction({
          name: 'sse',
          data: {
            sseChannel
          }
        } as UniCloudCallFunctionOptions)
        console.log(res, " at pages/API/unicloud/unicloud/sse-channel.uvue:55")
      }
    }
  })

export default __sfc__
function GenPagesAPIUnicloudUnicloudSseChannelRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("button", utsMapOf({ onClick: _ctx.receiveMessage }), "接收服务端通知", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", null, [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.messages, (item, __key, __index, _cached): any => {
          return createElementVNode("view", null, toDisplayString(item), 1 /* TEXT */)
        }), 256 /* UNKEYED_FRAGMENT */)
      ])
    ])
  ])
}
const GenPagesAPIUnicloudUnicloudSseChannelStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
