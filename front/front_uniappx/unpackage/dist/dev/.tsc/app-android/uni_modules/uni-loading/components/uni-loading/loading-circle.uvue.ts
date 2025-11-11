
  import { easeInOutCubic } from './util'
  let elId = 0
  const __sfc__ = defineComponent({
    name: "circle",
    props: {
      speed: {
        type: Number,
        default: 16,
      },
      size: {
        type: Number,
        default: 20,
      },
      color: {
        type: String,
        default: '#666',
      }
    },
    data() {
      // 防止多调用，随机元素id
      elId += 1
      const elID = `Uni_Load_Circle_${elId}`
      return {
        elId: elID,
        timer: 0,
      };
    },
    computed: {
      iconsSize() : number {
        return (this.size / 10) +1
      }
    },
    mounted() {

      this.init()

    },
    unmounted() {
      // 组件卸载时，需要卸载定时器，优化性能，防止页面卡死
      clearInterval(this.timer)
    },
    methods: {
      /**
       * 初始化圆环
       */
      init() {
        const refs = this.$refs[this.elId] as UniElement
        let ctx = refs.getDrawableContext()!
        this.build_circular(ctx)
      },

      /**
       * 构建圆环动画
       */
      build_circular(ctx : DrawableContext) {
        let startAngle = 0;
        let rotate = 0;
        const ARC_LENGTH = 359;
        const center = this.size / 2; // 圆心
        const lineWidth = this.size / 10; // 圆环宽度
        const duration = 1200; // 动画持续时间
        const interval = this.speed; // 定时器间隔（大约 60 帧每秒）

        // 使圆环过度更自然，不必运动到底
        const ARC_MAX = 358
        let startTime = 0;
        let foreward_end = 0 // 正传
        let reversal_end = ARC_MAX // 反转
        function pogress_time() : number {
          const currentTime = Date.now();
          // 运动时间计算
          const elapsedTime = currentTime - startTime;
          const progress = elapsedTime / duration;
          // 动画缓动
          const easedProgress = easeInOutCubic(progress);
          return easedProgress
        }
        const draw = () => {

          ctx.reset();
          ctx.beginPath();

          if (reversal_end == ARC_MAX) {
            foreward_end = Math.min(pogress_time() * ARC_LENGTH, ARC_LENGTH); // 限制 end 的最大值为 ARC_LENGTH
            if (foreward_end >= ARC_MAX) {
              reversal_end = 0
              foreward_end = ARC_MAX
              startTime = Date.now();
            }
          }

          if (foreward_end == ARC_MAX) {
            reversal_end = Math.min(pogress_time() * ARC_LENGTH, ARC_LENGTH);
            if (reversal_end >= ARC_MAX) {
              reversal_end = ARC_MAX
              foreward_end = 0
              startTime = Date.now();
            }
          }

          ctx.arc(
            center,
            center,
            center - lineWidth,
            startAngle + rotate + (reversal_end * Math.PI / 180),
            startAngle + rotate + (foreward_end * Math.PI / 180)
          );
          ctx.lineWidth = lineWidth;
          const fillColor = (this.color !== '' ? this.color : '#666').toString();
          ctx.strokeStyle = fillColor;
          ctx.stroke();
          ctx.update();
          rotate += 0.05; // 旋转速度
        }

        this.timer = setInterval(() => draw(), interval);
      }

    }
  })

export default __sfc__
function GenUniModulesUniLoadingComponentsUniLoadingLoadingCircleRender(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", utsMapOf({
    ref: _ctx.elId,
    class: "block",
    style: normalizeStyle(utsMapOf({width:_ctx.size+'px',height:_ctx.size+'px'}))
  }), null, 4 /* STYLE */)
}
const GenUniModulesUniLoadingComponentsUniLoadingLoadingCircleStyles = [utsMapOf([["block", padStyleMapOf(utsMapOf([["width", 50], ["height", 50]]))]])]
