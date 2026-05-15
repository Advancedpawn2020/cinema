<template>
    <div class="sidebar">
        <el-menu
            class="sidebar-el-menu"
            :default-active="onRoutes"
            :collapse="collapse"
            background-color="#324157"
            text-color="#bfcbd9"
            active-text-color="#20a0ff"
            unique-opened
            router
        >
            <!-- :default-active 表示当前激活菜单的 index
                 其实，即使不加这个属性，也能正常显示选中的item变亮，但是之所以要加这个属性，是为了浏览器刷新后，仍然可以定位到之前选中的路由。
                 如果没有多层嵌套路由的话，可以令:default-active="$route.path",但是如果有多层嵌套，建议在计算属性里，更新数据
                 将当前路由地址的/去掉
            -->
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="item.index" :key="item.index">
                        <!--slot插槽，也就是槽，是组件的一块HTML模板，这块模板显示不显示，以及怎样显示由父组件来决定。
                        插槽用于决定将所携带的内容，插入到指定的某个位置，从而使模板分块，具有模块化的特质和更大的重用性。
                        插槽显不显示、怎样显示是由父组件来控制的，而插槽在哪里显示就由子组件来进行控制
                        在向具名插槽提供内容的时候，我们可以在一个父组件的 <template> 元素上使用 slot特性
                        这里指，将名为title的slot内容分发到名为slot的父组件标签中
                        这里的父组件是element-ui的el-menu组件
                        -->
                        <template slot="title">
                            <i :class="item.icon"></i>
                            <span slot="title">{{ item.title }}</span>
                        </template>
                        <!--二级子菜单-->
                        <template v-for="subItem in item.subs">
                            <el-submenu
                                v-if="subItem.subs"
                                :index="subItem.index"
                                :key="subItem.index"
                            >
                                <template slot="title">{{ subItem.title }}</template>
                                <el-menu-item
                                    v-for="(threeItem,i) in subItem.subs"
                                    :key="i"
                                    :index="threeItem.index"
                                >
                                    {{ threeItem.title }}
                                </el-menu-item>
                            </el-submenu>
                            <el-menu-item
                                v-else
                                :index="subItem.index"
                                :key="subItem.index"
                            >{{ subItem.title }}</el-menu-item>
                        </template>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="item.index" :key="item.index">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
import bus from '../common/bus';
export default {
    data() {
        return {
            collapse: false,
            items: [
                {
                    icon: 'el-icon-lx-home',
                    index: 'dashboard',
                    title: '系统首页'
                },
                {
                    icon: 'el-icon-lx-people',
                    index: 'member',
                    title: '会员管理',
                    subs: [
                        {
                            index: 'member',
                            title: '会员列表'
                        }
                        ]
                },
                {
                    icon: 'el-icon-lx-copy',
                    index: 'card',
                    title: '会员卡管理',
                    subs: [
                        {
                            index: 'card',
                            title: '会员卡列表'
                        },
                        {
                            index: 'recharge',
                            title: '充值管理'
                        },
                        {
                            index: 'consume',
                            title: '消费管理'
                        },
                        {
                            index: 'record',
                            title: '交易记录'
                        }
                    ]
                },
                {
                    icon: 'el-icon-lx-cascades',
                    index: 'integral',
                    title: '积分管理',
                    subs: [
                        {
                            index: 'integral',
                            title: '积分列表'
                        }
                    ]
                },
                {
                    icon: 'el-icon-lx-calendar',
                    index: 'care',
                    title: '会员关怀'
                },
                {
                    icon: 'el-icon-pie-chart',
                    index: 'charts',
                    title: '统计数据'
                }
            ]
        };
    },
    //计算属性可用于快速计算视图（View）中显示的属性。这些计算将被缓存，并且只在需要时更新。
    computed: {
        onRoutes() {
            return this.$route.path.replace('/', '');
        }
    },
    created() {
        // 通过 Event Bus 进行组件间通信，来折叠侧边栏
        bus.$on('collapse', msg => {
            this.collapse = msg;
            bus.$emit('collapse-content', msg);
        });
    }
};
</script>

<style scoped>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}
.sidebar > ul {
    height: 100%;
}
</style>
