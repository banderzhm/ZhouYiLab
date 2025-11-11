
const __sfc__ = defineComponent({
  data() {
    return {
      log: [] as string[],
      objArg: {},
    }
  },
  methods: {
    fn(res : string) {
      this.log.push(res)
    },
    fn2(res : string) {
      this.log.push(res)
    },
    on() {
      uni.$on('test', this.fn)
    },
    on2() {
      uni.$on('test', this.fn2)
    },
    onObj() {
      uni.$on('test-obj', (res : UTSJSONObject) => {
        this.objArg = res
      })
    },
    once() {
      uni.$once('test', this.fn)
    },
    off() {
      uni.$off('test', this.fn)
    },
    offAll() {
      uni.$off('test')
    },
    emit() {
      uni.$emit('test', 'msg:' + Date.now())
    },
    emitWithObj() {
      uni.$emit('test-obj', { a: 1, b: 2 })
    },
    clear() {
      this.log.length = 0
    },
    testReturnId(){
      const id1 = uni.$on('test-return-id', this.fn)
      uni.$emit('test-return-id', '触发 test-return-id $on fn')
      uni.$off('test-return-id', id1)
      uni.$emit('test-return-id', '触发 test-return-id $on fn')

      uni.$once('test-return-id', this.fn)
      uni.$emit('test-return-id', '触发 test-return-id $once fn')
      uni.$emit('test-return-id', '触发 test-return-id $once fn')
      const id2 = uni.$once('test-id', this.fn)
      uni.$off('test-return-id', id2)
      uni.$emit('test-return-id', '触发 test-return-id $once fn')
    },
    testEmitNoArgs() {
      uni.$on('test-emit-no-args', () => {
        this.log.push('test-emit-no-args')
      })
      uni.$emit('test-emit-no-args')
      uni.$off('test-emit-no-args')
    },
    testEmitMultipleArgs() {
      uni.$on('test-emit-multiple-args', (arg1 : string, arg2 : number) => {
        this.log.push(`${arg1}_${arg2}`)
      })
      uni.$emit('test-emit-multiple-args', 'arg1', 2)
      uni.$off('test-emit-multiple-args')
    }
  },
})

export default __sfc__
function GenPagesAPIEventBusEventBusRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", utsMapOf({ class: "box" }), [
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.on
      }), "开始监听", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.once
      }), "监听一次", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.off
      }), "取消监听", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.emit
      }), "触发监听", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "uni-btn",
        onClick: _ctx.clear
      }), "清空消息", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", null, [
        createElementVNode("view", utsMapOf({ class: "uni-btn" }), "收到的消息："),
        createElementVNode("view", utsMapOf({ class: "uni-btn" }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.log, (item, index, __index, _cached): any => {
            return createElementVNode("view", utsMapOf({ key: index }), toDisplayString(item), 1 /* TEXT */)
          }), 128 /* KEYED_FRAGMENT */)
        ]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          onClick: _ctx.onObj
        }), "开始监听 obj 参数", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          onClick: _ctx.emitWithObj
        }), "触发监听 obj 参数", 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({ class: "uni-btn" }), [
          createElementVNode("text", null, "接收到的 obj 参数："),
          createElementVNode("text", null, toDisplayString(JSON.stringify(_ctx.objArg)), 1 /* TEXT */)
        ]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          onClick: _ctx.testReturnId
        }), "测试返回 id", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          onClick: _ctx.testEmitNoArgs
        }), "测试 $emit 无参", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({
          class: "uni-btn",
          onClick: _ctx.testEmitMultipleArgs
        }), "测试 $emit 多个参数", 8 /* PROPS */, ["onClick"])
      ])
    ])
  ])
}
const GenPagesAPIEventBusEventBusStyles = [utsMapOf([["box", padStyleMapOf(utsMapOf([["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))]])]
