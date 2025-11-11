import { ItemChildType } from './item.type.uts';
const __sfc__ = defineComponent({
    __name: 'uni-collapse',
    name: "UniCollapse",
    props: {
        accordion: { type: Boolean, default: true }
    },
    setup(__props): any | null {
        const __ins = getCurrentInstance()!;
        const _ctx = __ins.proxy as InstanceType<typeof __sfc__>;
        const _cache = __ins.renderCache;
        const props = __props;
        let child_nodes = [] as ItemChildType[];
        function registerChild(child: ItemChildType) {
            child_nodes.push(child);
        }
        // 关闭所有
        function toggle(elId: string) {
            // 开启手风琴效果才回关闭其他
            if (props.accordion && child_nodes.length > 0) {
                child_nodes.forEach((item: ItemChildType) => {
                    const is_open = item.is_open.value as boolean;
                    if (is_open && item.elId != elId) {
                        item.is_open.value = false;
                        item.openOrClose(false);
                    }
                });
            }
        }
        provide('uni-collapse-register-child', registerChild);
        provide('k-collapse-child-toggle', toggle);
        return (): any | null => {
            return createElementVNode("view", null, [
                renderSlot(_ctx.$slots, "default")
            ]);
        };
    }
});
export default __sfc__;
export type UniCollapseComponentPublicInstance = InstanceType<typeof __sfc__>;
const GenComponentsUniCollapseUniCollapseStyles = [];
//# sourceMappingURL=uni-collapse.vue.map