
	const __sfc__ = defineComponent({
		data() {
			return {
        arrayBufferToBase64Res:'',
        base64ToArrayBufferRes:''
			}
		},
		onLoad() {

		},
		methods: {
			arrayBufferToBase64() {
				const arraybuffer = new ArrayBuffer(3)
				const uint8 = new Uint8Array(arraybuffer)
				uint8.set([11, 22, 33])
				const base64 = uni.arrayBufferToBase64(arraybuffer)
				console.log(base64, " at pages/API/base64/base64.uvue:27") //CxYh
        this.arrayBufferToBase64Res = base64
        uni.showToast({
           title:this.arrayBufferToBase64Res
        })
			},
			base64ToArrayBuffer() {
				const base64 = 'CxYh'
				const arrayBuffer = uni.base64ToArrayBuffer(base64)
				const uint8 = new Uint8Array(arrayBuffer)
				console.log(uint8.toString(), " at pages/API/base64/base64.uvue:37")//11, 22, 33
        this.base64ToArrayBufferRes = uint8.toString()
        uni.showToast({
           title:this.base64ToArrayBufferRes
        })
			}

		}
	})

export default __sfc__
function GenPagesAPIBase64Base64Render(this: InstanceType<typeof __sfc__>): any | null {
const _ctx = this
const _cache = this.$.renderCache
  return createElementVNode("view", null, [
    createElementVNode("view", null, [
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.arrayBufferToBase64
      }), " arrayBufferToBase64 ", 8 /* PROPS */, ["onClick"]),
      createElementVNode("button", utsMapOf({
        class: "btnstyle",
        type: "primary",
        onClick: _ctx.base64ToArrayBuffer
      }), " base64ToArrayBuffer ", 8 /* PROPS */, ["onClick"])
    ])
  ])
}
const GenPagesAPIBase64Base64Styles = [utsMapOf([["logo", padStyleMapOf(utsMapOf([["height", 100], ["width", 100], ["marginTop", 100], ["marginRight", "auto"], ["marginBottom", 25], ["marginLeft", "auto"]]))], ["title", padStyleMapOf(utsMapOf([["fontSize", 18], ["color", "#8f8f94"], ["textAlign", "center"]]))], ["btnstyle", padStyleMapOf(utsMapOf([["marginTop", 4], ["marginRight", 4], ["marginBottom", 4], ["marginLeft", 4]]))]])]
