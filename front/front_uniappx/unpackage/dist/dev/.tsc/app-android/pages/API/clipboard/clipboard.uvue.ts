
  const __sfc__ = defineComponent({
    data() {
      return {
        title: 'get/setClipboardData',
        data: '',
        // 自动化测试
        getDataTest: '',
        setClipboardTest: false
      }
    },
    // 页面卸载时清空剪贴板，避免影响其他测试用例
    onUnload() {
      uni.setClipboardData({
        data: ''
      })
    },
    methods: {
      dataChange: function (e:UniInputEvent) {
        this.data = e.detail.value
      },
      getClipboard: function () {
        uni.getClipboardData({
          success: (res) => {
            console.log(res.data, " at pages/API/clipboard/clipboard.uvue:42");
            this.getDataTest = res.data;
            const content = res.data != "" ? '剪贴板内容为:' + res.data : '剪贴板暂无内容';
            uni.showModal({
              content,
              title: '读取剪贴板',
              showCancel: false
            })
          },
          fail: (err) => {
            uni.showModal({
              content: `读取剪贴板失败: ${err.errMsg}`,
              showCancel: false
            })
          }
        });
      },
      setClipboard: function () {
        if (this.data.length == 0) {
          uni.showModal({
            title: '设置剪贴板失败',
            content: '内容不能为空',
            showCancel: false
          })
        } else {
          uni.setClipboardData({
            data: this.data,
            success: () => {
              this.setClipboardTest = true
              // 成功处理
              uni.showToast({
                title: '设置剪贴板成功',
                icon: "success"
              })
            },
            fail: () => {
              // bug：自动化测试时设置成功也进入了fail
              this.setClipboardTest = false
              // 失败处理
              uni.showToast({
                title: '储存数据失败!',
                icon: "none"
              })
            }
          });
        }
      }
    }
  })

export default __sfc__
function GenPagesAPIClipboardClipboardRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("view", null, [
    createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
    createElementVNode("view", utsMapOf({ class: "uni-padding-wrap" }), [
      createElementVNode("view", utsMapOf({ class: "uni-title" }), "请输入剪贴板内容"),
      createElementVNode("view", utsMapOf({ class: "uni-list" }), [
        createElementVNode("view", utsMapOf({ class: "uni-list-cell" }), [
          createElementVNode("input", utsMapOf({
            class: "uni-input",
            type: "text",
            placeholder: "请输入剪贴板内容",
            value: _ctx.data,
            onInput: _ctx.dataChange
          }), null, 40 /* PROPS, NEED_HYDRATION */, ["value", "onInput"])
        ])
      ]),
      createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
        createElementVNode("button", utsMapOf({
          type: "primary",
          onClick: _ctx.setClipboard
        }), "存储数据", 8 /* PROPS */, ["onClick"]),
        createElementVNode("button", utsMapOf({ onClick: _ctx.getClipboard }), "读取数据", 8 /* PROPS */, ["onClick"])
      ])
    ])
  ])
}
const GenPagesAPIClipboardClipboardStyles = []

import _easycom_page_head from '@/components/page-head/page-head.vue'
