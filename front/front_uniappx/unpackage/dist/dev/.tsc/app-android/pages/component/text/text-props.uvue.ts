
  const __sfc__ = defineComponent({
    data() {
      return {
        decodeStr: '&lt; &gt; &amp; &apos;',
        title: 'text-props',
        multiLineText:
          'HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言；HBuilderX，轻巧、极速，极客编辑器；uni-app x，终极跨平台方案；uts，大一统语言',
        singleLineText: 'uni-app x，终极跨平台方案',
        // 自动化测试
        autoTest: false,
        nestedText: '三级节点文本',
        emptyText: '空文本',
        heightText: '设置高度文本',
        isNestedText1TapTriggered: false,
        isNestedText2TapTriggered: false
      }
    },
    methods: {
      nestedText1Tap() {
        uni.showModal({
          title: '点击了',
          content: '一级节点黑色',
          showCancel: false
        });
      },
      nestedText2Tap(e : UniPointerEvent) {
        e.stopPropagation();
        uni.showModal({
          title: '点击了',
          content: '二级节点红色且背景色黄色',
          showCancel: false
        });
      },
      nestedText3Tap(e : UniPointerEvent) {
        e.stopPropagation();
        uni.showModal({
          title: '点击了',
          content: 'App三级节点不继承二级的颜色',
          showCancel: false
        });
      },
      iconTap() {
        uni.showModal({
          title: '点击了',
          content: '字体图标1',
          showCancel: false
        });
      },
      iconTap2() {
        uni.showModal({
          title: '点击了',
          content: '字体图标2',
          showCancel: false
        });
      },
      // 自动化测试
      setNestedText() {
        this.nestedText = "修改三级节点文本";
      },
      setEmptyText() {
        this.emptyText = "";
      },
      setHeightText() {
        this.heightText = "修改设置高度文本";
      },
      getBoundingClientRectForTest() : DOMRect | null {
        return uni.getElementById('nested-text2')?.getBoundingClientRect();
      },
      nestedText1TapForTest() {
        this.isNestedText1TapTriggered = true;
      },
      nestedText2TapForTest() {
        this.isNestedText2TapTriggered = true;
      }
    }
  })

export default __sfc__
function GenPagesComponentTextTextPropsRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)

  return createElementVNode("scroll-view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    createElementVNode("view", null, [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("view", utsMapOf({ class: "uni-padding-wrap uni-common-mt" }), [
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-title-text" }), "text相关属性示例")
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "根据宽度自动折行")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", null, toDisplayString(_ctx.multiLineText), 1 /* TEXT */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "\\n换行")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", null, "\n 换行"),
          createElementVNode("text", null, "\\n 换行"),
          createElementVNode("text", null, "\\\n 换行"),
          createElementVNode("text", null, "\n 换行 \\n 换行 \\\n 换行 \\\\n 换行 \\\\\n 换行"),
          createElementVNode("text", utsMapOf({ space: "nbsp" }), "HBuilderX，轻巧、极速，极客编辑器;\nuni-app x，终极跨平台方案;")
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "截断（clip）")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({
            class: "uni-flex-item",
            style: normalizeStyle(utsMapOf({"width":"100%","text-overflow":"clip","white-space":"nowrap"}))
          }), toDisplayString(_ctx.multiLineText), 5 /* TEXT, STYLE */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "截断（ellipsis）")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({
            class: "uni-flex-item",
            style: normalizeStyle(utsMapOf({"width":"100%","text-overflow":"ellipsis","white-space":"nowrap"}))
          }), toDisplayString(_ctx.multiLineText), 5 /* TEXT, STYLE */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "selectable")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({ selectable: true }), toDisplayString(_ctx.singleLineText), 1 /* TEXT */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "space"),
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "依次为nbsp ensp emsp效果")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({ space: "nbsp" }), toDisplayString(_ctx.singleLineText), 1 /* TEXT */),
          createElementVNode("text", utsMapOf({ space: "ensp" }), toDisplayString(_ctx.singleLineText), 1 /* TEXT */),
          createElementVNode("text", utsMapOf({ space: "emsp" }), toDisplayString(_ctx.singleLineText), 1 /* TEXT */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "decode"),
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "依次为lt gt amp apos nbsp ensp emsp效果")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({ decode: true }), toDisplayString(_ctx.decodeStr), 1 /* TEXT */),
          createElementVNode("text", utsMapOf({ decode: true }), "uni-app x，终极跨平台方案"),
          createElementVNode("text", utsMapOf({ decode: true }), "uni-app x，终极跨平台方案"),
          createElementVNode("text", utsMapOf({ decode: true }), "uni-app x，终极跨平台方案")
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "嵌套1")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({ onClick: _ctx.nestedText1Tap }), [
            "一级节点黑色 ",
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"color":"red","background-color":"yellow"})),
              onClick: _ctx.nestedText2Tap
            }), [
              "二级节点红色且背景色黄色 ",
              createElementVNode("text", utsMapOf({
                style: normalizeStyle(utsMapOf({"text-decoration-line":"underline","color":"blue"})),
                onClick: _ctx.nestedText3Tap
              }), "App三级节点不继承二级的颜色", 12 /* STYLE, PROPS */, ["onClick"])
            ], 12 /* STYLE, PROPS */, ["onClick"]),
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"font-size":"50px"}))
            }), "二级节点大字体", 4 /* STYLE */)
          ], 8 /* PROPS */, ["onClick"])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "嵌套2")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", null, [
            createElementVNode("text", null, "文字应居中显示")
          ])
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "padding和border")
        ]),
        createElementVNode("view", utsMapOf({ class: "text-box" }), [
          createElementVNode("text", utsMapOf({ class: "text-padding-border" }), "hello uni-app x"),
          createElementVNode("text", utsMapOf({
            class: "text-padding-border",
            style: normalizeStyle(utsMapOf({"width":"200px"}))
          }), "hello uni-app x", 4 /* STYLE */),
          createElementVNode("text", utsMapOf({
            class: "text-padding-border",
            style: normalizeStyle(utsMapOf({"height":"100px"}))
          }), "hello uni-app x", 4 /* STYLE */),
          createElementVNode("text", utsMapOf({
            class: "text-padding-border",
            style: normalizeStyle(utsMapOf({"width":"200px","height":"100px"}))
          }), "hello uni-app x", 4 /* STYLE */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "含换行符的多行文本(3行)，但不自动换行，可横向滚动")
        ]),
        createElementVNode("scroll-view", utsMapOf({
          class: "text-container",
          direction: "horizontal"
        }), [
          createElementVNode("text", utsMapOf({
            style: normalizeStyle(utsMapOf({"white-space":"nowrap","align-self":"flex-start"}))
          }), "HBuilderX，轻巧、极速，极客编辑器；\nuni-app x，是下一代 uni-app，是一个跨平台应用开发引擎。uni-app x 是一个庞大的工程，它包括uts语言、uvue渲染引擎、uni的组件和API、以及扩展机制。\nuts是一门类ts的、跨平台的、新语言。", 4 /* STYLE */)
        ]),
        createElementVNode("view", utsMapOf({ class: "uni-title" }), [
          createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "点击事件测试")
        ]),
        createElementVNode("view", utsMapOf({
          class: "text-box",
          style: normalizeStyle(utsMapOf({"flex-direction":"row"}))
        }), [
          createElementVNode("text", utsMapOf({
            class: "text-icon",
            style: normalizeStyle(utsMapOf({"background-color":"aqua"})),
            onClick: _ctx.iconTap
          }), toDisplayString(String.fromCharCode(parseInt('E650',16))), 13 /* TEXT, STYLE, PROPS */, ["onClick"]),
          createElementVNode("text", utsMapOf({
            class: "text-icon",
            style: normalizeStyle(utsMapOf({"background-color":"yellow"})),
            onClick: _ctx.iconTap2
          }), toDisplayString(String.fromCharCode(parseInt('EA08',16))), 13 /* TEXT, STYLE, PROPS */, ["onClick"])
        ], 4 /* STYLE */),
        isTrue(_ctx.autoTest)
          ? createElementVNode("view", utsMapOf({ key: 0 }), [
              createElementVNode("view", utsMapOf({ class: "uni-row" }), [
                createElementVNode("text", utsMapOf({ id: "empty-text" }))
              ]),
              createElementVNode("view", utsMapOf({ class: "uni-row" }), [
                createElementVNode("text", utsMapOf({ id: "empty-text2" }), toDisplayString(_ctx.emptyText), 1 /* TEXT */)
              ]),
              createElementVNode("view", utsMapOf({ class: "uni-row" }), [
                createElementVNode("text", utsMapOf({
                  id: "empty-text3",
                  style: normalizeStyle(utsMapOf({"width":"100px","height":"100px"}))
                }), toDisplayString(_ctx.emptyText), 5 /* TEXT, STYLE */)
              ]),
              createElementVNode("text", null, [
                "一级节点文本 ",
                createElementVNode("text", null, [
                  "二级节点文本 ",
                  createElementVNode("text", utsMapOf({ id: "nested-text" }), toDisplayString(_ctx.nestedText), 1 /* TEXT */)
                ])
              ]),
              createElementVNode("text", utsMapOf({
                id: "height-text",
                style: normalizeStyle(utsMapOf({"height":"50px"}))
              }), toDisplayString(_ctx.heightText), 5 /* TEXT, STYLE */),
              createElementVNode("text", utsMapOf({
                style: normalizeStyle(utsMapOf({"position":"fixed"})),
                id: "nested-text2",
                onClick: _ctx.nestedText1TapForTest
              }), [
                "1 ",
                createElementVNode("text", utsMapOf({ onClick: _ctx.nestedText2TapForTest }), [
                  "2 ",
                  createElementVNode("text", null, "3")
                ], 8 /* PROPS */, ["onClick"])
              ], 12 /* STYLE, PROPS */, ["onClick"])
            ])
          : createCommentVNode("v-if", true)
      ])
    ])
  ], 4 /* STYLE */)
}
const GenPagesComponentTextTextPropsStyles = [utsMapOf([["text-box", padStyleMapOf(utsMapOf([["marginBottom", 20], ["paddingTop", 20], ["paddingRight", 0], ["paddingBottom", 20], ["paddingLeft", 0], ["backgroundColor", "#ffffff"], ["justifyContent", "center"], ["alignItems", "center"]]))], ["text-container", padStyleMapOf(utsMapOf([["width", "100%"], ["backgroundColor", "#ffffff"], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))], ["text", padStyleMapOf(utsMapOf([["fontSize", 15], ["color", "#353535"], ["lineHeight", "27px"], ["textAlign", "center"]]))], ["text-padding-border", padStyleMapOf(utsMapOf([["marginTop", 5], ["paddingTop", 20], ["paddingRight", 20], ["paddingBottom", 20], ["paddingLeft", 20], ["borderTopWidth", 5], ["borderRightWidth", 5], ["borderBottomWidth", 5], ["borderLeftWidth", 5], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#FF0000"], ["borderRightColor", "#FF0000"], ["borderBottomColor", "#FF0000"], ["borderLeftColor", "#FF0000"], ["textAlign", "center"]]))], ["text-icon", padStyleMapOf(utsMapOf([["fontFamily", "uni-icon"], ["fontSize", 100]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
