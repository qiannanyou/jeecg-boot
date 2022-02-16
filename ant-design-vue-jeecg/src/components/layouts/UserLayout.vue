<template>
  <div id="userLayout" :class="['user-layout-wrapper', device]">
    <div class="container">
      <div class="loginBg">
        <div class="top">
          <div class="header">
            <a href="/">
              <img src="~@/assets/logo.png" class="logo" alt="logo">
              <span class="title">叁木森管理平台</span>
            </a>
          </div>
          <div class="desc">
            博学审问慎思明辨笃行-给我一个机会，我必全力以赴
          </div>
        </div>

        <route-view></route-view>
      </div>
      <div class="footer">
        <div class="copyright">
          Copyright &copy; 2022 Fujian Sanmusen Network Technology Co.,
          Ltd.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import RouteView from "@/components/layouts/RouteView"
  import {
    mixinDevice
  } from '@/utils/mixin.js'

  export default {
    name: "UserLayout",
    components: {
      RouteView
    },
    mixins: [mixinDevice],
    data() {
      return {}
    },
    mounted() {
      document.body.classList.add('userLayout')
    },
    beforeDestroy() {
      document.body.classList.remove('userLayout')
    },
  }
</script>

<style lang="less" scoped>
  #userLayout.user-layout-wrapper {
    height: 100%;

    &.mobile {
      .container {
        .main {
          max-width: 368px;
          width: 98%;
        }
      }
    }

    .container {
      height: 100%;
      min-height: 100%;
      background-image: url(~@/assets/background.jpg);
      background-size: cover;
      background-position: 50%;
      position: relative;
      padding: 110px 0 144px;

      a {
        text-decoration: none;
      }

      .loginBg {
        margin: auto;
        min-width: 260px;
        width: calc(368px + 30px);
        background: rgba(240, 242, 245, .75);
        padding: 20px 0;
        border-radius: 8px;

        .top {
          text-align: center;

          .header {
            height: 44px;
            line-height: 44px;

            .badge {
              position: absolute;
              display: inline-block;
              line-height: 1;
              vertical-align: middle;
              margin-left: -12px;
              margin-top: -10px;
              opacity: 0.8;
            }

            .logo {
              height: 44px;
              vertical-align: top;
              margin-right: 16px;
              border-style: none;
            }

            .title {
              font-size: 33px;
              color: rgba(0, 0, 0, .85);
              font-family: "Chinese Quote", -apple-system, BlinkMacSystemFont, "Segoe UI", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "Helvetica Neue", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
              font-weight: 600;
              position: relative;
              top: 2px;
            }
          }

          .desc {
            font-size: 14px;
            color: rgba(0, 0, 0, 0.45);
            margin-top: 12px;
            margin-bottom: 30px;
          }
        }
      }

      .main {
        min-width: 260px;
        width: 368px;
        margin: 0 auto;
      }

      .footer {
        position: absolute;
        width: 100%;
        bottom: 0;
        padding: 0 16px;
        margin: 48px 0 24px;
        text-align: center;

        .links {
          margin-bottom: 8px;
          font-size: 14px;

          a {
            color: rgba(240, 242, 245, .55);
            transition: all 0.3s;

            &:not(:last-child) {
              margin-right: 40px;
            }
          }
        }

        .copyright {
          color: rgba(240, 242, 245, .55);
          font-size: 14px;
        }
      }
    }
  }
</style>