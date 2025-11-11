import { state } from '@/store/index.uts'

  // 作为页面渲染时，props会接收url中的参数
  // 作为组件使用时，可以正常传递props
  
const __sfc__ = defineComponent({
  __name: 'detail',
  props: {
    post_id: { type: String, default: '' },
    cover: { type: String, default: '' },
    title: { type: String, default: '' },
    isWideScreen: { type: Boolean, default: false },
    shareKey: { type: String, default: '' },
    // list-news预加载传入的详情内容，仅用于宽屏第一个新闻
    firstDetailContent: { type: String, default: '' }
  },
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const props = __props

  const isDarkMode = computed(() => state.isDarkMode)
  const htmlNodes = ref("")
  // 标记是否已经用过firstDetailContent
  const usedContent = ref(false)
  const btCover = ref("")


  function processHtmlContent(content: string, color: string): string {
    // 先给已有 style 的 <p> 追加 color
    content = content.replace(
      new RegExp('(<p[^>]*style=")([^"]*)"', 'g'),
      `$1$2;color:${color};"`
    );
    // 再给没有 style 的 <p> 加 style
    content = content.replace(
      new RegExp('<p((?!style)[^>]*)>', 'g'),
      `<p$1 style="color:${color};">`
    )
    // 替换 <h2 ...> 为 <p style="color:...;"><big>
    content = content.replace(
      new RegExp('<h2[^>]*>', 'g'),
      `<p style="color:${color};"><big>`
    )
    // 替换 </h2> 为 </big></p>
    content = content.replace(
      new RegExp('</h2>', 'g'),
      '</big></p>'
    )
    // 替换 <h3 ...> 为 <p style="color:...;"><big>
    content = content.replace(
      new RegExp('<h3[^>]*>', 'g'),
      `<p style="color:${color};"><big>`
    )
    // 替换 </h3> 为 </big></p>
    content = content.replace(
      new RegExp('</h3>', 'g'),
      '</big></p>'
    )
    // 匹配以 </p>、</img> 结尾，后面紧跟裸文本的情况
    content = content.replace(
      new RegExp('(<\\/p>|<\\/img>)([^<\\s][^<]*)', 'g'),
      `$1<p style="color:${color};">$2</p>`
    )
    // 先给已有 style 的 <span> 追加 color
    content = content.replace(
      new RegExp('(<span[^>]*style=")([^"]*)"', 'g'),
      `$1$2;color:${color};"`
    );
    // 再给没有 style 的 <span> 加 style
    content = content.replace(
      new RegExp('<span((?!style)[^>]*)>', 'g'),
      `<span$1 style="color:${color};">`
    )
    return content
  }


  // 适配深色模式
  function adaptContentForDarkMode(content: string): string {

    if (isDarkMode.value) {
      return processHtmlContent(content, '#ffffff')
    }

    return content
  }

  function getDetail(post_id: string) {
    uni.request({
      url: 'https://unidemo.dcloud.net.cn/api/news/36kr/' + post_id,
      success: (data) => {
        if (data.statusCode == 200) {
          const result = data.data as UTSJSONObject
          let content = result["content"] as string
          htmlNodes.value = adaptContentForDarkMode(content)
        }
      },
      fail: () => {
        console.log('fail', " at pages/template/list-news/detail/detail.uvue:115")
      }
    })
  }

  // 监听post_id变化，当id变化时加载数据
  watch(():string => props.post_id, (newVal:string) => {
    if (newVal != '') {
      // 优先用传入的预载详情内容（只在第一个新闻时有值）
      if (props.firstDetailContent !== '') {
        let content = props.firstDetailContent
        htmlNodes.value = adaptContentForDarkMode(content)
        usedContent.value = true
      } else {
        getDetail(newVal)
        usedContent.value = false
      }
    }
  }, { immediate: true, deep: true })

  // 只有没用过firstDetailContent时才赋值，防止重复
  watch(():string => props.firstDetailContent, (newVal:string) => {
    if (!usedContent.value && newVal !== '') {
      let content = newVal
      htmlNodes.value = adaptContentForDarkMode(content)
      usedContent.value = true
    }
  })

  onLoad(() => {
    if (!props.isWideScreen) {
      btCover.value = props.cover

      btCover.value = atob(btCover.value)

    }
  })

return (): any | null => {

  return createElementVNode("scroll-view", utsMapOf({
    class: normalizeClass([unref(isDarkMode) ? 'theme-dark' : 'theme-light', "detail-container"]),
    style: normalizeStyle(utsMapOf({"flex":"1"}))
  }), [
    isTrue(_ctx.isWideScreen)
      ? createElementVNode("view", utsMapOf({ key: 0 }), [
          createElementVNode("image", utsMapOf({
            class: "banner-img",
            src: _ctx.cover
          }), null, 8 /* PROPS */, ["src"]),
          createElementVNode("text", utsMapOf({ class: "banner-title" }), toDisplayString(_ctx.title), 1 /* TEXT */)
        ])
      : createElementVNode("share-element", utsMapOf({
          key: 1,
          "share-key": _ctx.shareKey
        }), [
          createElementVNode("view", null, [
            createElementVNode("image", utsMapOf({
              class: "banner-img",
              src: unref(btCover)
            }), null, 8 /* PROPS */, ["src"]),
            createElementVNode("text", utsMapOf({ class: "banner-title" }), toDisplayString(_ctx.title), 1 /* TEXT */)
          ])
        ], 8 /* PROPS */, ["share-key"]),
    isTrue(unref(htmlNodes))
      ? createElementVNode("rich-text", utsMapOf({
          key: 2,
          class: "rich-text",
          nodes: unref(htmlNodes),
          mode: "native"
        }), null, 8 /* PROPS */, ["nodes"])
      : createCommentVNode("v-if", true)
  ], 6 /* CLASS, STYLE */)
}
}

})
export default __sfc__
const GenPagesTemplateListNewsDetailDetailStyles = [utsMapOf([["detail-container", padStyleMapOf(utsMapOf([["backgroundColor", "var(--background-color,#f8f8f8)"]]))], ["banner", padStyleMapOf(utsMapOf([["height", 180], ["overflow", "hidden"], ["position", "relative"], ["backgroundColor", "var(--background-color,#f8f8f8)"]]))], ["banner-img", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["banner-title", padStyleMapOf(utsMapOf([["maxHeight", 42], ["overflow", "hidden"], ["position", "absolute"], ["left", 15], ["bottom", 15], ["width", "90%"], ["fontSize", 16], ["fontWeight", "400"], ["lineHeight", "21px"], ["color", "#FFFFFF"]]))], ["rich-text", padStyleMapOf(utsMapOf([["paddingTop", 3], ["paddingRight", 3], ["paddingBottom", 0], ["paddingLeft", 3], ["color", "var(--text-color,#333333)"]]))]])]
