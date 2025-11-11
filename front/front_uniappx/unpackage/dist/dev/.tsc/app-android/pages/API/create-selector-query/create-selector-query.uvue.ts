
  import nodeChild from './nodes-info-child.uvue'
  import multiChild from './selector-query-child-multi.uvue'

  type NodeInfoType = { __$originalPosition?: UTSSourceMapPosition<"NodeInfoType", "pages/API/create-selector-query/create-selector-query.uvue", 65, 8>;
    left : number | null,
    top : number | null,
    right : number | null,
    bottom : number | null,
    width : number | null,
    height : number | null,
  }

  const __sfc__ = defineComponent({
    components: {
      nodeChild,
      multiChild
    },
    data() {
      return {
        title: 'createSelectorQuery',
        nodeInfoList: [] as NodeInfoType[],
        // 仅用于自动化测试
        rootNodeInfo: null as NodeInfoType | null,
        //供自动化测试使用
        // resizeRectValid: false
        // TODO
        selectCount: 0,
        selectAllCount: 0,
        fieldsResultContainNode: false,
        nodeResultContainNode: false
      }
    },
    onLoad() {
      uni.$on('childReady', this.onChildReady)
    },
    onUnload() {
      uni.$off('childReady', this.onChildReady)
    },
    onReady() {
      this.testFields()
      this.testNode()
    },
    onResize() {
      //供自动化测试使用
      /* var rect12Element = uni.getElementById("rect-1-2")
      if(rect12Element != null) {
        var domRect = rect12Element.getBoundingClientRect()
        if(domRect.width > 100) {
          this.resizeRectValid = true
        }
      } */
    },
    methods: {
      // 仅用于自动化测试
      onChildReady() {
        const instance2 = (this.$refs['multi-child'] as ComponentPublicInstance);
        this.selectCount = instance2.$data['selectCount'] as number;
        this.selectAllCount = instance2.$data['selectAllCount'] as number;
      },
      // 仅用于自动化测试
      getRootNodeInfo(selector : string) {
        uni.createSelectorQuery().select(selector).boundingClientRect().exec((ret) => {
          if (ret.length == 1) {
            const nodeInfo = ret[0] as NodeInfo;
            const nodeType = {
              left: nodeInfo.left,
              top: nodeInfo.top,
              right: nodeInfo.right,
              bottom: nodeInfo.bottom,
              width: nodeInfo.width,
              height: nodeInfo.height,
            } as NodeInfoType;
            this.rootNodeInfo = nodeType
          }
        })
      },
      getNodeInfo() {
        uni.createSelectorQuery().select('.rect1').boundingClientRect().exec((ret) => {
          this.nodeInfoList.length = 0
          const i = ret[0] as NodeInfo
          this.nodeInfoList.push({
            left: i.left,
            top: i.top,
            right: i.right,
            bottom: i.bottom,
            width: i.width,
            height: i.height,
          } as NodeInfoType)
        })
      },
      getAllNodeInfo() {
        uni.createSelectorQuery().selectAll('.rect').boundingClientRect().exec((ret) => {
          this.nodeInfoList.length = 0
          const array = ret[0] as NodeInfo[]
          array.forEach((i) => {
            this.nodeInfoList.push({
              left: i.left,
              top: i.top,
              right: i.right,
              bottom: i.bottom,
              width: i.width,
              height: i.height,
            } as NodeInfoType)
          })
        })
      },
      // test .fields
      testFields() {
        uni.createSelectorQuery().select('.rect1').fields({
          node: true
        } as NodeField, (ret) => {
          const isElement = (ret as NodeInfo).node instanceof UniElement
          if (isElement) {
            this.fieldsResultContainNode = true
          } else {
            this.fieldsResultContainNode = false
          }
        }).exec()
      },
      // test .node
      testNode() {
        uni.createSelectorQuery().select('#canvas1').node((ret) => {
          const isElement = (ret as NodeInfo).node instanceof UniElement
          const isCanvasElement = ((ret as NodeInfo).node as UniCanvasElement).tagName == 'CANVAS'
          if (isElement && isCanvasElement) {
            this.nodeResultContainNode = true
          } else {
            this.nodeResultContainNode = false
          }
        }).exec()
      },
    }
  })

export default __sfc__
function GenPagesAPICreateSelectorQueryCreateSelectorQueryRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_page_head = resolveEasyComponent("page-head",_easycom_page_head)
const _component_node_child = resolveComponent("node-child")
const _component_multi_child = resolveComponent("multi-child")

  return createElementVNode("scroll-view", utsMapOf({ class: "page-scroll-view" }), [
    createElementVNode("view", utsMapOf({
      class: "page uni-list-cell-db-text",
      id: "page"
    }), [
      createVNode(_component_page_head, utsMapOf({ title: _ctx.title }), null, 8 /* PROPS */, ["title"]),
      createElementVNode("button", utsMapOf({
        class: "btn btn-get-node-info",
        onClick: _ctx.getNodeInfo
      }), "getNodeInfo", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btn btn-get-all-node-info",
        onClick: _ctx.getAllNodeInfo
      }), "getAllNodeInfo", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", utsMapOf({
        id: "rect-1-2",
        class: "rect-1-2"
      }), [
        createElementVNode("view", utsMapOf({ class: "rect rect1" })),
        createElementVNode("view", utsMapOf({ class: "rect rect2" }))
      ]),
      createElementVNode("view", utsMapOf({ class: "rect-info-1-2" }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.nodeInfoList, (nodeInfo, index, __index, _cached): any => {
          return createElementVNode("view", utsMapOf({
            class: "rect-info",
            key: index
          }), [
            createElementVNode("view", utsMapOf({ class: "node-info-item" }), [
              createElementVNode("text", utsMapOf({ class: "node-info-item-k" }), "left: "),
              createElementVNode("text", utsMapOf({ class: "node-info-item-v" }), toDisplayString(nodeInfo.left), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "node-info-item" }), [
              createElementVNode("text", utsMapOf({ class: "node-info-item-k" }), "top: "),
              createElementVNode("text", utsMapOf({ class: "node-info-item-v" }), toDisplayString(nodeInfo.top), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "node-info-item" }), [
              createElementVNode("text", utsMapOf({ class: "node-info-item-k" }), "right: "),
              createElementVNode("text", utsMapOf({ class: "node-info-item-v" }), toDisplayString(nodeInfo.right), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "node-info-item" }), [
              createElementVNode("text", utsMapOf({ class: "node-info-item-k" }), "bottom: "),
              createElementVNode("text", utsMapOf({ class: "node-info-item-v" }), toDisplayString(nodeInfo.bottom), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "node-info-item" }), [
              createElementVNode("text", utsMapOf({ class: "node-info-item-k" }), "width: "),
              createElementVNode("text", utsMapOf({ class: "node-info-item-v" }), toDisplayString(nodeInfo.width), 1 /* TEXT */)
            ]),
            createElementVNode("view", utsMapOf({ class: "node-info-item" }), [
              createElementVNode("text", utsMapOf({ class: "node-info-item-k" }), "height: "),
              createElementVNode("text", utsMapOf({ class: "node-info-item-v" }), toDisplayString(nodeInfo.height), 1 /* TEXT */)
            ])
          ])
        }), 128 /* KEYED_FRAGMENT */)
      ]),
      createVNode(_component_node_child, utsMapOf({ class: "node-child" })),
      createElementVNode("text", null, "子组件多根节点"),
      createVNode(_component_multi_child, utsMapOf({
        ref: "multi-child",
        id: "multi-child"
      }), null, 512 /* NEED_PATCH */),
      createElementVNode("text", null, "子组件多根节点(仅测试，用于验证查询是否超出范围)"),
      createVNode(_component_multi_child, utsMapOf({ id: "multi-child-2" })),
      createElementVNode("view", null, [
        createElementVNode("text", null, "测试.fields"),
        createElementVNode("text", null, toDisplayString(_ctx.fieldsResultContainNode), 1 /* TEXT */)
      ]),
      createElementVNode("view", null, [
        createElementVNode("text", null, "测试.node"),
        createElementVNode("text", null, toDisplayString(_ctx.nodeResultContainNode), 1 /* TEXT */)
      ]),
      createElementVNode("canvas", utsMapOf({ id: "canvas1" }))
    ])
  ])
}
const GenPagesAPICreateSelectorQueryCreateSelectorQueryStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["btn", padStyleMapOf(utsMapOf([["marginTop", 15]]))], ["rect-1-2", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginTop", 15]]))], ["rect", padStyleMapOf(utsMapOf([["width", 150], ["height", 100]]))], ["rect1", padStyleMapOf(utsMapOf([["backgroundColor", "#1E90FF"]]))], ["rect2", padStyleMapOf(utsMapOf([["marginLeft", "auto"], ["backgroundColor", "#2E8B57"]]))], ["rect-info-1-2", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["marginTop", 15]]))], ["rect-info", padStyleMapOf(utsMapOf([["flex", 1], ["flexDirection", "column"]]))], ["node-info-item", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))], ["node-info-item-k", padStyleMapOf(utsMapOf([["width", 72], ["lineHeight", 2]]))], ["node-info-item-v", padStyleMapOf(utsMapOf([["fontWeight", "bold"], ["lineHeight", 2]]))]])]

import _easycom_page_head from '@/components/page-head/page-head.vue'
