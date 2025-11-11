
  const __sfc__ = defineComponent({
    data() {
      return {
        text: "<span>hello uni-app x!</span><br/><span>uni-app x，终极跨平台方案</span>",
        richTextHeight: 0,
        richTextElement: null as UniElement | null,
        // 自动化测试
        autoTest: false,
        testNodes: '<img src="https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/uni@2x.png"></img>',
        isItemClickTrigger: false,
        richTextStr: false
      }
    },
    onReady() {
      this.richTextElement = uni.getElementById('richtext') as UniElement
      setTimeout(() => {
        this.updateRichTextHeight()
      }, 2500)
    },
    methods: {
      changeText() {
        if (this.text === "<span>hello uni-app x!</span><br/><span>uni-app x，终极跨平台方案</span>") {
          this.text = "<h1>hello uni-app x!</h1><br/><h2>uni-app x，终极跨平台方案</h2>"
        } else {
          this.text = "<span>hello uni-app x!</span><br/><span>uni-app x，终极跨平台方案</span>"
        }
        setTimeout(() => {
          this.updateRichTextHeight()
        }, 200)
      },
      updateRichTextHeight() {
        if (this.richTextElement != null) {
          this.richTextElement!.getBoundingClientRectAsync()!.then((elRect: DOMRect) => {
            this.richTextHeight = elRect.height
            console.log('richTextHeight:', this.richTextHeight, " at pages/component/rich-text/rich-text.uvue:72")
          })
        }
      },
      // 自动化测试
      itemClickForTest(_ : UniRichTextItemClickEvent) {
        this.isItemClickTrigger = true;
      },
      getBoundingClientRectForTest() : DOMRect {
        return uni.getElementById('test-rich-text')?.getBoundingClientRect()!;
      },
      richTextParentClick() {
        this.richTextStr = true;
      }
    }
  })

export default __sfc__
function GenPagesComponentRichTextRichTextRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_navigator = resolveComponent("navigator")

  return createElementVNode(Fragment, null, [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: "rich-text" })),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createVNode(_component_navigator, utsMapOf({ url: "/pages/component/rich-text/rich-text-tags" }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode("button", utsMapOf({ type: "primary" }), "rich-text渲染单个HTML标签示例")
            ]),
            _: 1 /* STABLE */
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-btn-v" }), [
          createVNode(_component_navigator, utsMapOf({ url: "/pages/component/rich-text/rich-text-complex" }), utsMapOf({
            default: withSlotCtx((): any[] => [
              createElementVNode("button", utsMapOf({ type: "primary" }), "rich-text渲染复杂HTML示例")
            ]),
            _: 1 /* STABLE */
          }))
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("button", utsMapOf({
            type: "default",
            onClick: _ctx.changeText
          }), "修改文本内容", 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({
          class: "text-box",
          id: "rich-text-parent",
          onClick: _ctx.richTextParentClick
        }), [
          createElementVNode("rich-text", utsMapOf({
            id: "richtext",
            style: normalizeStyle(utsMapOf({"border":"1px","border-style":"solid","border-color":"red"})),
            nodes: _ctx.text
          }), null, 12 /* STYLE, PROPS */, ["nodes"]),
          createElementVNode("view", null, [
            createElementVNode("text", null, "rich-text-parent"),
            createElementVNode("text", utsMapOf({ id: "rich-text-str" }), toDisplayString(_ctx.richTextStr), 1 /* TEXT */)
          ])
        ], 8 /* PROPS */, ["onClick"]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "selectable")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box2" }), [
          createElementVNode("rich-text", utsMapOf({
            style: normalizeStyle(utsMapOf({"height":"80px"})),
            selectable: true,
            nodes: _ctx.text
          }), null, 12 /* STYLE, PROPS */, ["nodes"])
        ])
      ])
    ]),
    isTrue(_ctx.autoTest)
      ? createElementVNode("rich-text", utsMapOf({
          key: 0,
          id: "test-rich-text",
          nodes: _ctx.testNodes,
          selectable: true,
          onItemclick: _ctx.itemClickForTest,
          style: normalizeStyle(utsMapOf({"position":"fixed","width":"100px","height":"100px"}))
        }), null, 44 /* STYLE, PROPS, NEED_HYDRATION */, ["nodes", "onItemclick"])
      : createCommentVNode("v-if", true)
  ], 64 /* STABLE_FRAGMENT */)
}
const GenPagesComponentRichTextRichTextStyles = [utsMapOf([["text-box", padStyleMapOf(utsMapOf([["paddingTop", 20], ["paddingRight", 0], ["paddingBottom", 20], ["paddingLeft", 0], ["backgroundColor", "#FFFFFF"]]))], ["text-box2", padStyleMapOf(utsMapOf([["top", 20], ["backgroundColor", "#FFFFFF"]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
