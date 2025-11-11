
  import CanvasChild from './canvas-child.uvue'

  function hidpi(canvas : UniCanvasElement) {
    const context = canvas.getContext("2d")!;
    const dpr = uni.getWindowInfo().pixelRatio;
    canvas.width = canvas.offsetWidth * dpr;
    canvas.height = canvas.offsetHeight * dpr;
    context.scale(dpr, dpr);
  }

  const __sfc__ = defineComponent({
    components: {
      CanvasChild
    },
    data() {
      return {
        title: 'Context2D',
        canvas: null as UniCanvasElement | null,
        canvasContext: null as CanvasContext | null,
        renderingContext: null as CanvasRenderingContext2D | null,
        canvasWidth: 0,
        canvasHeight: 0,
        dataBase64: '',
        taskId: 0,
        lastTime: 0,
        frameCount: 0,
        // 仅测试
        testCanvasContext: false,
        testToBlobResult: false,
        testToDataURLResult: false,
        testCreateImage: false,
        testCreatePath2D: false,
        testFrameCount: 0,
        testCanvasCtx1: false,
        testCanvasCtx2: false,
        testCounter: 0
      }
    },
    computed: {
      testCanvasCtx() {
        return this.testCanvasCtx1 && this.testCanvasCtx2
      }
    },
    onLoad() {
      // HBuilderX 4.25+
      // 异步调用方式, 跨平台写法
      uni.createCanvasContextAsync({
        id: 'canvas',
        component: this,
        success: (context : CanvasContext) => {
          this.canvasContext = context;
          this.renderingContext = context.getContext('2d')!;
          this.canvas = this.renderingContext!.canvas;

          hidpi(this.canvas!);
          this.canvasWidth = this.canvas!.width;
          this.canvasHeight = this.canvas!.height;







          setTimeout(() => {
            this.testToDataURLResult = this.canvasContext!.toDataURL().startsWith('data:image/png;base64')
          }, 50)

          this.testCanvasContext = true
        }
      })

      uni.$on('canvasChildReady', this.onChildReady)
    },
    onReady() {
      // 同步调用方式，仅支持 app/web
      // let canvas = uni.getElementById("canvas") as UniCanvasElement
      // this.renderingContext = canvas.getContext("2d")
      // hidpi(canvas);
      // this.canvas = canvas;
      // this.canvasWidth = canvas.width;
      // this.canvasHeight = canvas.height;
    },
    onUnload() {
      uni.$off('canvasChildReady', this.onChildReady)
      if (this.taskId > 0) {
        this.stopAnimationFrame()
      }
    },
    methods: {







      canvasToDataURL() {
        this.dataBase64 = this.canvasContext!.toDataURL()
      },
      onCreateImage() {
        this.renderingContext!.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
        let image = this.canvasContext!.createImage();
        image.src = "/static/logo.png"
        image.onload = () => {
          this.testCreateImage = true
          this.renderingContext?.drawImage(image, 0, 0, 100, 100);
        }
      },
      onCreatePath2D() {
        this.renderingContext!.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
        const context = this.renderingContext!
        let path2D = this.canvasContext!.createPath2D()
        this.testCreatePath2D = true
        const amplitude = 64;
        const wavelength = 64;
        for (let i = 0; i < 5; i++) {
          const x1 = 0 + (i * wavelength);
          const y1 = 128;
          const x2 = x1 + wavelength / 4;
          const y2 = y1 - amplitude;
          const x3 = x1 + 3 * wavelength / 4;
          const y3 = y1 + amplitude;
          const x4 = x1 + wavelength;
          const y4 = y1;
          context.moveTo(x1, y1);
          path2D.bezierCurveTo(x2, y2, x3, y3, x4, y4);
        }
        context.stroke(path2D);
      },
      startAnimationFrame() {
        this.taskId = this.canvasContext!.requestAnimationFrame((timestamp : number) => {
          this.testFrameCount++
          this.updateFPS(timestamp)
          this.startAnimationFrame()
        })
      },
      stopAnimationFrame() {
        this.canvasContext!.cancelAnimationFrame(this.taskId)
        this.taskId = 0
      },
      updateFPS(timestamp : number) {
        this.frameCount++
        if (timestamp - this.lastTime >= 1000) {
          const timeOfFrame = (1000 / this.frameCount)
          this.renderingContext!.clearRect(0, 0, this.canvasWidth, this.canvasHeight)
          this.renderingContext!.fillText(`${this.frameCount} / ${timeOfFrame.toFixed(3)}ms`, 10, 18)
          this.frameCount = 0
          this.lastTime = timestamp
        }
      },
      testCreateContextAsync() {
        uni.createCanvasContextAsync({
          id: 'canvas',
          component: this,
          success: () => {
            this.testCanvasCtx1 = true
          }
        })

        // no `component` param
        uni.createCanvasContextAsync({
          id: 'canvas',
          success: () => {
            this.testCanvasCtx2 = true
          }
        })
      },
      onChildReady() {
        const childInstance = (this.$refs['canvas-child'] as ComponentPublicInstance);
        this.testCounter = childInstance.$data['testCounter'] as number;
      }
    }
  })

export default __sfc__
function GenPagesComponentCanvasCanvasRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
const _component_navigator = resolveComponent("navigator")
const _component_canvas_child = resolveComponent("canvas-child")

  return createElementVNode("view", utsMapOf({
    class: "page",
    id: "page-canvas"
  }), [
    createElementVNode("canvas", utsMapOf({
      id: "canvas",
      class: "canvas-element"
    })),
    createElementVNode("scroll-view", utsMapOf({ class: "scroll-view" }), [
      createElementVNode("button", utsMapOf({
        class: "canvas-drawing-button",
        id: "toDataURL",
        onClick: _ctx.canvasToDataURL
      }), "canvasToDataURL", 8 /* PROPS */, ["onClick"]),
      _ctx.dataBase64.length>0
        ? createElementVNode("view", utsMapOf({
            key: 0,
            class: "text-group"
          }), [
            createElementVNode("text", null, "canvasToDataURL:"),
            createElementVNode("text", null, toDisplayString(_ctx.dataBase64.slice(0,22)) + "...", 1 /* TEXT */)
          ])
        : createCommentVNode("v-if", true),
      createElementVNode("button", utsMapOf({ onClick: _ctx.onCreateImage }), "createImage", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({ onClick: _ctx.onCreatePath2D }), "createPath2D", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({ onClick: _ctx.startAnimationFrame }), "requestAnimationFrame", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({ onClick: _ctx.stopAnimationFrame }), "cancelAnimationFrame", 8 /* PROPS */, ["onClick"]),
      createElementVNode("view", utsMapOf({
        style: normalizeStyle(utsMapOf({"padding":"8px 10px"}))
      }), "CanvasContext API 演示", 4 /* STYLE */),
      createVNode(_component_navigator, utsMapOf({ url: "./canvas-context" }), utsMapOf({
        default: withSlotCtx((): any[] => [
          createElementVNode("button", null, "CanvasContext API")
        ]),
        _: 1 /* STABLE */
      })),
      createElementVNode("view", utsMapOf({ class: "text-group" }), [
        createElementVNode("text", null, "获取 CanvasContext 结果："),
        createElementVNode("text", utsMapOf({ id: "testCanvasContext" }), toDisplayString(_ctx.testCanvasContext), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "text-group" }), [
        createElementVNode("text", null, "测试 ToDataURL 结果："),
        createElementVNode("text", utsMapOf({ id: "testToDataURLResult" }), toDisplayString(_ctx.testToDataURLResult), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "text-group" }), [
        createElementVNode("text", null, "测试 createImage 结果："),
        createElementVNode("text", utsMapOf({ id: "testCreateImage" }), toDisplayString(_ctx.testCreateImage), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "text-group" }), [
        createElementVNode("text", null, "测试 createPath2D 结果："),
        createElementVNode("text", utsMapOf({ id: "testCreatePath2D" }), toDisplayString(_ctx.testCreatePath2D), 1 /* TEXT */)
      ]),
      createElementVNode("view", utsMapOf({ class: "text-group" }), [
        createElementVNode("text", null, "测试 createCanvasContextAsync 结果："),
        createElementVNode("view", utsMapOf({
          onClick: _ctx.testCreateContextAsync,
          id: "createCanvasContextAsync"
        }), toDisplayString(_ctx.testCanvasCtx), 9 /* TEXT, PROPS */, ["onClick"])
      ]),
      createVNode(_component_canvas_child, utsMapOf({ ref: "canvas-child" }), null, 512 /* NEED_PATCH */)
    ])
  ])
}
const GenPagesComponentCanvasCanvasStyles = [utsMapOf([["page", padStyleMapOf(utsMapOf([["flex", 1], ["height", "100%"], ["overflow", "hidden"]]))], ["scroll-view", padStyleMapOf(utsMapOf([["flex", 1]]))], ["canvas-element", padStyleMapOf(utsMapOf([["width", "100%"], ["height", 250], ["backgroundColor", "#ffffff"]]))], ["btn-to-image", padStyleMapOf(utsMapOf([["marginTop", 10], ["marginRight", 10], ["marginBottom", 10], ["marginLeft", 10]]))], ["text-group", padStyleMapOf(utsMapOf([["display", "flex"], ["flexFlow", "row nowrap"], ["justifyContent", "space-between"], ["alignItems", "center"], ["paddingTop", 8], ["paddingRight", 10], ["paddingBottom", 8], ["paddingLeft", 10]]))]])]
