import {
    LayoutItem
  } from '../../common/types.uts'
  type ItemAndIndex = { __$originalPosition?: UTSSourceMapPosition<"ItemAndIndex", "uni_modules/uni-recycle-view/components/uni-recycle-view/uni-recycle-view.uvue", 158, 8>;
    item: any,
    index: number
  }
  
const __sfc__ = defineComponent({
  __name: 'uni-recycle-view',
  props: {
    itemHeight: {
      type: Number,
      default: 40,
      required: true
    },
    list: {
      type: Array as PropType<any[]>,
      default: [] as any[]
    },
    type: {
      type: String,
      default: ''
    },
    direction: {
      type: String,
      default: 'vertical'
    },
    associativeContainer: {
      type: String,
      default: ''
    },
    enableBackToTop: {
      type: Boolean,
      default: false
    },
    bounces: {
      type: Boolean,
      default: true
    },
    upperThreshold: {
      type: Number,
      default: 50
    },
    lowerThreshold: {
      type: Number,
      default: 50
    },
    scrollTop: {
      type: Number,
      default: 0
    },
    scrollLeft: {
      type: Number,
      default: 0
    },
    scrollIntoView: {
      type: String,
      default: ''
    },
    scrollWithAnimation: {
      type: Boolean,
      default: false
    },
    refresherEnabled: {
      type: Boolean,
      default: false
    },
    refresherThreshold: {
      type: Number,
      default: 45
    },
    refresherMaxDragDistance: {
      type: Number
    },
    refresherDefaultStyle: {
      type: String,
      default: 'black'
    },
    refresherBackground: {
      type: String,
      default: 'transparent'
    },
    refresherTriggered: {
      type: Boolean,
      default: false
    },
    showScrollbar: {
      type: Boolean,
      default: true
    },
    customNestedScroll: {
      type: Boolean,
      default: false
    },
    nestedScrollChild: {
      type: String,
      default: ''
    }
  },
  emits: [
    'scroll',
    'scrollend',
    'scrolltolower',
    'scrolltoupper',
    'refresherabort',
    'refresherpulling',
    'refresherrefresh',
    'refresherrestore',
  ],
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  const props = __props
  function emit(event: string, ...do_not_transform_spread: Array<any | null>) {
__ins.emit(event, ...do_not_transform_spread)
}

  const windowInfo = uni.getWindowInfo()

  // TODO 规避鸿蒙平台过早取不到windowHeight的Bug
  // 使用窗口高度计算节省不少逻辑，通常也不会有滚动区域超出窗口大小的需求
  const windowHeight = windowInfo.windowHeight == 0 ? windowInfo.screenHeight : windowInfo.windowHeight
  const cachedCount = 10
  const renderItemCount = computed(() => {
    return Math.min(
      props.list.length,
      Math.ceil(
        windowHeight / props.itemHeight) + 1 + cachedCount
    )
  })

  const placeholderHeight = computed(() => {
    return props.list.length * props.itemHeight
  })

  const scrollTop = ref(0)

  const startIndex = computed(() => {
    return Math.min(
      Math.max(
        Math.floor(scrollTop.value / props.itemHeight) - Math.floor(cachedCount / 2),
        0
      ),
      props.list.length - renderItemCount.value
    );
  })
  const items = computed < any[] > (() => {
    return props.list.slice(startIndex.value, Math.min(props.list.length, startIndex.value + renderItemCount.value))
  })
  const layoutItems = ref < LayoutItem[] > ([])

  watch([items, startIndex], () => {
    if (layoutItems.value.length !== items.value.length) {
      layoutItems.value.splice(0, layoutItems.value.length)
      items.value.forEach((item, index) => {
        layoutItems.value.push({
          id: index,
          offset: (startIndex.value + index) * props.itemHeight,
          item
        })
      })
    } else {
      // 仍存活的item仅更新offset，新生的item复用并更新死亡的item的layoutItem，注意id无论如何都不更新
      // TODO 当前方案在列表末尾向列表开头滚动时可能会命中if分支，导致所有item都触发更新
      const newItems: ItemAndIndex[] = []
      const aliveItems: ItemAndIndex[] = []
      for (let i = 0; i < items.value.length; i++) {
        const item = items.value[i]
        const layoutItemIndex = layoutItems.value.findIndex((layoutItem) => {
          return item == layoutItem.item
        })
        if (layoutItemIndex > -1) {
          aliveItems.push({
            item,
            index: i
          } as ItemAndIndex)
        } else {
          newItems.push({
            item,
            index: i
          } as ItemAndIndex)
        }
      }
      for (let i = 0; i < layoutItems.value.length; i++) {
        const layoutItem = layoutItems.value[i]
        const aliveItemAndIndex = aliveItems.find((aliveItem) => {
          return aliveItem.item == layoutItem.item
        })
        if (aliveItemAndIndex != null) {
          layoutItem.offset = (startIndex.value + aliveItemAndIndex!.index) * props.itemHeight
        } else {
          const newItemAndIndex = newItems.pop()!
          layoutItem.item = newItemAndIndex.item
          layoutItem.offset = (startIndex.value + newItemAndIndex.index) * props.itemHeight
        }
      }
    }

    layoutItems.value.sort((a, b) => {
      return items.value.indexOf(a.item) - items.value.indexOf(b.item)
    })
  }, {
    immediate: true
  })

  function onScroll (e: UniScrollEvent) {
    const _scrollTop = e.detail.scrollTop
    scrollTop.value = _scrollTop < 0 ? 0 : _scrollTop
    emit('scroll', e)
  }
  function onScrollToLower (e: UniScrollToLowerEvent) {
    emit('scrolltolower', e)
  }
  function onScrollToUpper (e: UniScrollToUpperEvent) {
    emit('scrolltoupper', e)
  }
  function onScrollEnd (e: UniScrollEvent) {
    emit('scrollend', e)
  }
  function onRefresherAbort (e: UniRefresherEvent) {
    emit('refresherabort', e)
  }
  function onRefresherPulling (e: UniRefresherEvent) {
    emit('refresherpulling', e)
  }
  function onRefresherRefresh (e: UniRefresherEvent) {
    emit('refresherrefresh', e)
  }
  function onRefresherRestore (e: UniRefresherEvent) {
    emit('refresherrestore', e)
  }


return (): any | null => {

  return createElementVNode("scroll-view", utsMapOf({
    "scroll-y": "true",
    style: normalizeStyle(utsMapOf({"flex":"1"})),
    type: props.type,
    direction: props.direction,
    "associative-container": props.associativeContainer,
    "enable-back-to-top": props.enableBackToTop,
    bounces: props.bounces,
    "upper-threshold": props.upperThreshold,
    "lower-threshold": props.lowerThreshold,
    "scroll-top": props.scrollTop,
    "scroll-left": props.scrollLeft,
    "scroll-into-view": props.scrollIntoView,
    "scroll-with-animation": props.scrollWithAnimation,
    "refresher-enabled": props.refresherEnabled,
    "refresher-threshold": props.refresherThreshold,
    "refresher-max-drag-distance": props.refresherMaxDragDistance,
    "refresher-default-style": props.refresherDefaultStyle,
    "refresher-background": props.refresherBackground,
    "refresher-triggered": props.refresherTriggered,
    "show-scrollbar": props.showScrollbar,
    "custom-nested-scroll": props.customNestedScroll,
    "nested-scroll-child": props.nestedScrollChild,
    onScroll: onScroll,
    onScrollend: onScrollEnd,
    onScrolltolower: onScrollToLower,
    onScrolltoupper: onScrollToUpper,
    onRefresherabort: onRefresherAbort,
    onRefresherpulling: onRefresherPulling,
    onRefresherrefresh: onRefresherRefresh,
    onRefresherrestore: onRefresherRestore
  }), [
    createElementVNode("view", utsMapOf({
      class: "placeholder",
      style: normalizeStyle(utsMapOf({height: unref(placeholderHeight) + 'px'}))
    }), [
      renderSlot(_ctx.$slots, "default", utsMapOf({
        layoutItems: unref(layoutItems),
        items: unref(items)
      }))
    ], 4 /* STYLE */),
    renderSlot(_ctx.$slots, "load-more")
  ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["type", "direction", "associative-container", "enable-back-to-top", "bounces", "upper-threshold", "lower-threshold", "scroll-top", "scroll-left", "scroll-into-view", "scroll-with-animation", "refresher-enabled", "refresher-threshold", "refresher-max-drag-distance", "refresher-default-style", "refresher-background", "refresher-triggered", "show-scrollbar", "custom-nested-scroll", "nested-scroll-child"])
}
}

})
export default __sfc__
export type UniRecycleViewComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesUniRecycleViewComponentsUniRecycleViewUniRecycleViewStyles = []
