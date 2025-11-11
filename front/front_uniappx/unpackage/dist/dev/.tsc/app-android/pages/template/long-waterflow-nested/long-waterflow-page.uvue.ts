import _easycom_uni_loading from '@/uni_modules/uni-loading/components/uni-loading/uni-loading.uvue'
type flowItem = { __$originalPosition?: UTSSourceMapPosition<"flowItem", "pages/template/long-waterflow-nested/long-waterflow-page.uvue", 39, 8>;
    plugin_id : number,
    plugin_img_link : string,
    plugin_name : string,
    plugin_intro : string,
    score : number,
    tags : Array<string>,
    update_date : string,
    author_name : string,
  }

  type ResponseDataType = { __$originalPosition?: UTSSourceMapPosition<"ResponseDataType", "pages/template/long-waterflow-nested/long-waterflow-page.uvue", 50, 8>;
    code : number,
    data : flowItem[]
  }

  
const __sfc__ = defineComponent({
  __name: 'long-waterflow-page',
  props: {
    type: { type: String, default: '' },
    preload: { type: Boolean, default: false },
    id: { type: String, default: '' }
  },
  setup(__props, { expose: __expose }: SetupContext): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const SERVER_URL = "https://unidemo.dcloud.net.cn/plugin/uniappx-plugin-list"
  const PAGE_SIZE = 10; // 最大值 10

  const props = __props

  const loading = ref(false);
  const datagrid = ref([] as flowItem[]);
  const isEnded = ref(false);
  const loadingError = ref('');
  const currentPage = ref(1);

  const loadingText = computed(() : string => {
    if (loading.value) {
      return "加载中..."
    } else if (isEnded.value) {
      return "没有更多了"
    } else if (loadingError.value.length > 0) {
      return loadingError.value
    } else {
      return ""
    }
  });

  function loadData(loadComplete : (() => void) | null) {
    if (loading.value || isEnded.value) {
      return
    }
    loading.value = true

    uni.request<ResponseDataType>({
      url: SERVER_URL,
      data: {
        type: props.type,
        page: currentPage.value,
        page_size: PAGE_SIZE
      },
      success: (res) => {
        const responseData = res.data
        if (responseData == null) {
          return
        }

        datagrid.value.push(...responseData.data)

        if (responseData.data.length == 0) {
          isEnded.value = true
        } else {
          currentPage.value++
        }
      },
      fail: (err) => {
        loadingError.value = err.errMsg
      },
      complete: () => {
        loading.value = false
        if (loadComplete != null) {
          loadComplete()
        }
      }
    })
  }

  function refreshData(loadComplete : (() => void) | null) {
    datagrid.value.length = 0
    currentPage.value = 1
    loadData(loadComplete)
  }

  // score 0 ~ 50
  function convertToStarUnicode(score : number) : string {
    const fill_code = '\ue879'
    const half_code = '\ue87a'
    const null_code = '\ue87b'

    const fillStarCount = parseInt(score / 10 % 10 + '')
    const halfStarCount = score % 10 >= 5 ? 1 : 0
    const nullStarCount = 5 - fillStarCount - halfStarCount

    let result = ''
    if (fillStarCount > 0) { result += fill_code.repeat(fillStarCount) }
    if (halfStarCount > 0) { result += half_code.repeat(halfStarCount) }
    if (nullStarCount > 0) { result += null_code.repeat(nullStarCount) }

    return result
  }

  onMounted(() => {
    if (props.preload) {
      loadData(null)
    }
  });

  __expose({
    refreshData,
    loadData
  });

return (): any | null => {

const _component_uni_loading = resolveEasyComponent("uni-loading",_easycom_uni_loading)

  return createElementVNode("waterflow", utsMapOf({
    id: _ctx.id,
    class: "grid",
    bounces: false,
    "scroll-y": true,
    "custom-nested-scroll": true,
    "main-axis-gap": "8px",
    "cross-axis-gap": "8px",
    "cross-axis-count": "2",
    onScrolltolower: () => {loadData(null)},
    "associative-container": "nested-scroll-view"
  }), [
    createElementVNode(Fragment, null, RenderHelpers.renderList(unref(datagrid), (item, _, __index, _cached): any => {
      return createElementVNode("flow-item", utsMapOf({
        class: "flow-item",
        key: item.plugin_id,
        type: "1"
      }), [
        createElementVNode("view", utsMapOf({ class: "flow-item-icon" }), [
          createElementVNode("image", utsMapOf({
            class: "flow-item-icon-image",
            src: item.plugin_img_link,
            mode: "aspectFit"
          }), null, 8 /* PROPS */, ["src"])
        ]),
        createElementVNode("view", utsMapOf({ class: "flow-item-fill" }), [
          createElementVNode("view", utsMapOf({ class: "flex-row" }), [
            createElementVNode("text", utsMapOf({ class: "title" }), toDisplayString(item.plugin_name), 1 /* TEXT */)
          ]),
          createElementVNode("view", null, [
            createElementVNode("text", utsMapOf({ class: "description-text" }), toDisplayString(item.plugin_intro), 1 /* TEXT */)
          ]),
          createElementVNode("text", utsMapOf({ class: "icon-star" }), toDisplayString(convertToStarUnicode(item.score)), 1 /* TEXT */),
          createElementVNode("view", utsMapOf({ class: "tag-grid" }), [
            createElementVNode(Fragment, null, RenderHelpers.renderList(item.tags.slice(0,1), (item2, index2, __index, _cached): any => {
              return createElementVNode("text", utsMapOf({
                class: "tag-item",
                key: index2
              }), toDisplayString(item2), 1 /* TEXT */)
            }), 128 /* KEYED_FRAGMENT */)
          ]),
          createElementVNode("view", utsMapOf({ class: "flex-row update-date" }), [
            createElementVNode("text", utsMapOf({ class: "author" }), toDisplayString(item.author_name), 1 /* TEXT */)
          ]),
          createElementVNode("view", utsMapOf({ class: "flex-row update-date" }), [
            createElementVNode("text", utsMapOf({ class: "update-date-text" }), "更新日期"),
            createElementVNode("text", utsMapOf({ class: "update-date-value" }), toDisplayString(item.update_date), 1 /* TEXT */)
          ])
        ])
      ])
    }), 128 /* KEYED_FRAGMENT */),
    createElementVNode("flow-item", utsMapOf({
      slot: "load-more",
      class: "loading",
      type: "6"
    }), [
      createVNode(_component_uni_loading, utsMapOf({
        loading: unref(loading),
        color: "#999",
        text: unref(loadingText)
      }), null, 8 /* PROPS */, ["loading", "text"])
    ])
  ], 40 /* PROPS, NEED_HYDRATION */, ["id", "onScrolltolower"])
}
}

})
export default __sfc__
const GenPagesTemplateLongWaterflowNestedLongWaterflowPageStyles = [utsMapOf([["grid", padStyleMapOf(utsMapOf([["flex", 1], ["backgroundColor", "#f7f7f7"]]))], ["flow-item", padStyleMapOf(utsMapOf([["flexDirection", "column"], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["backgroundColor", "#ffffff"]]))], ["flow-item-icon", padStyleMapOf(utsMapOf([["position", "relative"]]))], ["flow-item-icon-image", padStyleMapOf(utsMapOf([["width", "100%"]]))], ["flow-item-fill", padStyleMapOf(utsMapOf([["flex", 1], ["paddingTop", 5], ["paddingRight", 5], ["paddingBottom", 5], ["paddingLeft", 5]]))], ["description-text", padStyleMapOf(utsMapOf([["fontSize", 13], ["color", "#666666"], ["lineHeight", "19px"]]))], ["icon-star", padStyleMapOf(utsMapOf([["fontFamily", "UtsStarIcons"], ["fontSize", 16], ["fontStyle", "normal"], ["color", "#ffca3e"], ["letterSpacing", 3]]))], ["tag-grid", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginTop", 5]]))], ["tag-item", padStyleMapOf(utsMapOf([["fontSize", 12], ["backgroundColor", "#EFF9F0"], ["color", "#639069"], ["borderTopLeftRadius", 20], ["borderTopRightRadius", 20], ["borderBottomRightRadius", 20], ["borderBottomLeftRadius", 20], ["marginRight", 5], ["paddingTop", 2], ["paddingRight", 5], ["paddingBottom", 2], ["paddingLeft", 5]]))], ["update-date", padStyleMapOf(utsMapOf([["marginTop", 10]]))], ["update-date-text", padStyleMapOf(utsMapOf([["fontSize", 12], ["color", "#888888"]]))], ["update-date-value", padStyleMapOf(utsMapOf([["fontSize", 12], ["color", "#777777"], ["marginLeft", 5]]))], ["author", padStyleMapOf(utsMapOf([["fontSize", 12], ["color", "#008000"]]))], ["loading", padStyleMapOf(utsMapOf([["paddingTop", 30], ["paddingRight", 30], ["paddingBottom", 30], ["paddingLeft", 30], ["alignItems", "center"], ["height", 80]]))], ["flex-row", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))], ["@FONT-FACE", utsMapOf([["0", utsMapOf([["fontFamily", "UtsStarIcons"], ["src", "url('/static/fonts/icon-star.ttf')"]])]])]])]
