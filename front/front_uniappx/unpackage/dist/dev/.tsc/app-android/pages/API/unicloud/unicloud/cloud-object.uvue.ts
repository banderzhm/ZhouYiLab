
  const __sfc__ = defineComponent({
    data() {
      return {
        title: '请求云对象',
        todoTitle: '学习编程',
        todoContent: '熟悉uts语法',
        returnTodoTitle: '',
        returnTodoContent: '',
        genericDemoReturnTodoTitle: '',
        genericDemoReturnTodoContent: '',
        failErrCode: '',
        failErrSubject: '',
        failErrDetailTips: '',
        failNumberErrCode: 0,
        successErrCode: -1,
        isUniTest: false
      }
    },
    methods: {
      notify(content : string, title : string) {
        if (!this.isUniTest) {
          uni.showModal({
            title,
            content,
            showCancel: false
          })
        } else {
          console.log(title, content, " at pages/API/unicloud/unicloud/cloud-object.uvue:65")
        }
      },
      async addTodo() : Promise<void> {
        const todo = uniCloud.importObject('todo', {
          customUI: this.isUniTest
        })
        const title = this.todoTitle
        const content = this.todoContent
        await todo.add<UTSJSONObject>(title, content).then((res : UTSJSONObject) => {
          this.returnTodoTitle = res['title'] as string
          this.returnTodoContent = res['content'] as string
          this.notify(res['showMessage'] as string, '提示')
        }).catch((err : any | null) => {
          console.log(err, " at pages/API/unicloud/unicloud/cloud-object.uvue:79")
          const error = err as UniCloudError
          console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:81")
        })
      },
      async addTodoWithGeneric() : Promise<void> {
        type AddTodoResult = { __$originalPosition?: UTSSourceMapPosition<"AddTodoResult", "pages/API/unicloud/unicloud/cloud-object.uvue", 85, 14>;
          title : string,
          content : string,
          showMessage : string
        }
        const todo = uniCloud.importObject('todo', {
          customUI: this.isUniTest
        })
        const title = this.todoTitle
        const content = this.todoContent
        await todo.add<AddTodoResult>(title, content).then((res : AddTodoResult) => {
          this.genericDemoReturnTodoTitle = res.title
          this.genericDemoReturnTodoContent = res.content
          this.notify(res.showMessage, '提示')
        }).catch((err : any | null) => {
          console.log(err, " at pages/API/unicloud/unicloud/cloud-object.uvue:100")
          const error = err as UniCloudError
          console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:102")
        })
      },
      async randomFail() : Promise<void> {
        const todoObj = uniCloud.importObject('todo', {
          errorOptions: {
            retry: true
          }
        })
        await todoObj.randomFail().then((res : UTSJSONObject) => {
          this.notify(res['showMessage'] as string, '提示')
        }).catch((err : any | null) => {
          const error = err as UniCloudError
          console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:115")
        })
      },
      async fail() : Promise<void> {
        const todo = uniCloud.importObject('todo', {
          customUI: this.isUniTest
        })
        await todo.fail().then((res : UTSJSONObject) => {
          this.notify('todo.fail应调用失败，此处错误的触发了成功回调', '错误')
          console.log('todo.fail: ', res, " at pages/API/unicloud/unicloud/cloud-object.uvue:124");
        }).catch((err : any | null) => {
          const error = err as UniCloudError
          this.failErrCode = error.errCode as string
          this.failErrSubject = error.errSubject as string
          const detail = error.detail
          if (detail != null && detail['tips'] != null) {
            this.failErrDetailTips = detail['tips'] as string
          }
          console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:133")
        })
      },
      async failWithNumberErrCode() : Promise<void> {
        const todo = uniCloud.importObject('todo', {
          customUI: this.isUniTest
        })
        await todo.failWithNumberErrCode().then((res : UTSJSONObject) => {
          this.notify('todo.fail应调用失败，此处错误的触发了成功回调', '错误')
          console.log('todo.fail: ', res, " at pages/API/unicloud/unicloud/cloud-object.uvue:142");
        }).catch((err : any | null) => {
          const error = err as UniCloudError
          this.failNumberErrCode = error.errCode as number
          console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:146")
        })
      },
      async success() : Promise<void> {
        const todo = uniCloud.importObject('todo', {
          customUI: this.isUniTest
        })
        await todo.success().then((res : UTSJSONObject) => {
          this.successErrCode = res['errCode'] as number
          this.notify(res['showMessage'] as string, '提示')
        }).catch((err : any | null) => {
          const error = err as UniCloudError
          console.error(error, " at pages/API/unicloud/unicloud/cloud-object.uvue:158")
        })
      }
    }
  })

export default __sfc__
function GenPagesAPIUnicloudUnicloudCloudObjectRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.addTodo
          }), "添加Todo", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.addTodoWithGeneric
          }), " 添加Todo传入泛型 ", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.randomFail
          }), "随机触发失败重试", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.fail
          }), "云对象失败调用", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.failWithNumberErrCode
          }), " 云对象数字错误码 ", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v uni-common-mt" }), [
          createElementVNode("button", utsMapOf({
            type: "primary",
            onClick: _ctx.success
          }), "云对象成功调用", 8 /* PROPS */, ["onClick"])
        ])
      ])
    ])
  ])
}
const GenPagesAPIUnicloudUnicloudCloudObjectStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
