type Item = { __$originalPosition?: UTSSourceMapPosition<"Item", "pages/template/long-list-batch/long-list-batch.uvue", 18, 8>;
    id : number;
    name : string;
    info : string;
  };

  
const __sfc__ = defineComponent({
  __name: 'long-list-batch',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  class Jobs  implements IUTSSourceMap{
// @ts-expect-error 
override __$getOriginalPosition(): UTSSourceMapPosition { return new UTSSourceMapPosition("Jobs", "pages/template/long-list-batch/long-list-batch.uvue", 24, 9);}

    private jobs : (() => Promise<void>)[] = [];
    paused : boolean = true;
    constructor() { }
    add(job : () => Promise<void>) {
      this.jobs.push(job);
    }
    pause() {
      this.paused = true;
    }
    private execute() {
      if (this.paused) {
        return;
      }
      if (this.jobs.length == 0) {
        this.paused = true
        return;
      }
      const job = this.jobs.shift();
      if (job != null) {
        job().then(() => {
          this.execute();
        });
      }
    }
    resume() {
      if(!this.paused) {
        return
      }
      this.paused = false;
      setTimeout(() => {
        this.execute();
      }, 0)
    }
    reset() {
      this.jobs = [];
      this.paused = true;
    }
    get done() : boolean {
      return this.jobs.length == 0;
    }
  }

  function delay(time : number) : Promise<void> {
    return new Promise(resolve => {
      setTimeout(() => {
        resolve()
      }, time)
    })
  }

  const bigList = ref<Item[]>([]);
  const list = ref<Item[]>([]);
  const jobs = ref<Jobs>(new Jobs());
  const batchSize = ref<number>(100);
  const scrolling = ref<boolean>(false);
  const refresherTriggered = ref<boolean>(false);
  const scrollendTimeout = ref<number>(-1);
  const pulling = ref<boolean>(false);

  function init(autoResumeJobs: boolean = true) {
    // 将数据分批放入任务队列
    const batchCount = Math.ceil(bigList.value.length / batchSize.value);
    for (let i = 0; i < batchCount; i++) {
      const start = i * batchSize.value;
      const end = Math.min(start + batchSize.value, bigList.value.length);
      jobs.value.add(async () => {
        list.value.push(...bigList.value.slice(start, end));
        // 两批数据之间增加延迟，防止卡顿时间太久
        await nextTick();
        await delay(100)
      });
    }
    if(autoResumeJobs) {
      jobs.value.resume();
    }
  }

  const onScrollEnd = () => {
    scrolling.value = false;
    // 滚动结束，继续执行分批加载逻辑
    jobs.value.resume();
  };

  const onScroll = () => {
    // 部分平台不支持scrollend事件，使用定时器模拟
    clearTimeout(scrollendTimeout.value)
    scrollendTimeout.value = setTimeout(() => {
      onScrollEnd()
    }, 100)
    if (scrolling.value) {
      return;
    }
    scrolling.value = true;
    // 滚动期间暂停分批加载，保证滚动流畅度
    jobs.value.pause();
  };

  const onScrollToLower = () => {
    // 分批加载进行中，暂不执行滚动到底部加载更多数据的逻辑
    if (!jobs.value.done) {
      return;
    }
    // 加载更多数据
  };

  const onRefresh = () => {
    // 下拉刷新触发，重置任务队列
    jobs.value.reset();
    refresherTriggered.value = true
    setTimeout(() => {
      refresherTriggered.value = false;
      list.value.splice(0, list.value.length);
      /**
       * refreshRestore事件会触发继续分批加载，此处init不自动调用resume。这样做能减少一些下拉刷新复位期间加载数据引发的卡顿。
       * TODO 清空列表也会导致下拉刷新复位时发生卡顿，后续再优化
       */
      init(false);
    }, 500)
  };

  const onRefresherPulling = () => {
    if(pulling.value) {
      return
    }
    pulling.value = true
    // 在下拉刷新时暂停分批加载，避免影响刷新操作
    jobs.value.pause()
  };

  const onRefresherRestore = () => {
    pulling.value = false
    // 下拉刷新结束后恢复分批加载
    jobs.value.resume();
  };

  // 模拟大列表数据 - 相当于 created 生命周期
  for (let i = 0; i < 2000; i++) {
    bigList.value.push({
      id: i,
      name: `Wifi_` + i,
      info: `信号强度: -${Math.floor(Math.random() * 60) + 40
        } db, 安全性: WPA/WPA2/WPA3-Personal`,
    } as Item);
  }

  onReady(() => {
    init();
  });

return (): any | null => {

  return createElementVNode("view", utsMapOf({
    style: normalizeStyle(utsMapOf({"flex":"1","background-color":"aliceblue"}))
  }), [
    createElementVNode("view", utsMapOf({ class: "tips" }), "list-view组件虽然在渲染层有recycle机制，但长列表的vnode/Element太多也会造成初始化卡顿。 本示例有2000条数据，通过分批创建列表项，减少初始化卡顿。并通过闲时创建机制避免影响列表滚动等UI操作"),
    createElementVNode("list-view", utsMapOf({
      style: normalizeStyle(utsMapOf({"flex":"1"})),
      "refresher-enabled": true,
      "refresher-triggered": unref(refresherTriggered),
      onScroll: onScroll,
      onScrolltolower: onScrollToLower,
      onRefresherrefresh: onRefresh,
      onRefresherpulling: onRefresherPulling,
      onRefresherrestore: onRefresherRestore
    }), [
      createElementVNode(Fragment, null, RenderHelpers.renderList(unref(list), (item, index, __index, _cached): any => {
        return createElementVNode("list-item", utsMapOf({
          class: "item",
          key: index + '_' + item.id
        }), [
          createElementVNode("view", null, [
            createElementVNode("text", null, toDisplayString(item.name), 1 /* TEXT */)
          ]),
          createElementVNode("view", null, [
            createElementVNode("text", utsMapOf({
              style: normalizeStyle(utsMapOf({"font-size":"12px","color":"#999999"}))
            }), toDisplayString(item.info), 5 /* TEXT, STYLE */)
          ])
        ])
      }), 128 /* KEYED_FRAGMENT */)
    ], 44 /* STYLE, PROPS, NEED_HYDRATION */, ["refresher-triggered"])
  ], 4 /* STYLE */)
}
}

})
export default __sfc__
const GenPagesTemplateLongListBatchLongListBatchStyles = [utsMapOf([["tips", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["backgroundColor", "#FFFFFF"]]))], ["item", padStyleMapOf(utsMapOf([["marginTop", 5], ["marginRight", 10], ["marginBottom", 5], ["marginLeft", 10], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["borderTopLeftRadius", 5], ["borderTopRightRadius", 5], ["borderBottomRightRadius", 5], ["borderBottomLeftRadius", 5], ["backgroundColor", "#FFFFFF"]]))]])]
