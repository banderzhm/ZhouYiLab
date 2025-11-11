const __sfc__ = defineComponent({});
export default __sfc__;
function GenUniModulesTestImagePathComponentsTestImagePathTestImagePathRender(this: InstanceType<typeof __sfc__>): any | null {
    const _ctx = this;
    const _cache = this.$.renderCache;
    return createElementVNode("view", null, [
        createElementVNode("view", null, [
            createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "uni_modules的组件模板相对路径静态引入插件内static目录图片"),
            createElementVNode("view", utsMapOf({
                class: "uni-center",
                style: normalizeStyle(utsMapOf({ "background": "#FFFFFF" }))
            }), [
                createElementVNode("image", utsMapOf({
                    class: "image",
                    mode: "widthFix",
                    src: _imports_0
                }))
            ], 4 /* STYLE */)
        ]),
        createElementVNode("view", null, [
            createElementVNode("text", utsMapOf({ class: "uni-subtitle-text" }), "uni_modules的组件模板绝对路径静态引入插件内static目录图片"),
            createElementVNode("view", utsMapOf({
                class: "uni-center",
                style: normalizeStyle(utsMapOf({ "background": "#FFFFFF" }))
            }), [
                createElementVNode("image", utsMapOf({
                    class: "image",
                    mode: "widthFix",
                    src: "/uni_modules/test-image-path/static/test-image-path.png"
                }))
            ], 4 /* STYLE */)
        ])
    ]);
}
export type TestImagePathComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenUniModulesTestImagePathComponentsTestImagePathTestImagePathStyles = [utsMapOf([["image", padStyleMapOf(utsMapOf([["marginTop", 20], ["marginRight", "auto"], ["marginBottom", 20], ["marginLeft", "auto"], ["width", 100]]))]])];
import _imports_0 from '../../static/test-image-path.png';
//# sourceMappingURL=test-image-path.vue.map