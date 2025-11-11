import longPage from '../long-list/long-list-page.uvue';

  type SwiperTabsItem = { __$originalPosition?: UTSSourceMapPosition<"SwiperTabsItem", "pages/template/long-list2/long-list2.uvue", 34, 8>;
    x : number,
    w : number
  }

  type SwiperViewItem = { __$originalPosition?: UTSSourceMapPosition<"SwiperViewItem", "pages/template/long-list2/long-list2.uvue", 39, 8>;
    type : string,
    name : string,
    id: string,
    preload : boolean,
  }

  /**
   * 根据权重在两个值之间执行线性插值.
   * @constructor
   * @param {number} value1 - 第一个值，该值应为下限.
   * @param {number} value2 - 第二个值，该值应为上限.
   * @param {number} amount - 应介于 0 和 1 之间，指示内插的权重.
   * @returns {number} 内插值
   */
  
const __sfc__ = defineComponent({
  __name: 'long-list2',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  function lerpNumber(value1 : number, value2 : number, amount : number) : number {
    return (value1 + (value2 - value1) * amount)
  }

  const swiperList = ref<SwiperViewItem[]>([
    {
      type: 'UpdatedDate',
      name: '最新上架',
      id: "list-id-1",
      preload: true
    },
    {
      type: 'FreeHot',
      name: '免费热榜',
      id: "list-id-2",
      preload: false
    },
    {
      type: 'PaymentHot',
      name: '付费热榜',
      id: "list-id-3",
      preload: false
    },
    {
      type: 'HotList',
      name: '热门总榜',
      id: "list-id-4",
      preload: false
    }
  ])

  const swiperIndex = ref(0)
  const pageScrollView = ref<UniScrollViewElement|null>(null)
  const header = ref<UniElement|null>(null)
  const headerHeight = ref(0)
  const animationFinishIndex = ref(0)
  const tabScroll = ref<UniScrollViewElement|null>(null)
  const indicator = ref<UniElement|null>(null)
  const swiper = ref<UniSwiperElement|null>(null)
  const swiperWidth = ref(0)
  const swiperTabsRect = ref<SwiperTabsItem[]>([])
  const nestedScrollChildId = ref("")
  const longPageRef = ref<ComponentPublicInstance[]|null>(null)
  const swipertab = ref<UniTextElement[]|null>(null)

  function onStartNestedScroll() : boolean {
    return true
  }

  function onNestedPreScroll(event : NestedPreScrollEvent) {
    const deltaY = event.deltaY
    const scrollTop = pageScrollView.value!.scrollTop

    if (deltaY > 0) {
      // 向上滚动，如果父容器 header scrollTop < offsetHeight，先滚动父容器
      if (scrollTop < headerHeight.value) {
        const difference = headerHeight.value - scrollTop - deltaY
        if (difference > 0) {
          pageScrollView.value!.scrollBy(event.deltaX, deltaY)
          event.consumed(event.deltaX, deltaY)
        } else {
          const top : number = deltaY + difference
          event.consumed(event.deltaX, top.toFloat())
          pageScrollView.value!.scrollBy(event.deltaX, top.toFloat())
        }
      }
    }
  }

  function initSwiperItemData(index : number) {
    if (!swiperList.value[index].preload) {
      swiperList.value[index].preload = true;
      (longPageRef.value! as ComponentPublicInstance[])[index].$callMethod('loadData', null)
    }
    //swiper页面切换需要重新赋值嵌套滚动子元素的id
    nestedScrollChildId.value = swiperList.value[index].id
  }

  function updateTabIndicator(current_index : number, move_to_index : number, percentage : number) {
    const current_size = swiperTabsRect.value[current_index]
    const move_to_size = swiperTabsRect.value[move_to_index]

    // 计算指示线 左边距 和 宽度 在移动过程中的线性值
    const indicator_line_x = lerpNumber(current_size.x, move_to_size.x, percentage)
    const indicator_line_w = lerpNumber(current_size.w, move_to_size.w, percentage)

    // 更新指示线

    const x = indicator_line_x + indicator_line_w / 2
    indicator.value?.style?.setProperty('transform', `translateX(${x}px) scaleX(${indicator_line_w})`)
    initSwiperItemData(current_index)








    // 滚动到水平中心位置
    const scroll_x = x - swiperWidth.value / 2
    // app 平台后续支持 scrollTo()

    tabScroll.value!.scrollLeft = scroll_x






  }

  function setSwiperIndex(index : number, updateIndicator : boolean) {
    if (swiperIndex.value === index) {
      return
    }

    swiperIndex.value = index

    initSwiperItemData(index)

    if (updateIndicator) {
      updateTabIndicator(index, index, 1)
    }
  }

  function onTabClick(index : number) {
    setSwiperIndex(index, false)
  }

  function onSwiperTransition(e : SwiperTransitionEvent) {
    // 微信 skyline 每项完成触发 Animationfinish，偏移值重置
    // 微信 webview 全部完成触发 Animationfinish，偏移值累加
    // 在滑动到下一个项的过程中，再次反向滑动，偏移值递减
    // uni-app-x 和微信 webview 行为一致

    const offset_x = e.detail.dx

    // 计算当前索引并重置差异
    const current_offset_x = offset_x % swiperWidth.value
    const current_offset_i = offset_x / swiperWidth.value
    const current_index = animationFinishIndex.value + parseInt(current_offset_i + '')

    // 计算目标索引及边界检查
    let move_to_index = current_index
    if (current_offset_x > 0 && move_to_index < swiperList.value.length - 1) {
      move_to_index += 1
    } else if (current_offset_x < 0 && move_to_index > 0) {
      move_to_index -= 1
    }

    // 计算偏移百分比
    const percentage = Math.abs(current_offset_x) / swiperWidth.value

    // 通知更新指示线
    if (current_index != move_to_index) {
      updateTabIndicator(current_index, move_to_index, percentage)
    }

    // 首次可见时初始化数据
    initSwiperItemData(move_to_index)
  }

  function onSwiperAnimationfinish(e : SwiperAnimationFinishEvent) {
    setSwiperIndex(e.detail.current, true)
    animationFinishIndex.value = e.detail.current
  }

  async function cacheTabItemsSize() {
    swiperTabsRect.value.length = 0;
    const tabs = swipertab.value as UniElement[]
    for (let i = 0; i < tabs.length; i++) {
      const element = tabs[i];






      const x = element.offsetLeft
      const w = element.offsetWidth

      swiperTabsRect.value.push({
        x,
        w
      } as SwiperTabsItem)
    }
  }

  onPullDownRefresh(() => {
    (longPageRef.value! as ComponentPublicInstance[])[swiperIndex.value].$callMethod('refreshData', () => {
      uni.stopPullDownRefresh()
    })
  })

  onReady(() => {
    headerHeight.value = (header.value as UniElement).offsetHeight
    swiper.value?.getBoundingClientRectAsync()!.then((res : DOMRect) : Promise<void> => {
      swiperWidth.value = res.width
      return cacheTabItemsSize()
    }).then(() => {
      updateTabIndicator(swiperIndex.value, swiperIndex.value, 1)
    });
  })


return (): any | null => {

  return createElementVNode("scroll-view", utsMapOf({
    ref_key: "pageScrollView",
    ref: pageScrollView,
    class: "page",
    bounces: false,
    onStartnestedscroll: onStartNestedScroll,
    onNestedprescroll: onNestedPreScroll,
    "nested-scroll-child": unref(nestedScrollChildId)
  }), [
    createElementVNode("swiper", utsMapOf({
      ref_key: "header",
      ref: header,
      "indicator-dots": "true",
      circular: "true"
    }), [
      createElementVNode(Fragment, null, RenderHelpers.renderList(3, (i, __key, __index, _cached): any => {
        return createElementVNode("swiper-item", utsMapOf({ "item-id": i }), [
          createElementVNode("image", utsMapOf({
            src: "/static/shuijiao.jpg",
            style: normalizeStyle(utsMapOf({"width":"100%","height":"240px"}))
          }), null, 4 /* STYLE */)
        ], 8 /* PROPS */, ["item-id"])
      }), 64 /* STABLE_FRAGMENT */)
    ], 512 /* NEED_PATCH */),
    createElementVNode("view", utsMapOf({ class: "swiper-list" }), [
      createElementVNode("scroll-view", utsMapOf({
        ref_key: "tabScroll",
        ref: tabScroll,
        class: "swiper-tabs",
        direction: "horizontal",
        "show-scrollbar": false
      }), [
        createElementVNode("view", utsMapOf({
          class: "flex-row",
          style: normalizeStyle(utsMapOf({"align-self":"flex-start"}))
        }), [
          createElementVNode(Fragment, null, RenderHelpers.renderList(unref(swiperList), (item, index, __index, _cached): any => {
            return createElementVNode("text", utsMapOf({
              ref_for: true,
              ref_key: "swipertab",
              ref: swipertab,
              class: normalizeClass(["swiper-tabs-item", unref(swiperIndex)==index ? 'swiper-tabs-item-active' : '']),
              key: index,
              onClick: () => {onTabClick(index)}
            }), toDisplayString(item.name), 11 /* TEXT, CLASS, PROPS */, ["onClick"])
          }), 128 /* KEYED_FRAGMENT */)
        ], 4 /* STYLE */),
        createElementVNode("view", utsMapOf({
          ref_key: "indicator",
          ref: indicator,
          class: "swiper-tabs-indicator"
        }), null, 512 /* NEED_PATCH */)
      ], 512 /* NEED_PATCH */),
      createElementVNode("swiper", utsMapOf({
        ref_key: "swiper",
        ref: swiper,
        class: "swiper-view",
        current: unref(swiperIndex),
        onTransition: onSwiperTransition,
        onAnimationfinish: onSwiperAnimationfinish
      }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(unref(swiperList), (item, index, __index, _cached): any => {
          return createElementVNode("swiper-item", utsMapOf({
            class: "swiper-item",
            key: index
          }), [
            createVNode(unref(longPage), utsMapOf({
              ref_for: true,
              ref_key: "longPageRef",
              ref: longPageRef,
              id: item.id,
              type: item.type,
              preload: item.preload
            }), null, 8 /* PROPS */, ["id", "type", "preload"])
          ])
        }), 128 /* KEYED_FRAGMENT */)
      ], 40 /* PROPS, NEED_HYDRATION */, ["current"])
    ])
  ], 40 /* PROPS, NEED_HYDRATION */, ["nested-scroll-child"])
}
}

})
export default __sfc__
const GenPagesTemplateLongList2LongList2Styles = [utsMapOf([["flex-row", padStyleMapOf(utsMapOf([["flexDirection", "row"]]))], ["page", padStyleMapOf(utsMapOf([["flex", 1]]))], ["search-bar", padStyleMapOf(utsMapOf([["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))], ["swiper-list", padStyleMapOf(utsMapOf([["height", "100%"]]))], ["swiper-tabs", padStyleMapOf(utsMapOf([["backgroundColor", "#ffffff"], ["flexDirection", "column"]]))], ["swiper-tabs-item", padStyleMapOf(utsMapOf([["color", "#555555"], ["fontSize", 16], ["paddingTop", 12], ["paddingRight", 25], ["paddingBottom", 12], ["paddingLeft", 25], ["whiteSpace", "nowrap"]]))], ["swiper-tabs-item-active", padStyleMapOf(utsMapOf([["color", "#007AFF"]]))], ["swiper-tabs-indicator", padStyleMapOf(utsMapOf([["width", 1], ["height", 2], ["backgroundColor", "#007AFF"]]))], ["swiper-view", padStyleMapOf(utsMapOf([["flex", 1]]))], ["swiper-item", padStyleMapOf(utsMapOf([["flex", 1]]))]])]
