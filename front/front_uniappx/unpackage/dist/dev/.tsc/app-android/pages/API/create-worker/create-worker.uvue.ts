
  const __sfc__ = defineComponent({
    onUnload() {
      this.destory();
    },
    data() {
      return {
        created_boolean: false,
        workerStatus: 'none', // none, created, destroyed
        isListening: false,
        logs: [] as Array<UTSJSONObject>,
        inputValue: '1', // 默认值为1
        taskResult: '',
        worker: null as Worker | null
      }
    },
    computed: {
      statusText() : string {
        switch (this.workerStatus) {
          case 'none': return '未创建';
          case 'created': return '已创建';
          case 'destroyed': return '已销毁';
          default: return '未知';
        }
      }
    },
    methods: {
      // 添加日志方法
      addLog(message : string, type : string = 'info') {
        const now = new Date();
        const timeStr = `${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}:${now.getSeconds().toString().padStart(2, '0')}`;
        const logItem = { __$originalPosition: new UTSSourceMapPosition("logItem", "pages/API/create-worker/create-worker.uvue", 74, 15), 
          message: `[${timeStr}] ${message}`,
          type: type,
          time: timeStr
        } as UTSJSONObject;
        this.logs.unshift(logItem);
        // 限制日志数量
        if (this.logs.length > 50) {
          this.logs = this.logs.slice(0, 50);
        }
      },

      // 创建Worker
      create() {
        this.worker = uni.createWorker('workers/helloWorkerTask.uts');
        this.workerStatus = 'created';
        this.addLog('Worker创建成功', 'success');
        this.created_boolean = true;

      },
      onWorkerMsg() {
        if (this.worker == null) {
          this.addLog('请先创建worker', 'warning');
          return;
        }
        this.worker!.onMessage((result) => {
          // 处理Worker返回的消息
          console.log(`收到Worker消息:`, result, " at pages/API/create-worker/create-worker.uvue:101");
          const res = result as UTSJSONObject;
          const resultData = res['data'] as string;
          this.taskResult = resultData;
          this.inputValue = this.taskResult
          this.addLog(`收到WorkerTask返回: ${resultData}`, 'receive');
        })
      },
      onWorkerError() {
        if (this.worker == null) {
          this.addLog('请先创建worker', 'warning');
          return;
        }
        this.worker!.onError((error) => {
          console.error('Worker发生错误:', error, " at pages/API/create-worker/create-worker.uvue:115");
          // this.addLog(`Worker错误: ${error.message}`, 'error');
        })
      },

      // 向workerTask发送消息
      sendMessage() {
        // 检查输入值
        if (this.inputValue == '') {
          this.addLog('请输入有效的数字', 'warning');
          return;
        }
        const options = {__$originalPosition: new UTSSourceMapPosition("options", "pages/API/create-worker/create-worker.uvue", 127, 15),
          data: this.inputValue,
          needReply: true
        };
        this.worker!.postMessage(options, null);
        this.addLog(`发送值到WorkerTask: ${this.inputValue}`, 'send');
      },

      // 销毁Worker
      destory() {
        if (this.worker == null) {
          this.addLog('没有创建worker,无法销毁', 'warning');
          return;
        }
        this.worker!.terminate();
        this.workerStatus = 'destroyed';
        this.isListening = false;
        this.addLog('Worker已销毁', 'warning');
        this.created_boolean = false;
      },

      // 清空日志
      clearLogs() {
        this.logs = [];
      },

      test_resetInputValue() {
        this.inputValue = '1'
      },
    }
  })

export default __sfc__
function GenPagesAPICreateWorkerCreateWorkerRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("scroll-view", utsMapOf({ class: "container" }), [
    createElementVNode("view", utsMapOf({ class: "status-section" }), [
      createElementVNode("text", utsMapOf({ class: "status-label" }), "Worker状态: "),
      createElementVNode("text", utsMapOf({ class: "status-text" }), toDisplayString(_ctx.statusText), 1 /* TEXT */)
    ]),
    createElementVNode("view", utsMapOf({ class: "button-group" }), [
      createElementVNode("text", utsMapOf({ class: "description-text" }), "操作步骤：1.创建Worker 2.添加消息监听 3.发送数据测试"),
      createElementVNode("button", utsMapOf({
        class: "btn",
        type: "primary",
        disabled: _ctx.created_boolean,
        onClick: _ctx.create
      }), "创建Worker", 8 /* PROPS */, ["disabled", "onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btn",
        type: "primary",
        onClick: _ctx.onWorkerMsg
      }), "添加消息监听", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btn",
        type: "primary",
        onClick: _ctx.onWorkerError
      }), "添加错误监听", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btn",
        onClick: _ctx.destory,
        disabled: _ctx.workerStatus != 'created'
      }), "销毁Worker", 8 /* PROPS */, ["onClick", "disabled"])
    ]),
    createElementVNode("view", utsMapOf({ class: "input-section" }), [
      createElementVNode("text", utsMapOf({ class: "section-title" }), "输入测试值:"),
      createElementVNode("text", utsMapOf({ class: "description-text" }), "点击发送按钮后，会将输入值传给WorkerTask，在子线程执行+1操作后返回结果"),
      createElementVNode("input", utsMapOf({
        class: "input-field",
        modelValue: _ctx.inputValue,
        onInput: ($event: InputEvent) => {(_ctx.inputValue) = $event.detail.value},
        type: "number",
        placeholder: "请输入数字"
      }), null, 40 /* PROPS, NEED_HYDRATION */, ["modelValue", "onInput"]),
      createElementVNode("button", utsMapOf({
        class: "btn",
        type: "primary",
        onClick: _ctx.sendMessage,
        disabled: _ctx.workerStatus != 'created'
      }), "发送到WorkerTask (值+1)", 8 /* PROPS */, ["onClick", "disabled"])
    ]),
    createElementVNode("view", utsMapOf({ class: "log-section" }), [
      createElementVNode("text", utsMapOf({ class: "section-title" }), "通信日志:"),
      createElementVNode("scroll-view", utsMapOf({
        class: "log-container",
        "scroll-y": "true"
      }), [
        createElementVNode(Fragment, null, RenderHelpers.renderList(_ctx.logs, (log, index, __index, _cached): any => {
          return createElementVNode("view", utsMapOf({
            key: index,
            class: "log-item"
          }), [
            createElementVNode("text", utsMapOf({
              class: normalizeClass(['log-text', log.type])
            }), toDisplayString(log.message), 3 /* TEXT, CLASS */)
          ])
        }), 128 /* KEYED_FRAGMENT */)
      ]),
      createElementVNode("button", utsMapOf({
        onClick: _ctx.clearLogs,
        class: "btn clear-btn"
      }), "清空日志", 8 /* PROPS */, ["onClick"])
    ])
  ])
}
const GenPagesAPICreateWorkerCreateWorkerStyles = [utsMapOf([["container", padStyleMapOf(utsMapOf([["flex", 1], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10]]))], ["status-section", padStyleMapOf(utsMapOf([["display", "flex"], ["alignItems", "center"], ["justifyContent", "center"], ["marginBottom", 20], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8]]))], ["status-label", padStyleMapOf(utsMapOf([["fontSize", 16], ["color", "#666666"]]))], ["status-text", padStyleMapOf(utsMapOf([["fontSize", 16], ["fontWeight", "bold"], ["marginLeft", 8]]))], ["button-group", padStyleMapOf(utsMapOf([["flexDirection", "column"], ["marginBottom", 10]]))], ["input-section", padStyleMapOf(utsMapOf([["marginBottom", 20], ["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15], ["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8]]))], ["input-field", padStyleMapOf(utsMapOf([["width", "100%"], ["paddingTop", 12], ["paddingRight", 12], ["paddingBottom", 12], ["paddingLeft", 12], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#dddddd"], ["borderRightColor", "#dddddd"], ["borderBottomColor", "#dddddd"], ["borderLeftColor", "#dddddd"], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6], ["fontSize", 16], ["marginTop", 10], ["marginRight", 0], ["marginBottom", 10], ["marginLeft", 0], ["backgroundColor", "#ffffff"]]))], ["btn", padStyleMapOf(utsMapOf([["marginBottom", 10], ["paddingTop", 5], ["paddingRight", 10], ["paddingBottom", 5], ["paddingLeft", 10], ["borderTopLeftRadius", 6], ["borderTopRightRadius", 6], ["borderBottomRightRadius", 6], ["borderBottomLeftRadius", 6], ["fontSize", 14], ["textAlign", "center"]]))], ["log-section", padStyleMapOf(utsMapOf([["backgroundColor", "#ffffff"], ["borderTopLeftRadius", 8], ["borderTopRightRadius", 8], ["borderBottomRightRadius", 8], ["borderBottomLeftRadius", 8], ["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["section-title", padStyleMapOf(utsMapOf([["fontSize", 18], ["fontWeight", "bold"], ["color", "#333333"], ["marginBottom", 10]]))], ["log-container", padStyleMapOf(utsMapOf([["height", 300], ["borderTopWidth", 1], ["borderRightWidth", 1], ["borderBottomWidth", 1], ["borderLeftWidth", 1], ["borderTopStyle", "solid"], ["borderRightStyle", "solid"], ["borderBottomStyle", "solid"], ["borderLeftStyle", "solid"], ["borderTopColor", "#dddddd"], ["borderRightColor", "#dddddd"], ["borderBottomColor", "#dddddd"], ["borderLeftColor", "#dddddd"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4], ["paddingTop", 10], ["paddingRight", 10], ["paddingBottom", 10], ["paddingLeft", 10], ["marginTop", 10], ["marginRight", 0], ["marginBottom", 10], ["marginLeft", 0], ["backgroundColor", "#fafafa"]]))], ["log-item", padStyleMapOf(utsMapOf([["marginBottom", 5]]))], ["log-text", utsMapOf([["", utsMapOf([["fontSize", 12], ["lineHeight", 1.4]])], [".info", utsMapOf([["color", "#2196F3"]])], [".success", utsMapOf([["color", "#4CAF50"]])], [".warning", utsMapOf([["color", "#ff9800"]])], [".error", utsMapOf([["color", "#f44336"]])], [".send", utsMapOf([["color", "#9C27B0"]])], [".receive", utsMapOf([["color", "#009688"]])]])], ["clear-btn", padStyleMapOf(utsMapOf([["backgroundColor", "#ff9800"], ["fontSize", 12], ["paddingTop", 8], ["paddingRight", 12], ["paddingBottom", 8], ["paddingLeft", 12], ["color", "#ffffff"], ["borderTopLeftRadius", 4], ["borderTopRightRadius", 4], ["borderBottomRightRadius", 4], ["borderBottomLeftRadius", 4], ["textAlign", "center"]]))], ["description-text", padStyleMapOf(utsMapOf([["fontSize", 14], ["color", "#666666"], ["lineHeight", 1.4], ["marginBottom", 10]]))]])]
