const __sfc__ = defineComponent({
  __name: 'ball',
  setup(__props): any | null {
const __ins = getCurrentInstance()!;
const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
const _cache = __ins.renderCache;

  let fpsString = ref("-/-ms")
  let ballSpeed = ref(3)
  let ballLayer = ref(3)
  let ballInlayer = ref(20)

  class Ball  implements IUTSSourceMap{
// @ts-expect-error 
override __$getOriginalPosition(): UTSSourceMapPosition { return new UTSSourceMapPosition("Ball", "pages/component/canvas/canvas/ball.uvue", 32, 9);}

    private width : number
    private height : number
    public x : number
    public y : number
    public vx : number
    public vy : number
    public radius : number = 5

    constructor(w : number, h : number, x : number, y : number, vx : number, vy : number) {
      this.width = w
      this.height = h
      this.x = x
      this.y = y
      this.vx = vx
      this.vy = vy
    }

    move() {
      this.x += this.vx
      this.y += this.vy

      // 边框反弹
      if (this.x < this.radius) {
        this.vx = Math.abs(this.vx)
        return
      }
      if (this.x > this.width - this.radius) {
        this.vx = -Math.abs(this.vx)
      }
      if (this.y < this.radius) {
        this.vy = Math.abs(this.vy)
        return
      }
      if (this.y > this.height - this.radius) {
        this.vy = -Math.abs(this.vy)
      }
    }
  }

  class BallAnimation  implements IUTSSourceMap{
// @ts-expect-error 
override __$getOriginalPosition(): UTSSourceMapPosition { return new UTSSourceMapPosition("BallAnimation", "pages/component/canvas/canvas/ball.uvue", 72, 9);}

    private ctx : CanvasRenderingContext2D
    private canvasContext : CanvasContext
    private ballList : Array<Ball> = []
    private _speed = 3
    private _layer = 3
    private _ballInlayer = 20
    private runningFlag : boolean = false

    private _animateTaskId : number = 0
    private frameCount = 0
    private lastTime = 0

    constructor(canvasContext : CanvasContext) {
      this.canvasContext = canvasContext
      this.ctx = canvasContext.getContext('2d') as CanvasRenderingContext2D
      this.initBall()
      this.ctx.fillStyle = '#007AFF'
    }

    private getDistance(x : number, y : number) : number {
      return Math.pow((Math.pow(x, 2) + Math.pow(y, 2)), 0.5)
    }

    private initBall() {
      const canvasWidth = this.ctx.canvas.offsetWidth;
      const canvasHeight = this.ctx.canvas.offsetHeight;
      for (let i = 0; i < this._layer; i++) {
        let radius = this.getDistance(canvasWidth / 2, canvasHeight / 2) / this._layer * i
        for (let j = 0; j < this._ballInlayer; j++) {
          let deg = j * 2 * Math.PI / this._ballInlayer,
            sin = Math.sin(deg),
            cos = Math.cos(deg),
            x = radius * cos + canvasWidth / 2,
            y = radius * sin + canvasHeight / 2,
            vx = this.speed * cos,
            vy = this.speed * sin
          this.ballList.push(new Ball(canvasWidth, canvasHeight, x, y, vx, vy))
        }
      }
    }

    private reset() {
      this.ballList.length = 0
      this.initBall()
    }

    public get speed() : number {
      return this._speed
    }
    public set speed(value : number) {
      this._speed = value
      this.reset()
    }

    public get layer() : number {
      return this._layer
    }
    public set layer(value : number) {
      this._layer = value
      this.reset()
    }

    public get inLayer() : number {
      return this._ballInlayer
    }
    public set inLayer(value : number) {
      this._ballInlayer = value
      this.reset()
    }

    public animate() {
      this.ctx.clearRect(0, 0, this.ctx.canvas.offsetWidth, this.ctx.canvas.offsetHeight)
      this.ballList.forEach((item) => {
        item.move()
        this.ctx.beginPath()
        this.ctx.arc(item.x, item.y, item.radius, 0, 2 * Math.PI)
        // this.ctx.ellipse(item.x, item.y, item.radius, item.radius, 0, 0, Math.PI * 2)
        this.ctx.fill()
      })

      if (!this.runningFlag) {
        return
      }
      this._animateTaskId = this.canvasContext.requestAnimationFrame((timestamp : number) => {
        this.animate()
        this.updateFPS(timestamp)
      })
    }

    updateFPS(timestamp : number) {
      this.frameCount++
      if (timestamp - this.lastTime >= 1000) {
        const timeOfFrame = (1000 / this.frameCount)
        fpsString.value = `${this.frameCount} / ${timeOfFrame.toFixed(3)}ms`
        this.frameCount = 0
        this.lastTime = timestamp
      }
    }

    start() {
      this.canvasContext.cancelAnimationFrame(this._animateTaskId)
      this.runningFlag = true
      this.animate()
    }

    stop() {
      this.runningFlag = false
      this.canvasContext.cancelAnimationFrame(this._animateTaskId)
    }
  }

  let animation : BallAnimation | null = null
  onReady(() => {
    uni.createCanvasContextAsync({
      id: 'canvas',
      // component: this,
      success: (context : CanvasContext) => {
        const renderingContext = context.getContext('2d')!;
        const canvas = renderingContext.canvas;

        const dpr = uni.getWindowInfo().pixelRatio;
        canvas.width = canvas.offsetWidth * dpr
        canvas.height = canvas.offsetHeight * dpr
        renderingContext.scale(dpr, dpr)

        animation = new BallAnimation(context)
        animation?.start()
      }
    })
  })

  let lessClick = (type : string) => {
    if (type == 'speed') {
      animation!.speed--;
      ballSpeed.value = animation!.speed
    } else if (type == 'layer') {
      animation!.layer -= 5;
      ballLayer.value = animation!.layer
    } else if (type == 'inLayer') {
      animation!.inLayer -= 5;
      ballInlayer.value = animation!.inLayer
    }
  }

  let plusClick = (type : string) => {
    if (type == 'speed') {
      animation!.speed++;
      ballSpeed.value = animation!.speed
    } else if (type == 'layer') {
      animation!.layer += 5;
      ballLayer.value = animation!.layer
    } else if (type == 'inLayer') {
      animation!.inLayer += 5;
      ballInlayer.value = animation!.inLayer
    }
  }

  onUnload(() => {
    animation?.stop()
    animation = null
  })

  onPageShow(() => {
    animation?.start()
  })

  onPageHide(() => {
    animation?.stop()
  })

return (): any | null => {

  return createElementVNode("view", utsMapOf({ class: "page-body" }), [
    createElementVNode("canvas", utsMapOf({
      id: "canvas",
      class: "canvas"
    })),
    createElementVNode("text", utsMapOf({ class: "fps" }), "FPS: " + toDisplayString(unref(fpsString)), 1 /* TEXT */),
    createElementVNode("view", utsMapOf({ class: "item" }), [
      createElementVNode("text", utsMapOf({ class: "item-label" }), "Speed"),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {unref(lessClick)('speed')}
      }), "-", 8 /* PROPS */, ["onClick"]),
      createElementVNode("text", utsMapOf({ class: "item-value" }), toDisplayString(unref(ballSpeed)), 1 /* TEXT */),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {unref(plusClick)('speed')}
      }), "+", 8 /* PROPS */, ["onClick"])
    ]),
    createElementVNode("view", utsMapOf({ class: "item" }), [
      createElementVNode("text", utsMapOf({ class: "item-label" }), "Layer"),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {unref(lessClick)('layer')}
      }), "-", 8 /* PROPS */, ["onClick"]),
      createElementVNode("text", utsMapOf({ class: "item-value" }), toDisplayString(unref(ballLayer)), 1 /* TEXT */),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {unref(plusClick)('layer')}
      }), "+", 8 /* PROPS */, ["onClick"])
    ]),
    createElementVNode("view", utsMapOf({ class: "item" }), [
      createElementVNode("text", utsMapOf({ class: "item-label" }), "Inlayer"),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {unref(lessClick)('inLayer')}
      }), "-", 8 /* PROPS */, ["onClick"]),
      createElementVNode("text", utsMapOf({ class: "item-value" }), toDisplayString(unref(ballInlayer)), 1 /* TEXT */),
      createElementVNode("button", utsMapOf({
        size: "mini",
        onClick: () => {unref(plusClick)('inLayer')}
      }), "+", 8 /* PROPS */, ["onClick"])
    ])
  ])
}
}

})
export default __sfc__
const GenPagesComponentCanvasCanvasBallStyles = [utsMapOf([["page-body-wrapper", padStyleMapOf(utsMapOf([["textAlign", "center"]]))], ["page-body", padStyleMapOf(utsMapOf([["paddingTop", 15], ["paddingRight", 15], ["paddingBottom", 15], ["paddingLeft", 15]]))], ["canvas", padStyleMapOf(utsMapOf([["width", 300], ["height", 300], ["marginTop", "auto"], ["marginRight", "auto"], ["marginBottom", "auto"], ["marginLeft", "auto"], ["backgroundColor", "#ffffff"]]))], ["fps", padStyleMapOf(utsMapOf([["marginTop", 30], ["marginBottom", 20]]))], ["item", padStyleMapOf(utsMapOf([["flexDirection", "row"], ["alignItems", "center"], ["marginTop", 10]]))], ["item-label", padStyleMapOf(utsMapOf([["marginRight", "auto"]]))], ["item-value", padStyleMapOf(utsMapOf([["color", "#6495ED"], ["width", 50], ["textAlign", "center"]]))]])]
