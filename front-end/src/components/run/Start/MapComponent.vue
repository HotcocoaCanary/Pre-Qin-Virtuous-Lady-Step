<template>
  <div class="content">
    <div id="container"></div>
  </div>
</template>

<script setup>
import {onMounted, onUnmounted, ref} from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import {runClockService} from "@/api/run.js";

let map = null;
let marker = null;
let circle = null;
let infoWindow = null;

// 定义props，接收经纬度参数
const props = defineProps({
  location: {
    type: Object,
    required: true
  },
  startTime:{
    type: Date(),
  }
});
props.startTime=new Date();

// 定义信息窗口内容
const infoWindowContent = `
  <div class="custom-infowindow" style="background-color: white; padding: 10px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); font-family: 'Arial', sans-serif;">
  <label style="font-weight: bold; margin-bottom: 10px; display: block;">打卡</label>
  <div class="input-item" style="margin-bottom: 10px;">
    <div class="input-item-prepend" style="margin-right: 5px;">
      <span class="input-item-text" style="font-weight: bold;">是否在圈内</span>
    </div>
    <input id="lnglat" type="text" readonly style="border: 1px solid #ddd; border-radius: 4px; padding: 5px; font-size: 14px;" />
  </div>
  <input id="checkIn" type="button" class="btn" value="打卡签到" style="background-color: #007bff; color: white; border: none; border-radius: 4px; padding: 5px 10px; cursor: pointer; font-size: 14px;" />
</div>
`;

onMounted(() => {
  window._AMapSecurityConfig = {
    securityJsCode: "4827157a8f21474940bda160cff56dc6",
  };
  AMapLoader.load({
    key: "30c315e38d696ee544ff4f9e2e53c4c7", // 申请好的Web端开发者Key，首次调用 load 时必填
    version: "2.0", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
    plugins: ["AMap.Scale"], //需要使用的的插件列表，如比例尺'AMap.Scale'，支持添加多个如：['...','...']
  })
      .then((AMap) => {
        map = new AMap.Map("container", {
          // 设置地图容器id
          viewMode: "3D", // 是否为3D地图模式
          zoom: 11, // 初始化地图级别
          center: props.location.my, // 使用props中的my作为地图中心点
          mapStyle: "amap://styles/fresh", //设置地图的显示样式
        });

        // 创建标记并添加到地图上
        marker = new AMap.Marker({
          position: props.location.my, // 标记的位置
          title: '指定位置' // 标记的标题
        });
        map.add(marker);

        // 创建圆并添加到地图上
        circle = new AMap.Circle({
          center: props.location.goal,
          radius: 1000,
          fillOpacity: 0.2,
          strokeWeight: 1
        });
        map.add(circle);

        // 创建信息窗口
        infoWindow = new AMap.InfoWindow({
          content: infoWindowContent,
          offset: new AMap.Pixel(0, -30)
        });

        // 打卡签到事件
        const checkIn = () => {
          AMap.plugin('AMap.Geolocation', () => {
            const geolocation = new AMap.Geolocation({
              enableHighAccuracy: true, // 是否使用高精度定位，默认:true
              timeout: 10000, // 超过10秒后停止定位，默认：5s
              buttonPosition: 'RB', // 定位按钮的停靠位置
              buttonOffset: new AMap.Pixel(10, 20), // 定位按钮与设置的停靠位置的偏移量，默认：Pixel(10, 20)
              zoomToAccuracy: true, // 定位成功后是否自动调整地图视野到定位点
            });

            map.add(geolocation);
            geolocation.getCurrentPosition((status, result) => {
              if (status === 'complete') {
                onComplete(result);
              } else {
                onError(result);
              }
            });
          });

          const onComplete = async (data) => {
            const distance = AMap.GeometryUtil.distance(data.position, circle.getCenter());
            const isInside = distance <= circle.getRadius();
            const lnglatInput = document.getElementById('lnglat');
            lnglatInput.value = isInside ? '是' : '否';
            if (isInside) {
              geocoder.getAddress(props.location.my, (status, result) => {
                if (status === 'complete' && result.regeocode) {
                  const startAddress = result.regeocode.formattedAddress;
                  geocoder.getAddress(data.position, (status, result) => {
                    if (status === 'complete' && result.regecode) {
                      const endAddress = result.regeocode.formattedAddress;
                      const duration = (new Date() - props.startTime) / 1000; // 用时，单位秒

                      const LocationData = {
                        startAddress: startAddress,
                        endAddress: endAddress,
                        distance: distance,
                        duration: duration
                      };

                      // 发送LocationData到服务器或进行其他处理
                      console.log(LocationData);
                      // 这里可以调用runClockService方法将数据发送到服务器
                      runClockService(LocationData);
                    }
                  });
                }
              });
            }
          };

          const onError = (data) => {
            console.log(data);
          };
        };

        // 绑定打卡签到事件
        marker.on('click', () => {
          infoWindow.open(map, marker.getPosition());
          document.getElementById('checkIn').addEventListener('click', checkIn);
        });
      })
      .catch((e) => {
        console.log(e);
      });
});


onUnmounted(() => {
  map?.destroy();
  marker?.destroy();
  circle?.destroy();
});
</script>


<style scoped>
#container {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 800px;
}
</style>
